package com.shenkangyun.healthcenter.DBFolder;

import org.litepal.crud.LitePalSupport;

/**
 * Created by Administrator on 2018/7/5.
 */

public class User extends LitePalSupport {
    private int id;
    private int userID;
    private Object num;
    private Object hospitalID;
    private String loginName;
    private String name;
    private Object sex;
    private int age;
    private String national;
    private Object brithday;
    private String mobile;
    private String password;
    private int degree;
    private String idCard;
    private String provinceID;
    private String cityID;
    private Object regionID;
    private Object address;
    private Object postalCode;
    private Object diseasesID;
    private int profession;
    private int husbandAge;
    private int husbandProfession;
    private int childWeeks;
    private int complication;
    private String height;
    private String weight;
    private Object createUser;
    private long createTime;
    private long updateTime;
    private int delFlag;
    private Object delTime;
    private Object remark;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getNum() {
        return num;
    }

    public void setNum(Object num) {
        this.num = num;
    }

    public Object getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Object hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public Object getBrithday() {
        return brithday;
    }

    public void setBrithday(Object brithday) {
        this.brithday = brithday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public Object getRegionID() {
        return regionID;
    }

    public void setRegionID(Object regionID) {
        this.regionID = regionID;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getDiseasesID() {
        return diseasesID;
    }

    public void setDiseasesID(Object diseasesID) {
        this.diseasesID = diseasesID;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public int getHusbandAge() {
        return husbandAge;
    }

    public void setHusbandAge(int husbandAge) {
        this.husbandAge = husbandAge;
    }

    public int getHusbandProfession() {
        return husbandProfession;
    }

    public void setHusbandProfession(int husbandProfession) {
        this.husbandProfession = husbandProfession;
    }

    public int getChildWeeks() {
        return childWeeks;
    }

    public void setChildWeeks(int childWeeks) {
        this.childWeeks = childWeeks;
    }

    public int getComplication() {
        return complication;
    }

    public void setComplication(int complication) {
        this.complication = complication;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Object getDelTime() {
        return delTime;
    }

    public void setDelTime(Object delTime) {
        this.delTime = delTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }
}
