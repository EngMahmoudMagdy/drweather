package com.magdy.drweather.Data;

import java.io.Serializable;

public class PatentData implements Serializable{
    private String name ;
    private float height, weight , body_temp ;
    private int high_press, low_press,age, sugar_level;

    public PatentData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBody_temp() {
        return body_temp;
    }

    public void setBody_temp(float body_temp) {
        this.body_temp = body_temp;
    }

    public int getHigh_press() {
        return high_press;
    }

    public void setHigh_press(int high_press) {
        this.high_press = high_press;
    }

    public int getLow_press() {
        return low_press;
    }

    public void setLow_press(int low_press) {
        this.low_press = low_press;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSugar_level() {
        return sugar_level;
    }

    public void setSugar_level(int sugar_level) {
        this.sugar_level = sugar_level;
    }

    public PatentData(String name, float height, float weight, float body_temp, int high_press, int low_press, int age, int sugar_level) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.body_temp = body_temp;
        this.high_press = high_press;
        this.low_press = low_press;
        this.age = age;
        this.sugar_level = sugar_level;
    }
}
