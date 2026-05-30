package Validation;

import Entities.Mission;
import Validation.Rules.RequiredFieldsRule;

import java.util.List;

public class Validator implements IValidator {
    private final List<IValidator> rules;

    public Validator(List<IValidator> rules) {
        this.rules = rules;
    }

    public Validator() {
        this(List.of(new RequiredFieldsRule()));
    }

    @Override
    public void validate(Mission mission) {
        for (IValidator rule : rules) {
            rule.validate(mission);
        }
    }
}
