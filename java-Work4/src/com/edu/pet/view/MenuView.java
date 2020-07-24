package com.edu.pet.view;

import com.edu.pet.menu.Menu;
import com.edu.pet.po.Owner;
import com.edu.pet.po.Pet;
import com.edu.pet.po.Store;
import com.edu.pet.services.PetService;
import com.edu.pet.services.impl.PetServiceImpl;
import com.edu.pet.utils.DateUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    //1、根据商家分类显示宠物各种信息
    public static void displayPetsByStoreId(){
        PetService petService = new PetServiceImpl();
        List<Pet> allPets = petService.displayPetsByStoreId();
        System.out.printf("%-10s","宠物编号");
        System.out.printf("%-10s","宠物名");
        System.out.printf("%-10s","宠物类型");
        System.out.printf("%-10s","健康值");
        System.out.printf("%-10s","亲密度");
        System.out.printf("%-10s","宠物生日");
        System.out.printf("%-10s","所属商店");
        System.out.printf("%-10s","宠物价格");
        System.out.printf("%-10s","宠物技能");
        Iterator<Pet> iterator = allPets.iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            Pet pet = (Pet) object;
            System.out.printf("%-10s",pet.getPetId());
            System.out.printf("%-10s",pet.getPetName());
            System.out.printf("%-10s",pet.getTypeName());
            System.out.printf("%-10s",pet.getHealth());
            System.out.printf("%-10s",pet.getLove());
            System.out.printf("%-10s",DateUtils.dateConvertString(pet.getBirthDay()));
            System.out.printf("%-10s",pet.getStoreId());
            System.out.printf("%-10s",pet.getPrice());
            System.out.printf("%-10s",pet.getPetSkill());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------");
    }
    public static void checkLogin(boolean loginFlag){
        Scanner scanner = new Scanner(System.in);
        if (loginFlag == false) {
            System.out.println("请先登录:");
            System.out.println("----------------------请选择登录方式--------------------");
            System.out.println("                       1、商家登录");
            System.out.println("                       2、个人登录");
            System.out.println("                       3、退出");
            System.out.println("-------------------------------------------------------");
            System.out.println("请输入您的选择:");
            int logOpt = scanner.nextInt();
            switch (logOpt){
                case 1 :
                    Store store = StoreView.loginStore();
                    StoreView.storeMenu(store);
                    break;
                case 2 :
                    Owner owner = OwnerView.ownerLogin();
                    OwnerView.ownerMenu(owner);
                    break;
                case 3:
                    System.out.println("欢迎下次光临");
                    Menu.startMenu();
                    break;
                default:
                    System.out.println("您输入有误，请重新输入:");
                    MenuView.checkLogin(loginFlag);
                    break;
            }
        }
    }
//    public static void BackOut(){
//        System.out.println("按下任意键后回车返回上一级");
//        char c = new Scanner(System.in).next().charAt(0);
//    }
}
