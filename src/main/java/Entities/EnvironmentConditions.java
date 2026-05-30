package Entities;

public class EnvironmentConditions {
    private String weather;
    private String timeOfDay;
    private String visibility;
    private long cursedEnergyDensity;

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public String getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }
    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }
    public long getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(long cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
    public boolean isEmpty() { return weather == null && timeOfDay == null && visibility == null && cursedEnergyDensity == 0; }
}
