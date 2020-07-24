package com.edu.pet.dao;

import com.edu.pet.po.Pet;

import java.util.List;

public interface PetDao {

    /*
    保存宠物信息
     */
    public void savePet(Pet pet);
    /*
    修改宠物信息
     */
    public void updatePet(Pet pet);
    //按ID查找宠物
    public Pet queryPetById(int pid);
    /*
    查找所有宠物
     */
    public List<Pet> queryAllPets();
    //根据主人Id查找所属宠物
    List<Pet> queryOwnerPets(int ownerId);

    //根据商店Id查找所属宠物
    List<Pet> queryStorePets(int storeId);

    //根据商店Id和是否新培育查找宠物
    List<Pet> queryStorePetsByNew(int storeId,String isNew);

    // 通过petId删除
    void deletePet(int petId);

    List<Pet> displayPetBystoreId();
}
