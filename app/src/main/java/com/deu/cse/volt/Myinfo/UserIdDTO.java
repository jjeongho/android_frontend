package com.deu.cse.volt.Myinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserIdDTO {

    @Expose
    @SerializedName("data")
    private Data data;
    @Expose
    @SerializedName("responseMessage")
    private String responsemessage;
    @Expose
    @SerializedName("statusCode")
    private int statuscode;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public static class Data {
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("id")
        private String id;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
