package com.example.caspos.flavour;

public class FlavourWrapper {
    public static final String TABLE_NAME = "FlavourTable";
    public static final String KEY_FLAVOUR_ID = "Flavour_ID";
    public static final String KEY_FLAVOUR_TITLE = "Flavour_title";
    public static final String KEY_FLAVOUR_DESCRIPTION = "Flavour_Description";
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


    public static final String CREATE_TABLE = String.format
            ("CREATE TABLE IF NOT EXISTS " + " %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT ,%s TEXT , %s TEXT , %s TEXT, %s TEXT , %s TEXT)"
                    , TABLE_NAME, KEY_FLAVOUR_ID, KEY_FLAVOUR_TITLE, KEY_FLAVOUR_DESCRIPTION, KEY_UUID, KEY_INSERT_BY, KEY_DEACTIVE_BY, KEY_LAST_MODIFIED_BY
                    , KEY_DEACTIVE_REASON, KEY_DEACTIVE_TIME_DATE, KEY_INSERTION_TIME_DATE, KEY_LAST_MODIFIED_TIME_DATE, KEY_STATUS, KEY_SIGNATURE);

    public static final String DROP_TABLE = String.format(" DROP TABLE IF EXISTS " + TABLE_NAME);
    public static final String SELECT_ALL_RECORDS = " SELECT * FROM  " + TABLE_NAME;
}
