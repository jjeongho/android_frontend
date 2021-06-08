package com.deu.cse.volt.Main.Bidding.BiddingStatus;

public class TradeVO {
    private String orderType;
    private String productGrade;
    private String modelName;
    private int orderPrice;

    public TradeVO(String orderType,String productGrade,String modelName, int orderPrice){
        this.orderType = orderType;
        this.productGrade = productGrade;
        this.modelName = modelName;
        this.orderPrice = orderPrice;
    }

    public TradeVO() {

    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
