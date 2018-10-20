package com.magdy.drweather.Data;

import java.io.Serializable;

public class PatentData implements Serializable{
    private String name , gender , pregnancy , diabetic , asthmatic , blood_type;
    private float height, weight , body_temp , bmi  ;
    private int high_press, low_press,age , heart_rate ;
    private boolean is_new ;

    public PatentData(String name, String gender, String pregnancy, String diabetic, String asthmatic, String blood_type, float height, float weight, float body_temp, float bmi, int high_press, int low_press, int age, int heart_rate , boolean is_new) {
        this.name = name;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.diabetic = diabetic;
        this.asthmatic = asthmatic;
        this.blood_type = blood_type;
        this.height = height;
        this.weight = weight;
        this.body_temp = body_temp;
        this.bmi = bmi;
        this.high_press = high_press;
        this.low_press = low_press;
        this.age = age;
        this.heart_rate = heart_rate;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(String diabetic) {
        this.diabetic = diabetic;
    }

    public String getAsthmatic() {
        return asthmatic;
    }

    public void setAsthmatic(String asthmatic) {
        this.asthmatic = asthmatic;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

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

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public boolean isIs_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }
}
