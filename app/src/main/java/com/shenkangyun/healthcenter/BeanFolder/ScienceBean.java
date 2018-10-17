package com.shenkangyun.healthcenter.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ScienceBean {

    /**
     * data : {"pageCount":8,"totalCount":1,"pageNo":0,"list":[{"id":37,"moduleCode":"SP0410","articleName":"荤素搭配的备孕食谱,营养均衡","content":"{\"json\":[{\"sType\":5,\"attachmentID\":0,\"attachmentUrl\":\"attachment/htmlFile/20180810194906/1949064028e47b6523aade016523ab315c0003.html\"}]}","state":1,"createTime":1533901747000,"createUser":null,"updateTime":null,"updateUser":null,"delFlag":0,"remark":null}]}
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
         * pageCount : 8
         * totalCount : 1
         * pageNo : 0
         * list : [{"id":37,"moduleCode":"SP0410","articleName":"荤素搭配的备孕食谱,营养均衡","content":"{\"json\":[{\"sType\":5,\"attachmentID\":0,\"attachmentUrl\":\"attachment/htmlFile/20180810194906/1949064028e47b6523aade016523ab315c0003.html\"}]}","state":1,"createTime":1533901747000,"createUser":null,"updateTime":null,"updateUser":null,"delFlag":0,"remark":null}]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<ListBean> list;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 37
             * moduleCode : SP0410
             * articleName : 荤素搭配的备孕食谱,营养均衡
             * content : {"json":[{"sType":5,"attachmentID":0,"attachmentUrl":"attachment/htmlFile/20180810194906/1949064028e47b6523aade016523ab315c0003.html"}]}
             * state : 1
             * createTime : 1533901747000
             * createUser : null
             * updateTime : null
             * updateUser : null
             * delFlag : 0
             * remark : null
             */

            private int id;
            private String moduleCode;
            private String articleName;
            private String content;
            private int state;
            private long createTime;
            private Object createUser;
            private Object updateTime;
            private Object updateUser;
            private int delFlag;
            private Object remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getModuleCode() {
                return moduleCode;
            }

            public void setModuleCode(String moduleCode) {
                this.moduleCode = moduleCode;
            }

            public String getArticleName() {
                return articleName;
            }

            public void setArticleName(String articleName) {
                this.articleName = articleName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }
    }
}
