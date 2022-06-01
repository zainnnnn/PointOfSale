package com.example.caspos.Women_Products;

public class CategoriesWrapper {
    public static final String TABLE_NAME_For_Product_Categories = "ProductCategories";

    public static final String COLUMN_Category_Id = "CategoryID";
    public static final String COLUMN_Category_Title = "CategoryTitle";
    public static final String COLUMN_Category_Descriptiuon = "Description";
    public static final String COLUMN_Category_Uuid = "UUId";
    public static final String COLUMN_Category_Insertion_Timedate = "InsertionTime";
    public static final String COLUMN_Category_Inserted_By = "InsertedBy";
    public static final String COLUMN_Category_Deletion_Timedate = "DeactiveTimeDate";
    public static final String COLUMN_Category_Deletion_By = "DeactiveBy";
    public static final String COLUMN_Category_Deletion_Reason = "DeactiveReason";
    public static final String COLUMN_Category_Updation_TimeDate = "LastModificatonTimeDate";
    public static final String COLUMN_Category_Updation_By = "LasTModifiedBy";
    public static final String COLUMN_CATEGORY_STATUS = "Status";
    public static final String COLUMN_CATEGORY_SIGNATURE = "Signature";
    public static final String COLUMN_Category_Pic = "Pic";

    public static final String CreateProductCategoriesTable = String.format("CREATE TABLE IF NOT EXISTS " + "%s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s Text,%s Text,%s Text,%s Text,%s Text,%s Text,%s Text ,%s Text,%s Text,%s Text,%s Text)",
            TABLE_NAME_For_Product_Categories, COLUMN_Category_Id,COLUMN_Category_Title, COLUMN_Category_Descriptiuon, COLUMN_Category_Uuid
            , COLUMN_Category_Insertion_Timedate, COLUMN_Category_Inserted_By, COLUMN_Category_Deletion_Timedate
            , COLUMN_Category_Deletion_By, COLUMN_Category_Deletion_Reason, COLUMN_Category_Updation_TimeDate, COLUMN_Category_Updation_By, COLUMN_CATEGORY_STATUS
            , COLUMN_CATEGORY_SIGNATURE, COLUMN_Category_Pic);

    public static final String DropProductCategoriesTable = "DROP TABLE IF EXISTS " + TABLE_NAME_For_Product_Categories;
    public static final String SelectAllProductCategories = "SELECT * FROM " + TABLE_NAME_For_Product_Categories;
}
