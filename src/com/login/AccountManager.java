package com.login;

import com.accountoperation.AccountOperation;
import com.utiltool.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//账号操作  这个有几个问题，登陆时候发送的包会不同，

public class AccountManager {

    private GuestLoginRelated GL_Related;             //游客登陆相关的URL
    private PwdLoginRelated AL_Related;               //账号密码登陆相关URL
    private AccountOperation AOP_Related;             //账号操作URL
    private AccountInfoUtil accountInfo;              //账号info
    private DeviceInfoUtil deviceInfo;                //设备info
    private HeaderInfoUtil headerInfo;                //请求头info
    private String FilePath=null;                     //读取设备账号等文件的路径
    private String accountNum=null;                   //账号密码,返回操作账号ID
    private CodePlatformUtil codePlatform;            //接码平台
    private fileGenerationUtil fileGeneration;        //生成默认账号文件信息
    //登陆过程中初始化
    private String GL_RequestRet =null;               //游客登陆返回url
    private String AL_RequestRet =null;               //账号密码登陆返回url
    private String GL_Session =null;                  //游客登陆的session
    private String AL_Session =null;                  //账号密码登陆的session
    private String curResource =null;                 //构造发送URL参数
    private String GL_index_regwithalias_retStr =null;    //paas  URL返回的mzip中包含 参数字段，
    private String Session=null;                      //缓存登陆直接使用accountInfo中的session
    private String hashKey=null;                     //缓存登录paas使用hashKey

    private String _guid=null;                        //这个guid  在paas中会用到 修改密码用
    private String _token=null;                       //修改密码用

    public String getAccountNum() {    //控制台测试时候使用,判断选择哪个账号
        return accountNum;
    }

    public fileGenerationUtil getFileGeneration() {
        return fileGeneration;
    }

    public AccountManager(String accountNum){    //读取已经构造好的
        this.FilePath=this.getClass().getResource("/").getPath()+"/"+accountNum;    //每个账号去相应的目录下去读自己的文件
        this.accountInfo= new AccountInfoUtil(StringUtil.readToString(FilePath+"/"+"account_info"));
        this.deviceInfo=new DeviceInfoUtil(StringUtil.readToString(FilePath+"/"+"device_info"));
        this.headerInfo=new HeaderInfoUtil(StringUtil.readToString(FilePath+"/"+"header_info"));
        this.GL_Related =new GuestLoginRelated(deviceInfo,headerInfo);
        this.AL_Related =new PwdLoginRelated(accountInfo,deviceInfo,headerInfo);
        this.AOP_Related =new AccountOperation(deviceInfo,accountInfo,headerInfo);
        this.codePlatform=new CodePlatformUtil();       //接码平台
        this.accountNum=accountNum;
        this.Session=accountInfo.getSession();                     //获取session进行缓存登陆
        this.curResource=accountInfo.getCurResource();
        this.hashKey=accountInfo.getHashKey();
        if(AL_Session==null)AL_Session=Session;
    }

    //默认的是注册的时候构造函数使用这个,等生成账号完毕后,使用上面的进行初始化
    public AccountManager(String name,String birthday,String gender){ //"goodjoy"  "1999-10-02"  "F"
        this.fileGeneration=new fileGenerationUtil();
        com.alibaba.fastjson.JSONObject regMessageInfo = new com.alibaba.fastjson.JSONObject(true);
        regMessageInfo.put("name",name);
        regMessageInfo.put("birthday",birthday);
        regMessageInfo.put("gender",gender);
        fileGeneration.generationFile(regMessageInfo.toJSONString());
    }

    //游客登录及初始化 22
    public void iniGuestLogin(){
        GL_Related.INI_abtest_index();
        GL_Related.INI_index_config();
        GL_Related.INI_welcome_logs();
        GL_Related.INI_log_common_abtestupload_0();
        GL_Related.INI_log_common_upload();
        GL_Related.INI_log_common_androidonlinetime();
        curResource=GL_Related.INI_appconfig_index("1203",null);  //1203资源返回值，保存到account_info文件中

        accountInfo.setCurResource(curResource);
        String accountStr=accountInfo.AccountInfo2String();
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);

        GL_Related.GL_log_common_abtestupload_1();
        GL_Related.INI_appconfig_index("75",null);     //资源75
        GL_RequestRet=GL_Related.GL_login_index(); //游客登录返回值,游客登录后直到账号密码登录的时候都用这个session

        GL_Related.GL_version_getupdatelist();
        GL_Related.GL_version_checkupdate();
        GL_Session=ParamUtil.getInstance().getSession(GL_RequestRet);  //游客登录session    //注册时候会用到
        //测试打印start
        System.out.println(GL_Session);
        //测试打印end
        String alias=ParamUtil.getInstance().getAlias(GL_RequestRet);  //获取alias
        _guid=alias;
        String pushHashKey=GL_Related.GL_photon_getPushHashKey(alias,GL_Session);        //第一个用到alias的值,alias值为GL_RequestRet返回值的guestid hashkey
        GL_Related.GL_log_common_abtestupload_2(GL_Session);
        GL_Related.GL_log_common_abtestupload_4(GL_Session);
        GL_Related.INI_appconfig_index("0",curResource);      //资源0,参数是资源1203的返回值
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),pushHashKey);
        GL_index_regwithalias_retStr=GL_Related.GL_index_regwithalias(alias,sn);   //游客登录paas,这个返回值有fasttoken中使用的token值
        GL_Related.GL_log_common_abtestupload_3(GL_Session);
        GL_Related.GL_discover_category(GL_Session);
        GL_Related.GL_appconfigmulti_index(GL_Session);
        GL_Related.GL_client_ipInfo(GL_Session);
        String token=ParamUtil.getInstance().getToken(GL_index_regwithalias_retStr);
        _token=token;
        GL_Related.GL_fasttoken_reg(GL_Session,token);
        System.out.println("初始化登录完毕");
    }

    //账号密码登录
    public void accountLogin(){
        if(GL_Session==null)return;
        AL_Related.AL_log_common_abtestupload_0(GL_Session);    //在账号密码登录前都是用GL_session
        AL_Related.AL_log_common_permissionupload_0(GL_Session);
        AL_Related.AL_log_common_permissionupload_1(GL_Session);  //设置权限
        AL_RequestRet=AL_Related.AL_v2_login(GL_Session);         //账号密码登录返回值   有经纬度
        if(AL_Session==null)return;
        AL_Session=ParamUtil.getInstance().getSession(AL_RequestRet); // 账号密码返回的session,之后所有的session都是这个session
        accountInfo.setSession(AL_Session);
        AL_Related.AL_log_common_abtestupload_1(AL_Session);
        AL_Related.AL_my_base_no_zip(AL_Session);
        String pushHashKey=AL_Related.AL_photon_getPushHashKey(AL_Session);
        AL_Related.AL_log_common_upload(AL_Session);
        AL_Related.AL_business_getAllSwitch(AL_Session);

        String alias=ParamUtil.getInstance().getAlias(GL_RequestRet);   //游客登录返回的
        String token=ParamUtil.getInstance().getToken(GL_index_regwithalias_retStr);  //游客paas返回值

        AL_Related.AL_index_unalias(alias,token);     //unalias
        AL_Related.AL_log_common_androidonlinetime(AL_Session);

        AL_Related.AL_config_user_index(AL_Session);
        AL_Related.AL_v3_index_config(AL_Session);
        AL_Related.AL_effectListNew(AL_Session);
        AL_Related.AL_log_common_androidonlinetime(AL_Session);

        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),pushHashKey);
        accountInfo.setHashKey(sn);
        String accountStr=accountInfo.AccountInfo2String();  //保存了session和hashkey两个值
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);

        String AL_paas_ret=AL_Related.AL_index_regwithalias(accountInfo.getAccount(),sn); //账号密码登录paas
        AL_Related.AL_fasttoken_reg(AL_Session,AL_paas_ret);

        AL_Related.AL_message_historyv2(AL_Session);
        AL_Related.AL_v2_welcome(AL_Session);
        AL_Related.AL_custom_package(AL_Session);
        AL_Related.AL_token_transfer(AL_Session);
        AL_Related.AL_productListsAll(AL_Session);   //有经纬度
        AL_Related.AL_appconfig_index(AL_Session,curResource);   //这里需要确定
        AL_Related.AL_together_checkreddot(AL_Session);
        AL_Related.AL_abtest_index(AL_Session);
        AL_Related.AL_discover_category(AL_Session);
        AL_Related.AL_appconfigmulti_index(AL_Session);
        AL_Related.AL_my_base_zip(AL_Session);
        AL_Related.AL_official_config(AL_Session);
        AL_Related.AL_wifi_upload(AL_Session);
        AL_Related.AL_special_getallindustry(AL_Session);
        AL_Related.AL_webapp_wholelists(AL_Session);
        AL_Related.AL_list_my(AL_Session);
        //AL_Related.AL_log_common_statfileupload_0(AL_Session);
        AL_Related.AL_emotion_update(AL_Session);
        AL_Related.AL_friend_getGlobalSearchUsers(AL_Session);
        AL_Related.AL_service_getRedDot(AL_Session);
        //AL_Related.AL_log_common_statfileupload_1(AL_Session);
        AL_Related.AL_people_lists(AL_Session);
        AL_Related.AL_together_generalmsg(AL_Session);
        System.out.println("账号登录完毕");
    }

    //缓存登录  需要一个AL的session,一个curResource(游客登录的1203返回值)如果不能修改密码,账号注册的时候,session也需要保存,然后缓存登录就是那个session
    public void cacheLogin(){
        if(AL_Session==null||hashKey==null)return;
        String CL_pass_ret=AL_Related.CL_index_regwithalias(accountInfo.getAccount(),hashKey);  //paas    //ok
        AL_Related.CL_index_config(AL_Session);   //ok  lastRespTime不固定 /data/user/0/com.immomo.young/shared_prefs/com.immomo.molive.e.c832031493.xml.bak文件中IndexConfigRequest字段的值
        AL_Related.CL_fasttoken_reg(AL_Session,CL_pass_ret);  //ok
        AL_Related.CL_abtest_index(AL_Session);    //ok   有经纬度
        AL_Related.CL_version_checkupdate(AL_Session);  //ok
        AL_Related.CL_welcome_logs(AL_Session);  //ok
        AL_Related.CL_appconfig_index(AL_Session,curResource);  //ok
        AL_Related.CL_api_banners(AL_Session);
        AL_Related.CL_v2_welcome(AL_Session);   //ok
        AL_Related.CL_people_lists(AL_Session);
        AL_Related.CL_log_common_upload_0(AL_Session);  //ok
        AL_Related.CL_log_common_upload_1(AL_Session);   //ok
        AL_Related.CL_appconfigmulti_index(AL_Session);  //ok
        AL_Related.CL_together_checkreddot(AL_Session);
        AL_Related.CL_log_common_androidonlinetime(AL_Session);  //ok
        AL_Related.CL_client_ipInfo(AL_Session);   //ok
        AL_Related.CL_my_base_zip(AL_Session);  //ok
        AL_Related.CL_service_getRedDot(AL_Session);  //ok
        //AL_Related.CL_log_common_statfileupload(AL_Session);
        AL_Related.CL_webapp_wholelists(AL_Session);  //ok
        AL_Related.CL_people_lists(AL_Session);    //ok
        AL_Related.CL_together_generalmsg(AL_Session);  //ok
        System.out.println("缓存登录完毕");
    }


    //注册账号  注册账号首先要进行游客初始化登录  生日,性别,电话号码,注册的昵称 ,上传头像的路径
    public String registerAccount(String birthday, String gender, String phonenumber,String countrycode, String name,String smallPhotoPath,String bigPhotoPath){
        if(GL_Session==null)return null;
        AOP_Related.REG_log_common_permissionupload_0(GL_Session);
        AOP_Related.REG_log_common_permissionupload_1(GL_Session);
        AOP_Related.REG_common_abtestupload_0(GL_Session);
        //String countryCode=countrycode;  //电话的前缀 +86
        //模拟接受验证码

        String str=AOP_Related.REG_verifycode_send(GL_Session,countrycode,phonenumber);  PRINT("REG_verifycode_send:"+str); //发送验证码


        System.out.println("请输入验证码:");
        Scanner scanner = new Scanner(System.in);
        String smscode=(String)scanner.nextLine();  //收到验证码

        AOP_Related.REG_common_abtestupload_1(GL_Session,countrycode+phonenumber);
        String login_index_ret=AOP_Related.REG_login_index(GL_Session,smscode,countrycode+phonenumber);
        AOP_Related.REG_common_abtestupload_2(GL_Session);
        AOP_Related.REG_common_abtestupload_3(GL_Session);
        AOP_Related.REG_common_abtestupload_4(GL_Session);
        AOP_Related.REG_register_checkSimple(GL_Session,birthday,gender,name);
        AOP_Related.REG_common_abtestupload_5(GL_Session);

        Map<String,String>info=new HashMap<>();
        //birthday=1990-05-14 gender=M countrycode=+86 phonenumber=17067600818 registerToken=A6FE95BA-D55E-6E44-BBD1-22C0F655D447  name=xx
        String token=ParamUtil.getInstance().get_reg_token(login_index_ret);
        info.put("birthday",birthday);
        info.put("gender",gender);
        info.put("countrycode",countrycode);
        info.put("phonenumber",phonenumber);
        info.put("registerToken",token);
        info.put("name",name);

        String register_index_ret=AOP_Related.REG_register_index(GL_Session,smallPhotoPath,info); //path头像路径  上传头像
        AL_Session=ParamUtil.getInstance().getSession(register_index_ret);  //
        String guid=ParamUtil.getInstance().getAvatar(register_index_ret);
        String momoid=ParamUtil.getInstance().getMomoId(register_index_ret); //注册的账号
        if(momoid==null)return null;

        accountInfo.setAccount(momoid);
        accountInfo.setSession(AL_Session);
        accountInfo.setBirthday(birthday);
        accountInfo.setGender(gender);
        accountInfo.setPhone(phonenumber);
        accountInfo.setName(name);
        accountInfo.setCountrycode(countrycode);
        accountInfo.setPassword("0f5c5718c5db8c341f8efbc9b0aec20d");    //注册完就会获取到注册的momoid,然后保存到对应的文件夹中

        String accountStr=accountInfo.AccountInfo2String();
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);

        AOP_Related.REG_common_abtestupload_6(GL_Session);  //日志相关还使用游客登录的session
        AOP_Related.REG_my_base_no_zip(AL_Session);  //这里已经是使用AL_session
        AOP_Related.REG_common_abtestupload_7(GL_Session);
        AOP_Related.REG_personal_avatar(AL_Session,bigPhotoPath,guid);
        AOP_Related.REG_setting_recallpush(GL_Session);
        AOP_Related.REG_setting_sound_no_zip(GL_Session);
        AOP_Related.REG_index_config(AL_Session);
        String photon_getPushHashKey_ret=AOP_Related.REG_photon_getPushHashKey(AL_Session);  //返回值中有paas使用的sn
        AOP_Related.REG_business_getAllSwitch(AL_Session);
        AOP_Related.REG_log_common_upload(AL_Session);
        AOP_Related.REG_common_androidonlinetime(AL_Session);
        AOP_Related.REG_config_user_index(AL_Session);
        AOP_Related.REG_common_androidonlinetime(AL_Session);
        AOP_Related.REG_effectListNew(AL_Session);
        AOP_Related.REG_setting_pushlive(AL_Session);
        AOP_Related.REG_token_transfer(AL_Session);
        AOP_Related.REG_notice_config(AL_Session);
        AOP_Related.REG_appconfig_index(AL_Session,curResource);
        AOP_Related.REG_productListsAll(AL_Session);
        AOP_Related.REG_banners_v2_welcome(AL_Session);
        AOP_Related.REG_api_profiles(AL_Session,"10000");
        AOP_Related.REG_api_profiles(AL_Session,"10026");
        AOP_Related.REG_discover_category(AL_Session);
        AOP_Related.REG_together_checkreddot(AL_Session);
        AOP_Related.REG_appconfigmulti_index(AL_Session);
        AOP_Related.REG_my_base_zip(AL_Session);
        AOP_Related.REG_official_config(AL_Session);
        AOP_Related.REG_setting_recallpush(AL_Session);
        AOP_Related.REG_setting_sound_zip(AL_Session);
        AOP_Related.REG_friend_getGlobalSearchUsers(AL_Session);
        AOP_Related.REG_webapp_wholelists(AL_Session);
        AOP_Related.REG_wifi_upload(AL_Session);
        AOP_Related.REG_special_getallindustry(AL_Session);
        AOP_Related.REG_list_my(AL_Session);
        AOP_Related.REG_emotion_update(AL_Session);
        AOP_Related.REG_service_getRedDot(AL_Session);
        AOP_Related.REG_index_unalias(AL_Session,token);
        AOP_Related.REG_my_base_zip_1(AL_Session);
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),photon_getPushHashKey_ret);
        String index_regwithalias_ret=AOP_Related.REG_index_regwithalias(AL_Session,sn);
        String token_regwithalias=ParamUtil.getInstance().getToken(index_regwithalias_ret);
        AOP_Related.REG_fasttoken_reg(AL_Session,token_regwithalias);
        AOP_Related.REG_abtest_index(AL_Session);
        AOP_Related.REG_people_lists(AL_Session);
        AOP_Related.REG_together_generalmsg(AL_Session);
        return momoid;//返回陌陌id
    }

    //修改密码  这个必须要做，否则缓存登录最多一天，必须要账号密码去登录一次  这个是注册完毕登录后做的
    public void modifyPassword(){
        if(AL_Session==null)return;
        clickMy();  //点击我的
        AOP_Related.OP_log_common_settingGoto(AL_Session);   //点击设置
        AOP_Related.OP_setting_index(AL_Session);  //点击设置
        //https://live-log.immomo.com/v3/log/client/upload 这个包发不了,暂时是发不了,一个直播日志的包
        AOP_Related.OP_payment_findAlipayStatus(AL_Session);   //点击账号与安全的包
        AOP_Related.OP_setting_index(AL_Session);              //点击账号与安全的包
        AOP_Related.OP_password_getPwdCheck(AL_Session);       //点击账号与安全的包

        AOP_Related.OP_password_setUserPwd(AL_Session);  //点击修改密码
        AOP_Related.OP_setting_momologout(AL_Session);   //点击修改密码
        AOP_Related.OP_index_unalias(accountInfo.getAccount(),_token);  //点击修改密码   再次抓包时候要看一下这个值  {"app_id":"e66b3f1d949694ad0e88fc83654e3b25","alias":"845051818","token":"T-dfae1a2d2710432b9b81b4b751fbcb1b-5572-126"}
        AOP_Related.OP_log_common_androidonlinetime(null);  //点击修改密码
        String HashKeyRet=AOP_Related.OP_photon_getPushHashKey(GL_Session,_guid); //点击修改密码   这个session已经不是al_session了，并且这个alias是哪里的  返回个hashkey   看一下这个session是不是初始化登录时候那个session,修改密码后就又用到了
        if(HashKeyRet==null)return;
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),HashKeyRet);
        AOP_Related.OP_index_regwithalias(_guid,sn);    //点击修改密码   具体参数再抓{"alias":"1004039503488","sn":"hashkey_6766272a7e000278b21192236b3c3eb1_7f50ed5f25ac1eaaa982b922656e0365919d6986","device_id":"05de98a68979cbf034313a9cf1395e4a34c43840:+com.immomo.young","app_id":"e66b3f1d949694ad0e88fc83654e3b25","keystore_sha1":"04:47:FA:95:5C:5C:94:3D:B0:04:F2:28:12:32:EB:AE:95:DF:FD:84"}
        AOP_Related.OP_log_common_androidonlinetime(AL_Session);  //没有sorceid参数
        AOP_Related.OP_welcome_logs(GL_Session);   //GLSession
        AOP_Related.OP_version_checkupdate_no_zip(GL_Session);
        AOP_Related.OP_fasttoken_reg(GL_Session,_token);  //OP_index_regwithalias返回token
        AOP_Related.OP_log_common_abtestupload_0(GL_Session);
        AOP_Related.OP_log_common_abtestupload_1(GL_Session);
        AOP_Related.OP_token_transfer(GL_Session);
        AOP_Related.OP_appconfig_index(GL_Session);
        AOP_Related.OP_common_androidonlinetime(GL_Session);
        AOP_Related.OP_appconfigmulti_index(GL_Session);
        AOP_Related.OP_nearby_index(GL_Session);                           //guestid=1004052476174 和session=2CABAF07-3CB4-AF2E-08F6-3899920F71DA_G
    }

    //点击我的
    public void clickMy(){
        AOP_Related.OP_my_base_click(AL_Session);
        AOP_Related.OP_play_discover(AL_Session);
        AOP_Related.OP_log_common_statfileupload(AL_Session);
    }

    //点击动态
    public void clickDynamic(){
        AOP_Related.OP_nearby_lists(AL_Session);
        AOP_Related.OP_phone_sendNoBindPhoneCard(AL_Session);
    }

    //点击消息
    public void clickNews(){

    }

    //点击查看个人资料
    public void clickViewProfile(){

    }

    //点击我的动态
    public void clickMyActivity(){
        AOP_Related.OP_feed_profile(AL_Session);
        AOP_Related.OP_user_timeline(AL_Session);
        AOP_Related.OP_user_getGuideSwitch(AL_Session);
        AOP_Related.OP_user_getGuideConfig(AL_Session);
    }

    //发送动态(图片+文字)  文字内容,图片路径
    public void sendDynamic(String context, String PhotoPath){
        if(AL_Session==null)return;
        clickMy();   //点击我的
        clickDynamic();  //点击动态
        clickMyActivity();  //点击我的动态
        AOP_Related.OP_log_common_statfileupload_sendDynamic_0(AL_Session);
        AOP_Related.OP_site_auto_select(AL_Session);   //这经纬度得改，默认选择的地址
        String str=AOP_Related.OP_publish_check(AL_Session,context);
        System.out.println("OP_publish_check:"+str);       //测试打印
        str=AOP_Related.OP_publish_send_index(AL_Session,PhotoPath,context);
        System.out.println("OP_publish_send_index:"+str);   //测试打印

        //AOP_Related.OP_feed_read_kill(AL_Session,"","");
        sendDynamicReturn(); //发送完动态返回
        System.out.println("动态发送完毕");
    }

    //发送动态后返回
    public void sendDynamicReturn(){
        AOP_Related.OP_log_common_statfileupload_sendDynamic_1(AL_Session);
        AOP_Related.OP_log_common_statfileupload_sendDynamic_return(AL_Session);
        AOP_Related.OP_play_discover(AL_Session);
    }


    //打印测试
    /////////////////////////////////////TEST_START////////////////////////////////////////////////////////////////////////
    public void PRINT(String str){
        System.out.println(str+"\n");
    }

    public void INI_GUEST_LOGIN_TEST(){
        String str =GL_Related.INI_abtest_index();PRINT("INI_abtest_index:"+str);
        str =GL_Related.INI_index_config();PRINT("INI_index_config:"+str);
        str =GL_Related.INI_welcome_logs();PRINT("INI_welcome_logs:"+str);
        str =GL_Related.INI_log_common_abtestupload_0();PRINT("INI_log_common_abtestupload_0"+str);
        str =GL_Related.INI_log_common_upload();PRINT("INI_log_common_upload:"+str);
        str =GL_Related.INI_log_common_androidonlinetime();PRINT("INI_log_common_androidonlinetime:"+str);
        curResource=GL_Related.INI_appconfig_index("1203",null);PRINT("curResource1203:"+curResource);  //1203资源返回值，保存到account_info文件中

        accountInfo.setCurResource(curResource);
        String accountStr=accountInfo.AccountInfo2String();
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);

        str =GL_Related.GL_log_common_abtestupload_1();PRINT("GL_log_common_abtestupload_1:"+str);
        str =GL_Related.INI_appconfig_index("75",null);PRINT("INI_appconfig_index75:"+str);     //资源75
        GL_RequestRet=GL_Related.GL_login_index();PRINT("GL_login_index:"+GL_RequestRet); //游客登录返回值,游客登录后直到账号密码登录的时候都用这个session
        str =GL_Related.GL_version_getupdatelist();PRINT("GL_version_getupdatelist:"+str);
        str =GL_Related.GL_version_checkupdate();PRINT("GL_version_checkupdate:"+str);
        GL_Session=ParamUtil.getInstance().getSession(GL_RequestRet);PRINT("GL_Session:"+GL_Session);  //游客登录session
        String alias=ParamUtil.getInstance().getAlias(GL_RequestRet);  //获取alias
        String pushHashKey =GL_Related.GL_photon_getPushHashKey(alias,GL_Session);   PRINT("GL_photon_getPushHashKey:"+pushHashKey);     //第一个用到alias的值,alias值为GL_RequestRet返回值的guestid,sn的值是hash_key
        str =GL_Related.GL_log_common_abtestupload_2(GL_Session); PRINT("GL_log_common_abtestupload_2:"+str);
        str =GL_Related.GL_log_common_abtestupload_4(GL_Session);PRINT("GL_log_common_abtestupload_4:"+str);
        str =GL_Related.INI_appconfig_index("0",curResource);  PRINT("INI_appconfig_index0:"+str);    //资源0,参数是资源1203的返回值
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),pushHashKey);
        GL_index_regwithalias_retStr=GL_Related.GL_index_regwithalias(alias,sn); PRINT("GL_index_regwithalias_retStr:"+GL_index_regwithalias_retStr);  //游客登录paas,这个返回值有fasttoken中使用的token值
        str =GL_Related.GL_log_common_abtestupload_3(GL_Session);PRINT("GL_log_common_abtestupload_3:"+str);
        str =GL_Related.GL_discover_category(GL_Session);PRINT("GL_discover_category:"+str);
        str =GL_Related.GL_appconfigmulti_index(GL_Session);PRINT("GL_appconfigmulti_index:"+str);
        str =GL_Related.GL_client_ipInfo(GL_Session);PRINT("GL_client_ipInfo:"+str);
        String token=ParamUtil.getInstance().getToken(GL_index_regwithalias_retStr);
        str =GL_Related.GL_fasttoken_reg(GL_Session,token);PRINT("GL_fasttoken_reg:"+str);
    }

    public void AL_ACCOUNT_TEST(){
        if(GL_Session==null)return;
        String str=AL_Related.AL_log_common_abtestupload_0(GL_Session); PRINT("AL_log_common_abtestupload_0:"+str);  //paas   //在账号密码登录前都是用GL_session
        str=AL_Related.AL_log_common_permissionupload_0(GL_Session);PRINT("AL_log_common_permissionupload_0:"+str);
        str=AL_Related.AL_log_common_permissionupload_1(GL_Session);PRINT("AL_log_common_permissionupload_1:"+str);  //设置权限
        AL_RequestRet=AL_Related.AL_v2_login(GL_Session);   PRINT("AL_v2_login:"+AL_RequestRet);        //账号密码登录返回值
        if(AL_RequestRet==null)return;
        AL_Session=ParamUtil.getInstance().getSession(AL_RequestRet);PRINT("AL_Session:"+AL_Session);  // 账号密码返回的session,之后所有的session都是这个session
        PRINT("AL_SESSION="+AL_Session);
        accountInfo.setSession(AL_Session);   //accountInfo.SaveValue2File(this.FilePath+"/"+"account_info",accountStr);   //保存session字段到文件中
        str=AL_Related.AL_log_common_abtestupload_1(AL_Session);PRINT("AL_log_common_abtestupload_1:"+str);
        str=AL_Related.AL_my_base_no_zip(AL_Session);PRINT("AL_my_base_no_zip:"+str);
        String pushHashKey=AL_Related.AL_photon_getPushHashKey(AL_Session);PRINT("AL_photon_getPushHashKey:"+pushHashKey);
        str=AL_Related.AL_log_common_upload(AL_Session);PRINT("AL_log_common_upload:"+str);
        str=AL_Related.AL_business_getAllSwitch(AL_Session);PRINT("AL_business_getAllSwitch:"+str);

        String alias=ParamUtil.getInstance().getAlias(GL_RequestRet);   //游客登录返回的
        String token=ParamUtil.getInstance().getToken(GL_index_regwithalias_retStr);  //游客paas返回值

        str=AL_Related.AL_index_unalias(alias,token);   PRINT("AL_index_unalias:"+str);  //unalias
        str=AL_Related.AL_log_common_androidonlinetime(AL_Session);PRINT("AL_log_common_androidonlinetime:"+str);

        str= AL_Related.AL_config_user_index(AL_Session);PRINT("AL_config_user_index:"+str);
        str=AL_Related.AL_v3_index_config(AL_Session);PRINT("AL_v3_index_config:"+str);
        str=AL_Related.AL_effectListNew(AL_Session);PRINT("AL_effectListNew:"+str);
        str=AL_Related.AL_log_common_androidonlinetime(AL_Session);PRINT("AL_log_common_androidonlinetime:"+str);

        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),pushHashKey);
        accountInfo.setHashKey(sn);
        String accountStr=accountInfo.AccountInfo2String();  //保存了session和hashkey两个值
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);


        String AL_paas_ret=AL_Related.AL_index_regwithalias(accountInfo.getAccount(),sn); PRINT("AL_paas_ret:"+AL_paas_ret);//账号密码登录paas
        str=AL_Related.AL_fasttoken_reg(AL_Session,AL_paas_ret);PRINT("AL_fasttoken_reg:"+str);

        str=AL_Related.AL_message_historyv2(AL_Session);PRINT("AL_message_historyv2:"+str);
        str=AL_Related.AL_v2_welcome(AL_Session);PRINT("AL_v2_welcome:"+str);
        str=AL_Related.AL_custom_package(AL_Session);PRINT("AL_custom_package:"+str);
        str= AL_Related.AL_token_transfer(AL_Session);PRINT("AL_token_transfer:"+str);
        str=AL_Related.AL_productListsAll(AL_Session);PRINT("AL_productListsAll:"+str);
        str=AL_Related.AL_appconfig_index(AL_Session,curResource); PRINT("AL_appconfig_index:"+str);  //这里需要确定
        str=AL_Related.AL_together_checkreddot(AL_Session);PRINT("AL_together_checkreddot:"+str);
        str=AL_Related.AL_abtest_index(AL_Session);PRINT("AL_abtest_index:"+str);
        str=AL_Related.AL_discover_category(AL_Session);PRINT("AL_discover_category:"+str);
        str=AL_Related.AL_appconfigmulti_index(AL_Session);PRINT("AL_appconfigmulti_index:"+str);
        str=AL_Related.AL_my_base_zip(AL_Session);PRINT("AL_my_base_zip:"+str);
        str=AL_Related.AL_official_config(AL_Session);PRINT("AL_official_config:"+str);
        str=AL_Related.AL_wifi_upload(AL_Session);PRINT("AL_wifi_upload:"+str);
        str=AL_Related.AL_special_getallindustry(AL_Session);PRINT("AL_special_getallindustry:"+str);
        str=AL_Related.AL_webapp_wholelists(AL_Session);PRINT("AL_webapp_wholelists:"+str);
        str=AL_Related.AL_list_my(AL_Session);PRINT("AL_list_my:"+str);
        str=AL_Related.AL_log_common_statfileupload_0(AL_Session);PRINT("AL_log_common_statfileupload_0:"+str);   //日志
        str=AL_Related.AL_emotion_update(AL_Session);PRINT("AL_emotion_update:"+str);
        str=AL_Related.AL_friend_getGlobalSearchUsers(AL_Session);PRINT("AL_friend_getGlobalSearchUsers:"+str);
        str=AL_Related.AL_service_getRedDot(AL_Session);PRINT("AL_service_getRedDot:"+str);
        str=AL_Related.AL_log_common_statfileupload_1(AL_Session);PRINT("AL_log_common_statfileupload_1:"+str);  //日志
        str=AL_Related.AL_people_lists(AL_Session);PRINT("AL_people_lists:"+str);
        str=AL_Related.AL_together_generalmsg(AL_Session);PRINT("AL_together_generalmsg:"+str);
    }

    public void CL_LOGIN_TEST(){
        if(AL_Session==null||hashKey==null)return;PRINT("cL——session:"+AL_Session);
        String CL_pass_ret=AL_Related.CL_index_regwithalias(accountInfo.getAccount(),hashKey);PRINT("CL_index_regwithalias:"+CL_pass_ret);  //paas  缓存登录使用sn
        String str=AL_Related.CL_index_config(AL_Session);PRINT("CL_index_config:"+str);
        str=AL_Related.CL_fasttoken_reg(AL_Session,CL_pass_ret);PRINT("CL_fasttoken_reg:"+str);
        str=AL_Related.CL_abtest_index(AL_Session);PRINT("CL_abtest_index:"+str);
        str=AL_Related.CL_version_checkupdate(AL_Session);PRINT("CL_version_checkupdate:"+str);
        str=AL_Related.CL_welcome_logs(AL_Session);PRINT("CL_welcome_logs:"+str);
        str=AL_Related.CL_appconfig_index(AL_Session,curResource);PRINT("CL_appconfig_index:"+str);
        //str=AL_Related.CL_api_banners(AL_Session);PRINT("CL_api_banners:"+str);
        str=AL_Related.CL_v2_welcome(AL_Session);PRINT("CL_v2_welcome:"+str);
        str=AL_Related.CL_people_lists(AL_Session);PRINT("CL_people_lists:"+str);
        str=AL_Related.CL_log_common_upload_0(AL_Session);PRINT("CL_log_common_upload_0:"+str);
        str=AL_Related.CL_log_common_upload_1(AL_Session);PRINT("CL_log_common_upload_1:"+str);
        str=AL_Related.CL_appconfigmulti_index(AL_Session);PRINT("CL_appconfigmulti_index:"+str);
        str=AL_Related.CL_together_checkreddot(AL_Session);PRINT("CL_together_checkreddot:"+str);
        str=AL_Related.CL_log_common_androidonlinetime(AL_Session);PRINT("CL_log_common_androidonlinetime:"+str);
        str=AL_Related.CL_client_ipInfo(AL_Session);PRINT("CL_client_ipInfo:"+str);
        str=AL_Related.CL_my_base_zip(AL_Session);PRINT("CL_my_base_zip:"+str);
        str=AL_Related.CL_service_getRedDot(AL_Session);PRINT("CL_service_getRedDot:"+str);
        str=AL_Related.CL_log_common_statfileupload(AL_Session);PRINT("CL_log_common_statfileupload:"+str);  //日志
        str=AL_Related.CL_webapp_wholelists(AL_Session);PRINT("CL_webapp_wholelists:"+str);
        str=AL_Related.CL_people_lists(AL_Session);PRINT("CL_people_lists:"+str);
        str=AL_Related.CL_together_generalmsg(AL_Session);PRINT("CL_together_generalmsg:"+str);
    }

    public void CLICK_MY_TEST(){
        String str=AOP_Related.OP_my_base_click(AL_Session);PRINT("OP_nearby_lists:"+str);
        str=AOP_Related.OP_play_discover(AL_Session);PRINT("OP_nearby_lists:"+str);
        str=AOP_Related.OP_log_common_statfileupload(AL_Session);PRINT("OP_nearby_lists:"+str);   //日志构成问题
    }


    public void CLICK_DYNAMIC_TEST(){
        String str=AOP_Related.OP_nearby_lists(AL_Session);PRINT("OP_nearby_lists:"+str);
        str=AOP_Related.OP_phone_sendNoBindPhoneCard(AL_Session);PRINT("OP_phone_sendNoBindPhoneCard:"+str);
    }



    public void CLICK_MY_ACTIVITY_TEST(){

        String str=AOP_Related.OP_feed_profile(AL_Session);PRINT("OP_feed_profile:"+str);
        str=AOP_Related.OP_user_timeline(AL_Session);PRINT("OP_user_timeline:"+str);
        str=AOP_Related.OP_user_getGuideSwitch(AL_Session);PRINT("OP_user_getGuideSwitch:"+str);
        str=AOP_Related.OP_user_getGuideConfig(AL_Session);PRINT("OP_user_getGuideConfig:"+str);
    }

    public void SEND_DYNAMIC_TEST(String context,String PhotoPath){
        if(AL_Session==null)return;
        CLICK_MY_TEST();   //点击我的
        CLICK_DYNAMIC_TEST();  //点击动态
        CLICK_MY_ACTIVITY_TEST();  //点击我的动态
        String str=AOP_Related.OP_log_common_statfileupload_sendDynamic_0(AL_Session);PRINT("OP_log_common_statfileupload_sendDynamic_0:"+str);  //日志
        str=AOP_Related.OP_site_auto_select(AL_Session);PRINT("OP_site_auto_select:"+str);   //这经纬度得改，默认选择的地址
        str=AOP_Related.OP_publish_check(AL_Session,context);PRINT("OP_publish_check:"+str);
        str=AOP_Related.OP_publish_send_index(AL_Session,PhotoPath,context);PRINT("OP_publish_send_index:"+str);
        //str=AOP_Related.OP_feed_read_kill(AL_Session,"","");PRINT("OP_feed_read_kill:"+str);
        SEND_DYNAMIC_RETURN_TEST(); //发送完动态返回

    }

    public void SEND_DYNAMIC_RETURN_TEST(){
        String str=AOP_Related.OP_log_common_statfileupload_sendDynamic_1(AL_Session);PRINT("OP_log_common_statfileupload_sendDynamic_1:"+str);
        str=AOP_Related.OP_log_common_statfileupload_sendDynamic_return(AL_Session);PRINT("OP_log_common_statfileupload_sendDynamic_return:"+str);  //日志问题
        str=AOP_Related.OP_play_discover(AL_Session);PRINT("OP_play_discover:"+str);
    }



    /*
    * 出现问题首先接码平台不稳定
    * 头像上传的包发送失败
    * */

    public String REG_TEST(String birthday, String gender, String phonenumber1,String countrycode1, String name,String smallPhotoPath,String bigPhotoPath){
        if(GL_Session==null)return null;
        String str=null;
        str=AOP_Related.REG_log_common_permissionupload_0(GL_Session);PRINT("REG_log_common_permissionupload_0:"+str);
        str=AOP_Related.REG_log_common_permissionupload_1(GL_Session);PRINT("REG_log_common_permissionupload_1:"+str);
        str=AOP_Related.REG_common_abtestupload_0(GL_Session);PRINT("REG_common_abtestupload_0:"+str);
        //String countryCode=countrycode;  //电话的前缀 +86
        //模拟接受验证码？睡眠30s？

        String session=null;
        boolean flag=true;   //退出标记
        int count=0;   //60秒未获得验证码就没用了
        String momoid=null;  //陌陌id

        str=AOP_Related.REG_verifycode_send(GL_Session,countrycode1,phonenumber1);PRINT("REG_verifycode_send:"+str);//发送验证码  相当于点击获取验证码


        //接码平台这个session总是一会有一会没有的
        /*String loginUrl=codePlatform.ReceiveCodeLogin("testJiema888","testJiema888",session);    //接码平台登录

        session=codePlatform.getSession(loginUrl);
        if(session==null){
            session="4B99ABB5F5D9EAC401750EBFFE12B8A4";
        }



        System.out.println("loginUrl:"+loginUrl);
        String retPhoneUrl=codePlatform.ReceiveCodeGetPhone("testJiema888","testJiema888",session); //登录完发送获取手机号


        String phonenumber=codePlatform.getPhoneNum(retPhoneUrl);    //获取电话号码
        if(phonenumber==null||phonenumber.equals("")){
            System.out.println("手机号码获取失败");
            return momoid;
        }

        str=AOP_Related.REG_verifycode_send(GL_Session,countrycode,phonenumber);PRINT("REG_verifycode_send:"+str);//发送验证码  相当于点击获取验证码

        String verifyCode="";

        while (flag){
            try {
                Thread.sleep(15 * 1000); //设置暂停的时间 15 秒
                count++;
                String verifyCodeUrl=codePlatform.ReceiveCodeGetVerifyCode("testJiema888","testJiema888",phonenumber,session);   //返回验证码的url
                verifyCode=codePlatform.getVerifyCode(verifyCodeUrl);
                if(verifyCode!=null||count==4){
                    flag=false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

         if(verifyCode==null){
             System.out.println("验证码接受已经超时");
             return momoid;
         }*/


        //点击完后,接码平台进行收验证码

        System.out.println("请输入验证码:");

        Scanner scanner = new Scanner(System.in);
        String input=(String)scanner.nextLine();

        str=AOP_Related.REG_common_abtestupload_1(GL_Session,countrycode1+phonenumber1);PRINT("REG_common_abtestupload_1:"+str);
        String login_index_ret=AOP_Related.REG_login_index(GL_Session,input/*verifyCode*/,countrycode1+phonenumber1);PRINT("REG_login_index:"+login_index_ret);//smcode收到的验证码
        str=AOP_Related.REG_common_abtestupload_2(GL_Session);PRINT("REG_common_abtestupload_2:"+str);
        str=AOP_Related.REG_common_abtestupload_3(GL_Session);PRINT("REG_common_abtestupload_3:"+str);
        str=AOP_Related.REG_common_abtestupload_4(GL_Session);PRINT("REG_common_abtestupload_4:"+str);
        str=AOP_Related.REG_register_checkSimple(GL_Session,birthday,gender,name);PRINT("REG_register_checkSimple:"+str);  //检查上传头像是否正常
        str=AOP_Related.REG_common_abtestupload_5(GL_Session);PRINT("REG_register_checkSimple:"+str);

        Map<String,String>info=new HashMap<>();
        //birthday=1990-05-14 gender=M countrycode=+86 phonenumber=17067600818 registerToken=A6FE95BA-D55E-6E44-BBD1-22C0F655D447  name=xx
        String token=ParamUtil.getInstance().get_reg_token(login_index_ret);PRINT("token:"+token);
        info.put("birthday",birthday);
        info.put("gender",gender);
        info.put("countrycode",countrycode1);
        info.put("phonenumber",phonenumber1);
        info.put("registerToken",token);
        info.put("name",name);

        String register_index_ret=AOP_Related.REG_register_index(GL_Session,smallPhotoPath,info); PRINT("register_index_ret:"+register_index_ret);//path头像路径  上传头像的时候出问题   150*150像素
        AL_Session=ParamUtil.getInstance().getSession(register_index_ret); PRINT("AL_Session:"+AL_Session);
        String avatar=ParamUtil.getInstance().getAvatar(register_index_ret);PRINT("avatar:"+avatar);
        momoid=ParamUtil.getInstance().getMomoId(register_index_ret);PRINT("momoid:"+momoid); //注册的账号
        if(momoid==null)return null;   //注册失败


        accountInfo.setAccount(momoid);
        accountInfo.setSession(AL_Session);
        accountInfo.setBirthday(birthday);
        accountInfo.setGender(gender);
        accountInfo.setPhone(phonenumber1);
        accountInfo.setName(name);
        accountInfo.setCountrycode(countrycode1);
        accountInfo.setPassword("0f5c5718c5db8c341f8efbc9b0aec20d");    //注册完就会获取到注册的momoid,然后保存到对应的文件夹中

        String accountStr=accountInfo.AccountInfo2String();
        StringUtil.getInstance().Save(this.FilePath+"/"+"account_info",accountStr);

        str=AOP_Related.REG_common_abtestupload_6(GL_Session); PRINT("REG_common_abtestupload_6:"+str); //日志相关还使用游客登录的session
        str=AOP_Related.REG_my_base_no_zip(AL_Session);PRINT("REG_my_base_no_zip:"+str);  //这里已经是使用AL_session
        str=AOP_Related.REG_common_abtestupload_7(GL_Session);PRINT("REG_common_abtestupload_7:"+str);
        str=AOP_Related.REG_personal_avatar(AL_Session,bigPhotoPath,avatar);PRINT("REG_personal_avatar:"+str);   //600*600像素
        str=AOP_Related.REG_setting_recallpush(GL_Session);PRINT("REG_setting_recallpush:"+str);
        str=AOP_Related.REG_setting_sound_no_zip(GL_Session);PRINT("REG_setting_sound_no_zip:"+str);
        str=AOP_Related.REG_index_config(AL_Session);PRINT("REG_index_config:"+str);
        String photon_getPushHashKey_ret=AOP_Related.REG_photon_getPushHashKey(AL_Session); PRINT("photon_getPushHashKey_ret:"+photon_getPushHashKey_ret); //返回值中有paas使用的sn
        str=AOP_Related.REG_business_getAllSwitch(AL_Session);PRINT("REG_business_getAllSwitch:"+str);
        str=AOP_Related.REG_log_common_upload(AL_Session);PRINT("REG_log_common_upload:"+str);
        str=AOP_Related.REG_common_androidonlinetime(AL_Session);PRINT("REG_common_androidonlinetime:"+str);
        str=AOP_Related.REG_config_user_index(AL_Session);PRINT("REG_config_user_index:"+str);
        str=AOP_Related.REG_common_androidonlinetime(AL_Session);PRINT("REG_common_androidonlinetime:"+str);
        str=AOP_Related.REG_effectListNew(AL_Session);PRINT("REG_effectListNew:"+str);
        str=AOP_Related.REG_setting_pushlive(AL_Session);PRINT("REG_setting_pushlive:"+str);
        str=AOP_Related.REG_token_transfer(AL_Session);PRINT("REG_token_transfer:"+str);
        str=AOP_Related.REG_notice_config(AL_Session);PRINT("REG_notice_config:"+str);
        str=AOP_Related.REG_appconfig_index(AL_Session,curResource);PRINT("REG_appconfig_index:"+str);
        str=AOP_Related.REG_productListsAll(AL_Session);PRINT("REG_productListsAll:"+str);
        str=AOP_Related.REG_banners_v2_welcome(AL_Session);PRINT("REG_banners_v2_welcome:"+str);
        str=AOP_Related.REG_api_profiles(AL_Session,"10000");PRINT("REG_api_profiles_10000:"+str);
        str=AOP_Related.REG_api_profiles(AL_Session,"10026");PRINT("REG_api_profiles_10026:"+str);
        str=AOP_Related.REG_discover_category(AL_Session);PRINT("REG_discover_category:"+str);
        str=AOP_Related.REG_together_checkreddot(AL_Session);PRINT("REG_together_checkreddot:"+str);
        str=AOP_Related.REG_appconfigmulti_index(AL_Session);PRINT("REG_appconfigmulti_index:"+str);
        str=AOP_Related.REG_my_base_zip(AL_Session);PRINT("REG_my_base_zip:"+str);
        str=AOP_Related.REG_official_config(AL_Session);PRINT("REG_official_config:"+str);
        str=AOP_Related.REG_setting_recallpush(AL_Session);PRINT("REG_setting_recallpush:"+str);
        str=AOP_Related.REG_setting_sound_zip(AL_Session);PRINT("REG_setting_sound_zip:"+str);
        str=AOP_Related.REG_friend_getGlobalSearchUsers(AL_Session);PRINT("REG_friend_getGlobalSearchUsers:"+str);
        str=AOP_Related.REG_webapp_wholelists(AL_Session);PRINT("REG_webapp_wholelists:"+str);
        str=AOP_Related.REG_wifi_upload(AL_Session);PRINT("REG_wifi_upload:"+str);
        str=AOP_Related.REG_special_getallindustry(AL_Session);PRINT("REG_special_getallindustry:"+str);
        str=AOP_Related.REG_list_my(AL_Session);PRINT("REG_list_my:"+str);
        str=AOP_Related.REG_emotion_update(AL_Session);PRINT("REG_emotion_update:"+str);
        str=AOP_Related.REG_service_getRedDot(AL_Session);PRINT("REG_service_getRedDot:"+str);
        str=AOP_Related.REG_index_unalias(AL_Session,token);PRINT("REG_index_unalias:"+str);
        str=AOP_Related.REG_my_base_zip_1(AL_Session);PRINT("REG_my_base_zip_1:"+str);
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),photon_getPushHashKey_ret);PRINT("sn:"+sn);
        String index_regwithalias_ret=AOP_Related.REG_index_regwithalias(AL_Session,sn);PRINT("index_regwithalias_ret:"+index_regwithalias_ret);
        String token_regwithalias=ParamUtil.getInstance().getToken(index_regwithalias_ret);PRINT("token_regwithalias:"+token_regwithalias);
        str=AOP_Related.REG_fasttoken_reg(AL_Session,token_regwithalias);PRINT("REG_fasttoken_reg:"+str);
        str=AOP_Related.REG_abtest_index(AL_Session);PRINT("REG_abtest_index:"+str);
        str=AOP_Related.REG_people_lists(AL_Session);PRINT("REG_people_lists:"+str);
        str=AOP_Related.REG_together_generalmsg(AL_Session);PRINT("REG_together_generalmsg:"+str);
        return momoid;//返回陌陌id
    }



    public void MODIFY_TEST(){
        if(AL_Session==null)return;
        clickMy();  //点击我的
        String str=null;
        str=AOP_Related.OP_log_common_settingGoto(AL_Session);PRINT("OP_log_common_settingGoto:"+str);   //点击设置
        str=AOP_Related.OP_setting_index(AL_Session); PRINT("OP_setting_index:"+str); //点击设置
        //https://live-log.immomo.com/v3/log/client/upload 这个包发不了,暂时是发不了,一个直播日志的包
        str=AOP_Related.OP_payment_findAlipayStatus(AL_Session);PRINT("OP_payment_findAlipayStatus:"+str);   //点击账号与安全的包
        str=AOP_Related.OP_setting_index(AL_Session);  PRINT("OP_setting_index:"+str);             //点击账号与安全的包
        str=AOP_Related.OP_password_getPwdCheck(AL_Session);  PRINT("OP_setting_index:"+str);      //点击账号与安全的包

        str=AOP_Related.OP_password_setUserPwd(AL_Session); PRINT("OP_password_setUserPwd:"+str);  //点击修改密码
        str=AOP_Related.OP_setting_momologout(AL_Session); PRINT("OP_setting_momologout:"+str);  //点击修改密码
        str=AOP_Related.OP_index_unalias(accountInfo.getAccount(),_token); PRINT("OP_index_unalias:"+str);  //点击修改密码   再次抓包时候要看一下这个值  {"app_id":"e66b3f1d949694ad0e88fc83654e3b25","alias":"845051818","token":"T-dfae1a2d2710432b9b81b4b751fbcb1b-5572-126"}
        str=AOP_Related.OP_log_common_androidonlinetime(null); PRINT("OP_log_common_androidonlinetime:"+str);  //点击修改密码
        String HashKeyRet=AOP_Related.OP_photon_getPushHashKey(GL_Session,_guid);PRINT("OP_photon_getPushHashKey:"+HashKeyRet); //点击修改密码   这个session已经不是al_session了，并且这个alias是哪里的  返回个hashkey   看一下这个session是不是初始化登录时候那个session,修改密码后就又用到了
        if(HashKeyRet==null)return;
        String sn=ParamUtil.getInstance().getSn(deviceInfo.getUid(),HashKeyRet);PRINT("sn:"+sn);
        str=AOP_Related.OP_index_regwithalias(_guid,sn);PRINT("OP_index_regwithalias:"+str);    //点击修改密码   具体参数再抓{"alias":"1004039503488","sn":"hashkey_6766272a7e000278b21192236b3c3eb1_7f50ed5f25ac1eaaa982b922656e0365919d6986","device_id":"05de98a68979cbf034313a9cf1395e4a34c43840:+com.immomo.young","app_id":"e66b3f1d949694ad0e88fc83654e3b25","keystore_sha1":"04:47:FA:95:5C:5C:94:3D:B0:04:F2:28:12:32:EB:AE:95:DF:FD:84"}
        str=AOP_Related.OP_log_common_androidonlinetime(AL_Session);PRINT("OP_log_common_androidonlinetime:"+str);   //没有sorceid参数
        str=AOP_Related.OP_welcome_logs(GL_Session); PRINT("OP_welcome_logs:"+str);  //GLSession
        str=AOP_Related.OP_version_checkupdate_no_zip(GL_Session);PRINT("OP_version_checkupdate_no_zip:"+str);
        str=AOP_Related.OP_fasttoken_reg(GL_Session,_token); PRINT("OP_fasttoken_reg:"+str); //OP_index_regwithalias返回token
        str=AOP_Related.OP_log_common_abtestupload_0(GL_Session);PRINT("OP_log_common_abtestupload_0:"+str);
        str=AOP_Related.OP_log_common_abtestupload_1(GL_Session);PRINT("OP_log_common_abtestupload_1:"+str);
        str=AOP_Related.OP_token_transfer(GL_Session);PRINT("OP_token_transfer:"+str);
        str=AOP_Related.OP_appconfig_index(GL_Session);PRINT("OP_appconfig_index:"+str);
        str=AOP_Related.OP_common_androidonlinetime(GL_Session);PRINT("OP_common_androidonlinetime:"+str);
        str=AOP_Related.OP_appconfigmulti_index(GL_Session);PRINT("OP_appconfigmulti_index:"+str);
        str=AOP_Related.OP_nearby_index(GL_Session); PRINT("OP_nearby_index:"+str);   //guestid=1004052476174 和session=2CABAF07-3CB4-AF2E-08F6-3899920F71DA_G
    }


    /////////////////////////////////////TEST_END////////////////////////////////////////////////////////////////////////
}
