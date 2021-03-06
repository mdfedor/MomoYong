package com.utiltool;

import net.sf.json.JSONObject;

public class HeaderInfoUtil {

    private String X_Span_Id="";
    private String cookie="";
    private String X_KV="";
    private String Connection="";
    private String User_Agent="";
    private String Charset="";
    private String MultiUA="";
    private String Host="";
    private String Accept_Encoding="";
    private String X_Trace_Id="";
    private String X_LV="";
    private String X_SIGN="";
    private String Accept_Language="";
    private String Content_Length="";
    private String Content_Type="";
    private String logHost="";

    private static HeaderInfoUtil instance=null;

    public static HeaderInfoUtil getInstance(String headerInfo) {
        if (instance==null)
            instance= new HeaderInfoUtil(headerInfo);
        return instance;
    }

    public HeaderInfoUtil(){

    }

    public HeaderInfoUtil(String headerInfo){

        headerInfo = headerInfo.replace("\r\n", "");
        headerInfo = headerInfo.replace("\t", "");
        JSONObject jsonObject = JSONObject.fromString(headerInfo);

        this.X_Span_Id=jsonObject.getString("X-Span-Id");
        this.cookie=jsonObject.getString("cookie");
        this.X_KV=jsonObject.getString("X-KV");
        this.Connection=jsonObject.getString("Connection");
        this.User_Agent=jsonObject.getString("User-Agent");
        this.Charset=jsonObject.getString("Charset");
        this.MultiUA=jsonObject.getString("MultiUA");
        this.Host=jsonObject.getString("Host");
        this.Accept_Encoding=jsonObject.getString("Accept-Encoding");
        this.X_Trace_Id=jsonObject.getString("X-Trace-Id");
        this.X_LV=jsonObject.getString("X-LV");
        this.X_SIGN=jsonObject.getString("X-SIGN");
        this.Accept_Language=jsonObject.getString("Accept-Language");
        this.Content_Length=jsonObject.getString("Content-Length");
        this.Content_Type=jsonObject.getString("Content-Type");
        this.logHost=jsonObject.getString("logHost"); //日志host
    }

    public String HeaderInfo2String(){
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);    //排列顺序会一样
        jsonObject.put("Content-Length",this.Content_Length);
        jsonObject.put("X-SIGN",this.X_SIGN);
        jsonObject.put("X-Trace-Id",this.X_Trace_Id);
        jsonObject.put("X-KV",this.X_KV);
        jsonObject.put("X-LV",this.X_LV);
        jsonObject.put("cookie",this.cookie);
        jsonObject.put("X-Span-Id",this.X_Span_Id);
        jsonObject.put("Charset",this.Charset);
        jsonObject.put("Connection",this.Connection);
        jsonObject.put("User-Agent",this.User_Agent);
        jsonObject.put("MultiUA",this.MultiUA);
        jsonObject.put("Accept-Language",this.Accept_Language);
        jsonObject.put("Host",this.Host);
        jsonObject.put("Accept-Encoding",this.Accept_Encoding);
        jsonObject.put("Content-Type",this.Content_Type);
        jsonObject.put("logHost",this.logHost);
        return jsonObject.toString();
    }


    public String getX_Span_Id() {
        return X_Span_Id;
    }

    public void setX_Span_Id(String x_Span_Id) {
        X_Span_Id = x_Span_Id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getX_KV() {
        return X_KV;
    }

    public void setX_KV(String x_KV) {
        X_KV = x_KV;
    }

    public String getConnection() {
        return Connection;
    }

    public void setConnection(String connection) {
        Connection = connection;
    }

    public String getUser_Agent() {
        return User_Agent;
    }

    public void setUser_Agent(String user_Agent) {
        User_Agent = user_Agent;
    }

    public String getCharset() {
        return Charset;
    }

    public void setCharset(String charset) {
        Charset = charset;
    }

    public String getMultiUA() {
        return MultiUA;
    }

    public void setMultiUA(String multiUA) {
        MultiUA = multiUA;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getAccept_Encoding() {
        return Accept_Encoding;
    }

    public void setAccept_Encoding(String accept_Encoding) {
        Accept_Encoding = accept_Encoding;
    }

    public String getX_Trace_Id() {
        return X_Trace_Id;
    }

    public void setX_Trace_Id(String x_Trace_Id) {
        X_Trace_Id = x_Trace_Id;
    }

    public String getX_LV() {
        return X_LV;
    }

    public void setX_LV(String x_LV) {
        X_LV = x_LV;
    }

    public String getX_SIGN() {
        return X_SIGN;
    }

    public void setX_SIGN(String x_SIGN) {
        X_SIGN = x_SIGN;
    }

    public String getAccept_Language() {
        return Accept_Language;
    }

    public void setAccept_Language(String accept_Language) {
        Accept_Language = accept_Language;
    }

    public String getContent_Length() {
        return Content_Length;
    }

    public void setContent_Length(String content_Length) {
        Content_Length = content_Length;
    }

    public String getContent_Type() {
        return Content_Type;
    }

    public void setContent_Type(String content_Type) {
        Content_Type = content_Type;
    }

    public String getLogHost() {
        return logHost;
    }

    public void setLogHost(String logHost) {
        this.logHost = logHost;
    }
}
