package Builders;

import Entities.*;

public class MissionBuilder implements IMissionBuilder {
    private final Mission mission = new Mission();

    @Override public MissionBuilder missionId(String value) { mission.setMissionId(value); return this; }
    @Override public MissionBuilder date(String value) { mission.setDate(value); return this; }
    @Override public MissionBuilder location(String value) { mission.setLocation(value); return this; }
    @Override public MissionBuilder outcome(String value) { mission.setOutcome(value); return this; }
    @Override public MissionBuilder damageCost(long value) { mission.setDamageCost(value); return this; }
    @Override public MissionBuilder notes(String value) { mission.setNotes(value); return this; }
    @Override public MissionBuilder curse(Curse value) { mission.setCurse(value); return this; }
    @Override public MissionBuilder sorcerer(Sorcerer value) { mission.addSorcerer(value); return this; }
    @Override public MissionBuilder technique(Technique value) { mission.addTechnique(value); return this; }
    @Override public MissionBuilder economicAssessment(EconomicAssessment value) { mission.setEconomicAssessment(value); return this; }
    @Override public MissionBuilder civilianImpact(CivilianImpact value) { mission.setCivilianImpact(value); return this; }
    @Override public MissionBuilder enemyActivity(EnemyActivity value) { mission.setEnemyActivity(value); return this; }
    @Override public MissionBuilder environmentConditions(EnvironmentConditions value) { mission.setEnvironmentConditions(value); return this; }
    @Override public MissionBuilder timelineEvent(OperationTimelineEvent value) { mission.addTimelineEvent(value); return this; }
    @Override public MissionBuilder extensionBlock(MissionExtensionBlock value) { mission.addExtensionBlock(value); return this; }
    @Override public Mission build() { return mission; }
}
