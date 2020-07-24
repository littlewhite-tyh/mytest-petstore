package com.edu.pet.po;

import java.io.Serializable;

public class Owner implements Serializable {
    private int ownerId;
    private String ownerName;
    private String ownerCount;
    private String ownerPwd;
    private int ownerMoney;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(String ownerCount) {
        this.ownerCount = ownerCount;
    }

    public String getOwnerPwd() {
        return ownerPwd;
    }

    public void setOwnerPwd(String ownerPwd) {
        this.ownerPwd = ownerPwd;
    }

    public int getOwnerMoney() {
        return ownerMoney;
    }

    public void setOwnerMoney(int ownerMoney) {
        this.ownerMoney = ownerMoney;
    }
}
