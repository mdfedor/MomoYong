package com.login;

import com.constantfield.RequestUrl;
import com.utiltool.*;
import net.sf.json.JSONObject;
import java.net.URLEncoder;
import java.util.*;

//账号密码登陆请求
public class PwdLogin {

    private String publickey;
    private String aesKey;

    private AccountInfoUtil accountInfo =null;
    private DeviceInfoUtil deviceInfo =null;
    private HeaderInfoUtil headerInfo =null;

    //header变
    private String X_SIGN=null;
    private String Content_Length=null;
    private String X_Trace_Id=null;
    private String X_KV=null;
    private String cookie=null;

    //body
    private String map_id;
    private String ck;

    public PwdLogin(){
    }

    public PwdLogin(AccountInfoUtil accountInfoUtil, DeviceInfoUtil deviceInfo,HeaderInfoUtil headerInfoUtil){
        this.accountInfo =accountInfoUtil;
        this.deviceInfo =deviceInfo;
        this.headerInfo =headerInfoUtil;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.aesKey=deviceInfo.getAesKey().substring(0,16);
        this.publickey=deviceInfo.getPublic_key();
        this.map_id=ParamUtil.getInstance().getMapId();
    }


    public String pwdLogin(String session){

        //byte[] respone= HttpUtil.postRetByte(RequestUrl.pwdLoginUrl + deviceInfo.getUid(), getBody(), getHeader(session));


        //test>>>>>>>
        byte[] respone= HttpUtil.postRetByte("https://api-mini.immomo.com/api/v2/login?fu=6766272a7e000278b21192236b3c3eb1", ParamUtil.getInstance().body(), ParamUtil.getInstance().header(session));
        //test<<<<<<<

        //System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> getHeader(String session) {

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
        headerMap.put("User-Agent", headerInfo.getUserAgent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA",headerInfo.getMultiUA());

        return headerMap;
    }

    private String getBody() {

        String jsonStr="dpp:"+ deviceInfo.getDpp()+","+"BaseBandVersion:"+ deviceInfo.getBaseBandVersion()+","+"device_type:"+ deviceInfo.getDevice_type()+","+
                "screen:"+ deviceInfo.getScreen()+","+"isRoot:"+ deviceInfo.getIsRoot()+","+"utdid:"+ deviceInfo.getUtdid()+","+"phone_netWork:"+ deviceInfo.getPhone_netWork()+","+
                "CpuInfo:"+ deviceInfo.getCpuInfo()+","+"market_source:"+ deviceInfo.getMarket_source()+","+"rom:"+ deviceInfo.getRom()+","+"etype:"+ deviceInfo.getEtype()+","+
                "bindSource:"+ deviceInfo.getBindSource()+","+"oaid:"+ deviceInfo.getOaid()+","+"androidId:"+ deviceInfo.getAndroidId()+","+"hw:"+ deviceInfo.getHw()+","+"imei:"+ deviceInfo.getImei()+","+
                "emu:"+ deviceInfo.getEmu()+","+"version:"+ deviceInfo.getVersion()+","+"osversion_int:"+ deviceInfo.getOsversion_int()+","+"manufacturer:"+ deviceInfo.getManufacturer()+","+"phone_type:"+
                deviceInfo.getPhone_type()+","+"apksign:"+ deviceInfo.getApksign()+","+"acc:"+ deviceInfo.getAcc()+","+"imsi:"+ deviceInfo.getImsi()+","+"sensorNames:"+ deviceInfo.getSensorNames()+","+
                "password:"+ accountInfo.getPassword()+","+"SerialNumber:"+ deviceInfo.getSerialNumber()+","+"gapps:"+ deviceInfo.getGapps()+","+"buildnumber:"+ deviceInfo.getBuildnumber()+","+"mmuid:"+ deviceInfo.getMmuid()+","+
                "_uid_:"+ deviceInfo.get_uid_()+","+"mac:"+ deviceInfo.getMac()+","+"current_wifi:"+ deviceInfo.getCurrent_wifi()+","+"network_class:"+ deviceInfo.getNetwork_class()+","+"RAMSize:"+ deviceInfo.getRAMSize()+","+
                "KernelVersion:"+ deviceInfo.getKernelVersion()+","+"_net_:"+ deviceInfo.get_net_()+","+"lng:"+ deviceInfo.getLng()+","+"MacInfo:"+ deviceInfo.getMacInfo()+","+"router_mac:"+ deviceInfo.getRouter_mac()+","+
                "Serialno:"+ deviceInfo.getSerialno()+","+"_uidType:"+ deviceInfo.get_uidType()+","+"_iid:"+ deviceInfo.get_iid()+","+"uid:"+ deviceInfo.getUid()+","+"account:"+ accountInfo.getAccount()+","+"lat:"+ deviceInfo.getLat()+","+
                "BootSerialno:"+ deviceInfo.getBootSerialno()+","+"model:"+ deviceInfo.getModel();

        String BodyStr= string2Json(jsonStr);
        //System.out.println(BodyStr);

        this.X_KV= ParamUtil.getInstance().getXkv(publickey);
        try {
            this.ck= URLEncoder.encode(ParamUtil.getInstance().getCk(publickey),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);

        this.X_SIGN = ParamUtil.getInstance().getXsign(BodyStr.getBytes(), infoMap, aesKey, deviceInfo.getUserAgent());  //第三个参数是aesKey

        String mzip=StringUtil.getMzip(BodyStr,aesKey);

        String RequestBody="code_version=" + deviceInfo.getCode_version() + "&map_id=" +this.map_id+ "&mzip="+mzip+"&X-KV="+this.X_KV+"&ck="+this.ck;

        this.Content_Length =String.valueOf(RequestBody.length());

        return RequestBody;
    }

    public String string2Json(String str){
        String[] arrayPair = str.split(",");
        JSONObject jsonObject = new JSONObject();
        String key=null;
        String value=null;
        for(int n=0; n < arrayPair.length; n++){
            key=arrayPair[n].substring(0,arrayPair[n].indexOf(":"));
            value=arrayPair[n].substring(key.length()+1,arrayPair[n].length());
            jsonObject.put(key,value);
        }
        String jsonStr = jsonObject.toString();

        return jsonStr;
    }

}
