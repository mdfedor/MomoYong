package com.momoyong;

import com.login.AccountManager;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleOperation {

    LinkedList<AccountManager> AccountManagerList = new LinkedList<>(); //
    LinkedList<String> AccountShowList = new LinkedList<>();   //显示当前已经存在的账号列表
    Scanner scanner = new Scanner(System.in);    //控制台输入
    String currentAccount = null;                //当前操作的账号
    String localPath = null;                     //文件夹路径

    public ConsoleOperation() {
        this.localPath = this.getClass().getResource("/").getPath();
        initAccountNumInfo();
    }

    //保存已经存在的账号文件夹
    private void initAccountNumInfo() {
        File file = new File(localPath);
        if (!file.exists()) {
            System.out.println("目录不存在...");
            return;
        }
        String[] filelist = file.list();
        if (filelist.length != 0) {
            for (String str : filelist) {
                File f = new File(localPath + str);
                if (f.isDirectory()) {
                    Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
                    Matcher isNum = pattern.matcher(str);
                    if (isNum.matches()) {
                        if(AccountShowList.contains(str))continue;
                        AccountShowList.add(str);
                    }
                }
            }
        } else {
            System.out.println("当前没有账号");
        }
    }


    //展示操作列表
    public void showOperationList() {
        System.out.println("★★★★★★★★★\r");
        System.out.println("☆ 1->账号列表\r");
        System.out.println("☆ 2->选择账号\r");
        System.out.println("☆ 3->密码登录\r");
        System.out.println("☆ 4->缓存登录\r");
        System.out.println("☆ 5->发送动态\r");
        System.out.println("☆ 6->注册账号\r");
        System.out.println("☆ 7->退出\r");
        System.out.println("★★★★★★★★★\r");
    }

    //操作选项
    public void Operation() {
        String input;
        boolean flag = true;

        do {
            showOperationList();
            System.out.println("请输入操作序号:");
            input=(String)scanner.nextLine();
            switch (input){
                case "1":{
                    System.out.println("账号列表");
                    AccountList();  //把当前文件夹已有的账号文件夹打印
                    break;
                }
                case "2":{
                    System.out.println("选择账号");
                    input=(String)scanner.nextLine();
                    ChooseAccount(input);
                    break;
                }
                case "3":{
                    System.out.println("密码登录");
                    if(currentAccount==null){
                        System.out.println("账号密码登录前,请先选择操作账号");
                        break;
                    }else {
                        PasswordLogin(currentAccount);
                    }
                    break;
                }
                case "4":{
                    System.out.println("缓存登录");
                    if(currentAccount==null){
                        System.out.println("缓存登录前,请先选择操作账号");
                        break;
                    }else {
                        CacheLogin(currentAccount);
                    }
                    break;
                }
                case "5":{
                    System.out.println("发送动态");
                    if(currentAccount==null){
                        System.out.println("发送动态前,请先选择操作账号");
                        break;
                    }else {
                        Random random=new Random();
                        String context=random.nextInt(10)+"-test";
                        //String context="hello";
                        SendDynamic(currentAccount,context,"C:\\Users\\JOY\\Pictures\\hello.jpg");   //动态图片路径
                    }
                    break;
                }
                case "6":{
                    System.out.println("注册账号");
                    System.out.println("请输入手机区号:");
                    input=(String)scanner.nextLine();
                    String countryCode=input;
                    System.out.println("请输入手机号码:");
                    input=(String)scanner.nextLine();
                    String phoneNum=input;

                    System.out.println("区号:"+countryCode+",电话号:"+phoneNum);

                    RegistrationNumber(countryCode,phoneNum);

                    break;
                }
                case "7":{
                    flag=false;
                    System.out.println("正在退出....");
                    break;
                }
                default:System.out.println("请输入正确的选择序号!");break;
            }

        }while (flag);

    }

    //展示当前所有的账号信息
    public void AccountList(){
        initAccountNumInfo();  //每获取列表时更新一下
        if(AccountShowList.size()==0){
            System.out.println("账号列表为空");
            return;
        }else {
            for(String str:AccountShowList){
                System.out.println(str);
            }
        }
    }

    //选择账号并进行过滤
    public void ChooseAccount(String account){
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(account);
        if (!isNum.matches()) {
            System.out.println("检查是否输入账号\n");
            return;
        }

        for(String str:AccountShowList){
            if(account.equals(str)){
                currentAccount=account;
                System.out.println("选择账号:"+account);
                return;
            }
        }
        System.out.println("不存在该账号,请核实\n");
    }

    //账号密码登录
    public void PasswordLogin(String account){
        AccountManager accountManager=new AccountManager(account);
        if(AccountManagerList.size()==0){
            AccountManagerList.add(accountManager);
        }else {
            for(AccountManager acc:AccountManagerList){
                if(acc.getAccountNum().equals(account)){
                    System.out.println("账号已登录,不要重复登录(PasswordLogin)");
                    return;
                }
            }
        }
        accountManager.iniGuestLogin();
        accountManager.accountLogin();

    }

    //缓存登录
    public void CacheLogin(String account){
        AccountManager accountManager=new AccountManager(account);
        if(AccountManagerList.size()==0){
            AccountManagerList.add(accountManager);
        }else {
            for(AccountManager acc:AccountManagerList){
                if(acc.getAccountNum().equals(account)){
                    System.out.println("账号已登录,不要重复登录(CacheLogin)");
                    return;
                }
            }
        }
        //accountManager.iniGuestLogin();
        accountManager.CL_LOGIN_TEST();
    }

    //发送动态
    public void SendDynamic(String account,String context,String PhotoPath){
        if(AccountManagerList.size()==0){
            System.out.println("发送动态前,请先登录账号");
            return;
        }else {
            for(AccountManager acc:AccountManagerList){
                if(acc.getAccountNum().equals(account)){
                    acc.sendDynamic(context,PhotoPath);
                    acc.sendDynamicReturn();
                    System.out.println("发送动态成功");
                    return;
                }
            }
            System.out.println("当前账号并未登录,请核实");
        }
    }

    //注册号码
    public void RegistrationNumber(String countrycode,String phone){

        Random random=new Random();
        //String str=random.nextInt(50)+"711";  //昵称,性别,年龄  生成可以通过读文件来进行填写
        String name="HelloJoy";      //注册时候的昵称
        String birthday="1999-10-02";
        String gender="F";
        AccountManager regist=new AccountManager(name,birthday,gender);   //生成默认文件夹

        AccountManager zero_account=new AccountManager("000");  //读取000账号,初始化账号信息
        zero_account.iniGuestLogin();
        //账号注册  注册后会返回一个陌陌id  注册过程中账号信息文件会被修改

        //接码平台先获取手机号,从这里传进来

        String momoid=zero_account.registerAccount(birthday,gender,phone,countrycode,name,"C:\\Users\\JOY\\Desktop\\smallpad.jpg","C:\\Users\\JOY\\Desktop\\bigpad.jpg"); //一个150*150像素,一个600*600像素
        //String momoid=null;  //根据陌陌id来判断是否注册成功

        zero_account.MODIFY_TEST();//修改密码
        if(momoid!=null){
            regist.getFileGeneration().modifyFilletName(momoid);  //修改000文件夹名字
            AccountShowList.add(momoid);

            System.out.println("注册成功!momoid为:"+momoid);
        }else {
            regist.getFileGeneration().deleteDefaultAccount();  //删除默认生成的账号信息
            System.out.println("注册失败!");
        }
    }
}

