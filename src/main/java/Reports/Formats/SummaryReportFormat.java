package Reports.Formats;

import Entities.Mission;
import Reports.IReportFormat;
import Reports.Support.ReportTextSupport;

public class SummaryReportFormat implements IReportFormat {
    private final ReportTextSupport support = new ReportTextSupport();

    @Override public String getName() { return "summary"; }

    @Override
    public String render(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== КРАТКАЯ СВОДКА ПО МИССИИ ===\n");
        sb.append("ID: ").append(support.safe(mission.getMissionId())).append('\n');
        sb.append("Дата: ").append(support.safe(mission.getDate())).append('\n');
        sb.append("Локация: ").append(support.safe(mission.getLocation())).append('\n');
        sb.append("Итог: ").append(support.safe(mission.getOutcome())).append('\n');
        sb.append("Стоимость ущерба: ").append(support.money(mission.getDamageCost())).append("\n\n");
        sb.append("Проклятие: ").append(support.safe(mission.getCurse().getName())).append('\n');
        sb.append("Уровень угрозы: ").append(support.safe(mission.getCurse().getThreatLevel())).append('\n');
        sb.append("Участников: ").append(mission.getSorcerers().size()).append('\n');
        sb.append("Техник: ").append(mission.getTechniques().size()).append('\n');
        return sb.toString();
    }
}
