package com.edu.pet.dao.impl;

import com.edu.pet.dao.StoreDao;
import com.edu.pet.utils.DBUtils;
import com.edu.pet.po.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoImpl implements StoreDao {
    //按照id查询商店
    @Override
    public Store queryStoreById(int storeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Store store = null;
        String sql = "select * from store where StoreId = ?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,storeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                store = new Store();
                store.setStoreId(resultSet.getInt(1));
                store.setStoreName(resultSet.getString(2));
                store.setStoreCount(resultSet.getString(3));
                store.setStorePwd(resultSet.getString(4));
                store.setStoreMoney(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return store;
    }
    //查询所有商店
    @Override
    public List<Store> queryAllStores() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from store";
        List<Store> allStores = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet!=null&&resultSet.next()){
                Store store = new Store();
                store.setStoreId(resultSet.getInt(1));
                store.setStoreName(resultSet.getString(2));
                store.setStoreCount(resultSet.getString(3));
                store.setStorePwd(resultSet.getString(4));
                store.setStoreMoney(resultSet.getInt(5));
                allStores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,statement,connection);
        }
        return allStores;
    }
    //按照商店账户密码查询
    @Override
    public Store queryStoreByCP(String count, String pwd) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Store store = null;
        String sql = "select * from store where StoreCount = ? and StorePwd = ?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,count);
            preparedStatement.setString(2,pwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                store = new Store();
                store.setStoreId(resultSet.getInt(1));
                store.setStoreName(resultSet.getString(2));
                store.setStoreCount(resultSet.getString(3));
                store.setStorePwd(resultSet.getString(4));
                store.setStoreMoney(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return store;
    }
    //更新商店信息
    @Override
    public void updateStore(Store store) {
        String sql = "update store set storename = ?,storecount = ?,storepwd = ?,storemoney = ? where storeid = ?";
        DBUtils.executeSQL(sql,
                store.getStoreName(),
                store.getStoreCount(),
                store.getStorePwd(),
                store.getStoreMoney(),
                store.getStoreId()
                );
    }
}
