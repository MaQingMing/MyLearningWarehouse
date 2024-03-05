package com.atguigu.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "plan")
public class Plan {
    @Id
    private Integer id;

    private java.sql.Timestamp playTime;

    private Integer mid;

    private Integer hid;

    private Float price;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Timestamp getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(java.sql.Timestamp playTime) {
        this.playTime = playTime;
    }

    public Integer getMid() {
        return this.mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getHid() {
        return this.hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
