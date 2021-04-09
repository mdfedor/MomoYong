package com.utiltool;

import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//设备文件账号文件Heard头文件生成  注册账号的时候需要用到
public class fileGenerationUtil {

    private String localPath=null;          //存储文件文件生成路径
    private final String ACCOUNT_INFO="account_info";
    private final String DEVICE_INFO="device_info";
    private final String HEADER_INFO="header_info";


    public String getLocalPath() {
        return localPath;
    }

    public fileGenerationUtil(){
       localPath = this.getClass().getResource("/").getPath()+"/"+"000";    //当前工程out目录位置默认生成文件夹000
    }

    //构造方法生成文件路径，该函数生成相应账号的文件夹，设备文件，账号文件，请求头文件  这是生成一份默认的
    public void generationFile(String jsonInfo){
        if(mkDirectory(localPath)){                           //生成目录
            GenerationDeviceInfo(localPath);                  //设备文件   这个生成的时候,设备信息的构造已经完成
            GenerationAccountInfo(localPath,jsonInfo);        //账号文件  注册的时候默认使用三个值,姓名,生日,性别
            GenerationHeaderInfo(localPath);                  //请求头文件
        }
        System.out.println("正在注册,请勿重复操作...");
    }

    //构造默认的账号文件夹
    private boolean mkDirectory(String path){
        File file=null;
        file=new File(path);
        if(!file.exists()){
            return file.mkdir();
        }else{
            return false;
        }
    }

    //生成设备文件   //构造设备信息在这里构造
    private void GenerationDeviceInfo(String path){
        DeviceInfoUtil deviceInfo=new DeviceInfoUtil();
        DeviceInfoInit(deviceInfo);
        String deviceInfoStr=deviceInfo.DeviceInfo2String();
        WriteStr2File(deviceInfoStr,path+"/"+DEVICE_INFO);
        System.out.println("生成设备文件\r");
    }

    //构造设备信息,实际参数如何获取
    private void DeviceInfoInit(DeviceInfoUtil deviceInfo){
        deviceInfo.setAcc("0");
        deviceInfo.setGapps("1");
        deviceInfo.setBuildnumber("OPM4.171019.021.P1/4820305");
        deviceInfo.setIsRoot("0");
        deviceInfo.setNative_ua("Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM4.171019.021.P1; wv) AppleWebKit/537.36 (KHTML# like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36");
        deviceInfo.setScreen("1080x1794");
        deviceInfo.setDevice_type("android");
        deviceInfo.setImsi("c82874a30ad57ba29ec5ef709e45cceb");
        deviceInfo.setEmu("029f181d6e7ba188885c78462623c37a");
        deviceInfo.setMac("02:00:00:00:00:00");
        deviceInfo.setHw("d8d71f9a499fddc52f8a1d8e201cdc17");
        deviceInfo.setManufacturer("Google");
        deviceInfo.setOsversion_int("27");
        deviceInfo.setUid("6766272a7e000278b21192236b3c3eb1");
        deviceInfo.setRom("8.1.0");
        deviceInfo.setRAMSize("3852816");
        deviceInfo.setBaseBandVersion("8996-130091-1802061512");
        deviceInfo.setCurrent_wifi("8C:53:C3:D2:98:A2");  //这个mac地址不清楚为什么是A2,因为本地路由器的mac地址是 	8C:53:C3:D2:98:A2
        deviceInfo.setMarket_source("14");
        deviceInfo.setModel("Pixel");
        deviceInfo.setEtype("2");
        deviceInfo.setAndroidId("912cd84c01034e24");
        deviceInfo.setLat("112.428718");
        deviceInfo.set_uid_("6766272a7e000278b21192236b3c3eb1");
        deviceInfo.setPhone_type("CDMA");
        deviceInfo.setLng("37.843120");
        deviceInfo.setCpuInfo("0-3");
        deviceInfo.setPhone_netWork("0");
        deviceInfo.setDpp("8b54c9211ea677d3b72f760ea1801d1b");
        deviceInfo.setUtdid("00000000");
        deviceInfo.setMacInfo("02:00:00:00:00:00");
        deviceInfo.setUserAgent("MomoChat/8.21 Android/5129 (Pixel; Android 8.1.0; Gapps 1; zh_CN; 14; Google)");
        deviceInfo.setApksign("4f3a531caff3e37c278659cc78bfaecc");
        deviceInfo.set_net_("wifi");
        deviceInfo.setRouter_mac("8C:53:C3:D2:98:A2"); //同current_wifi 都是当前网络wifi
        deviceInfo.setNetwork_class("wifi");
        deviceInfo.setSerialNumber("FA68W0308543");
        deviceInfo.setSensorNames("G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0");
        deviceInfo.setImei("352531081145847");
        deviceInfo.setUniqueid("02:00:00:00:00:00");
        deviceInfo.setIdfa("6766272a7e000278b21192236b3c3eb1");
        deviceInfo.set_iid("f763497b83ed46d6dae0eb2af3e10aec");
        deviceInfo.set_uidType("androidid");
        deviceInfo.setCode_version("2");
        deviceInfo.setPublic_key("BNTc+loFgCXU8mbnDohv7j0Jv6Hmz7v3DcLwyVLMzQd4a7Yrv2dgfDFnw6SNR7OmAQ==");
        deviceInfo.setAesKey("y4pps+687+hKV8jB3iXpBdIjTGDTaOpUy4pps+687+hKV8jB");
        deviceInfo.setCpuString("0 1593600 307200");
        deviceInfo.setSn("6766272a7e000278b21192236b3c3eb1");
        deviceInfo.setNet("1");
    }


    //生成账号信息 jsonInfo 存储需要初始化的账号信息
    private void GenerationAccountInfo(String path,String jsonAccountInfo){
        JSONObject jsonObject = JSONObject.fromString(jsonAccountInfo);
        String name=jsonObject.getString("name");
        String birthday=jsonObject.getString("birthday");
        String gender=jsonObject.getString("gender");
        AccountInfoUtil accountInfo=new AccountInfoUtil(name,birthday,gender);
        String accountInfoStr=accountInfo.AccountInfo2String();
        WriteStr2File(accountInfoStr,path+"/"+ACCOUNT_INFO);
        System.out.println("生成账号文件\r");
    }

    //生成http请求头的信息  这些参数当手机改变的时候可能会变
    private void GenerationHeaderInfo(String path){
        HeaderInfoUtil headerInfo=new HeaderInfoUtil();
        HeaderInfoInit(headerInfo);
        String headerInfoStr=headerInfo.HeaderInfo2String();
        WriteStr2File(headerInfoStr,path+"/"+HEADER_INFO);
        System.out.println("生成请求头文件\r");
    }

    private void HeaderInfoInit(HeaderInfoUtil headerInfo){
        headerInfo.setX_LV("1");
        headerInfo.setX_Span_Id("0");
        headerInfo.setCharset("UTF-8");
        headerInfo.setConnection("Keep-Alive");
        headerInfo.setUser_Agent("MomoChat/8.21 Android/5129 (Pixel; Android 8.1.0; Gapps 1; zh_CN; 14; Google)");   //类似这些参数会变
        headerInfo.setMultiUA("MomoChat/8.21.18_fast Android/100071 (Pixel; Android 8.1.0; zh_CN; 14; Google; fast)");
        headerInfo.setAccept_Language("zh-CN");
        headerInfo.setHost("api-mini.immomo.com");
        headerInfo.setAccept_Encoding("gzip");
        headerInfo.setContent_Type("application/x-www-form-urlencoded");
        headerInfo.setLogHost("api-log.immomo.com");
    }

    //jsStr写入文件
    private void WriteStr2File(String fileContent,String filePath){
        FileWriter writer;
        try {
            writer = new FileWriter(filePath);
            writer.write(fileContent);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除默认000文件夹的内容
    public void deleteDefaultAccount(){
        deleteAccount(localPath);
    }

    private void deleteSpecifiedAccount(String account){
        deleteAccount(localPath.substring(0,localPath.length()-3)+account);
    }

    //删除指定账号文件夹 这里是当注册账号失败后，把生成的默认账号文件夹进行删除
    private void deleteAccount(String filePath){
        File file=new File(filePath);
        if(!file.exists()){
            System.out.println("目录不存在...");
            return;
        }
        rmDir(filePath);
    }

    private static void rmDir(String dir){
        File file = new File(dir);
        if(file.isDirectory()){       //如果是个目录  ,就遍历文件夹下的所有文件
            File[] files = file.listFiles();
            if (files != null && files.length > 0){   //如果目录下有东西，就遍历这些文件
                for (File fileTmp : files) {
                    if(fileTmp.isDirectory()){         //文件如果仍然是目录,递归遍历
                        rmDir(fileTmp.getPath());
                    } else {
                        fileTmp.delete();             //删除文件
                    }
                }
            }//如果是空目录直接删除，这里不用判断，因为每个文件夹进去必然会有文件
        }//当前路径的文件如果是文件直接删除,也不用判断，账号的必定是文件夹，不会是文件
        if(file.listFiles().length==0){      //如果目录里面为空，则删除文件夹
            file.delete();
        }
    }

    //修改accountInfo中的信息 ,注册完毕后,进行修改jsonAccountInfo 保存注册完后的一些信息
    public void modifyAccountInfo(String jsonAccountInfo){
        JSONObject jsonObject = JSONObject.fromString(jsonAccountInfo);
        String account=jsonObject.getString("account");
        String password=jsonObject.getString("password");
        String countrycode=jsonObject.getString("countrycode");
        String phone=jsonObject.getString("phone");

        AccountInfoUtil accountInfo=new AccountInfoUtil();
        accountInfo.setAccount(account);
        accountInfo.setPassword(password);
        accountInfo.setCountrycode(countrycode);
        accountInfo.setPhone(phone);
        String accountInfoStr=accountInfo.AccountInfo2String();
        WriteStr2File(accountInfoStr,localPath.substring(0,localPath.length()-3)+account+"/"+ACCOUNT_INFO);
    }

    //修该文件夹的名字为注册成功账号的名字 这个是最后一步
    public void modifyFilletName(String accountName){
        File file= new File(this.localPath);//E:\gitRepository\MomoYong\out\production\MomoYong\832031493
        String newPath=localPath.substring(0,localPath.length()-3)+accountName;
        file.renameTo(new File(newPath));
    }


}
