package com.edu.pet.dao.impl;

import com.edu.pet.dao.OwnerDao;
import com.edu.pet.utils.DBUtils;
import com.edu.pet.po.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    //按照id查询用户
    @Override
    public Owner queryOwnerById(int ownerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from owner where OwnerId = ?";
        Owner owner = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ownerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                owner = new Owner();
                owner.setOwnerId(resultSet.getInt(1));
                owner.setOwnerName(resultSet.getString(2));
                owner.setOwnerCount(resultSet.getString(3));
                owner.setOwnerPwd(resultSet.getString(4));
                owner.setOwnerMoney(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return owner;
    }
    //查询所有用户
    @Override
    public List<Owner> queryAllOwners() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from owner";
        List<Owner> allOwners = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet!=null&&resultSet.next()){
                Owner owner = new Owner();
                owner.setOwnerId(resultSet.getInt(1));
                owner.setOwnerName(resultSet.getString(2));
                owner.setOwnerCount(resultSet.getString(3));
                owner.setOwnerPwd(resultSet.getString(4));
                owner.setOwnerMoney(resultSet.getInt(5));
                allOwners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,statement,connection);
        }
        return allOwners;
    }
    //按照用户名和密码查询
    @Override
    public Owner queryOwnerByCP(String count, String pwd) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Owner owner = null;
        String sql = "select * from owner where OwnerCount = ? and OwnerPwd = ?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,count);
            preparedStatement.setString(2,pwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet!=null&&resultSet.next()){
                owner = new Owner();
                owner.setOwnerId(resultSet.getInt(1));
                owner.setOwnerName(resultSet.getString(2));
                owner.setOwnerCount(resultSet.getString(3));
                owner.setOwnerPwd(resultSet.getString(4));
                owner.setOwnerMoney(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return owner;
    }
    //更新用户信息
    @Override
    public void updateOwner(Owner owner) {
        String sql = "update owner set ownername = ?,ownercount=?,ownerpwd=?,ownermoney=? where ownerid = ?";
        DBUtils.executeSQL(sql,
                owner.getOwnerName(),
                owner.getOwnerCount(),
                owner.getOwnerPwd(),
                owner.getOwnerMoney(),
                owner.getOwnerId()
                );
    }
}
