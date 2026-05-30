package Analysis;

import Parsers.ParserFactory;
import Reports.ReportFormatFactory;
import Validation.ValidatorFactory;

public class AnalyzerFactory {
    private AnalyzerFactory() {
    }

    public static IAnalyzer createDefault() {
        return new Analyzer(
                ParserFactory.defaultFactory(),
                ReportFormatFactory.defaultFactory(),
                ValidatorFactory.createDefault()
        );
    }
}
