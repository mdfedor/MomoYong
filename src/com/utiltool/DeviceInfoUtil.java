package com.utiltool;

import net.sf.json.JSONObject;


//获取设备信息
public class DeviceInfoUtil {
    private String uid="";
    private String model="";
    private String Serialno="";
    private String hw="";
    private String device_type="";
    private String _net_="";
    private String androidId="";
    private String router_mac="";
    private String emu="";
    private String mac="";
    private String lng="";
    private String sensorNames="";
    private String current_wifi="";
    private String network_class="";
    private String SerialNumber="";
    private String utdid="";
    private String KernelVersion="";
    private String osversion_int="";
    private String MacInfo="";
    private String etype="";
    private String lat="";
    private String BaseBandVersion="";
    private String screen="";
    private String BootSerialno="";
    private String rom="";
    private String phone_type="";
    private String imei="";
    private String _uid_="";
    private String isRoot="";
    private String buildnumber="";
    private String apksign="";
    private String CpuInfo="";
    private String acc="";
    private String phone_netWork="";
    private String manufacturer="";
    private String imsi="";
    private String gapps="";
    private String market_source="";
    private String RAMSize="";
    private String userAgent="";
    private String dpp="";
    private String mmuid="";   //新增字段
    private String uniqueid="";
    private String oaid="";   //新增字段
    private String idfa="";   //新增字段
    private String _iid="";   //新增字段
    private String _uidType=""; //新增字段
    private String code_version=""; //账号密码登陆的时候请求头用
    private String public_key="";
    private String aesKey="";
    private String cpuString="";
    private String native_ua="";
    private String sn="";
    private String net="";

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

        this.uid = jsonObject.getString("uid");
        this.model = jsonObject.getString("model");
        this.Serialno = jsonObject.getString("Serialno");
        this.hw = jsonObject.getString("hw");
        this.device_type = jsonObject.getString("device_type");
        this._net_ = jsonObject.getString("_net_");
        this.androidId = jsonObject.getString("androidId");
        this.router_mac = jsonObject.getString("router_mac");
        this.emu = jsonObject.getString("emu");
        this.mac = jsonObject.getString("mac");
        this.lng = jsonObject.getString("lng");
        this.sensorNames = jsonObject.getString("sensorNames");
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
        this.dpp = jsonObject.getString("dpp");
        this.mmuid=jsonObject.getString("mmuid");
        this.uniqueid=jsonObject.getString("uniqueid");
        this.oaid=jsonObject.getString("oaid");
        this.idfa=jsonObject.getString("idfa");
        this._iid=jsonObject.getString("_iid");
        this._uidType=jsonObject.getString("_uidType");
        this.code_version=jsonObject.getString("code_version");
        this.public_key=jsonObject.getString("public_key");
        this.aesKey=jsonObject.getString("aesKey").substring(0,16);
        this.cpuString=jsonObject.getString("cpuString");
        this.native_ua=jsonObject.getString("native_ua");
        this.sn=jsonObject.getString("sn");
        this.net=jsonObject.getString("net");
    }

    public String DeviceInfo2String() {

        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        jsonObject.put("uid", this.uid);
        jsonObject.put("model", this.model);
        jsonObject.put("Serialno", this.Serialno);
        jsonObject.put("hw", this.hw);
        jsonObject.put("device_type", this.device_type);
        jsonObject.put("_net_", this._net_);
        jsonObject.put("androidId", this.androidId);
        jsonObject.put("router_mac", this.router_mac);
        jsonObject.put("emu", this.emu);
        jsonObject.put("mac", this.mac);
        jsonObject.put("lng", this.lng);
        jsonObject.put("sensorNames", this.sensorNames);
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
        jsonObject.put("dpp", this.dpp);
        jsonObject.put("mmuid",this.mmuid);
        jsonObject.put("uniqueid",this.uniqueid);
        jsonObject.put("oaid",this.oaid);
        jsonObject.put("idfa",this.idfa);
        jsonObject.put("_iid",this._iid);
        jsonObject.put("_uidType",this._uidType);
        jsonObject.put("code_version",this.code_version);
        jsonObject.put("public_key",this.public_key);
        jsonObject.put("aesKey",this.aesKey);
        jsonObject.put("cpuString",this.cpuString);
        jsonObject.put("native_ua",this.native_ua);
        jsonObject.put("sn",this.sn);
        jsonObject.put("net",this.net);
        return jsonObject.toString();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialno() {
        return Serialno;
    }

    public void setSerialno(String serialno) {
        Serialno = serialno;
    }

    public String getHw() {
        return hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }


    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String get_net_() {
        return _net_;
    }

    public void set_net_(String _net_) {
        this._net_ = _net_;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getRouter_mac() {
        return router_mac;
    }

    public void setRouter_mac(String router_mac) {
        this.router_mac = router_mac;
    }

    public String getEmu() {
        return emu;
    }

    public void setEmu(String emu) {
        this.emu = emu;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSensorNames() {
        return sensorNames;
    }

    public void setSensorNames(String sensorNames) {
        this.sensorNames = sensorNames;
    }

    public String getCurrent_wifi() {
        return current_wifi;
    }

    public void setCurrent_wifi(String current_wifi) {
        this.current_wifi = current_wifi;
    }

    public String getNetwork_class() {
        return network_class;
    }

    public void setNetwork_class(String network_class) {
        this.network_class = network_class;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getUtdid() {
        return utdid;
    }

    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }

    public String getKernelVersion() {
        return KernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        KernelVersion = kernelVersion;
    }

    public String getOsversion_int() {
        return osversion_int;
    }

    public void setOsversion_int(String osversion_int) {
        this.osversion_int = osversion_int;
    }

    public String getMacInfo() {
        return MacInfo;
    }

    public void setMacInfo(String macInfo) {
        MacInfo = macInfo;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBaseBandVersion() {
        return BaseBandVersion;
    }

    public void setBaseBandVersion(String baseBandVersion) {
        BaseBandVersion = baseBandVersion;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getBootSerialno() {
        return BootSerialno;
    }

    public void setBootSerialno(String bootSerialno) {
        BootSerialno = bootSerialno;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String get_uid_() {
        return _uid_;
    }

    public void set_uid_(String _uid_) {
        this._uid_ = _uid_;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(String buildnumber) {
        this.buildnumber = buildnumber;
    }

    public String getApksign() {
        return apksign;
    }

    public void setApksign(String apksign) {
        this.apksign = apksign;
    }

    public String getCpuInfo() {
        return CpuInfo;
    }

    public void setCpuInfo(String cpuInfo) {
        CpuInfo = cpuInfo;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPhone_netWork() {
        return phone_netWork;
    }

    public void setPhone_netWork(String phone_netWork) {
        this.phone_netWork = phone_netWork;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getGapps() {
        return gapps;
    }

    public void setGapps(String gapps) {
        this.gapps = gapps;
    }

    public String getMarket_source() {
        return market_source;
    }

    public void setMarket_source(String market_source) {
        this.market_source = market_source;
    }

    public String getRAMSize() {
        return RAMSize;
    }

    public void setRAMSize(String RAMSize) {
        this.RAMSize = RAMSize;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    public String getDpp() {
        return dpp;
    }

    public void setDpp(String dpp) {
        this.dpp = dpp;
    }

    public String getMmuid() {
        return mmuid;
    }

    public void setMmuid(String mmuid) {
        this.mmuid = mmuid;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String get_iid() {
        return _iid;
    }

    public void set_iid(String _iid) {
        this._iid = _iid;
    }

    public String get_uidType() {
        return _uidType;
    }

    public void set_uidType(String _uidType) {
        this._uidType = _uidType;
    }


    public String getCode_version() {
        return code_version;
    }

    public void setCode_version(String code_version) {
        this.code_version = code_version;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getCpuString() {
        return cpuString;
    }

    public void setCpuString(String cpuString) {
        this.cpuString = cpuString;
    }

    public String getNative_ua() {
        return native_ua;
    }

    public void setNative_ua(String native_ua) {
        this.native_ua = native_ua;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
}
