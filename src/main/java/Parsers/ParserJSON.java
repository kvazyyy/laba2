package Parsers;

import Builders.MissionBuilder;
import Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserJSON extends BaseParser {
    @Override public String getName() { return "JSON"; }
    @Override public boolean supports(String fileName, String content) { return fileName.toLowerCase().endsWith(".json"); }

    @Override
    public Mission parse(String fileName, String content) {
        MissionBuilder builder = new MissionBuilder()
                .missionId(findString(content, "missionId"))
                .date(findString(content, "date"))
                .location(findString(content, "location"))
                .outcome(findString(content, "outcome"))
                .damageCost(parseLong(findNumber(content, "damageCost")));

        String curse = findObject(content, "curse");
        if (curse != null) {
            builder.curse(new Curse(findString(curse, "name"), findString(curse, "threatLevel")));
        }
        for (String obj : splitObjects(findArray(content, "sorcerers"))) {
            builder.sorcerer(new Sorcerer(findString(obj, "name"), findString(obj, "rank")));
        }
        for (String obj : splitObjects(findArray(content, "techniques"))) {
            builder.technique(new Technique(findString(obj, "name"), findString(obj, "type"), findString(obj, "owner"), parseLong(findNumber(obj, "damage"))));
        }
        return builder.build();
    }

    private String findString(String json, String key) {
        Matcher matcher = Pattern.compile("\\\"" + Pattern.quote(key) + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"").matcher(json);
        return matcher.find() ? clean(matcher.group(1)) : null;
    }

    private String findNumber(String json, String key) {
        Matcher matcher = Pattern.compile("\\\"" + Pattern.quote(key) + "\\\"\\s*:\\s*([0-9]+)").matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }

    private String findObject(String json, String key) {
        return findBalanced(json, "\"" + key + "\"", '{', '}');
    }

    private String findArray(String json, String key) {
        return findBalanced(json, "\"" + key + "\"", '[', ']');
    }

    private String findBalanced(String text, String marker, char open, char close) {
        int markerIndex = text.indexOf(marker);
        if (markerIndex < 0) return null;
        int start = text.indexOf(open, markerIndex);
        if (start < 0) return null;
        int level = 0;
        for (int i = start; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == open) level++;
            if (c == close) level--;
            if (level == 0) return text.substring(start, i + 1);
        }
        return null;
    }

    private List<String> splitObjects(String array) {
        List<String> result = new ArrayList<>();
        if (array == null) return result;
        int level = 0, start = -1;
        for (int i = 0; i < array.length(); i++) {
            char c = array.charAt(i);
            if (c == '{') {
                if (level == 0) start = i;
                level++;
            } else if (c == '}') {
                level--;
                if (level == 0 && start >= 0) result.add(array.substring(start, i + 1));
            }
        }
        return result;
    }
}
