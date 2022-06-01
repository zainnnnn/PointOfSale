package com.example.caspos;

public class DbWrapperBill {

    public static final String TABLE_ADD_TO_CART = "Bill";

    public static final String BILL_ID = "Bill_ID";
    public static final String COLUMN_PRODUCT_NAME = "ProductName";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_QUANTITY = "Quantity";
    public static final String COLUMN_TOTAL = "Total";


    public static final String CreateBillTable = String.format("CREATE TABLE IF NOT EXISTS " + "%s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)",
            TABLE_ADD_TO_CART,BILL_ID, COLUMN_PRODUCT_NAME, COLUMN_PRICE, COLUMN_QUANTITY, COLUMN_TOTAL);

    public static final String DropBillTable = "DROP TABLE IF EXISTS " + TABLE_ADD_TO_CART;
    public static final String SelectBillAllRecords = "SELECT * FROM " + TABLE_ADD_TO_CART;
}
