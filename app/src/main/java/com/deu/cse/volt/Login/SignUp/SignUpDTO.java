package com.deu.cse.volt.Login.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpDTO {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;


    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("data")
    @Expose
    private SignUpDataDTO data;


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

    public SignUpDataDTO getData() {
        return data;
    }

    public void setData(SignUpDataDTO data) {
        this.data = data;
    }
}
