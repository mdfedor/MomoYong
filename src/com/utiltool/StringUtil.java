package com.utiltool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Random;
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
    static String getImageBinary(String path){
        String imgStr = "";
        File f = new File(path);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            imgStr = new String(bytes);
            return imgStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*Picture read as byte array*/
    static byte[] getImageByte(String path){

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

    public static String toHex(byte[] bytes) {
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

    public static boolean Save(String content,String path,String name)throws Exception{
        File fileName=new File(path+name);
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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

    public static String getMzip(String jsonStr,String aesKey) {
        try {
            String mzip= CryptUtil.getInstance().momoEncode(jsonStr.getBytes(),aesKey.getBytes());
            mzip= URLEncoder.encode(mzip,"UTF-8");
            //mzip = mzip.substring(0, mzip.length() - 3);
            return mzip;
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
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


}