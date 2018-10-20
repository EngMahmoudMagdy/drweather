package com.magdy.drweather.Data;

public class WeatherData  {
    private float temp , pm25 , co , no2, o3;
    private int pressure , humidity ;

    public WeatherData() {
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getPm25() {
        return pm25;
    }

    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }

    public float getCo() {
        return co;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public float getNo2() {
        return no2;
    }

    public void setNo2(float no2) {
        this.no2 = no2;
    }

    public float getO3() {
        return o3;
    }

    public void setO3(float o3) {
        this.o3 = o3;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public WeatherData(float temp, float pm25, float co, float no2, float o3, int pressure, int humidity) {
        this.temp = temp;
        this.pm25 = pm25;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.pressure = pressure;
        this.humidity = humidity;
    }
}
