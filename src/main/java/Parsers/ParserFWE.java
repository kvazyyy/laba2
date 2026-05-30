package Parsers;

import Builders.MissionBuilder;
import Entities.*;

public class ParserFWE extends BaseParser {
    @Override public String getName() { return "EVENT_PROTOCOL"; }
    @Override public boolean supports(String fileName, String content) { return fileName.toLowerCase().endsWith(".log") || content.startsWith("MISSION_CREATED|"); }

    @Override
    public Mission parse(String fileName, String content) {
        MissionBuilder builder = new MissionBuilder();
        EnemyActivity enemyActivity = new EnemyActivity();
        CivilianImpact civilianImpact = new CivilianImpact();
        for (String line : content.split("\\R")) {
            String[] parts = line.split("\\|");
            if (parts.length == 0) continue;
            switch (parts[0]) {
                case "MISSION_CREATED" -> builder.missionId(get(parts, 1)).date(get(parts, 2)).location(get(parts, 3));
                case "CURSE_DETECTED" -> builder.curse(new Curse(get(parts, 1), get(parts, 2)));
                case "SORCERER_ASSIGNED" -> builder.sorcerer(new Sorcerer(get(parts, 1), get(parts, 2)));
                case "TECHNIQUE_USED" -> builder.technique(new Technique(get(parts, 1), get(parts, 2), get(parts, 3), parseLong(get(parts, 4))));
                case "TIMELINE_EVENT" -> builder.timelineEvent(new OperationTimelineEvent(get(parts, 1), get(parts, 2), get(parts, 3)));
                case "ENEMY_ACTION" -> enemyActivity.addAttackPattern(get(parts, 1) + ": " + get(parts, 2));
                case "CIVILIAN_IMPACT" -> readCivilianImpact(civilianImpact, parts);
                case "MISSION_RESULT" -> {
                    builder.outcome(get(parts, 1));
                    if (parts.length > 2 && parts[2].startsWith("damageCost=")) builder.damageCost(parseLong(parts[2].substring("damageCost=".length())));
                }
            }
        }
        if (!enemyActivity.isEmpty()) builder.enemyActivity(enemyActivity);
        if (!civilianImpact.isEmpty()) builder.civilianImpact(civilianImpact);
        return builder.build();
    }

    private String get(String[] parts, int index) { return index < parts.length ? parts[index].trim() : null; }
    private void readCivilianImpact(CivilianImpact impact, String[] parts) {
        for (int i = 1; i < parts.length; i++) {
            String[] kv = parts[i].split("=", 2);
            if (kv.length != 2) continue;
            switch (kv[0]) { case "evacuated" -> impact.setEvacuated(parseLong(kv[1])); case "injured" -> impact.setInjured(parseLong(kv[1])); case "missing" -> impact.setMissing(parseLong(kv[1])); }
        }
    }
}
