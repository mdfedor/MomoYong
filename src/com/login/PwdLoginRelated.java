package com.login;

import com.constantfield.RequestUrl;
import com.utiltool.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import static java.lang.Long.parseLong;

//账号密码登陆相关请求
public class PwdLoginRelated {

    private String publickey;
    private String aesKey;

    private AccountInfoUtil accountInfo =null;
    private DeviceInfoUtil deviceInfo =null;
    private HeaderInfoUtil headerInfo =null;

    private String X_SIGN=null;
    private String Content_Length=null;
    private String X_Trace_Id=null;
    private String X_KV=null;
    private String cookie=null;
    private String map_id;
    private String ck;

    public PwdLoginRelated(){
    }

    public PwdLoginRelated(AccountInfoUtil accountInfoUtil, DeviceInfoUtil deviceInfo, HeaderInfoUtil headerInfoUtil){
        this.accountInfo =accountInfoUtil;
        this.deviceInfo =deviceInfo;
        this.headerInfo =headerInfoUtil;
        this.aesKey=deviceInfo.getAesKey();
        this.publickey=deviceInfo.getPublic_key();
        this.map_id=ParamUtil.getInstance().getMapId();
        this.X_KV= ParamUtil.getInstance().getXkv(publickey);
    }



    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String AL_log_common_abtestupload_0(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_abtestupload_0 + deviceInfo.getUid(), get_AL_log_common_abtestupload_0_Body(), get_AL_log_common_abtestupload_0_Header(session));
        return response;

    }

    private Map<String,String> get_AL_log_common_abtestupload_0_Header(String session){
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

    private String get_AL_log_common_abtestupload_0_Body(){
        String RequestBody= null;
        String data="{\"log\":\"guest_button_login:first_enter\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/permissionupload?fu=6766272a7e000278b21192236b3c3eb1
    public String AL_log_common_permissionupload_0(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_permissionupload_0 + deviceInfo.getUid(), get_AL_log_common_permissionupload_0_Body(), get_AL_log_common_permissionupload_0_Header(session));
        return response;
    }

    private Map<String,String> get_AL_log_common_permissionupload_0_Header(String session){
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

    private String get_AL_log_common_permissionupload_0_Body(){
        String RequestBody= null;
        String permission="android.permission.READ_PHONE_STATE , android.permission.WRITE_EXTERNAL_STORAGE";
        try {
            permission=URLEncoder.encode(permission,"UTF-8");
            permission=permission.replaceAll("\\+",  "%20");  //空格urlencode后会变成+
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="_uidType="+deviceInfo.get_uidType()+"&_iid="+deviceInfo.get_iid()+"&permission="+permission+"&pop_type="+"user_enter"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/permissionupload?fu=6766272a7e000278b21192236b3c3eb1
    public String AL_log_common_permissionupload_1(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_permissionupload_1 + deviceInfo.getUid(), get_AL_log_common_permissionupload_1_Body(), get_AL_log_common_permissionupload_1_Header(session));
        return response;
    }

    private Map<String,String> get_AL_log_common_permissionupload_1_Header(String session){
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

    private String get_AL_log_common_permissionupload_1_Body(){
        String RequestBody= null;
        String permission="android.permission.READ_PHONE_STATE , android.permission.WRITE_EXTERNAL_STORAGE";
        String permission_result="android.permission.READ_PHONE_STATE:0 , android.permission.WRITE_EXTERNAL_STORAGE:0";
        try {
            permission=URLEncoder.encode(permission,"UTF-8");
            permission=permission.replaceAll("\\+",  "%20");  //空格urlencode后会变成+
            permission_result=URLEncoder.encode(permission_result,"UTF-8");
            permission_result=permission_result.replaceAll("\\+",  "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="_uidType="+deviceInfo.get_uidType()+"&_iid="+deviceInfo.get_iid()+"&permission="+permission+
                "&permission_result="+permission_result+ "&pop_type="+"user_enter"+"&_net_="+deviceInfo.get_net_()+
                "&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/v2/login?fu="
    public String AL_v2_login(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_v2_login + deviceInfo.getUid(), get_AL_v2_login_Body(), get_AL_v2_login_Header(session));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_v2_login_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    //{"gapps":"1","acc":"0","Serialno":"","mmuid":"","isRoot":"0","screen":"1080x1794","device_type":"android","hw":"d5cec4069e08444eccdca0b1691be31b","osversion_int":"27","RAMSize":"3852816","password":"0f5c5718c5db8c341f8efbc9b0aec20d","current_wifi":"02:00:00:00:00:00","model":"Pixel","androidId":"912cd84c01034e24","lat":"0","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","lng":"0","CpuInfo":"0-3","MacInfo":"02:00:00:00:00:00","utdid":"00000000","_iid":"5c41c5a777e799a241e7f45adc4fad7e","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"02:00:00:00:00:00","KernelVersion":"","network_class":"wifi","SerialNumber":"FA68W0308543","sensorNames":"G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0","buildnumber":"OPM4.171019.021.P1\/4820305","BootSerialno":"","imsi":"unknown","emu":"029f181d6e7ba188885c78462623c37a","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","BaseBandVersion":"8996-130091-1802061512","market_source":"14","etype":"2","oaid":"","phone_netWork":"0","dpp":"2e78d1b91c6d81260fe4723c541b90c3","bindSource":"bind_source_new_login","_uidType":"androidid","imei":"352531081145847","account":"844528798"}
    private String get_AL_v2_login_Body() {

        String jsonStr="dpp:"+ deviceInfo.getDpp()+","+"BaseBandVersion:"+ deviceInfo.getBaseBandVersion()+","+"device_type:"+ deviceInfo.getDevice_type()+","+
                "screen:"+ deviceInfo.getScreen()+","+"isRoot:"+ deviceInfo.getIsRoot()+","+"utdid:"+ deviceInfo.getUtdid()+","+"phone_netWork:"+ deviceInfo.getPhone_netWork()+","+
                "CpuInfo:"+ deviceInfo.getCpuInfo()+","+"market_source:"+ deviceInfo.getMarket_source()+","+"rom:"+ deviceInfo.getRom()+","+"etype:"+ deviceInfo.getEtype()+","+
                "bindSource:bind_source_new_login"+","+"oaid:"+ deviceInfo.getOaid()+","+"androidId:"+ deviceInfo.getAndroidId()+","+"hw:"+ deviceInfo.getHw()+","+"imei:"+ deviceInfo.getImei()+","+
                "emu:"+ deviceInfo.getEmu()+","+"version:100071"+","+"osversion_int:"+ deviceInfo.getOsversion_int()+","+"manufacturer:"+ deviceInfo.getManufacturer()+","+"phone_type:"+
                deviceInfo.getPhone_type()+","+"apksign:"+ deviceInfo.getApksign()+","+"acc:"+ deviceInfo.getAcc()+","+"imsi:unknown"+","+"sensorNames:"+ deviceInfo.getSensorNames()+","+
                "password:"+ accountInfo.getPassword()+","+"SerialNumber:"+ deviceInfo.getSerialNumber()+","+"gapps:"+ deviceInfo.getGapps()+","+"buildnumber:"+ deviceInfo.getBuildnumber()+","+"mmuid:"+ deviceInfo.getMmuid()+","+
                "_uid_:"+ deviceInfo.get_uid_()+","+"mac:"+ deviceInfo.getMac()+","+"current_wifi:02:00:00:00:00:00"+","+"network_class:"+ deviceInfo.getNetwork_class()+","+"RAMSize:"+ deviceInfo.getRAMSize()+","+
                "KernelVersion:"+ deviceInfo.getKernelVersion()+","+"_net_:"+ deviceInfo.get_net_()+","+"lng:0"+","+"MacInfo:"+ deviceInfo.getMacInfo()+","+"router_mac:"+ deviceInfo.getRouter_mac()+","+
                "Serialno:"+","+"_uidType:"+ deviceInfo.get_uidType()+","+"_iid:"+ deviceInfo.get_iid()+","+"uid:"+ deviceInfo.getUid()+","+"account:"+ accountInfo.getAccount()+","+"lat:0"+","+
                "BootSerialno:"+","+"model:"+ deviceInfo.getModel();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        try {
            this.ck= ParamUtil.getInstance().getCk(publickey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="code_version=" + deviceInfo.getCode_version() + "&mzip="+mzip + "&X-KV="+this.X_KV + "&map_id=" +this.map_id+"&ck="+this.ck;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/user/my/base?fr="
    public String AL_my_base_no_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_my_base_no_zip + accountInfo.getAccount(), get_AL_my_base_no_zip_Body(), get_AL_my_base_no_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_my_base_no_zip_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_my_base_no_zip_Body() {
        String RequestBody="_iid="+deviceInfo.get_iid()+"&source="+"login"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/abtestupload?fr=668700100
    public String AL_log_common_abtestupload_1(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_abtestupload_1 + accountInfo.getAccount(), get_AL_log_common_abtestupload_1_Body(), get_AL_log_common_abtestupload_1_Header(session));
        return response;

    }

    private Map<String,String> get_AL_log_common_abtestupload_1_Header(String session){
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

    private String get_AL_log_common_abtestupload_1_Body(){
        String RequestBody= null;
        String data="{\"log\":\"guest_login_success:account:first_enter\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }



    //"https://paas-push-api.immomo.com/push/index/unalias?appsr="
    public String AL_index_unalias(String alias, String token){
        String response= HttpUtil.post(RequestUrl.AL_index_unalias + ParamUtil.getInstance().getAppsr(), get_AL_index_unalias_Body(alias,token), get_AL_index_unalias_Header());
        return response;
    }

    private Map<String,String> get_AL_index_unalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_index_unalias_Body(String alias, String token){
        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getUnaliasMzip(alias,token);
        try {
            msc= URLEncoder.encode(msc,"UTF-8");
            mzip=URLEncoder.encode(mzip,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody= "msc="+msc+"&mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr="
    public String AL_photon_getPushHashKey(String session){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_photon_getPushHashKey + accountInfo.getAccount(), get_AL_photon_getPushHashKey_Body(), get_AL_photon_getPushHashKey_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_photon_getPushHashKey_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_photon_getPushHashKey_Body() {
        String jsonStr="alias:"+accountInfo.getAccount()+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/upload?fr=668700100
    public String AL_log_common_upload(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_upload + accountInfo.getAccount(), get_AL_log_common_upload_Body(), get_AL_log_common_upload_Header(session));
        return response;
    }

    private Map<String,String> get_AL_log_common_upload_Header(String session){
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

    private String get_AL_log_common_upload_Body(){
        String RequestBody= null;
        String sourceid=UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&opentype=hot"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://paas-push-api.immomo.com/push/index/regwithalias?appsr="
    public String AL_index_regwithalias(String alias,String sn){
        String response= HttpUtil.post(RequestUrl.AL_index_regwithalias + ParamUtil.getInstance().getAppsr(), get_AL_index_regwithalias_Body(alias,sn), get_AL_index_regwithalias_Header());
        return response;
    }

    private Map<String,String> get_AL_index_regwithalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_index_regwithalias_Body(String alias,String sn){
        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getMzip(alias,"",true);    //账号密码登陆的时候flag的标志是true
        try {
            msc= URLEncoder.encode(msc,"UTF-8");
            mzip=URLEncoder.encode(mzip,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody= "msc="+msc+"&mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://live-api.immomo.com/v3/index/config?fr="
    public String AL_v3_index_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_v3_index_config + accountInfo.getAccount(), get_AL_v3_index_config_Body(), get_AL_v3_index_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_v3_index_config_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", "live-api.immomo.com");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_v3_index_config_Body() {
        String uuid=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String jsonStr="lng:0.0"+","+"molive_uid:"+deviceInfo.getUid()+","+"imei:"+deviceInfo.getImei()+","+"__fr__:"+accountInfo.getAccount()+","+
                "net:"+deviceInfo.get_net_()+","+"uuid:"+uuid+","+"lat:0.0"+","+"androidId:"+deviceInfo.getAndroidId()+","+
                "mac:"+deviceInfo.getMac();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://live-api.immomo.com/v3/config/user/index?fr="
    public String AL_config_user_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_config_user_index + accountInfo.getAccount(), get_AL_config_user_index_Body(), get_AL_config_user_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_config_user_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", "live-api.immomo.com");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_config_user_index_Body() {
        String uuid=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String jsonStr="lng:0.0"+","+"molive_uid:"+deviceInfo.getUid()+","+"isPublicTest:0"+","+"__fr__:"+accountInfo.getAccount()+","+"net:"+deviceInfo.get_net_()+","+
                "uuid:"+uuid+","+"lat:0.0";
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://live-api.immomo.com/v3/room/p/effectListNew?fr="
    public String AL_effectListNew(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_effectListNew + accountInfo.getAccount(), get_AL_effectListNew_Body(), get_AL_effectListNew_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_effectListNew_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", "live-api.immomo.com");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_effectListNew_Body() {
        String uuidStr=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String jsonStr="lng:0.0"+","+"molive_uid:"+deviceInfo.getUid()+","+"__fr__:"+accountInfo.getAccount()+","+
                "net:"+deviceInfo.get_net_()+","+"uuid:"+uuidStr+","+"version:100071"+","+
                "lat:0.0"+","+"roomid:"+"";
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-log.immomo.com/v1/log/common/androidonlinetime?fr="
    public String AL_log_common_androidonlinetime(String session){
        String response= HttpUtil.post(RequestUrl.AL_log_common_androidonlinetime + deviceInfo.getUid(), get_AL_log_common_androidonlinetime_Body(), get_AL_log_common_androidonlinetime_Header(session));
        return response;
    }

    private Map<String,String> get_AL_log_common_androidonlinetime_Header(String session){
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

    private String get_AL_log_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/notify/business/getAllSwitch?fr="
    public String AL_business_getAllSwitch(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_business_getAllSwitch + accountInfo.getAccount(), get_AL_business_getAllSwitch_Body(), get_AL_business_getAllSwitch_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_business_getAllSwitch_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_business_getAllSwitch_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/user/message/historyv2?fr="
    public String AL_message_historyv2(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_message_historyv2 + accountInfo.getAccount(), get_AL_message_historyv2_Body(), get_AL_message_historyv2_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_message_historyv2_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_message_historyv2_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-alpha.immomo.com/momopush/fasttoken/reg?fr="
    public String AL_fasttoken_reg(String session, String token){
        String response= HttpUtil.post(RequestUrl.AL_fasttoken_reg +accountInfo.getAccount(), get_AL_fasttoken_reg_Body(token), get_AL_fasttoken_reg_Header(session));
        return response;
    }

    private Map<String,String> get_AL_fasttoken_reg_Header(String session){
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

    private String get_AL_fasttoken_reg_Body(String token){
        String RequestBody= null;
        RequestBody="_iid="+deviceInfo.get_iid()+"&token="+token+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://live-api.immomo.com/v3/room/sale/productListsAll?fr="
    public String AL_productListsAll(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_productListsAll + accountInfo.getAccount(), get_AL_productListsAll_Body(), get_AL_productListsAll_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_productListsAll_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", "live-api.immomo.com");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_productListsAll_Body() {
        String uuidStr=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String jsonStr="lng:0.0"+","+"iVersion:"+"0"+","+"molive_uid:"+deviceInfo.getUid()+","+"__fr__:"+accountInfo.getAccount()+","+
                "net:"+deviceInfo.get_net_()+","+"uuid:"+uuidStr+","+"lat:0.0";
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/emotion/custom/package?fr="
    public String AL_custom_package(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_custom_package + accountInfo.getAccount(), get_AL_custom_package_Body(), get_AL_custom_package_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_custom_package_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_custom_package_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/banners/v2/welcome?fr="
    public String AL_v2_welcome(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_v2_welcome + accountInfo.getAccount(), get_AL_v2_welcome_Body(), get_AL_v2_welcome_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_v2_welcome_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_v2_welcome_Body() {
        String jsonStr="gapps:"+deviceInfo.getGapps()+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+deviceInfo.getMmuid()+","+"screen:"+deviceInfo.getScreen()+","+
                "device_type:"+deviceInfo.getDevice_type()+","+"imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+","+"mac:"+deviceInfo.getMac()+","+
                "manufacturer:"+deviceInfo.getManufacturer()+","+"osversion_int:"+deviceInfo.getOsversion_int()+","+"rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+
                "native_ua:"+deviceInfo.getNative_ua()+","+"market_source:"+deviceInfo.getMarket_source()+","+"model:"+deviceInfo.getModel()+","+"sn:"+deviceInfo.getSn()+","+
                "net:"+deviceInfo.getNet()+","+"oaid:"+deviceInfo.getOaid()+","+"androidId:"+deviceInfo.getAndroidId()+","+"_uid_:"+deviceInfo.get_uid_()+","+"phone_type:"+deviceInfo.getPhone_type()+","+
                "phone_netWork:"+deviceInfo.getPhone_netWork()+","+"dpp:"+deviceInfo.getDpp()+","+"_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+
                "_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+"network_class:"+deviceInfo.getNetwork_class()+","+"_uidType:"+deviceInfo.get_uidType()+","+
                "imei:"+deviceInfo.getImei();
        //"native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36"
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/account/token/transfer?fr=
    public String AL_token_transfer(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_token_transfer + accountInfo.getAccount(), get_AL_token_transfer_Body(), get_AL_token_transfer_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_token_transfer_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_token_transfer_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/appconfig/index?fr="
    public String AL_appconfig_index(String session, String gustLoginRet){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_appconfig_index + accountInfo.getAccount(), get_AL_appconfig_index_Body(gustLoginRet), get_AL_appconfig_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_appconfig_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_appconfig_index_Body(String ret) {
       String marks="524288#524289#524290#105#108#103#16384#200#102#1048576#330#12#202#339#9528#203#2097152#10000#205#120#122#403#712#405#211#1203#207#213#214#215#713#407#801#223#1050#1080#50308#3000001#303#227#228#229#804#230#55000#305#805#806#307#716#719#232#414#718#1051#341#2000#415#309";
        String curResource=ParamUtil.getInstance().getCurResource(ret,false);
        String jsonStr="temp_uid:"+deviceInfo.getUid()+","+"momoid:"+accountInfo.getAccount()+","+"client:"+deviceInfo.getDevice_type()+","+"_iid:"+deviceInfo.get_iid()+","+"marks:"+marks+","+
                "curResource:"+curResource+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }
    //"https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr="
    public String AL_together_checkreddot(String session){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_together_checkreddot + accountInfo.getAccount(), get_AL_together_checkreddot_Body(), get_AL_together_checkreddot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_together_checkreddot_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_together_checkreddot_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();;
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/emotion/discover/category?fr="
    public String AL_discover_category(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_discover_category + accountInfo.getAccount(), get_AL_discover_category_Body(), get_AL_discover_category_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_discover_category_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_discover_category_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();;
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr="
    public String AL_appconfigmulti_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_appconfigmulti_index + accountInfo.getAccount(), get_AL_appconfigmulti_index_Body(), get_AL_appconfigmulti_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_appconfigmulti_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_appconfigmulti_index_Body() {
        String jsonStr="temp_uid:"+deviceInfo.getUid()+","+"momoid:"+accountInfo.getAccount()+","+"client:"+deviceInfo.getDevice_type()+","+"_iid:"+deviceInfo.get_iid()+","+
                "marks:"+"18#20#21#26#37#30#36#39#38#88#90#113#22#92#103#111#155#136#114#117#23#121#124#128#137#131#134#140#142#138#144#146#147#151#152#156#158#160#165#167#170#169#175#171#173#178#183#172#186#191#180#194#197#195#196#199#198#200#201#181#105#101#188#1#2#6"+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/setting/abtest/index?fr="
    public String AL_abtest_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_abtest_index + accountInfo.getAccount(), get_AL_abtest_index_Body(), get_AL_abtest_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_abtest_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_abtest_index_Body() {
        String jsonStr="guest_versions:"+"{}"+","+"_iid:"+deviceInfo.get_iid()+","+"login_versions:"+""+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/user/my/base?fr=668700100
    public String AL_my_base_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_my_base_zip + accountInfo.getAccount(), get_AL_my_base_zip_Body(), get_AL_my_base_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_my_base_zip_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_my_base_zip_Body() {
        String jsonStr="read_external_storage:"+"true"+","+"is_talk_back_enable:"+"0"+","+"write_external_storage:"+"true"+","+"_iid:"+deviceInfo.get_iid()+","+"source:"+"home_resume"+","+
                "read_phone_state:"+"true"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/official/config?fr="
    public String AL_official_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_official_config + accountInfo.getAccount(), get_AL_official_config_Body(), get_AL_official_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_official_config_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_official_config_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/collection/wifi/upload?fr="
    public String AL_wifi_upload(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_wifi_upload + accountInfo.getAccount(), get_AL_wifi_upload_Body(), get_AL_wifi_upload_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_wifi_upload_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_wifi_upload_Body() {
        String jsonStr="data:"+"\"[{\"mac\":\"" + deviceInfo.getMac() + "\",\"ssid\":\"\",\"conn\":\"1\"}]\""+","+"data_type:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/user/special/getallindustry?fr="
    public String AL_special_getallindustry(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_special_getallindustry + accountInfo.getAccount(), get_AL_special_getallindustry_Body(), get_AL_special_getallindustry_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_special_getallindustry_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_special_getallindustry_Body() {
        String jsonStr="v:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr="
    public String AL_webapp_wholelists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_webapp_wholelists + accountInfo.getAccount(), get_AL_webapp_wholelists_Body(), get_AL_webapp_wholelists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_webapp_wholelists_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_webapp_wholelists_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/relation/friend/getGlobalSearchUsers?fr="
    public String AL_friend_getGlobalSearchUsers(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_friend_getGlobalSearchUsers + accountInfo.getAccount(), get_AL_friend_getGlobalSearchUsers_Body(), get_AL_friend_getGlobalSearchUsers_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_friend_getGlobalSearchUsers_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_friend_getGlobalSearchUsers_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"isall:"+"1"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/emotion/list/my?fr="
    public String AL_list_my(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_list_my + accountInfo.getAccount(), get_AL_list_my_Body(), get_AL_list_my_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_list_my_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_list_my_Body() {
        String jsonStr="count:"+"-1"+","+"index:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr=
    public String AL_log_common_statfileupload_0(String session){
        String respone= HttpUtil.post(RequestUrl.AL_log_common_statfileupload_0 + accountInfo.getAccount(), get_AL_log_common_statfileupload_0_Body(), get_AL_log_common_statfileupload_0_Header(session));
        return respone;
    }

    private Map<String, String> get_AL_log_common_statfileupload_0_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", headerInfo.getLogHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    //2020-12-15 22:11:31
    private String get_AL_log_common_statfileupload_0_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();

        String time_0=ParamUtil.getInstance().getmicTime();
        String time_1=ParamUtil.getInstance().calc_end_time(time_0,999);

        String c_time_0=ParamUtil.getInstance().conversion_time(time_0);
        String c_time_1=ParamUtil.getInstance().conversion_time(time_1);  //转换成小数格式

        String radom=UUID.randomUUID().toString();
        String c_time_0_context="NearbyPeopleFragment:"+radom+":enter";
        String c_time_1_context="NearbyPeopleFragment:"+radom+":exit";
        Map mapInfo=new HashMap();
        mapInfo.put(c_time_0,c_time_0_context);
        mapInfo.put(c_time_1,c_time_1_context);
        String file="\""+ParamUtil.getInstance().construct_log_parameter(mapInfo)+"\"";
        String file_name="tmp_log_file_"+UUID.randomUUID().toString();

        //tmp_log_file_58da4a33-b46a-4aad-86de-c1a2a93ab995
        //String file="{\"1608041491776.021\":\"NearbyPeopleFragment:af583d74-8663-416a-ba3e-84163100cbfa:enter\",\"1608041491818.242\":\"NearbyPeopleFragment:af583d74-8663-416a-ba3e-84163100cbfa:exit\"}";
        String RequestBody="-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_iid\"\n" +
                "Content-Length: "+_iid.length()+"\r\n\r\n"+_iid+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_net_\"\n" +
                "Content-Length: "+_net_.length()+"\r\n\r\n"+_net_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_uid_\"\n" +
                "Content-Length: "+_uid_.length()+"\r\n\r\n"+_uid_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"stat\"; filename="+"\""+file_name+"\""+"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/emotion/update?fr="
    public String AL_emotion_update(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_emotion_update + accountInfo.getAccount(), get_AL_emotion_update_Body(), get_AL_emotion_update_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_emotion_update_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_emotion_update_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"eids:"+"custom#1364"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-vip.immomo.com/v1/vgift/service/getRedDot?fr="
    public String AL_service_getRedDot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_service_getRedDot + accountInfo.getAccount(), get_AL_service_getRedDot_Body(), get_AL_service_getRedDot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone_no_zip(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_service_getRedDot_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", "api-vip.immomo.com");
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_AL_service_getRedDot_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"version:"+"0"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

//https://api-log.immomo.com/v1/log/common/statfileupload?fr=
    public String AL_log_common_statfileupload_1(String session){
        String respone= HttpUtil.post(RequestUrl.AL_log_common_statfileupload_1 + accountInfo.getAccount(), get_AL_log_common_statfileupload_1_Body(), get_AL_log_common_statfileupload_1_Header(session));
        return respone;
    }

    private Map<String, String> get_AL_log_common_statfileupload_1_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", headerInfo.getLogHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_AL_log_common_statfileupload_1_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();

        String time_0=ParamUtil.getInstance().getmicTime();
        String time_1=ParamUtil.getInstance().calc_end_time(time_0,2000);
        String time_2=ParamUtil.getInstance().calc_end_time(time_1,2000);

        String c_time_0=ParamUtil.getInstance().conversion_time(time_0);
        String c_time_1=ParamUtil.getInstance().conversion_time(time_1);
        String c_time_2=ParamUtil.getInstance().conversion_time(time_2);    //转换时间

        String time_0_context="key_is_support64:1";
        String time_1_context="permission_notification_on";
        String time_2_context="push_result:1";

        Map mapInfo=new HashMap();
        mapInfo.put(c_time_0,time_0_context);
        mapInfo.put(c_time_1,time_1_context);
        mapInfo.put(c_time_2,time_2_context);

        String file="\""+ParamUtil.getInstance().construct_log_parameter(mapInfo)+"\"";  //转成json字符串
        String file_name="goto_log_"+UUID.randomUUID().toString();

        //filename="goto_log_cb3a632a-872b-4762-84ad-c12fd1d3f14d"
        //String file="{\"1608041494759.442\":\"key_is_support64:1\", \"1608041494775.879\":\"permission_notification_on\", \"1608041494786.629\":\"push_result:1\"}";
        String RequestBody="-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_iid\"\n" +
                "Content-Length: "+_iid.length()+"\r\n\r\n"+_iid+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_net_\"\n" +
                "Content-Length: "+_net_.length()+"\r\n\r\n"+_net_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_uid_\"\n" +
                "Content-Length: "+_uid_.length()+"\r\n\r\n"+_uid_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"stat\"; filename="+"\""+file_name+"\""+"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr="
    public String AL_people_lists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_people_lists + accountInfo.getAccount(), get_AL_people_lists_Body(), get_AL_people_lists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_people_lists_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    //43004577
    //acc是获取精度偏差Location.getAccuracy()
    //{"acc":"375.0","gapps":"1","mmuid":"","realauth":"0","screen":"1080x1794","device_type":"android","osversion_int":"27","ddian_active_time":"1603418398","online_time":"0","constellation":"0","model":"Pixel","net":"1","cell_id":"19905","lat":"37.84252","androidId":"912cd84c01034e24","age_max":"100","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"CDMA","lng":"112.43033","refreshmode":"auto","count":"24","index":"0","age_min":"18","_iid":"85729950da789ab20fec3cd184cf8285","is_bubble_up":"0","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","vip_filter":"0","style":"1","buildnumber":"OPM4.171019.021.P1\/4820305","save":"YES","locater":"202","imsi":"c82874a30ad57ba29ec5ef709e45cceb","moment_sex":"","emu":"029f181d6e7ba188885c78462623c37a","loctype":"1","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","total":"0","native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36","market_source":"14","oaid":"","social":"0","phone_netWork":"7","dpp":"198285d8baff25ed1839a3c8679b85aa","sex":"","firstRefresh":"1","_uidType":"androidid","imei":"35253108114584"}
    //{"acc":"30.0","gapps":"1","mmuid":"","realauth":"0","screen":"1080x1794","device_type":"android","osversion_int":"27","ddian_active_time":"1611981187","online_time":"0","constellation":"0","model":"Pixel","net":"1","cell_id":"43004580","lat":"37.842495","androidId":"912cd84c01034e24","age_max":"100","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","lng":"112.430403","refreshmode":"auto","count":"24","index":"0","age_min":"18","_iid":"5c41c5a777e799a241e7f45adc4fad7e","is_bubble_up":"0","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","vip_filter":"0","style":"1","buildnumber":"OPM4.171019.021.P1\/4820305","save":"YES","locater":"202","imsi":"unknown","moment_sex":"","emu":"029f181d6e7ba188885c78462623c37a","loctype":"1","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","total":"0","native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36","market_source":"14","oaid":"","social":"0","phone_netWork":"0","dpp":"d9220b3ec6a107a0b19eef2011386e38","sex":"","firstRefresh":"1","_uidType":"androidid","imei":"352531081145847"}
    private String get_AL_people_lists_Body() {
        String jsonStr="acc:"+"375.0"+","+"gapps:"+deviceInfo.getGapps()+","+"mmuid:"+deviceInfo.getMmuid()+","+"realauth:"+"0"+","+"screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"ddian_active_time:"+System.currentTimeMillis()+","+"online_time:"+"0"+","+"constellation:"+"0"+","+"model:"+deviceInfo.getModel()+","+
                "net:"+deviceInfo.getNet()+","+"cell_id:"+"0"+","+"lat:"+deviceInfo.getLat()+","+"androidId:"+deviceInfo.getAndroidId()+","+"age_max:"+"100"+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"lng:"+deviceInfo.getLng()+","+"refreshmode:"+"auto"+","+"count:"+"24"+","+"index:"+"0"+","+"age_min:"+"18"+","+"_iid:"+deviceInfo.get_iid()+","+
                "is_bubble_up:"+"0"+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+
                "network_class:"+deviceInfo.getNetwork_class()+","+"vip_filter:"+"0"+","+"style:"+"1"+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"save:"+"YES"+","+"locater:"+"202"+","+
                "imsi:unknown"+","+"moment_sex:"+""+","+"emu:"+deviceInfo.getEmu()+","+"loctype:"+"1"+","+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"total:"+"0"+","+"native_ua:"+deviceInfo.getNative_ua()+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "oaid:"+deviceInfo.getOaid()+","+"social:"+"0"+","+"phone_netWork:"+"7"+","+"dpp:"+deviceInfo.getDpp()+","+"sex:"+""+","+"firstRefresh:"+"1"+","+"_uidType:"+deviceInfo.get_uidType()+","+
                "imei:"+deviceInfo.getImei();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v2/nearby/together/generalmsg?fr="
    public String AL_together_generalmsg(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.AL_together_generalmsg + accountInfo.getAccount(), get_AL_together_generalmsg_Body(), get_AL_together_generalmsg_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_AL_together_generalmsg_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    //{"gapps":"1","buildnumber":"OPM4.171019.021.P1\/4820305","mmuid":"","screen":"1080x1794","device_type":"android","imsi":"c82874a30ad57ba29ec5ef709e45cceb","emu":"029f181d6e7ba188885c78462623c37a","mac":"02:00:00:00:00:00","manufacturer":"Google","osversion_int":"27","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","market_source":"14","model":"Pixel","net":"1","oaid":"","androidId":"912cd84c01034e24","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"CDMA","phone_netWork":"7","dpp":"7687239765f84306695cc94ef6aeb0b7","_iid":"85729950da789ab20fec3cd184cf8285","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","_uidType":"androidid","imei":"35253108114584"}
   //{"gapps":"1","buildnumber":"OPM4.171019.021.P1\/4820305","mmuid":"","screen":"1080x1794","device_type":"android","imsi":"unknown","emu":"029f181d6e7ba188885c78462623c37a","mac":"02:00:00:00:00:00","manufacturer":"Google","osversion_int":"27","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","market_source":"14","model":"Pixel","net":"1","oaid":"","androidId":"912cd84c01034e24","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","phone_netWork":"0","dpp":"18d20ed8d0625bd3c89729c0f5fdd637","_iid":"5c41c5a777e799a241e7f45adc4fad7e","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","_uidType":"androidid","imei":"352531081145847"}
    private String get_AL_together_generalmsg_Body() {
        String jsonStr="gapps:"+deviceInfo.getGapps()+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+deviceInfo.getMmuid()+","+"screen:"+deviceInfo.getScreen()+","+
                "device_type:"+deviceInfo.getDevice_type()+","+"imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "model:"+deviceInfo.getModel()+","+"net:"+deviceInfo.getNet()+","+"oaid:"+deviceInfo.getOaid()+","+"androidId:"+deviceInfo.getAndroidId()+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"phone_netWork:"+"0"+","+"dpp:"+deviceInfo.getDpp()+","+"_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+
                "apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+"network_class:"+deviceInfo.getNetwork_class()+","+
                "_uidType:"+deviceInfo.get_uidType()+","+"imei:"+deviceInfo.getImei();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://paas-push-api.immomo.com/push/index/regwithalias?appsr=
    public String CL_index_regwithalias(String alias,String sn){
        String response= HttpUtil.post(RequestUrl.CL_index_regwithalias + ParamUtil.getInstance().getAppsr(), get_CL_index_regwithalias_Body(alias,sn), get_CL_index_regwithalias_Header());
        return response;
    }

    private Map<String,String> get_CL_index_regwithalias_Header(){

        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_CL_index_regwithalias_Body(String alias,String sn){

        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getMzip(alias,sn,false);

        try {
            msc= URLEncoder.encode(msc,"UTF-8");
            mzip=URLEncoder.encode(mzip,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody= "msc="+msc+"&mzip="+mzip;

        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-alpha.immomo.com/momopush/fasttoken/reg?fr=
    public String CL_fasttoken_reg(String session, String token){
        String response= HttpUtil.post(RequestUrl.CL_fasttoken_reg +accountInfo.getAccount(), get_CL_fasttoken_reg_Body(token), get_CL_fasttoken_reg_Header(session));
        return response;
    }

    private Map<String,String> get_CL_fasttoken_reg_Header(String session){
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

    private String get_CL_fasttoken_reg_Body(String token){
        String RequestBody= null;
        RequestBody="_iid="+deviceInfo.get_iid()+"&token="+token+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://live-api.immomo.com/v3/index/config?fr=
    public String CL_index_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_index_config + accountInfo.getAccount(), get_CL_index_config_Body(), get_CL_index_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_index_config_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Content-Encoding","gzip");
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", "live-api.immomo.com");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_CL_index_config_Body() {
        String uuid=UUID.nameUUIDFromBytes(deviceInfo.getAndroidId().getBytes()).toString();
        String jsonStr="lng:"+deviceInfo.getLng()+","+"molive_uid:"+deviceInfo.getUid()+","+"imei:"+deviceInfo.getImei()+","+"__fr__:"+accountInfo.getAccount()+","+
                "net:"+deviceInfo.get_net_()+","+"uuid:"+uuid+","+"lastRespTime:"+"171"+","+"lat:"+deviceInfo.getLat()+","+"androidId:"+deviceInfo.getAndroidId()+","+
                "mac:"+deviceInfo.getMac();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/setting/abtest/index?fr=
    public String CL_abtest_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_abtest_index + accountInfo.getAccount(), get_CL_abtest_index_Body(), get_CL_abtest_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_abtest_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_abtest_index_Body() {
        String jsonStr="lng:"+deviceInfo.getLng()+","+"guest_versions:"+"{}"+","+"_iid:"+deviceInfo.get_iid()+","+"login_versions:"+""+","+"lat:"+deviceInfo.getLat()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/welcome/logs?fr="
    public String CL_welcome_logs(String session){
        String response= HttpUtil.post(RequestUrl.CL_welcome_logs+ accountInfo.getAccount(), get_CL_welcome_logs_Body(), get_CL_welcome_logs_Header(session));
        return response;
    }

    private Map<String,String> get_CL_welcome_logs_Header(String session){
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_welcome_logs_Body(){
        String jsonStr="mmuid:"+","+"cpuString:"+deviceInfo.getCpuInfo()+","+"_uidType:"+"androidid"+","+"idfa:"+deviceInfo.get_uid_()+","+"imei:"+deviceInfo.getImei()+","+"_iid:"+deviceInfo.get_iid()+","+
                "new_installation:0"+","+"oaid:"+""+"androidId:"+deviceInfo.getAndroidId()+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);

        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/mk/version/checkupdate?fr=
    public String CL_version_checkupdate(String session){
        String response= HttpUtil.post(RequestUrl.CL_version_checkupdate + accountInfo.getAccount(), get_CL_version_checkupdate_Boday(), get_CL_version_checkupdate_Header(session));
        return response;
    }

    private Map<String,String> get_CL_version_checkupdate_Header(String session){
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
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

    private String get_CL_version_checkupdate_Boday(){
        String referer="https://s.immomo.com/fep/momo/m-fes-sdk/adr-mk-jssdk/index.js";
        String jsonStr="referer:"+referer+","+"_ab_test_:"+""+","+"bid:"+"1000597"+","+"net:"+"1"+","+"version:"+"1000300000"+","+"_net_:"+deviceInfo.get_net_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);

        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/appconfig/index?fr
    //即使缓存登陆，还是需要登陆一下guestLoginRelated.INI_appconfig_index(0)  ,使用返回值
    public String CL_appconfig_index(String session, String gustLoginRet){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_appconfig_index + accountInfo.getAccount(), get_CL_appconfig_index_Body(gustLoginRet), get_CL_appconfig_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_appconfig_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_appconfig_index_Body(String ret) {
        String marks="524288#524289#524290#105#108#103#16384#200#102#1048576#330#12#202#339#9528#203#2097152#10000#205#120#122#403#712#405#211#1203#207#213#214#215#713#407#801#223#1050#1080#50308#3000001#303#227#228#229#804#230#55000#305#805#806#307#716#719#232#414#718#1051#341#2000#415#309";
        String curResource=ParamUtil.getInstance().getCurResource(ret,true);
        String jsonStr="temp_uid:"+deviceInfo.getUid()+","+"momoid:"+accountInfo.getAccount()+","+"client:"+deviceInfo.getDevice_type()+","+"_iid:"+deviceInfo.get_iid()+","+"marks:"+marks+","+
                "curResource:"+curResource+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/api/banners?fr
    public String CL_api_banners(String session){
        String response= HttpUtil.post(RequestUrl.CL_api_banners + deviceInfo.getUid(), get_CL_api_banners_Boday(), get_CL_api_banners_Header(session));
        return response;
    }

    private Map<String,String> get_CL_api_banners_Header(String session){
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
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

    private String get_CL_api_banners_Boday(){
        String jsonStr="bannerid:"+"kp2020112714260827415"+","+"_iid:"+deviceInfo.get_iid()+","+"fr:"+accountInfo.getAccount()+","+"version:"+"14067"+","+"rqid:"+"CMBwKsbBkhPMhmmTQFJjQtShrhVJRbRb"+","+
                "network:"+"outside"+","+"_net_:"+deviceInfo.get_net_()+","+"momoid:"+accountInfo.getAccount()+","+"ad_show_type:"+"1"+","+"encrypt:"+"MN4T-WG9ORGFT8J41eynQKFWDqjPqZv96bRN9Uatnhl2Yj6hLBkI0Bv_5P1V0kQevQLmcVn3fIG3MTF_aAy_3rY84z0yEV_vCXgWcz9JmgL6m_57KWBebaI1-J8wsJqKy6xMmeZzZvcK-E7JnBTavlwgHImfD3JJoRsuvgK3HG4VqQAJ4MkadsmlWL7bhmuZY60CtZ-CdZlMPolh_sqnPNhYJKdVZnjAsvI1gH_Dm5KSHThdB-N8WHiYv62KVXo4GT9wODKCAtwzt8NSTChKzRCJzFxVtafuHYXGcNeM9Vk16Ndy-XQarzIsA7nNRSGKTllH_hJV5aeAJ6SP5bTOV79YRcRjg1ooE3XNJSoW2vi4aLF2_AQ3F_Uby9924Iagb_IY9SI6_3zvN5ct4GLLZ1zpdq0q2yoWdpp1xDZzvVUGbz5R9_cf_Co-sasRf7vUG9jL0lbRfXdHhbx_Jayz8XwK3E29sbWlOrOxsWqhoMxNKfs2UbihEglz9ocuYPFH"+","+
                "action:"+"cover_showed"+","+"position:"+"23"+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api.immomo.com/v2/log/client/ipInfo?fr="
    public String CL_client_ipInfo(String session){
        Map<String,String>mapHeder=get_CL_client_ipInfo_Header(session);
        String mzip=mapHeder.get("mzip");
        mapHeder.remove("mzip");
        String reqUrl=RequestUrl.CL_client_ipInfo +accountInfo.getAccount()+"&mzip="+mzip;
        String response= HttpUtil.get(reqUrl,mapHeder);
        return response;
    }

    private Map<String,String> get_CL_client_ipInfo_Header(String session){
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN);
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
        headerMap.put("mzip",mzip);
        return headerMap;
    }

    //https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=
    public String CL_appconfigmulti_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_appconfigmulti_index + accountInfo.getAccount(), get_CL_appconfigmulti_index_Body(), get_CL_appconfigmulti_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_appconfigmulti_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_appconfigmulti_index_Body() {
        String jsonStr="temp_uid:"+deviceInfo.getUid()+","+"momoid:"+accountInfo.getAccount()+","+"client:"+deviceInfo.getDevice_type()+","+"_iid:"+deviceInfo.get_iid()+","+
                "marks:"+"18#20#21#26#37#30#36#39#38#88#90#113#22#92#103#111#155#136#114#117#23#121#124#128#137#131#134#140#142#138#144#146#147#151#152#156#158#160#165#167#170#169#175#171#173#178#183#172#186#191#180#194#197#195#196#199#198#200#201#181#105#101#188#1#2#6"+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/api/banners/v2/welcome?fr=
    public String CL_v2_welcome(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_v2_welcome + accountInfo.getAccount(), get_CL_v2_welcome_Body(), get_CL_v2_welcome_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_v2_welcome_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_v2_welcome_Body() {
        String jsonStr="gapps:"+deviceInfo.getGapps()+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+deviceInfo.getMmuid()+","+"screen:"+deviceInfo.getScreen()+","+
                "device_type:"+deviceInfo.getDevice_type()+","+"imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+","+"mac:"+deviceInfo.getMac()+","+
                "manufacturer:"+deviceInfo.getManufacturer()+","+"osversion_int:"+deviceInfo.getOsversion_int()+","+"rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+
                "native_ua:"+deviceInfo.getNative_ua()+","+"market_source:"+deviceInfo.getMarket_source()+","+"model:"+deviceInfo.getModel()+","+"sn:"+deviceInfo.getSn()+","+
                "net:"+deviceInfo.getNet()+","+"oaid:"+deviceInfo.getOaid()+","+"androidId:"+deviceInfo.getAndroidId()+","+"_uid_:"+deviceInfo.get_uid_()+","+"phone_type:"+deviceInfo.getPhone_type()+","+
                "phone_netWork:"+deviceInfo.getPhone_netWork()+","+"dpp:"+deviceInfo.getDpp()+","+"_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+
                "_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+"network_class:"+deviceInfo.getNetwork_class()+","+"_uidType:"+deviceInfo.get_uidType()+","+
                "imei:"+deviceInfo.getImei();
        //"native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36"
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        BodyStr=BodyStr.replace("#",",");
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=
    public String CL_together_checkreddot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_together_checkreddot + accountInfo.getAccount(), get_CL_together_checkreddot_Body(), get_CL_together_checkreddot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_together_checkreddot_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_together_checkreddot_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();;
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/upload?fr=  0

    public String CL_log_common_upload_0(String session){
        String response= HttpUtil.post(RequestUrl.CL_log_common_upload_0 + accountInfo.getAccount(), get_CL_log_common_upload_0_Body(), get_CL_log_common_upload_0_Header(session));
        return response;
    }

    private Map<String,String> get_CL_log_common_upload_0_Header(String session){
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

    private String get_CL_log_common_upload_0_Body(){
        String RequestBody= null;
        String sourceid=UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&opentype=cold"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/upload?fr=668700100   1
    public String CL_log_common_upload_1(String session){
        String response= HttpUtil.post(RequestUrl.CL_log_common_upload_1 + accountInfo.getAccount(), get_CL_log_common_upload_1_Body(), get_CL_log_common_upload_1_Header(session));
        return response;
    }

    private Map<String,String> get_CL_log_common_upload_1_Header(String session){
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

    private String get_CL_log_common_upload_1_Body(){
        String RequestBody= null;
        RequestBody="sourcevalue=hh"+"&opensource=other"+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/user/my/base?fr
    public String CL_my_base_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_my_base_zip + accountInfo.getAccount(), get_CL_my_base_zip_Body(), get_CL_my_base_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_my_base_zip_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_my_base_zip_Body() {
        String jsonStr="read_external_storage:"+"true"+","+"is_talk_back_enable:"+"0"+","+"write_external_storage:"+"true"+","+"_iid:"+deviceInfo.get_iid()+","+"source:"+"home_resume"+","+
                "read_phone_state:"+"true"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/androidonlinetime?fr
    public String CL_log_common_androidonlinetime(String session){
        String response= HttpUtil.post(RequestUrl.CL_log_common_androidonlinetime + deviceInfo.getUid(), get_CL_log_common_androidonlinetime_Body(), get_CL_log_common_androidonlinetime_Header(session));
        return response;
    }

    private Map<String,String> get_CL_log_common_androidonlinetime_Header(String session){
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

    private String get_CL_log_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-vip.immomo.com/v1/vgift/service/getRedDot?fr=
    public String CL_service_getRedDot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_service_getRedDot + accountInfo.getAccount(), get_CL_service_getRedDot_Body(), get_CL_service_getRedDot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone_no_zip(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_service_getRedDot_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", "api-vip.immomo.com");
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_service_getRedDot_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"version:1610015747"/*+System.currentTimeMillis()*/+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr=
    public String CL_webapp_wholelists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_webapp_wholelists + accountInfo.getAccount(), get_CL_webapp_wholelists_Body(), get_CL_webapp_wholelists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_webapp_wholelists_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_webapp_wholelists_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr=
    public String CL_log_common_statfileupload(String session){
        String respone= HttpUtil.post(RequestUrl.CL_log_common_statfileupload + accountInfo.getAccount(), get_CL_log_common_statfileupload_Body(), get_CL_log_common_statfileupload_Header(session));
        return respone;
    }

    private Map<String, String> get_CL_log_common_statfileupload_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", headerInfo.getLogHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_CL_log_common_statfileupload_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();

        String time_0=ParamUtil.getInstance().getmicTime();
        String time_1=ParamUtil.getInstance().calc_end_time(time_0,2000);
        String time_2=ParamUtil.getInstance().calc_end_time(time_1,2000);
        String time_3=ParamUtil.getInstance().calc_end_time(time_2,2000);

        String c_time_0=ParamUtil.getInstance().conversion_time(time_0);
        String c_time_1=ParamUtil.getInstance().conversion_time(time_1);
        String c_time_2=ParamUtil.getInstance().conversion_time(time_2);
        String c_time_3=ParamUtil.getInstance().conversion_time(time_3);//转换时间


        String c_time_0_context="permission_notification_on";
        String c_time_1_context="push_result:1";
        String c_time_2_context="permission_notification_on";
        String c_time_3_context="push_result:1";

        Map mapInfo=new HashMap();
        mapInfo.put(c_time_0,c_time_0_context);
        mapInfo.put(c_time_1,c_time_1_context);
        mapInfo.put(c_time_2,c_time_2_context);
        mapInfo.put(c_time_3,c_time_3_context);

        String file="\""+ParamUtil.getInstance().construct_log_parameter(mapInfo)+"\"";  //转成json字符串
        String file_name="goto_log_"+UUID.randomUUID().toString();

        //filename="goto_log_3803d4ad-f7a5-44d5-9790-4b03d6f76971"
        //String file="{\"1608102653349.905\":\"permission_notification_on\", \"1608102653397.065\":\"push_result:1\", \"1608103655660.573\":\"permission_notification_on\", \"1608103655664.088\":\"push_result:1\"}";
        String RequestBody="-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_iid\"\n" +
                "Content-Length: "+_iid.length()+"\r\n\r\n"+_iid+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_net_\"\n" +
                "Content-Length: "+_net_.length()+"\r\n\r\n"+_net_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"_uid_\"\n" +
                "Content-Length: "+_uid_.length()+"\r\n\r\n"+_uid_+"\r\n"+
                "-----------------------------7da2137580612\n" +
                "Content-Disposition: form-data; name=\"stat\"; filename="+"\""+file_name+"\""+"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr=
    public String CL_people_lists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_people_lists + accountInfo.getAccount(), get_CL_people_lists_Body(), get_CL_people_lists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_people_lists_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    //43004577
    //{"acc":"375.0","gapps":"1","mmuid":"","realauth":"0","screen":"1080x1794","device_type":"android","osversion_int":"27","ddian_active_time":"1603418398","online_time":"0","constellation":"0","model":"Pixel","net":"1","cell_id":"19905","lat":"37.84252","androidId":"912cd84c01034e24","age_max":"100","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"CDMA","lng":"112.43033","refreshmode":"auto","count":"24","index":"0","age_min":"18","_iid":"85729950da789ab20fec3cd184cf8285","is_bubble_up":"0","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","vip_filter":"0","style":"1","buildnumber":"OPM4.171019.021.P1\/4820305","save":"YES","locater":"202","imsi":"c82874a30ad57ba29ec5ef709e45cceb","moment_sex":"","emu":"029f181d6e7ba188885c78462623c37a","loctype":"1","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","total":"0","native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36","market_source":"14","oaid":"","social":"0","phone_netWork":"7","dpp":"198285d8baff25ed1839a3c8679b85aa","sex":"","firstRefresh":"1","_uidType":"androidid","imei":"35253108114584"}
    //{"acc":"30.0","gapps":"1","mmuid":"","realauth":"0","screen":"1080x1794","device_type":"android","osversion_int":"27","ddian_active_time":"1611981187","online_time":"0","constellation":"0","model":"Pixel","net":"1","cell_id":"43004580","lat":"37.842495","androidId":"912cd84c01034e24","age_max":"100","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","lng":"112.430403","refreshmode":"auto","count":"24","index":"0","age_min":"18","_iid":"5c41c5a777e799a241e7f45adc4fad7e","is_bubble_up":"0","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","vip_filter":"0","style":"1","buildnumber":"OPM4.171019.021.P1\/4820305","save":"YES","locater":"202","imsi":"unknown","moment_sex":"","emu":"029f181d6e7ba188885c78462623c37a","loctype":"1","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","total":"0","native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36","market_source":"14","oaid":"","social":"0","phone_netWork":"0","dpp":"d9220b3ec6a107a0b19eef2011386e38","sex":"","firstRefresh":"1","_uidType":"androidid","imei":"352531081145847"}
    private String get_CL_people_lists_Body() {
        String jsonStr="acc:"+"375.0"+","+"gapps:"+deviceInfo.getGapps()+","+"mmuid:"+deviceInfo.getMmuid()+","+"realauth:"+"0"+","+"screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"ddian_active_time:"+System.currentTimeMillis()+","+"online_time:"+"0"+","+"constellation:"+"0"+","+"model:"+deviceInfo.getModel()+","+
                "net:"+deviceInfo.getNet()+","+"cell_id:"+"0"+","+"lat:"+deviceInfo.getLat()+","+"androidId:"+deviceInfo.getAndroidId()+","+"age_max:"+"100"+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"lng:"+deviceInfo.getLng()+","+"refreshmode:"+"auto"+","+"count:"+"24"+","+"index:"+"0"+","+"age_min:"+"18"+","+"_iid:"+deviceInfo.get_iid()+","+
                "is_bubble_up:"+"0"+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+
                "network_class:"+deviceInfo.getNetwork_class()+","+"vip_filter:"+"0"+","+"style:"+"1"+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"save:"+"YES"+","+"locater:"+"202"+","+
                "imsi:unknown"+","+"moment_sex:"+""+","+"emu:"+deviceInfo.getEmu()+","+"loctype:"+"1"+","+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"total:"+"0"+","+"native_ua:"+deviceInfo.getNative_ua()+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "oaid:"+deviceInfo.getOaid()+","+"social:"+"0"+","+"phone_netWork:"+"7"+","+"dpp:"+deviceInfo.getDpp()+","+"sex:"+""+","+"firstRefresh:"+"1"+","+"_uidType:"+deviceInfo.get_uidType()+","+
                "imei:"+deviceInfo.getImei();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/nearby/together/generalmsg?fr
    public String CL_together_generalmsg(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_together_generalmsg + accountInfo.getAccount(), get_CL_together_generalmsg_Body(), get_CL_together_generalmsg_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_together_generalmsg_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    //{"gapps":"1","buildnumber":"OPM4.171019.021.P1\/4820305","mmuid":"","screen":"1080x1794","device_type":"android","imsi":"c82874a30ad57ba29ec5ef709e45cceb","emu":"029f181d6e7ba188885c78462623c37a","mac":"02:00:00:00:00:00","manufacturer":"Google","osversion_int":"27","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","market_source":"14","model":"Pixel","net":"1","oaid":"","androidId":"912cd84c01034e24","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"CDMA","phone_netWork":"7","dpp":"7687239765f84306695cc94ef6aeb0b7","_iid":"85729950da789ab20fec3cd184cf8285","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","_uidType":"androidid","imei":"35253108114584"}
    private String get_CL_together_generalmsg_Body() {
        String jsonStr="gapps:"+deviceInfo.getGapps()+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+deviceInfo.getMmuid()+","+"screen:"+deviceInfo.getScreen()+","+
                "device_type:"+deviceInfo.getDevice_type()+","+"imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "model:"+deviceInfo.getModel()+","+"net:"+deviceInfo.getNet()+","+"oaid:"+deviceInfo.getOaid()+","+"androidId:"+deviceInfo.getAndroidId()+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"phone_netWork:"+"0"+","+"dpp:"+deviceInfo.getDpp()+","+"_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+
                "apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+"network_class:"+deviceInfo.getNetwork_class()+","+
                "_uidType:"+deviceInfo.get_uidType()+","+"imei:"+deviceInfo.getImei();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //
    //https://api-mini.immomo.com/v1/account/token/transfer?fr=
    public String CL_token_transfer(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_token_transfer + accountInfo.getAccount(), get_CL_token_transfer_Body(), get_CL_token_transfer_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_token_transfer_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_token_transfer_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-mini.immomo.com/v1/mk/version/getupdatelist?fr=668700100
    public String CL_version_getupdatelist(String session){
        String response= HttpUtil.post(RequestUrl.CL_version_getupdatelist + deviceInfo.getUid(), get_CL_version_getupdatelist_Body(), get_CL_version_getupdatelist_Header(session));
        return response;
    }

    private Map<String,String> get_CL_version_getupdatelist_Header(String session){
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_version_getupdatelist_Body(){
        String jsonStr="_ab_test_:"+","+"bids:"+"{\"1028\":{\"version\":56,\"visited\":0,\"multi\":\"\"},\"1000101\":{\"version\":1001800000,\"visited\":0,\"multi\":\"\"},\"1029\":{\"version\":103,\"visited\":0,\"multi\":\"\"},\"1000597\":{\"version\":1000300000,\"visited\":0,\"multi\":\"\"},\"1030\":{\"version\":68,\"visited\":0,\"multi\":\"\"},\"1038\":{\"version\":10,\"visited\":0,\"multi\":\"\"},\"1093\":{\"version\":89,\"visited\":0,\"multi\":\"\"},\"1279\":{\"version\":17,\"visited\":0,\"multi\":\"\"},\"1000105\":{\"version\":2000100000,\"visited\":0,\"multi\":\"\"},\"1000218\":{\"version\":4000200000,\"visited\":0,\"multi\":\"\"},\"1000257\":{\"version\":1001100000,\"visited\":0,\"multi\":\"\"},\"1000582\":{\"version\":1000700000,\"visited\":0,\"multi\":\"\"}}"+","+
                "net:1"+","+"_net_:"+deviceInfo.get_net_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/relation/friend/getGlobalSearchUsers?fr="
    public String CL_friend_getGlobalSearchUsers(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_friend_getGlobalSearchUsers + accountInfo.getAccount(), get_CL_friend_getGlobalSearchUsers_Body(), get_CL_friend_getGlobalSearchUsers_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_friend_getGlobalSearchUsers_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_friend_getGlobalSearchUsers_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"isall:"+"0"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/emotion/discover/category?fr="
    public String CL_discover_category(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_discover_category + accountInfo.getAccount(), get_CL_discover_category_Body(), get_CL_discover_category_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_discover_category_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_discover_category_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();;
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/v1/download/android/check?fr="
    public String CL_android_check(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_android_check + accountInfo.getAccount(), get_CL_android_check_Body(), get_CL_android_check_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_android_check_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_android_check_Body() {
        String jsonStr="base_version:"+"100071"+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/api/log/uploadlocalapps?fr=668700100
    public String CL_log_uploadlocalapps(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.CL_log_uploadlocalapps + accountInfo.getAccount(), get_CL_log_uploadlocalapps_Body(), get_CL_log_uploadlocalapps_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_CL_log_uploadlocalapps_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN );
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_CL_log_uploadlocalapps_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"apps:"+"BAF52789639A73309B30A1A6C06CC4471156B69645B2470B8CC1AAD16E0BC611C2939863A9E6C06EC086A9301B13A311566B2DD1D8B12CA33737962216221B4CE4C8D04CB1331930239C160B46E06ED8C613331C23561C26C1C6C0CC8DE611A31156D6B0E62BEC85D68D2C83221622261B632307E470D80699939A36131936136699CAE0A62B663156439916A9268544E8442BD6B0E62216228946DC6499668936183373B07313278D62C0661B661883639A43332CCCC8C46C11A31156D6B0E673EDA5E6C9ACB3221630301843B14788CA91ED1BE6B796373956B344ED58F5C6C99398437399238107C06CC4471156B69645B2470B8CC1AAD16E0BC611C2639C938986DC66C806C1301B13A311566B2DD1D8B12CA33737962216220B6CDC70C86C9163991346B233892CC862896C91361C63661B4699269568916611A31156D6B0E62BEC85D68D2C83221622739C36232C85CAD04CB9531813301A93A107D4C2DC4C13831B13309A26B12CE044E8442BD6B0E6221622B1669168C4E61B43B166561B6381C6916289AC234618269399532B6CC0728DE611A31156D6B0E673EDA5E6C9ACB32216303399A311AC91DE8DCD7B96B927563B22DB85F560D8E681331A8363985389A6C47488AC6B962A5647B04683AAD1E6858D11C21C63539B539107D862D426A9131D2256B696A38DB1C2D16E739611C22218562306C870D0C6C9231830363136C94C8966C846C9531933131C43A126C46C88471156B616E6B2760B6D8DC2C14461229B66339A309166C86CE48C13131A8333B23681ACD4C8998CA123B243469C16A944E84495AD0BE611C2223193C92CC064CC4CB123B1635698638107E0CCD886B930B113303293894689C888471156B616E637F64B6EC9CAD94461131D225632F61BCDBDD2CD4E2B6711B7C2BE33A927C064D006814318306398A311ACB5D251ACA3163207553A370B8D8858CCA6C93019433018438106D862E8442BD6B447C63616A36EB9D288851173B0736399832B6C916A914C1B9319638332300B86C870E4271B133346569813114788CAB52C7356B316B6B1168344B044E0C61B369C336399569106E0CCD06C0B661926431863C9279166D8CC1B261846221D222BAD85DC888511131A568331461B06E0688906C1669C83263193B926C8C48DA699631C4663B136114788CAB52C73E6B796373956B344B06EDCA6B9139B53131823D14495C8BD6C73F6B43727B267116FB0FAC807C9639963639C338106D862E8442BD6B445563A16230E55E8CD2C63221663539943A186D864C806816398A322B2D64B8EB1D8858E9BE6B422C21130A946CC7299C681561893561B23B926DC60E06C81539C2666B0932B2C9568E444D122B2D61637563B2CADC6850E11C21163261916B1268960D42C1B439843661C63A1CC896495861313B166439A7333E6887488AC6B163722C211131B86E072910799239813239A169907CCC8898C2B139B16139830A107C0CC8544D122B2D61637E67B2DCDE495CE11C29B63231D222B8CBDC6B9ED4B3739566711B761AFD464D48689439A53631930B126E84495AD4B45B247163207AA8ECDC2B14461531953439843A9A6D864C0C689A31156D6B447638D85E8CDCD4B221622369B932B2699C2DC260B239A83439B26B12CC4C88D4C23439B3623B053B12CCC44E8442BD6B0E656B3165B6C85E0888511363333533333C16699C6914623661C23731823A986D068DCCCB9169B56269930114788CAB52C73221622231813914CDC70D086A143B026331C5323E699709546C1361963831C66B14CC444E8442BD6B0E6E6B7969B4E95EC888589301B303098A311AC91DE8DCD7B96B927563B22DB85F56AD4E6C9439A2353187381C6C47488AC6B962A5647B04683AAD1E6858D11C29B30531A63B946D072C006B1131D2256B696A38DB1C2D16E739611C222B2331386C0C699CCB9739983661B130B46956A856623939926309C53B9CCC06688471156B616E6B2760B6D8DC2C1446122331313337313ACDCC68546B9939956463393C146D06E956CB14333163018238944E84495AD0BE611C2223193C92CC064CC4CB123B1635698638107E0CCD886B930B113303293894689C888471156B616E637F64B6EC9CAD94461131D225632F61BCDBDD2CD4E2B6711B7C2BE13B1C6D46CD8E6A12318306398A311ACB5D251ACA3163207553A370B8D8858C4C6B1531B63731A238106D862E8442BD6B447C63616A36EB9D288851146B283631823A927D460E4C6A1303373169B6633A6D4CA8566A1833373161993114788CAB52C7356B316B6B1168344B0448DC6A923183353B2662BCC91C2C46C1B139C43739963894C91C6D08C33309B53221D222BAD85DC888511269C93161823994CD8648DC62B131B30831C66B186DC608D2681469C13233146114788CAB52C73E6B796373956B344B062E8442B46B736E6B7969B4E95EC886F61D71A13631C539186CC62E006B1131D2256B696A2ACD1C2910EAA47B916C611C2A126D870D446A1339883301B13D14495DAA58E63C6B04737379611858864E406C96619136698168986E4C8DC869183B073731B3089CCC468D4A60B569822A311566B2CB9CA9D2C5B36B007221622B9E6C07091C6B1639B63131A13B1CCE46EC48CA9463156631B461B078570996C11A31156D6B0E6118588C2E007C9469913231C269107DC64CC669936B216309A16C94689C6D4AC33533222A311566B2CB9DCBD2D9B27B2672216439146C86ECC4691A3115646B73673EDA5E6C9ACB322BDC2D71A43B127CC6AC486A1939C53131D222BADA5A8958E0B46385547B9166344B068D0C6C9339A13431A93C9A6C47488AC6B963AC6C6B0479BCDA544B044A926186326B16633CC996E8DA681269893461983A986996089E613533393139B22D14495DA85CD2B76B0B636B0071185886CC48CA9301836139B43134CD470C4071B331826339A26C98C95CCDC270B66B122A311566B2CB944B04413939C1630193313C6C8C6D8AC89631883833363A1E6C0C6C40623939823263222D14495DA85CD73F6B43727B2671185C47488AC23F6B1E6F6B43793ACD944ED85EB539943301B93B127CC60C0C689A31156D6B4452B8E85C8C1AAA337B0C62216539986C06CE4C6C93318306398A311ACB5D2D18D63163A37E6B4226144DCCCC04C2B4633834398331B068D68E0261336B21636B24333CCD4669107C98311A322B2D60BCD95CE856D1B163822C21126B18695C2C826C9939C13831B5633CCD468E0A689161863731C8381CC91C49944D122B2D616372261449962D02CA9939B43631A66A107D4668DCC89739993661B23B146D4709106232611A322B2D60BCDB9DEA56E93563B22C219A311AC91DE8DCD7B96B927563B22DB85F564E0C6C9539893331A3081C6C47488AC6B962A5647B04683AAD1E6858D11C21983639C538927CC68C006B1131D2256B696A38DB1C2D16E739611C222995391C6E0CCD0CCC993329323B136998CC86C99E6A9301846339C36C1CC856E88471156B616E6B2760B6D8DC2C14461221C30933383B1A6CCC2E006A9733146663330B946D0C4E4AC0B139B161698632B44E84495AD0BE611C22233638906E4C8C46C0B339A56731B63A96CC868D4ACC9639843363346C146D86688471156B616E637F64B6EC9CAD94461631813A3115623ED8DDCBD2D9B27B26722BDC2EBA6D860C04681939C63939C53894788CAB52DA2563A16463855A36E85D88885A9631830231893C9C6E472D426D122B2D6963AC6632CD1E6B92D11C2111383996399869162C4669930B083169C63892CE468D8C6A9431B1343B2368106887488AC6B16375676B0B61B2CC144B044B95631262698430BA68970C46633561C73731B6333CCE46CD846A9631923239822D14495DA85CD11C21146731A73B14C99C8950689231C33469A63B166D472CC4C2B361973369C6699A6887488AC6B1637E6F6B43793ACD944B006A17398A322B2467B6CB9DEA56E93563B22B716D7B927C866D0E6C1331A30301B13D14495DAA58A2B47B04607AA479B2CB144B0E6C9239943731C33A106C06CC4471156B6964736C60B8ECDDCA544612232439318631B868960C08CB96631468319631B26D86295C6C123993023B266B944E84495AD0BE6B27616B5360B0E8858880613931930239B168926D0C2C82CC9469B23569A6633C6D064C446233318302611A311ACB5C2B94461221C26231853A12CE070C007B113B13056981389268D6A9566237319334332331B44E84495AD0BE637F696B9272BCE8858D42699A3115646B73673EDA5E6C9ACB322BDC2D79833C146C470DC2799301863131D222BADA5A8958E0B46385547B9166344B062CC0791131C7393993081C6C47488AC6B963AC6C6B0479BCDA544B0441B269C5633B073232C8D70D4AC99633226461963A9CCC862DC279173B166439922D14495DA85CD2B76B0B636B00711858860998C0B26999393336313469160D8A6C15698432399939906DCC2C406C9439822A311566B2CB944B04491431866831836338C9162CC663313B136669A5333A6C0CCE4079916B230533222D14495DA85CD73F6B43727B2671185CC68E8442B46B736E6B7969B4E95EC886F61D71973631B83B927E066D426A9131D2256B696A2ACD1C2910EAA47B916C611C291E6D86CE0E6C9839953139A13D14495DAA58E63C6B047373796118588C6C8A69966B126831856C94CC0C2E486A973B236239966896CDCC6DC4C23303122A311566B2CB9CA9D2C5B36B007221622C18C99CAC027A146999336B08333068D62E00691239846561A431386D468C8C611A31156D6B0E611858868E4ACA1303333461C668986DCC4CC6CA1661853663313C1C6D06A8D4623661C22A311566B2CB9DCBD2D9B27B2672216308186E844958C7B3637F696B9272BCE88F6B0AFB1431A33331C3399A6E472D426D122B2D6962A56A32C91E0558E9B163622C21B43A166CC70CC66A9939C53131D222BADA5E8B18D0B47B9E69611C21146C86ED8861B169C4313B236B966E46AD4C60B831A5323B123B1C6C4CADC4C91221D2256B61673AC9DC2AD6C0B0711C222996699C6856E8D4CC9369953631C5681E6C062DC8CA1139C66831C360B07D86A88471156B616E611C211A6D8728946A16398566398362B4CCC6E8D4691469A2353B0168146E4CAC466B9221D2256B61673CDBDD2CD4E2B6711C2131D222B8CBDC6B9ED4B3739566711B761AFCC72D48699539A13631830B126E84495AD4B45B247163207AA8ECDC2B14461339C53439953A926D860C0C689A31156D6B447638D85E8CDCD4B221622161C3691A69162D08C33939913469A661BA6C06CE0271B931826933193A106D844E8442BD6B0E656B3165B6C85E0888511733213131B262B66D462D8461B839B932631263306C466C4E68136B116939C56114788CAB52C73221622563216814CD4C8896C9930B163461836B1E69568C42613461B837333939126DC44E8442BD6B0E6E6B7969B4E95EC8885A95318133018308126C47488AC23F6B1E6F6B43793ACD944ED85EB831C437398839927D460C0C689A31156D6B4452B8E85C8C1AAA337B0C6221683C186DC62E066C95318306398A311ACB5D2D18D63163A37E6B4226144C0C6856CC9631A6630B230892791C499CC33331C30831B83C9C6C86AC0A60B5311A322B2D60BCD95CE856D1B163822C21143A92CC86AD84CA163B053301A33136CE472E42CA1939A56531B83B966C4708544D122B2D61637226144D068C0663383321693194381E695C28DA699369B7326983691C6DC68DC2C994611A322B2D60BCDB9DEA56E93563B22C21930B14788CA91ED1BE6B796373956B344ED58F506A9331823939C83C986C06CC4471156B69645B2470B8CC1AAD16E0BC611C2309A338146E472E027A1301B13A311566B2DD1D8B12CA3373796221622130695C2CCA62323327383B216A9E695C2CC8C2B361823139A26C1A69962C84C11A31156D6B0E62BEC85D68D2C8322162226B283994C85CAE06C0B26B0533698233306896ACC4C8193324346988323A69144E8442BD6B0E62216221327E4C2C04699261B23361B5689C6C070E0CCB1439B303698302327C464898C11A31156D6B0E673EDA5E6C9ACB3221613A3115623ED8DDCBD2D9B27B26722BDC2EBC6C066DC07A1531930831863894788CAB52DA2563A16463855A36E85D88885B1309973831A539106E060D826D122B2D6963AC6632CD1E6B92D11C21163363193B9868D70C0AC2B23B143231836A186956AE42CA1431B46731B5323E6887488AC6B16375676B0B61B2CC144B044B143B25693191333CCCC62C08C23531A2313314381ACD8648907A1133243239922D14495DA85CD11C211837398231BC6E4C2D86C89309913169A53B107DC7285E6C1831B9333B2932307887488AC6B1637E6F6B43793ACD944B006917399A322B2467B6CB9DEA56E93563B22B7AD"+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

}
