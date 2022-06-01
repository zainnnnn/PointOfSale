package com.example.caspos.Mens_Products;

public class ProductTypeWrapper {
    public static final String TABLE_NAME = "ProductType";
    public static final String KEY_PRODUCT_ID = "Product_ID";
    public static final String KEY_PRODUCT_TITLE = "Product_title";
    public static final String KEY_PRODUCT_DESCRIPTION = "Product_Description";
    public static final String KEY_UUID = "UUID";
    public static final String KEY_INSERT_BY = "InsertBy";
    public static final String KEY_DEACTIVE_BY = "DeactiveBy";
    public static final String KEY_LAST_MODIFIED_BY = "LasTModifiedBy";
    public static final String KEY_DEACTIVE_REASON = "DeactiveReason";
    public static final String KEY_DEACTIVE_TIME_DATE = "DeactiveTimeDate";
    public static final String KEY_INSERTION_TIME_DATE = "InsertionTimeDate";
    public static final String KEY_LAST_MODIFIED_TIME_DATE = "LastModificatonTimeDate";
    public static final String KEY_STATUS = "Status";
    public static final String KEY_SIGNATURE = "Signature";
    public static final String KEY_PRODUCT_PIC = "ProductPic";

    public static final String KEY_PRODUCT_Price = "ProductPrice";

    public static final String CREATE_TABLE = String.format
            ("CREATE TABLE IF NOT EXISTS " + " %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s INTEGER , %s INTEGER , %s INTEGER , %s INTEGER , %s INTEGER ,%s TEXT, %s INTEGER)"
                    , TABLE_NAME, KEY_PRODUCT_ID, KEY_PRODUCT_TITLE, KEY_PRODUCT_DESCRIPTION, KEY_UUID, KEY_INSERT_BY, KEY_DEACTIVE_BY, KEY_LAST_MODIFIED_BY
                    , KEY_DEACTIVE_REASON, KEY_DEACTIVE_TIME_DATE, KEY_INSERTION_TIME_DATE, KEY_LAST_MODIFIED_TIME_DATE, KEY_STATUS, KEY_SIGNATURE, KEY_PRODUCT_PIC,KEY_PRODUCT_Price);

    public static final String DROP_TABLE = String.format(" DROP TABLE IF EXISTS " + TABLE_NAME);
    public static final String SELECT_ALL_RECORDS = " SELECT * FROM  " + TABLE_NAME;

}

/*
"ProductTypeId"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"ProductTypeTitle"	TEXT,
	"Status"	INTEGER NOT NULL,
	"Signature"	INTEGER NOT NULL,
	"Description"	TEXT,
	"UUID"	TEXT,
	"LastModifiedBy"	TEXT,
	"InsertedBy"	TEXT,
	"DeactiveBy"	TEXT,
	"DeactiveReason"	TEXT,
	"InsertionTimeDate"	INTEGER,
	"DeactiveTimeDate"	INTEGER,
	"LastModificatonTimeDate"
 */