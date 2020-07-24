package com.edu.pet.po;

import java.io.Serializable;
import java.util.Date;

/*
宠物实体类
 */
public class Pet implements Serializable {

    private int petId;
    private String petName;
    private String typeName;//类型
    private int Health;
    private int Love;
    private Date birthDay;//生日
    private int ownerId;//主人ID
    private int storeId;//商站ID
    private int Price;//宠物售价
    private String isNew;//是否为新培育
    private int petSkill;//pet获得的技能
    private int feedCount;//宠物喂养次数
    public Pet(){

    }
    public Pet(int petId, String petName, String typeName, int health, int love, Date birthDay, int ownerId, int storeId, int price, String isNew, int petSkill,int feedCount) {
        this.petId = petId;
        this.petName = petName;
        this.typeName = typeName;
        this.Health = health;
        this.Love = love;
        this.birthDay = birthDay;
        this.ownerId = ownerId;
        this.storeId = storeId;
        this.Price = price;
        this.isNew = isNew;
        this.petSkill = petSkill;
        this.feedCount = feedCount;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getLove() {
        return Love;
    }

    public void setLove(int love) {
        Love = love;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String isNew() {
        return isNew;
    }

    public void setNew(String aNew) {
        isNew = aNew;
    }

    public int getPetSkill() {
        return petSkill;
    }

    public void setPetSkill(int petSkill) {
        this.petSkill = petSkill;
    }

    public int getFeedCount() {
        return feedCount;
    }
    public void setFeedCount(int feedCount) {
        this.feedCount = feedCount;
    }
}
