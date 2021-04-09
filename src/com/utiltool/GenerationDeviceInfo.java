package com.utiltool;

import java.security.SecureRandom;

import static java.lang.Long.parseLong;

//构造生成设备信息
public class GenerationDeviceInfo {

    //构造函数生成固定的,需要变动的字段其它函数生成
    public GenerationDeviceInfo(){
        this.acc="0";
        this.gapps="1";
        this.isRoot="0";
        this.device_type="android";
        this.imsi="unknown";
        this.emu= StringUtil.getInstance().getMD5("android+momo+0");
        this.mac="02:00:00:00:00:00";
        this.current_wifi="02:00:00:00:00:00";
        this.market_source="14";
        this.etype="2";
        this.phone_netWork="0";
        this.utdid="00000000";
        this.MacInfo="02:00:00:00:00:00";
        this._net_="wifi";
        this.router_mac="02:00:00:00:00:00";
        this.network_class="wifi";
        this.uniqueid="02:00:00:00:00:00";
        this._uidType="androidid";
        this.code_version="2";
        this.public_key="BNTc+loFgCXU8mbnDohv7j0Jv6Hmz7v3DcLwyVLMzQd4a7Yrv2dgfDFnw6SNR7OmAQ==";
        this.aesKey="y4pps+687+hKV8jB3iXpBdIjTGDTaOpUy4pps+687+hKV8jB";
        this.net="1";
    }


    //构造上面的字段值   这个函数每调用一次都生成不同的数据？  经纬度手动赋值
    public void ForgedParameters(String lat,String lng,String momoid){
        this.androidId= Generate_androidId();
        this.rom= Generate_rom();
        this.buildnumber= Generate_buildnumber();
        this.native_ua= Generate_native_ua();
        this.screen= Generate_screen();
        this.hw= Generate_hw();
        this.manufacturer= Generate_manufacturer();
        this.osversion_int= Generate_osversion_int(this.rom);
        this.uid= Generate_uid(androidId);
        this.lat=lat;
        this.RAMSize= Generate_RAMSize();
        this.BaseBandVersion= Generate_BaseBandVersion();
        this.model= Generate_model();
        this._uid_=Generate_uid_(androidId);
        this.phone_type=Generate_phone_type();
        this.lng=lng;
        this.CpuInfo=Generate_CpuInfo();
        this.dpp=Generate_dpp(momoid);
        this.userAgent=Generate_userAgent();
        this.apksign=Generate_apksign();
        this.SerialNumber=Generate_SerialNumber();
        this.sensorNames=Generate_sensorNames();
        this.imei=Generate_imei("");
        this.idfa=Generate_idfa(androidId);
        this._iid=Generate_iid();
        this.cpuString=Generate_cpuString();
        this.sn=Generate_sn(androidId);
    }

    //生成指纹
    public String Generate_buildnumber(){

        return null;
    }

    //生成native_ua
    public String Generate_native_ua(){
//Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM4.171019.021.P1; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36
        StringBuilder str=new StringBuilder();
        str.append("Mozilla/5.0 (Linux; ");

        return null;
    }

    //生成分辨率
    public String Generate_screen(){
        return "1080x1794";

    }

    //生成hw
    public String Generate_hw(){
        //IMEI  + 内存大小 + 基带版本 + 屏幕宽 + 屏幕高 + 屏幕密度 + 前两个传感器名字
        //352531081145847+3762+8996-130091-1802061512+1080+1794+2.625+BMI160 accelerometer+BMI160 gyroscope
        return null;
    }

    //生成手机厂商
    public String Generate_manufacturer(){
        return "小米";
    }

    //获取系统版本对应的系统API
    public String Generate_osversion_int(String rom){
        //通过系统版本获取对应API等级
        String[] rom_arry={"8.1","8.13","9","9.0","9.1","10"};
        if(rom_arry[0].equals(rom)||rom_arry[1].equals(rom)){
            return "27";
        }else if(rom_arry[2].equals(rom)||rom_arry[3].equals(rom)||rom_arry[4].equals(rom)){
            return "28";
        }else if(rom_arry[5].equals(rom)){
            return "29";
        }
        return null;
    }

    public String Generate_uid(String androidId){
        return StringUtil.getInstance().getMD5(androidId);
    }

    //系统版本
    public String Generate_rom(){
        String[] rom_arry={"8.1","8.13","9","9.0","9.1","10"};
        int len=rom_arry.length;
        int index=(int)(Math.random() * len);
        return rom_arry[index];
    }

    //获取内存大小
    public String Generate_RAMSize(){


        return null;
    }

    //基带版本
    public String Generate_BaseBandVersion(){

        return null;
    }

    //手机型号
    public String Generate_model(){

        return null;
    }

    //生成安卓id
    public String Generate_androidId(){
        final SecureRandom random = new SecureRandom();
        final String newAndroidIdValue = Long.toHexString(random.nextLong());
        return newAndroidIdValue;
    }

    public String Generate_uid_(String androidId){
        return StringUtil.getInstance().getMD5(androidId);
    }

    //运营商
    public String Generate_phone_type(){

        return null;
    }

    //cpu的核数
    public String Generate_CpuInfo(){

        return null;
    }


    public String Generate_dpp(String momoid){
        //Art*#26个字母中随机的一个  加上陌陌id   然后md5
        String str = "Art*#" + "abcdefghijklmnopqrsuvwxyz".charAt((int)(Math.random() * 25.0d))+momoid;
        return StringUtil.getInstance().getMD5(str);
    }


    public String Generate_userAgent(){

        return null;
    }

    public String Generate_apksign(){

        return null;
    }

    public String Generate_SerialNumber(){

        return null;
    }

    public String Generate_sensorNames(){

        return null;
    }

    public String Generate_imei(String input){
        /*int r1 = 1000000 + new java.util.Random().nextInt(9000000);
        int r2 = 1000000 + new java.util.Random().nextInt(9000000);
        String input = r1 + "" + r2;
        char[] ch = input.toCharArray();
        int a = 0, b = 0;
        for (int i = 0; i < ch.length; i++) {
            int tt = Integer.parseInt(ch[i] + "");
            if (i % 2 == 0) {
                a = a + tt;
            } else {
                int temp = tt * 2;
                b = b + temp / 10 + temp % 10;
            }
        }
        int last = (a + b) % 10;
        if (last == 0) {
            last = 0;
        } else {
            last = 10 - last;
        }
        return input + last;*/
        System.out.println("输入原始imei:"+input);
        String SNR=null;

        SNR=input.substring(8,14);



        input=input.substring(0,8)+String.valueOf(parseLong(SNR)+1);

        char[] ch = input.toCharArray();

        int a = 0, b = 0;
        for (int i = 0; i < ch.length; i++) {
            int tt = Integer.parseInt(ch[i] + "");
            if (i % 2 == 0) {   //奇数位,因为下标是0开始,如果i%2==0,说名这是一个奇数位  0  1  2  3    对应  1  2  3  4
                a = a + tt;
            } else {
                int temp = tt * 2;
                b = b + temp / 10 + temp % 10;
            }
        }

        int last = (a + b) % 10;
        if (last == 0) {
            last = 0;
        } else {
            last = 10 - last;
        }


        return input + last;
    }

    public String Generate_idfa(String androidId){
        return StringUtil.getInstance().getMD5(androidId);
    }

    public String Generate_iid(){

        return null;
    }

    public String Generate_cpuString(){

        return null;
    }

    public String Generate_sn(String androidId){
        return StringUtil.getInstance().getMD5(androidId);
    }




    private String acc="";                 //精确度
    private String gapps="";               //标记一个类是否使用    Class.forName("com.google.android.maps.MapActivity");
    private String buildnumber="";         //手机指纹
    private String isRoot="";              //是否root
    private String native_ua="";           //构造规则暂时未知  native_ua -> Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM4.171019.021.P1; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36
    private String screen="";              //屏幕分辨率
    private String device_type="";         //固定值 android
    private String imsi="";                //先写成unknown
    private String emu="";                 //android+momo+0的MD5值  表示不是模拟器
    private String mac="";                 //物理地址
    private String hw="";                  // ①IMEI + ②内存大小 +③基带版本 + ④屏幕宽 + ⑤屏幕高 + ⑥像素密度dpi() + ⑦两个传感器名字  这个构造不确定是否对
    private String manufacturer="";        //手机厂商
    private String osversion_int="";       //系统版本对应API等级
    private String uid="";                 //androidId的MD5
    private String rom="";                 //系统版本
    private String RAMSize="";             //系统内存大小  /proc/meminfo
    private String BaseBandVersion="";     //基带版本    测试
    private String current_wifi="";        //mac地址
    private String market_source="";       //没找到位置 值为14
    private String model="";               //手机型号
    private String etype="";               //固定值2
    private String androidId="";           //安卓id
    private String lat="";                 //经度  填写实际坐标,当值为0时候写死
    private String _uid_="";               //androidId的MD5
    private String phone_type="";          //运营商
    private String lng="";                 //纬度,同经度构造一样
    private String CpuInfo="";             //cpu的核数    测试
    private String phone_netWork="";       //先写成0  表示未知
    private String dpp="";                 //Art*#26个字母中随机的一个  加上陌陌id   然后md5
    private String utdid="";               //暂时写成固定值  "00000000"
    private String MacInfo="";             //mac地址,但这个地址有时候会是实际物理地址
    private String userAgent="";           //计算x-sign用到  浏览器信息
    private String apksign="";            //没找到位置 暂定
    private String _net_="";              //先写固定值wifi
    private String router_mac="";         //也是路由器mac
    private String network_class="";     //wifi
    private String SerialNumber="";      //序列号
    private String sensorNames="";       //传感器信息，如何去构造某些传感器有还是没有
    private String imei="";              //串号                                                                   0
    private String uniqueid="";          //固定值 02:00:00:00:00:00
    private String idfa="";              //androidId的MD5
    private String _iid="";              //uuid+currentTimeMillis  再求md5
    private String _uidType="";          //androidid
    private String code_version="";      //固定值2        账号密码登陆的时候请求头用
    private String public_key="";        //加密的key
    private String aesKey="";            //aeskey
    private String cpuString="";         //三组数据
    private String sn="";                //androidId的MD5
    private String net="";               //固定值1

    private String Serialno="";        //值是空的
    private String KernelVersion="";   //值是空的
    private String BootSerialno="";    //值是空的
    private String mmuid="";           //值是空的
    private String oaid="";            //值是空的























     /* deviceInfo.setAcc("0");          精确度  填0
        deviceInfo.setGapps("1");        填写固定值1
        deviceInfo.setBuildnumber("OPM4.171019.021.P1/4820305");   找抹机软件生成指纹的方式？
        deviceInfo.setIsRoot("0");        固定值0
        deviceInfo.setNative_ua("Mozilla/5.0 (Linux; Android 8.1.0; Pixel Build/OPM4.171019.021.P1; wv) AppleWebKit/537.36 (KHTML# like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36");  构造规则没确定
        deviceInfo.setScreen("1080x1794");    //跟随抹机软件
        deviceInfo.setDevice_type("android");   //固定值 android
        deviceInfo.setImsi("c82874a30ad57ba29ec5ef709e45cceb");    //统一先写成unknown
        deviceInfo.setEmu("029f181d6e7ba188885c78462623c37a");   //android+momo+0的MD5值
        deviceInfo.setMac("02:00:00:00:00:00");                  //固定02:00:00:00:00:00
        deviceInfo.setHw("d8d71f9a499fddc52f8a1d8e201cdc17");
        deviceInfo.setManufacturer("Google");                 手机厂商，跟随抹机软件
        deviceInfo.setOsversion_int("27");                    //系统版本对应API等级
        deviceInfo.setUid("6766272a7e000278b21192236b3c3eb1");   //androidId的MD5
        deviceInfo.setRom("8.1.0");                           //系统版本
        deviceInfo.setRAMSize("3852816");                     //内存大小 KB
        deviceInfo.setBaseBandVersion("8996-130091-1802061512");    //基带版本
        deviceInfo.setCurrent_wifi("8C:53:C3:D2:98:A2");  //这个mac地址不清楚为什么是A2,因为本地路由器的mac地址是 	8C:53:C3:D2:98:A2    这个也是mac地址 也写成02:00:00:00:00:00？
        deviceInfo.setMarket_source("14");               //没找到赋值先写成14
        deviceInfo.setModel("Pixel");                    //手机型号
        deviceInfo.setEtype("2");                        //固定值
        deviceInfo.setAndroidId("912cd84c01034e24");     //安卓id
        deviceInfo.setLat("112.428718");                 //经度             当值为0直接写成固定值
        deviceInfo.set_uid_("6766272a7e000278b21192236b3c3eb1");   安卓id MD5
        deviceInfo.setPhone_type("CDMA");                //运营商 抹机生成
        deviceInfo.setLng("37.843120");                  //纬度             当值为0直接写成固定值
        deviceInfo.setCpuInfo("0-3");                    //cpu内核数
        deviceInfo.setPhone_netWork("0");                //先写成0 未知
        deviceInfo.setDpp("8b54c9211ea677d3b72f760ea1801d1b");   Art*#26个字母中随机的一个  加上陌陌id   然后md5
        deviceInfo.setUtdid("00000000");   固定值
        deviceInfo.setMacInfo("02:00:00:00:00:00");
        deviceInfo.setUserAgent("MomoChat/8.21 Android/5129 (Pixel; Android 8.1.0; Gapps 1; zh_CN; 14; Google)");
        deviceInfo.setBindSource("bind_source_new_login");      这个值不是固定的,对应的包写成对应的值   已删
        deviceInfo.setVersion("100071");                        这也不是个定值    已删
        deviceInfo.setApksign("4f3a531caff3e37c278659cc78bfaecc");   没位置      暂定
        deviceInfo.set_net_("wifi");                                       固定值
        deviceInfo.setRouter_mac("8C:53:C3:D2:98:A2"); //同current_wifi 都是当前网络wifi
        deviceInfo.setNetwork_class("wifi");                                固定值
        deviceInfo.setSerialNumber("FA68W0308543");                         序列号
        deviceInfo.setSensorNames("G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0");    传感器信息
        deviceInfo.setImei("352531081145847");           移动通信国际身份码
        deviceInfo.setUniqueid("02:00:00:00:00:00");
        deviceInfo.setIdfa("6766272a7e000278b21192236b3c3eb1");  安卓id md5
        deviceInfo.set_iid("f763497b83ed46d6dae0eb2af3e10aec");
        deviceInfo.set_uidType("androidid");
        deviceInfo.setCode_version("2");
        deviceInfo.setPublic_key("BNTc+loFgCXU8mbnDohv7j0Jv6Hmz7v3DcLwyVLMzQd4a7Yrv2dgfDFnw6SNR7OmAQ==");
        deviceInfo.setAesKey("y4pps+687+hKV8jB3iXpBdIjTGDTaOpUy4pps+687+hKV8jB");
        deviceInfo.setCpuString("0 1593600 307200");
        deviceInfo.setSn("6766272a7e000278b21192236b3c3eb1");
        deviceInfo.setNet("1");*/



     //
     /*
     * public static String l() {
        if (cc.a((CharSequence) l)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a.h().k() ? "MomoChat/" : "Molive/");
            stringBuffer.append(t());
            stringBuffer.append(" ");
            stringBuffer.append("Android/");
            stringBuffer.append(u());
            stringBuffer.append(" ");
            stringBuffer.append("(");
            stringBuffer.append(z() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Android " + C() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Gapps " + (D() ? 1 : 0) + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append(F() + "_" + E() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append(G());
            stringBuffer.append(")");
            try {
                l = new String(stringBuffer.toString().getBytes(), "UTF-8");
            } catch (Exception unused) {
                l = stringBuffer.toString();
            }
        }
        return l;
    }
    *
    *
    *
    * public static String E() {
        if (co.a((CharSequence) f24883d)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("MomoChat/");
            stringBuffer.append(w());
            stringBuffer.append(" ");
            stringBuffer.append("Android/");
            stringBuffer.append(s());
            stringBuffer.append(" ");
            stringBuffer.append("(");
            stringBuffer.append(c.w() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Android " + c.i() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Gapps " + (r() ? 1 : 0) + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append(com.immomo.mmutil.a.a.j() + "_" + com.immomo.mmutil.a.a.i() + f.f2637b);
            stringBuffer.append(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(e());
            sb.append(f.f2637b);
            stringBuffer.append(sb.toString());
            stringBuffer.append(" ");
            stringBuffer.append(c.x());
            stringBuffer.append(")");
            try {
                f24883d = new String(stringBuffer.toString().getBytes(), "UTF-8");
            } catch (Exception unused) {
                f24883d = stringBuffer.toString();
            }
        }
        return f24883d;
    }

*
*  public static String getUserAgent() {
        if (TextUtils.isEmpty(userAgent)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("MomoChat/");
            stringBuffer.append(Configs.SDK_VERSION_NAME);
            stringBuffer.append(" ");
            stringBuffer.append("Android/");
            stringBuffer.append(Configs.SDK_VERSION_CODE);
            stringBuffer.append(" ");
            stringBuffer.append("(");
            stringBuffer.append(DeviceUtils.getModle() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Android " + Build.VERSION.RELEASE + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("Gapps " + (hasGoogleMap() ? 1 : 0) + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append(Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append("1;");
            stringBuffer.append(" ");
            stringBuffer.append(DeviceUtils.getManufacturer() + f.f2637b);
            stringBuffer.append(" ");
            stringBuffer.append(MoPushManager.market);
            stringBuffer.append(")");
            try {
                userAgent = new String(stringBuffer.toString().getBytes(), "UTF-8");
            } catch (Exception unused) {
                userAgent = stringBuffer.toString();
            }
        }
        return userAgent;
    }

* _iid这个是时间戳+随机值的MD5？
*
* public static String g() {
        if(!TextUtils.isEmpty(c.f)) {
            return c.f;
        }

        c.f = com.immomo.framework.storage.c.b.save_cache("instance_id_cache", "");
        if(k.e(c.f)) {
            StringBuilder str = new StringBuilder();
            str.append(String.valueOf(UUID.randomUUID()));
            str.append(System.currentTimeMillis());
            c.f = k.md5_str(str.toString());
            com.immomo.framework.storage.c.b.save_cache("instance_id_cache", c.f);
        }

        return c.f;
    }

     *
     *
     *
     * */



}
