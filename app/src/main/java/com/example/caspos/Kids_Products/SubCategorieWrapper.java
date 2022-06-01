package com.example.caspos.Kids_Products;

public class SubCategorieWrapper {
    public static final String TABLE_NAME_For_Product_Sub_Categories = "ProductSubCategories";
    public static final String COLUMN_Category_ID = "CategoryID";
    public static final String COLUMN_Sub_Category_Id = "SubCategoryID";
    public static final String COLUMN_Sub_Category_Title = "SubCategoryTitle";
    public static final String COLUMN_Sub_Category_Descriptiuon = "Description";
    public static final String COLUMN_Sub_Category_Uuid = "UUId";
    public static final String COLUMN_Sub_Category_Insertion_Timedate = "InsertionTime";
    public static final String COLUMN_Sub_Category_Inserted_By = "InsertedBy";
    public static final String COLUMN_Sub_Category_Deletion_Timedate = "DeactiveTimeDate";
    public static final String COLUMN_Sub_Category_Deletion_By = "DeactiveBy";
    public static final String COLUMN_Sub_Category_Deletion_Reason = "DeactiveReason";
    public static final String COLUMN_Sub_Category_lastModification_TimeDate = "LastModificatonTimeDate";
    public static final String COLUMN_Sub_Category_lastModification_By = "LasTModifiedBy";
    public static final String COLUMN_SUB_Category_Status = "Status";
    public static final String COLUMN_SUB_Category_Signature = "Signature";
    public static final String COLUMN_SUB_Category_PIC = "Pic";

    public static final String CreateProductSubCategoriesTable = String.format("CREATE TABLE IF NOT EXISTS " + "%s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s Text,%s Text,%s Text,%s Text,%s Text,%s Text,%s Text,%s Text,%s INTEGER,%s INTEGER )",
            TABLE_NAME_For_Product_Sub_Categories ,COLUMN_Sub_Category_Id, COLUMN_Sub_Category_Title, COLUMN_Sub_Category_Descriptiuon, COLUMN_Sub_Category_Uuid
            , COLUMN_Sub_Category_Insertion_Timedate, COLUMN_Sub_Category_Inserted_By, COLUMN_Sub_Category_Deletion_Timedate
            , COLUMN_Sub_Category_Deletion_By, COLUMN_Sub_Category_Deletion_Reason, COLUMN_Sub_Category_lastModification_TimeDate, COLUMN_Sub_Category_lastModification_By,
            COLUMN_SUB_Category_Status, COLUMN_SUB_Category_Signature, COLUMN_SUB_Category_PIC);

    public static final String DropProductSubCategoriesTable = "DROP TABLE IF EXISTS " + TABLE_NAME_For_Product_Sub_Categories;
    public static final String SelectAllProductSubCategories = "SELECT * FROM " + TABLE_NAME_For_Product_Sub_Categories;
}
