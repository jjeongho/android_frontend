package com.deu.cse.volt.Login.IDsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdSearchDTO {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;


    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("data")
    @Expose
    private IdSearchDataDTO data;


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public IdSearchDataDTO getData() {
        return data;
    }

    public void setData(IdSearchDataDTO data) {
        this.data = data;
    }
}
