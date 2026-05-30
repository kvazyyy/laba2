package Entities;

public class EconomicAssessment {
    private long totalDamageCost;
    private long infrastructureDamage;
    private long commercialDamage;
    private long transportDamage;
    private long recoveryEstimateDays;
    private boolean insuranceCovered;

    public long getTotalDamageCost() { return totalDamageCost; }
    public void setTotalDamageCost(long totalDamageCost) { this.totalDamageCost = totalDamageCost; }
    public long getInfrastructureDamage() { return infrastructureDamage; }
    public void setInfrastructureDamage(long infrastructureDamage) { this.infrastructureDamage = infrastructureDamage; }
    public long getCommercialDamage() { return commercialDamage; }
    public void setCommercialDamage(long commercialDamage) { this.commercialDamage = commercialDamage; }
    public long getTransportDamage() { return transportDamage; }
    public void setTransportDamage(long transportDamage) { this.transportDamage = transportDamage; }
    public long getRecoveryEstimateDays() { return recoveryEstimateDays; }
    public void setRecoveryEstimateDays(long recoveryEstimateDays) { this.recoveryEstimateDays = recoveryEstimateDays; }
    public boolean isInsuranceCovered() { return insuranceCovered; }
    public void setInsuranceCovered(boolean insuranceCovered) { this.insuranceCovered = insuranceCovered; }
    public boolean isEmpty() { return totalDamageCost == 0 && infrastructureDamage == 0 && commercialDamage == 0 && transportDamage == 0 && recoveryEstimateDays == 0; }
}
