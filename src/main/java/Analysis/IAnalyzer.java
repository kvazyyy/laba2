package Analysis;

import java.nio.file.Path;
import java.util.List;

public interface IAnalyzer {
    AnalysisResult analyze(Path path, String reportType);
    List<AnalysisResult> analyzeBatch(Path folder, String reportType);
}
