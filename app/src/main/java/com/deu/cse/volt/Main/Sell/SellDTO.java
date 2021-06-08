package com.deu.cse.volt.Main.Sell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellDTO {

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
        @SerializedName("localDate")
        private String localdate;
        @Expose
        @SerializedName("transactionCount")
        private int transactioncount;
        @Expose
        @SerializedName("avgPrice")
        private int avgprice;
        @Expose
        @SerializedName("highestPrice")
        private int highestprice;
        @Expose
        @SerializedName("lowestPrice")
        private int lowestprice;
        @Expose
        @SerializedName("modelName")
        private String modelname;
        @Expose
        @SerializedName("dailyStatisticsIdx")
        private int dailystatisticsidx;
        @Expose
        @SerializedName("updatedAt")
        private String updatedat;
        @Expose
        @SerializedName("createdAt")
        private String createdat;

        public String getLocaldate() {
            return localdate;
        }

        public void setLocaldate(String localdate) {
            this.localdate = localdate;
        }

        public int getTransactioncount() {
            return transactioncount;
        }

        public void setTransactioncount(int transactioncount) {
            this.transactioncount = transactioncount;
        }

        public int getAvgprice() {
            return avgprice;
        }

        public void setAvgprice(int avgprice) {
            this.avgprice = avgprice;
        }

        public int getHighestprice() {
            return highestprice;
        }

        public void setHighestprice(int highestprice) {
            this.highestprice = highestprice;
        }

        public int getLowestprice() {
            return lowestprice;
        }

        public void setLowestprice(int lowestprice) {
            this.lowestprice = lowestprice;
        }

        public String getModelname() {
            return modelname;
        }

        public void setModelname(String modelname) {
            this.modelname = modelname;
        }

        public int getDailystatisticsidx() {
            return dailystatisticsidx;
        }

        public void setDailystatisticsidx(int dailystatisticsidx) {
            this.dailystatisticsidx = dailystatisticsidx;
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
