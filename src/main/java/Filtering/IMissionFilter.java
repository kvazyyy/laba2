package Filtering;

import Entities.Mission;

public interface IMissionFilter {
    boolean accept(Mission mission);
}
