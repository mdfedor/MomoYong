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
        this.session=ParamUtil.getInstance().getSession(request);
        return request;
    }

    //发送文字
    public String textCheck(String Request, String strContext){
        String request=accountOperation.publishCheck(session,strContext);
        return request;
    }

    public String textSend(String strContext){
        String request=accountOperation.publishSend(session,strContext);
        return request;
    }

    //发送图片+文字
    public String photoCheck(String Request, String strContext){
        String request=accountOperation.publishPhotoCheck(session,strContext);
        return request;
    }

    public String photoSend(String path, String strContext){
        String request=accountOperation.publishPhotoSend(session,path,strContext);
        return request;
    }

}
