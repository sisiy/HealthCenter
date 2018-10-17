package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/7/7.
 */

public class RegisterBean {

    /**
     * status : 0
     * data : {"patient":{"id":0,"num":null,"hospitalID":0,"useriID":null,"loginName":"zhangbao","name":null,"sex":null,"age":0,"national":null,"brithday":null,"mobile":"15165055068","password":"","degree":0,"idCard":"370911199605212819","address":null,"provinceID":null,"cityID":null,"regionID":null,"createTime":"2018-07-07 15:16:14","updateTime":"2018-07-07 15:16:14","delFlag":0,"delTime":null,"remark":null}}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * patient : {"id":0,"num":null,"hospitalID":0,"useriID":null,"loginName":"zhangbao","name":null,"sex":null,"age":0,"national":null,"brithday":null,"mobile":"15165055068","password":"","degree":0,"idCard":"370911199605212819","address":null,"provinceID":null,"cityID":null,"regionID":null,"createTime":"2018-07-07 15:16:14","updateTime":"2018-07-07 15:16:14","delFlag":0,"delTime":null,"remark":null}
         */

        private PatientBean patient;

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public static class PatientBean {
            /**
             * id : 0
             * num : null
             * hospitalID : 0
             * useriID : null
             * loginName : zhangbao
             * name : null
             * sex : null
             * age : 0
             * national : null
             * brithday : null
             * mobile : 15165055068
             * password :
             * degree : 0
             * idCard : 370911199605212819
             * address : null
             * provinceID : null
             * cityID : null
             * regionID : null
             * createTime : 2018-07-07 15:16:14
             * updateTime : 2018-07-07 15:16:14
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private int id;
            private int num;
            private int hospitalID;
            private int useriID;
            private String loginName;
            private String name;
            private String sex;
            private int age;
            private String national;
            private String brithday;
            private String mobile;
            private String password;
            private int degree;
            private String idCard;
            private String address;
            private String provinceID;
            private String cityID;
            private String regionID;
            private String createTime;
            private String updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getHospitalID() {
                return hospitalID;
            }

            public void setHospitalID(int hospitalID) {
                this.hospitalID = hospitalID;
            }

            public int getUseriID() {
                return useriID;
            }

            public void setUseriID(int useriID) {
                this.useriID = useriID;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
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

            public String getBrithday() {
                return brithday;
            }

            public void setBrithday(String brithday) {
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getRegionID() {
                return regionID;
            }

            public void setRegionID(String regionID) {
                this.regionID = regionID;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getDelTime() {
                return delTime;
            }

            public void setDelTime(String delTime) {
                this.delTime = delTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
