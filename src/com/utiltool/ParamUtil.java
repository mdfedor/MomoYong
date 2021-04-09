package com.utiltool;

import com.sun.jdi.request.StepRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static java.lang.Long.parseLong;

/*
* http request parameters获取请求参数
* */
public class ParamUtil {

    private static ParamUtil instance = null;

    public static ParamUtil getInstance() {
        if (instance == null)
            instance = new ParamUtil();
        return instance;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ParamUtil() {
        numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        PubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKbj7WvmhEVXZbeqvMGXdMDvGlD6/Aa/MRxkhtUzdMBtB1FzUGOs77Yo7Es3cxt4HQGrioAaPXCyNC4KX1L8qdcCAwEAAQ==";
        appId = "e66b3f1d949694ad0e88fc83654e3b25";
        appSign = "04:47:FA:95:5C:5C:94:3D:B0:04:F2:28:12:32:EB:AE:95:DF:FD:84";
        randGen = new Random();
        random = radam(12);
        //random="U4NzIYHkFIBP";
        ivspec = new IvParameterSpec("GUgemWNhGTrh6kSM".getBytes());
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }


    private char[] numbersAndLetters;
    private String PubKey;
    private Random randGen;
    private String random;
    private IvParameterSpec ivspec;
    private Cipher cipher;
    private String appId;
    private String appSign;


    public String getRandom() {
        return random;
    }

    public String radam(int num) {
        char[] cArr = new char[num];
        for (int index = 0; index < cArr.length; index++) {
            cArr[index] = numbersAndLetters[randGen.nextInt(62)];
        }
        return new String(cArr);
    }

    public byte[] RSAEncode(byte[] bArr) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, getpublickey(PubKey));
        return instance.doFinal(bArr);
    }

    public PublicKey getpublickey(String str) throws Exception {
        byte[] ret = Base64.getDecoder().decode(str.getBytes());
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(ret));
    }


    public String getMsc() {
        byte[] becb = null;
        try {
            becb = RSAEncode(random.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ret = Base64.getEncoder().encodeToString(becb);
        return ret;
    }

    //原函数
    public String getMzip(String alias,String sn, boolean flag) {

        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        jsonObject.put("alias", alias);
        //jsonObject.put("sn", "hashkey_6766272a7e000278b21192236b3c3eb1_0fd08c68b146399ece80103b663ea86bf6a07ff7");
        jsonObject.put("sn", sn);
        if (flag) {
            jsonObject.put("device_id", "c58614d50086ef81ca590f6ba0d6886a4b304a64");   //第一次登陆时,无缓存登陆返回的
        } else {
            jsonObject.put("device_id", "05de98a68979cbf034313a9cf1395e4a34c43840:+com.immomo.young");   //有缓存返回的
        }

        jsonObject.put("app_id", appId);
        jsonObject.put("keystore_sha1", appSign);


        String ret = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes());

        String mzip = encrypt(ret, random);

        return mzip;
    }

    public String getUnaliasMzip(String alias, String token) {

        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        jsonObject.put("app_id", appId);
        jsonObject.put("alias", alias);   //游客登陆guid
        jsonObject.put("token", token);
        String ret = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes());
        String mzip = encrypt(ret, random);
        return mzip;
    }

    public String getSn(String uuid,String jsonstr){
        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        String hashKey=dataObject.getString("hashKey");
        String sn="hashkey_"+uuid+"_"+hashKey;
        return sn;
    }


///////////////////// 测试使用
   /* public String getMzip(String alias){


        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        jsonObject.put("alias","1003897884290");
        jsonObject.put("sn","hashkey_6766272a7e000278b21192236b3c3eb1_53d4c5e5b5e28be6eb840e4a4ba915c8cda172b5");
        jsonObject.put("device_id","c58614d50086ef81ca590f6ba0d6886a4b304a64");
        jsonObject.put("app_id","e66b3f1d949694ad0e88fc83654e3b25");
        jsonObject.put("keystore_sha1","04:47:FA:95:5C:5C:94:3D:B0:04:F2:28:12:32:EB:AE:95:DF:FD:84");


        String ret=Base64.getEncoder().encodeToString(jsonObject.toString().getBytes());

        String mzip=encrypt(ret,random);

        return mzip;
    }*/
///////////////////// 测试使用


    public String encrypt(String paramStr, String randomStr) {
        //IvParameterSpec ivspec=new IvParameterSpec("GUgemWNhGTrh6kSM".getBytes());

        try {
            cipher.init(1, new SecretKeySpec(hash256(randomStr), "AES"), ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(paramStr.getBytes()));
        } catch (Exception unused_ex) {
            return null;
        }
    }


    private static byte[] hash256(String str) {
        try {
            MessageDigest Md = MessageDigest.getInstance("SHA-256");
            Md.update(str.getBytes());
            return Md.digest();
        } catch (Exception unused_ex) {
            return null;
        }
    }


    public String decrypt(String retStr, String randomStr) throws Exception {
        if (retStr != null && retStr.length() != 0) {
            try {
                cipher.init(2, new SecretKeySpec(hash256(randomStr), "AES"), ivspec);
                return new String(cipher.doFinal(Base64.getDecoder().decode(retStr.getBytes())));
            } catch (Exception unused_ex) {
                return null;
            }
        }
        throw new Exception("Empty string");
    }


    public String testDecrpt(String retStr, String randomStr) throws Exception {
        if (retStr != null && retStr.length() != 0) {
            try {
                cipher.init(2, new SecretKeySpec(hash256(randomStr), "AES"), ivspec);
                return new String(cipher.doFinal(Base64.getDecoder().decode(retStr.getBytes())));
            } catch (Exception unused_ex) {
                return null;
            }
        }
        throw new Exception("Empty string");
    }

////////////////////////////////////////////////////////////////////


    public String getXsign(byte[] jsonBytes, Map<String, String> pMap, String str, String userAgent) {
        int nInt = 0;
        if (isNull(str)) {
            return null;
        }
        byte[] strBytes = str.getBytes();
        if (strBytes.length < 8) {
            return null;
        }
        byte[] encBytes = isUseang(pMap) ? userAgent.getBytes(Charset.forName("UTF-8")) : "".getBytes(Charset.forName("UTF-8"));

        if (jsonBytes != null) {
            try {
                byte[] totalBytes = new byte[jsonBytes.length + encBytes.length];
                int mInt;
                for (mInt = 0; mInt < encBytes.length; ++mInt) {
                    totalBytes[mInt] = encBytes[mInt];
                }
                while (nInt < jsonBytes.length) {
                    totalBytes[encBytes.length + nInt] = jsonBytes[nInt];
                    ++nInt;
                }

                return sign(totalBytes, strBytes);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String sign(byte[] user, byte[] key) {
        byte[] total = new byte[user.length + 8];
        System.arraycopy(user, 0, total, 0, user.length);
        System.arraycopy(key, 0, total, user.length, 8);
        byte[] hash = CryptUtil.Sha1(total);
        return new String(Base64.getEncoder().encode(hash));
    }

    public boolean isNull(CharSequence arg1) {
        boolean v0 = arg1 == null || arg1.length() == 0;
        return v0;
    }

    public boolean isUseang(Map<String, String> arg2) {
        boolean v0 = arg2 == null || !arg2.containsKey("useang") || !"false".equalsIgnoreCase(arg2.get("useang"));
        return v0;
    }


    //游客登陆请求体参数prm_3
    public String getPrm_3(String arg3) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String retString;
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(arg3.getBytes("UTF-8"));
        retString = StringUtil.getInstance().toHex(digest.digest());
        return retString;
    }


    public String getXkv(String key) {
        return StringUtil.getInstance().getMD5(key).substring(0, 8);
    }

    public String getCk(String localPublicKey) throws Exception {
        byte[] lpKey = Base64.getDecoder().decode(localPublicKey);
        byte[] iu0WKHFyBytes = new byte[16];
        System.arraycopy("Iu0WKHFy".getBytes(), 0, iu0WKHFyBytes, 0, 8);
        byte[] bytesCk = CryptUtil.getInstance().momoEncode(lpKey, iu0WKHFyBytes);
        String ckStr = URLEncoder.encode(Base64.getEncoder().encodeToString(bytesCk), "UTF-8");
        return ckStr;
    }

    public String getCkNoURLEncoder(String localPublicKey) throws Exception {
        byte[] lpKey = Base64.getDecoder().decode(localPublicKey);
        byte[] iu0WKHFyBytes = new byte[16];
        System.arraycopy("Iu0WKHFy".getBytes(), 0, iu0WKHFyBytes, 0, 8);
        byte[] bytesCk = CryptUtil.getInstance().momoEncode(lpKey, iu0WKHFyBytes);
        String ckStr = Base64.getEncoder().encodeToString(bytesCk);
        return ckStr;
    }





    public String getSession(String json) throws JSONException {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        return dataObject.getString("session");
    }

    public String getAvatar(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        return dataObject.getString("avatar");
    }

    public String getMomoId(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        return dataObject.getString("momoid");
    }


    //获取curResource
    public String getCurResource(String jsonstr, boolean isGuest) {  //isGuest用来判断那两个字段是不是为空
        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        JSONObject configObject = dataObject.getJSONObject("config");
        JSONObject ID1203Object = configObject.getJSONObject("1203");
        JSONObject plugin_updateObject = ID1203Object.getJSONObject("plugin_update");
        JSONArray jsonArray = new JSONArray();
        LinkedList<String> strList = new LinkedList<>();
        strList.add("name");
        strList.add("guid");
        strList.add("version");

        if (!isGuest) {   //
            JSONObject mmcv_android_facedetect_model = plugin_updateObject.getJSONObject("mmcv_android_facedetect_model");
            JSONObject aliyun_auth = plugin_updateObject.getJSONObject("aliyun_auth");
            JSONObject mm_emoji = plugin_updateObject.getJSONObject("mm_emoji");
            JSONObject scan_media = plugin_updateObject.getJSONObject("scan_media");
            JSONObject record_effects_video = plugin_updateObject.getJSONObject("record_effects_video");
            JSONObject mmcv_android_fa_model = plugin_updateObject.getJSONObject("mmcv_android_fa_model");
            JSONObject mmcv_android_facequality_model = plugin_updateObject.getJSONObject("mmcv_android_facequality_model");
            JSONObject photo_spam = plugin_updateObject.getJSONObject("photo_spam");
            JSONObject justice_model = plugin_updateObject.getJSONObject("justice_model");
            JSONObject mmbg_video = plugin_updateObject.getJSONObject("mmbg_video");

            jsonArray.put(putValue(mmcv_android_facedetect_model, strList, "mmcv_android_facedetect_model"));
            jsonArray.put(putValue(aliyun_auth, strList, "aliyun_auth"));
            jsonArray.put(putValue(mm_emoji, strList, "mm_emoji"));
            jsonArray.put(putValue(scan_media, strList, "scan_media"));
            jsonArray.put(putValue(record_effects_video, strList, "record_effects_video"));
            jsonArray.put(putValue(mmcv_android_fa_model, strList, "mmcv_android_fa_model"));
            jsonArray.put(putValue(mmcv_android_facequality_model, strList, "mmcv_android_facequality_model"));
            jsonArray.put(putValue(photo_spam, strList, "photo_spam"));
            jsonArray.put(putValue(justice_model, strList, "justice_model"));
            jsonArray.put(putValue(mmbg_video, strList, "mmbg_video"));
            JSONObject qrcodeObj = new JSONObject();
            qrcodeObj.put("name", "qrcode_detect");
            qrcodeObj.put("version", "0");
            jsonArray.put(qrcodeObj);
        } else {  //游客登陆有两个字段是0  qrcode_detect  mmcv_android_facequality_model
            JSONObject mmcv_android_facedetect_model = plugin_updateObject.getJSONObject("mmcv_android_facedetect_model");
            JSONObject aliyun_auth = plugin_updateObject.getJSONObject("aliyun_auth");
            JSONObject mm_emoji = plugin_updateObject.getJSONObject("mm_emoji");
            JSONObject scan_media = plugin_updateObject.getJSONObject("scan_media");
            JSONObject record_effects_video = plugin_updateObject.getJSONObject("record_effects_video");
            JSONObject mmcv_android_fa_model = plugin_updateObject.getJSONObject("mmcv_android_fa_model");
            //JSONObject mmcv_android_facequality_model=plugin_updateObject.getJSONObject("mmcv_android_facequality_model");
            JSONObject photo_spam = plugin_updateObject.getJSONObject("photo_spam");
            JSONObject justice_model = plugin_updateObject.getJSONObject("justice_model");
            JSONObject mmbg_video = plugin_updateObject.getJSONObject("mmbg_video");

            jsonArray.put(putValue(mmcv_android_facedetect_model, strList, "mmcv_android_facedetect_model"));
            jsonArray.put(putValue(aliyun_auth, strList, "aliyun_auth"));
            jsonArray.put(putValue(mm_emoji, strList, "mm_emoji"));
            jsonArray.put(putValue(scan_media, strList, "scan_media"));
            jsonArray.put(putValue(record_effects_video, strList, "record_effects_video"));
            jsonArray.put(putValue(mmcv_android_fa_model, strList, "mmcv_android_fa_model"));
            //jsonArray.put(putValue(mmcv_android_facequality_model,strList,"mmcv_android_facequality_model"));
            jsonArray.put(putValue(photo_spam, strList, "photo_spam"));
            jsonArray.put(putValue(justice_model, strList, "justice_model"));
            jsonArray.put(putValue(mmbg_video, strList, "mmbg_video"));
            JSONObject qrcodeObj = new JSONObject();
            qrcodeObj.put("name", "qrcode_detect");
            qrcodeObj.put("version", "0");
            jsonArray.put(qrcodeObj);
            JSONObject mmcv_android_facequality_model = new JSONObject();
            mmcv_android_facequality_model.put("name", "mmcv_android_facequality_model");
            mmcv_android_facequality_model.put("version", "0");
            jsonArray.put(mmcv_android_facequality_model);
        }


        return jsonArray.toString();
    }

    public JSONObject putValue(JSONObject jsonObject, LinkedList<String> strList, String name) {
        JSONObject jsObj = new JSONObject();
        for (String str : strList) {
            if (str.equals("name")) {
                jsObj.put(str, name);
                continue;
            }
            jsObj.put(str, jsonObject.get(str));
        }
        return jsObj;
    }

    //初始化Cur[{"name":"record_effects_video","version":0},{"name":"photo_spam","version":0},{"name":"qrcode_detect","version":0},{"name":"aliyun_auth","version":0},{"name":"mmcv_android_facedetect_model","version":0},{"name":"mmcv_android_fa_model","version":0},{"name":"mm_emoji","version":0},{"name":"mmbg_video","version":0},{"name":"mmcv_android_facequality_model","version":0},{"name":"justice_model","version":0},{"name":"scan_media","version":0}]
    public String initCurResource() {
        LinkedList<String> valueList = new LinkedList<>();
        valueList.add("name");
        valueList.add("version");

        LinkedList<String> nameList = new LinkedList<>();
        nameList.add("record_effects_video");
        nameList.add("photo_spam");
        nameList.add("qrcode_detect");
        nameList.add("aliyun_auth");
        nameList.add("mmcv_android_facedetect_model");
        nameList.add("mmcv_android_fa_model");
        nameList.add("mm_emoji");
        nameList.add("mmbg_video");
        nameList.add("mmcv_android_facequality_model");
        nameList.add("justice_model");
        nameList.add("scan_media");
        return initJSObj(nameList, valueList).toString();
    }

    public JSONArray initJSObj(LinkedList<String> nameList, LinkedList<String> valueList) {
        JSONArray jsonArray = new JSONArray();
        for (String name : nameList) {
            JSONObject jsonObject = new JSONObject();
            for (String value : valueList) {
                if (value.equals("name")) jsonObject.put(value, name);
                if (value.equals("version")) jsonObject.put(value, 0);
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }


    public String getMapId() {
        long tmpLong = 100000;
        long tmoTime = System.currentTimeMillis() % 1000000;
        if (tmoTime < tmpLong) {
            tmoTime += tmpLong;
        }
        return tmoTime + "" + ((((int) (Math.random() * 9000.0d))) + 1000);
    }


    public String getAlias(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        return dataObject.getString("guestid");
    }


    public String getToken(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        String mzip = dataObject.getString("mzip");
        String token = null;
        try {
            token = decrypt(mzip, random);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObjectToken = JSONObject.fromObject(token);
        token = jsonObjectToken.getString("token");

        return token;
    }

    public String getAppsr() {
        return StringUtil.getInstance().getMD5(appId);
    }

    public String clientLog_upload(int isFirst, ArrayList<String> arrayList, String msg, long currentTime,String lat,String lng) {
        com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject(true);
        jsonObject1.put("net", 1);
        jsonObject1.put("carrier", "");
        jsonObject1.put("os", "Android");
        jsonObject1.put("i_v", "100071");
        jsonObject1.put("o_v", "1100001055");
        jsonObject1.put("rom", "8.1.0");
        jsonObject1.put("brand", "google");
        jsonObject1.put("mobile_type", "Pixel");
        jsonObject1.put("channel", "14");
        jsonObject1.put("userid", "668700100");
        jsonObject1.put("lat", lat);
        jsonObject1.put("lng", lng);
        jsonObject1.put("log_t", System.currentTimeMillis());
        com.alibaba.fastjson.JSONArray jsonArray_list = new com.alibaba.fastjson.JSONArray();

        com.alibaba.fastjson.JSONObject jsonObject_list_content = new com.alibaba.fastjson.JSONObject(true);
        jsonObject_list_content.put("bz_1", "momo-web");
        jsonObject_list_content.put("bz_2", "1000568");
        jsonObject_list_content.put("bz_3", "ERR_2.1");
        com.alibaba.fastjson.JSONObject json_body = new com.alibaba.fastjson.JSONObject(true);
        json_body.put("logID", "c5e517ae-7315-4940-9560-62bfb35547c0");
        json_body.put("url", "https://s.immomo.com/fep/momo/fep-web/activity-layer/index.html?_bid=1000568&_wk=1&name=fast_sign&profilemomoid=668700100");
        json_body.put("createTime", currentTime);
        json_body.put("uploadTime", currentTime + 50);
        json_body.put("isFirst", isFirst);
        json_body.put("isOfflined", 1);
        com.alibaba.fastjson.JSONArray json_body_Arry = new com.alibaba.fastjson.JSONArray();
        /*String str1="1608174928000|native|log|[LC]onPageFinished&&&https://s.immomo.com/fep/momo/fep-web/activity-layer/index.html?_bid=1000568&_wk=1&name=fast_sign&profilemomoid=668700100&_offline=1";
        String str2="1608174928054|native|ERR_2.1|[LC]onReceivedError2&&&-1&&&net::ERR_FAILED&&&https://s.immomo.com/favicon.ico";*/
        for (String str : arrayList) {
            json_body_Arry.add(str);
        }
        json_body.put("listArray", json_body_Arry);
        com.alibaba.fastjson.JSONObject extra = new com.alibaba.fastjson.JSONObject(true);
        extra.put("msg", msg);
        extra.put("offlineVersion", "1.7.0.0");
        extra.put("ua", "Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM4.171019.021.P1; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36 momoKit/1.0.0 momoWebView/8.21 android/5129(Pixel;android 8.1.0;zh_CN;14;netType/1)");
        extra.put("useDns", false);
        extra.put("fep", "none");
        jsonObject_list_content.put("json_body", json_body);
        jsonObject_list_content.put("extra", extra);
        jsonArray_list.add(jsonObject_list_content);
        jsonObject1.put("list", jsonArray_list);

        return jsonObject1.toJSONString();
    }


    //获取微秒的时间戳
    public String getmicTime() {
        Long cutime = System.currentTimeMillis() * 1000; // 微秒
        Long nanoTime = System.nanoTime(); // 纳秒
        Long micTime = cutime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
        ///return cutime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
        String micTimeStr = micTime.toString();
        StringBuilder micTimeBuilder = new StringBuilder(micTimeStr);
        //micTimeBuilder.insert(13,".");
        return micTimeBuilder.toString();
    }

    public String calc_end_time(String start_time,int radom) {   //初始时间+随机值
        java.util.Random random = new java.util.Random();
        Long st_tm = parseLong(start_time);
        Long time_end = st_tm + random.nextInt(radom);
        return time_end.toString();
    }

    public String conversion_time(String time) {  //转化成小数格式
        StringBuilder new_time = new StringBuilder(time);
        new_time.insert(13, ".");
        return new_time.toString();
    }

    public String construct_log_parameter(Map<String, String> mapInfo) {
        Set keys = mapInfo.keySet();
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);

        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String value = mapInfo.get(key);
                jsonObject.put(key,value);
            }
        }
        return jsonObject.toJSONString();
    }


    //获取注册账号时的token
    public String get_reg_token(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        String token = dataObject.getString("token");
        return token;
    }

}