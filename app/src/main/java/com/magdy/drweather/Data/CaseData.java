package com.magdy.drweather.Data;

public class CaseData {
    private PatentData patent_data ;
    private WeatherData weather_data ;

    public PatentData getPatent_data() {
        return patent_data;
    }

    public void setPatent_data(PatentData patent_data) {
        this.patent_data = patent_data;
    }

    public WeatherData getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(WeatherData weather_data) {
        this.weather_data = weather_data;
    }

    public CaseData() {
    }

    public CaseData(PatentData patent_data, WeatherData weather_data) {
        this.patent_data = patent_data;
        this.weather_data = weather_data;
    }
}
