package Reports;

import Entities.Mission;

public interface IReportFormat {
    String getName();
    String render(Mission mission);
}
