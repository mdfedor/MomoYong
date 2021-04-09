package com.momoyong;

import com.login.AccountManager;
import com.utiltool.fileGenerationUtil;

import java.util.Random;

public class Main {

    /*
     * 这个的登陆流程
     *
     * 1.首先生成 account_info(账号信息)/device_infotest(设备信息)/header_info(请求头信息)这三个文件;
     * 而账号这个默认生成的，是没有注册过的，注册时候需要填昵称，性别，生日 那么初始生成的账号信息中只有这三个有值
     * 2.然后进行注册，如果按照控制台的形式来操作，需要有哪些功能。
     *
     *
     * 初始化登陆
     * 登陆完以后,账号密码登陆，实际上就是账号密码登陆。
     * */

    public static void main(String[] args) {


        ConsoleOperation co=new ConsoleOperation();
        co.Operation();


/*        AccountManager manager=new AccountManager("832031493");
        String context="nihao";
        String path="C:\\Users\\JOY\\Pictures\\123.jpg";
        boolean flag=true;
        while(flag){
            System.out.println("请输入操做序号:");


        }

        manager.iniGuestLogin();  //游客登录及初始化
        manager.accountLogin();      //账号密码登录
        manager.cacheLogin();       //缓存登录
        manager.sendDynamic(context,path);  //发送动态 参数:文字内容,图片路径
        manager.registerAccount("","","",""); //账号注册  参数:生日,性别,电话号码,昵称*/

        //manager.INI_GUEST_LOGIN_TEST();
        //manager.AL_ACCOUNT_TEST();
        //manager.CL_LOGIN_TEST();
        //manager.SEND_DYNAMIC_TEST(context,path);



       /* com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject(true);
        json.put("name","goodjoy");
        json.put("birthday","1999-10-02");
        json.put("gender","F");

        fileGenerationUtil fi=new fileGenerationUtil();*/

        //fi.generationFile(json.toJSONString());

        //fi.modifyFilletName("123456789");

        //fi.deleteDefaultAccount();

        //fi.deleteSpecifiedAccount("123456789");
    }



}
