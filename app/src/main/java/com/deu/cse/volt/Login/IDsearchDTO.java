package com.deu.cse.volt.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IDsearchDTO {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;


    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("data")
    @Expose
    private IDsearchDataDTO data;


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

    public IDsearchDataDTO getData() {
        return data;
    }

    public void setData(IDsearchDataDTO data) {
        this.data = data;
    }
}
