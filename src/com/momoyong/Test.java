package com.momoyong;

import com.utiltool.CryptUtil;
import net.sf.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
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

    }

    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }


    public static void decodeTest(){

        //String[] arr={AgNcavSHAKH/fkFaa3ytdGxl6BtFDb2R9MuMhTtzerdtr5O+57G/S0TP95bWLqdiydRkNktONFS2KebZ1H9WCRDI/gSPryaA7gFUUq2FAAEBnYFoNFcF1HxbrUVX1AV5n2ElLESNAu+rhcanklDrLJzwBnATTOfcMLaKGyQMvYtCtfh99XPvIl93IP1U710+OrrN9aKaZoKTTNdzAzc5HRRt4vlydROPIQD5rPgVWq+q8pWAfeHp6NgMi/XN4b58E4uxiUxo+7kjdzY6WhJUswVchA1EMyV4n8uOVAx/GG+eP1TEsYdtoKyITaD0TdJq9M33CMODrvCPRppAqJbtSjzaYUFIsHnqVYxvLf4+3g9Ja6Lw51EhWSjTKUPulL3OoGO1LIXY217KeOW8KPqaf9YrhaXOdrG4BdIXOeykUpnwdGZy/9zxbXgH4VyhUor5ZeBM/fa7mGeRlWoMo7h4gCnsqK/kIdWsaCyyT+z5INtFEY0o0zbOighlQyV825g+oiSupo333icqLNjP8eDkKtPmP4K/Aiijw6bH0IWlPhy3/hiLiHm5lXR1JQjwigeci+iADDHZpStpKIwu5yyhtBx9fdxFsLhyQz6BXqAIWcans6uUd2Zdtzph8aF6rjyMTuL0nYjcdYMA8en+j7C+uAGflyRak43EPFb9ladUE7nNCZFqXOBbXIZ/8xVZP1yxzplUAZFg/nUagvAKMZZ8KkxPfxaupyIOcUA6wMeA2nR92zLPbtAlAbR4iOGMNy7vTc7rttpk7Fh9cyjMldaNu3lxt8dcbyP7H6sob0ZzTaNZOKmW3OgSzytZtGV3Icfog9oHEhxINcJEZbTGE6e+HsWrvp2ue4Co2omT2cSwmMu69v/s1sF0mT6jE7cED+/j0FiN1Hfl2HiLgiSMqvNX0lC8pTOJDTqsHr9TKUvWIYVP1peEC49jadNF+63C56B/fvBjsOYYZvArW9BBV0KMJEnYjNbQ1MF/kp4ydj2XYqvgcYvU9AZDUNCbclsaQ5TWELMCaMo6Kgaq2KhMeWyKdKEwws+0UAYT3TpTUbPXJN+IzBCKDJnsInLbURtos6XX5QUe9Q4d3dhz+zycWWjR2U0/4pp+J/om4Qhls4z66EXDEjF+vyEXeOYEJcG/7adL/bAMyOjUGCUiY9ZlW8vWcaWwymApoMnQW6BVlljt3fDrvbMypgX1f8poAKU6bHOpyKX8CFsabSxVCa7QePr94She0TDnB1DRDH0oo4S/v78WLuTATuDEbAfQeYAo6edqFOyjpUnmCwSp+pi8QX/QUlpgt5Brw7ePpcIUhQ1bbbLcJ2f8njW6NGy4L7RiP4fpUJHWBdyZDkDuTOeS9pUvJmUVMcHVG6BXo9vSSdtRJxvugEfu++VGjLpdABSe4csjfsywDqzVqiSEwF1HbDmVRjAgSDFqbOlQdA4ZOymG7UBAFG6KpjiB};

        String data="AgMI5sy/AKAIrEWJQpg0zSYjoKM0miVOE4d+XqTps4+D174wqRlxEaBlVTflt6uZ7UN6NjgAQlKpOl3edn9CkagYsgEh6POEX7W9ebqrTcTq2koc8OqJN1NgEou6WruRO79F+f/BZGcLhKRsYZ6LcHYmPbbJBRGyAzHMbiAeQEz5ChhsdWcd7IvQ3UNU+mxvPbLzlbgkrU2agOrANrsPXq7xKJng9XuGvOp8GKaUWOo47GWxHCxJVCiu8yjM0AAyr7VQxPZ8HQ==";
        String encodData="{\"gapps\":\"1\",\"acc\":\"0\",\"Serialno\":\"\",\"mmuid\":\"\",\"isRoot\":\"0\",\"screen\":\"1080x1794\",\"device_type\":\"android\",\"hw\":\"d8d71f9a499fddc52f8a1d8e201cdc17\",\"osversion_int\":\"27\",\"RAMSize\":\"3852816\",\"password\":\"0f5c5718c5db8c341f8efbc9b0aec20d\",\"current_wifi\":\"02:00:00:00:00:00\",\"model\":\"Pixel\",\"androidId\":\"912cd84c01034e24\",\"lat\":\"0\",\"_uid_\":\"6766272a7e000278b21192236b3c3eb1\",\"phone_type\":\"CDMA\",\"lng\":\"0\",\"CpuInfo\":\"0-3\",\"MacInfo\":\"02:00:00:00:00:00\",\"utdid\":\"00000000\",\"_iid\":\"f763497b83ed46d6dae0eb2af3e10aec\",\"version\":\"100071\",\"apksign\":\"4f3a531caff3e37c278659cc78bfaecc\",\"_net_\":\"wifi\",\"router_mac\":\"02:00:00:00:00:00\",\"KernelVersion\":\"\",\"network_class\":\"wifi\",\"SerialNumber\":\"FA68W0308543\",\"sensorNames\":\"G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0\",\"buildnumber\":\"OPM4.171019.021.P1\\/4820305\",\"BootSerialno\":\"\",\"imsi\":\"c82874a30ad57ba29ec5ef709e45cceb\",\"emu\":\"029f181d6e7ba188885c78462623c37a\",\"mac\":\"02:00:00:00:00:00\",\"manufacturer\":\"Google\",\"rom\":\"8.1.0\",\"uid\":\"6766272a7e000278b21192236b3c3eb1\",\"BaseBandVersion\":\"8996-130091-1802061512\",\"market_source\":\"14\",\"etype\":\"2\",\"oaid\":\"\",\"phone_netWork\":\"7\",\"dpp\":\"8b54c9211ea677d3b72f760ea1801d1b\",\"bindSource\":\"bind_source_new_login\",\"_uidType\":\"androidid\",\"imei\":\"35253108114584\",\"account\":\"668700100\"}";
        String key="HpynGEn8NY7lCK3i0B0ux3nEGM6NE3cdHpynGEn8NY7lCK3i".substring(0,16);

        try {
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


    public static String getimage(String path){

        File file = new File(path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[(int)file.length()];
        try {
            inputStream.read(b);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ImageStr= null;
        try {
            ImageStr = new String(b,"ISO-8859-1");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ImageStr;

    }

}
