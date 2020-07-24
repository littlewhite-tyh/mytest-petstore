package com.edu.pet.dao;


import com.edu.pet.po.Record;

import java.util.List;

public interface RecordDao {

    //添加一条记录
    void addRecord(Record record);
    //查询所有记录
    List<Record> queryAllRecords();
    //按照卖家id查询商店出售的记录
    List<Record> queryStoreSellRecords(int storeId);
    //计算商店出售总收入
    int queryStoreSumIncome(int sid);
    //按照买家id查询商店收购的记录
    List<Record> queryStoreBuyRecords(int storeId);
    //计算商店购买总支出
    int queryStoreSumPayment(int bid);
    //查询对应宠物主人的购买记录
    List<Record> queryOwnerBuyRecords(int ownerId);
    //计算宠物主人的总支出
    int queryOwnerSumPayment(int ownerId);
    //查询对应宠物主人的出售记录
    List<Record> queryOwnerSellRecords(int ownerId);
    //计算宠物主人出售总收入
    int queryOwnerSumIncome(int ownerId);
}
