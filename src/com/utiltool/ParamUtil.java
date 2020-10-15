package com.utiltool;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
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


    public String getXkv(String key) {
        return StringUtil.getInstance().getMD5(key).substring(0,8);
    }

    public String getCk(String localPublicKey) throws Exception {
        byte[] lpKey = Base64.getDecoder().decode(localPublicKey);
        byte[] iu0WKHFyBytes = new byte[16];
        System.arraycopy("Iu0WKHFy".getBytes(),0,iu0WKHFyBytes,0,8);
        byte[] bytesCk= CryptUtil.getInstance().momoEncode(lpKey,iu0WKHFyBytes);
        String ckStr = URLEncoder.encode(Base64.getEncoder().encodeToString(bytesCk),"UTF-8");
        return ckStr;
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
        return tmoTime + "" + ((((int)(Math.random() * 9000.0d))) + 1000);
    }

    //temporary
    public Map<String,String> header(String session)  {
        String key = "06O9U/45g0Otam1C";
        String mzip= "AgPmbNq1AHXME7p3i8iVq9em8dLNvEE3HKWXR3fENIICeH3TJEyDzLTjdP%2B89YMlDd9iFUfL%2FvS85ZUvojF3bTv3duC7%2B4busaf%2F5upvOGo7Bkn6IohxHopw%2B9oNIlhqlYxd8LaoGo2Gqs4RfJzp2cxhABlK%2BMss6dXSTDK1F%2BZfn1qGBWNULWq%2B%2BDCav82IwuVqA7U2ZmfGV%2BIMo1N3DOLaUaeOMF4vPKWyHu2AyFQ6JhyU6x6bUqZ9vRHDX5lGaHSX2J%2FNPi4x1a1JlpAp8IpEhjOCU0LFKn65smA6rwsch7XS1fwMfLZ4DnyYNgPXIb7a5W%2B61AeF3VkD3zNHgf%2F%2FDa%2FV%2FySz39%2BQNRK%2Bd5V3%2FLYB%2BiqPzUaCgGZbBmJhxe06d1rWo2SsHMTwp6oWqnQ1AsGPa%2B6L7KmSfLa%2BxBN3fkR%2FH8Pv00tIYoq%2FIzWCE9jKPHltiUjNmyrKpnnJY2RdwmlCNDF%2BINq%2F00wmSIrhwn5lwFdww8bYnX%2Fif%2ByfRH1CaNYokV62begFrHPVVU6uUw2u1o9OkurCRMvKaZZ4mIPaoZ7Fc5oFBeYDBCDkbm4nzslxsrdZtoNqEbEoj3cyiqPfN%2BuMLCXhteaHmleTLwvr%2B1sE%2BacCjRXxMfj4ipcfcgGXFHquFvKIC0F%2FDK04ibPdmP703R7F2mrTsi%2F2MUF0nDajMvMhJlmEwzARQCiPQ7xMndEAS2Bltfox8AjnQ0viCnMrGUKUtNs6l4lHck%2FVZYCG%2FlO4w03eZ6ZeYyVda3NXcrLz3gibQfPS3pt9EHLrHOcNcTmecRg28TE8RsLHinNasXe2F8omqCZzqu%2FREQvNvpXv%2FED9l2Xi2bKJ6X8c5GmrbEv3iToTv3VhlgQ4M%2BiPG8QdD7Z5aJK35f80%2F66BFH2HlfW6z%2B2n%2BrQOFDAGYYV3BgADSHgVfFvKb%2FN7F03o5yE5j8PxDQdA2%2B55T3VBt9JTe%2FhEasjoyRfN4tyLhpq3SH37sL3Qmd4xfJvZW1VIoezzUMFgE5xzmPkGik14oONbQljx%2B4ZWg0gV2VBSETqjghFsfVTheWlwX8OLTEZNMPW%2BA8X5b4SyT6WwKXj48fqL21Br10ZDMvKnAGxLv9AnVAo%2FNWZt5zH%2BXzapeyKTkUzxFTHReiDshiHy3dmbLG8roZnRRY9V9vb1DQJByGhFSr56x2F%2F67ePTcCarKxSE8dmsTJ2%2B%2BRCuvNYh387Tk96g%2FA0D0yMuBna1IHV02gdH4mY9eTAfZ83xE3v0brIKpnZxpvp3hsu6tcJKteFaiVgWpx0YcW9RP5q%2B2pBZ%2BbmyhFpNWxiH1V8dKdsClKasKIPr80I7TzVIcpBeRY7vUPa5cAPJXFuhCimyyyh%2B566YKIeXkbHD02CV2GpS6vHC2%2BplF9p1T%2FeltOpOC%2B%2F30S1QD3SITaN0XQs9%2FM6lgs7kRj2UMxMCrV4Vj4Hm4i%2Fce1iYEUzSWkfQgp1Ju%2BJxjtJQMT%2FZ5cCONAy6tE90Ixa4N%2F7Ff6blcVNtPMyd6hLVr8OWnN6M%2FY49%2BVWXYCzX%2BAq44OH%2FYHn0MG9obPTsE%2BNf0ASJ1gFPFtk8cjyFUNFbBXy1Gr3paixVgIaNB42FAHub8jzR0B79Bsxn23HUw7Z75nc3KldlhR1NqZzeuFE1JJHRmxjmjRh77X3BnkcUYCMw%2FuZRSPnKEJk3Nyl0f224g%2BxDEKo6f1h6fNxQJqq";
        byte[] jsonStr = null;
        try {
            jsonStr = Base64.getDecoder().decode(URLDecoder.decode(mzip));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String X_Span_Id="0";
        String X_LV="1";
        String X_KV="073cbcd2";
//        String X_SIGN="SKadBfd4x/QSD353Hy9hoynKNL0=";
        Map<String, String> infoMap = new LinkedHashMap<>();
        infoMap.put("X-Span-Id","0");
        infoMap.put("X-LV", X_LV);
        infoMap.put("X-KV", X_KV);
        infoMap.put("X-Trace-Id","0667B2A4-86A4-46B7-83EA-3A432B33543D");
        String X_Trace_Id="0667B2A4-86A4-46B7-83EA-3A432B33543D";
        String Connection="Keep-Alive";
        String Charset="UTF-8";
        String cookie="SESSIONID=A5A170C9-1034-2BD3-0D9E-E99785F1A1F0_G";
        String Accept_Language="zh-CN";
        String User_Agent="MomoChat/8.21 Android/5129 (Pixel; Android 8.1.0; Gapps 1; en_GB; 14; Google)";
        String MultiUA="MomoChat/8.21.18_fast Android/100071 (Pixel; Android 8.1.0; en_GB; 14; Google; fast)";
        String X_SIGN= getXsign(jsonStr,infoMap, key,MultiUA);
        String Content_Type="application/x-www-form-urlencoded";
        String Content_Length="2014";
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

        String body="code_version=2&mzip=AgPmbNq1AHXME7p3i8iVq9em8dLNvEE3HKWXR3fENIICeH3TJEyDzLTjdP%2B89YMlDd9iFUfL%2FvS85ZUvojF3bTv3duC7%2B4busaf%2F5upvOGo7Bkn6IohxHopw%2B9oNIlhqlYxd8LaoGo2Gqs4RfJzp2cxhABlK%2BMss6dXSTDK1F%2BZfn1qGBWNULWq%2B%2BDCav82IwuVqA7U2ZmfGV%2BIMo1N3DOLaUaeOMF4vPKWyHu2AyFQ6JhyU6x6bUqZ9vRHDX5lGaHSX2J%2FNPi4x1a1JlpAp8IpEhjOCU0LFKn65smA6rwsch7XS1fwMfLZ4DnyYNgPXIb7a5W%2B61AeF3VkD3zNHgf%2F%2FDa%2FV%2FySz39%2BQNRK%2Bd5V3%2FLYB%2BiqPzUaCgGZbBmJhxe06d1rWo2SsHMTwp6oWqnQ1AsGPa%2B6L7KmSfLa%2BxBN3fkR%2FH8Pv00tIYoq%2FIzWCE9jKPHltiUjNmyrKpnnJY2RdwmlCNDF%2BINq%2F00wmSIrhwn5lwFdww8bYnX%2Fif%2ByfRH1CaNYokV62begFrHPVVU6uUw2u1o9OkurCRMvKaZZ4mIPaoZ7Fc5oFBeYDBCDkbm4nzslxsrdZtoNqEbEoj3cyiqPfN%2BuMLCXhteaHmleTLwvr%2B1sE%2BacCjRXxMfj4ipcfcgGXFHquFvKIC0F%2FDK04ibPdmP703R7F2mrTsi%2F2MUF0nDajMvMhJlmEwzARQCiPQ7xMndEAS2Bltfox8AjnQ0viCnMrGUKUtNs6l4lHck%2FVZYCG%2FlO4w03eZ6ZeYyVda3NXcrLz3gibQfPS3pt9EHLrHOcNcTmecRg28TE8RsLHinNasXe2F8omqCZzqu%2FREQvNvpXv%2FED9l2Xi2bKJ6X8c5GmrbEv3iToTv3VhlgQ4M%2BiPG8QdD7Z5aJK35f80%2F66BFH2HlfW6z%2B2n%2BrQOFDAGYYV3BgADSHgVfFvKb%2FN7F03o5yE5j8PxDQdA2%2B55T3VBt9JTe%2FhEasjoyRfN4tyLhpq3SH37sL3Qmd4xfJvZW1VIoezzUMFgE5xzmPkGik14oONbQljx%2B4ZWg0gV2VBSETqjghFsfVTheWlwX8OLTEZNMPW%2BA8X5b4SyT6WwKXj48fqL21Br10ZDMvKnAGxLv9AnVAo%2FNWZt5zH%2BXzapeyKTkUzxFTHReiDshiHy3dmbLG8roZnRRY9V9vb1DQJByGhFSr56x2F%2F67ePTcCarKxSE8dmsTJ2%2B%2BRCuvNYh387Tk96g%2FA0D0yMuBna1IHV02gdH4mY9eTAfZ83xE3v0brIKpnZxpvp3hsu6tcJKteFaiVgWpx0YcW9RP5q%2B2pBZ%2BbmyhFpNWxiH1V8dKdsClKasKIPr80I7TzVIcpBeRY7vUPa5cAPJXFuhCimyyyh%2B566YKIeXkbHD02CV2GpS6vHC2%2BplF9p1T%2FeltOpOC%2B%2F30S1QD3SITaN0XQs9%2FM6lgs7kRj2UMxMCrV4Vj4Hm4i%2Fce1iYEUzSWkfQgp1Ju%2BJxjtJQMT%2FZ5cCONAy6tE90Ixa4N%2F7Ff6blcVNtPMyd6hLVr8OWnN6M%2FY49%2BVWXYCzX%2BAq44OH%2FYHn0MG9obPTsE%2BNf0ASJ1gFPFtk8cjyFUNFbBXy1Gr3paixVgIaNB42FAHub8jzR0B79Bsxn23HUw7Z75nc3KldlhR1NqZzeuFE1JJHRmxjmjRh77X3BnkcUYCMw%2FuZRSPnKEJk3Nyl0f224g%2BxDEKo6f1h6fNxQJqq&X-KV=073cbcd2&map_id=4702975133&ck=AgPmbNq1AGXDDvpp3XJ7KH4ffSUy1LEoJWq35%2Barw6E4afvW07Gev7VMDbJfuVerPV4LShyDO02owPJKr639UNQFOvhHYw8%3D";
        return body;
    }


}
