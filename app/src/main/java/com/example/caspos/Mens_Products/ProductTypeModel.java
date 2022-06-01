package com.example.caspos.Mens_Products;

public class ProductTypeModel {
    private String product_type_ID;
    private String product_type_title;
    private String product_type_description;
    private String productTypeUUID = "hy";
    private String productTypeInsertBY = "ali";
    private String productTypeDeactiveBy = "ali";
    private String productTypeModifiedBy = "ali";
    private String productTypeDeactiveReason;
    private int productTypeInsertTimeDate;
    private int productTypeDeactiveTimeDate;
    private int productTypeLastModifiedTimeDate;
    private int productTypeStatus;
    private int productTypeSignature = 1;
    private int productPrice;

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTypePic() {
        return productTypePic;
    }

    public void setProductTypePic(String productTypePic) {
        this.productTypePic = productTypePic;
    }

    private String productTypePic;

    public int getProductTypeInsertTimeDate() {
        return productTypeInsertTimeDate;
    }

    public void setProductTypeInsertTimeDate(int productTypeInsertTimeDate) {
        this.productTypeInsertTimeDate = productTypeInsertTimeDate;
    }

    public int getProductTypeDeactiveTimeDate() {
        return productTypeDeactiveTimeDate;
    }

    public void setProductTypeDeactiveTimeDate(int productTypeDeactiveTimeDate) {
        this.productTypeDeactiveTimeDate = productTypeDeactiveTimeDate;
    }

    public int getProductTypeLastModifiedTimeDate() {
        return productTypeLastModifiedTimeDate;
    }

    public void setProductTypeLastModifiedTimeDate(int productTypeLastModifiedTimeDate) {
        this.productTypeLastModifiedTimeDate = productTypeLastModifiedTimeDate;
    }

    public int getProductTypeStatus() {
        return productTypeStatus;
    }

    public void setProductTypeStatus(int productTypeStatus) {
        this.productTypeStatus = productTypeStatus;
    }

    public int getProductTypeSignature() {
        return productTypeSignature;
    }

    public void setProductTypeSignature(int productTypeSignature) {
        this.productTypeSignature = productTypeSignature;
    }

    public ProductTypeModel() {
    }

    public ProductTypeModel(String product_type_ID, String product_type_title, String product_type_description) {
        this.product_type_ID = product_type_ID;
        this.product_type_title = product_type_title;
        this.product_type_description = product_type_description;
    }

    public String getProductTypeUUID() {
        return productTypeUUID;
    }

    public void setProductTypeUUID(String productTypeUUID) {
        this.productTypeUUID = productTypeUUID;
    }

    public String getProductTypeInsertBY() {
        return productTypeInsertBY;
    }

    public void setProductTypeInsertBY(String productTypeInsertBY) {
        this.productTypeInsertBY = productTypeInsertBY;
    }

    public String getProductTypeDeactiveBy() {
        return productTypeDeactiveBy;
    }

    public void setProductTypeDeactiveBy(String productTypeDeactiveBy) {
        this.productTypeDeactiveBy = productTypeDeactiveBy;
    }

    public String getProductTypeModifiedBy() {
        return productTypeModifiedBy;
    }

    public void setProductTypeModifiedBy(String productTypeModifiedBy) {
        this.productTypeModifiedBy = productTypeModifiedBy;
    }


    public String getProductTypeDeactiveReason() {
        return productTypeDeactiveReason;
    }

    public void setProductTypeDeactiveReason(String productTypeDeactiveReason) {
        this.productTypeDeactiveReason = productTypeDeactiveReason;
    }

    public String getProduct_type_ID() {
        return product_type_ID;
    }

    public void setProduct_type_ID(String product_type_ID) {
        this.product_type_ID = product_type_ID;
    }

    public String getProduct_type_title() {
        return product_type_title;
    }

    public void setProduct_type_title(String product_type_title) {
        this.product_type_title = product_type_title;
    }

    public String getProduct_type_description() {
        return product_type_description;
    }

    public void setProduct_type_description(String product_type_description) {
        this.product_type_description = product_type_description;
    }
}
