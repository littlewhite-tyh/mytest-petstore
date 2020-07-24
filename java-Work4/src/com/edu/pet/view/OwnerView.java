package com.edu.pet.view;

import com.edu.pet.menu.Menu;
import com.edu.pet.po.Owner;
import com.edu.pet.po.Pet;
import com.edu.pet.po.Record;
import com.edu.pet.services.PetService;
import com.edu.pet.services.impl.PetServiceImpl;

import java.util.List;
import java.util.Scanner;

public class OwnerView {
    static Scanner scanner = new Scanner(System.in);
    //主人菜单
    public static void ownerMenu(Owner owner) {
        //登陆
//        Owner owner = ownerLogin();
        //登陆失败
        if (owner==null)
            ownerMenu(owner);
        else
            //登陆成功后修改登陆标志为true
            Menu.loginFlag = true;
        //登陆标志为true才显示个人仓库
        if (Menu.loginFlag) {
            while (true) {
                System.out.println("***********欢迎来到-我的个人仓库-*************");
                //显示菜单
                System.out.println("1.我的宠物");
                System.out.println("2.喂养宠物");
                System.out.println("3.培养宠物技能");
                System.out.println("4.购买宠物");
                System.out.println("5.售卖宠物");
                System.out.println("6.查看购买记录");
                System.out.println("7.查看售卖记录");
                System.out.println("8.我的金币");
                System.out.println("9.退出登录");
                System.out.print("请选择相应数字进行操作：");
                int flag = scanner.nextInt();
                System.out.println();
                switch (flag) {
                    case 1:
                        System.out.println("宠物市场->宠物主人->我的宠物");
                        showPet(owner.getOwnerId());
                        break;
                    case 2:
                        System.out.println("宠物市场->宠物主人->喂养宠物");
                        showPet(owner.getOwnerId());
                        feed();
                        break;
                    case 3:
                        System.out.println("宠物市场->宠物主人->培养宠物技能");
                        getSkill(owner.getOwnerId());
                        break;
                    case 4:
                        System.out.println("宠物市场->宠物主人->购买宠物");
                        buyPet(owner.getOwnerId());
                        break;
                    case 5:
                        System.out.println("宠物市场->宠物主人->售卖宠物");
                        showPet(owner.getOwnerId());
                        sell(owner.getOwnerId());
                        break;
                    case 6:
                        System.out.println("宠物市场->宠物主人->查看购买记录");
                        showBuyRecords(owner.getOwnerId());
                        break;
                    case 7:
                        System.out.println("宠物市场->宠物主人->查看售卖记录");
                        showSellRecords(owner.getOwnerId());
                        break;
                    case 8:
                        System.out.println("宠物市场->宠物主人->我的金币");
                        showMyMoney(owner.getOwnerId());
                        break;
                    case 9:
                        Menu.loginFlag = false;
                        System.out.println("退出成功");
                        MenuView.checkLogin(Menu.loginFlag);
                        break;
                    default:
                        System.out.println("请输入正确的操作！");
                        break;
                }
                back();
            }
        }
    }

    //主人登陆
    public static Owner ownerLogin(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入账号：");
        String count = in.next();
        System.out.println("请输入密码：");
        String pwd = in.next();
        PetService petService = new PetServiceImpl();
        Owner owner = petService.ownerLoginCheck(count,pwd);
        return owner;
    }

    //显示宠物列表
    public static void showPet(int ownerId) {
        System.out.printf("%-10s", "宠物编号");
        System.out.printf("%-10s", "宠物名");
        System.out.printf("%-10s", "宠物类型");
        System.out.printf("%-10s", "健康值");
        System.out.printf("%-10s", "亲密度");
        System.out.printf("%-10s", "宠物生日");
        System.out.printf("%-10s", "宠物价格");
        System.out.printf("%-10s", "宠物技能");
        System.out.printf("%-10s", "喂养次数");
        PetService petService = new PetServiceImpl();
        List<Pet> pets = petService.showPet(ownerId);
        for (Pet pet : pets) {
            System.out.printf("%-10s", pet.getPetId());
            System.out.printf("%-10s", pet.getPetName());
            System.out.printf("%-10s", pet.getTypeName());
            System.out.printf("%-10s", pet.getHealth());
            System.out.printf("%-10s", pet.getLove());
            System.out.printf("%-10s", pet.getBirthDay());
            System.out.printf("%-10s", pet.getPrice());
            System.out.printf("%-10s", pet.getPetSkill());
            System.out.printf("%-10s", pet.getFeedCount());
            System.out.println();
        }
    }

    //购买宠物
    public static void buyPet(int ownerId){
        //购买前展示商店宠物信息
        MenuView.displayPetsByStoreId();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入要购买的宠物编号:");
        int petId = scanner.nextInt();
        PetService petService = new PetServiceImpl();
        System.out.println("请确认您要购买的宠物信息:");
        Pet pet = petService.queryPet(petId);
        petService.showPetInfo(pet);
        System.out.println();
        System.out.println("确认购买？y/n");
        String check = scanner.next();
        if ("y".equals(check)){
            petService.buyPet(petId,ownerId,pet.getStoreId());
            System.out.println("购买成功");
        }
    }

    //喂养宠物
    public static void feed() {
        PetService petService = new PetServiceImpl();
        System.out.print("请输入要喂养的宠物序号：");
        int petId = scanner.nextInt();
        boolean feedFlag = petService.feed(petId);
        if (feedFlag)
            System.out.println("喂养成功！");
        else
            System.out.println("每天只能喂养3次！宠物吃不下了");
        System.out.print("是否继续（y/n）：");
        String str = scanner.next();
        if (str.equals("y"))
            feed();
    }

    //培养宠物技能
    public static void getSkill(int ownerId){
        showPet(ownerId);
        System.out.print("请选择要培养的宠物编号：");
        int petId = scanner.nextInt();
        System.out.println("-------技能表-------");
        System.out.println("1.捡飞盘");
        System.out.println("2.钻箱子");
        System.out.println("3.爬滚轮");
        System.out.print("请输入想要学习的技能：");
        int skillId = scanner.nextInt();
        PetService petService = new PetServiceImpl();
        petService.petPlayGame(petId,skillId);
        System.out.println("太棒了！您的宠物学会了该技能");
        System.out.println("是否继续培养？(y/n)");
        if (scanner.next().equals("y"))
            getSkill(ownerId);
    }

    //售卖宠物
    public static void sell(int ownerId) {
        PetService petService = new PetServiceImpl();
        System.out.print("请选择你要出售的宠物编号：");
        int pid = scanner.nextInt();
        System.out.print("请选择你要交易的宠物商店编号：");
        int sid = scanner.nextInt();
        boolean sellFlag = petService.sellPet(pid, sid, ownerId);
        if (sellFlag)
            System.out.println("出售成功！");
        else
            System.out.println("未达到喂养次数（1次及以上）或健康值不足85，不能出售");
        System.out.print("是否继续（y/n）：");
        String s = scanner.next();
        if (s.equals("y"))
            sell(ownerId);
    }

    //查看主人购买记录和总支出
    public static void showBuyRecords(int ownerId) {
        System.out.printf("%-10s", "交易类型");
        System.out.printf("%-10s", "宠物id");
        System.out.printf("%-10s", "卖家id");
        System.out.printf("%-10s", "买家id");
        System.out.printf("%-10s", "交易价格");
        System.out.printf("%-10s", "交易时间");
        PetService petService = new PetServiceImpl();
        List<Record> buyRecords = petService.showBuyRecord(ownerId);
        for (Record record : buyRecords) {
            System.out.printf("%-10s", record.getType());
            System.out.printf("%-10s", record.getPid());
            System.out.printf("%-10s", record.getSid());
            System.out.printf("%-10s", record.getBid());
            System.out.printf("%-10s", record.getPrice());
            System.out.printf("%-10s", record.getDate());
            System.out.println();
        }
        System.out.printf("%-10s", "总支出：");
        System.out.println(petService.showPay(ownerId));
    }

    //查看主人售卖记录和总收入
    public static void showSellRecords(int ownerId) {
        System.out.printf("%-10s", "交易类型");
        System.out.printf("%-10s", "宠物id");
        System.out.printf("%-10s", "卖家id");
        System.out.printf("%-10s", "买家id");
        System.out.printf("%-10s", "交易价格");
        System.out.printf("%-10s", "交易时间");
        PetService petService = new PetServiceImpl();
        List<Record> sellRecords = petService.showSellRecord(ownerId);
        for (Record record : sellRecords) {
            System.out.printf("%-10s", record.getType());
            System.out.printf("%-10s", record.getPid());
            System.out.printf("%-10s", record.getSid());
            System.out.printf("%-10s", record.getBid());
            System.out.printf("%-10s", record.getPrice());
            System.out.printf("%-10s", record.getDate());
            System.out.println();
        }
        System.out.printf("%-10s", "总收入：");
        System.out.println(petService.showIncome(ownerId));
    }

    //查看我的金币
    public static void showMyMoney(int ownerId) {
        PetService petService = new PetServiceImpl();
        System.out.println("金币数：" + petService.showMoney(ownerId));
    }

    //返回上一层
    public static void back() {
        System.out.println("输入q返回上一层");
        if (scanner.next().equals("q")) ;
    }
}
