package com.accountoperation;

import com.constantfield.RequestUrl;
import com.utiltool.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


//账号操作类  REG_开头是账号注册发送的包  OP_开头是账号的一些操作,包括修改密码发送的包
public class AccountOperation {

    private DeviceInfoUtil deviceInfo =null;       //账号设备信息
    private AccountInfoUtil accountInfo =null;     //账号信息
    private HeaderInfoUtil headerInfo =null;       //请求头信息

    private String publicKey;                      //公钥，构造ck参数使用
    private String aesKey;                         //加密mzip使用
    private String Content_Length;                 //请求体长度
    private String X_KV;                           //参数使用
    private String X_SIGN;                         //参数使用
    private String X_Trace_Id;                     //参数使用
    private String ck;                             //参数使用
    private String map_id;                         //参数使用
    private String cookie;                         //session

    public AccountOperation(DeviceInfoUtil deviceInfoUtil,AccountInfoUtil accountInfoUtil,HeaderInfoUtil headerInfoUtil){
        this.deviceInfo =deviceInfoUtil;
        this.accountInfo =accountInfoUtil;
        this.headerInfo =headerInfoUtil;
        this.aesKey=deviceInfo.getAesKey().substring(0,16);
        this.publicKey=deviceInfo.getPublic_key();
        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);
        this.map_id=ParamUtil.getInstance().getMapId();
    }

    /*
     * 点击我的发送的包
     * */
    //https://api-mini.immomo.com/v2/fast/homepage/play/discover?fr=
    public String OP_play_discover(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_play_discover + accountInfo.getAccount(), get_OP_play_discover_Body(), get_OP_play_discover_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_play_discover_Header(String session) {
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

    private String get_OP_play_discover_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"source:"+"myinfo"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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
    public String OP_log_common_statfileupload(String session){
        String respone= HttpUtil.post(RequestUrl.OP_log_common_statfileupload + accountInfo.getAccount(), get_OP_log_common_statfileupload_Body(), get_OP_log_common_statfileupload_Header(session));
        return respone;
    }

    private Map<String, String> get_OP_log_common_statfileupload_Header(String session) {
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

    private String get_OP_log_common_statfileupload_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();
        String file="{\"1608172484511.357\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:309790962:14389:null:null:1:recommend:-1:1:show_full\",\"1608172484511.002\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:173692683:offline:null:null:0:recommend:-1:2:show\",\"1608172484511.910\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:173692683:offline:null:null:0:recommend:-1:2:show_full\",\"1608172484511.328\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:355000967:8156:null:null:0:recommend:-1:3:show\",\"1608172484512.737\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:355000967:8156:null:null:0:recommend:-1:3:show_full\",\"1608172483173.333\":\"NearbyPeopleFragment:00847701-1675-4587-8963-0f8a9a5ca2e5:enter\",\"1608172484512.684\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:131916942:offline:null:null:0:recommend:-1:4:show\",\"1608172484510.514\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:213210886:2032:null:null:0:recommend:-1:0:show_full\",\"1608172498209.225\":\"NearbyPeopleFragment:00847701-1675-4587-8963-0f8a9a5ca2e5:exit\",\"1608172484509.158\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:213210886:2032:null:null:0:recommend:-1:0:show\",\"1608172484510.379\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:309790962:14389:null:null:1:recommend:-1:1:show\",\"1608172484512.141\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:131916942:offline:null:null:0:recommend:-1:4:show_full\",\"1608172484512.053\":\"477a5b5a26539d27dbda0e07a455bc1496551:np:momoid:479720881:469:null:null:0:recommend:-1:5:show\"}";
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
                "Content-Disposition: form-data; name=\"stat\"; filename=\"tmp_log_file_b167ba98-fda4-4557-adf2-d0da376ac3fc\"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr=
    public String OP_my_base_click(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_my_base_click + accountInfo.getAccount(), get_OP_my_base_click_Body(), get_OP_my_base_click_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_my_base_click_Header(String session) {
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

    private String get_OP_my_base_click_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"source:"+"myinfo"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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
    public String OP_my_base_checking_data(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_my_base_checking_data + accountInfo.getAccount(), get_OP_my_base_checking_data_Body(), get_OP_my_base_checking_data_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_my_base_checking_data_Header(String session) {
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

    private String get_OP_my_base_checking_data_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"source:"+"self"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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


    //https://api-mini.immomo.com/v1/user/feed/profile?fr=668700100
    public String OP_feed_profile(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_feed_profile + accountInfo.getAccount(), get_OP_feed_profile_Body(), get_OP_feed_profile_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_feed_profile_Header(String session) {
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

    private String get_OP_feed_profile_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"source:"+"profile"+","+"remoteid:"+accountInfo.getAccount()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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
    public String OP_version_checkupdate(String session){
        String response= HttpUtil.post(RequestUrl.OP_version_checkupdate + accountInfo.getAccount(), get_OP_version_checkupdate_Boday(), get_OP_version_checkupdate_Header(session));
        return response;
    }

    private Map<String,String> get_OP_version_checkupdate_Header(String session){
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

    private String get_OP_version_checkupdate_Boday(){
        String referer="https://s.immomo.com/fep/momo/fep-web/activity-layer/index.html?_bid=1000568&_wk=1&name=fast_sign&profilemomoid="+accountInfo.getAccount();
        String jsonStr="referer:"+referer+","+"_ab_test_:"+""+","+"bid:"+"1000568"+","+"net:"+"1"+","+"version:"+"1000700000"+","+"_net_:"+deviceInfo.get_net_();
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





    //https://api-mini.immomo.com/v1/feed/user/timeline?fr=
    public String OP_user_timeline(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_user_timeline + accountInfo.getAccount(), get_OP_user_timeline_Body(), get_OP_user_timeline_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_user_timeline_Header(String session) {
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

    private String get_OP_user_timeline_Body() {
        String jsonStr="ish265:1"+","+"h265_level:2"+","+"count:20"+","+"_iid:"+deviceInfo.get_iid()+","+"remoteid:"+accountInfo.getAccount()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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



    //////////
    public String OP_clientLog_upload(String session,String jsonStr){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_clientLog_upload + accountInfo.getAccount(), get_OP_clientLog_upload_Body(jsonStr), get_OP_clientLog_upload_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_clientLog_upload_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", "application/json;charset=utf-8");
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_OP_clientLog_upload_Body(String jsonStr) {
        String RequestBody=jsonStr;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/statfileupload?fr=
    public String OP_log_common_statfileupload_MYPROFILR_RETURN(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_log_common_statfileupload_MYPROFILR_RETURN + accountInfo.getAccount(), get_OP_log_common_statfileupload_MYPROFILR_RETURN_Body(), get_OP_log_common_statfileupload_MYPROFILR_RETURN_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_log_common_statfileupload_MYPROFILR_RETURN_Header(String session) {
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

    private String get_OP_log_common_statfileupload_MYPROFILR_RETURN_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();
        String file="{\"1608181339817.739\":\"UserFeedListActivity:b9bb6c42-7934-41ea-b603-5e18eeb012ce:exit\",\"1608181223040.339\":\"UserFeedListActivity:b9bb6c42-7934-41ea-b603-5e18eeb012ce:enter\",\"1608181223039.413\":\"UserFeedListActivity:9a4c8a5b-bab2-4ebd-b282-11063e11b8b9:enter\"}";
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
                "Content-Disposition: form-data; name=\"stat\"; filename=\"tmp_log_file_240af7b6-d2a4-4631-9723-69cc07a8b0a3\"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }



    //https://api-mini.immomo.com/v2/feed/user/getGuideSwitch?fr=668700100
    public String OP_user_getGuideSwitch(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_user_getGuideSwitch + accountInfo.getAccount(), get_OP_user_getGuideSwitch_Body(), get_OP_user_getGuideSwitch_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_user_getGuideSwitch_Header(String session) {
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

    private String get_OP_user_getGuideSwitch_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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


    //https://api-mini.immomo.com/v2/feed/user/getGuideConfig?fr=668700100
    public String OP_user_getGuideConfig(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_user_getGuideConfig + accountInfo.getAccount(), get_OP_user_getGuideConfig_Body(), get_OP_user_getGuideConfig_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_user_getGuideConfig_Header(String session) {
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

    private String get_OP_user_getGuideConfig_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"type:1"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr
    public String OP_log_common_statfileupload_sendDynamic_0(String session){
        String respone= HttpUtil.post(RequestUrl.OP_log_common_statfileupload_sendDynamic_0 + accountInfo.getAccount(), get_OP_log_common_statfileupload_sendDynamic_0_Body(), get_OP_log_common_statfileupload_sendDynamic_0_Header(session));
        return respone;
    }

    private Map<String, String> get_OP_log_common_statfileupload_sendDynamic_0_Header(String session) {
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

    private String get_OP_log_common_statfileupload_sendDynamic_0_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();


        String time_0=ParamUtil.getInstance().getmicTime();
        String time_1=ParamUtil.getInstance().calc_end_time(time_0,2000);

        String c_time_0=ParamUtil.getInstance().conversion_time(time_0);
        String c_time_1=ParamUtil.getInstance().conversion_time(time_1);

        String radom=UUID.randomUUID().toString();
        String c_time_0_context="NearbyPeopleFragment:"+radom+":enter";
        String c_time_1_context="NearbyPeopleFragment:"+radom+":exit";

        Map mapInfo=new HashMap();
        mapInfo.put(c_time_0,c_time_0_context);
        mapInfo.put(c_time_1,c_time_1_context);

        String file="\""+ParamUtil.getInstance().construct_log_parameter(mapInfo)+"\"";  //转成json字符串
        String file_name="tmp_log_file_"+UUID.randomUUID().toString();

        //filename="tmp_log_file_d45e1d54-7357-4a90-97d7-35415d128e3c"
        //String file="{\"1608183621661.407\":\"UserFeedListActivity:1b1c5038-8329-481a-a26a-b969764e41c7:exit\",\"1608183541642.512\":\"UserFeedListActivity:1b1c5038-8329-481a-a26a-b969764e41c7:enter\"}";
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


    //https://api-mini.immomo.com/v1/feed/site/auto_select?fr=
    public String OP_site_auto_select(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_site_auto_select + accountInfo.getAccount(), get_OP_site_auto_select_Body(), get_OP_site_auto_select_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_site_auto_select_Header(String session) {
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

    private String get_OP_site_auto_select_Body() {
        String jsonStr="lng:"+deviceInfo.getLng()+","+"_iid:"+deviceInfo.get_iid()+","+"is_cancel:0"+","+"lat:"+deviceInfo.getLat()+","+"loctype:1"+","+"sid:"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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

    //https://api-mini.immomo.com/v1/feed/publish/check?fr=
    public String OP_publish_check(String session,String strContext){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_publish_check + accountInfo.getAccount(), get_OP_publish_check_Body(strContext), get_OP_publish_check_Header(session));

        //System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //photocheck
    private Map<String,String> get_OP_publish_check_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);

        return headerMap;
    }

    private String get_OP_publish_check_Body(String strContent){

     /*   String jsonStr="share_to:"+""+","+"favor_type:"+"2"+","+"_iid:"+deviceInfo.get_iid()+","+"favor_id:"+""+","+"content:"+"\"[{\"text\":\""+strContent+"\",\"type\":\"1\"}]\""+","+"forward_origin_feedid:"+""+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();*/
        String jsonStr="share_to:"+""+","+"favor_type:"+"2"+","+"_iid:"+deviceInfo.get_iid()+","+"favor_id:"+""+","+"content:"+"[{\"text\":\""+strContent+"\",\"type\":\"1\"}]"+","+"forward_origin_feedid:"+""+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        //System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v3/feed/publish/send/index?fr=
    public String OP_publish_send_index(String session,String path,String strContext){

        byte[] respone= HttpUtil.postByte(RequestUrl.OP_publish_send_index + accountInfo.getAccount(), get_OP_publish_send_index_Body(path,strContext), get_OP_publish_send_index_Header(session));

        //System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_OP_publish_send_index_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    private byte[] get_OP_publish_send_index_Body(String path,String strContext){

        /*String jsonStr="last_type:"+"com.immomo.momo.feed.activity.UserFeedListActivity"+","+"is_from_digimon:"+"0"+","+"is_super:"+"0"+","+"content:"+"\"[{\"text\":\""+strContext+"\",\"type\":\"1\"}]\""+","+"loctype:"+"1"+","+
                "sid:"+"1f586c6bad0c9971"+","+"photo_extra:"+"\"[{\"take_photo\":{},\"edit_photo\":{}}]\""+","+"sync_sina:"+"0"+","+"parent_sid:"+""+","+"sync_qzone:"+"0"+","+
                "pics:"+"\"[{\"key\":\"photo_0\",\"optimized\":1,\"upload\":\"NO\"}]\""+","+"lat:"+deviceInfo.getLat()+","+"_uid_:"+deviceInfo.get_uid_()+","+"lng:"+deviceInfo.getLng()+","+
                "src:"+"5"+","+"share_mode:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"tags_postion:"+"\"{}\""+","+"tags:"+"\"[{\"from\":\"album_pic\"}]\""+","+"_net_:"+deviceInfo.get_net_()+","+
                "share_to:"+""+","+"sync_feed:"+"1"+","+"addFavor:"+"0"+","+"sync_weixin:"+"0"+","+"feed_type:"+"2";*/
        String jsonStr="last_type:"+"com.immomo.momo.feed.activity.UserFeedListActivity"+","+"is_from_digimon:"+"0"+","+"is_super:"+"0"+","+"content:"+"[{\"text\":\""+strContext+"\",\"type\":\"1\"}]"+","+"loctype:"+"1"+","+
                "sid:"+"1f586c6bad0c9971"+","+"photo_extra:"+"[{\"take_photo\":{},\"edit_photo\":{}}]"+","+"sync_sina:"+"0"+","+"parent_sid:"+""+","+"sync_qzone:"+"0"+","+
                "pics:"+"[{\"key\":\"photo_0\",\"optimized\":1,\"upload\":\"NO\"}]"+","+"lat:"+deviceInfo.getLat()+","+"_uid_:"+deviceInfo.get_uid_()+","+"lng:"+deviceInfo.getLng()+","+
                "src:"+"5"+","+"share_mode:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"tags_postion:"+"{}"+","+"tags:"+"[{\"from\":\"album_pic\"}]"+","+"_net_:"+deviceInfo.get_net_()+","+
                "share_to:"+""+","+"sync_feed:"+"1"+","+"addFavor:"+"0"+","+"sync_weixin:"+"0"+","+"feed_type:"+"2";
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        //System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        //mzip字符串参与拼接
        String mzip = StringUtil.getNoUrlDecodeMzip(bytesMzip);
        String mzipLen=String.valueOf(mzip.length());
        //图片文件二进制
        byte[] imgByte=StringUtil.getInstance().getImageByte(path);
        String imgStrLen=String.valueOf(imgByte.length);
        String BodyStr="-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"mzip\"\r\n" +
                "Content-Length: "+mzipLen+"\r\n\r\n"+
                mzip+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"photo_0\"; filename=\"avator.jpg\"\r\n"+
                "Content-Type: image/jpeg\r\n"+
                "Content-Length: "+imgStrLen+"\r\n\r\n";
        byte[]  BodyByte=BodyStr.getBytes();
        byte[]  endByte="\r\n-----------------------------7da2137580612--\r\n".getBytes();
        byte[]  RequestBodyByte=new byte[BodyByte.length+imgByte.length+endByte.length];
        System.arraycopy(BodyByte,0,RequestBodyByte,0,BodyByte.length);
        System.arraycopy(imgByte,0,RequestBodyByte,BodyByte.length,imgByte.length);
        System.arraycopy(endByte,0,RequestBodyByte,BodyByte.length+imgByte.length,endByte.length);
        this.Content_Length=String.valueOf(RequestBodyByte.length);
        return RequestBodyByte;
    }

    //https://api-mini.immomo.com/v1/feed/read/kill?fr=
    public String OP_feed_read_kill(String session,String duration,String feedid){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_feed_read_kill + accountInfo.getAccount(), get_OP_feed_read_kill_Body(duration,feedid), get_OP_feed_read_kill_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_feed_read_kill_Header(String session) {
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

    private String get_OP_feed_read_kill_Body(String duration,String feedid) {
        //"duration:1730484"+","+"feedid:au9481594895"
        String jsonStr="duration:"+duration+","+"feedid:"+feedid+","+"show_read:1"+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr
    public String OP_log_common_statfileupload_sendDynamic_1(String session){
        String respone= HttpUtil.post(RequestUrl.OP_log_common_statfileupload_sendDynamic_1 + accountInfo.getAccount(), get_OP_log_common_statfileupload_sendDynamic_1_Body(), get_OP_log_common_statfileupload_sendDynamic_1_Header(session));
        return respone;
    }

    private Map<String, String> get_OP_log_common_statfileupload_sendDynamic_1_Header(String session) {
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

    private String get_OP_log_common_statfileupload_sendDynamic_1_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();

        String time_0=ParamUtil.getInstance().getmicTime();
        String time_1=ParamUtil.getInstance().calc_end_time(time_0,2000);

        String c_time_0=ParamUtil.getInstance().conversion_time(time_0);
        String c_time_1=ParamUtil.getInstance().conversion_time(time_1);

        String radom=UUID.randomUUID().toString();
        String c_time_0_context="UserFeedListActivity:"+radom+":enter";
        String c_time_1_context="UserFeedListActivity:"+radom+":exit";

        Map mapInfo=new HashMap();
        mapInfo.put(c_time_0,c_time_0_context);
        mapInfo.put(c_time_1,c_time_1_context);

        String file="\""+ParamUtil.getInstance().construct_log_parameter(mapInfo)+"\"";  //转成json字符串
        String file_name="tmp_log_file_"+UUID.randomUUID().toString();

        //filename="tmp_log_file_29020ac5-281f-496b-af05-aa8458a7fd03\
        //String file="{\"1608184651498.469\":\"UserFeedListActivity:763adba5-662a-45c3-b43e-828fc9682344:exit\",\"1608184651553.760\":\"UserFeedListActivity:d5e065c2-cb0e-405c-9c64-8e5994a217a9:enter\",\"1608184650982.554\":\"UserFeedListActivity:763adba5-662a-45c3-b43e-828fc9682344:enter\"}";
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




    //https://api-log.immomo.com/v1/log/common/statfileupload?fr
    public String OP_log_common_statfileupload_sendDynamic_return(String session){
        String respone= HttpUtil.post(RequestUrl.OP_log_common_statfileupload_sendDynamic_return + accountInfo.getAccount(), get_OP_log_common_statfileupload_sendDynamic_return_Body(), get_OP_log_common_statfileupload_sendDynamic_return_Header(session));
        return respone;
    }

    private Map<String, String> get_OP_log_common_statfileupload_sendDynamic_return_Header(String session) {
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

    private String get_OP_log_common_statfileupload_sendDynamic_return_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();
        String file="{\"1608186075529.917\":\"UserFeedListActivity:d5e065c2-cb0e-405c-9c64-8e5994a217a9:exit\",\"1608184651779.840\":\"momopersonal:b0f775e2d5f9be757d9d6dae700fd04f20308:668700100:au9473076963:101:null:37.842398_112.430137:0:show\"}";
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
                "Content-Disposition: form-data; name=\"stat\"; filename=\"tmp_log_file_cf21440e-99b3-4395-9493-ec23e6ae6875\"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }



    //https://api-mini.immomo.com/v2/user/relation/activate?fr=
    public String OP_relation_activate(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_relation_activate + accountInfo.getAccount(), get_OP_relation_activate_Body(), get_OP_relation_activate_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_relation_activate_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-SIGN",this.X_SIGN);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
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

    private String get_OP_relation_activate_Body() {
        String jsonStr="last_time:0"+","+"_iid:"+deviceInfo.get_iid()+","+"version:7"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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

    //https://api-log.immomo.com/v1/log/common/statfileupload?fr
    public String OP_common_statfileupload_NEW_LOG(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_common_statfileupload_NEW_LOG + accountInfo.getAccount(), get_OP_common_statfileupload_NEW_LOG_Body(), get_OP_common_statfileupload_NEW_LOG_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_common_statfileupload_NEW_LOG_Header(String session) {
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

    private String get_OP_common_statfileupload_NEW_LOG_Body() {
        String _iid=deviceInfo.get_iid();
        String _net_=deviceInfo.get_net_();
        String _uid_=deviceInfo.get_uid_();
        String file="{\"1608198901976.935\":\"NearbyPeopleFragment:0a7257b8-ea2f-4ae5-8291-7815ff359566:enter\",\"1608198903984.804\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:479720881:829:null:null:0:recommend:-1:4:show\",\"1608198903988.563\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:808149991:1789:null:null:0:recommend:-1:5:show\",\"1608198903981.141\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:726774213:17825:null:null:0:recommend:-1:0:show\",\"1608198903984.124\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:558512853:41:null:null:0:recommend:-1:3:show_full\",\"1608198903982.656\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:829408613:473:null:null:0:recommend:-1:1:show\",\"1608198903982.227\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:738138222:4104:null:null:0:recommend:-1:2:show\",\"1608198903983.815\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:558512853:41:null:null:0:recommend:-1:3:show\",\"1608198903987.699\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:479720881:829:null:null:0:recommend:-1:4:show_full\",\"1608198903982.373\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:726774213:17825:null:null:0:recommend:-1:0:show_full\",\"1608198970839.291\":\"NearbyPeopleFragment:0a7257b8-ea2f-4ae5-8291-7815ff359566:exit\",\"1608198903983.149\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:738138222:4104:null:null:0:recommend:-1:2:show_full\",\"1608198903982.190\":\"d8636872214d2ee507dc863a61f69a7351020:np:momoid:829408613:473:null:null:0:recommend:-1:1:show_full\"}";
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
                "Content-Disposition: form-data; name=\"stat\"; filename=\"tmp_log_file_867b8b13-ce67-4949-bbc2-76abcb016b42\"\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: "+file.length()+"\r\n\r\n"+file+"\r\n"+
                "-----------------------------7da2137580612--";
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=
    public String OP_together_checkreddot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_together_checkreddot + accountInfo.getAccount(), get_OP_together_checkreddot_Body(), get_OP_together_checkreddot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_together_checkreddot_Header(String session) {
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

    private String get_OP_together_checkreddot_Body() {
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

    //https://api-mini.immomo.com/v2/fast/feed/nearby/lists?fr=668700100
    public String OP_nearby_lists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_nearby_lists + accountInfo.getAccount(), get_OP_nearby_lists_Body(), get_OP_nearby_lists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_nearby_lists_Header(String session) {
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

    private String get_OP_nearby_lists_Body() {
        String jsonStr="acc:38.0"+","+"gapps:1"+","+"ish265:1"+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+","+"save:YES"+","+"screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+
                "imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+","+"loctype:1"+","+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+"osversion_int:"+deviceInfo.getOsversion_int()+
                "rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"h265_level:2"+","+"market_source:"+deviceInfo.getMarket_source()+","+"model:"+deviceInfo.getModel()+","+"net:1"+","+"lat:"+deviceInfo.getLat()+","+
                "oaid:"+","+"androidId:"+deviceInfo.getAndroidId()+","+"_uid_:"+deviceInfo.get_uid_()+","+"ad_channel_id:14"+","+"phone_type:GSM"+","+"lng:"+deviceInfo.getLng()+","+"refreshmode:auto"+","+"phone_netWork:0"+","+
                "dpp:"+deviceInfo.getDpp()+","+"count:20"+","+"index:0"+","+"_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+
                "router_mac:"+deviceInfo.getRouter_mac()+","+"firstRefresh:0"+","+"network_class:"+deviceInfo.getNetwork_class()+","+"_uidType:"+deviceInfo.get_uidType()+","+"channel_source:1"+","+"imei:"+deviceInfo.getImei();
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

    //https://api-mini.immomo.com/api/safe/phone/sendNoBindPhoneCard?fr=
    public String OP_phone_sendNoBindPhoneCard(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_phone_sendNoBindPhoneCard + accountInfo.getAccount(), get_OP_phone_sendNoBindPhoneCard_Body(), get_OP_phone_sendNoBindPhoneCard_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_phone_sendNoBindPhoneCard_Header(String session) {
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

    private String get_OP_phone_sendNoBindPhoneCard_Body() {
        String jsonStr="uid:"+deviceInfo.getUid()+","+"_iid:"+deviceInfo.get_iid()+","+"bindSource:bind_source_nearby_user"+","+"loginfrq:0"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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

    //修改密码
    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String OP_log_common_settingGoto(String session){
        String response= HttpUtil.post(RequestUrl.OP_log_common_settingGoto + deviceInfo.getUid(), get_OP_log_common_settingGoto_Body(), get_OP_log_common_settingGoto_Header(session));
        return response;

    }

    private Map<String,String> get_OP_log_common_settingGoto_Header(String session){
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

    private String get_OP_log_common_settingGoto_Body(){
        String RequestBody= null;
        RequestBody="&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //点击设置后发送"https://api-mini.immomo.com/api/safe/setting/index?fr=";
    public String OP_setting_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_setting_index + accountInfo.getAccount(), get_OP_setting_index_Body(), get_OP_setting_index_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Map<String,String> get_OP_setting_index_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);

        return headerMap;
    }

    private String get_OP_setting_index_Body(){

        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //点击账号时候会发"https://api-mini.immomo.com/v1/wallet/payment/findAlipayStatus?fr="
    public String OP_payment_findAlipayStatus(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_payment_findAlipayStatus + accountInfo.getAccount(), get_OP_payment_findAlipayStatus_Body(), get_OP_payment_findAlipayStatus_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_OP_payment_findAlipayStatus_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    private String get_OP_payment_findAlipayStatus_Body(){
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //点击账号安全end"https://api-mini.immomo.com/v2/core/password/getPwdCheck?fr="
    public String OP_password_getPwdCheck(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_password_getPwdCheck + accountInfo.getAccount(), get_OP_password_getPwdCheck_Body(), get_OP_password_getPwdCheck_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_OP_password_getPwdCheck_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    private String get_OP_password_getPwdCheck_Body(){
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);
        System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    ///修改密码start"https://api-mini.immomo.com/v2/core/password/setUserPwd?fr="
    public String OP_password_setUserPwd(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_password_setUserPwd + accountInfo.getAccount(), get_OP_password_setUserPwd_Body(), get_OP_password_setUserPwd_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_password_setUserPwd_Header(String session) {
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

    private String get_OP_password_setUserPwd_Body() {
        String jsonStr="password:"+accountInfo.getPassword()+","+"_iid:"+deviceInfo.get_iid()+","+"type:"+"phone"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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
    public String OP_log_common_androidonlinetime(String session){
        String response= HttpUtil.post(RequestUrl.OP_log_common_androidonlinetime + deviceInfo.getUid(), get_OP_log_common_androidonlinetime_Body(), get_OP_log_common_androidonlinetime_Header(session));
        return response;
    }

    private Map<String,String> get_OP_log_common_androidonlinetime_Header(String session){
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

    private String get_OP_log_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=downline"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/api/setting/momologout?fr="
    public String OP_setting_momologout(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_setting_momologout + accountInfo.getAccount(), get_OP_setting_momologout_Body(), get_OP_setting_momologout_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_setting_momologout_Header(String session) {
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

    private String get_OP_setting_momologout_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
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


    //"https://paas-push-api.immomo.com/push/index/unalias?appsr="
    public String OP_index_unalias(String alias, String token){
        String response= HttpUtil.post(RequestUrl.OP_index_unalias + ParamUtil.getInstance().getAppsr(), get_OP_index_unalias_Body(alias,token), get_OP_index_unalias_Header());
        return response;
    }

    private Map<String,String> get_OP_index_unalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_OP_index_unalias_Body(String alias, String token){
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

    //https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr=
    public String OP_photon_getPushHashKey(String session,String alias){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_photon_getPushHashKey + accountInfo.getAccount(), get_OP_photon_getPushHashKey_Body(alias), get_OP_photon_getPushHashKey_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Map<String, String> get_OP_photon_getPushHashKey_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
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
    private String get_OP_photon_getPushHashKey_Body(String alias) {
        //alias=1003856453578&_iid=9fca9e36b55b6c4a6f70138f15dfc68a&_net_=wifi&_uid_=6766272a7e000278b21192236b3c3eb1
        String RequestBody="alias="+alias+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //"https://paas-push-api.immomo.com/push/index/regwithalias?appsr="
    public String OP_index_regwithalias(String alias,String sn){
        String response= HttpUtil.post(RequestUrl.OP_index_regwithalias + ParamUtil.getInstance().getAppsr(), get_OP_index_regwithalias_Body(alias,sn), get_OP_index_regwithalias_Header());
        return response;
    }

    private Map<String,String> get_OP_index_regwithalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_OP_index_regwithalias_Body(String alias,String sn){
        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getMzip(alias,sn,true);    //账号密码登陆的时候flag的标志是true
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


    //"https://api-mini.immomo.com/v2/setting/abtest/index?fr="
    public String OP_abtest_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_abtest_index + accountInfo.getAccount(), get_OP_abtest_index_Body(), get_OP_abtest_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_abtest_index_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
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

    private String get_OP_abtest_index_Body() {
        String guest_versions="{}";
        try {
            guest_versions= URLEncoder.encode(guest_versions,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody="lng="+deviceInfo.getLng()+"&guest_versions="+guest_versions+"&_iid="+deviceInfo.get_iid()+"&login_versions="+"&lat="+deviceInfo.getLat()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String OP_log_common_abtestupload_0(String session){
        String response= HttpUtil.post(RequestUrl.OP_log_common_abtestupload_0 + accountInfo.getAccount(), get_OP_log_common_abtestupload_0_Body(), get_OP_log_common_abtestupload_0_Header(session));
        return response;
    }

    private Map<String,String> get_OP_log_common_abtestupload_0_Header(String session){
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

    private String get_OP_log_common_abtestupload_0_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_show_phone_login_page:account_back\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //---------https://api-mini.immomo.com/v1/welcome/logs?fr=831737702
    public String OP_welcome_logs(String session){
        String response= HttpUtil.post(RequestUrl.OP_welcome_logs + accountInfo.getAccount(), get_OP_welcome_logs_Body(), get_OP_welcome_logs_Header(session));
        return response;
    }

    private Map<String,String> get_OP_welcome_logs_Header(String session){
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

    private String get_OP_welcome_logs_Body(){
        String RequestBody= null;
        String cpuStr=deviceInfo.getCpuString();
        try {
            cpuStr=URLEncoder.encode(cpuStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="mmuid="+"&cpuString="+cpuStr+"&_uidType="+deviceInfo.get_uidType()+"&idfa="+deviceInfo.getIdfa()+
                "&imei="+deviceInfo.getImei()+"&_iid="+deviceInfo.get_iid()+"&new_installation="+"0"+"&oaid="+"&androidId="+deviceInfo.getAndroidId()+"&_net_="+deviceInfo.get_net_()+
                "&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/account/token/transfer?fr=831737702
    public String OP_token_transfer(String session){
        String response= HttpUtil.post(RequestUrl.OP_token_transfer + accountInfo.getAccount(), get_OP_token_transfer_Body(), get_OP_token_transfer_Header(session));
        return response;
    }

    private Map<String,String> get_OP_token_transfer_Header(String session){
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

    private String get_OP_token_transfer_Body(){
        String RequestBody= "_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/mk/version/getupdatelist?fr=668700100
    public String OP_version_getupdatelist(String session){
        String response= HttpUtil.post(RequestUrl.OP_version_getupdatelist + accountInfo.getAccount(), get_OP_version_getupdatelist_Body(), get_OP_version_getupdatelist_Header(session));
        return response;
    }

    private Map<String,String> get_OP_version_getupdatelist_Header(String session){
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        return headerMap;
    }

    private String get_OP_version_getupdatelist_Body(){
        String RequestBody= null;
        String bids="{\"1028\":{\"version\":56,\"visited\":0,\"multi\":\"\"},\"1000101\":{\"version\":1001800000,\"visited\":0,\"multi\":\"\"},\"1029\":{\"version\":103,\"visited\":0,\"multi\":\"\"},\"1000597\":{\"version\":1000300000,\"visited\":0,\"multi\":\"\"},\"1030\":{\"version\":68,\"visited\":0,\"multi\":\"\"},\"1038\":{\"version\":10,\"visited\":0,\"multi\":\"\"},\"1093\":{\"version\":89,\"visited\":0,\"multi\":\"\"},\"1279\":{\"version\":17,\"visited\":0,\"multi\":\"\"},\"1000568\":{\"version\":1000700000,\"visited\":0,\"multi\":\"\"},\"1000257\":{\"version\":1001100000,\"visited\":0,\"multi\":\"\"},\"1000582\":{\"version\":1000700000,\"visited\":0,\"multi\":\"\"}}";
        try {
            bids=URLEncoder.encode(bids,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="_ab_test_="+"&bids="+bids+"&net=1"+"&_net_="+deviceInfo.get_net_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v1/mk/version/checkupdate?fr=831737702
    public String OP_version_checkupdate_no_zip(String session){
        String response= HttpUtil.post(RequestUrl.OP_version_checkupdate_no_zip + deviceInfo.getUid(), get_OP_version_checkupdate_no_zip_Body(), get_OP_version_checkupdate_no_zip_Header(session));
        return response;
    }

    private Map<String,String> get_OP_version_checkupdate_no_zip_Header(String session){
        this.cookie=session;
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

    private String get_OP_version_checkupdate_no_zip_Body(){
        String RequestBody= null;
        String referer="https://s.immomo.com/fep/momo/m-fes-sdk/adr-mk-jssdk/index.js";
        try {
            referer=URLEncoder.encode(referer,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="referer="+referer+"&_ab_test_="+"&bid=1000597"+"&net=1"+"&version=1000300000"+"&_net_="+deviceInfo.get_net_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-mini.immomo.com/guest/nearby/index?fr="
    public String OP_nearby_index(String session){
        String response= HttpUtil.post(RequestUrl.OP_nearby_index + deviceInfo.getUid(), get_OP_nearby_index_Body(), get_OP_nearby_index_Header(session));
        return response;
    }

    private Map<String,String> get_OP_nearby_index_Header(String session){
        this.cookie=null;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
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

    //43004577
    private String get_OP_nearby_index_Body(){
        String RequestBody= null;
        String native_ua=deviceInfo.getNative_ua();
        try {
            native_ua=URLEncoder.encode(native_ua,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="acc=30.0"+"&gapps="+deviceInfo.getGapps()+"&mmuid="+"&realauth=0"+"&screen="+deviceInfo.getScreen()+"&device_type="+deviceInfo.getDevice_type()+"&osversion_int="+deviceInfo.getOsversion_int()+
                "&ddian_active_time="+System.currentTimeMillis()+"&constellation=0"+"&model="+deviceInfo.getModel()+"&net="+deviceInfo.getNet()+"&cell_id=0"+"&lat="+deviceInfo.getLat()+"&androidId="+deviceInfo.getAndroidId()+
                "&age_max=100"+"&_uid_="+deviceInfo.get_uid_()+"&phone_type="+deviceInfo.getPhone_type()+"&lng="+deviceInfo.getLng()+"&refreshmode=auto"+"&count=24"+"&index=0"+"&age_min=18"+"&_iid="+deviceInfo.get_iid()+
                "&version=100071"+"&apksign="+deviceInfo.getApksign()+"&_net_="+deviceInfo.get_net_()+"router_mac:"+deviceInfo.getRouter_mac()+"&activetime="+"4320"+"&network_class="+deviceInfo.getNetwork_class()+
                "&vip_filter=0"+"&buildnumber="+deviceInfo.getBuildnumber()+"&save="+"YES"+"&imsi=unknown"+"&moment_sex="+"&emu="+deviceInfo.getEmu()+"&mac="+deviceInfo.getMac()+"&manufacturer="+deviceInfo.getManufacturer()+
                "&rom="+deviceInfo.getRom()+"&uid="+deviceInfo.getUid()+"&total=0"+"&native_ua="+native_ua+"&market_source="+deviceInfo.getMarket_source()+"&oaid="+deviceInfo.getOaid()+"&social=0"+"&phone_netWork=6"+
                "&dpp="+deviceInfo.getDpp()+"&sex="+"&_uidType="+deviceInfo.get_uidType()+"&imei="+deviceInfo.getImei();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String OP_log_common_abtestupload_1(String session){
        String response= HttpUtil.post(RequestUrl.OP_log_common_abtestupload_1 + accountInfo.getAccount(), get_OP_log_common_abtestupload_1_Body(), get_OP_log_common_abtestupload_1_Header(session));
        return response;
    }

    private Map<String,String> get_OP_log_common_abtestupload_1_Header(String session){
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

    private String get_OP_log_common_abtestupload_1_Body(){
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


    //"https://api-alpha.immomo.com/momopush/fasttoken/reg?fr="
    public String OP_fasttoken_reg(String session, String token){
        String response= HttpUtil.post(RequestUrl.OP_fasttoken_reg +accountInfo.getAccount(), get_OP_fasttoken_reg_Body(token), get_OP_fasttoken_reg_Header(session));
        return response;
    }

    private Map<String,String> get_OP_fasttoken_reg_Header(String session){
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

    private String get_OP_fasttoken_reg_Body(String token){
        String RequestBody= null;
        RequestBody="_iid="+deviceInfo.get_iid()+"&token="+token+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-log.immomo.com/v1/log/common/androidonlinetime?fr="
    public String OP_common_androidonlinetime(String session){
        String response= HttpUtil.post(RequestUrl.OP_common_androidonlinetime + deviceInfo.getUid(), get_OP_common_androidonlinetime_Body(), get_OP_common_androidonlinetime_Header(session));
        return response;
    }

    private Map<String,String> get_OP_common_androidonlinetime_Header(String session){
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

    private String get_OP_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-mini.immomo.com/v1/appconfig/index?fr=831737702
    public String OP_appconfig_index(String session){
        String response= HttpUtil.post(RequestUrl.OP_appconfig_index + deviceInfo.getUid(), get_OP_appconfig_index_Body(), get_OP_appconfig_index_Header(session));
        return response;
    }

    private Map<String,String> get_OP_appconfig_index_Header(String session){
        this.cookie=session;
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

    private String get_OP_appconfig_index_Body(){
        String RequestBody= null;
        String marks="524288,524289,524290,105,108,103,16384,200,102,1048576,300,330,12,202,339,9528,203,2097152,10000,205,120,122,403,712,405,211,1203,207,213,214,215,713,407,801,223,1050,1080,50308,3000001,303,227,228,229,804,230,55000,305,805,806,307,716,719,232,414,718,1051,341,2000,415,309";
        //String curResource="[{\"name\":\"record_effects_video\",\"version\":0},{\"name\":\"photo_spam\",\"version\":0},{\"name\":\"qrcode_detect\",\"version\":0},{\"name\":\"aliyun_auth\",\"version\":0},{\"name\":\"mmcv_android_facedetect_model\",\"version\":0},{\"name\":\"mmcv_android_fa_model\",\"version\":0},{\"name\":\"mm_emoji\",\"version\":0},{\"name\":\"mmbg_video\",\"version\":0},{\"name\":\"mmcv_android_facequality_model\",\"version\":0},{\"name\":\"justice_model\",\"version\":0},{\"name\":\"scan_media\",\"version\":0}]";
        //String curResource="[{\"name\":\"record_effects_video\",\"version\":100065,\"guid\":\"D9E0F4E9-0377-49DB-AEED-27D8CD6EFFEA20200805\"},{\"name\":\"photo_spam\",\"version\":100000,\"guid\":\"68E9D8AF-756D-45B1-EF06-DBABB8D8B22A20200103\"},{\"name\":\"qrcode_detect\",\"version\":0},{\"name\":\"aliyun_auth\",\"version\":100000,\"guid\":\"9010447B-F84E-41F6-A235-1DBE71000DA920191227\"},{\"name\":\"mmcv_android_facedetect_model\",\"version\":100000,\"guid\":\"0F281901-0A38-5E26-E1A3-3A1AD110812C20191209\"},{\"name\":\"mmcv_android_fa_model\",\"version\":100000,\"guid\":\"54C75272-C216-628D-3D6F-7C77812A757620191210\"},{\"name\":\"mm_emoji\",\"version\":100000,\"guid\":\"4CEA96ED-7839-2750-06ED-18B90584A91E20191206\"},{\"name\":\"mmbg_video\",\"version\":100000,\"guid\":\"C5FEA850-81B0-BB22-E6CA-E94BD0EA396520191206\"},{\"name\":\"mmcv_android_facequality_model\",\"version\":0},{\"name\":\"justice_model\",\"version\":100000,\"guid\":\"699464A6-E6F4-7A17-7FF9-AA0FB29C322B20200103\"},{\"name\":\"scan_media\",\"version\":100000,\"guid\":\"645CF2A3-AC4C-E388-34EE-9ECCCAFB6DFF20191214\"}]";

        String curResource=ParamUtil.getInstance().getCurResource(accountInfo.getCurResource(),true);
        try {
            marks=URLEncoder.encode(marks,"UTF-8");
            curResource=URLEncoder.encode(curResource,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="temp_uid="+deviceInfo.get_uid_()+"momoid="+accountInfo.getAccount()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+"&marks="+marks+"&curResource="+curResource+
                "&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=831737702

    public String OP_appconfigmulti_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.OP_appconfigmulti_index + accountInfo.getAccount(), get_OP_appconfigmulti_index_Body(), get_OP_appconfigmulti_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_OP_appconfigmulti_index_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
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

    private String get_OP_appconfigmulti_index_Body() {
        String marks="18,20,21,26,37,30,36,39,38,88,90,113,22,92,103,111,155,136,114,117,23,121,124,128,137,131,134,140,142,138,144,146,147,151,152,156,158,160,165,167,170,169,175,171,173,178,183,172,186,191,180,194,197,195,196,199,198,200,201,181,105,101,188,1,2,6";
        try {
            marks=URLEncoder.encode(marks,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBody="temp_uid="+deviceInfo.getUid()+"&momoid="+accountInfo.getAccount()+"&client="+deviceInfo.getDevice_type()+"&_iid="+deviceInfo.get_iid()+"&marks="+marks+"&_net_="+deviceInfo.get_net_()+
                "&_uid_="+deviceInfo.get_uid_();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/permissionupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_log_common_permissionupload_0(String session){
        String response= HttpUtil.post(RequestUrl.REG_log_common_permissionupload_0 + deviceInfo.getUid(), get_REG_log_common_permissionupload_0_Body(), get_REG_log_common_permissionupload_0_Header(session));
        return response;
    }

    private Map<String,String> get_REG_log_common_permissionupload_0_Header(String session){
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

    private String get_REG_log_common_permissionupload_0_Body(){
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
    public String REG_log_common_permissionupload_1(String session){
        String response= HttpUtil.post(RequestUrl.REG_log_common_permissionupload_1 + deviceInfo.getUid(), get_REG_log_common_permissionupload_1_Body(), get_REG_log_common_permissionupload_1_Header(session));
        return response;
    }

    private Map<String,String> get_REG_log_common_permissionupload_1_Header(String session){
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

    private String get_REG_log_common_permissionupload_1_Body(){
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




    //REG
    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_0(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_0 + deviceInfo.getUid(), get_REG_common_abtestupload_0_Body(), get_REG_common_abtestupload_0_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_0_Header(String session){
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

    private String get_REG_common_abtestupload_0_Body(){
        String RequestBody= null;
        String data="{\"log\":\"guest_button_validate:first_enter\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //发送验证码"https://api-mini.immomo.com/api/safe/verifycode/send?fu="
    public String REG_verifycode_send(String session, String countryCode, String phoneNumber){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_verifycode_send + deviceInfo.getUid(), get_REG_verifycode_send_Body(countryCode,phoneNumber), get_REG_verifycode_send_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_REG_verifycode_send_Header(String session){
        this.cookie=session;
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV","f14dd39f");
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    private String get_REG_verifycode_send_Body(String countryCode, String phoneNumber){
        //countryCode=%2B86&phonenumber=17067600818&_iid=5536e5b165387d8d4d634ae01e430186&voiceSms=0&_net_=wifi&_uid_=6766272a7e000278b21192236b3c3eb1
        String countryCodeEncode=null;
        try {
            countryCodeEncode= URLEncoder.encode(countryCode,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String RequestBodyByte="countryCode="+countryCodeEncode+"&phonenumber="+phoneNumber+"&_iid="+deviceInfo.get_iid()+"&voiceSms=0"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();

        System.out.println(RequestBodyByte);

        this.Content_Length=String.valueOf(RequestBodyByte.length());
        return RequestBodyByte;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_1(String session,String phoneNum){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_1 + deviceInfo.getUid(), get_REG_common_abtestupload_1_Body(phoneNum), get_REG_common_abtestupload_1_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_1_Header(String session){
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

    private String get_REG_common_abtestupload_1_Body(String phoneNum){
        String RequestBody= null;
        String log_data="\""+"log_reglogin_click_phone_login_next:+"+phoneNum+"\"";
        String data="{\"log\":"+log_data+",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //验证码登陆"https://api-mini.immomo.com/v2/core/login/index?fu="
    public String REG_login_index(String session, String smscode, String phoneNum){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_login_index + deviceInfo.getUid(), get_REG_login_index_Body(smscode,phoneNum), get_REG_login_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_login_index_Header(String session) {
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

    //{"gapps":"1","acc":"0","Serialno":"","mmuid":"","isRoot":"0","screen":"1080x1794","device_type":"android","hw":"d5cec4069e08444eccdca0b1691be31b","osversion_int":"27","RAMSize":"3852816","password":"","current_wifi":"02:00:00:00:00:00","model":"Pixel","androidId":"912cd84c01034e24","lat":"0","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","lng":"0","CpuInfo":"0-3","MacInfo":"02:00:00:00:00:00","utdid":"00000000","_iid":"9dc14fa42e557b59494fed1e110ded70","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"02:00:00:00:00:00","KernelVersion":"","network_class":"wifi","SerialNumber":"FA68W0308543","smscode":"126227","sensorNames":"G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0","buildnumber":"OPM4.171019.021.P1\/4820305","BootSerialno":"","imsi":"unknown","emu":"029f181d6e7ba188885c78462623c37a","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","BaseBandVersion":"8996-130091-1802061512","market_source":"14","etype":"2","oaid":"","phone_netWork":"0","dpp":"391ccee4a6d5b194e06407120723de70","bindSource":"bind_source_login","_uidType":"androidid","imei":"352531081145847","account":"+639682895636"}
    private String get_REG_login_index_Body(String smscode, String phoneNum) {
        String jsonStr="gapps:"+deviceInfo.getGapps()+","+"acc:"+deviceInfo.getAcc()+","+"Serialno:"+","+"mmuid:"+deviceInfo.getMmuid()+","+"isRoot:"+deviceInfo.getIsRoot()+","+
                "screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+","+"hw:"+deviceInfo.getHw()+","+"osversion_int:"+deviceInfo.getOsversion_int()+","+"RAMSize:"+deviceInfo.getRAMSize()+","+
                "password:"+""+","+"current_wifi:"+"02:00:00:00:00:00"+","+"model:"+deviceInfo.getModel()+","+"androidId:"+deviceInfo.getAndroidId()+","+"lat:0"+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"lng:0"+","+"CpuInfo:"+deviceInfo.getCpuInfo()+","+"MacInfo:"+deviceInfo.getMacInfo()+","+"utdid:"+deviceInfo.getUtdid()+","+
                "_iid:"+deviceInfo.get_iid()+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+"02:00:00:00:00:00"+","+
                "KernelVersion:"+deviceInfo.getKernelVersion()+","+"network_class:"+deviceInfo.getNetwork_class()+","+"SerialNumber:"+deviceInfo.getSerialNumber()+","+"smscode:"+smscode+","+"sensorNames:"+deviceInfo.getSensorNames()+","+
                "buildnumber:"+deviceInfo.getBuildnumber()+","+"BootSerialno:"+""+","+"imsi:unknown"+","+"emu:"+deviceInfo.getEmu()+","+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"BaseBandVersion:"+deviceInfo.getBaseBandVersion()+","+"market_source:"+deviceInfo.getMarket_source()+","+"etype:"+deviceInfo.getEtype()+","+
                "oaid:"+deviceInfo.getOaid()+","+"phone_netWork:"+deviceInfo.getPhone_netWork()+","+"dpp:"+deviceInfo.getDpp()+","+"bindSource:bind_source_login"+","+"_uidType:"+deviceInfo.get_uidType()+","+
                "imei:"+deviceInfo.getImei()+","+"account:"+phoneNum;
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        try {
            this.ck= ParamUtil.getInstance().getCk(publicKey);
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

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_2(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_2 + deviceInfo.getUid(), get_REG_common_abtestupload_2_Body(), get_REG_common_abtestupload_2_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_2_Header(String session){
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

    private String get_REG_common_abtestupload_2_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_input_smscode_success\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


   //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_3(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_3 + deviceInfo.getUid(), get_REG_common_abtestupload_3_Body(), get_REG_common_abtestupload_3_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_3_Header(String session){
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

    private String get_REG_common_abtestupload_3_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_show_register_profile:register_phone\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_4(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_4 + deviceInfo.getUid(), get_REG_common_abtestupload_4_Body(), get_REG_common_abtestupload_4_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_4_Header(String session){
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

    private String get_REG_common_abtestupload_4_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_register_profile_finished:register_phone\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //填写用户名等信息"https://api-mini.immomo.com/v2/core/register/checkSimple?fu="
    public String REG_register_checkSimple(String session, String age, String gender, String name){
        /*byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_register_checkSimple + deviceInfo.getUid(), get_REG_register_checkSimple_Body(age,gender,name), get_REG_register_checkSimple_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        String respone= HttpUtil.post(RequestUrl.REG_register_checkSimple + deviceInfo.getUid(), get_REG_register_checkSimple_Body(age,gender,name), get_REG_register_checkSimple_Header(session));

        return respone;
    }

    private Map<String,String> get_REG_register_checkSimple_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",this.X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    private String get_REG_register_checkSimple_Body(String age, String gender, String name){

        //birthday=1990-05-14&gender=M&name=this&_iid=5536e5b165387d8d4d634ae01e430186&_net_=wifi&_uid_=6766272a7e000278b21192236b3c3eb1
        String RequestBody="birthday="+age+"&gender="+gender+"&name="+name+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();

        System.out.println(RequestBody);

        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_5(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_5 + deviceInfo.getUid(), get_REG_common_abtestupload_5_Body(), get_REG_common_abtestupload_5_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_5_Header(String session){
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

    private String get_REG_common_abtestupload_5_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_click_upload_avatar:register_phone\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //上传头像"https://api-mini.immomo.com/v2/core/register/index?fu="
    //头像路径,注册信息
    public String REG_register_index(String session, String path,Map<String,String>info){

        byte[] respone= HttpUtil.postByte(RequestUrl.REG_register_index + deviceInfo.getUid(), get_REG_register_index_Body(path,info), get_REG_register_index_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_REG_register_index_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    //上传头像时候 birthday=1990-05-14 gender=M countrycode=+86 phonenumber=17067600818 registerToken=A6FE95BA-D55E-6E44-BBD1-22C0F655D447  name=xx
    private byte[] get_REG_register_index_Body(String path,Map<String,String>info){
       String birthday=info.get("birthday");
       String gender=info.get("gender");
       String countrycode=info.get("countrycode");
       String phonenumber=info.get("phonenumber");
       String registerToken=info.get("registerToken");
       String name=info.get("name");
        String jsonStr="birthday:"+birthday+","+"gapps:"+deviceInfo.getGapps()+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"mmuid:"+deviceInfo.getMmuid()+","+"gender:"+gender+","+
                "countrycode:"+countrycode+","+"phonenumber:"+phonenumber+","+"screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+","+"imsi:"+"unknown"+","+
                "emu:"+deviceInfo.getEmu()+","+"mac:"+deviceInfo.getMac()+","+"hw:"+deviceInfo.getHw()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+"temp_uid:"+deviceInfo.getUid()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"password:"+""+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "model:"+deviceInfo.getModel()+","+"registerToken:"+registerToken+","+"oaid:"+deviceInfo.getOaid()+","+"androidId:"+deviceInfo.getAndroidId()+","+
                "_uid_:"+deviceInfo.get_uid_()+","+"phone_type:"+deviceInfo.getPhone_type()+","+"phone_netWork:"+deviceInfo.getPhone_netWork()+","+"dpp:"+deviceInfo.getDpp()+","+
                "_iid:"+deviceInfo.get_iid()+","+"bindSource:bind_source_register"+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+
                "router_mac:"+"02:00:00:00:00:00"+","+"network_class:"+deviceInfo.getNetwork_class()+","+"_uidType:"+deviceInfo.get_uidType()+","+"name:"+name+","+"imei:"+deviceInfo.getImei()+","+
                "confirm_reregister:"+"0";
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
        String x_kv_len=String.valueOf(this.X_KV.length());
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        //mzip字符串参与拼接
        String mzip = StringUtil.getNoUrlDecodeMzip(bytesMzip);
        String mzipLen=String.valueOf(mzip.length());
        //图片文件二进制
        byte[] imgByte=StringUtil.getInstance().getImageByte(path);
        String imgStrLen=String.valueOf(imgByte.length);
        String map_id=ParamUtil.getInstance().getMapId();
        String map_id_len=String.valueOf(map_id.length());
        String ck=null;
        try {
            ck=ParamUtil.getInstance().getCkNoURLEncoder(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ck_len=String.valueOf(ck.length());
        String BodyStr="-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"map_id\"\r\n" +
                "Content-Length: "+map_id_len+"\r\n\r\n"+
                map_id+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"code_version\"\r\n"+
                "Content-Length: 1\r\n\r\n"+
                "2"+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"mzip\"\r\n"+
                "Content-Length: "+mzipLen+"\r\n\r\n"+
                mzip+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"ck\"\r\n"+
                "Content-Length: "+ck_len+"\r\n\r\n"+
                ck+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"X-KV\"\r\n"+
                "Content-Length: "+x_kv_len+"\r\n\r\n"+
                this.X_KV+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"avatarimg\"; filename=\"VMgNPFIC.jpg_\"\r\n"+
                "Content-Type: application/octet-stream"+"\r\n"+
                "Content-Length: "+imgStrLen+"\r\n\r\n";
        byte[]  BodyByte=BodyStr.getBytes();
        byte[]  endByte="\r\n-----------------------------7da2137580612--".getBytes();
        byte[]  RequestBodyByte=new byte[BodyByte.length+imgByte.length+endByte.length];
        System.arraycopy(BodyByte,0,RequestBodyByte,0,BodyByte.length);
        System.arraycopy(imgByte,0,RequestBodyByte,BodyByte.length,imgByte.length);
        System.arraycopy(endByte,0,RequestBodyByte,BodyByte.length+imgByte.length,endByte.length);
        this.Content_Length=String.valueOf(RequestBodyByte.length);
        return RequestBodyByte;
    }

    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_6(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_6 + accountInfo.getAccount(), get_REG_common_abtestupload_6_Body(), get_REG_common_abtestupload_6_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_6_Header(String session){
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

    private String get_REG_common_abtestupload_6_Body(){
        String RequestBody= null;
        String data="{\"log\":\"log_reglogin_register_success:register_phone:first_enter\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/user/my/base?fr=831737702
    public String REG_my_base_no_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_my_base_no_zip + accountInfo.getAccount(), get_REG_my_base_no_zip_Body(), get_REG_my_base_no_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_my_base_no_zip_Header(String session) {
        this.cookie=session;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> headerMap = new LinkedHashMap<>();
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

    private String get_REG_my_base_no_zip_Body() {
        String RequestBody="_iid="+deviceInfo.get_iid()+"&source="+"register"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //https://api-log.immomo.com/v1/log/common/abtestupload?fu=6766272a7e000278b21192236b3c3eb1
    public String REG_common_abtestupload_7(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_abtestupload_7 + accountInfo.getAccount(), get_REG_common_abtestupload_7_Body(), get_REG_common_abtestupload_7_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_abtestupload_7_Header(String session){
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

    private String get_REG_common_abtestupload_7_Body(){
        String RequestBody= null;
        String data="{\"log\":\"shield_contact:0\",\"startTime\":0,\"endTime\":0}";
        try {
            data=URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody="data="+data+"&_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }



    //https://api-mini.immomo.com/api/personal/avatar?fr=831737702
    public String REG_personal_avatar(String session, String path,String guid){
        byte[] respone= HttpUtil.postByte(RequestUrl.REG_personal_avatar + accountInfo.getAccount(), get_REG_personal_avatar_Body(path,guid), get_REG_personal_avatar_Header(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String,String> get_REG_personal_avatar_Header(String session){
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",X_SIGN);
        headerMap.put("X-LV", headerInfo.getX_LV());
        headerMap.put("X-KV",X_KV);
        headerMap.put("Connection", headerInfo.getConnection());
        headerMap.put("Charset", headerInfo.getCharset());
        headerMap.put("X-Span-Id", headerInfo.getX_Span_Id());
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("cookie","SESSIONID="+ this.cookie);
        headerMap.put("Accept-Language", headerInfo.getAccept_Language());
        headerMap.put("User-Agent", headerInfo.getUser_Agent());
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);
        return headerMap;
    }

    //上传头像时候
    private byte[] get_REG_personal_avatar_Body(String path,String guid){
        String jsonStr="guid:"+guid+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        String x_kv_len=String.valueOf(this.X_KV.length());
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());
        //mzip字符串参与拼接
        String mzip = StringUtil.getNoUrlDecodeMzip(bytesMzip);
        String mzipLen=String.valueOf(mzip.length());
        //图片文件二进制
        byte[] imgByte=StringUtil.getInstance().getImageByte(path);
        String imgStrLen=String.valueOf(imgByte.length);
        String map_id=ParamUtil.getInstance().getMapId();
        String map_id_len=String.valueOf(map_id.length());
        String ck=null;
        try {
            ck=ParamUtil.getInstance().getCkNoURLEncoder(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ck_len=String.valueOf(ck.length());

        String BodyStr="-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"map_id\"\r\n" +
                "Content-Length: "+map_id_len+"\r\n\r\n"+
                map_id+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"mzip\"\r\n"+
                "Content-Length: "+mzipLen+"\r\n\r\n"+
                mzip+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"code_version\"\r\n"+
                "Content-Length: 1\r\n\r\n"+
                "2"+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"ck\"\r\n"+
                "Content-Length: "+ck_len+"\r\n\r\n"+
                ck+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"X-KV\"\r\n"+
                "Content-Length: "+x_kv_len+"\r\n\r\n"+
                this.X_KV+"\r\n"+"-----------------------------7da2137580612\r\n"+
                "Content-Disposition: form-data; name=\"avatarimg\"; filename=\"220FA143-6509-03F3-2E97-7CDBCD2655F720201101.jpg_\"\r\n"+
                "Content-Type: application/octet-stream"+"\r\n"+
                "Content-Length: "+imgStrLen+"\r\n\r\n";
        byte[]  BodyByte=BodyStr.getBytes();
        byte[]  endByte="\r\n-----------------------------7da2137580612--".getBytes();
        byte[]  RequestBodyByte=new byte[BodyByte.length+imgByte.length+endByte.length];
        System.arraycopy(BodyByte,0,RequestBodyByte,0,BodyByte.length);
        System.arraycopy(imgByte,0,RequestBodyByte,BodyByte.length,imgByte.length);
        System.arraycopy(endByte,0,RequestBodyByte,BodyByte.length+imgByte.length,endByte.length);
        this.Content_Length=String.valueOf(RequestBodyByte.length);
        return RequestBodyByte;
    }

    //https://api-mini.immomo.com/api/setting/recallpush?fr=831737702
    public String REG_setting_recallpush(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_setting_recallpush + accountInfo.getAccount(), get_REG_setting_recallpush_Body(), get_REG_setting_recallpush_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_setting_recallpush_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
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
        headerMap.put("X-SIGN",X_SIGN);
        return headerMap;
    }

    private String get_REG_setting_recallpush_Body() {

        //String RequestBody="_iid="+deviceInfo.get_iid()+"&switch=1"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"switch:1"+","+"_net_:"+deviceInfo.get_net_()+"_uid_:"+deviceInfo.get_uid_();
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

    //https://api-mini.immomo.com/api/setting/sound?fr=
    public String REG_setting_sound_no_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_setting_sound_no_zip + accountInfo.getAccount(), get_REG_setting_sound_no_zip_Body(), get_REG_setting_sound_no_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_setting_sound_no_zip_Header(String session) {
        this.cookie=session;
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
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

    private String get_REG_setting_sound_no_zip_Body() {
        String RequestBody="_iid="+deviceInfo.get_iid()+"&status=1"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //"https://live-api.immomo.com/v3/index/config?fr="
    public String REG_index_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_index_config + accountInfo.getAccount(), get_REG_index_config_Body(), get_REG_index_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_index_config_Header(String session) {
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

    private String get_REG_index_config_Body() {
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

    ///https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr
    public String REG_photon_getPushHashKey(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_photon_getPushHashKey + accountInfo.getAccount(), get_REG_photon_getPushHashKey_Body(), get_REG_photon_getPushHashKey_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_photon_getPushHashKey_Header(String session) {
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

    private String get_REG_photon_getPushHashKey_Body() {
        String jsonStr="alias:"+accountInfo.getAccount()+","+"_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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

    //https://api-mini.immomo.com/v2/notify/business/getAllSwitch?fr=

    public String REG_business_getAllSwitch(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_business_getAllSwitch + accountInfo.getAccount(), get_REG_business_getAllSwitch_Body(), get_REG_business_getAllSwitch_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_business_getAllSwitch_Header(String session) {
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

    private String get_REG_business_getAllSwitch_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
        String BodyStr= StringUtil.getInstance().string2Json(jsonStr);
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);
        byte[] bytesMzip=StringUtil.getBytesMzip(BodyStr,aesKey);
        String mzip = StringUtil.getMzip(bytesMzip);
        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String RequestBody="mzip="+mzip;
        this.Content_Length =String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-log.immomo.com/v1/log/common/upload?fr=668700100
    public String REG_log_common_upload(String session){
        String response= HttpUtil.post(RequestUrl.REG_log_common_upload + accountInfo.getAccount(), get_REG_log_common_upload_Body(), get_REG_log_common_upload_Header(session));
        return response;
    }

    private Map<String,String> get_REG_log_common_upload_Header(String session){
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

    private String get_REG_log_common_upload_Body(){
        String RequestBody= null;
        String sourceid=UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&opentype=hot"+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //"https://api-log.immomo.com/v1/log/common/androidonlinetime?fr="
    public String REG_common_androidonlinetime(String session){
        String response= HttpUtil.post(RequestUrl.REG_common_androidonlinetime + deviceInfo.getUid(), get_REG_common_androidonlinetime_Body(), get_REG_common_androidonlinetime_Header(session));
        return response;
    }

    private Map<String,String> get_REG_common_androidonlinetime_Header(String session){
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

    private String get_REG_common_androidonlinetime_Body(){
        String RequestBody= null;
        String sourceid = UUID.randomUUID().toString().toUpperCase();
        RequestBody="sourceid="+sourceid+"&_iid="+deviceInfo.get_iid()+"&type=online"+"&_net_="+deviceInfo.get_net_()+"&_uid_"+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //"https://live-api.immomo.com/v3/config/user/index?fr="
    public String REG_config_user_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_config_user_index + accountInfo.getAccount(), get_REG_config_user_index_Body(), get_REG_config_user_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_config_user_index_Header(String session) {
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

    private String get_REG_config_user_index_Body() {
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
    public String REG_effectListNew(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_effectListNew + accountInfo.getAccount(), get_REG_effectListNew_Body(), get_REG_effectListNew_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_effectListNew_Header(String session) {
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

    private String get_REG_effectListNew_Body() {
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


    //https://api-mini.immomo.com/api/setting/pushlive?fr=
    public String REG_setting_pushlive(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_setting_pushlive + accountInfo.getAccount(), get_REG_setting_pushlive_Body(), get_REG_setting_pushlive_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_setting_pushlive_Header(String session) {
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

    private String get_REG_setting_pushlive_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"status:1"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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

    //https://api-mini.immomo.com/v1/account/token/transfer?fr=831737702
    public String REG_token_transfer(String session){
        String response= HttpUtil.post(RequestUrl.REG_token_transfer + deviceInfo.getUid(), get_REG_token_transfer_Body(), get_REG_token_transfer_Header(session));
        return response;
    }

    private Map<String,String> get_REG_token_transfer_Header(String session){
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

    private String get_REG_token_transfer_Body(){
        String RequestBody= "_iid="+deviceInfo.get_iid()+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }

    //https://api-mini.immomo.com/v2/growth/notice/config?fr=
    public String REG_notice_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_notice_config + accountInfo.getAccount(), get_REG_notice_config_Body(), get_REG_notice_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_notice_config_Header(String session) {
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

    private String get_REG_notice_config_Body() {
        String jsonStr="dpmedia:"+""+","+"_iid:"+deviceInfo.get_iid()+","+"channelid:"+"14"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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


    //即使缓存登陆，还是需要登陆一下guestLoginRelated.INI_appconfig_index(0)  ,使用返回值
    public String REG_appconfig_index(String session, String gustLoginRet){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_appconfig_index + accountInfo.getAccount(), get_REG_appconfig_index_Body(gustLoginRet), get_REG_appconfig_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_appconfig_index_Header(String session) {
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

    private String get_REG_appconfig_index_Body(String ret) {
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

    //"https://live-api.immomo.com/v3/room/sale/productListsAll?fr="
    public String REG_productListsAll(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_productListsAll + accountInfo.getAccount(), get_REG_productListsAll_Body(), get_REG_productListsAll_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_productListsAll_Header(String session) {
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

    private String get_REG_productListsAll_Body() {
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

    //"https://api-mini.immomo.com/api/banners/v2/welcome?fr="
    public String REG_banners_v2_welcome(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_banners_v2_welcome + accountInfo.getAccount(), get_REG_banners_v2_welcome_Body(), get_REG_banners_v2_welcome_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_banners_v2_welcome_Header(String session) {
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

    private String get_REG_banners_v2_welcome_Body() {
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


    //https://api-mini.immomo.com/api/profiles/832031493?fr=832031493
    public String REG_api_profiles(String session, String remoteids){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_api_profiles +accountInfo.getAccount()+"?fr="+ accountInfo.getAccount(), get_REG_api_profiles_Body(remoteids), get_REG_api_profiles_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_api_profiles_Header(String session) {
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

    private String get_REG_api_profiles_Body(String remoteids) {

        String jsonStr="remoteids:"+remoteids+","+"_iid:"+deviceInfo.get_iid()+","+"fields:"+"momoid#name#sign#remarkname#photos#loc_timesec#sex#distance#age#relation#sina_user_id#sina_vip_desc#group_role#baned#industry#level#official"+","+
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


    //"https://api-mini.immomo.com/v1/emotion/discover/category?fr="
    public String REG_discover_category(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_discover_category + accountInfo.getAccount(), get_REG_discover_category_Body(), get_REG_discover_category_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_discover_category_Header(String session) {
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

    private String get_REG_discover_category_Body() {
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

    //https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=
    public String REG_together_checkreddot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_together_checkreddot + accountInfo.getAccount(), get_REG_together_checkreddot_Body(), get_REG_together_checkreddot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_together_checkreddot_Header(String session) {
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

    private String get_REG_together_checkreddot_Body() {
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


    //"https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr="
    public String REG_appconfigmulti_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_appconfigmulti_index + accountInfo.getAccount(), get_REG_appconfigmulti_index_Body(), get_REG_appconfigmulti_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_appconfigmulti_index_Header(String session) {
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

    private String get_REG_appconfigmulti_index_Body() {
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



    //https://api-mini.immomo.com/v2/user/my/base?fr=668700100
    public String REG_my_base_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_my_base_zip + accountInfo.getAccount(), get_REG_my_base_zip_Body(), get_REG_my_base_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_my_base_zip_Header(String session) {
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

    private String get_REG_my_base_zip_Body() {
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
    public String REG_official_config(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_official_config + accountInfo.getAccount(), get_REG_official_config_Body(), get_REG_official_config_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_official_config_Header(String session) {
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

    private String get_REG_official_config_Body() {
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

    //https://api-mini.immomo.com/api/setting/sound?fr=832031493
    public String REG_setting_sound_zip(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_setting_sound_zip + accountInfo.getAccount(), get_REG_setting_sound_zip_Body(), get_REG_setting_sound_zip_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_setting_sound_zip_Header(String session) {
        Map<String, String> headerMap = new LinkedHashMap<>();
        this.cookie=session;
        headerMap.put("X-SIGN",this.X_SIGN);
        headerMap.put("X-Trace-Id",this.X_Trace_Id);
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("X-KV", this.X_KV);
        headerMap.put("cookie","SESSIONID="+this.cookie);
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

    private String get_REG_setting_sound_zip_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"status:1"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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
    public String REG_friend_getGlobalSearchUsers(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_friend_getGlobalSearchUsers + accountInfo.getAccount(), get_REG_friend_getGlobalSearchUsers_Body(), get_REG_friend_getGlobalSearchUsers_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_friend_getGlobalSearchUsers_Header(String session) {
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

    private String get_REG_friend_getGlobalSearchUsers_Body() {
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

    //"https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr="
    public String REG_webapp_wholelists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_webapp_wholelists + accountInfo.getAccount(), get_REG_webapp_wholelists_Body(), get_REG_webapp_wholelists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_webapp_wholelists_Header(String session) {
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

    private String get_REG_webapp_wholelists_Body() {
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
    public String REG_wifi_upload(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_wifi_upload + accountInfo.getAccount(), get_REG_wifi_upload_Body(), get_REG_wifi_upload_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_wifi_upload_Header(String session) {
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

    private String get_REG_wifi_upload_Body() {
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
    public String REG_special_getallindustry(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_special_getallindustry + accountInfo.getAccount(), get_REG_special_getallindustry_Body(), get_REG_special_getallindustry_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_special_getallindustry_Header(String session) {
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

    private String get_REG_special_getallindustry_Body() {
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

    //"https://api-mini.immomo.com/api/emotion/list/my?fr="
    public String REG_list_my(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_list_my + accountInfo.getAccount(), get_REG_list_my_Body(), get_REG_list_my_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_list_my_Header(String session) {
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

    private String get_REG_list_my_Body() {
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

    //"https://api-mini.immomo.com/api/emotion/update?fr="
    public String REG_emotion_update(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_emotion_update + accountInfo.getAccount(), get_REG_emotion_update_Body(), get_REG_emotion_update_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_emotion_update_Header(String session) {
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

    private String get_REG_emotion_update_Body() {
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
    public String REG_service_getRedDot(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_service_getRedDot + accountInfo.getAccount(), get_REG_service_getRedDot_Body(), get_REG_service_getRedDot_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone_no_zip(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_service_getRedDot_Header(String session) {
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

    private String get_REG_service_getRedDot_Body() {
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

    //"https://paas-push-api.immomo.com/push/index/unalias?appsr="
    public String REG_index_unalias(String alias, String token){
        String response= HttpUtil.post(RequestUrl.REG_index_unalias + ParamUtil.getInstance().getAppsr(), get_REG_index_unalias_Body(alias,token), get_REG_index_unalias_Header());
        return response;
    }

    private Map<String,String> get_REG_index_unalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_REG_index_unalias_Body(String alias, String token){
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


    //https://api-mini.immomo.com/v2/user/my/base?fr=668700100
    public String REG_my_base_zip_1(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_my_base_zip_1 + accountInfo.getAccount(), get_REG_my_base_zip_1_Body(), get_REG_my_base_zip_1_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_my_base_zip_1_Header(String session) {
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

    private String get_REG_my_base_zip_1_Body() {
        String jsonStr="_iid:"+deviceInfo.get_iid()+","+"source:after_avatar_check"+","+"_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();
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

    //"https://paas-push-api.immomo.com/push/index/regwithalias?appsr="
    public String REG_index_regwithalias(String alias,String sn){
        String response= HttpUtil.post(RequestUrl.REG_index_regwithalias + ParamUtil.getInstance().getAppsr(), get_REG_index_regwithalias_Body(alias,sn), get_REG_index_regwithalias_Header());
        return response;
    }

    private Map<String,String> get_REG_index_regwithalias_Header(){
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("User-Agent",headerInfo.getUser_Agent());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Content-Length",this.Content_Length);
        headerMap.put("Host","paas-push-api.immomo.com");
        headerMap.put("Connection",headerInfo.getConnection());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    private String get_REG_index_regwithalias_Body(String alias,String sn){
        String msc=ParamUtil.getInstance().getMsc();
        String mzip=ParamUtil.getInstance().getMzip(alias,sn,true);    //账号密码登陆的时候flag的标志是true
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


    //"https://api-alpha.immomo.com/momopush/fasttoken/reg?fr="
    public String REG_fasttoken_reg(String session, String token){
        String response= HttpUtil.post(RequestUrl.REG_fasttoken_reg +accountInfo.getAccount(), get_REG_fasttoken_reg_Body(token), get_REG_fasttoken_reg_Header(session));
        return response;
    }

    private Map<String,String> get_REG_fasttoken_reg_Header(String session){
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

    private String get_REG_fasttoken_reg_Body(String token){
        String RequestBody= null;
        RequestBody="_iid="+deviceInfo.get_iid()+"&token="+token+"&_net_="+deviceInfo.get_net_()+"&_uid_="+deviceInfo.get_uid_();
        this.Content_Length=String.valueOf(RequestBody.length());
        return RequestBody;
    }


    //"https://api-mini.immomo.com/v2/setting/abtest/index?fr="
    public String REG_abtest_index(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_abtest_index + accountInfo.getAccount(), get_REG_abtest_index_Body(), get_REG_abtest_index_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_abtest_index_Header(String session) {
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

    private String get_REG_abtest_index_Body() {
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


    //"https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr="
    public String REG_people_lists(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_people_lists + accountInfo.getAccount(), get_REG_people_lists_Body(), get_REG_people_lists_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_people_lists_Header(String session) {
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
    //{"acc":"33.0","gapps":"1","mmuid":"","realauth":"0","screen":"1080x1794","device_type":"android","osversion_int":"27","ddian_active_time":"1608801661","online_time":"0","constellation":"0","model":"Pixel","net":"1","cell_id":"43004577","lat":"37.842468","androidId":"912cd84c01034e24","age_max":"100","_uid_":"6766272a7e000278b21192236b3c3eb1","phone_type":"GSM","lng":"112.430393","refreshmode":"auto","count":"24","index":"0","age_min":"18","_iid":"9dc14fa42e557b59494fed1e110ded70","is_bubble_up":"0","version":"100071","apksign":"4f3a531caff3e37c278659cc78bfaecc","_net_":"wifi","router_mac":"8c:53:c3:d2:98:a2","network_class":"wifi","vip_filter":"0","style":"1","buildnumber":"OPM4.171019.021.P1\/4820305","save":"YES","locater":"202","imsi":"unknown","moment_sex":"","emu":"029f181d6e7ba188885c78462623c37a","loctype":"1","mac":"02:00:00:00:00:00","manufacturer":"Google","rom":"8.1.0","uid":"6766272a7e000278b21192236b3c3eb1","total":"0","native_ua":"Mozilla\/5.0 (Linux; Android 8.1.0; Pixel Build\/OPM4.171019.021.P1; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/61.0.3163.98 Mobile Safari\/537.36","market_source":"14","oaid":"","social":"0","phone_netWork":"0","dpp":"5a528750058b6a75118a4107ded02d6e","sex":"","firstRefresh":"1","_uidType":"androidid","imei":"352531081145847"}
    private String get_REG_people_lists_Body() {
        String jsonStr="acc:"+"33.0"+","+"gapps:"+deviceInfo.getGapps()+","+"mmuid:"+deviceInfo.getMmuid()+","+"realauth:"+"0"+","+"screen:"+deviceInfo.getScreen()+","+"device_type:"+deviceInfo.getDevice_type()+","+
                "osversion_int:"+deviceInfo.getOsversion_int()+","+"ddian_active_time:"+System.currentTimeMillis()+","+"online_time:"+"0"+","+"constellation:"+"0"+","+"model:"+deviceInfo.getModel()+","+
                "net:"+deviceInfo.getNet()+","+"cell_id:"+"0"+","+"lat:"+deviceInfo.getLat()+","+"androidId:"+deviceInfo.getAndroidId()+","+"age_max:"+"100"+","+"_uid_:"+deviceInfo.get_uid_()+","+
                "phone_type:"+deviceInfo.getPhone_type()+","+"lng:"+deviceInfo.getLng()+","+"refreshmode:"+"auto"+","+"count:"+"24"+","+"index:"+"0"+","+"age_min:"+"18"+","+"_iid:"+deviceInfo.get_iid()+","+
                "is_bubble_up:"+"0"+","+"version:100071"+","+"apksign:"+deviceInfo.getApksign()+","+"_net_:"+deviceInfo.get_net_()+","+"router_mac:"+deviceInfo.getRouter_mac()+","+
                "network_class:"+deviceInfo.getNetwork_class()+","+"vip_filter:"+"0"+","+"style:"+"1"+","+"buildnumber:"+deviceInfo.getBuildnumber()+","+"save:"+"YES"+","+"locater:"+"202"+","+
                "imsi:unknown"+","+"moment_sex:"+""+","+"emu:"+deviceInfo.getEmu()+","+"loctype:"+"1"+","+"mac:"+deviceInfo.getMac()+","+"manufacturer:"+deviceInfo.getManufacturer()+","+
                "rom:"+deviceInfo.getRom()+","+"uid:"+deviceInfo.getUid()+","+"total:"+"0"+","+"native_ua:"+deviceInfo.getNative_ua()+","+"market_source:"+deviceInfo.getMarket_source()+","+
                "oaid:"+deviceInfo.getOaid()+","+"social:"+"0"+","+"phone_netWork:"+"0"+","+"dpp:"+deviceInfo.getDpp()+","+"sex:"+""+","+"firstRefresh:"+"1"+","+"_uidType:"+deviceInfo.get_uidType()+","+
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
    public String REG_together_generalmsg(String session){
        byte[] respone= HttpUtil.postRetByte(RequestUrl.REG_together_generalmsg + accountInfo.getAccount(), get_REG_together_generalmsg_Body(), get_REG_together_generalmsg_Header(session));
        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> get_REG_together_generalmsg_Header(String session) {
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
    private String get_REG_together_generalmsg_Body() {
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

}
