package com.utiltool;

import com.constantfield.RequestUrl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//接码平台操作
public class CodePlatformUtil {


    public String ReceiveCodeToken=null;
    public String Content_Length=null;


    //登录  登录的时候随机有一个session
    public String ReceiveCodeLogin(String userName,String passWord,String session){
        String response= HttpUtil.code_post(RequestUrl.ReceiveCodeLoginUrl,getReceiveCodeLoginBody(userName,passWord),getReceiveCodeLoginHeader(userName,passWord,session),true);
        return response;
    }

    private String getReceiveCodeLoginBody(String userName,String passWord){
        String jsonStr="account:"+userName+","+"password:"+passWord;
        String resBody=StringUtil.getInstance().string2Json(jsonStr);
        this.Content_Length=String.valueOf(resBody.length());
        return resBody;
    }

    private Map<String,String> getReceiveCodeLoginHeader(String userName,String passWord,String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","103.100.61.153:3690");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Accept","application/json, text/javascript, */*; q=0.01");
        headerMap.put("X-Requested-With","XMLHttpRequest");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Origin","http://103.100.61.153:3690");
        headerMap.put("Referer","http://103.100.61.153:3690/login");
        headerMap.put("Accept-Encoding","gzip, deflate");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        String cookie="name="+userName+"; password="+passWord+"; JSESSIONID="+session;
        headerMap.put("Cookie",cookie);
        return headerMap;
    }

    public String getSession(String retStr){
        //[JSESSIONID=462CA9AF5A10B9F0F758BEC2619EF2EA; Path=/; HttpOnly]
        if(retStr==null)return null;
        return retStr.substring(11,44);
    }


    //点击获取验证码
    public String ReceiveCodeGetCode(String userName,String passWord,String session){
        String response= HttpUtil.get(RequestUrl.ReceiveCodeGetCodeUrl,getReceiveCodeGetCodeHeader(userName,passWord,session));
        return response;
    }


    private Map<String,String> getReceiveCodeGetCodeHeader(String userName,String passWord,String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","103.100.61.153:3690");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Referer","http://103.100.61.153:3690/user/index");
        headerMap.put("Accept-Encoding","gzip, deflate");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        String cookie="name="+userName+"; password="+passWord+"; JSESSIONID="+session;
        headerMap.put("Cookie",cookie);
        return headerMap;
    }



    //获取手机号码返回的url
    public String ReceiveCodeGetPhone(String userName,String passWord,String session){
        String response= HttpUtil.code_post(RequestUrl.ReceiveCodeGetPhoneUrl,getReceiveCodeGetPhoneBody(),getReceiveCodeGetPhoneHeader(userName,passWord,session),false);
        return response;
    }

    private String getReceiveCodeGetPhoneBody(){
        String resBody="{}";
        this.Content_Length=String.valueOf(resBody.length());
        return resBody;
    }

    private Map<String,String> getReceiveCodeGetPhoneHeader(String userName,String passWord,String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","103.100.61.153:3690");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Accept","application/json, text/javascript, */*; q=0.01");
        headerMap.put("X-Requested-With","XMLHttpRequest");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Origin","http://103.100.61.153:3690");
        headerMap.put("Referer","http://103.100.61.153:3690/user/getCode");
        headerMap.put("Accept-Encoding","gzip, deflate");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        String cookie="name="+userName+"; password="+passWord+"; JSESSIONID="+session;
        headerMap.put("Cookie",cookie);
        return headerMap;
    }

    //{"code":"10000","data":"15006994469山东 滨州"}
    //获取手机号
    public String getPhoneNum(String jsStr){
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsStr);
        String phoneStr=jsonObject.getString("data");

        String regex="[\\d]{11}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(phoneStr);
        String result="";
        while (matcher.find()){
            result=matcher.group();
        }
        return result;  //返回手机号码
    }


    //发送获取验证码的url  //接码平台自己本身会一直发送接受验证码,所以这里  每隔20s发送一次,接受返回值，  60s过期··
    public String ReceiveCodeGetVerifyCode(String userName,String passWord,String phone,String session){
        String reqUrl= RequestUrl.ReceiveCodeGetVerifyCodeUrl+phone;
        String response= HttpUtil.code_post(reqUrl,getReceiveCodeGetVerifyCodeBody(),getReceiveCodeGetVerifyCodeHeader(userName,passWord,session),false);
        return response;
    }

    private String getReceiveCodeGetVerifyCodeBody(){
        String resBody="{}";
        this.Content_Length=String.valueOf(resBody.length());
        return resBody;
    }

    private Map<String, String> getReceiveCodeGetVerifyCodeHeader(String userName, String passWord,String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","103.100.61.153:3690");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Accept","application/json, text/javascript, */*; q=0.01");
        headerMap.put("X-Requested-With","XMLHttpRequest");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Origin","http://103.100.61.153:3690");
        headerMap.put("Referer","http://103.100.61.153:3690/user/getCode");
        headerMap.put("Accept-Encoding","gzip, deflate");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        String cookie="name="+userName+"; password="+passWord+"; JSESSIONID="+session;
        headerMap.put("Cookie",cookie);
        return headerMap;
    }

    //获取验证码  //{"code":"10000","data":[{"number":"15006994469","sms":"【陌陌科技】验证码449406仅用于登录你的陌陌，请勿提供给他人导致陌陌被盗。"}]}
    //{"code":"10000","data":[]}  这种是还没收到验证码
    public String getVerifyCode(String jsStr){
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsStr);
        JSONArray dataObj=jsonObject.getJSONArray("data");
        if(dataObj.isEmpty()){
            return null;
        }
        JSONObject smsObj=dataObj.getJSONObject(0);
        String smsStr=smsObj.getString("sms");
        String verifyCode="";
        String regex="[\\d]{6}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(smsStr);
        while (matcher.find()){
            verifyCode=matcher.group();
        }
        return verifyCode;
    }
}
