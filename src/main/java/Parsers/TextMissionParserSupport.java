package Parsers;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextMissionParserSupport {
    public Map<String, String> readColonKeyValue(String content) {
        Map<String, String> values = new LinkedHashMap<>();
        for (String line : content.split("\\R")) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            int index = trimmed.indexOf(':');
            if (index > 0) {
                values.put(trimmed.substring(0, index).trim(), trimmed.substring(index + 1).trim());
            }
        }
        return values;
    }

    public Map<String, String> readEqualsKeyValue(String content) {
        Map<String, String> values = new LinkedHashMap<>();
        String section = "MISSION";
        int sorcererIndex = -1;
        int techniqueIndex = -1;
        for (String line : content.split("\\R")) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                section = trimmed.substring(1, trimmed.length() - 1).trim().toUpperCase();
                if ("SORCERER".equals(section)) sorcererIndex++;
                if ("TECHNIQUE".equals(section)) techniqueIndex++;
                continue;
            }
            int index = trimmed.indexOf('=');
            if (index > 0) {
                String key = trimmed.substring(0, index).trim();
                String value = trimmed.substring(index + 1).trim();
                if ("MISSION".equals(section)) {
                    values.put(key, value);
                } else if ("CURSE".equals(section)) {
                    values.put("curse." + key, value);
                } else if ("SORCERER".equals(section)) {
                    values.put("sorcerer[" + sorcererIndex + "]." + key, value);
                } else if ("TECHNIQUE".equals(section)) {
                    values.put("technique[" + techniqueIndex + "]." + key, value);
                } else {
                    values.put(section.toLowerCase() + "." + key, value);
                }
            }
        }
        return values;
    }
}
