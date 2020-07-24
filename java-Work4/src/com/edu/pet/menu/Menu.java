package com.edu.pet.menu;

import com.edu.pet.view.MenuView;

import java.util.Scanner;

public class Menu {
    public static boolean loginFlag = false;
    public static void startMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("****************欢迎来到宠物商城官方网站(www.PetStore.com)******************");
        System.out.println("-----------------------------当前正在热销----------------------------------");
        //1、根据商家分类显示宠物各种信息
//        MenuView.displayPetsByStoreId();
        //2、询问是否要购买
        System.out.println("有心怡的宠物吗（y/n),可以购买哦！");
        String Opt = scanner.next();
        if ("y".equals(Opt)){
            MenuView.checkLogin(loginFlag);
            startMenu();
        }
        else {
            System.out.println("一起去---我的仓库---看看你有哪些小可爱吧！");
            MenuView.checkLogin(loginFlag);
            startMenu();
        }
        System.out.println("*****************************Pet Store************************************");
        //2
    }
    public static void main(String[] args) {
        startMenu();
    }
}
