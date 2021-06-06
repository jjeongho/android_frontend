package com.deu.cse.volt.Main;

import com.deu.cse.volt.Login.BearerTokenTemp;

public class ProductNameTemp {
    private static ProductNameTemp productNameTemp = new ProductNameTemp();
    private String productName;

    public static ProductNameTemp getProductNameTemp() {
        return productNameTemp;
    }

    public static void setProductNameTemp(ProductNameTemp productNameTemp) {
        ProductNameTemp.productNameTemp = productNameTemp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
