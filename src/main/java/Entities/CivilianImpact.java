package Entities;

public class CivilianImpact {
    private long evacuated;
    private long injured;
    private long missing;
    private String publicExposureRisk;

    public long getEvacuated() { return evacuated; }
    public void setEvacuated(long evacuated) { this.evacuated = evacuated; }
    public long getInjured() { return injured; }
    public void setInjured(long injured) { this.injured = injured; }
    public long getMissing() { return missing; }
    public void setMissing(long missing) { this.missing = missing; }
    public String getPublicExposureRisk() { return publicExposureRisk; }
    public void setPublicExposureRisk(String publicExposureRisk) { this.publicExposureRisk = publicExposureRisk; }
    public boolean isEmpty() { return evacuated == 0 && injured == 0 && missing == 0 && (publicExposureRisk == null || publicExposureRisk.isBlank()); }
}
