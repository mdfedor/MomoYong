package com.momoyong;

import com.utiltool.CryptUtil;
import net.sf.json.JSONObject;

import javax.sql.rowset.spi.SyncResolver;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Test {

    public static String getMD5(String plainText) {

        byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 error ??");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16????????
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {

        //AgPmbNq1AGXDDvpp3XJ7KH4ffSUy1LEoJWq35%2Barw6E4afvW07Gev7VMDbJfuVerPV4LShyDO02owPJKr639UNQFOvhHYw8%3D

      /*  String ck="AgPmbNq1AGXDDvpp3XJ7KH4ffSUy1LEoJWq35%2Barw6E4afvW07Gev7VMDbJfuVerPV4LShyDO02owPJKr639UNQFOvhHYw8%3D";
        String key="Iu0WKHFy";
        byte[]  keybytes=new byte[16];
        System.arraycopy(key.getBytes(),0,keybytes,0,8);
        String urldecodeck=null;
        try {
            urldecodeck=URLDecoder.decode(ck,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String decodeck=CryptUtil.getInstance().momoDecode(Base64.getDecoder().decode(urldecodeck),keybytes);
            System.out.println(decodeck);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
      //decodeTest();


       /* String pk = "BLMZjor4q/4vLvfyBAca8eT2KwinKnvckvZQOUwfmzox+6zcyr8R6dQz/93Owz8KKA==";
        String md5 = getMD5(pk);
        System.out.println(md5);*/
//        decodeTest();
//        "AgOTFc8GAO/7wmlh09kI/EhCugjfP6h9UDPSaT6ccrDdz4Oda9pKNAHD6djyt02sSW/pq+znC2C17UbVbNO14RkJKqtk/bIA";
//         AgOTFc8GAO/7wmlh09kI/EhCugjfP6h9UDPSaT6ccrDdz4Oda9pKNAHD6djyt02sSW/pq+znC2C17UbVbNO14RkJKqtk/bI=

        jasontest();
    }


    public static void decodeTest(){

        String data="AgOzte+mAHLxyo71H4izmiC6/+ALKHaG4eadjdL5RtToIUZOzTJ8s29ihUpBdsTH57sV++UQ4aiiKWpjcgg2PNFPdG/NNQZUAtS6c++PLqb8vEkKgxPb5Bq07J+HwTJ0shruncbNVUFhsGVoqg6JlEcnpej3ogmD3XP0wnmEo8EkueFEVtup4ItJ3F1e+icHKdo3q084Uoj+dYw66BER0kk2z23SrkIh0o/nsYRcxOrIVa3uW/47PiSDoO0mXTMJiVm5yp0Ho8PlYbw14m3YlCWgg0npv7lgTxPTwDbe/+9OVcXl+5tQ1T5lZFv8HXGWty1R/3uqeljVRBthgdPTZRLQTJLk6go=";
        String encodData="{\"gapps\":\"1\",\"acc\":\"0\",\"Serialno\":\"\",\"mmuid\":\"\",\"isRoot\":\"0\",\"screen\":\"1080x1794\",\"device_type\":\"android\",\"hw\":\"d8d71f9a499fddc52f8a1d8e201cdc17\",\"osversion_int\":\"27\",\"RAMSize\":\"3852816\",\"password\":\"0f5c5718c5db8c341f8efbc9b0aec20d\",\"current_wifi\":\"02:00:00:00:00:00\",\"model\":\"Pixel\",\"androidId\":\"912cd84c01034e24\",\"lat\":\"0\",\"_uid_\":\"6766272a7e000278b21192236b3c3eb1\",\"phone_type\":\"CDMA\",\"lng\":\"0\",\"CpuInfo\":\"0-3\",\"MacInfo\":\"02:00:00:00:00:00\",\"utdid\":\"00000000\",\"_iid\":\"f763497b83ed46d6dae0eb2af3e10aec\",\"version\":\"100071\",\"apksign\":\"4f3a531caff3e37c278659cc78bfaecc\",\"_net_\":\"wifi\",\"router_mac\":\"02:00:00:00:00:00\",\"KernelVersion\":\"\",\"network_class\":\"wifi\",\"SerialNumber\":\"FA68W0308543\",\"sensorNames\":\"G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0\",\"buildnumber\":\"OPM4.171019.021.P1\\/4820305\",\"BootSerialno\":\"\",\"imsi\":\"c82874a30ad57ba29ec5ef709e45cceb\",\"emu\":\"029f181d6e7ba188885c78462623c37a\",\"mac\":\"02:00:00:00:00:00\",\"manufacturer\":\"Google\",\"rom\":\"8.1.0\",\"uid\":\"6766272a7e000278b21192236b3c3eb1\",\"BaseBandVersion\":\"8996-130091-1802061512\",\"market_source\":\"14\",\"etype\":\"2\",\"oaid\":\"\",\"phone_netWork\":\"7\",\"dpp\":\"8b54c9211ea677d3b72f760ea1801d1b\",\"bindSource\":\"bind_source_new_login\",\"_uidType\":\"androidid\",\"imei\":\"35253108114584\",\"account\":\"668700100\"}";
        String key="euOsDFQM6MzRqavpctN7hmrowD59dPY8euOsDFQM6MzRqavp".substring(0,16);

        try {
//            String ret=CryptUtil.getInstance().momoEncode(encodData.getBytes(),key.getBytes());
//            System.out.println(ret);

            String decodeRet=CryptUtil.getInstance().momoDecode(Base64.getDecoder().decode(data),key.getBytes());
            System.out.println(decodeRet);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void jasontest(){
        String bodyStr= "{\"share_to\":\"\",\"favor_type\":\"0\",\"_iid\":\"f763497b83ed46d6dae0eb2af3e10aec\",\"favor_id\":\"\",\"content\":\"[{\\\"text\\\":\\\"gghshjshsshxhdhhshhshdszhhshzhzhzhzhshshzhhzzhjzhhzhzhsshhshzh\\\",\\\"type\\\":\\\"1\\\"}]\",\"forward_origin_feedid\":\"\",\"_net_\":\"wifi\",\"_uid_\":\"6766272a7e000278b21192236b3c3eb1\"}";
                       //{"\"favor_type\"":"0","\"_uid_\"":"\"6766272a7e000278b21192236b3c3eb1\"}","\"favor_id\"":"","\\\"type\\\"":"\\\"1\\\"}]\"","\"forward_origin_feedid\"":"","\"_iid\"":"f763497b83ed46d6dae0eb2af3e10aec","{\"share_to\"":"","\"content\"":"[{\\\"text\\\":\\\"gghshjshsshxhdhhshhshdszhhshzhzhzhzhshshzhhzzhjzhhzhzhsshhshzh\\","\"_net_\"":"wifi"}
        string2Json(bodyStr);
        
    }

    public static String string2Json(String str){
        String[] arrayPair = str.split(",");
        List<String> listPair=new ArrayList<>();
        List<String> listTmp=new ArrayList<>();
        for(String jsStr:arrayPair){
            if(jsStr.contains("[{")||jsStr.contains("}]")){
                listTmp.add(jsStr);
                continue;
            }
            listPair.add(jsStr);
        }
        Iterator iter=listTmp.iterator();
        String strTmp="";
        int count=0;
        while (iter.hasNext()){
            if(count%2==0){
                strTmp+=iter.next()+",";
            }else
            {
                strTmp+=iter.next();
            }
            count++;
        }
        listPair.add(strTmp);

        JSONObject jsonObject = new JSONObject();
        String key=null;
        String value=null;

        for(int n=0; n < listPair.size(); n++){
            key=listPair.get(n).substring(0,arrayPair[n].indexOf(":"));
            value=listPair.get(n).substring(key.length()+1,arrayPair[n].length());
            jsonObject.put(key,value);
        }
        String jsonStr = jsonObject.toString();

        return jsonStr;
    }

}
