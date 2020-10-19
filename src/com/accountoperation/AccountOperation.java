package com.accountoperation;

import com.constantfield.RequestUrl;
import com.utiltool.*;

import java.io.UnsupportedEncodingException;
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
//-----------------------------------------------------------------------单纯发文字------------------------------------------------------------------------------------
    //发动态检查
    public String publishCheck(String session,String strContext){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.publishPosChecktUrl+ accountInfo.getAccount(), getcheckBody(strContext), getcheckHeader(session));

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

    public String getcheckBody(String strContent){

        String jsonStr="share_to:"+""+","+"favor_type:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"favor_id:"+""+","+"content:"+"\"[{\"text\":\"" + strContent + "\",\"type\":\"1\"}]\""+","+"forward_origin_feedid:"+""+","+
                "_net_:"+deviceInfo.get_net_()+"_uid_"+deviceInfo.get_uid_();

        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);

        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("X-Span-Id",headerInfo.getX_Span_Id());
        infoMap.put("X-LV", headerInfo.getX_LV());
        infoMap.put("X-KV", this.X_KV);
        infoMap.put("X-Trace-Id",this.X_Trace_Id);

        byte[] bytesMzip=StringUtil.getBytesMzip(bodyStr,aesKey);

        this.X_SIGN = ParamUtil.getInstance().getXsign(bytesMzip, infoMap, aesKey, headerInfo.getMultiUA());  //param1:boday加密后的数据，第三个参数是aesKey
        String mzip = StringUtil.getMzip(bytesMzip);
        String RequestBody="mzip="+mzip;

        this.Content_Length=String.valueOf(RequestBody.length());

        return RequestBody;
    }

//动态发送
    public String publishSend(String session,String strContext){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.publishPostSendUrl+ accountInfo.getAccount(), getsendBody(strContext), getsendHeader(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,String> getsendHeader(String session){
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

    public String getsendBody(String strContent){

        String jsonStr="last_type:"+"com.immomo.momo.feedlist.fragment.impl.NearbyFeedListFragment"+","+"lng:"+"112.430347"+","+"src:"+"5"+","+"is_from_digimon:"+"0"+","+"share_mode:"+"0"+","+
                "is_super:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"tags_postion:"+"{}"+","+"content:"+"\"[{\"text\":\""+strContent+"\",\"type\":\"1\"}]\""+","+"loctype:"+"1"+","+
                "tags:"+"[]"+","+"sid:"+""+","+"_net_:"+deviceInfo.get_net_()+","+"share_to:"+""+","+"sync_feed:"+"1"+","+"addFavor:"+"0"+","+"sync_sina:"+"0"+","+"sync_weixin:"+"0"+","+
                "parent_sid:"+""+","+"sync_qzone:"+""+","+"feed_type:"+""+","+"lat:"+"37.84258"+","+"_uid_:"+deviceInfo.get_uid_();

        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);

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

//-----------------------------------------------------------------------单纯发文字------------------------------------------------------------------------------------

//-----------------------------------------------------------------------发图片+文字------------------------------------------------------------------------------------
    public String publishPhotoCheck(String session,String strContext){

        byte[] respone= HttpUtil.postRetByte(RequestUrl.publishPosChecktUrl+ accountInfo.getAccount(), getcheckPhotoBody(strContext), getcheckPhotoHeader(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//photocheck
    public Map<String,String> getcheckPhotoHeader(String session){
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

    public String getcheckPhotoBody(String strContent){

        String jsonStr="share_to:"+""+","+"favor_type:"+"2"+","+"_iid:"+deviceInfo.get_iid()+","+"favor_id:"+""+","+"content:"+"\"[{\"text\":\""+strContent+"\",\"type\":\"1\"}]\""+","+"forward_origin_feedid:"+""+","+
                "_net_:"+deviceInfo.get_net_()+","+"_uid_:"+deviceInfo.get_uid_();

        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);


        System.out.println(bodyStr);


        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);

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


//photosend
    public String publishPhotoSend(String session,String path,String strContext){

        byte[] respone= HttpUtil.postByte(RequestUrl.publishPostSendUrl+ accountInfo.getAccount(), getsendPhotoBody(path,strContext), getsendPhotoHeader(session));

        System.out.println(Arrays.toString(respone));

        try {
            return CryptUtil.getInstance().decodeRespone(respone,aesKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,String> getsendPhotoHeader(String session){
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
        headerMap.put("Content-Type", "multipart/form-data; boundary=---------------------------7da2137580612");
        headerMap.put("Host", headerInfo.getHost());
        headerMap.put("Accept-Encoding", headerInfo.getAccept_Encoding());
        headerMap.put("MultiUA", headerInfo.getMultiUA());
        headerMap.put("Content-Length",this.Content_Length);

        return headerMap;
    }

    public byte[] getsendPhotoBody(String path,String strContext){

        String jsonStr="last_type:"+"com.immomo.momo.feedlist.fragment.impl.NearbyFeedListFragment"+","+"is_from_digimon:"+"0"+","+"is_super:"+"0"+","+"content:"+"\"[{\"text\":\""+strContext+"\",\"type\":\"1\"}]\""+","+"loctype:"+"1"+","+
                "sid:"+"1f586c6bad0c9971"+","+"photo_extra:"+"\"[{\"take_photo\":{},\"edit_photo\":{}}]\""+","+"sync_sina:"+"0"+","+"parent_sid:"+""+","+"sync_qzone:"+"0"+","+
                "pics:"+"\"[{\"key\":\"photo_0\",\"optimized\":1,\"upload\":\"NO\"}]\""+","+"lat:"+"37.842563"+","+"_uid_:"+deviceInfo.get_uid_()+","+"lng:"+"112.430395"+","+
                "src:"+"5"+","+"share_mode:"+"0"+","+"_iid:"+deviceInfo.get_iid()+","+"tags_postion:"+"\"{}\""+","+"tags:"+"\"[{\"from\":\"album_pic\"}]\""+","+"_net_:"+deviceInfo.get_net_()+","+
                "share_to:"+""+","+"sync_feed:"+"1"+","+"addFavor:"+"0"+","+"sync_weixin:"+"0"+","+"feed_type:"+"2";

        String bodyStr= StringUtil.getInstance().string2Json(jsonStr);

        System.out.println(bodyStr);

        this.X_KV= ParamUtil.getInstance().getXkv(publicKey);

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

//-----------------------------------------------------------------------发图片+文字------------------------------------------------------------------------------------













































//-----------------------------------------------------------------------单纯发图片------------------------------------------------------------------------------------






}
