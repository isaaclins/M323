package ch.bbw.m323.model;

import java.time.LocalDate;

public class WeatherEntry {
    private LocalDate date;
    private String city;
    private double temperature;
    private int humidity;
    private double precipitation;
    private int windSpeed;
    private String weatherCondition;

    public WeatherEntry(LocalDate date, String city, double temperature, int humidity, double precipitation,
            int windSpeed, String weatherCondition) {
        this.date = date;
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.weatherCondition = weatherCondition;
    }

    // Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    @Override
    public String toString() {
        return "WeatherEntry{" +
                "date=" + date +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", precipitation=" + precipitation +
                ", windSpeed=" + windSpeed +
                ", weatherCondition='" + weatherCondition + '\'' +
                '}';
    }
}
