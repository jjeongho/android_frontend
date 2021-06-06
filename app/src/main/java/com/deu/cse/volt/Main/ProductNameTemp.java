package com.deu.cse.volt.Main;


public class ProductNameTemp {
    private static ProductNameTemp productNameTemp = new ProductNameTemp();
    private String product;
    private ProductNameTemp(){

    }

    public static ProductNameTemp getInstance(){
        return productNameTemp;
    }

    public void setProductNameTemp(String product){
        this.product = product;
    }

    public String getProductNameTemp(){
        return product;
    }
}
