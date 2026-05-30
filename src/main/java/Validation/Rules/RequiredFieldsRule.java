package Validation.Rules;

import Entities.Mission;
import Validation.IValidator;

public class RequiredFieldsRule implements IValidator {
    @Override
    public void validate(Mission mission) {
        require(mission.getMissionId(), "missionId");
        require(mission.getDate(), "date");
        require(mission.getLocation(), "location");
        require(mission.getOutcome(), "outcome");
        if (mission.getCurse() == null) {
            throw new IllegalArgumentException("Отсутствует обязательный блок curse");
        }
        require(mission.getCurse().getName(), "curse.name");
        require(mission.getCurse().getThreatLevel(), "curse.threatLevel");
    }

    private void require(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Отсутствует обязательное поле: " + field);
        }
    }
}
