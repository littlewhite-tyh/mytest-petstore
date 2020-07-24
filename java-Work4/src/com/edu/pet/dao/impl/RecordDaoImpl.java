package com.edu.pet.dao.impl;

import com.edu.pet.dao.RecordDao;
import com.edu.pet.po.Record;
import com.edu.pet.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {

    //向售卖记录表中添加数据
    @Override
    public void addRecord(Record record) {
        String sql="insert into record values(null,?,?,?,?,?,?);";
        DBUtils.executeSQL(sql,
                           record.getType(),
                           record.getPid(),
                           record.getSid(),
                           record.getBid(),
                           record.getPrice(),
                           record.getDate()
                          );
    }
    //查询售卖记录表中所有数据
    @Override
    public  List<Record> queryAllRecords() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from record;";
        List<Record> allrecords = new ArrayList<>();
        try {
            connection=DBUtils.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet != null && resultSet.next()){
                Record record=new Record();
                record.setId(resultSet.getInt("ID"));
                record.setType(resultSet.getInt("DEAL_TYPE"));
                record.setPid(resultSet.getInt("PET_ID"));
                record.setSid(resultSet.getInt("SELLER_ID"));
                record.setBid(resultSet.getInt("BUYER_ID"));
                record.setPrice(resultSet.getInt("PRICE"));
                record.setDate(resultSet.getString("DEAL_TIME"));
                allrecords.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,statement,connection);
        }
        return allrecords;
    }
    //按照卖家id查询商店出售的记录
    @Override
    public List<Record> queryStoreSellRecords(int storeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from record where seller_id = ? and deal_type = 2";
        List<Record> storeSellRecords = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,storeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet!=null&&resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("ID"));
                record.setType(resultSet.getInt("DEAL_TYPE"));
                record.setPid(resultSet.getInt("PET_ID"));
                record.setSid(resultSet.getInt("SELLER_ID"));
                record.setBid(resultSet.getInt("BUYER_ID"));
                record.setPrice(resultSet.getInt("PRICE"));
                record.setDate(resultSet.getString("DEAL_TIME"));
                storeSellRecords.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return storeSellRecords;
    }
    //计算商店出售总收入
    @Override
    public int queryStoreSumIncome(int storeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT SUM(price) FROM record where seller_id = ? and deal_type = 2";
        Record record = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,storeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                record = new Record();
                record.setPrice(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return record.getPrice();
    }
    //按照买家id查询商店收购的记录
    @Override
    public List<Record> queryStoreBuyRecords(int storeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from record where buyer_id = ? and deal_type = 1";
        List<Record> storeSellRecords = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,storeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet!=null&&resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("ID"));
                record.setType(resultSet.getInt("DEAL_TYPE"));
                record.setPid(resultSet.getInt("PET_ID"));
                record.setSid(resultSet.getInt("SELLER_ID"));
                record.setBid(resultSet.getInt("BUYER_ID"));
                record.setPrice(resultSet.getInt("PRICE"));
                record.setDate(resultSet.getString("DEAL_TIME"));
                storeSellRecords.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return storeSellRecords;
    }
    //计算商店购买总支出
    @Override
    public int queryStoreSumPayment(int storeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT SUM(price) FROM record where buyer_id = ? and deal_type = 1";
        Record record = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,storeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                record = new Record();
                record.setPrice(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return record.getPrice();
    }
    //查询对应宠物主人的购买记录
    @Override
    public List<Record> queryOwnerBuyRecords(int ownerId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "select * from record where DEAL_TYPE=2 and BUYER_ID=?";
        List<Record> allrecords = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ownerId);
            resultSet = ps.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Record record=new Record();
                record.setId(resultSet.getInt("ID"));
                record.setType(resultSet.getInt("DEAL_TYPE"));
                record.setPid(resultSet.getInt("PET_ID"));
                record.setSid(resultSet.getInt("SELLER_ID"));
                record.setBid(resultSet.getInt("BUYER_ID"));
                record.setPrice(resultSet.getInt("PRICE"));
                record.setDate(resultSet.getString("DEAL_TIME"));
                allrecords.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,ps,connection);
        }
        return allrecords;
    }
    //计算宠物主人购买总支出
    @Override
    public int queryOwnerSumPayment(int ownerId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT SUM(price) FROM record where buyer_id = ? and deal_type = 2";
        Record record = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ownerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                record = new Record();
                record.setPrice(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return record.getPrice();
    }
    //查询对应宠物主人的出售记录
    @Override
    public List<Record> queryOwnerSellRecords(int ownerId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "select * from record where DEAL_TYPE=1 and SELLER_ID=?";
        List<Record> allrecords = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ownerId);
            resultSet = ps.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Record record=new Record();
                record.setId(resultSet.getInt("ID"));
                record.setType(resultSet.getInt("DEAL_TYPE"));
                record.setPid(resultSet.getInt("PET_ID"));
                record.setSid(resultSet.getInt("SELLER_ID"));
                record.setBid(resultSet.getInt("BUYER_ID"));
                record.setPrice(resultSet.getInt("PRICE"));
                record.setDate(resultSet.getString("DEAL_TIME"));
                allrecords.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,ps,connection);
        }
        return allrecords;
    }
    //计算宠物主人出售总收入
    @Override
    public int queryOwnerSumIncome(int ownerId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT SUM(price) FROM record where seller_id = ? and deal_type = 1";
        Record record = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ownerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                record = new Record();
                record.setPrice(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return record.getPrice();
    }
}
