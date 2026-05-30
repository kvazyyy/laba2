package Builders;

import Entities.*;

public interface IMissionBuilder {
    IMissionBuilder missionId(String value);
    IMissionBuilder date(String value);
    IMissionBuilder location(String value);
    IMissionBuilder outcome(String value);
    IMissionBuilder damageCost(long value);
    IMissionBuilder notes(String value);
    IMissionBuilder curse(Curse value);
    IMissionBuilder sorcerer(Sorcerer value);
    IMissionBuilder technique(Technique value);
    IMissionBuilder economicAssessment(EconomicAssessment value);
    IMissionBuilder civilianImpact(CivilianImpact value);
    IMissionBuilder enemyActivity(EnemyActivity value);
    IMissionBuilder environmentConditions(EnvironmentConditions value);
    IMissionBuilder timelineEvent(OperationTimelineEvent value);
    IMissionBuilder extensionBlock(MissionExtensionBlock value);
    Mission build();
}
