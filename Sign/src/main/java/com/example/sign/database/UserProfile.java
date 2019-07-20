package com.example.sign.database;

/**
 * Created by mac on 2019/2/26.
 */

public class UserProfile {


    /**
     * code : 0
     * message : OK
     * data : {"userId":1,"name":"猿猿","avatar":"http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0","gender":"男","address":"西安"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1
         * name : 猿猿
         * avatar : http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0
         * gender : 男
         * address : 西安
         */

        private int userId;
        private String name;
        private String avatar;
        private String gender;
        private String address;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "{"
                    + '"' + "userId" + '"' + ":" + userId + ","
                    + '"' + "name" + '"' + ":" + '"' + name + '"' + ","
                    + '"' + "avatar" + '"' + ":" + '"' + avatar + '"' + ","
                    + '"' + "gender" + '"' + ":" + '"' + gender + '"' + ","
                    + '"' + "address" + '"' + ":" + '"' + address + '"'
                    + "}";
        }
    }

    @Override
    public String toString() {
        return "{"
                + '"' + "code" + '"' + ":" + code + ","
                + '"' + "message" + '"' + ":" + '"' + message + '"' + ","
                + '"' + "data" + '"' + ":" + data.toString()
                + "}";
    }
}
