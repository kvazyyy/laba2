package Reports.Decorators;

import Entities.Mission;
import Reports.IReportFormat;

public class HeaderReportDecorator extends ReportDecorator {
    public HeaderReportDecorator(IReportFormat wrapped) {
        super(wrapped);
    }

    @Override
    public String render(Mission mission) {
        return "Отчет архивного отдела Токийского магического колледжа\n" + wrapped.render(mission);
    }
}
