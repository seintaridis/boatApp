package com.openwt.boats.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Boat {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Float  weight;
    private Timestamp createDate;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }




    public Boat(){}

    public Boat(String name, String description, Float weight, Timestamp createDate,String owner) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.createDate = createDate;
        this.owner=owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }



}
