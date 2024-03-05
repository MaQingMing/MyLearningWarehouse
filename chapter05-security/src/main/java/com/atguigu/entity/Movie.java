package com.atguigu.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "movie")
public class Movie {
    @Id
    private Integer id;

    private String name;

    private Integer length;

    private String type;

    private java.sql.Date beginTime;

    private java.sql.Date endTime;

    private Byte is3D;

    private String intro;

    private String image;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.sql.Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(java.sql.Date beginTime) {
        this.beginTime = beginTime;
    }

    public java.sql.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
    }

    public Byte getIs3D() {
        return this.is3D;
    }

    public void setIs3D(Byte is3D) {
        this.is3D = is3D;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
