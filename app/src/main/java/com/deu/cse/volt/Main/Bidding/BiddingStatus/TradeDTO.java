package com.deu.cse.volt.Main.Bidding.BiddingStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradeDTO {

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
        @SerializedName("result")
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    public static class Result {
        @Expose
        @SerializedName("expiredAt")
        private String expiredat;
        @Expose
        @SerializedName("orderPrice")
        private int orderprice;
        @Expose
        @SerializedName("modelName")
        private String modelname;
        @Expose
        @SerializedName("username")
        private String username;
        @Expose
        @SerializedName("productGrade")
        private String productgrade;
        @Expose
        @SerializedName("orderStatusType")
        private String orderstatustype;
        @Expose
        @SerializedName("orderType")
        private String ordertype;
        @Expose
        @SerializedName("orderIdx")
        private int orderidx;
        @Expose
        @SerializedName("updatedAt")
        private String updatedat;
        @Expose
        @SerializedName("createdAt")
        private String createdat;

        public String getExpiredat() {
            return expiredat;
        }

        public void setExpiredat(String expiredat) {
            this.expiredat = expiredat;
        }

        public int getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(int orderprice) {
            this.orderprice = orderprice;
        }

        public String getModelname() {
            return modelname;
        }

        public void setModelname(String modelname) {
            this.modelname = modelname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getProductgrade() {
            return productgrade;
        }

        public void setProductgrade(String productgrade) {
            this.productgrade = productgrade;
        }

        public String getOrderstatustype() {
            return orderstatustype;
        }

        public void setOrderstatustype(String orderstatustype) {
            this.orderstatustype = orderstatustype;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public int getOrderidx() {
            return orderidx;
        }

        public void setOrderidx(int orderidx) {
            this.orderidx = orderidx;
        }

        public String getUpdatedat() {
            return updatedat;
        }

        public void setUpdatedat(String updatedat) {
            this.updatedat = updatedat;
        }

        public String getCreatedat() {
            return createdat;
        }

        public void setCreatedat(String createdat) {
            this.createdat = createdat;
        }
    }
}
