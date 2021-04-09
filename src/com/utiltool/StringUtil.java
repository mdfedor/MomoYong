package com.utiltool;

import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.zip.GZIPInputStream;

/*
* 字符串处理工具类
* */
public class StringUtil {

    private static StringUtil instance=null;

    public static StringUtil getInstance() {
        if (instance==null)
            instance= new StringUtil();
        return instance;
    }

    /*
    * Encryption and decryption related
    * */
    /*UUID*/
    public String getUuid(int range) {
        char[] array = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String string;
        if(range < 1) {
            string = null;
        }
        else {
            char[] array1 = new char[range];
            Random rnd = new Random();
            int index;
            for(index = 0; index < array1.length; ++index) {
                array1[index] = array[rnd.nextInt(71)];
            }
            string = new String(array1);
        }
        return string;
    }
    /*MD5*/
    public String getMD5(String plainText) {

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


    /*
    * Character conversion processing
    * */

    /*BYTE2HEX*/
    public  String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes) {
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }


    /*
    * Stream processing
    * */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /*Picture read as string data*/
    public String getImageBinary(String path){
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
            ImageStr = new String(b);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ImageStr;
    }

    /*Picture read as byte array*/
    public byte[] getImageByte(String path){

        File f = new File(path);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toHexPrint(byte[] bytes) {
        Formatter formatter = new Formatter();
        int nInt = bytes.length;
        int mInt;
        int count=0;

        for(mInt = 0; mInt < nInt; ++mInt) {
            if(count!=0&&count%16==0){
                formatter.format("\r\n");
            }
            formatter.format("%02X ", Byte.valueOf(bytes[mInt]));
            count++;
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }


    public String toHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        int nInt = bytes.length;
        int mInt;
        for(mInt = 0; mInt < nInt; ++mInt) {
            formatter.format("%02x", Byte.valueOf(bytes[mInt]));
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    /*
     *File processing
     * */

    public static boolean Save(String path,String content){
        File fileName=new File(path);
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("UTF-8"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void savaByteToFile(byte[] data, String path){
        File fileName=new File(path);
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(data);
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static String UnGzip(byte[] bytes) throws IOException {
        //      Log.d(TAG, "UnGzip is doing");
        BufferedReader bufferedReader=null;
        InputStreamReader inputStreamReader = null;
        GZIPInputStream gzipInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        String retStr = null;
        byteArrayInputStream = new ByteArrayInputStream(bytes);
        gzipInputStream = new GZIPInputStream(((InputStream)byteArrayInputStream));
        inputStreamReader = new InputStreamReader(((InputStream)gzipInputStream), "UTF-8");
        bufferedReader = new BufferedReader(((Reader)inputStreamReader));
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            String string = bufferedReader.readLine();
            if(string == null) {
                break;
            }
            stringBuilder.append(string);
        }
        retStr = stringBuilder.toString();
        //       Log.d(TAG, "UnGzip ret is "+retStr);
        return retStr;
    }


    public static byte[] getBytesMzip(String jsonStr,String aesKey) {
        try {
            return CryptUtil.getInstance().momoEncode(jsonStr.getBytes(),aesKey.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMzip(byte[] bytesMzip){
        try {
            return URLEncoder.encode(Base64.getEncoder().encodeToString(bytesMzip),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNoUrlDecodeMzip(byte[] bytesMzip){
        return Base64.getEncoder().encodeToString(bytesMzip);
    }

    /*Read the file according to the specified character set*/
    public static String readToString(String fileName) {
        String encoding = "ISO-8859-1";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static String string2Json(String str){
        String[] arrayPair = str.split(",");
        List<String> listPair=new ArrayList<>();
        List<String> listTmp=new ArrayList<>();
        List<List<String>> ListTmp=new ArrayList<>();
        List<String> jslist=null;
        boolean jsFlag=false;
        for(String jsStr:arrayPair){
            if(jsStr.contains("[{")){
                jsFlag=true;                 //以这个[{ 开始
                if(jsStr.contains("}]")){
                    jsFlag=false;            //有这种字符串"tags":"[{"from":"album_pic"}]"  一个里面既包含[{  也包含}]
                    listTmp.add(jsStr);
                    jslist=ListCopy(listTmp);
                    ListTmp.add(jslist);     //每个[{}]快都放到一个list中
                    listTmp.clear();
                    continue;
                }
                listTmp.add(jsStr);
                continue;
            }else if(jsStr.contains("}]")){
                jsFlag=false;                  //表示}]结束
                listTmp.add(jsStr);
                jslist=ListCopy(listTmp);
                ListTmp.add(jslist);           //每个[{}]快都放到一个list中
                listTmp.clear();
                continue;
            }
            if(jsFlag){                        //jsFlag=true 说明此时的字段还在[{中间
                listTmp.add(jsStr);            //把中间的东西放进去
                continue;
            }
            listPair.add(jsStr);                //jsFlag=false  ,正常存放
        }

        if(ListTmp.size()!=0){                  //存放list的list
            Iterator iter=ListTmp.iterator();
            String strTmp="";
            while (iter.hasNext()){
                List<String> tmp=(List<String>) iter.next();
                Iterator itTmp=tmp.iterator();
                while (itTmp.hasNext()){
                    strTmp+=itTmp.next()+",";
                }
                String newStr=strTmp.substring(0,strTmp.length()-1);
                strTmp="";
                listPair.add(newStr);
            }
        }

        //JSONObject jsonObject = new JSONObject();
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        String key=null;
        String value=null;

        for(int n=0; n < listPair.size(); n++){
            key=listPair.get(n).substring(0,listPair.get(n).indexOf(":"));
            value=listPair.get(n).substring(key.length()+1,listPair.get(n).length());
            jsonObject.put(key,value);
        }
        String jsonStr = jsonObject.toString();
        return jsonStr;
    }


    public static List<String> ListCopy(List<String>psrc){
        List<String> src=psrc;
        List<String> des=new ArrayList<>();
        for (String str:src){
            des.add(str);
        }
        return des;
    }
}
