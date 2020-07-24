package com.edu.pet.test;

import com.edu.pet.dao.RecordDao;
import com.edu.pet.dao.StoreDao;
import com.edu.pet.dao.impl.RecordDaoImpl;
import com.edu.pet.dao.impl.StoreDaoImpl;
import com.edu.pet.po.Record;
import com.edu.pet.po.Store;
import org.junit.Test;

import java.util.List;

public class TestDao {
    @Test
    public void testQueryAllStores(){
        System.out.println("+++++++testqueryAllStores+++++++");
        StoreDao storeDao = new StoreDaoImpl();
        List<Store> allStores = storeDao.queryAllStores();
        for (Store store:allStores) {
            System.out.println(store.getStoreId()+" "+store.getStoreName());
        }
    }
    @Test
    public void testQueryStoreById(){
        System.out.println("+++++++testqueryAllStores+++++++");
        StoreDao storeDao = new StoreDaoImpl();
        Store store = storeDao.queryStoreById(1);
        System.out.println(store.getStoreId()+" "+store.getStoreMoney());
    }
    @Test
    public void testQueryStoreByCP(){
        System.out.println("+++++++testQueryStoreByCP+++++++");
        StoreDao storeDao = new StoreDaoImpl();
        Store store = storeDao.queryStoreByCP("dan","huang");
        System.out.println(store.getStoreId()+" "+store.getStoreMoney());
    }
    @Test
    public void testUpdateStore(){
        StoreDao storeDao = new StoreDaoImpl();
        Store store = storeDao.queryStoreById(1);
        store.setStoreMoney(store.getStoreMoney()+10);
        storeDao.updateStore(store);
    }
    @Test
    public void testQueryStoreBuyRecords(){
        RecordDao recordDao = new RecordDaoImpl();
        List<Record> list = recordDao.queryStoreBuyRecords(1);
        for (Record record:list) {
            System.out.println(record.getId() + " "
                    + record.getType() + " "
                    + record.getBid() + " "
                    + record.getSid() + " "
                    + record.getPrice() + " "
                    + record.getDate());
        }
        System.out.println("总支出："+recordDao.queryStoreSumPayment(1));
    }
    @Test
    public void testQueryStoreSellRecords(){
        RecordDao recordDao = new RecordDaoImpl();
        List<Record> list = recordDao.queryStoreSellRecords(1);
        for (Record record:list) {
            System.out.println(record.getId() + " "
                    + record.getType() + " "
                    + record.getBid() + " "
                    + record.getSid() + " "
                    + record.getPrice() + " "
                    + record.getDate());
        }
        System.out.println("总收入："+recordDao.queryStoreSumIncome(1));
    }
    @Test
    public void testShowStorePetsNotNew(){

    }
}
