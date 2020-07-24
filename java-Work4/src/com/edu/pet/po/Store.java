package com.edu.pet.po;

import java.io.Serializable;

public class Store implements Serializable {
    private int storeId;
    private String storeName;
    private String storeCount;
    private String storePwd;
    private int storeMoney;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(String storeCount) {
        this.storeCount = storeCount;
    }

    public String getStorePwd() {
        return storePwd;
    }

    public void setStorePwd(String storePwd) {
        this.storePwd = storePwd;
    }

    public int getStoreMoney() {
        return storeMoney;
    }

    public void setStoreMoney(int storeMoney) {
        this.storeMoney = storeMoney;
    }
}
