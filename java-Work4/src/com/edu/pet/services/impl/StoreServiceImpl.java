package com.edu.pet.services.impl;

import com.edu.pet.dao.PetDao;
import com.edu.pet.dao.RecordDao;
import com.edu.pet.dao.StoreDao;
import com.edu.pet.dao.impl.PetDaoImpl;
import com.edu.pet.dao.impl.RecordDaoImpl;
import com.edu.pet.dao.impl.StoreDaoImpl;
import com.edu.pet.po.Pet;
import com.edu.pet.po.Record;
import com.edu.pet.po.Store;
import com.edu.pet.services.StoreService;

import java.util.List;

public class StoreServiceImpl implements StoreService {

    //商家登录检查
    @Override
    public Store loginStoreCheck(String count, String pwd) {
        StoreDao storeDao = new StoreDaoImpl();
        Store store = storeDao.queryStoreByCP(count, pwd);
        if (store != null) {
            System.out.println("登陆成功！");
        } else
            System.out.println("登陆失败！用户名或密码错误！");
        return store;
    }
    //查看库存宠物
    @Override
    public void getStorePetsNotNew(Store store) {
        PetDao petDao = new PetDaoImpl();
        List<Pet> petsNotNew = petDao.queryStorePetsByNew(store.getStoreId(),"false");
        for (Pet pet:petsNotNew) {
            System.out.print(pet.getPetId() + "\t\t"
                    +pet.getPetName()+ "\t"
                    +pet.getTypeName()+ "\t"
                    +pet.getHealth()+ "\t\t"
                    +pet.getLove()+ "\t\t"
                    +pet.getBirthDay()+ "\t"
                    +pet.getStoreId()+ "\t\t"
                    +pet.getPrice()+ "\t\t"
                    +pet.getPetSkill());
            System.out.println();
        }
    }
    //查看新培育宠物
    @Override
    public void getStorePetsIsNew(Store store) {
        PetDao petDao = new PetDaoImpl();
        List<Pet> petsNotNew = petDao.queryStorePetsByNew(store.getStoreId(),"true");
        for (Pet pet:petsNotNew) {
            System.out.print(pet.getPetId() + "\t\t"
                    +pet.getPetName()+ "\t"
                    +pet.getTypeName()+ "\t"
                    +pet.getHealth()+ "\t\t"
                    +pet.getLove()+ "\t\t"
                    +pet.getBirthDay()+ "\t"
                    +pet.getStoreId()+ "\t\t"
                    +pet.getPrice()+ "\t\t"
                    +pet.getPetSkill());
            System.out.println();
        }
    }
    //上架新宠物
    @Override
    public void uploadNewPet(int petId){
        PetDao petDao = new PetDaoImpl();
        Pet pet = petDao.queryPetById(petId);
        pet.setNew("false");
        petDao.updatePet(pet);
    }
    //查看售卖记录和总收入
    @Override
    public void getStoreSellRecords(Store store){
        RecordDao recordDao = new RecordDaoImpl();
        List<Record> sellRecords = recordDao.queryStoreSellRecords(store.getStoreId());
        for (Record record:sellRecords) {
            System.out.print(record.getType() + "\t\t"
                    +record.getPid()+ "\t\t"
                    +record.getSid()+ "\t\t"
                    +record.getBid()+ "\t\t"
                    +record.getPrice()+ "\t\t"
                    +record.getDate());
            System.out.println();
        }
        System.out.printf("%-10s","总收入：");
        System.out.println(recordDao.queryStoreSumIncome(store.getStoreId()));
    }
    //查看收购记录和总支出
    @Override
    public void getStoreBuyRecords(Store store){
        RecordDao recordDao = new RecordDaoImpl();
        List<Record> buyRecords = recordDao.queryStoreBuyRecords(store.getStoreId());
        for (Record record:buyRecords) {
            System.out.print(record.getType() + "\t\t"
                    +record.getPid()+ "\t\t"
                    +record.getSid()+ "\t\t"
                    +record.getBid()+ "\t\t"
                    +record.getPrice()+ "\t\t"
                    +record.getDate());
            System.out.println();
        }
        System.out.printf("%-10s","总支出：");
        System.out.println(recordDao.queryStoreSumPayment(store.getStoreId()));
    }
    //查看结余
    @Override
    public int getStoreMoney(int storeId){
        StoreDao storeDao = new StoreDaoImpl();
        Store store = storeDao.queryStoreById(storeId);
        return store.getStoreMoney();
    }
}
