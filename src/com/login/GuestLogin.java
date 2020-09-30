package com.login;

import com.constantfield.RequestUrl;
import com.utiltool.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

//游客登陆请求
public class GuestLogin {

    private DeviceInfoUtil deviceInfo =null;
    private HeaderInfoUtil headerInfo =null;

    private String Content_Length=null;
    private String X_Trace_Id=null;
    private String X_KV=null;
    private String cookie=null;

    private String prm_1;
    private String prm_2;
    private String prm_3;

    public GuestLogin(){}

    public GuestLogin(DeviceInfoUtil deviceInfoUtil,HeaderInfoUtil headerInfoUtil){
        this.deviceInfo =deviceInfoUtil;
        this.headerInfo =headerInfoUtil;
        this.X_Trace_Id=UUID.randomUUID().toString().toUpperCase();
        this.X_KV="f14dd39f";        //KV的生成
        this.prm_1= UUID.randomUUID().toString();
        this.prm_2=UUID.randomUUID().toString();
        String uniqueidStr= deviceInfoUtil.getImei()+deviceInfoUtil.getRouter_mac();
        try {
            this.prm_3= ParamUtil.getInstance().getPrm_3(uniqueidStr+ StringUtil.getInstance().getMD5(prm_1).substring(0,8)+StringUtil.getInstance().getMD5(prm_2).substring(24)).substring(10, 22);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String guestLogin(){
        String response= HttpUtil.post(RequestUrl.guestLoginUrl+ deviceInfo.getUid(), getBody(), getHeader());
        return response;
    }

    private Map<String,String> getHeader(){
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
        headerMap.put("User-Agent",headerInfo.getUserAgent());
        headerMap.put("MultiUA",headerInfo.getMultiUA());
        headerMap.put("Content-Type",headerInfo.getContent_Type());
        headerMap.put("Host",headerInfo.getHost());
        headerMap.put("Accept-Encoding",headerInfo.getAccept_Encoding());
        return headerMap;
    }

    public String getBody(){

        String RequestBody= null;
        try {
            RequestBody = "prm_3="+prm_3+"&gapps="+ deviceInfo.getGapps()+"&prm_2="+prm_2+"&prm_1="+prm_1+"&buildnumber=" +URLEncoder.encode(deviceInfo.getBuildnumber(),"GBK")+"&mmuid="+
                    "&screen="+ deviceInfo.getScreen()+"&device_type="+ deviceInfo.getDevice_type()+"&imsi="+ deviceInfo.getImsi()+"&emu="+ deviceInfo.getEmu()+"&mac="+ URLEncoder.encode(deviceInfo.getMac(),"GBK")+"&manufacturer="+ deviceInfo.getManufacturer()+
                    "&osversion_int="+ deviceInfo.getOsversion_int()+"&rom="+ deviceInfo.getRom()+"&uid="+ deviceInfo.getUid()+"&market_source="+ deviceInfo.getMarket_source()+"&model="+ deviceInfo.getModel()+""+"&uniqueid="+URLEncoder.encode(deviceInfo.getUniqueid(),"GBK")+
                    "&oaid="+"&androidId="+ deviceInfo.getAndroidId()+"&_uid_="+ deviceInfo.get_uid_()+"&phone_type="+ deviceInfo.getPhone_type()+"&phone_netWork="+ deviceInfo.getPhone_netWork()+"&dpp="+ deviceInfo.getDpp()+"&idfa="+ deviceInfo.getIdfa()+
                    "&_iid="+ deviceInfo.get_iid()+"&version="+ deviceInfo.getVersion()+"&apksign="+ deviceInfo.getApksign()+"&_net_="+ deviceInfo.get_net_()+"&router_mac="+URLEncoder.encode(deviceInfo.getRouter_mac(),"GBK")+"&network_class="+ deviceInfo.getNetwork_class()+
                    "&_uidType="+ deviceInfo.get_uidType()+"&imei="+"&uniquetime="+ System.currentTimeMillis();
            this.Content_Length =String.valueOf(RequestBody.length());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return RequestBody;
    }
}
