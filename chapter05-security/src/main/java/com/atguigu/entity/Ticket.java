package com.atguigu.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ticket")
public class Ticket {
    @Id
    private Integer id;

    private Integer pid;

    private Float money;

    private String payType;

    private java.sql.Timestamp payTime;

    private Integer seatSn;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Float getMoney() {
        return this.money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public java.sql.Timestamp getPayTime() {
        return this.payTime;
    }

    public void setPayTime(java.sql.Timestamp payTime) {
        this.payTime = payTime;
    }

    public Integer getSeatSn() {
        return this.seatSn;
    }

    public void setSeatSn(Integer seatSn) {
        this.seatSn = seatSn;
    }
}
