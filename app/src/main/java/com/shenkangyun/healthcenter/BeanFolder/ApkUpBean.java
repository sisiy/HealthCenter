package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/9/7.
 */

public class ApkUpBean {


    /**
     * status : 0
     * data : {"data":{"parentqiangzhiupdate":"0","app_parent_version_url":"http://shenkangyun.com:806/app/cfy_patient.apk","app_parent_version":"1.0.1"}}
     */

    private String status;
    private DataBeanX data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"parentqiangzhiupdate":"0","app_parent_version_url":"http://shenkangyun.com:806/app/cfy_patient.apk","app_parent_version":"1.0.1"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * parentqiangzhiupdate : 0
             * app_parent_version_url : http://shenkangyun.com:806/app/cfy_patient.apk
             * app_parent_version : 1.0.1
             */

            private String parentqiangzhiupdate;
            private String app_parent_version_url;
            private String app_parent_version;

            public String getParentqiangzhiupdate() {
                return parentqiangzhiupdate;
            }

            public void setParentqiangzhiupdate(String parentqiangzhiupdate) {
                this.parentqiangzhiupdate = parentqiangzhiupdate;
            }

            public String getApp_parent_version_url() {
                return app_parent_version_url;
            }

            public void setApp_parent_version_url(String app_parent_version_url) {
                this.app_parent_version_url = app_parent_version_url;
            }

            public String getApp_parent_version() {
                return app_parent_version;
            }

            public void setApp_parent_version(String app_parent_version) {
                this.app_parent_version = app_parent_version;
            }
        }
    }
}
