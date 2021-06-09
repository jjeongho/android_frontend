package com.deu.cse.volt.Main.DetailThings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AiDTO {

    @Expose
    @SerializedName("data")
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
