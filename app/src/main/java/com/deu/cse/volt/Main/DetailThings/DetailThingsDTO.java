package com.deu.cse.volt.Main.DetailThings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailThingsDTO {

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
        private List<Result> result;

        public List<Result> getResult() {
            return result;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }
    }

    public static class Result {
        @Expose
        @SerializedName("updated_at")
        private String updatedAt;
        @Expose
        @SerializedName("created_at")
        private String createdAt;
        @Expose
        @SerializedName("productPicture")
        private String productpicture;
        @Expose
        @SerializedName("shippingPrice")
        private int shippingprice;
        @Expose
        @SerializedName("manufacturer")
        private String manufacturer;
        @Expose
        @SerializedName("modelName")
        private String modelname;
        @Expose
        @SerializedName("productName")
        private String productname;
        @Expose
        @SerializedName("productId")
        private int productid;

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getProductpicture() {
            return productpicture;
        }

        public void setProductpicture(String productpicture) {
            this.productpicture = productpicture;
        }

        public int getShippingprice() {
            return shippingprice;
        }

        public void setShippingprice(int shippingprice) {
            this.shippingprice = shippingprice;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getModelname() {
            return modelname;
        }

        public void setModelname(String modelname) {
            this.modelname = modelname;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }
    }
}
