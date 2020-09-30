package com.utiltool;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/*
* http request parameters获取请求参数
* */
public class ParamUtil {

    private static ParamUtil instance=null;

    public static ParamUtil getInstance() {
        if (instance==null)
            instance= new ParamUtil();
        return instance;
    }

    public String getXsign(byte[] jsonBytes, Map<String, String> pMap, String str,String userAgent) {
        int nInt = 0;
        if(isNull(str)) {
            return null;
        }
        byte[] strBytes = str.getBytes();
        if(strBytes.length < 8) {
            return null;
        }
        byte[] encBytes = isUseang(pMap) ? userAgent.getBytes(Charset.forName("UTF-8")) : "".getBytes(Charset.forName("UTF-8"));

        if(jsonBytes != null) {
            try {
                byte[] totalBytes = new byte[jsonBytes.length + encBytes.length];
                int mInt;
                for(mInt = 0; mInt < encBytes.length; ++mInt) {
                    totalBytes[mInt] = encBytes[mInt];
                }
                while(nInt < jsonBytes.length) {
                    totalBytes[encBytes.length + nInt] = jsonBytes[nInt];
                    ++nInt;
                }

                return sign(totalBytes,strBytes);
            }
            catch(Exception e) {
                return null;
            }
        }
        return null;
    }

    public String sign(byte[] user,byte[] key){
        byte[] total=new byte[user.length+8];
        System.arraycopy(user,0,total,0,user.length);
        System.arraycopy(key,0,total,user.length,8);
        byte[] hash= CryptUtil.Sha1(total);
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
        retString =StringUtil.toHex(digest.digest());
        return retString;
    }


    public String getXkv(String Key) {
        String key_base64=Base64.getEncoder().encodeToString(Key.getBytes());
        return StringUtil.getInstance().getMD5(key_base64).substring(0,8);
    }

    public String getCk(String localPublicKey) throws Exception {
        byte[] lpKey = Base64.getDecoder().decode(localPublicKey.getBytes());
        byte[] iu0WKHFyBytes = new byte[16];
        System.arraycopy("Iu0WKHFy".getBytes(),0,iu0WKHFyBytes,0,8);
        String ckString= CryptUtil.getInstance().momoEncode(lpKey,iu0WKHFyBytes);
        //       System.out.print(ckString);
        return ckString;
    }

    public String getSession(String json) throws JSONException {
        JSONObject jsonObject=JSONObject.fromObject(json);
        JSONObject dataObject=jsonObject.getJSONObject("data");
        return dataObject.getString("session");
    }

    public String getMapId() {
        long tmpLong = 100000;
        long tmoTime = System.currentTimeMillis() % 1000000;
        if(tmoTime < tmpLong) {
            tmoTime += tmpLong;
        }
        return tmoTime + "" + ((((int)(Math.random() * 9000))) + 1000);
    }

    //temporary
    public Map<String,String> header(String session){
        String X_Span_Id="0";
        String X_LV="1";
        String X_KV="723adeb2";
        String X_SIGN="swJa+mc6BbGO48MeisIefXOutdk=";
        String X_Trace_Id="ECDBDFB8-0782-4A48-940C-5FDAEBF5F44F";
        String Connection="Keep-Alive";
        String Charset="UTF-8";
        String cookie="SESSIONID=A5A170C9-1034-2BD3-0D9E-E99785F1A1F0_G";
        String Accept_Language="zh-CN";
        String User_Agent="MomoChat/8.21 Android/5129 (Pixel; Android 8.1.0; Gapps 1; en_GB; 14; Google)";
        String MultiUA="MomoChat/8.21.18_fast Android/100071 (Pixel; Android 8.1.0; en_GB; 14; Google; fast)";
        String Content_Type="application/x-www-form-urlencoded";
        String Content_Length="1994";
        String Host="api-mini.immomo.com";
        String Accept_Encoding="gzip";
        Map<String,String> header=new HashMap<>();
        header.put("X-Span-Id",X_Span_Id);
        header.put("X-LV",X_LV);
        header.put("X-KV",X_KV);
        header.put("X-SIGN",X_SIGN);
        header.put("X-Trace-Id",X_Trace_Id);
        header.put("Connection",Connection);
        header.put("Charset",Charset);
        header.put("cookie",session);
        header.put("Accept-Language",Accept_Language);
        header.put("User-Agent",User_Agent);
        header.put("MultiUA",MultiUA);
        header.put("Content-Type",Content_Type);
        header.put("Content-Length",Content_Length);
        header.put("Host",Host);
        header.put("Accept-Encoding",Accept_Encoding);
        return header;
    }

    public String body(){

        String body="code_version=2&mzip=AgMPGOAMAH1r5JsT8vYVtDUhWzZU8SRAeKiWIVSYG4p%2FXjX64HwcJdYZ7nGwRpMMzZmHZ8Zy1eOxRA1u3TDDoCF4MlBfBcowHc08HC79Tj2BkeFX1i3In6kyXk3D1D%2BgNIDswsj%2BEvrX7JXO3MHfALl6PB7adp2YViejwGRaeKMcmpxy%2FJZK2pUZK02K0r7%2B3po4zZAmDWrTT0kbOQxS6jYKNG%2F%2BZY29xgynSnPmgb8kLTMtQSPBURYLRLAxX17gvi82Vhs64U6vExraJCAt%2BxN%2BRhD0CDJFoAghmXSas5lqp%2BiWa3xCiHVIVS3U6qftJmFWN2u%2FTItVrKJPMnsZTV9FYbxzXErUR6oHpnQZtVULxHlOtzy9pD%2Bov4p8%2BHl3riEdKLEzGCtk%2B2vhTVhLNKJS%2FKkcoUhdqiYoEIsD8IFkJs%2FeQzv65kImOOQR8Lma0PZEp9O4r%2Fnrm%2Bo55sSFUq86IXK3heZFg%2BAaP%2FthcovCigBt7QUPnuFq1mDa1vRUyn3Xl09ISOeEdAspPqqlsqDBGZDm%2F0Qv3xhfXzbz1igiTzo98q8Dq08Pw88HGs77MAAH3FJ2iVtEklQ4f59teqXOa%2B6IESImgtWAdDWLb1LMGtQBIJDGYfahWk2BebuC7JO1zkeNTIRrh6EPZwtKn5BtSnwO2VB3Ax7qQ7fCOd%2FL2Sgqt1KAV14YfbYPnAAZrFil%2BQKC5nI9tYlmhtn6K%2FrTO8cB7YKn1dsr%2BTQBGgwSyZMR4XMx%2FuRe%2Fr%2FQFSiHK4got8L4U4XJ%2BoF8LlSgbftGjE%2FJZJ2%2FgJGGR1FtPaJny3O1nVs8XVbxBTGdMEYKOrDdP%2FC2oFYB9PcNwBlmUM4dPZmvKFTz5%2B1OYVuEk5cFBhb4VAv6OogpwYLngikbBAVpDsQa%2FeSjmz%2BqmiTtPOYJXOhgPsngrRn94FMADIjU%2F81T29RnKiRSIrvQIl38JK63B4GzLBT2Ig4dvV8FwPVXC57%2BsTl7oaf%2BRAc9mMN9eRJjTUcUJTwTTpAyy8kg22N07aMm5GzOKlPZa4oI38hLDw5VJAyc4%2Bh3UZzJk%2FsNV%2FtB2VyqY3kAhfi2RKeIqj4vEDuLKaxEL8r5JAArSP7gw0P7z8s%2BwsuO93Aw7YZtlBaMv5sKMQxXLgrnHPpO6iajBZ%2F%2BejKmS0bACL0py1paLhN%2BWhxGxTsVFqGRyJvjrZKtBYKwsnLpvuNDpT4xfpyujWsq8O4CSt4bV5VIlKvAIzKny%2B4nWMbG2eT7gh8IG%2FJ6h5c0z5AEY0GLgijOvdmCccTS7vXfwbhA8DVAwhMdUwkHA8%2BNXrFX65KKpAAjtlcqWoycNy06r7hIbxbwKB7azjzyevC3qUnkqoLJEBaBsEhh4Rhl5DG1rg686kCJ0NcNTtnLbGpAbkkWEgk5CulyYCG6hrVKZbXJ%2Byy5g6%2BOBrUK9VP8Ul8mbC6vqBRJfri1olPGr7%2BV3vmspV3ecTWGvc2mBLzhy6eRuOLnY2QBhVOpg0hw0xNzfcLS149qZjoHnwSJvFQ2%2BgKh%2FYcjE8kUVPAMq%2ButzdztQ%2FKFaXnCvxTUOF73uLELURPfwpNs0u83Sm2m5Ym0KlRb4nRDCSn5bbc2xbBVof9gxvj39wfGB1J%2BqDRCsKfYyvH7rYotmu9PnsPRyz4zt7TfLH5ALKomZ65qxVTCRjXKlWnq7D7Y4YhwYoL%2BaC%2B4l%2BT0OyVpLR8zIvHd&X-KV=723adeb2&map_id=1412585629&ck=AgMPGOAMABgwJqhZe6dtbdI%2FRuGMeumJLYixx44tB9MJsmHOBya70Po064TM6BVdvwTx%2BnATo3ACJQmaC0d8KxBDGuwzX6s%3D";
        return body;
    }


}
