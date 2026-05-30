package Entities;

import java.util.ArrayList;
import java.util.List;

public class EnemyActivity {
    private String behaviorType;
    private String targetPriority;
    private String mobility;
    private String escalationRisk;
    private final List<String> attackPatterns = new ArrayList<>();
    private final List<String> countermeasuresUsed = new ArrayList<>();

    public String getBehaviorType() { return behaviorType; }
    public void setBehaviorType(String behaviorType) { this.behaviorType = behaviorType; }
    public String getTargetPriority() { return targetPriority; }
    public void setTargetPriority(String targetPriority) { this.targetPriority = targetPriority; }
    public String getMobility() { return mobility; }
    public void setMobility(String mobility) { this.mobility = mobility; }
    public String getEscalationRisk() { return escalationRisk; }
    public void setEscalationRisk(String escalationRisk) { this.escalationRisk = escalationRisk; }
    public List<String> getAttackPatterns() { return attackPatterns; }
    public List<String> getCountermeasuresUsed() { return countermeasuresUsed; }
    public void addAttackPattern(String pattern) { if (pattern != null && !pattern.isBlank()) attackPatterns.add(pattern); }
    public void addCountermeasureUsed(String measure) { if (measure != null && !measure.isBlank()) countermeasuresUsed.add(measure); }
    public boolean isEmpty() { return behaviorType == null && targetPriority == null && attackPatterns.isEmpty() && countermeasuresUsed.isEmpty(); }
}
