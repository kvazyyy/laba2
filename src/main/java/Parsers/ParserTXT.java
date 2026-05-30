package Parsers;

import Builders.MissionBuilder;
import Entities.*;
import java.util.Map;

public class ParserTXT extends BaseParser {
    private final TextMissionParserSupport support = new TextMissionParserSupport();

    @Override public String getName() { return "TXT"; }
    @Override public boolean supports(String fileName, String content) { return fileName.toLowerCase().endsWith(".txt"); }

    @Override
    public Mission parse(String fileName, String content) {
        Map<String, String> values = content.contains("[MISSION]") ? support.readEqualsKeyValue(content) : support.readColonKeyValue(content);
        MissionBuilder builder = new MissionBuilder()
                .missionId(values.get("missionId"))
                .date(values.get("date"))
                .location(values.get("location"))
                .outcome(values.get("outcome"))
                .damageCost(parseLong(values.get("damageCost")))
                .curse(new Curse(values.get("curse.name"), values.get("curse.threatLevel")));

        for (int i = 0; i < 20; i++) {
            String name = values.get("sorcerer[" + i + "].name");
            if (name == null) continue;
            builder.sorcerer(new Sorcerer(name, values.get("sorcerer[" + i + "].rank")));
        }
        for (int i = 0; i < 20; i++) {
            String name = values.get("technique[" + i + "].name");
            if (name == null) continue;
            builder.technique(new Technique(name, values.get("technique[" + i + "].type"), values.get("technique[" + i + "].owner"), parseLong(values.get("technique[" + i + "].damage"))));
        }
        EnvironmentConditions env = new EnvironmentConditions();
        env.setWeather(values.get("environment.weather"));
        env.setTimeOfDay(values.get("environment.timeOfDay"));
        env.setVisibility(values.get("environment.visibility"));
        env.setCursedEnergyDensity(parseLong(values.get("environment.cursedEnergyDensity")));
        if (!env.isEmpty()) builder.environmentConditions(env);
        return builder.build();
    }
}
