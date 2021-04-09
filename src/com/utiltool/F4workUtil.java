package com.utiltool;



import com.constantfield.RequestUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.utiltool.StringUtil.UnGzip;

public class F4workUtil {

    public static final String JIEMA_LIST="list_phone.php";
    public static final String JIEMA_LIST_GROUP="phone_show.php";
    public static final String CHINA_LIST="list_phones.php";        //获取分组需要的常量
    public static final String CHINA_LIST_GROUP="phone_shows.php";  //获取所在分组的手机号需要的常量
    public static final String ACCOUNT="1027466751@qq.com";
    public static final String PASSWORD="md1993";
    public String cookie=null;
    public String phoneNumParam =null;
    public String phoneInfo=null;
    public String Content_Length=null;



    public F4workUtil() {
        this.cookie=getF4workCookie();
        loginAccount(ACCOUNT, PASSWORD, cookie);
    }

    //第一次访问f4获取,需要通过响应体来获取session
    public Map requestF4work(){
        Map responseHead= HttpUtil.getf4(RequestUrl.F4Work,null,getF4workHeader());
        return responseHead;
    }

    private Map<String,String> getF4workHeader(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","none");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        return headerMap;
    }

    //获取cookie
    public String getF4workCookie(){
        String cookie=null;
        Map responseHeaders= requestF4work();
        Set<String> keys = responseHeaders.keySet();
        for( String key : keys ){
            if("Set-Cookie".equals(key)){
                cookie= responseHeaders.get(key).toString();
                int index_equal=cookie.indexOf("=");
                int index_semicolon=cookie.indexOf(";");
                cookie=cookie.substring(index_equal+1,index_semicolon);
                break;
            }else {
                continue;
            }
        }
        return cookie;
    }


    //登录账号密码
    public String loginAccount(String userName,String passWord,String cookie){
        String reqUrl= RequestUrl.F4WorkLogin;
        byte[] response= HttpUtil.postRetByte(reqUrl, getloginAccountBody(userName,passWord), getloginAccountHeader(cookie));
        try {
            return UnGzip(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getloginAccountBody(String userName, String passWord){
        String uname="";
        try {
            uname= URLEncoder.encode(userName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String paramStr="name="+uname+"&password="+passWord;
        return paramStr;
    }

    private Map<String, String> getloginAccountHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","40");
        headerMap.put("Content-Length","keep-alive");
        headerMap.put("Cache-Control","max-age=0");
        headerMap.put("Cache-Control","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("Origin","https://f4.work");
        headerMap.put("Content-Type","application/x-www-form-urlencoded");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/login.php");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.651521561."+timeStr+"; _gat_gtag_UA_10290770_23=1");
        return headerMap;
    }


    //点击国内China
    public String clickChinaList(String cookie){
        String response= HttpUtil.getF4zip(RequestUrl.F4WorkChinaList,null, getclickChinaListHeader(cookie));
        return response;

    }

    private Map<String,String> getclickChinaListHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/user.php");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","PHPSESSID="+cookie+"; _ga=GA1.2.187226053."+timeStr+"; _gid=GA1.2.702477965."+timeStr);
        return headerMap;
    }


    //点击获取的联通号码的分组 channelNum是对应分组的url参数，由clickChinaList()返回
    public String clickUnicomPhoneGroup(String cookie, String channelNumParam){
        String response= HttpUtil.getF4zip(RequestUrl.F4Work+channelNumParam,null, getclickUnicomChannelHeader(cookie));
        return response;
    }

    private Map<String,String> getclickUnicomChannelHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/list_china.php");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        //Cookie: _ga=GA1.2.961526060.1604501934; PHPSESSID=0s1r1ersneds3s78hdm4psn0g6; _gid=GA1.2.1885153189.1617679231
        //Cookie: PHPSESSID=ifr0842kkuaqkrk39om1jih6d3; _ga=GA1.2.187226053.1617084749; _gid=GA1.2.702477965.1617084749; _gat_gtag_UA_10290770_23=1
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.1885153189."+timeStr);
        return headerMap;
    }


    //点击接码Channel
    public String clickJiemaChannel(String cookie){
        String response= HttpUtil.getF4zip(RequestUrl.F4WorkJiemaChannel,null, getclickJiemaChannelHeader(cookie));
        return response;

    }

    private Map<String,String> getclickJiemaChannelHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/list_jiema.php");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.1885153189."+timeStr);
        return headerMap;
    }


    //点击接码频道分组
    public String clickJiemaPhoneGroup(String cookie, String channelNumParam){
        String response= HttpUtil.getF4zip(RequestUrl.F4Work+channelNumParam,null, getclickJiemaPhoneHeader(cookie));
        return response;
    }

    private Map<String,String> getclickJiemaPhoneHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/list_jiema.php");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        //Cookie: _ga=GA1.2.961526060.1604501934; PHPSESSID=0s1r1ersneds3s78hdm4psn0g6; _gid=GA1.2.1885153189.1617679231
        //Cookie: PHPSESSID=ifr0842kkuaqkrk39om1jih6d3; _ga=GA1.2.187226053.1617084749; _gid=GA1.2.702477965.1617084749; _gat_gtag_UA_10290770_23=1
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.651521561."+timeStr+"; _gat_gtag_UA_10290770_23=1");
        return headerMap;
    }


    //点击某个手机号,clickUnicomChannel()返回的值,解析后作为参数传入
    public String clickPhone(String cookie, String phoneNum){
        String response= HttpUtil.getF4zip(RequestUrl.F4Work+phoneNum,null,getPhoneHeader(cookie,phoneNum));
        return response;
    }

    private Map<String,String> getPhoneHeader(String cookie,String phoneList){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer","https://f4.work/"+phoneList);
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.1885153189."+timeStr+"; _gat_gtag_UA_10290770_23=1");
        return headerMap;
    }


    //点击获取最新短信
    public String clickGetNewSMS(String cookie, String phoneNum){
        String response= HttpUtil.getF4zip(RequestUrl.F4Work+phoneNum,null,getReceiveCodeGetCodeHeader(cookie));
        return response;
    }

    private Map<String,String> getReceiveCodeGetCodeHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","cross-site");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");

        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.1885153189."+timeStr);
        //headerMap.put("Cookie",cookie);
        return headerMap;
    }


    //点击国内china列表 或者 jiemachannel  以及点击某一分组 解析html中的字段,作为请求url的部分参数
    public List<String> ParsingHtmlData(String htmlStr,String Tag){
        List<String> retList=new ArrayList<>();
        String html = htmlStr;
        Document doc = Jsoup.parse(html);
        Elements divElement = doc.getElementsByTag("a");
        for(int i=0;i<divElement.size();i++){
            Element tmp=divElement.get(i);
            Elements tmp1=tmp.select("a");
            String str=tmp1.attr("href");
            if(str.contains(Tag)){
                retList.add(str);
            }
        }
        return retList;
    }

    //解析某个app的验证码信息 字符串信息返回存入list返回
    public List<String> ParsingVerifiCodeMsg(String htmlStr){
        String html = htmlStr;
        Document document = Jsoup.parse(html);
        List<String> MessageInfo=new ArrayList<>();

        Elements element = document.getElementsByAttributeValue("class","row border-bottom table-hover ");
        Elements element_bg = document.getElementsByAttributeValue("class","row border-bottom table-hover bg-messages");


        for(int i=0;i<element.size();i++){
            Elements str = element.get(i).select("div[class=col-xs-12 col-md-8]");
            if(!str.text().contains("已禁用项目")&&!str.text().contains("屏蔽项目")){
                MessageInfo.add(str.text());
            }
        }
        for(int i=0;i<element_bg.size();i++){
            Elements str = element_bg.get(i).select("div[class=col-xs-12 col-md-8]");
            if(!str.text().contains("已禁用项目")&&!str.text().contains("屏蔽项目")){
                MessageInfo.add(str.text());
            }
        }
        return MessageInfo;
    }


    //获取某个手机号注册账户号的标签信息
    private boolean isRegister(String htmlStr, String AppName){
        boolean flag=false;
        Document document = Jsoup.parse(htmlStr);
        Elements element = document.getElementsByAttributeValue("class","label label-info");
        for(int i=0;i<element.size();i++){
            if(element.get(i).text().contains(AppName)){
                flag=true;
                break;
            }else {
                continue;
            }
        }
        return flag;
    }

    public boolean isRegister(String AppName){
        return isRegister(phoneInfo,AppName);
    }


    //获取频道分组中的某个手机号的所有短信
    //flag标记是china还是jiema
    //groupId所在组  phoneId某个手机号  groupTag解析所在组字段  phoneTag解析某个手机号的字段
    public List<String> getChannelMsg(boolean flag,int groupId, int phoneId, String groupTag, String phoneTag){
        String groupList=null;
        if(flag){//true  是china列表
            groupList = clickChinaList(cookie);
        }else{//false  接码列表
            groupList = clickJiemaChannel(cookie);
        }
        List<String> GroupList=ParsingHtmlData(groupList,groupTag);
        System.out.println("当前有"+GroupList.size()+"个分组");
        String selectGroup=null;
        String phoneNumParam=null;
        if(groupId>=0&&groupId<=GroupList.size()){
            selectGroup=GroupList.get(groupId);
        }else {
            System.out.println("分组选择错误");
            return null;
        }

        String clickGroupStr=null;
        if(flag){
            clickGroupStr= clickUnicomPhoneGroup(cookie,selectGroup);
        }else {
            clickGroupStr=clickJiemaPhoneGroup(cookie,selectGroup);
        }

        List<String> phoneList=ParsingHtmlData(clickGroupStr,phoneTag);
        System.out.println("当前组总共有"+phoneList.size()+"个号码");
        if(phoneId>=0&&phoneId<=phoneList.size()&&phoneList.size()!=0){
            phoneNumParam=phoneList.get(phoneId); //选择某一个手机号,以下标分辨
        }
        String phoneInfo=clickPhone(cookie,phoneNumParam);
        this.phoneNumParam =phoneNumParam;
        this.phoneInfo=phoneInfo;

        String phoneNum=getPhoneNum(phoneInfo);   //获取手机号码
        System.out.println(phoneNum);
        //获取验证码字符串
        List<String> msgInfo=ParsingVerifiCodeMsg(phoneInfo);

        for(String str:msgInfo){
            System.out.println("-->"+str);
        }
        return msgInfo;
    }

    //刷新获取最新短信
    public void flushNewInfo(){
        String flushNew=clickGetNewSMS(cookie, phoneNumParam);
        List<String> msgInfo=ParsingVerifiCodeMsg(flushNew);

        for(String str:msgInfo){
            System.out.println(str);
        }
    }

    //获取手机号码
    public String getPhoneNum(String htmlStr){
        Document document = Jsoup.parse(htmlStr);
        String retStr=null;
        Elements element = document.getElementsByAttributeValue("class","text-center");
        for(int i=0;i<element.size();i++){
            retStr = element.get(i).text();
        }
        return retStr;
    }


    //购买手机号码
    public boolean buy(){
        if(!isRegister("豆瓣")){
            buyPhoneNum(this.phoneInfo);
            return true;
        }
        return false;
    }

    public String buyPhoneNum(String htmlStr){
        Document document = Jsoup.parse(htmlStr);
        Elements element = document.getElementsByTag("input");
        String retValue=element.attr("value");
        if(retValue!=null){
            String reqUrl= RequestUrl.F4Work+this.phoneNumParam;
            byte[] response= HttpUtil.postRetByte(reqUrl, getbuyPhoneNumBody(retValue), getbuyPhoneNumHeader(cookie));
            try {
                return UnGzip(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getbuyPhoneNumBody(String value){
        String paramStr="buy="+value;
        this.Content_Length=String.valueOf(paramStr.length());
        return paramStr;
    }

    private Map<String,String> getbuyPhoneNumHeader(String cookie){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Host","f4.work");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Cache-Control","max-age=0");
        headerMap.put("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        headerMap.put("sec-ch-ua-mobile","?0");
        headerMap.put("Upgrade-Insecure-Requests","1");
        headerMap.put("Origin","https://f4.work");
        headerMap.put("Content-Type","application/x-www-form-urlencoded");
        headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headerMap.put("Sec-Fetch-Site","same-origin");
        headerMap.put("Sec-Fetch-Mode","navigate");
        headerMap.put("Sec-Fetch-User","?1");
        headerMap.put("Sec-Fetch-Dest","document");
        headerMap.put("Referer",RequestUrl.F4Work+this.phoneNumParam);
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        long time = System.currentTimeMillis();
        String timeStr=time+"";
        timeStr=timeStr.substring(0,9);
        headerMap.put("Cookie","_ga=GA1.2.961526060."+timeStr+"; PHPSESSID="+cookie+"; _gid=GA1.2.651521561."+timeStr+"; _gat_gtag_UA_10290770_23=1");
        return headerMap;
    }


    //解析短信中包含的验证码
    public String getVeriyCode(String msg){
        String verifyCode=null;
        String regex="[\\d]{6}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(msg);
        while (matcher.find()){
            verifyCode=matcher.group();
        }
        return verifyCode;
    }





}
