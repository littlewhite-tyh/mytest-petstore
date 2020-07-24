package com.edu.pet.view;

import com.edu.pet.menu.Menu;
import com.edu.pet.po.Store;
import com.edu.pet.services.StoreService;
import com.edu.pet.services.impl.StoreServiceImpl;

import java.util.Scanner;

public class StoreView {
    private static Scanner scanner = new Scanner(System.in);
    //商家菜单
    public static void storeMenu(Store store){
//        Store store = StoreView.loginStore();
        //登陆失败，重新登陆
        if (store==null)
            storeMenu(store);
        else
            Menu.loginFlag = true;
        if (Menu.loginFlag) {
            while (true) {
                System.out.println("1.查看库存宠物");
                System.out.println("2.查看新培育宠物（可选择将宠物上架）");
                System.out.println("3.查看售卖记录");
                System.out.println("4.查看收购记录");
                System.out.println("5.查看结余");
                System.out.println("6.退出登录");
                System.out.print("请选择相应数字进行操作：");
                int flag = scanner.nextInt();
                switch (flag) {
                    case 1:
                        System.out.println("宠物市场->商店卖家->查看库存宠物");
                        StoreView.showStorePetsNotNew(store);
                        break;
                    case 2:
                        System.out.println("宠物市场->商店卖家->查看新培育宠物");
                        StoreView.showStorePetIsNew(store);
                        break;
                    case 3:
                        System.out.println("宠物市场->商店卖家->查看售卖记录");
                        StoreView.showStoreSellRecords(store);
                        break;
                    case 4:
                        System.out.println("宠物市场->商店卖家->查看收购记录");
                        StoreView.showStoreBuyRecords(store);
                        break;
                    case 5:
                        System.out.println("宠物市场->商店卖家->查看结余");
                        StoreView.showStoreMoney(store.getStoreId());
                        break;
                    case 6:
                        Menu.loginFlag = false;
                        System.out.println("退出成功");
                        MenuView.checkLogin(Menu.loginFlag);
                        break;
                }
                back();
            }
        }
    }
    //商家登陆
    public static Store loginStore(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入账号：");
        String count = in.next();
        System.out.println("请输入密码：");
        String pwd = in.next();
        StoreService storeService = new StoreServiceImpl();
        Store store = storeService.loginStoreCheck(count,pwd);
        return store;
    }
    //查看库存宠物
    public static void showStorePetsNotNew(Store store){
        System.out.println("宠物编号" + "\t名称\t类型\t健康度\t亲密度\t出生日期\t\t商店编号\t价格\t技能");
        StoreService storeService = new StoreServiceImpl();
        storeService.getStorePetsNotNew(store);
    }
    //查看新培育宠物并上架
    public static void showStorePetIsNew(Store store){
        System.out.println("宠物编号" + "\t名称\t类型\t健康度\t亲密度\t出生日期\t\t商店编号\t价格\t技能");
        StoreService storeService = new StoreServiceImpl();
        storeService.getStorePetsIsNew(store);
        System.out.println("是否要上架新宠物?(y/n)");
        String uploadFlag = scanner.next();
        if (uploadFlag.equals("y")) {
            System.out.print("请输入要上架的宠物编号：");
            int petId = scanner.nextInt();
            StoreService storeService1 = new StoreServiceImpl();
            storeService.uploadNewPet(petId);
            System.out.println("上架成功！");
            System.out.println("是否继续？(y/n)");
            if (scanner.next().equals("y"))
                showStorePetIsNew(store);
        }
    }
    //查看售卖记录和总收入
    public static void showStoreSellRecords(Store store){
        System.out.println("交易类型" + "\t宠物id\t卖家id\t买家id\t交易价格\t交易时间");
        StoreService storeService = new StoreServiceImpl();
        storeService.getStoreSellRecords(store);
    }
    //查看收购记录和总支出
    public static void showStoreBuyRecords(Store store){
        System.out.println("交易类型" + "\t宠物id\t卖家id\t买家id\t交易价格\t交易时间");
        StoreService storeService = new StoreServiceImpl();
        storeService.getStoreBuyRecords(store);
    }
    //查看结余
    public static void showStoreMoney(int storeId){
        StoreService storeService = new StoreServiceImpl();
        System.out.println("金币：" + storeService.getStoreMoney(storeId));
    }
    //返回上一层
    public static void back(){
        System.out.println("输入q返回上一层");
        if(scanner.next().equals("q"));
    }
}
