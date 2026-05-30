package Reports.Support;

import java.text.DecimalFormat;

public class ReportTextSupport {
    private final DecimalFormat format = new DecimalFormat("#,###");

    public String safe(String value) {
        return value == null || value.isBlank() ? "не указано" : value;
    }

    public String money(long value) {
        return format.format(value).replace(',', ' ') + " ¥";
    }
}
