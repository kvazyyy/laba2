package Filtering;

import Entities.Mission;

public class OutcomeFilter implements IMissionFilter {
    private final String outcome;

    public OutcomeFilter(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean accept(Mission mission) {
        return mission.getOutcome() != null && mission.getOutcome().equalsIgnoreCase(outcome);
    }
}
