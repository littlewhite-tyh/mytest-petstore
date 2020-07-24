package com.edu.pet.services.impl;


import com.edu.pet.dao.OwnerDao;
import com.edu.pet.dao.PetDao;
import com.edu.pet.dao.RecordDao;
import com.edu.pet.dao.StoreDao;
import com.edu.pet.dao.impl.OwnerDaoImpl;
import com.edu.pet.dao.impl.PetDaoImpl;
import com.edu.pet.dao.impl.RecordDaoImpl;
import com.edu.pet.dao.impl.StoreDaoImpl;
import com.edu.pet.po.Owner;
import com.edu.pet.po.Pet;
import com.edu.pet.po.Record;
import com.edu.pet.po.Store;
import com.edu.pet.services.PetService;
import com.edu.pet.utils.DateUtils;

import java.util.List;

public class PetServiceImpl implements PetService {
    //主人登陆检查
    @Override
    public Owner ownerLoginCheck(String count, String pwd) {
        OwnerDao ownerDao = new OwnerDaoImpl();
        Owner owner = new Owner();
        owner = ownerDao.queryOwnerByCP(count, pwd);
        if (owner != null) {
            System.out.println("登陆成功！");
        } else
            System.out.println("登陆失败！用户名或密码错误！");
        return owner;
    }
    //出售宠物
    @Override
    public boolean sellPet(int petid,int buyerid,int sellerid) {
        //根据petID找到pet对象
        PetDao petDao=new PetDaoImpl();
        Pet pet=petDao.queryPetById(petid);
        //喂养1次且健康值达到85才能出售
        if (pet.getFeedCount()>1&&pet.getHealth()>85) {
            //修改宠物归属
            pet.setOwnerId(0);
            pet.setStoreId(buyerid);
            //喂养次数清0
            pet.setFeedCount(0);
            //更新pet表数据
            petDao.updatePet(pet);
            //增加用户金币
            OwnerDao ownerDao = new OwnerDaoImpl();
            Owner owner = ownerDao.queryOwnerById(sellerid);
            owner.setOwnerMoney(owner.getOwnerMoney() + pet.getPrice());
            ownerDao.updateOwner(owner);
            //减少商店金币
            StoreDao storeDao = new StoreDaoImpl();
            Store store = storeDao.queryStoreById(buyerid);
            store.setStoreMoney(store.getStoreMoney()-pet.getPrice());
            //增加record记录
            RecordDao recordDao = new RecordDaoImpl();
            Record record = new Record();
            record.setType(1);
            record.setPid(pet.getPetId());
            record.setSid(sellerid);
            record.setBid(buyerid);
            record.setPrice(pet.getPrice());
            record.setDate(DateUtils.getDateTime());
            recordDao.addRecord(record);
            return true;
        }
        return false;
    }
    //显示我的宠物信息
    @Override
    public List<Pet> showPet(int ownerId) {
        PetDao petDao=new PetDaoImpl();
        return petDao.queryOwnerPets(ownerId);
    }
    //喂养宠物
    @Override
    public boolean feed(int id) {
        PetDao petDao=new PetDaoImpl();
        Pet pet=petDao.queryPetById(id);
        if (pet.getFeedCount() < 3) {
            //健康值加5
            pet.setHealth(pet.getHealth()+5);
            //宠物价格加2
            pet.setPrice(pet.getPrice()+2);
            //喂养次数+1
            pet.setFeedCount(pet.getFeedCount()+1);
            petDao.updatePet(pet);
            return true;
        }
        return false;
    }
    //显示宠物主人购买记录
    @Override
    public List<Record> showBuyRecord(int ownerId) {
        RecordDao recordDao=new RecordDaoImpl();
        return recordDao.queryOwnerBuyRecords(ownerId);
    }
    @Override
    //显示宠物主人购买总支出
    public int showPay(int ownerId){
        RecordDao recordDao=new RecordDaoImpl();
        return recordDao.queryOwnerSumPayment(ownerId);
    }
    //显示宠物主人出售记录
    public List<Record> showSellRecord(int ownerId) {
        RecordDao recordDao=new RecordDaoImpl();
        return recordDao.queryOwnerSellRecords(ownerId);
    }
    //显示宠物主人出售总收入
    public int showIncome(int ownerId){
        RecordDao recordDao=new RecordDaoImpl();
        return recordDao.queryOwnerSumIncome(ownerId);
    }
    //显示我的金币
    public int showMoney(int ownerId){
        OwnerDao ownerDao=new OwnerDaoImpl();
        return ownerDao.queryOwnerById(ownerId).getOwnerMoney();
    }
    //买宠物
    @Override
    public void buyPet(int petId, int ownerId, int storeId) {
        RecordDao recordDao = new RecordDaoImpl();
        PetDao petDao = new PetDaoImpl();
        OwnerDao ownerDao = new OwnerDaoImpl();
        StoreDao storeDao = new StoreDaoImpl();
        Pet pet = petDao.queryPetById(petId);
        //1 store更新
        Store store = storeDao.queryStoreById(storeId);
        store.setStoreMoney(store.getStoreMoney()+pet.getPrice());
        storeDao.updateStore(store);
        //2 owner更新
        Owner owner = ownerDao.queryOwnerById(ownerId);
        owner.setOwnerMoney(owner.getOwnerMoney()-pet.getPrice());
        ownerDao.updateOwner(owner);
        //3 更新归属
        pet.setStoreId(0);
        pet.setOwnerId(ownerId);
        petDao.updatePet(pet);
        //4 添加记录
        Record record = new Record();
        record.setPid(petId);
        record.setBid(ownerId);
        record.setSid(storeId);
        record.setType(2);
        record.setPrice(pet.getPrice());
        record.setDate(DateUtils.getDateTime());
        recordDao.addRecord(record);
        System.out.println("购买成功!");
    }
    //培养宠物技能
    @Override
    public void petPlayGame(int petId, int petSkill) {
        PetDao petDao = new PetDaoImpl();
        Pet pet = petDao.queryPetById(petId);
        pet.setPetSkill(petSkill);
        pet.setLove(pet.getLove()+10);
        petDao.updatePet(pet);
    }
    //按照商店编号分组查询宠物
    @Override
    public List<Pet> displayPetsByStoreId(){
        PetDao petDao = new PetDaoImpl();
        List<Pet> pets = petDao.displayPetBystoreId();
        return pets;
    }
    //得到宠物
    @Override
    public Pet queryPet(int petId) {
        PetDao petDao = new PetDaoImpl();
        Pet pet = petDao.queryPetById(petId);
        return pet;
    }
    //展示宠物信息
    @Override
    public void showPetInfo(Pet pet) {

        System.out.println("宠物编号" + "\t名称\t类型\t健康度\t亲密度\t出生日期\t\t商店编号\t价格\t技能");
        System.out.print(pet.getPetId() + "\t\t"
                +pet.getPetName()+ "\t"
                +pet.getTypeName()+ "\t"
                +pet.getHealth()+ "\t\t"
                +pet.getLove()+ "\t\t"
                +pet.getBirthDay()+ "\t"
                +pet.getStoreId()+ "\t\t"
                +pet.getPrice()+ "\t\t"
                +pet.getPetSkill());
    }
}

