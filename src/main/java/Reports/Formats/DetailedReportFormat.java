package Reports.Formats;

import Entities.*;
import Reports.IReportFormat;
import Reports.Support.ReportTextSupport;

public class DetailedReportFormat implements IReportFormat {
    private final ReportTextSupport support = new ReportTextSupport();

    @Override public String getName() { return "detailed"; }

    @Override
    public String render(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ДЕТАЛИЗИРОВАННЫЙ ОТЧЕТ ПО МИССИИ ===\n");
        sb.append("ID: ").append(support.safe(mission.getMissionId())).append('\n');
        sb.append("Дата: ").append(support.safe(mission.getDate())).append('\n');
        sb.append("Локация: ").append(support.safe(mission.getLocation())).append('\n');
        sb.append("Итог: ").append(support.safe(mission.getOutcome())).append('\n');
        sb.append("Стоимость ущерба: ").append(support.money(mission.getDamageCost())).append("\n\n");
        sb.append("Проклятие:\n");
        sb.append("  Название: ").append(support.safe(mission.getCurse().getName())).append('\n');
        sb.append("  Уровень угрозы: ").append(support.safe(mission.getCurse().getThreatLevel())).append("\n\n");
        sb.append("Участники миссии:\n");
        int index = 1;
        for (Sorcerer s : mission.getSorcerers()) {
            sb.append("  ").append(index++).append(") ").append(support.safe(s.getName())).append(" — ").append(support.safe(s.getRank())).append('\n');
        }
        sb.append("\nПримененные техники:\n");
        index = 1;
        for (Technique t : mission.getTechniques()) {
            sb.append("  ").append(index++).append(") ").append(support.safe(t.getName()))
              .append(" | тип: ").append(support.safe(t.getType()))
              .append(" | владелец: ").append(support.safe(t.getOwner()))
              .append(" | урон: ").append(support.money(t.getDamage())).append('\n');
        }
        sb.append("Суммарный урон техник: ").append(support.money(mission.totalTechniqueDamage())).append('\n');
        if (mission.getEconomicAssessment() != null && !mission.getEconomicAssessment().isEmpty()) {
            EconomicAssessment e = mission.getEconomicAssessment();
            sb.append("\nЭкономическая оценка:\n");
            sb.append("  Общий ущерб: ").append(support.money(e.getTotalDamageCost())).append('\n');
            sb.append("  Ущерб инфраструктуре: ").append(support.money(e.getInfrastructureDamage())).append('\n');
            sb.append("  Коммерческий ущерб: ").append(support.money(e.getCommercialDamage())).append('\n');
            sb.append("  Ущерб транспорту: ").append(support.money(e.getTransportDamage())).append('\n');
        }
        if (mission.getEnemyActivity() != null && !mission.getEnemyActivity().isEmpty()) {
            sb.append("\nАктивность противника:\n");
            sb.append("  Тип поведения: ").append(support.safe(mission.getEnemyActivity().getBehaviorType())).append('\n');
            for (String p : mission.getEnemyActivity().getAttackPatterns()) sb.append("  - ").append(p).append('\n');
        }
        if (!mission.getOperationTimeline().isEmpty()) {
            sb.append("\nХронология операции:\n");
            for (OperationTimelineEvent event : mission.getOperationTimeline()) {
                sb.append("  ").append(support.safe(event.getTimestamp())).append(" | ").append(support.safe(event.getType())).append(" | ").append(support.safe(event.getDescription())).append('\n');
            }
        }
        return sb.toString();
    }
}
