package com.edu.pet.dao;

import com.edu.pet.po.Owner;

import java.util.List;

public interface OwnerDao {
    //按照id查询用户
    Owner queryOwnerById(int ownerId);
    //查询所有用户
    List<Owner> queryAllOwners();
    //按照用户名和密码查询
    Owner queryOwnerByCP(String count,String pwd);
    //更新用户信息
    void updateOwner(Owner owner);
}
