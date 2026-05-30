package Parsers;

public abstract class BaseParser implements IParser {
    protected long parseLong(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }
        String normalized = value.replace("_", "").replace(" ", "").replace("¥", "").trim();
        try {
            return Long.parseLong(normalized);
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    protected boolean parseBoolean(String value) {
        return value != null && ("true".equalsIgnoreCase(value.trim()) || "yes".equalsIgnoreCase(value.trim()));
    }

    protected String clean(String value) {
        if (value == null) {
            return null;
        }
        String result = value.trim();
        if ((result.startsWith("\"") && result.endsWith("\"")) || (result.startsWith("'") && result.endsWith("'"))) {
            result = result.substring(1, result.length() - 1);
        }
        return result.trim();
    }
}
