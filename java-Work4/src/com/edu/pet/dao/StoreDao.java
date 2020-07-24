package com.edu.pet.dao;

import com.edu.pet.po.Store;

import java.util.List;

public interface StoreDao {
    //按照id查询商店
    Store queryStoreById(int storeId);
    //查询所有商店
    List<Store> queryAllStores();
    //按照商店账户密码查询
    Store queryStoreByCP(String count,String pwd);
    //更新商店信息
    void updateStore(Store store);
}
