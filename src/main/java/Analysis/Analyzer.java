package Analysis;

import Entities.Mission;
import Logging.AppLogger;
import Parsers.IParser;
import Parsers.ParserFactory;
import Reports.IReportFormat;
import Reports.ReportFormatFactory;
import Validation.IValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Analyzer implements IAnalyzer {
    private final ParserFactory parserFactory;
    private final ReportFormatFactory reportFormatFactory;
    private final IValidator validator;

    public Analyzer(ParserFactory parserFactory, ReportFormatFactory reportFormatFactory, IValidator validator) {
        this.parserFactory = parserFactory;
        this.reportFormatFactory = reportFormatFactory;
        this.validator = validator;
    }

    @Override
    public AnalysisResult analyze(Path path, String reportType) {
        try {
            String fileName = path.getFileName().toString();
            String content = Files.readString(path, StandardCharsets.UTF_8);
            IParser parser = parserFactory.findParser(fileName, content);
            Mission mission = parser.parse(fileName, content);
            validator.validate(mission);
            IReportFormat format = reportFormatFactory.findFormat(reportType);
            String report = format.render(mission);
            AppLogger.info("Файл обработан: " + fileName + ", формат: " + parser.getName() + ", отчет: " + format.getName());
            return new AnalysisResult(parser.getName(), format.getName(), mission, report);
        } catch (IOException exception) {
            throw new IllegalArgumentException("Не удалось прочитать файл: " + path, exception);
        }
    }

    @Override
    public List<AnalysisResult> analyzeBatch(Path folder, String reportType) {
        List<AnalysisResult> results = new ArrayList<>();
        try (Stream<Path> stream = Files.list(folder)) {
            stream.filter(Files::isRegularFile).forEach(path -> {
                try {
                    results.add(analyze(path, reportType));
                } catch (RuntimeException exception) {
                    AppLogger.error("Файл пропущен: " + path.getFileName() + " — " + exception.getMessage());
                }
            });
        } catch (IOException exception) {
            throw new IllegalArgumentException("Не удалось прочитать папку: " + folder, exception);
        }
        return results;
    }
}
