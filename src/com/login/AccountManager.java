package com.login;

import com.accountoperation.AccountOperation;
import com.utiltool.*;

public class AccountManager {

    GuestLogin guestLogin;
    PwdLogin pwdLogin;
    AccountOperation accountOperation;
    String session=null;

    private AccountInfoUtil accountInfo=AccountInfoUtil.getInstance(StringUtil.readToString("E:\\INFO\\account_info"));
    private DeviceInfoUtil deviceInfo=DeviceInfoUtil.getInstance(StringUtil.readToString("E:\\INFO\\device_infotest"));
    private HeaderInfoUtil headerInfo=HeaderInfoUtil.getInstance(StringUtil.readToString("E:\\INFO\\header_info"));

    public AccountManager(){
        guestLogin =new GuestLogin(deviceInfo,headerInfo);
        pwdLogin =new PwdLogin(accountInfo,deviceInfo,headerInfo);
        accountOperation=new AccountOperation(deviceInfo,accountInfo,headerInfo);
    }


    public String GustLogin(){
        String gustRequest= guestLogin.guestLogin();
        return gustRequest;
    }


    public String pwdLogin(String Request){
        String session= ParamUtil.getInstance().getSession(Request);
        String request= pwdLogin.pwdLogin(session);
        return request;
    }


    public String check(String Request,String strContext){
        session = ParamUtil.getInstance().getSession(Request);
        String request=accountOperation.publishCheck(session,strContext);
        return request;
    }

    public String send(String strContext){
        String request=accountOperation.publishSend(session,strContext);
        return request;
    }
}
