package com.edu.pet.dao.impl;

import com.edu.pet.dao.PetDao;
import com.edu.pet.po.Pet;
import com.edu.pet.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDaoImpl implements PetDao {
    @Override
    public void savePet(Pet pet) {
            String saveSql = "insert into values(null,?,?,?,?,?,?,?,?,?,?,?)";
            boolean Flag =DBUtils.executeSQL(saveSql,
                    pet.getPetName(),
                    pet.getTypeName(),
                    pet.getHealth(),
                    pet.getLove(),
                    pet.getBirthDay(),
                    pet.getOwnerId(),
                    pet.getStoreId(),
                    pet.getPrice(),
                    pet.isNew(),
                    pet.getPetSkill(),
                    pet.getFeedCount());
            if (Flag == true){
                System.out.println("宠物信息保存成功！");
            }
            else {
                System.out.println("宠物信息保存失败！");
            }
    }

    @Override
    public void updatePet(Pet pet) {
            String updateSql = "update Pet set petName = ?," +
                    "typeName = ?," +
                    "Health = ?," +
                    "Love = ?," +
                    "birthDay = ?," +
                    "ownerId = ?," +
                    "storeId = ?," +
                    "Price = ?," +
                    "isNew = ?," +
                    "petSkill = ?," +
                    "feedCount = ? where petid = ?";
            boolean Flag =DBUtils.executeSQL(updateSql,
                    pet.getPetName(),
                    pet.getTypeName(),
                    pet.getHealth(),
                    pet.getLove(),
                    pet.getBirthDay(),
                    pet.getOwnerId(),
                    pet.getStoreId(),
                    pet.getPrice(),
                    pet.isNew(),
                    pet.getPetSkill(),
                    pet.getFeedCount(),
                    pet.getPetId());
            if (Flag == true){
                System.out.println("宠物信息更新成功！");
            }
            else {
                System.out.println("宠物信息更新失败！");
            }
    }

    @Override
    public Pet queryPetById(int petId) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String querySql = "select * from Pet where petId = ?";
        Pet pet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setInt(1,petId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()){
                pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                pet.setFeedCount(resultSet.getInt(12));
            }
            else {
                System.out.println("未查到宠物信息！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return pet;
    }

    @Override
    public List<Pet> queryAllPets() {
        List<Pet> allPets = new ArrayList<>();
        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet = null;
        String querySql = "select * from Pet ";
        try {
            statement = connection.createStatement();
            boolean Flag = statement.execute(querySql);
            resultSet = statement.executeQuery(querySql);
            while (resultSet != null && resultSet.next()){
                Pet pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                pet.setFeedCount(resultSet.getInt(12));
                allPets.add(pet);
            }
            if (Flag == true){
                System.out.println("查询所有宠物信息成功！");
            }
            else {
                System.out.println("查询所有宠物信息失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,statement,connection);
        }
        return allPets;
    }
    //根据主人Id查找所属宠物
    @Override
    public List<Pet> queryOwnerPets(int ownerId) {
        List<Pet> allPets = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            String querySql = "select * from Pet where ownerId = ?";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setInt(1,ownerId);
            boolean Flag = preparedStatement.execute();
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()){
                Pet pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                pet.setFeedCount(resultSet.getInt(12));
                allPets.add(pet);
            }
            if (Flag == true){
                System.out.println("查询宠物信息成功！");
            }
            else {
                System.out.println("未查到宠物信息！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return allPets;
    }
    //根据商店Id查找所属宠物
    @Override
    public List<Pet> queryStorePets(int storeId) {
        List<Pet> allPets = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            String querySql = "select * from Pet where storeId = ?";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setInt(1,storeId);
            boolean Flag = preparedStatement.execute();
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()){
                Pet pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                pet.setFeedCount(resultSet.getInt(12));
                allPets.add(pet);
            }
            if (Flag == true){
                System.out.println("查询宠物信息成功！");
            }
            else {
                System.out.println("未查到宠物信息！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return allPets;
    }
    //根据商店Id和是否新培育查找宠物
    @Override
    public List<Pet> queryStorePetsByNew(int storeId, String isNew) {
        List<Pet> allPets = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            String querySql = "select * from Pet where storeId = ? and isNew = ?";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setInt(1,storeId);
            preparedStatement.setString(2,isNew);
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()){
                Pet pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                pet.setFeedCount(resultSet.getInt(12));
                allPets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return allPets;
    }
    // 通过petId删除
    @Override
    public void deletePet(int petId) {
        String deleteSql = "delete from Pet where petId = ?";
        DBUtils.executeSQL(deleteSql,petId);
    }
    //根据商店id分组查询宠物
    @Override
    public List<Pet> displayPetBystoreId() {
        List<Pet> allPets = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            String querySql = "select * from Pet where isNew = ? group by storeId";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1,"false" );
//            boolean Flag = preparedStatement.execute();
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()){
                Pet pet = new Pet();
                pet.setPetId(resultSet.getInt(1));
                pet.setPetName(resultSet.getString(2));
                pet.setTypeName(resultSet.getString(3));
                pet.setHealth(resultSet.getInt(4));
                pet.setLove(resultSet.getInt(5));
                pet.setBirthDay(resultSet.getDate(6));
                pet.setOwnerId(resultSet.getInt(7));
                pet.setStoreId(resultSet.getInt(8));
                pet.setPrice(resultSet.getInt(9));
                pet.setNew(resultSet.getString(10));
                pet.setPetSkill(resultSet.getInt(11));
                allPets.add(pet);
            }
//            if (Flag == true){
//                System.out.println("查询宠物信息成功！");
//            }
//            else {
//                System.out.println("未查到宠物信息！");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return allPets;
    }
}
