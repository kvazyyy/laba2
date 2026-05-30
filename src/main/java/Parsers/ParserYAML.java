package Parsers;

import Builders.MissionBuilder;
import Entities.*;

public class ParserYAML extends BaseParser {
    @Override public String getName() { return "YAML"; }
    @Override public boolean supports(String fileName, String content) {
        String lower = fileName.toLowerCase();
        return lower.endsWith(".yaml") || lower.endsWith(".yml");
    }

    @Override
    public Mission parse(String fileName, String content) {
        MissionBuilder builder = new MissionBuilder();
        Curse curse = new Curse();
        EconomicAssessment economics = new EconomicAssessment();
        Sorcerer currentSorcerer = null;
        Technique currentTechnique = null;
        String section = "root";
        for (String raw : content.split("\\R")) {
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            if (!raw.startsWith(" ") && line.endsWith(":")) {
                if (currentSorcerer != null) { builder.sorcerer(currentSorcerer); currentSorcerer = null; }
                if (currentTechnique != null) { builder.technique(currentTechnique); currentTechnique = null; }
                section = line.substring(0, line.length() - 1);
                continue;
            }
            if (line.startsWith("- ")) {
                if ("sorcerers".equals(section)) { if (currentSorcerer != null) builder.sorcerer(currentSorcerer); currentSorcerer = new Sorcerer(); applySorcerer(currentSorcerer, line.substring(2)); }
                else if ("techniques".equals(section)) { if (currentTechnique != null) builder.technique(currentTechnique); currentTechnique = new Technique(); applyTechnique(currentTechnique, line.substring(2)); }
                continue;
            }
            int idx = line.indexOf(':');
            if (idx < 0) continue;
            String key = line.substring(0, idx).trim();
            String value = clean(line.substring(idx + 1).trim());
            switch (section) {
                case "root" -> applyRoot(builder, key, value);
                case "curse" -> { if ("name".equals(key)) curse.setName(value); else if ("threatLevel".equals(key)) curse.setThreatLevel(value); }
                case "sorcerers" -> { if (currentSorcerer == null) currentSorcerer = new Sorcerer(); applySorcerer(currentSorcerer, key + ": " + value); }
                case "techniques" -> { if (currentTechnique == null) currentTechnique = new Technique(); applyTechnique(currentTechnique, key + ": " + value); }
                case "economicAssessment" -> applyEconomic(economics, key, value);
                default -> { }
            }
        }
        if (currentSorcerer != null) builder.sorcerer(currentSorcerer);
        if (currentTechnique != null) builder.technique(currentTechnique);
        builder.curse(curse);
        if (!economics.isEmpty()) builder.economicAssessment(economics);
        return builder.build();
    }

    private void applyRoot(MissionBuilder b, String key, String value) {
        switch (key) {
            case "missionId" -> b.missionId(value);
            case "date" -> b.date(value);
            case "location" -> b.location(value);
            case "outcome" -> b.outcome(value);
            case "damageCost" -> b.damageCost(parseLong(value));
        }
    }
    private void applySorcerer(Sorcerer s, String text) {
        int idx = text.indexOf(':'); if (idx < 0) return;
        String k = text.substring(0, idx).trim(); String v = clean(text.substring(idx + 1));
        if ("name".equals(k)) s.setName(v); else if ("rank".equals(k)) s.setRank(v);
    }
    private void applyTechnique(Technique t, String text) {
        int idx = text.indexOf(':'); if (idx < 0) return;
        String k = text.substring(0, idx).trim(); String v = clean(text.substring(idx + 1));
        switch (k) { case "name" -> t.setName(v); case "type" -> t.setType(v); case "owner" -> t.setOwner(v); case "damage" -> t.setDamage(parseLong(v)); }
    }
    private void applyEconomic(EconomicAssessment e, String key, String value) {
        switch (key) { case "totalDamageCost" -> e.setTotalDamageCost(parseLong(value)); case "infrastructureDamage" -> e.setInfrastructureDamage(parseLong(value)); case "commercialDamage" -> e.setCommercialDamage(parseLong(value)); case "transportDamage" -> e.setTransportDamage(parseLong(value)); case "recoveryEstimateDays" -> e.setRecoveryEstimateDays(parseLong(value)); case "insuranceCovered" -> e.setInsuranceCovered(parseBoolean(value)); }
    }
}
