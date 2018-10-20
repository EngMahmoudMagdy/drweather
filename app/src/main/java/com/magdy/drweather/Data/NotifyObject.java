package com.magdy.drweather.Data;

import java.io.Serializable;

public class NotifyObject implements Serializable{
    private String name , desc  , id;
    private int seen ;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public NotifyObject() {
    }

    public NotifyObject(String name, String desc, int seen) {
        this.name = name;
        this.desc = desc;
        this.seen = seen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
