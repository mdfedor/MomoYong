package com.login;

import com.constantfield.RequestUrl;
import com.utiltool.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

//游客登陆相关请求
public class GuestLoginRelated {

    private DeviceInfoUtil deviceInfo =null;
    private HeaderInfoUtil headerInfo =null;

    private String Content_Length=null;
    private String X_Trace_Id=null;
    private String X_KV=null;
    private String cookie=null;

    private String prm_1;
    private String prm_2;
    private String prm_3;

    public GuestLoginRelated(){}
    //初始化需要字段
    public GuestLoginRelated(DeviceInfoUtil deviceInfoUtil, HeaderInfoUtil headerInfoUtil){
        this.deviceInfo =deviceInfoUtil;
        this.headerInfo =headerInfoUtil;
        this.X_KV="f14dd39f";        //KV的生成
    }

    //"https://live-api.immomo.com/v3/index/config?fu="
    public String INI_index_config(){
        String response= HttpUtil.post(RequestUrl.INI_index_config + deviceInfo.getUid(), get_INI_index_config_Body(), get_INI_index_config_Header());
        return response;
    }

    private Map<String,String> get_INI_index_config_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host","live-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_index_config_Body(){
        String uuid=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String RequestBody="molive_uid="+deviceInfo.getUid()+"&imei="+"&__fr__="+"&net="+deviceInfo.get_net_()+"&uuid="+uuid+"&androidId="+deviceInfo.getAndroidId()+"&mac="+deviceInfo.getMac();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/setting/abtest/index?fu=6766272a7e000278b21192236b3c3eb1
    public String INI_abtest_index(){
        String response= HttpUtil.post(RequestUrl.INI_abtest_index + deviceInfo.getUid(), get_INI_abtest_index_Body(), get_INI_abtest_index_Header());
        return response;
    }

    private Map<String,String> get_INI_abtest_index_Header(){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_abtest_index_Body(){
        String RequestBody= null;
        RequestBody="guest_versions="+"&_iid="+deviceInfo.get_iid()+"&login_versions="+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/welcome/logs?fu="
    public String INI_welcome_logs(){
        String response= HttpUtil.post(RequestUrl.INI_welcome_logs + deviceInfo.getUid(), get_INI_welcome_logs_Body(), get_INI_welcome_logs_Header());
        return response;
    }

    private Map<String,String> get_INI_welcome_logs_Header(){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }
    //cpuStr 获取cpu最大频率和最小频率
    private String get_INI_welcome_logs_Body(){
        String RequestBody= null;
        String cpuStr=deviceInfo.getCpuString();
        try {
            cpuStr=URLEncoder.encode(cpuStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="mmuid="+"&cpuString="+cpuStr+"&_uidType="+deviceInfo.get_uidType()+"&idfa="+deviceInfo.getIdfa()+
                "&imei="+deviceInfo.getImei()+"&_iid="+deviceInfo.get_iid()+"&new_installation="+"1"+"&oaid="+"&androidId="+deviceInfo.getAndroidId()+"&_net_="+deviceInfo.get_net_()+
                "&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/appconfig/index?fu="
    public String INI_appconfig_index(String marks, String ret){
        String response= HttpUtil.post(RequestUrl.INI_appconfig_index + deviceInfo.getUid(), get_INI_appconfig_index_Body(marks,ret), get_INI_appconfig_index_Header());
        return response;
    }

    private Map<String,String> get_INI_appconfig_index_Header(){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_appconfig_index_Body(String marks, String ret){   //包含三个包
        String RequestBody= null;

        if(marks.equals("1203")){
            String curResourceStr=ParamUtil.getInstance().initCurResource();
            try {
                curResourceStr=URLEncoder.encode(curResourceStr,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            RequestBody="temp_uid="+deviceInfo.get_uid_()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+"&marks="+marks+"&curResource="+curResourceStr+
                    "&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        }else if(marks.equals("75")){
            RequestBody="temp_uid="+deviceInfo.get_uid_()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+"&marks="+marks+
                    "&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        }else if(marks.equals("0")){
            marks="524288,524289,524290,105,108,103,16384,200,102,1048576,300,330,12,202,339,9528,203,2097152,10000,205,120,122,403,712,405,211,1203,207,213,214,215,713,407,801,223,1050,1080,50308,3000001,303,227,228,229,804,230,55000,305,805,806,307,716,719,232,414,718,1051,341,2000,415,309";
            String curResource=ParamUtil.getInstance().getCurResource(ret,true);
            try {
                marks=URLEncoder.encode(marks,"UTF-8");
                curResource=URLEncoder.encode(curResource,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            RequestBody="temp_uid="+deviceInfo.get_uid_()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+"&marks="+marks+"&curResource="+curResource+
                    "&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        }
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String INI_log_common_abtestupload_0(){
        String response= HttpUtil.post(RequestUrl.INI_log_common_abtestupload_0 + deviceInfo.getUid(), get_INI_log_common_abtestupload_Body_0(), get_INI_log_common_abtestupload_Header_0());
        return response;

    }

    private Map<String,String> get_INI_log_common_abtestupload_Header_0(){
        this.cookie=null;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_log_common_abtestupload_Body_0(){
        String RequestBody= null;
        String data="{\"log\":\"user_private_window_show\"}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/upload?fu=6766272a7e000278b21192236b3c3eb1
    public String INI_log_common_upload(){
        String response= HttpUtil.post(RequestUrl.INI_log_common_upload + deviceInfo.getUid(), get_INI_log_common_upload_Body(), get_INI_log_common_upload_Header());
        return response;

    }

    private Map<String,String> get_INI_log_common_upload_Header(){
        this.cookie=null;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_log_common_upload_Body(){
        String RequestBody= null;
        RequestBody="sourcevalue=hh"+"&opensource=other"+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/androidonlinetime?fu=6766272a7e000278b21192236b3c3eb1
    public String INI_log_common_androidonlinetime(){
        String response= HttpUtil.post(RequestUrl.INI_log_common_androidonlinetime + deviceInfo.getUid(), get_INI_log_common_androidonlinetime_Body(), get_INI_log_common_androidonlinetime_Header());
        return response;
    }

    private Map<String,String> get_INI_log_common_androidonlinetime_Header(){
        this.cookie=null;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_INI_log_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //guest_login
    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String GL_log_common_abtestupload_1(){
        String response= HttpUtil.post(RequestUrl.GL_log_common_abtestupload_1 + deviceInfo.getUid(), get_GL_log_common_abtestupload_1_Body(), get_GL_log_common_abtestupload_1_Header());
        return response;
    }

    private Map<String,String> get_GL_log_common_abtestupload_1_Header(){
        this.cookie=null;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_log_common_abtestupload_1_Body(){
        String RequestBody= null;
        String data="{\"log\":\"user_private_window_confirm\"}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-mini.immomo.com/v1/appconfig/index?fu=6766272a7e000278b21192236b3c3eb1   marks=75


    //游客登陆  这里获取头和体的数据都在各自的函数中去获取
    //https://api-mini.immomo.com/guest/login/index?fu=6766272a7e000278b21192236b3c3eb1
    public String GL_login_index(){
        String response= HttpUtil.post(RequestUrl.GL_login_index+ deviceInfo.getUid(), get_GL_login_index_Body(), get_GL_login_index_Header());
        return response;
    }

    private Map<String,String> get_GL_login_index_Header(){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_login_index_Body(){
        this.prm_1= UUID.randomUUID().toString();
        this.prm_2=UUID.randomUUID().toString();
        String imei="";
        String uniqueidStr= imei+"02:00:00:00:00:00";  //初始化的router_mac是 02:00:00:00:00:00
        try {
            this.prm_3= ParamUtil.getInstance().getPrm_3(uniqueidStr+ StringUtil.getInstance().getMD5(prm_1).substring(0,8)+StringUtil.getInstance().getMD5(prm_2).substring(24)).substring(10, 22);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody= null;
        try {
            RequestBody = "prm_3="+prm_3+"&gapps="+ deviceInfo.getGapps()+"&prm_2="+prm_2+"&prm_1="+prm_1+"&buildnumber=" +URLEncoder.encode(deviceInfo.getBuildnumber(),"GBK")+"&mmuid="+
                    "&screen="+ deviceInfo.getScreen()+"&device_type="+ deviceInfo.getDevice_type()+"&imsi=unknown"+"&emu="+ deviceInfo.getEmu()+"&mac="+ URLEncoder.encode(deviceInfo.getMac(),"GBK")+"&manufacturer="+ deviceInfo.getManufacturer()+
                    "&osversion_int="+ deviceInfo.getOsversion_int()+"&rom="+ deviceInfo.getRom()+"&uid="+ deviceInfo.getUid()+"&market_source="+ deviceInfo.getMarket_source()+"&model="+ deviceInfo.getModel()+""+"&uniqueid="+URLEncoder.encode(deviceInfo.getUniqueid(),"GBK")+
                    "&oaid="+"&androidId="+ deviceInfo.getAndroidId()+"&_uid_="+ deviceInfo.get_uid_()+"&phone_type="+ deviceInfo.getPhone_type()+"&phone_netWork="+ deviceInfo.getPhone_netWork()+"&dpp="+ deviceInfo.getDpp()+"&idfa="+ deviceInfo.getIdfa()+
                    "&_iid="+ deviceInfo.get_iid()+"&version=100071"+"&apksign="+ deviceInfo.getApksign()+"&_net_="+ deviceInfo.get_net_()+"&router_mac="+URLEncoder.encode("02:00:00:00:00:00","GBK")+"&network_class="+ deviceInfo.getNetwork_class()+
                    "&_uidType="+ deviceInfo.get_uidType()+"&imei="+"&uniquetime="+ System.currentTimeMillis();
            this.Content_Length =String.valueOf(RequestBody.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fu="
    public String GL_photon_getPushHashKey(String alias, String session){
        String response= HttpUtil.post(RequestUrl.GL_photon_getPushHashKey + deviceInfo.getUid(), get_GL_photon_getPushHashKey_Body(alias), get_GL_photon_getPushHashKey_Header(session));
        return response;
    }

    private Map<String,String> get_GL_photon_getPushHashKey_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_photon_getPushHashKey_Body(String alias){
        String RequestBody= null;
        RequestBody="alias="+alias+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1  1
    public String GL_log_common_abtestupload_2(String session){
        String response= HttpUtil.post(RequestUrl.GL_log_common_abtestupload_2 + deviceInfo.getUid(), get_GL_log_common_abtestupload_2_Body(), get_GL_log_common_abtestupload_2_Header(session));
        return response;

    }

    private Map<String,String> get_GL_log_common_abtestupload_2_Header(String session){
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_log_common_abtestupload_2_Body(){
        String RequestBody= null;
        String data="{\"log\":\"source_welcome:0\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1  2
    public String GL_log_common_abtestupload_3(String session){
        String response= HttpUtil.post(RequestUrl.GL_log_common_abtestupload_3 + deviceInfo.getUid(), get_GL_log_common_abtestupload_3_Body(), get_GL_log_common_abtestupload_3_Header(session));
        return response;

    }

    private Map<String,String> get_GL_log_common_abtestupload_3_Header(String session){
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_log_common_abtestupload_3_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_show_phone_login_page:first_enter\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/appconfig/index?fu=  marks=0

    //"https://api-mini.immomo.com/v1/mk/version/getupdatelist?fu="
    public String GL_version_getupdatelist(){
        String response= HttpUtil.post(RequestUrl.GL_version_getupdatelist + deviceInfo.getUid(), get_GL_version_getupdatelist_Body(), get_GL_version_getupdatelist_Header());
        return response;
    }

    private Map<String,String> get_GL_version_getupdatelist_Header(){
        this.cookie=null;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_version_getupdatelist_Body(){
        String RequestBody= null;
        String bids="{}";
        try {
            bids=URLEncoder.encode(bids,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="_ab_test_="+"&bids="+bids+"&net=1"+"&_net_="+deviceInfo.get_net_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/mk/version/checkupdate?fu="
    public String GL_version_checkupdate(){
        String response= HttpUtil.post(RequestUrl.GL_version_checkupdate + deviceInfo.getUid(), get_GL_version_checkupdate_Body(), get_GL_version_checkupdate_Header());
        return response;
    }

    private Map<String,String> get_GL_version_checkupdate_Header(){
        this.cookie=null;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_version_checkupdate_Body(){
        String RequestBody= null;
        String referer="https://s.immomo.com/fep/momo/m-fes-sdk/adr-mk-jssdk/index.js";
        try {
            referer=URLEncoder.encode(referer,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="referer="+referer+"&_ab_test_="+"&bid=1000597"+"&net=1"+"&version=0"+"&_net_="+deviceInfo.get_net_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1  3
    public String GL_log_common_abtestupload_4(String session){
        String response= HttpUtil.post(RequestUrl.GL_log_common_abtestupload_4 + deviceInfo.getUid(), get_GL_log_common_abtestupload_4_Body(), get_GL_log_common_abtestupload_4_Header(session));
        return response;
    }

    private Map<String,String> get_GL_log_common_abtestupload_4_Header(String session){
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("Host",headerInfo.getLogHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_log_common_abtestupload_4_Body(){
        String RequestBody= null;
        String data="{\"log\":\"source_guest_welcome\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/emotion/discover/category?fu="
    public String GL_discover_category(String session){
        String response= HttpUtil.post(RequestUrl.GL_discover_category + deviceInfo.getUid(), get_GL_discover_category_Body(), get_GL_discover_category_Header(session));
        return response;
    }

    private Map<String,String> get_GL_discover_category_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_discover_category_Body(){
        String RequestBody= null;
        RequestBody="_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fu="
    public String GL_appconfigmulti_index(String session){
        String response= HttpUtil.post(RequestUrl.GL_appconfigmulti_index + deviceInfo.getUid(), get_GL_appconfigmulti_index_Body(), get_GL_appconfigmulti_index_Header(session));
        return response;
    }

    private Map<String,String> get_GL_appconfigmulti_index_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_appconfigmulti_index_Body(){
        String RequestBody= null;
        String marks="18,20,21,26,37,30,36,39,38,88,90,113,22,92,103,111,155,136,114,117,23,121,124,128,137,131,134,140,142,138,144,146,147,151,152,156,158,160,165,167,170,169,175,171,173,178,183,172,186,191,180,194,197,195,196,199,198,200,201,181,105,101,188,1,2,6";
        try {
            marks=URLEncoder.encode(marks,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestBody="temp_uid="+deviceInfo.get_uid_()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+
                "&marks="+marks+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://paas-push-api.immomo.com/push/index/regwithalias?appsr=
    public String GL_index_regwithalias(String alias,String sn){
        String response= HttpUtil.post(RequestUrl.GL_index_regwithalias + ParamUtil.getInstance().getAppsr(), get_GL_index_regwithalias_Body(alias,sn), get_GL_index_regwithalias_Header());
        return response;
    }

    private Map<String,String> get_GL_index_regwithalias_Header(){

        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_index_regwithalias_Body(String alias,String sn){

        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getMzip(alias,sn,true);

        try {
            msc=URLEncoder.encode(msc,"UTF-8");
            mzip=URLEncoder.encode(mzip,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody= "msc="+msc+"&mzip="+mzip;

        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    ///https://api.immomo.com/v2/log/client/ipInfo?fu=6766272a7e000278b21192236b3c3eb1&_iid=73d40e1cbabb59caa2ee6d808b6f6e63&_net_=wifi&_uid_=6766272a7e000278b21192236b3c3eb1
    public String GL_client_ipInfo(String session){
        String reqUrl=RequestUrl.GL_client_ipInfo + deviceInfo.getUid()+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        String response= HttpUtil.get(reqUrl, get_GL_client_ipInfo_Header(session));
        return response;
    }

    private Map<String,String> get_GL_client_ipInfo_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    //"https://api-alpha.immomo.com/momopush/fasttoken/reg?fu="
    public String GL_fasttoken_reg(String session, String token){
        String response= HttpUtil.post(RequestUrl.GL_fasttoken_reg + deviceInfo.getUid(), get_GL_fasttoken_reg_Body(token), get_GL_fasttoken_reg_Header(session));
        return response;
    }

    private Map<String,String> get_GL_fasttoken_reg_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
        headerMap.put("Content-Length", this.Content_Length);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("X-LV",headerInfo.getX_LV());
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Charset",headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language",headerInfo.getAccept_Language());
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host","api-alpha.immomo.com");
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_GL_fasttoken_reg_Body(String token){
        String RequestBody= "_iid="+deviceInfo.get_iid()+"&token="+token+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }
}
