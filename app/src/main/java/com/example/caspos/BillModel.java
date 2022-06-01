package com.example.caspos;

public class BillModel {
    public int getBill_ID() {
        return bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        this.bill_ID = bill_ID;
    }

    private int bill_ID;
    private String ProductName;
    private int ProductQuantity;
    private int ProdctPrice;
    private String ProductTotal;
    private String ProductSrNo;

    public String getProductSrNo() {
        return ProductSrNo;
    }

    public void setProductSrNo(String productSrNo) {
        ProductSrNo = productSrNo;
    }

    public BillModel(String productName, int productQuantity, int prodctPrice, String productTotal, String productSrNo) {
        ProductName = productName;
        ProductQuantity = productQuantity;
        ProdctPrice = prodctPrice;
        ProductSrNo=productSrNo;
        ProductTotal = productTotal;
    }

    public BillModel() {
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public int getProdctPrice() {
        return ProdctPrice;
    }

    public void setProdctPrice(int prodctPrice) {
        ProdctPrice = prodctPrice;
    }

    public String getProductTotal() {
        return ProductTotal;
    }

    public void setProductTotal(String productTotal) {
        ProductTotal = productTotal;
    }
}
