package com.constantfield;

public class RequestUrl {

    //Initial login
    public static final String INI_index_config ="https://live-api.immomo.com/v3/index/config?fu=";
    public static final String INI_abtest_index ="https://api-mini.immomo.com/v2/setting/abtest/index?fu=";
    public static final String INI_welcome_logs ="https://api-mini.immomo.com/v1/welcome/logs?fu=";
    public static final String INI_appconfig_index ="https://api-mini.immomo.com/v1/appconfig/index?fu=";
    public static final String INI_log_common_abtestupload_0 ="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String INI_log_common_upload="https://api-log.immomo.com/v1/log/common/upload?fu=";
    public static final String INI_log_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fu=";
    //Guest login
    public static final String GL_log_common_abtestupload_1 ="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String GL_login_index="https://api-mini.immomo.com/guest/login/index?fu=";
    public static final String GL_photon_getPushHashKey ="https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fu=";
    public static final String GL_log_common_abtestupload_2 ="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String GL_log_common_abtestupload_3 ="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String GL_version_getupdatelist ="https://api-mini.immomo.com/v1/mk/version/getupdatelist?fu=";
    public static final String GL_version_checkupdate ="https://api-mini.immomo.com/v1/mk/version/checkupdate?fu=";
    public static final String GL_log_common_abtestupload_4 ="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String GL_discover_category ="https://api-mini.immomo.com/v1/emotion/discover/category?fu=";
    public static final String GL_appconfigmulti_index ="https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fu=";
    public static final String GL_index_regwithalias ="https://paas-push-api.immomo.com/push/index/regwithalias?appsr=";
    public static final String GL_client_ipInfo ="https://api.immomo.com/v2/log/client/ipInfo?fu=";
    public static final String GL_fasttoken_reg ="https://api-alpha.immomo.com/momopush/fasttoken/reg?fu=";

    //Account login  40 个包
    public static final String AL_log_common_abtestupload_0="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String AL_log_common_permissionupload_0="https://api-log.immomo.com/v1/log/common/permissionupload?fu=";
    public static final String AL_log_common_permissionupload_1="https://api-log.immomo.com/v1/log/common/permissionupload?fu=";
    public static final String AL_v2_login ="https://api-mini.immomo.com/api/v2/login?fu=";
    public static final String AL_my_base_no_zip ="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String AL_log_common_abtestupload_1="https://api-log.immomo.com/v1/log/common/abtestupload?fr=";
    public static final String AL_index_unalias ="https://paas-push-api.immomo.com/push/index/unalias?appsr=";
    public static final String AL_photon_getPushHashKey ="https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr=";
    public static final String AL_log_common_upload="https://api-log.immomo.com/v1/log/common/upload?fr=";
    public static final String AL_index_regwithalias ="https://paas-push-api.immomo.com/push/index/regwithalias?appsr=";
    public static final String AL_v3_index_config ="https://live-api.immomo.com/v3/index/config?fr=";
    public static final String AL_config_user_index ="https://live-api.immomo.com/v3/config/user/index?fr=";
    public static final String AL_effectListNew ="https://live-api.immomo.com/v3/room/p/effectListNew?fr=";
    public static final String AL_log_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fr=";  //*2次
    public static final String AL_business_getAllSwitch ="https://api-mini.immomo.com/v2/notify/business/getAllSwitch?fr=";
    public static final String AL_message_historyv2 ="https://api-mini.immomo.com/v1/user/message/historyv2?fr=";
    public static final String AL_fasttoken_reg ="https://api-alpha.immomo.com/momopush/fasttoken/reg?fr=";
    public static final String AL_productListsAll ="https://live-api.immomo.com/v3/room/sale/productListsAll?fr=";
    public static final String AL_custom_package ="https://api-mini.immomo.com/v1/emotion/custom/package?fr=";
    public static final String AL_v2_welcome ="https://api-mini.immomo.com/api/banners/v2/welcome?fr=";
    public static final String AL_token_transfer ="https://api-mini.immomo.com/v1/account/token/transfer?fr=";
    public static final String AL_appconfig_index ="https://api-mini.immomo.com/v1/appconfig/index?fr=";
    public static final String AL_together_checkreddot ="https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=";
    public static final String AL_discover_category ="https://api-mini.immomo.com/v1/emotion/discover/category?fr=";
    public static final String AL_appconfigmulti_index ="https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=";
    public static final String AL_abtest_index ="https://api-mini.immomo.com/v2/setting/abtest/index?fr=";
    public static final String AL_my_base_zip="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String AL_official_config ="https://api-mini.immomo.com/api/official/config?fr=";
    public static final String AL_wifi_upload ="https://api-mini.immomo.com/v1/collection/wifi/upload?fr=";
    public static final String AL_special_getallindustry ="https://api-mini.immomo.com/v1/user/special/getallindustry?fr=";
    public static final String AL_webapp_wholelists ="https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr=";
    public static final String AL_friend_getGlobalSearchUsers ="https://api-mini.immomo.com/v1/relation/friend/getGlobalSearchUsers?fr=";
    public static final String AL_list_my ="https://api-mini.immomo.com/api/emotion/list/my?fr=";
    public static final String AL_log_common_statfileupload_0 ="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";   //参数需要找
    public static final String AL_emotion_update ="https://api-mini.immomo.com/api/emotion/update?fr=";
    public static final String AL_service_getRedDot ="https://api-vip.immomo.com/v1/vgift/service/getRedDot?fr=";
    public static final String AL_log_common_statfileupload_1="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String AL_people_lists ="https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr=";
    public static final String AL_together_generalmsg ="https://api-mini.immomo.com/v2/nearby/together/generalmsg?fr=";

    //cache login  缓存登陆
    public static final String CL_index_regwithalias="https://paas-push-api.immomo.com/push/index/regwithalias?appsr=";
    public static final String CL_fasttoken_reg="https://api-alpha.immomo.com/momopush/fasttoken/reg?fr=";
    public static final String CL_index_config="https://live-api.immomo.com/v3/index/config?fr=";   //参数固定 lastRespTime
    public static final String CL_abtest_index="https://api-mini.immomo.com/v2/setting/abtest/index?fr=";
    public static final String CL_welcome_logs="https://api-mini.immomo.com/v1/welcome/logs?fr=";
    public static final String CL_version_checkupdate="https://api-mini.immomo.com/v1/mk/version/checkupdate?fr=";
    public static final String CL_appconfig_index="https://api-mini.immomo.com/v1/appconfig/index?fr=";    //参数也是有两个字段为0
    public static final String CL_api_banners="https://api-mini.immomo.com/api/banners?fr=";    //参数需要找，都是固定值
    public static final String CL_client_ipInfo="https://api.immomo.com/v2/log/client/ipInfo?fr=";
    public static final String CL_appconfigmulti_index="https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=";
    public static final String CL_v2_welcome="https://api-mini.immomo.com/api/banners/v2/welcome?fr=";     //router_mac 是本地wiffi
    public static final String CL_together_checkreddot="https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=";
    public static final String CL_log_common_upload_0 ="https://api-log.immomo.com/v1/log/common/upload?fr=";
    public static final String CL_log_common_upload_1 ="https://api-log.immomo.com/v1/log/common/upload?fr=";
    public static final String CL_my_base_zip="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String CL_log_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fr=";
    public static final String CL_service_getRedDot="https://api-vip.immomo.com/v1/vgift/service/getRedDot?fr=";
    public static final String CL_webapp_wholelists="https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr=";
    public static final String CL_log_common_statfileupload="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String CL_people_lists="https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr="; //这个cell id等很多参数要找
    public static final String CL_together_generalmsg="https://api-mini.immomo.com/v2/nearby/together/generalmsg?fr=";   ////调用get_AL_together_generalmsg_Bod
    public static final String CL_token_transfer="https://api-mini.immomo.com/v1/account/token/transfer?fr=";
    public static final String CL_version_getupdatelist="https://api-mini.immomo.com/v1/mk/version/getupdatelist?fr=";   ///参数bids固定
    public static final String CL_friend_getGlobalSearchUsers="https://api-mini.immomo.com/v1/relation/friend/getGlobalSearchUsers?fr=";
    public static final String CL_discover_category="https://api-mini.immomo.com/v1/emotion/discover/category?fr=";
    public static final String CL_android_check="https://api-mini.immomo.com/v1/download/android/check?fr=";
    public static final String CL_log_uploadlocalapps="https://api-mini.immomo.com/api/log/uploadlocalapps?fr=";

    //operating  界面操作的一些包
    public static final String OP_play_discover="https://api-mini.immomo.com/v2/fast/homepage/play/discover?fr=";  //点击我的
    public static final String OP_log_common_statfileupload="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String OP_my_base_click ="https://api-mini.immomo.com/v2/user/my/base?fr=";  //主动点击我的
    public static final String OP_my_base_checking_data="https://api-mini.immomo.com/v2/user/my/base?fr=";   //点击查看资料后
    public static final String OP_feed_profile="https://api-mini.immomo.com/v1/user/feed/profile?fr=";
    public static final String OP_user_timeline="https://api-mini.immomo.com/v1/feed/user/timeline?fr=";
    public static final String OP_clientLog_upload="https://cm.immomo.com/api/clientLog/upload";
    public static final String OP_log_common_statfileupload_MYPROFILR_RETURN ="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";  //查看个人资料后，点返回
    public static final String OP_version_checkupdate="https://api-mini.immomo.com/v1/mk/version/checkupdate?fr=";
    public static final String OP_user_getGuideSwitch="https://api-mini.immomo.com/v2/feed/user/getGuideSwitch?fr=";
    public static final String OP_user_getGuideConfig="https://api-mini.immomo.com/v2/feed/user/getGuideConfig?fr=";
    public static final String OP_log_common_statfileupload_sendDynamic_0 ="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String OP_site_auto_select="https://api-mini.immomo.com/v1/feed/site/auto_select?fr=";
    public static final String OP_publish_check ="https://api-mini.immomo.com/v1/feed/publish/check?fr=";   //发送动态
    public static final String OP_publish_send_index ="https://api-mini.immomo.com/v3/feed/publish/send/index?fr=";
    public static final String OP_feed_read_kill="https://api-mini.immomo.com/v1/feed/read/kill?fr=";
    public static final String OP_log_common_statfileupload_sendDynamic_1="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String LOG="https://api-log.immomo.com/v2/log/client/index?fr=";   //日志加密了
    public static final String OP_log_common_statfileupload_sendDynamic_return="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";  //发完动态后点返回
    public static final String OP_relation_activate="https://api-mini.immomo.com/v2/user/relation/activate?fr=";
    public static final String OP_common_statfileupload_NEW_LOG="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";   //点击消息产生的日志
    public static final String OP_together_checkreddot="https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=";
    public static final String OP_nearby_lists="https://api-mini.immomo.com/v2/fast/feed/nearby/lists?fr=";
    public static final String OP_phone_sendNoBindPhoneCard="https://api-mini.immomo.com/api/safe/phone/sendNoBindPhoneCard?fr=";

    //修改密码的包
    public static final String OP_log_common_settingGoto="https://api-log.immomo.com/v1/log/common/settingGoto?fr=";
    //点击设置后发送  点击账号与安全发送
    public static final String OP_setting_index="https://api-mini.immomo.com/api/safe/setting/index?fr=";
    //点击账号与安全发送
    public static final String OP_payment_findAlipayStatus ="https://api-mini.immomo.com/v1/wallet/payment/findAlipayStatus?fr=";
    //点击账号与安全发送
    public static final String OP_password_getPwdCheck ="https://api-mini.immomo.com/v2/core/password/getPwdCheck?fr=";
    //设置密码
    public static final String OP_password_setUserPwd ="https://api-mini.immomo.com/v2/core/password/setUserPwd?fr=";
    public static final String OP_log_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fr=";  //没session
    public static final String OP_setting_momologout ="https://api-mini.immomo.com/api/setting/momologout?fr=";
    public static final String OP_index_unalias="https://paas-push-api.immomo.com/push/index/unalias?appsr=";
    public static final String OP_photon_getPushHashKey ="https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr=";  //游客登录的session
    //调这个OP_log_common_androidonlinetime
    public static final String OP_index_regwithalias="https://paas-push-api.immomo.com/push/index/regwithalias?appsr=";
    public static final String OP_abtest_index ="https://api-mini.immomo.com/v2/setting/abtest/index?fr=";  //GL
    public static final String OP_log_common_abtestupload_0="https://api-log.immomo.com/v1/log/common/abtestupload?fr="; //GL
    public static final String OP_welcome_logs ="https://api-mini.immomo.com/v1/welcome/logs?fr="; //GL
    public static final String OP_token_transfer ="https://api-mini.immomo.com/v1/account/token/transfer?fr=";  //GL
    public static final String OP_version_getupdatelist ="https://api-mini.immomo.com/v1/mk/version/getupdatelist?fr=";
    //public static final String OP_version_checkupdate ="https://api-mini.immomo.com/v1/mk/version/checkupdate?fu=";
    public static final String OP_version_checkupdate_no_zip ="https://api-mini.immomo.com/v1/mk/version/checkupdate?fr=";
    public static final String OP_nearby_index ="https://api-mini.immomo.com/guest/nearby/index?fr=";
    public static final String OP_log_common_abtestupload_1="https://api-log.immomo.com/v1/log/common/abtestupload?fr="; //log的session全是游客登录返回的
    public static final String OP_fasttoken_reg="https://api-alpha.immomo.com/momopush/fasttoken/reg?fr=";
    public static final String OP_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fr=";  //
    public static final String OP_appconfig_index ="https://api-mini.immomo.com/v1/appconfig/index?fr=";
    public static final String OP_appconfigmulti_index ="https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=";


    //注册账号  注册账号使用的是空账号的文件,注册完毕后才把相关信息写入账号信息中
    public static final String REG_log_common_permissionupload_0="https://api-log.immomo.com/v1/log/common/permissionupload?fu=";
    public static final String REG_log_common_permissionupload_1="https://api-log.immomo.com/v1/log/common/permissionupload?fu=";
    public static final String REG_common_abtestupload_0="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";//{"log":"guest_button_validate:first_enter","startTime":0,"endTime":0}
    public static final String REG_verifycode_send ="https://api-mini.immomo.com/api/safe/verifycode/send?fu=";
    public static final String REG_common_abtestupload_1="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String REG_login_index ="https://api-mini.immomo.com/v2/core/login/index?fu=";  //返回一个token，在上传头像时用
    public static final String REG_common_abtestupload_2="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    //填写资料
    public static final String REG_common_abtestupload_3="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String REG_common_abtestupload_4="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    public static final String REG_register_checkSimple ="https://api-mini.immomo.com/v2/core/register/checkSimple?fu=";
    public static final String REG_common_abtestupload_5="https://api-log.immomo.com/v1/log/common/abtestupload?fu=";
    //上传头像及其之后发送的包
    public static final String REG_register_index ="https://api-mini.immomo.com/v2/core/register/index?fu="; //返回momoid  这里要把账号写入账号文件中 返回guid
    public static final String REG_common_abtestupload_6="https://api-log.immomo.com/v1/log/common/abtestupload?fr="; //log的session全是游客登录返回的
    public static final String REG_my_base_no_zip ="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String REG_common_abtestupload_7="https://api-log.immomo.com/v1/log/common/abtestupload?fr=";
    public static final String REG_personal_avatar ="https://api-mini.immomo.com/api/personal/avatar?fr="; //这里需要用guid
    public static final String REG_setting_recallpush ="https://api-mini.immomo.com/api/setting/recallpush?fr=";  //发两次
    public static final String REG_setting_sound_no_zip ="https://api-mini.immomo.com/api/setting/sound?fr=";
    public static final String REG_index_config="https://live-api.immomo.com/v3/index/config?fr=";
    public static final String REG_photon_getPushHashKey ="https://api-mini.immomo.com/v2/setting/photon/getPushHashKey?fr=";  //返回一个hashkey
    public static final String REG_business_getAllSwitch ="https://api-mini.immomo.com/v2/notify/business/getAllSwitch?fr=";
    public static final String REG_log_common_upload="https://api-log.immomo.com/v1/log/common/upload?fr=";
    public static final String REG_common_androidonlinetime="https://api-log.immomo.com/v1/log/common/androidonlinetime?fr=";  //发送两次,第二次在index之后
    public static final String REG_config_user_index="https://live-api.immomo.com/v3/config/user/index?fr=";
    public static final String REG_effectListNew ="https://live-api.immomo.com/v3/room/p/effectListNew?fr=";
    public static final String REG_setting_pushlive ="https://api-mini.immomo.com/api/setting/pushlive?fr=";
    public static final String REG_token_transfer ="https://api-mini.immomo.com/v1/account/token/transfer?fr=";
    public static final String REG_notice_config ="https://api-mini.immomo.com/v2/growth/notice/config?fr=";
    public static final String REG_appconfig_index="https://api-mini.immomo.com/v1/appconfig/index?fr=";  //游客INI_appconfig_index登录的返回
    public static final String REG_productListsAll="https://live-api.immomo.com/v3/room/sale/productListsAll?fr=";
    public static final String REG_banners_v2_welcome="https://api-mini.immomo.com/api/banners/v2/welcome?fr=";
    public static final String REG_api_profiles ="https://api-mini.immomo.com/api/profiles/";
    public static final String REG_discover_category="https://api-mini.immomo.com/v1/emotion/discover/category?fr=";
    public static final String REG_together_checkreddot="https://api-mini.immomo.com/v2/nearby/together/checkreddot?fr=";
    public static final String REG_appconfigmulti_index ="https://api-mini.immomo.com/v2/setting/appconfigmulti/index?fr=";
    public static final String REG_my_base_zip ="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String REG_official_config ="https://api-mini.immomo.com/api/official/config?fr=";
    public static final String REG_setting_sound_zip ="https://api-mini.immomo.com/api/setting/sound?fr=";
    public static final String REG_friend_getGlobalSearchUsers ="https://api-mini.immomo.com/v1/relation/friend/getGlobalSearchUsers?fr=";
    public static final String REG_webapp_wholelists ="https://api-mini.immomo.com/v2/fast/webapp/wholelists?fr=";
    public static final String REG_wifi_upload ="https://api-mini.immomo.com/v1/collection/wifi/upload?fr=";
    public static final String REG_special_getallindustry ="https://api-mini.immomo.com/v1/user/special/getallindustry?fr=";
    public static final String REG_list_my ="https://api-mini.immomo.com/api/emotion/list/my?fr=";
    public static final String REG_log_common_statfileupload_0="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String REG_emotion_update ="https://api-mini.immomo.com/api/emotion/update?fr=";
    public static final String REG_service_getRedDot ="https://api-vip.immomo.com/v1/vgift/service/getRedDot?fr=";
    public static final String REG_log_common_statfileupload_1="https://api-log.immomo.com/v1/log/common/statfileupload?fr=";
    public static final String REG_index_unalias="https://paas-push-api.immomo.com/push/index/unalias?appsr=";
    public static final String REG_my_base_zip_1 ="https://api-mini.immomo.com/v2/user/my/base?fr=";
    public static final String REG_index_regwithalias="https://paas-push-api.immomo.com/push/index/regwithalias?appsr=";
    public static final String REG_fasttoken_reg="https://api-alpha.immomo.com/momopush/fasttoken/reg?fr=";
    public static final String REG_abtest_index ="https://api-mini.immomo.com/v2/setting/abtest/index?fr=";
    public static final String REG_people_lists ="https://api-mini.immomo.com/v2/fast/homepage/people/lists?fr=";
    public static final String REG_together_generalmsg ="https://api-mini.immomo.com/v2/nearby/together/generalmsg?fr=";




    //接码平台账号登陆
    public static final String ReceiveCodeLoginUrl = "http://103.100.61.153:3690/web/api/login/verify";

    public static final String ReceiveCodeGetCodeUrl="http://103.100.61.153:3690/user/getCode";   //点击获取验证码
    //获取手机号码
    public static final String ReceiveCodeGetPhoneUrl="http://103.100.61.153:3690/web/api/getNumber/4/1";
    //获取验证码
    public static final String ReceiveCodeGetVerifyCodeUrl="http://103.100.61.153:3690/web/api/getSms/4/";


    ////////////////////////F4接码////////////////////////////////////////////////

    public static final String F4Work="https://f4.work/";
    public static final String F4WorkLogin="https://f4.work/login.php";
    public static final String F4WorkChinaList="https://f4.work/list_china.php";
    public static final String F4WorkJiemaChannel="https://f4.work/list_jiema.php";

}
