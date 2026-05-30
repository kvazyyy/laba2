package Reports;

import Reports.Formats.DetailedReportFormat;
import Reports.Formats.SummaryReportFormat;

import java.util.ArrayList;
import java.util.List;

public class ReportFormatFactory {
    private final List<IReportFormat> formats = new ArrayList<>();

    public ReportFormatFactory register(IReportFormat format) {
        formats.add(format);
        return this;
    }

    public IReportFormat findFormat(String type) {
        String requested = type == null || type.isBlank() ? "summary" : type.trim();
        return formats.stream()
                .filter(format -> format.getName().equalsIgnoreCase(requested))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неподдерживаемый тип отчета: " + type));
    }

    public static ReportFormatFactory defaultFactory() {
        return new ReportFormatFactory()
                .register(new SummaryReportFormat())
                .register(new DetailedReportFormat());
    }
}
