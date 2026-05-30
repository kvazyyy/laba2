package Parsers;

import Builders.MissionBuilder;
import Entities.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class ParserXML extends BaseParser {
    @Override public String getName() { return "XML"; }
    @Override public boolean supports(String fileName, String content) { return fileName.toLowerCase().endsWith(".xml"); }

    @Override
    public Mission parse(String fileName, String content) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
            Element root = doc.getDocumentElement();
            MissionBuilder builder = new MissionBuilder()
                    .missionId(text(root, "missionId"))
                    .date(text(root, "date"))
                    .location(text(root, "location"))
                    .outcome(text(root, "outcome"))
                    .damageCost(parseLong(text(root, "damageCost")));
            Element curse = child(root, "curse");
            if (curse != null) builder.curse(new Curse(text(curse, "name"), text(curse, "threatLevel")));
            Element sorcerers = child(root, "sorcerers");
            for (Element e : children(sorcerers, "sorcerer")) builder.sorcerer(new Sorcerer(text(e, "name"), text(e, "rank")));
            Element techniques = child(root, "techniques");
            for (Element e : children(techniques, "technique")) builder.technique(new Technique(text(e, "name"), text(e, "type"), text(e, "owner"), parseLong(text(e, "damage"))));
            Element enemy = child(root, "enemyActivity");
            if (enemy != null) {
                EnemyActivity activity = new EnemyActivity();
                activity.setBehaviorType(text(enemy, "behaviorType"));
                activity.setTargetPriority(text(enemy, "targetPriority"));
                activity.setMobility(text(enemy, "mobility"));
                activity.setEscalationRisk(text(enemy, "escalationRisk"));
                for (Element p : children(child(enemy, "attackPatterns"), "pattern")) activity.addAttackPattern(p.getTextContent().trim());
                builder.enemyActivity(activity);
            }
            return builder.build();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Ошибка разбора XML", exception);
        }
    }

    private Element child(Element parent, String name) {
        if (parent == null) return null;
        NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            if (n instanceof Element e && e.getTagName().equals(name)) return e;
        }
        return null;
    }

    private java.util.List<Element> children(Element parent, String name) {
        java.util.List<Element> result = new java.util.ArrayList<>();
        if (parent == null) return result;
        NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            if (n instanceof Element e && e.getTagName().equals(name)) result.add(e);
        }
        return result;
    }

    private String text(Element parent, String name) {
        Element e = child(parent, name);
        return e == null ? null : e.getTextContent().trim();
    }
}
