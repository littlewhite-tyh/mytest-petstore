package com.edu.pet.services;

import com.edu.pet.po.Pet;
import com.edu.pet.po.Record;
import com.edu.pet.po.Store;

import java.util.List;

public interface StoreService {
    //商家登录检查
    Store loginStoreCheck(String count, String pwd);
    //查看库存宠物
    void getStorePetsNotNew(Store store);
    //查看新培育宠物
    void getStorePetsIsNew(Store store);

    //上架新宠物
    void uploadNewPet(int petId);

    //查看售卖记录和总收入
    void getStoreSellRecords(Store store);
    //查看收购记录和总支出
    void getStoreBuyRecords(Store store);
    //查看结余
    int getStoreMoney(int storeId);
}
