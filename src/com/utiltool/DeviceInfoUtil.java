package com.utiltool;

import net.sf.json.JSONObject;


//获取设备信息
public class DeviceInfoUtil {
    private String deviceID;
    private String uid;
    private String SdcardInfo;
    private String model;
    private String Serialno;
    private String hw;
    private String bindSource;
    private String device_type;
    private String _net_;
    private String androidId;
    private String router_mac;
    private String emu;
    private String mac;
    private String lng;
    private String sensorNames;
    private String version;
    private String current_wifi;
    private String network_class;
    private String SerialNumber;
    private String utdid;
    private String KernelVersion;
    private String osversion_int;
    private String MacInfo;
    private String etype;
    private String lat;
    private String BaseBandVersion;
    private String screen;
    private String BootSerialno;
    private String rom;
    private String phone_type;
    private String imei;
    private String _uid_;
    private String isRoot;
    private String buildnumber;
    private String apksign;
    private String CpuInfo;
    private String acc;
    private String phone_netWork;
    private String manufacturer;
    private String imsi;
    private String gapps;
    private String market_source;
    private String RAMSize;
    private String userAgent;
    private String divikAgent;
    private String mapid;
    private String dpp;
    private String mmuid;   //新增字段
    private String uniqueid;
    private String oaid;   //新增字段
    private String idfa;   //新增字段
    private String _iid;   //新增字段
    private String _uidType; //新增字段
    private String uniquetime;
    private String code_version; //账号密码登陆的时候请求头用
    private String public_key;
    private String aesKey;

    private static DeviceInfoUtil instance=null;

    public static DeviceInfoUtil getInstance(String deviceInfoJsonString) {
        if (instance==null)
            instance= new DeviceInfoUtil(deviceInfoJsonString);
        return instance;
    }

    public DeviceInfoUtil(){

    }

    public DeviceInfoUtil(String deviceInfoJsonString){

        deviceInfoJsonString = deviceInfoJsonString.replace("\r\n", "");
        deviceInfoJsonString = deviceInfoJsonString.replace("\t", "");
        JSONObject jsonObject = JSONObject.fromString(deviceInfoJsonString);

        this.deviceID = jsonObject.getString("deviceID");
        this.uid = jsonObject.getString("uid");
        this.SdcardInfo = jsonObject.getString("SdcardInfo");
        this.model = jsonObject.getString("model");
        this.Serialno = jsonObject.getString("Serialno");
        this.hw = jsonObject.getString("hw");
        this.bindSource = jsonObject.getString("bindSource");
        this.device_type = jsonObject.getString("device_type");
        this._net_ = jsonObject.getString("_net_");
        this.androidId = jsonObject.getString("androidId");
        this.router_mac = jsonObject.getString("router_mac");
        this.emu = jsonObject.getString("emu");
        this.mac = jsonObject.getString("mac");
        this.lng = jsonObject.getString("lng");
        this.sensorNames = jsonObject.getString("sensorNames");
        this.version = jsonObject.getString("version");
        this.current_wifi = jsonObject.getString("current_wifi");
        this.network_class = jsonObject.getString("network_class");
        this.SerialNumber = jsonObject.getString("SerialNumber");
        this.utdid = jsonObject.getString("utdid");
        this.KernelVersion = jsonObject.getString("KernelVersion");
        this.osversion_int = jsonObject.getString("osversion_int");
        this.MacInfo = jsonObject.getString("MacInfo");
        this.etype = jsonObject.getString("etype");
        this.lat = jsonObject.getString("lat");
        this.BaseBandVersion = jsonObject.getString("BaseBandVersion");
        this.screen = jsonObject.getString("screen");
        this.BootSerialno = jsonObject.getString("BootSerialno");
        this.rom = jsonObject.getString("rom");
        this.phone_type = jsonObject.getString("phone_type");
        this.imei = jsonObject.getString("imei");
        this._uid_ = jsonObject.getString("_uid_");
        this.isRoot = jsonObject.getString("isRoot");
        this.buildnumber = jsonObject.getString("buildnumber");
        this.apksign = jsonObject.getString("apksign");
        this.CpuInfo = jsonObject.getString("CpuInfo");
        this.acc = jsonObject.getString("acc");
        this.phone_netWork = jsonObject.getString("phone_netWork");
        this.manufacturer = jsonObject.getString("manufacturer");
        this.imsi = jsonObject.getString("imsi");
        this.gapps = jsonObject.getString("gapps");
        this.market_source = jsonObject.getString("market_source");
        this.RAMSize = jsonObject.getString("RAMSize");
        this.userAgent = jsonObject.getString("userAgent");
        this.divikAgent = jsonObject.getString("divikAgent");
        this.mapid = jsonObject.getString("mapid");
        this.dpp = jsonObject.getString("dpp");
        this.mmuid=jsonObject.getString("mmuid");
        this.uniqueid=jsonObject.getString("uniqueid");
        this.oaid=jsonObject.getString("oaid");
        this.idfa=jsonObject.getString("idfa");
        this._iid=jsonObject.getString("_iid");
        this._uidType=jsonObject.getString("_uidType");
        this.uniquetime=jsonObject.getString("uniquetime");
        this.code_version=jsonObject.getString("code_version");
        this.public_key=jsonObject.getString("public_key");
        this.aesKey=jsonObject.getString("aesKey").substring(0,16);
    }

    public String DeviceInfo2String() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceID", this.deviceID);
        jsonObject.put("uid", this.uid);
        jsonObject.put("SdcardInfo", this.SdcardInfo);
        jsonObject.put("model", this.model);
        jsonObject.put("Serialno", this.Serialno);
        jsonObject.put("hw", this.hw);
        jsonObject.put("bindSource", this.bindSource);
        jsonObject.put("device_type", this.device_type);
        jsonObject.put("_net_", this._net_);
        jsonObject.put("androidId", this.androidId);
        jsonObject.put("router_mac", this.router_mac);
        jsonObject.put("emu", this.emu);
        jsonObject.put("mac", this.mac);
        jsonObject.put("lng", this.lng);
        jsonObject.put("sensorNames", this.sensorNames);
        jsonObject.put("version", this.version);
        jsonObject.put("current_wifi", this.current_wifi);
        jsonObject.put("network_class", this.network_class);
        jsonObject.put("SerialNumber", this.SerialNumber);
        jsonObject.put("utdid", this.utdid);
        jsonObject.put("KernelVersion", this.KernelVersion);
        jsonObject.put("osversion_int", this.osversion_int);
        jsonObject.put("MacInfo", this.MacInfo);
        jsonObject.put("etype", this.etype);
        jsonObject.put("lat", this.lat);
        jsonObject.put("BaseBandVersion", this.BaseBandVersion);
        jsonObject.put("screen", this.screen);
        jsonObject.put("BootSerialno", this.BootSerialno);
        jsonObject.put("rom", this.rom);
        jsonObject.put("phone_type", this.phone_type);
        jsonObject.put("imei", this.imei);
        jsonObject.put("_uid_", this._uid_);
        jsonObject.put("isRoot", this.isRoot);
        jsonObject.put("buildnumber", this.buildnumber);
        jsonObject.put("apksign", this.apksign);
        jsonObject.put("CpuInfo", this.CpuInfo);
        jsonObject.put("acc", this.acc);
        jsonObject.put("phone_netWork", this.phone_netWork);
        jsonObject.put("manufacturer", this.manufacturer);
        jsonObject.put("imsi", this.imsi);
        jsonObject.put("gapps", this.gapps);
        jsonObject.put("market_source", this.market_source);
        jsonObject.put("RAMSize", this.RAMSize);
        jsonObject.put("userAgent", this.userAgent);
        jsonObject.put("divikAgent", this.divikAgent);
        jsonObject.put("mapid", this.mapid);
        jsonObject.put("dpp", this.dpp);
        jsonObject.put("mmuid",this.mmuid);
        jsonObject.put("uniqueid",this.uniqueid);
        jsonObject.put("oaid",this.oaid);
        jsonObject.put("idfa",this.idfa);
        jsonObject.put("_iid",this._iid);
        jsonObject.put("_uidType",this._uidType);
        jsonObject.put("uniquetime",this.uniquetime);
        jsonObject.put("code_version",this.code_version);
        jsonObject.put("public_key",this.public_key);
        jsonObject.put("aesKey",this.aesKey);

        return jsonObject.toString();
    }

    public String getScreen() {
        return screen;
    }

    public String getMmuid() {
        return mmuid;
    }

    public String getGapps() {
        return gapps;
    }

    public String getRouter_mac() {
        return router_mac;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public String getImei() {
        return imei;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getImsi() {
        return imsi;
    }

    public String getEmu() {
        return emu;
    }

    public String getMac() {
        return mac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getOsversion_int() {
        return osversion_int;
    }

    public String getRom() {
        return rom;
    }

    public String getUid() {
        return uid;
    }

    public String getMarket_source() {
        return market_source;
    }

    public String getModel() {
        return model;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public String getOaid() {
        return oaid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String get_uid_() {
        return _uid_;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public String getPhone_netWork() {
        return phone_netWork;
    }

    public String getDpp() {
        return dpp;
    }

    public String getIdfa() {
        return idfa;
    }

    public String get_iid() {
        return _iid;
    }

    public String getVersion() {
        return version;
    }

    public String getApksign() {
        return apksign;
    }

    public String get_net_() {
        return _net_;
    }

    public String getNetwork_class() {
        return network_class;
    }

    public String get_uidType() {
        return _uidType;
    }

    public String getUniquetime() {
        return uniquetime;
    }

    public String getAcc() {
        return acc;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getSdcardInfo() {
        return SdcardInfo;
    }

    public String getSerialno() {
        return Serialno;
    }

    public String getHw() {
        return hw;
    }

    public String getBindSource() {
        return bindSource;
    }

    public String getLng() {
        return lng;
    }

    public String getSensorNames() {
        return sensorNames;
    }

    public String getCurrent_wifi() {
        return current_wifi;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public String getUtdid() {
        return utdid;
    }

    public String getKernelVersion() {
        return KernelVersion;
    }

    public String getMacInfo() {
        return MacInfo;
    }

    public String getEtype() {
        return etype;
    }

    public String getLat() {
        return lat;
    }

    public String getBaseBandVersion() {
        return BaseBandVersion;
    }

    public String getBootSerialno() {
        return BootSerialno;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public String getCpuInfo() {
        return CpuInfo;
    }

    public String getRAMSize() {
        return RAMSize;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDivikAgent() {
        return divikAgent;
    }

    public String getMapid() {
        return mapid;
    }

    public String getCode_version() {
        return code_version;
    }


    public String getPublic_key() {
        return public_key;
    }

    public String getAesKey() {
        return aesKey;
    }
}
