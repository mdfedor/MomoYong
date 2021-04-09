package com.utiltool;

import net.sf.json.JSONObject;

public class AccountInfoUtil {

    private String birthday="";
    private String gender="";
    private String movie="";
    private String session="";
    private String countrycode="";
    private String book="";
    private String sign="";
    private String industry="";
    private String hangout="";
    private String uptoken="";
    private String password="";
    private String music="";
    private String school="";
    private String interest="";
    private String company="";
    private String tieba="";
    private String relationship="";
    private String email="";
    private String hometown="";
    private String website="";
    private String signcount="";
    private String avatar="";
    private String checkDevToken="";
    private String credit_star="";
    private String phone="";
    private String name="";
    private String job="";
    private String profileVer="";
    private String account="";//账号
    private String publickey="";    //生成x_kv和ck
    private String aesKey="";       //生成mzip用的
    private String curResource="";  //appcongfig用到
    private String hashKey="";



    private static AccountInfoUtil instance=null;

    public static AccountInfoUtil getInstance(String accountInfoJsonStr) {
        if (instance==null)
            instance= new AccountInfoUtil(accountInfoJsonStr);
        return instance;
    }

    public AccountInfoUtil(){

    }

    //账号注册需要填写名字，生日，性别三个信息  初始化账号信息，设备信息，初始化的数据可以放在构造函数中，关键看怎么去获取，构造完成后直接可以写入
    public AccountInfoUtil(String name,String birthday,String sex){
        this.name=name;
        this.birthday=birthday;
        this.gender=sex;
    }

    public AccountInfoUtil(String accountInfoJsonStr){

        accountInfoJsonStr = accountInfoJsonStr.replace("\r\n", "");
        accountInfoJsonStr = accountInfoJsonStr.replace("\t", "");
        JSONObject jsonObject = JSONObject.fromString(accountInfoJsonStr);

        //31个字段
        this.birthday=jsonObject.getString("birthday");
        this.gender=jsonObject.getString("gender");
        this.movie=jsonObject.getString("movie");
        this.session=jsonObject.getString("session");
        this.countrycode=jsonObject.getString("countrycode");
        this.book=jsonObject.getString("book");
        this.sign=jsonObject.getString("sign");
        this.industry=jsonObject.getString("industry");
        //this.momoid=jsonObject.getString("momoid");
        this.hangout=jsonObject.getString("hangout");
        this.uptoken=jsonObject.getString("uptoken");
        this.password=jsonObject.getString("password");
        this.music=jsonObject.getString("music");
        this.school=jsonObject.getString("school");
        this.interest=jsonObject.getString("interest");
        this.company=jsonObject.getString("company");
        this.tieba=jsonObject.getString("tieba");
        this.relationship=jsonObject.getString("relationship");
        this.email=jsonObject.getString("email");
        this.hometown=jsonObject.getString("hometown");
        this.website=jsonObject.getString("website");
        this.signcount=jsonObject.getString("signcount");
        this.avatar=jsonObject.getString("avatar");
        this.checkDevToken=jsonObject.getString("checkDevToken");
        this.credit_star=jsonObject.getString("credit_star");
        this.phone=jsonObject.getString("phone");
        this.name=jsonObject.getString("name");
        this.job=jsonObject.getString("job");
        this.profileVer=jsonObject.getString("profileVer");
        this.account=jsonObject.getString("account");
        this.publickey=jsonObject.getString("publickey");
        this.aesKey=jsonObject.getString("aesKey");
        this.curResource=jsonObject.getString("curResource");
        this.hashKey=jsonObject.getString("hashKey");

    }

    public String AccountInfo2String(){
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
        jsonObject.put("birthday",this.birthday);
        jsonObject.put("gender",this.gender);
        jsonObject.put("movie",this.movie);
        jsonObject.put("session",this.session);
        jsonObject.put("countrycode",this.countrycode);
        jsonObject.put("book",this.book);
        jsonObject.put("sign",this.sign);
        jsonObject.put("industry",this.industry);
        jsonObject.put("hangout",this.hangout);
        jsonObject.put("uptoken",this.uptoken);
        jsonObject.put("uptoken",this.uptoken);
        jsonObject.put("password",this.password);
        jsonObject.put("music",this.music);
        jsonObject.put("school",this.school);
        jsonObject.put("interest",this.interest);
        jsonObject.put("company",this.company);
        jsonObject.put("tieba",this.tieba);
        jsonObject.put("relationship",this.relationship);
        jsonObject.put("email",this.email);
        jsonObject.put("hometown",this.hometown);
        jsonObject.put("website",this.website);
        jsonObject.put("signcount",this.signcount);
        jsonObject.put("avatar",this.avatar);
        jsonObject.put("checkDevToken",this.checkDevToken);
        jsonObject.put("credit_star",this.credit_star);
        jsonObject.put("phone",this.phone);
        jsonObject.put("name",this.name);
        jsonObject.put("job",this.job);
        jsonObject.put("profileVer",this.profileVer);
        jsonObject.put("account",this.account);
        jsonObject.put("publickey",this.publickey);
        jsonObject.put("aesKey",this.aesKey);
        jsonObject.put("curResource",this.curResource);
        jsonObject.put("hashKey",this.hashKey);

        return jsonObject.toString();
    }

    //保存某个字段到文件中 param  路径,内容
    public void SaveValue2File(String filePath,String Context){
        try {
            StringUtil.getInstance().Save(filePath,Context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHangout() {
        return hangout;
    }

    public void setHangout(String hangout) {
        this.hangout = hangout;
    }

    public String getUptoken() {
        return uptoken;
    }

    public void setUptoken(String uptoken) {
        this.uptoken = uptoken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTieba() {
        return tieba;
    }

    public void setTieba(String tieba) {
        this.tieba = tieba;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSigncount() {
        return signcount;
    }

    public void setSigncount(String signcount) {
        this.signcount = signcount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCheckDevToken() {
        return checkDevToken;
    }

    public void setCheckDevToken(String checkDevToken) {
        this.checkDevToken = checkDevToken;
    }

    public String getCredit_star() {
        return credit_star;
    }

    public void setCredit_star(String credit_star) {
        this.credit_star = credit_star;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProfileVer() {
        return profileVer;
    }

    public void setProfileVer(String profileVer) {
        this.profileVer = profileVer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getCurResource() {
        return curResource;
    }

    public void setCurResource(String curResource) {
        this.curResource = curResource;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
