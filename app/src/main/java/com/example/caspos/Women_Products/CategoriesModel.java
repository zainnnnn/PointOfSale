package com.example.caspos.Women_Products;

public class CategoriesModel {
    private String Product_Type_Id;
    private String Product_Category_Id;
    private String Product_Category_Title;
    private String Product_Category_Description;
    private String Product_Category_Uu_Id = "1";
    private String Product_Category_Insertion_TimeDate;
    private String Product_Category_Inserted_By = "Ali";
    private String Product_Cateogory_Deletion_TimeDate;
    private String Product_Category_Deletion_By = "Ali";
    private String Product_Category_Deletion_Reason;
    private String Product_Category_Updation_TimeDate;
    private String Product_Category_Updation_By;
    private String Product_Category_Pic;
    private int Product_Category_Status;
    private int Product_Category_Signature = 1;

    private String productTypeIDUsesForReplacement;


    public String getProductTypeIDUsesForReplacement() {
        return productTypeIDUsesForReplacement;
    }

    public void setProductTypeIDUsesForReplacement(String productTypeIDUsesForReplacement) {
        this.productTypeIDUsesForReplacement = productTypeIDUsesForReplacement;
    }


    public int getProduct_Category_Status() {
        return Product_Category_Status;
    }

    public void setProduct_Category_Status(int product_Category_Status) {
        Product_Category_Status = product_Category_Status;
    }

    public int getProduct_Category_Signature() {
        return Product_Category_Signature;
    }

    public void setProduct_Category_Signature(int product_Category_Signature) {
        Product_Category_Signature = product_Category_Signature;
    }

    public String getProduct_Type_Id() {
        return Product_Type_Id;
    }

    public void setProduct_Type_Id(String product_Type_Id) {
        Product_Type_Id = product_Type_Id;
    }

    public String getProduct_Category_Id() {
        return Product_Category_Id;
    }

    public void setProduct_Category_Id(String product_Category_Id) {
        Product_Category_Id = product_Category_Id;
    }

    public String getProduct_Category_Title() {
        return Product_Category_Title;
    }

    public void setProduct_Category_Title(String product_Category_Title) {
        Product_Category_Title = product_Category_Title;
    }

    public String getProduct_Category_Description() {
        return Product_Category_Description;
    }

    public void setProduct_Category_Description(String product_Category_Description) {
        Product_Category_Description = product_Category_Description;
    }

    public String getProduct_Category_Uu_Id() {
        return Product_Category_Uu_Id;
    }

    public void setProduct_Category_Uu_Id(String product_Category_Uu_Id) {
        Product_Category_Uu_Id = product_Category_Uu_Id;
    }

    public String getProduct_Category_Insertion_TimeDate() {
        return Product_Category_Insertion_TimeDate;
    }

    public void setProduct_Category_Insertion_TimeDate(String product_Category_Insertion_TimeDate) {
        Product_Category_Insertion_TimeDate = product_Category_Insertion_TimeDate;
    }

    public String getProduct_Category_Inserted_By() {
        return Product_Category_Inserted_By;
    }

    public void setProduct_Category_Inserted_By(String product_Category_Inserted_By) {
        Product_Category_Inserted_By = product_Category_Inserted_By;
    }

    public String getProduct_Cateogory_Deletion_TimeDate() {
        return Product_Cateogory_Deletion_TimeDate;
    }

    public void setProduct_Cateogory_Deletion_TimeDate(String product_Cateogory_Deletion_TimeDate) {
        Product_Cateogory_Deletion_TimeDate = product_Cateogory_Deletion_TimeDate;
    }

    public String getProduct_Category_Deletion_By() {
        return Product_Category_Deletion_By;
    }

    public void setProduct_Category_Deletion_By(String product_Category_Deletion_By) {
        Product_Category_Deletion_By = product_Category_Deletion_By;
    }

    public String getProduct_Category_Deletion_Reason() {
        return Product_Category_Deletion_Reason;
    }

    public void setProduct_Category_Deletion_Reason(String product_Category_Deletion_Reason) {
        Product_Category_Deletion_Reason = product_Category_Deletion_Reason;
    }

    public String getProduct_Category_Updation_TimeDate() {
        return Product_Category_Updation_TimeDate;
    }

    public void setProduct_Category_Updation_TimeDate(String product_Category_Updation_TimeDate) {
        Product_Category_Updation_TimeDate = product_Category_Updation_TimeDate;
    }

    public String getProduct_Category_Updation_By() {
        return Product_Category_Updation_By;
    }

    public void setProduct_Category_Updation_By(String product_Category_Updation_By) {
        Product_Category_Updation_By = product_Category_Updation_By;
    }

    public String getProduct_Category_Pic() {
        return Product_Category_Pic;
    }

    public void setProduct_Category_Pic(String product_Category_Pic) {
        Product_Category_Pic = product_Category_Pic;
    }

    public CategoriesModel() {
    }

    public CategoriesModel(String product_Type_Id, String product_Category_Id, String product_Category_Title, String product_Category_Description, String product_Category_Uu_Id, String product_Category_Insertion_TimeDate, String product_Category_Inserted_By, String product_Cateogory_Deletion_TimeDate, String product_Category_Deletion_By, String product_Category_Deletion_Reason, String product_Category_Updation_TimeDate, String product_Category_Updation_By, String product_Category_Pic) {
        Product_Type_Id = product_Type_Id;
        Product_Category_Id = product_Category_Id;
        Product_Category_Title = product_Category_Title;
        Product_Category_Description = product_Category_Description;
        Product_Category_Uu_Id = product_Category_Uu_Id;
        Product_Category_Insertion_TimeDate = product_Category_Insertion_TimeDate;
        Product_Category_Inserted_By = product_Category_Inserted_By="ALI";
        Product_Cateogory_Deletion_TimeDate = product_Cateogory_Deletion_TimeDate;
        Product_Category_Deletion_By = product_Category_Deletion_By;
        Product_Category_Deletion_Reason = product_Category_Deletion_Reason;
        Product_Category_Updation_TimeDate = product_Category_Updation_TimeDate;
        Product_Category_Updation_By = product_Category_Updation_By;
        Product_Category_Pic = product_Category_Pic;
    }
}
