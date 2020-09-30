package com.accountoperation;

import com.constantfield.RequestUrl;
import com.utiltool.*;

import java.util.*;

public class AccountOperation {

    private DeviceInfoUtil deviceInfo =null;
    private AccountInfoUtil accountInfo =null;
    private HeaderInfoUtil headerInfo =null;

    String publicKey;
    String aesKey;

    private String X_KV;
    private String X_SIGN;
    private String Content_Length;
    private String X_Trace_Id;
    private String cookie;

    public AccountOperation(DeviceInfoUtil deviceInfoUtil,AccountInfoUtil accountInfoUtil,HeaderInfoUtil headerInfoUtil){
        this.deviceInfo =deviceInfoUtil;
        this.accountInfo =accountInfoUtil;
        this.headerInfo =headerInfoUtil;
        this.aesKey=deviceInfo.getAesKey().substring(0,16);
        this.publicKey=deviceInfo.getPublic_key();
        this.X_Trace_Id=String.valueOf(UUID.randomUUID()).toUpperCase();
    }

    public String publishCheck(String session){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.publishPosChecktUrl+ accountInfo.getAccount(), getcheckBody(), getcheckHeader(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,String> getcheckHeader(String session){
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
        headerMap.put("User-Agent", headerInfo.getUserAgent());
        headerMap.put("Content-Type", headerInfo.getContent_Type());
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);

        return headerMap;
    }

    public String getcheckBody(){

        String bodyStr="{\"share_to\":\"\",\"favor_type\":\"0\",\"_iid\":\"b0238aafb09eda07e3451c7b631e8c0d\",\"favor_id\":\"\",\"content\":\"[{\\\"text\\\":\\\"1111111111111111111111111111111222222222222223333333333333444444444444\\\",\\\"type\\\":\\\"1\\\"}]\",\"forward_origin_feedid\":\"\",\"_net_\":\"wifi\",\"_uid_\":\"6766272a7e000278b21192236b3c3eb1\"}";
        System.out.println(bodyStr);

        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);

        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);

        this.X_SIGN = ParamUtil.getInstance().getXsign(bodyStr.getBytes(), infoMap, aesKey, deviceInfo.getUserAgent());

        String mzip=StringUtil.getMzip(bodyStr,aesKey);

        String RequestBody="mzip="+mzip;

        this.Content_Length=String.valueOf(RequestBody.length());

        return RequestBody;
    }


}
