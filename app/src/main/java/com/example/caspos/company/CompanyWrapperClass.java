package com.example.caspos.company;

public class CompanyWrapperClass {
    public static final String COMPANY_TABLE = "CompanyTable";
    public static final String COMPANY_ID = "CompanyId";                                       //	INTEGER NOT NULL,
    public static final String COMPANY_TITLE = "CompanyTitle";                                 //   TEXT,
    public static final String COMPANY_STATUS = "Status";                                      //	INTEGER NOT NULL,
    public static final String COMPANY_SIGNATURE = "Signature";                                //	INTEGER NOT NULL,
    public static final String COMPANY_DESCRIPTION = "Description";                            //	TEXT,
    public static final String COMPANY_UUID = "UUID";                                          //	TEXT,
    public static final String COMPANY_LASTMODIFIED_BY = "LastModifiedBy";                     //	TEXT,
    public static final String COMPANY_INSERTED_BY = "InsertedBy";                             //	TEXT,
    public static final String COMPANY_DEACTIVE_BY = "DeactiveBy";                             //	TEXT,
    public static final String COMPANY_DEACTIVE_REASON = "DeactiveReason";                     //	TEXT,
    public static final String COMPANY_INSERTION_TIME_DATE = "InsertionTimeDate";              //	TEXT,
    public static final String COMPANY_DEACTIVE_TIME_DATE = "DeactiveTimeDate";                //	TEXT,
    public static final String COMPANY_LAST_MODIFICATION_TIME_DATE = "LastModificatonTimeDate";//	TEXT,

    public static final String CREATE_COMPANY_TABLE = String.format
            ("CREATE TABLE IF NOT EXISTS " + " %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT , %s TEXT, %s TEXT, %s TEXT, %s TEXT , %s INTEGER , %s INTEGER )",
                    COMPANY_TABLE, COMPANY_ID, COMPANY_TITLE, COMPANY_DESCRIPTION, COMPANY_UUID, COMPANY_LASTMODIFIED_BY, COMPANY_INSERTED_BY
                    , COMPANY_DEACTIVE_BY, COMPANY_DEACTIVE_REASON, COMPANY_INSERTION_TIME_DATE, COMPANY_DEACTIVE_TIME_DATE, COMPANY_LAST_MODIFICATION_TIME_DATE, COMPANY_STATUS, COMPANY_SIGNATURE);

    public static final String DROP_COMPANY_TABLE = String.format(" DROP TABLE IF EXISTS " + COMPANY_TABLE);
    public static final String SELECT_ALL_COMPANY_RECORDS = " SELECT * FROM  " + COMPANY_TABLE;
}
