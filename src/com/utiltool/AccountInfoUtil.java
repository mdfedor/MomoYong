package com.utiltool;

import net.sf.json.JSONObject;

public class AccountInfoUtil {

    private String birthday;
    private String gender;
    private String movie;
    private String session;
    private String countrycode;
    private String book;
    private String sign;
    private String industry;
    private String hangout;
    private String uptoken;
    private String password;
    private String music;
    private String school;
    private String interest;
    private String company;
    private String tieba;
    private String relationship;
    private String email;
    private String hometown;
    private String website;
    private String signcount;
    private String avatar;
    private String checkDevToken;
    private String credit_star;
    private String phone;
    private String name;
    private String job;
    private String profileVer;
    private String account;//账号
    private String publickey;    //生成x_kv和ck
    private String aesKey;       //生成mzip用的


    private static AccountInfoUtil instance=null;

    public static AccountInfoUtil getInstance(String accountInfoJsonStr) {
        if (instance==null)
            instance= new AccountInfoUtil(accountInfoJsonStr);
        return instance;
    }

    public AccountInfoUtil(){

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
        this.publickey=jsonObject.getString("public_key");
        this.aesKey=jsonObject.getString("aesKey");
    }

    public String AccountInfo2String(){
        JSONObject jsonObject = new JSONObject();
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

        return jsonObject.toString();
    }


    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getMovie() {
        return movie;
    }

    public String getSession() {
        return session;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getBook() {
        return book;
    }

    public String getSign() {
        return sign;
    }

    public String getIndustry() {
        return industry;
    }

    public String getHangout() {
        return hangout;
    }

    public String getUptoken() {
        return uptoken;
    }

    public String getPassword() {
        return password;
    }

    public String getMusic() {
        return music;
    }

    public String getSchool() {
        return school;
    }

    public String getInterest() {
        return interest;
    }

    public String getCompany() {
        return company;
    }

    public String getTieba() {
        return tieba;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getEmail() {
        return email;
    }

    public String getHometown() {
        return hometown;
    }

    public String getWebsite() {
        return website;
    }

    public String getSigncount() {
        return signcount;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCheckDevToken() {
        return checkDevToken;
    }

    public String getCredit_star() {
        return credit_star;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getProfileVer() {
        return profileVer;
    }


    public String getAccount() {
        return account;
    }

    public String getPublickey() {
        return publickey;
    }


    public String getAesKey() {
        return aesKey;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
