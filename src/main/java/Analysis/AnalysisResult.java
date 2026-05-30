package Analysis;

import Entities.Mission;

public class AnalysisResult {
    private final String format;
    private final String reportType;
    private final Mission mission;
    private final String report;

    public AnalysisResult(String format, String reportType, Mission mission, String report) {
        this.format = format;
        this.reportType = reportType;
        this.mission = mission;
        this.report = report;
    }

    public String getFormat() { return format; }
    public String getReportType() { return reportType; }
    public Mission getMission() { return mission; }
    public String getReport() { return report; }
}
