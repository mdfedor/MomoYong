package com.utiltool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class CryptUtil {
    private static CryptUtil instance=null;
    public CryptUtil(){

    }
    public static CryptUtil getInstance() {
        if (instance==null)
            instance= new CryptUtil();
        return instance;
    }

    public byte[] encrypt(byte[] sSrc,  byte[] sKey, byte[] ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        //	return new BASE64Encoder().encode(encrypted);
        return cipher.doFinal(sSrc);
    }


    public byte[] decrypt(byte[] sSrc, byte[] sKey, byte[] ivParameter) throws Exception {
        try {

            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
            System.out.print("\n");

            return cipher.doFinal(sSrc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] ivGenerate(byte[] ivNum) {

        byte[] hash=Sha1(ivNum);
        return Arrays.copyOf(hash, 16);
    }

    public byte[] getIvNum() {
//		int num = 1000 + (int)(Math.random() * (9999-1000+1));
//		return intToByte(num);
        return new byte[]{-40, -83, 81, 41};
    }


    public static byte[] Sha1(byte[] info){

        if(info==null||info.length==0){
            return null;
        }

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(info);
            return mdTemp.digest();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public byte[] intToByte(int val){
        byte[] b = new byte[4];
        b[0] = (byte)(val & 0xff);
        b[1] = (byte)((val >> 8) & 0xff);
        b[2] = (byte)((val >> 16) & 0xff);
        b[3] = (byte)((val >> 24) & 0xff);
        return b;
    }

    public static String UnGzip(byte[] bytes) throws IOException {
        //Log.d(TAG, "UnGzip is doing");
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
        return retStr;
    }


    public byte[] momoEncode(byte[] src,byte[] key) throws Exception {

        byte[] ivNUm=getIvNum();
        byte[] iv=ivGenerate(ivNUm);

        byte[] outBytes= CryptUtil.getInstance().encrypt(src, key, iv);
        byte[] header=new byte[7];
        header[0]=02;
        header[1]=03;

        System.arraycopy(ivNUm, 0, header, 2, 4);
        header[6]=00;
        byte[] total=new byte[outBytes.length+7];
        System.arraycopy(header, 0, total, 0, 7);
        System.arraycopy(outBytes, 0, total, 7, outBytes.length);
        return total;
    }


    public String momoDecode(byte[] src,byte[] key) throws Exception {
        byte[] newDataBytes={};
        byte[] decodeBytes= {};
        byte[] ivNum=new byte[4];
        System.arraycopy(src, 2, ivNum, 0, 4);
        byte[] iv=Sha1(ivNum);
        byte[] ivBytes=new byte[16];
        System.arraycopy(iv, 0, ivBytes, 0, 16);
        byte[] dataBytes=new byte[src.length-7];
        System.arraycopy(src, 7, dataBytes, 0, src.length-7);
        if((dataBytes.length%16)!=0){
            newDataBytes=new byte[(dataBytes.length/16+1)*16];
            System.arraycopy(dataBytes,0,newDataBytes,0,dataBytes.length);
            decodeBytes=decrypt(newDataBytes, key,ivBytes);
        }else{
            decodeBytes=decrypt(dataBytes, key,ivBytes);
        }
        try{
            return UnGzip(decodeBytes);
        }catch (Exception e){
            return new String(decodeBytes);
        }
    }


    public String decodeRespone(byte[] bStr,String aesKey) throws Exception {

        GZIPInputStream gzipInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        byteArrayInputStream = new ByteArrayInputStream(bStr);
        try {
            gzipInputStream = new GZIPInputStream(((InputStream)byteArrayInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(gzipInputStream==null){
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int writeLen;
        byte[] bRespone = new byte[1024];
        while ((writeLen = gzipInputStream.read(bRespone)) != -1) {
            byteArrayOutputStream.write(bRespone, 0, writeLen);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        if(data[0]!=2&&data[1]!=3){
            return new String(data);
        }
        String decodeStr= "";
        try{
            decodeStr= CryptUtil.getInstance().momoDecode(data,aesKey.getBytes());
        }catch (Exception e){
            //return new String(data);
        }
        return decodeStr;
    }

    public String decodeRespone_no_zip(byte[] bStr,String aesKey){
        String decodeStr= "";
        try{
            decodeStr= CryptUtil.getInstance().momoDecode(bStr,aesKey.getBytes());
        }catch (Exception e){
            return new String(bStr);
        }
        return decodeStr;
    }


}
