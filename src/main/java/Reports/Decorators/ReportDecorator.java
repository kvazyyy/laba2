package Reports.Decorators;

import Entities.Mission;
import Reports.IReportFormat;

public abstract class ReportDecorator implements IReportFormat {
    protected final IReportFormat wrapped;

    protected ReportDecorator(IReportFormat wrapped) {
        this.wrapped = wrapped;
    }

    @Override public String getName() { return wrapped.getName(); }
    @Override public String render(Mission mission) { return wrapped.render(mission); }
}
