package com.edu.pet.services;



import com.edu.pet.po.Owner;
import com.edu.pet.po.Pet;
import com.edu.pet.po.Record;

import java.util.List;

public interface PetService {
    //主人登陆检查
    Owner ownerLoginCheck(String count, String pwd);
    //出售宠物
    boolean sellPet(int petid, int buyerid, int sellerid);
    //显示我的宠物信息
    List<Pet> showPet(int ownerId);
    //喂养宠物
    boolean feed(int id);
    //显示宠物主人购买记录
    List<Record> showBuyRecord(int ownerId);
    //显示宠物主人购买总支出
    int showPay(int ownerId);
    //显示宠物主人出售记录
    List<Record> showSellRecord(int ownerId);
    //显示宠物主人出售总收入
    int showIncome(int ownerId);
    //显示我的金币
    int showMoney(int ownerId);
    // 购买pet
    public void buyPet(int  petId, int ownerId, int StoreId) ;
    //培养技能
    public void petPlayGame (int petId,int petSkill);

    //按照商店编号分组查询宠物
    List<Pet> displayPetsByStoreId();

    //得到宠物
    Pet queryPet(int petId);

    //展示宠物信息
    void showPetInfo(Pet pet);
}
