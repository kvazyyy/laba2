package Entities;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private String notes;
    private Curse curse;
    private EconomicAssessment economicAssessment;
    private CivilianImpact civilianImpact;
    private EnemyActivity enemyActivity;
    private EnvironmentConditions environmentConditions;
    private final List<Sorcerer> sorcerers = new ArrayList<>();
    private final List<Technique> techniques = new ArrayList<>();
    private final List<OperationTimelineEvent> operationTimeline = new ArrayList<>();
    private final List<MissionExtensionBlock> extensionBlocks = new ArrayList<>();

    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public long getDamageCost() { return damageCost; }
    public void setDamageCost(long damageCost) { this.damageCost = damageCost; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) { this.economicAssessment = economicAssessment; }
    public CivilianImpact getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpact civilianImpact) { this.civilianImpact = civilianImpact; }
    public EnemyActivity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivity enemyActivity) { this.enemyActivity = enemyActivity; }
    public EnvironmentConditions getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) { this.environmentConditions = environmentConditions; }
    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public List<OperationTimelineEvent> getOperationTimeline() { return operationTimeline; }
    public List<MissionExtensionBlock> getExtensionBlocks() { return extensionBlocks; }

    public void addSorcerer(Sorcerer sorcerer) { if (sorcerer != null) sorcerers.add(sorcerer); }
    public void addTechnique(Technique technique) { if (technique != null) techniques.add(technique); }
    public void addTimelineEvent(OperationTimelineEvent event) { if (event != null) operationTimeline.add(event); }
    public void addExtensionBlock(MissionExtensionBlock block) { if (block != null) extensionBlocks.add(block); }

    public long totalTechniqueDamage() {
        return techniques.stream().mapToLong(Technique::getDamage).sum();
    }
}
