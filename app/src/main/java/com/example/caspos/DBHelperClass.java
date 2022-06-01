package com.example.caspos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.caspos.Signup.SignUpModel;
import com.example.caspos.Signup.SignUpWrapper;
import com.example.caspos.Women_Products.CategoriesModel;
import com.example.caspos.Women_Products.CategoriesWrapper;
import com.example.caspos.company.CompanyModel;
import com.example.caspos.company.CompanyWrapperClass;
import com.example.caspos.flavour.FlavourModelClass;
import com.example.caspos.flavour.FlavourWrapper;
import com.example.caspos.Mens_Products.ProductTypeModel;
import com.example.caspos.Mens_Products.ProductTypeWrapper;
import com.example.caspos.Kids_Products.SubCategorieWrapper;
import com.example.caspos.Kids_Products.SubCategoriesModel;
import com.example.caspos.varient.VariantModelClass;
import com.example.caspos.varient.VariantWrapper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelperClass extends SQLiteOpenHelper {
    private static final String DB_NAME = "POS";
    private static final int DB_VERSION = 23;
    private static DBHelperClass instance;

    public DBHelperClass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelperClass getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelperClass(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SignUpWrapper.CreateSignUpTable);
        sqLiteDatabase.execSQL(ProductTypeWrapper.CREATE_TABLE);
        sqLiteDatabase.execSQL(DbWrapperBill.CreateBillTable);
        sqLiteDatabase.execSQL(CategoriesWrapper.CreateProductCategoriesTable);
        sqLiteDatabase.execSQL(SubCategorieWrapper.CreateProductSubCategoriesTable);
        sqLiteDatabase.execSQL(FlavourWrapper.CREATE_TABLE);
        sqLiteDatabase.execSQL(VariantWrapper.CREATE_TABLE);
        sqLiteDatabase.execSQL(CompanyWrapperClass.CREATE_COMPANY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(SignUpWrapper.DropSignUpTable);
            sqLiteDatabase.execSQL(SignUpWrapper.CreateSignUpTable);
            sqLiteDatabase.execSQL(ProductTypeWrapper.DROP_TABLE);
            sqLiteDatabase.execSQL(ProductTypeWrapper.CREATE_TABLE);
            sqLiteDatabase.execSQL(CategoriesWrapper.DropProductCategoriesTable);
            sqLiteDatabase.execSQL(CategoriesWrapper.CreateProductCategoriesTable);
            sqLiteDatabase.execSQL(SubCategorieWrapper.DropProductSubCategoriesTable);
            sqLiteDatabase.execSQL(SubCategorieWrapper.CreateProductSubCategoriesTable);
            sqLiteDatabase.execSQL(FlavourWrapper.DROP_TABLE);
            sqLiteDatabase.execSQL(FlavourWrapper.CREATE_TABLE);
            sqLiteDatabase.execSQL(VariantWrapper.DROP_TABLE);
            sqLiteDatabase.execSQL(VariantWrapper.CREATE_TABLE);
            sqLiteDatabase.execSQL(CompanyWrapperClass.DROP_COMPANY_TABLE);
            sqLiteDatabase.execSQL(CompanyWrapperClass.CREATE_COMPANY_TABLE);
            sqLiteDatabase.execSQL(DbWrapperBill.DropBillTable);
            sqLiteDatabase.execSQL(DbWrapperBill.CreateBillTable);
        }
    }

    ////////////////////////////  Create User Login Account \\\\\\\\\\\\\\\\\\\\\\\

    public boolean CreateAcoount(SignUpModel s) {
        boolean IsSuccess = false;
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SignUpWrapper.COLUMN_EMAIL, s.getEmail());
            values.put(SignUpWrapper.COLUMN_NAME, s.getName());
            values.put(SignUpWrapper.COLUMN_PASSWORD, s.getPassword());
            values.put(SignUpWrapper.COLUMN_PHONE_NO, s.getPhoneNo());

            Log.d(TAG, "The Data Before insertion.................");

            database.insertOrThrow(SignUpWrapper.TABLE_NAME, null, values);
            database.setTransactionSuccessful();
            Log.d("ii", "insert");
            IsSuccess = true;
        } catch (Exception ex) {
            Log.d(TAG, "Error while adding" + s.getName());
            IsSuccess = false;
        } finally {
            database.endTransaction();
        }
        return IsSuccess;
    }

    ///////////////////////////////////  Seacrch User login Password \\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public String SearchPassword(String email) {
        SQLiteDatabase liteDatabase = getReadableDatabase();
        String query = SignUpWrapper.SelectSignUpAllRecords;
        Cursor cursor = liteDatabase.rawQuery(query, null);
        String emailAddress, password;
        password = "Not Found";
        if (cursor.moveToFirst()) {
            do {
                emailAddress = cursor.getString(cursor.getColumnIndex(SignUpWrapper.COLUMN_EMAIL));
                Log.d("ddd", "UserName.." + emailAddress);
                Log.d("ddd", "User.." + email);
                if (emailAddress.equals(email)) {
                    password = cursor.getString(cursor.getColumnIndex(SignUpWrapper.COLUMN_PASSWORD));

                    Log.d("ddd", "Password.." + password);

                }
            } while (cursor.moveToNext());
        }
        return password;

    }


    /////////////////////////////////// Get All Login Username \\\\\\\\\\\\\\\\\\\\\\\\
   /* public List<LoginModel> dbGetLoginRecords() {
        SQLiteDatabase db = getReadableDatabase();
        String query = LoginUtil.SELECT_ALL_LOGIN_RECORDS;
        Cursor cursor = db.rawQuery(query, null);
        List<LoginModel> loginModelList = new ArrayList<>();
        LoginModel loginModel = new LoginModel();
        if (cursor.moveToNext()) {
            do {
                loginModel.setUser_name(cursor.getString(cursor.getColumnIndex(LoginUtil.COLUMN_USER_NAME)));
                loginModelList.add(loginModel);
            } while (cursor.moveToNext());

        }
        return loginModelList;
    }*/

    public boolean dbProductTypeInsert(ProductTypeModel productTypeModel) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessfull = false;
        db.beginTransaction();

        Date date = new Date();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_ID, productTypeModel.getProduct_type_ID());
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_TITLE, productTypeModel.getProduct_type_title());
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_DESCRIPTION, productTypeModel.getProduct_type_description());
            contentValues.put(ProductTypeWrapper.KEY_UUID, productTypeModel.getProductTypeUUID());
            contentValues.put(ProductTypeWrapper.KEY_INSERT_BY, productTypeModel.getProductTypeInsertBY());//   contentValues.put(ProductTypeWrapper.KEY_DEACTIVE_BY, productTypeModel.getProductTypeDeactiveBy());
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_Price, productTypeModel.getProductPrice());
            //    contentValues.put(ProductTypeWrapper.KEY_DEACTIVE_REASON, productTypeModel.getProductTypeDeactiveReason());
            //    contentValues.put(ProductTypeWrapper.KEY_DEACTIVE_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(ProductTypeWrapper.KEY_INSERTION_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            //    contentValues.put(ProductTypeWrapper.KEY_LAST_MODIFIED_TIME_DATE, productTypeModel.getProductTypeLastModifiedTimeDate());
            contentValues.put(ProductTypeWrapper.KEY_STATUS, productTypeModel.getProductTypeStatus());
            contentValues.put(ProductTypeWrapper.KEY_SIGNATURE, productTypeModel.getProductTypeSignature());
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_PIC, productTypeModel.getProductTypePic());
            Log.d("Tag", "HOW ARE YOU" + productTypeModel.getProduct_type_title() + "  " + productTypeModel.getProduct_type_description());
            db.insertOrThrow(ProductTypeWrapper.TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
            IsSuccessfull = true;
        } catch (Exception ex) {
            IsSuccessfull = true;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessfull;
    }

    ///////////////////////////////// This is used for Delete ProductType \\\\\\\\\\\\\\\\\\\\

    public boolean dbProductTypeDelete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();
            db.delete(ProductTypeWrapper.TABLE_NAME,ProductTypeWrapper.KEY_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }

    ///////////////////////////////// This is used for update ProductType \\\\\\\\\\\\\\\\\\\\

    public boolean dbProductTypeUpdate(ProductTypeModel productTypeModel) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_TITLE, productTypeModel.getProduct_type_title());
            contentValues.put(ProductTypeWrapper.KEY_PRODUCT_DESCRIPTION, productTypeModel.getProduct_type_description());
            contentValues.put(ProductTypeWrapper.KEY_STATUS, productTypeModel.getProductTypeStatus());
            contentValues.put(ProductTypeWrapper.KEY_LAST_MODIFIED_BY, productTypeModel.getProductTypeModifiedBy());
            contentValues.put(ProductTypeWrapper.KEY_LAST_MODIFIED_TIME_DATE, DateFormat.getDateTimeInstance().format(date));

            db.update(ProductTypeWrapper.TABLE_NAME, contentValues, ProductTypeWrapper.KEY_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(productTypeModel.getProduct_type_ID())});
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }

    ///////////////////////////// This used for get All ProductTypes in RecyclerView \\\\\\\\\\\\\\\\\

    public List<ProductTypeModel> getAllProductTypes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ProductTypeWrapper.SELECT_ALL_RECORDS, null);
        List<ProductTypeModel> list = new ArrayList<>(cursor.getCount());
        ProductTypeModel p;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(ProductTypeWrapper.KEY_STATUS));

                if (status == 1) {
                    p = new ProductTypeModel();
                    p.setProduct_type_ID(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_ID)));
                    p.setProduct_type_title(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_TITLE)));
                    p.setProduct_type_description(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_DESCRIPTION)));
                    p.setProductTypePic(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_PIC)));
                    p.setProductPrice(cursor.getInt(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_Price)));

                    list.add(p);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public List<ProductTypeModel> getAllProductTypesTitles() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ProductTypeWrapper.SELECT_ALL_RECORDS, null);
        List<ProductTypeModel> list = new ArrayList<>(cursor.getCount());
        ProductTypeModel p;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(ProductTypeWrapper.KEY_STATUS));

                if (status == 1) {
                    p = new ProductTypeModel();
                    p.setProduct_type_ID(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_ID)));
                    p.setProduct_type_title(cursor.getString(cursor.getColumnIndex(ProductTypeWrapper.KEY_PRODUCT_TITLE)));
                    list.add(p);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
    //////////////////////////// THis is used for Add Category   \\\\\\\\\\\\\\\\\\\\\\\

    public boolean AddProductCategory(CategoriesModel categoriesModel) {
        SQLiteDatabase database = getWritableDatabase();
        Log.d("ddd", "Add method called ");
        boolean isSucces = false;
        Date date = new Date();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(CategoriesWrapper.COLUMN_Category_Title, categoriesModel.getProduct_Category_Title());
            values.put(CategoriesWrapper.COLUMN_Category_Descriptiuon, categoriesModel.getProduct_Category_Description());
            //     values.put(CategoriesWrapper.COLUMN_Category_Deletion_By, categoriesModel.getProduct_Category_Deletion_By());
            //     values.put(CategoriesWrapper.COLUMN_Category_Deletion_Reason, categoriesModel.getProduct_Category_Deletion_Reason());
            //     values.put(CategoriesWrapper.COLUMN_Category_Deletion_Timedate, categoriesModel.getProduct_Cateogory_Deletion_TimeDate());
            values.put(CategoriesWrapper.COLUMN_Category_Inserted_By, categoriesModel.getProduct_Category_Inserted_By());
            values.put(CategoriesWrapper.COLUMN_Category_Insertion_Timedate, DateFormat.getDateTimeInstance().format(date));
            //     values.put(CategoriesWrapper.COLUMN_Category_Updation_By, categoriesModel.getProduct_Category_Updation_By());
            //     values.put(CategoriesWrapper.COLUMN_Category_Updation_TimeDate, categoriesModel.getProduct_Category_Updation_TimeDate());
            values.put(CategoriesWrapper.COLUMN_Category_Uuid, categoriesModel.getProduct_Category_Uu_Id());
            values.put(CategoriesWrapper.COLUMN_CATEGORY_STATUS, categoriesModel.getProduct_Category_Status());
            values.put(CategoriesWrapper.COLUMN_CATEGORY_SIGNATURE, categoriesModel.getProduct_Category_Signature());
            values.put(CategoriesWrapper.COLUMN_Category_Pic, categoriesModel.getProduct_Category_Pic());

            Log.d("H", "product ID" + categoriesModel.getProduct_Type_Id());
            Log.d("H", "product ID" + categoriesModel.getProduct_Category_Pic());
            database.insertOrThrow(CategoriesWrapper.TABLE_NAME_For_Product_Categories, null, values);

            database.setTransactionSuccessful();
            isSucces = true;
        } catch (Exception ex) {
            isSucces = false;
        } finally {
            database.endTransaction();
        }
        return isSucces;
    }

    ///////////////////////////////////  Category Update Method  \\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean DBCategoryUpdate(CategoriesModel categoriesModel) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(CategoriesWrapper.COLUMN_Category_Title, categoriesModel.getProduct_Category_Title());
            contentValues.put(CategoriesWrapper.COLUMN_Category_Descriptiuon, categoriesModel.getProduct_Category_Description());
            contentValues.put(CategoriesWrapper.COLUMN_CATEGORY_STATUS, categoriesModel.getProduct_Category_Status());
            contentValues.put(CategoriesWrapper.COLUMN_Category_Updation_By, categoriesModel.getProduct_Category_Updation_By());
            contentValues.put(CategoriesWrapper.COLUMN_Category_Updation_TimeDate, DateFormat.getDateTimeInstance().format(date));

            db.update(CategoriesWrapper.TABLE_NAME_For_Product_Categories, contentValues, CategoriesWrapper.COLUMN_Category_Id + "=?",
                    new String[]{String.valueOf(categoriesModel.getProduct_Category_Id())});
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }

    ///////////////////////////////// Delete Category Method \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean DBCategoryDelete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();
            db.delete(CategoriesWrapper.TABLE_NAME_For_Product_Categories, CategoriesWrapper.COLUMN_Category_Id + "=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }


    //////////////////////// this function is for get All Category \\\\\\\\\\\\\\\\
    public List<CategoriesModel> getAllProductCategories() {
        SQLiteDatabase database = getReadableDatabase();
        boolean isSucces = false;
        Cursor cursor = database.rawQuery(CategoriesWrapper.SelectAllProductCategories, null);

        List<CategoriesModel> list = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                CategoriesModel categoriesModel = new CategoriesModel();
                categoriesModel.setProduct_Category_Id(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Id)));
                categoriesModel.setProduct_Category_Title(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Title)));
                categoriesModel.setProduct_Category_Description(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Descriptiuon)));
                categoriesModel.setProduct_Category_Pic(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Pic)));
                list.add(categoriesModel);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    ///////////////////////////  This Function is to Show Category \\\\\\\\\\\\\\\\
    public List<CategoriesModel> getAllCatagories() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CategoriesWrapper.SelectAllProductCategories, null);
        List<CategoriesModel> list = new ArrayList<>(cursor.getCount());
        CategoriesModel p;
        String productTypeID;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(CategoriesWrapper.COLUMN_CATEGORY_STATUS));
                if (status == 1) {
                    p = new CategoriesModel();
                    p.setProduct_Category_Id(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Id)));
                    p.setProduct_Category_Title(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Title)));
                    p.setProduct_Category_Description(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Descriptiuon)));
                    p.setProduct_Category_Pic(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Pic)));
                    list.add(p);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    ///////////////////////////  This Function is to Show Category \\\\\\\\\\\\\\\\
    public List<CategoriesModel> getSingleCatagories(String ID) {
        Log.d("Tag", " ID SEARCH" + ID);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CategoriesWrapper.SelectAllProductCategories, null);
        List<CategoriesModel> list = new ArrayList<>(cursor.getCount());
        CategoriesModel p;
        String productTypeID;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(CategoriesWrapper.COLUMN_CATEGORY_STATUS));
                if (status == 1) {
                    p = new CategoriesModel();
                    p.setProduct_Category_Id(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Id)));
                    p.setProduct_Category_Title(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Title)));
                    p.setProduct_Category_Description(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Descriptiuon)));
                    p.setProduct_Category_Pic(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Pic)));
                    list.add(p);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }


    ////////////////////////////// This Function is used For to get Category Title To Show in Spinner \\\\\\\

    public List<CategoriesModel> getAllProductCategoriesTitle() {
        SQLiteDatabase database = getReadableDatabase();
        boolean isSucces = false;
        Cursor cursor = database.rawQuery(CategoriesWrapper.SelectAllProductCategories, null);

        List<CategoriesModel> list = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                int status = cursor.getInt(cursor.getColumnIndex(CategoriesWrapper.COLUMN_CATEGORY_STATUS));
                if (status == 1) {
                    CategoriesModel categoriesModel = new CategoriesModel();
                    categoriesModel.setProduct_Category_Title(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Title)));
                    categoriesModel.setProduct_Category_Id(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Id)));
                    //    categoriesModel.setProduct_Category_Description(cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Descriptiuon)));
                    list.add(categoriesModel);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }


    /////////////////////////////// Add Sub Category Function  \\\\\\\\\\\\\\\\\\\\\\

    public boolean AddProductSubCategory(SubCategoriesModel subCategoriesModel) {
        SQLiteDatabase database = getWritableDatabase();
        //    Log.d("ddd", "Add method called ");
        boolean isSucces = false;
        Date date = new Date();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SubCategorieWrapper.COLUMN_Sub_Category_Title, subCategoriesModel.getSub_category_Title());
            values.put(SubCategorieWrapper.COLUMN_Sub_Category_Descriptiuon, subCategoriesModel.getSub_category_Description());
            //   values.put(SubCategorieWrapper.COLUMN_Sub_Category_Deletion_By, subCategoriesModel.getSub_category_Deletion_By());
            //   values.put(SubCategorieWrapper.COLUMN_Sub_Category_Deletion_Reason, subCategoriesModel.getSub_category_Deletion_Reason());
            //   values.put(SubCategorieWrapper.COLUMN_Sub_Category_Deletion_Timedate, subCategoriesModel.getSub_cateogory_Deletion_TimeDate());
            values.put(SubCategorieWrapper.COLUMN_Sub_Category_Inserted_By, subCategoriesModel.getSub_category_Inserted_By());
            values.put(SubCategorieWrapper.COLUMN_Sub_Category_Insertion_Timedate, DateFormat.getDateTimeInstance().format(date));
            //    values.put(SubCategorieWrapper.COLUMN_Sub_Category_lastModification_By, subCategoriesModel.getSub_category_Updation_By());
            //    values.put(SubCategorieWrapper.COLUMN_Sub_Category_lastModification_TimeDate, DateFormat.getDateTimeInstance().format(date));
            values.put(SubCategorieWrapper.COLUMN_Sub_Category_Uuid, subCategoriesModel.getSub_category_Uu_Id());
            values.put(SubCategorieWrapper.COLUMN_SUB_Category_Status, subCategoriesModel.getSubcategory_Status());
            values.put(SubCategorieWrapper.COLUMN_SUB_Category_Signature, subCategoriesModel.getSubcategory_Signature());
            values.put(SubCategorieWrapper.COLUMN_SUB_Category_PIC, subCategoriesModel.getSub_category_Pic());
            database.insertOrThrow(SubCategorieWrapper.TABLE_NAME_For_Product_Sub_Categories, null, values);
            database.setTransactionSuccessful();
            isSucces = true;
        } catch (Exception ex) {
            isSucces = false;
        } finally {
            database.endTransaction();
        }
        return isSucces;
    }

    ////////////////////// Start Sub Category Update Function \\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean dbSubCategoryUpdate(SubCategoriesModel subCategoriesModel) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SubCategorieWrapper.COLUMN_Sub_Category_Title, subCategoriesModel.getSub_category_Title());
            contentValues.put(SubCategorieWrapper.COLUMN_Sub_Category_Descriptiuon, subCategoriesModel.getSub_category_Description());
            contentValues.put(SubCategorieWrapper.COLUMN_Sub_Category_Id, subCategoriesModel.getSub_category_Id());
            contentValues.put(SubCategorieWrapper.COLUMN_SUB_Category_Status, subCategoriesModel.getSubcategory_Status());
            contentValues.put(SubCategorieWrapper.COLUMN_Sub_Category_lastModification_By, subCategoriesModel.getSub_category_Updation_By());
            contentValues.put(SubCategorieWrapper.COLUMN_Sub_Category_lastModification_TimeDate, DateFormat.getDateTimeInstance().format(date));
            Log.d("aaa", "update  title" + subCategoriesModel.getSub_category_Title());
            Log.d("aaa", "update  des" + subCategoriesModel.getSub_category_Description());
            Log.d("aaa", "update  ID" + subCategoriesModel.getSub_category_Id());
            db.update(SubCategorieWrapper.TABLE_NAME_For_Product_Sub_Categories, contentValues, SubCategorieWrapper.COLUMN_Sub_Category_Id + "=?",
                    new String[]{String.valueOf(subCategoriesModel.getSub_category_Id())});
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }

    ////////////////////// End Category Update Function \\\\\\\\\\\\\\\\\\\\\\\\\

    ////////////////////// Start Category Delete Function \\\\\\\\\\\\\\\\\\\\\\\\\\

    public boolean dbSubCategoryDelete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessFull = false;
        Date date = new Date();
        try {
            db.beginTransaction();

            db.delete(SubCategorieWrapper.TABLE_NAME_For_Product_Sub_Categories, SubCategorieWrapper.COLUMN_Sub_Category_Id + "=?",
                    new String[]{String.valueOf(id)});
          //  Log.d("ggg", "hhkdfkdfjdsdd vvvvv" + subCategoriesModel.getSub_category_Id());
            db.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessFull;
    }
    ////////////////////// End SubCategory Delete Function \\\\\\\\\\\\\\\\\\\\\\\\\\

    ////////////////////////// Get All SubCategory Method \\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public List<SubCategoriesModel> getAllSubCatagories() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SubCategorieWrapper.SelectAllProductSubCategories, null);
        List<SubCategoriesModel> list = new ArrayList<>(cursor.getCount());
        SubCategoriesModel p;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_SUB_Category_Status));
                if (status == 1) {
                    p = new SubCategoriesModel();
                    p.setSub_category_Title(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Title)));
                    p.setSub_category_Description(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Descriptiuon)));
                    p.setSub_category_Id(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Id)));
                    p.setSub_category_Pic(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_SUB_Category_PIC)));
                    list.add(p);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    //////////////////////////////   Get Single Sub Category  method \\\\\\\\\\\\\\\\\\\\\\\\\\\

    public List<SubCategoriesModel> getSingleSubCatagories(String ID) {
        Log.d("ttt", " Category............... ID ......." + ID);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SubCategorieWrapper.SelectAllProductSubCategories, null);
        List<SubCategoriesModel> list = new ArrayList<>(cursor.getCount());
        SubCategoriesModel p;
        String categoryID;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_SUB_Category_Status));
                if (status == 1) {
                    p = new SubCategoriesModel();
                    p.setSub_category_Title(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Title)));
                    p.setSub_category_Description(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Descriptiuon)));
                    p.setSub_category_Id(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_Sub_Category_Id)));
                    p.setSub_category_Pic(cursor.getString(cursor.getColumnIndex(SubCategorieWrapper.COLUMN_SUB_Category_PIC)));
                    list.add(p);
                }

            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public boolean dBFlavourInsert(FlavourModelClass flavourModelClass) {
        SQLiteDatabase db = getWritableDatabase();
        boolean IsSuccessfull = false;
        db.beginTransaction();
        Date date = new Date();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_ID, flavourModelClass.getFlavour_ID());
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_TITLE, flavourModelClass.getFlavour_title());
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_DESCRIPTION, flavourModelClass.getFlavour_description());
            contentValues.put(FlavourWrapper.KEY_INSERT_BY, flavourModelClass.getFlavour_InsertBY());
            contentValues.put(FlavourWrapper.KEY_INSERTION_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(FlavourWrapper.KEY_STATUS, flavourModelClass.getFlavour_Status());
            contentValues.put(FlavourWrapper.KEY_UUID, flavourModelClass.getFlavour_UUID());
            contentValues.put(FlavourWrapper.KEY_SIGNATURE, flavourModelClass.getFlavour_Signature());
            //contentValues.put(FlavourWrapper.KEY_DEACTIVE_BY,flavourModelClass.getFlavour_DeactiveBy());
            //contentValues.put(FlavourWrapper.KEY_DEACTIVE_TIME_DATE,flavourModelClass.getFlavour_DeactiveTimeDate());
            //contentValues.put(FlavourWrapper.KEY_DEACTIVE_REASON,flavourModelClass.getFlavour_DeactiveReason());
            //contentValues.put(FlavourWrapper.KEY_LAST_MODIFIED_BY,flavourModelClass.getFlavour_ModifiedBy());
            //contentValues.put(FlavourWrapper.KEY_LAST_MODIFIED_TIME_DATE,flavourModelClass.getFlavour_LastModifiedTimeDate());

            db.insertOrThrow(FlavourWrapper.TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();

        } catch (Exception ex) {
            IsSuccessfull = true;
            throw ex;
        } finally {
            db.endTransaction();
        }
        return IsSuccessfull;
    }

    public List<FlavourModelClass> getAllFlavours() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FlavourWrapper.SELECT_ALL_RECORDS, null);
        List<FlavourModelClass> List = new ArrayList<>(cursor.getCount());
        FlavourModelClass f;
        int status;
        if (cursor.moveToFirst()) {
            do {

                status = cursor.getInt(cursor.getColumnIndex(FlavourWrapper.KEY_STATUS));
                if (status == 1) {
                    f = new FlavourModelClass();
                    f.setFlavour_ID(cursor.getString(cursor.getColumnIndex(FlavourWrapper.KEY_FLAVOUR_ID)));
                    f.setFlavour_title(cursor.getString(cursor.getColumnIndex(FlavourWrapper.KEY_FLAVOUR_TITLE)));
                    f.setFlavour_description(cursor.getString(cursor.getColumnIndex(FlavourWrapper.KEY_FLAVOUR_DESCRIPTION)));
                    List.add(f);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return List;
    }

    public boolean dbFlavourUpdate(FlavourModelClass flavourModelClass) {
        SQLiteDatabase sq = getWritableDatabase();
        boolean IsSuccessfull = false;
        Date date = new Date();
        try {
            sq.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_TITLE, flavourModelClass.getFlavour_title());
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_DESCRIPTION, flavourModelClass.getFlavour_description());
            contentValues.put(FlavourWrapper.KEY_LAST_MODIFIED_BY, flavourModelClass.getFlavour_ModifiedBy());
            contentValues.put(FlavourWrapper.KEY_LAST_MODIFIED_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(FlavourWrapper.KEY_STATUS, flavourModelClass.getFlavour_Status());
            contentValues.put(FlavourWrapper.KEY_SIGNATURE, flavourModelClass.getFlavour_Signature());
            sq.update(FlavourWrapper.TABLE_NAME, contentValues, FlavourWrapper.KEY_FLAVOUR_ID + "=?",
                    new String[]{String.valueOf(flavourModelClass.getFlavour_ID())});
            sq.setTransactionSuccessful();
            IsSuccessfull = true;
        } catch (Exception ex) {
            IsSuccessfull = false;
            throw ex;
        } finally {
            sq.endTransaction();
        }
        return IsSuccessfull;
    }

    public boolean dbFlavpurDelete(FlavourModelClass flavourModelClass) {
        SQLiteDatabase sq = getWritableDatabase();
        Boolean IsSuccessfull = false;
        Date date = new Date();
        try {
            sq.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FlavourWrapper.KEY_FLAVOUR_TITLE, flavourModelClass.getFlavour_title());
            contentValues.put(FlavourWrapper.KEY_DEACTIVE_BY, flavourModelClass.getFlavour_DeactiveBy());
            contentValues.put(FlavourWrapper.KEY_DEACTIVE_REASON, flavourModelClass.getFlavour_DeactiveReason());
            contentValues.put(FlavourWrapper.KEY_DEACTIVE_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(FlavourWrapper.KEY_STATUS, flavourModelClass.getFlavour_Status());
            contentValues.put(FlavourWrapper.KEY_SIGNATURE, flavourModelClass.getFlavour_Signature());
            sq.update(FlavourWrapper.TABLE_NAME, contentValues, FlavourWrapper.KEY_FLAVOUR_ID + "=?",
                    new String[]{String.valueOf(flavourModelClass.getFlavour_ID())});
            sq.setTransactionSuccessful();
            IsSuccessfull = true;
        } catch (Exception ex) {
            IsSuccessfull = false;
            throw ex;
        } finally {
            sq.endTransaction();
        }
        return IsSuccessfull;
    }

    public boolean dbVariantInsert(VariantModelClass variantModelClass) {
        SQLiteDatabase sq = getWritableDatabase();
        boolean IsSuccessfull = false;
        sq.beginTransaction();
        Date date = new Date();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(VariantWrapper.KEY_VARIANT_ID, variantModelClass.getVariant_ID());
            contentValues.put(VariantWrapper.KEY_VARIANT_TITLE, variantModelClass.getVariant_title());
            contentValues.put(VariantWrapper.KEY_VARIANT_DESCRIPTION, variantModelClass.getVariant_description());
            contentValues.put(VariantWrapper.KEY_INSERT_BY, variantModelClass.getVariant_InsertBY());
            contentValues.put(VariantWrapper.KEY_INSERTION_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(VariantWrapper.KEY_STATUS, variantModelClass.getVariant_Status());
            contentValues.put(VariantWrapper.KEY_UUID, variantModelClass.getVariant_UUID());
            contentValues.put(VariantWrapper.KEY_SIGNATURE, variantModelClass.getVariant_Signature());

            sq.insertOrThrow(VariantWrapper.TABLE_NAME, null, contentValues);
            sq.setTransactionSuccessful();
        } catch (Exception ex) {
            IsSuccessfull = true;
            throw ex;
        } finally {
            sq.endTransaction();
        }
        return IsSuccessfull;
    }

    public List<VariantModelClass> getAllVariants() {
        SQLiteDatabase sq = getReadableDatabase();
        Cursor cursor = sq.rawQuery(VariantWrapper.SELECT_ALL_RECORDS, null);
        List<VariantModelClass> list = new ArrayList<>(cursor.getCount());
        VariantModelClass v;
        int status;
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(cursor.getColumnIndex(VariantWrapper.KEY_STATUS));
                if (status == 1) {
                    v = new VariantModelClass();
                    v.setVariant_ID(cursor.getString(cursor.getColumnIndex(VariantWrapper.KEY_VARIANT_ID)));
                    v.setVariant_title(cursor.getString(cursor.getColumnIndex(VariantWrapper.KEY_VARIANT_TITLE)));
                    v.setVariant_description(cursor.getString(cursor.getColumnIndex(VariantWrapper.KEY_VARIANT_DESCRIPTION)));
                    list.add(v);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return list;

    }

    public boolean dbVariantUpdate(VariantModelClass variantModelClass) {
        SQLiteDatabase sq = getWritableDatabase();
        boolean IsSuccessfull = false;
        Date date = new Date();
        try {
            sq.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(VariantWrapper.KEY_VARIANT_TITLE, variantModelClass.getVariant_title());
            contentValues.put(VariantWrapper.KEY_VARIANT_DESCRIPTION, variantModelClass.getVariant_description());
            contentValues.put(VariantWrapper.KEY_LAST_MODIFIED_BY, variantModelClass.getVariant_ModifiedBy());
            contentValues.put(VariantWrapper.KEY_LAST_MODIFIED_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(VariantWrapper.KEY_STATUS, variantModelClass.getVariant_Status());
            contentValues.put(VariantWrapper.KEY_SIGNATURE, variantModelClass.getVariant_Signature());
            sq.update(VariantWrapper.TABLE_NAME, contentValues, VariantWrapper.KEY_VARIANT_ID + "=?",
                    new String[]{String.valueOf(variantModelClass.getVariant_ID())});
            sq.setTransactionSuccessful();
            IsSuccessfull = true;
        } catch (Exception ex) {
            IsSuccessfull = false;
            throw ex;
        } finally {
            sq.endTransaction();
        }
        return IsSuccessfull;
    }

    public boolean dbVariantDelete(VariantModelClass variantModelClass) {
        SQLiteDatabase sq = getWritableDatabase();
        Boolean IsSuccessfull = false;
        Date date = new Date();
        try {
            sq.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(VariantWrapper.KEY_VARIANT_TITLE, variantModelClass.getVariant_title());
            contentValues.put(VariantWrapper.KEY_DEACTIVE_BY, variantModelClass.getVariant_DeactiveBy());
            contentValues.put(VariantWrapper.KEY_DEACTIVE_REASON, variantModelClass.getVariant_DeactiveReason());
            contentValues.put(VariantWrapper.KEY_DEACTIVE_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(VariantWrapper.KEY_STATUS, variantModelClass.getVariant_Status());
            contentValues.put(VariantWrapper.KEY_SIGNATURE, variantModelClass.getVariant_Signature());
            sq.update(VariantWrapper.TABLE_NAME, contentValues, VariantWrapper.KEY_VARIANT_ID + "=?",
                    new String[]{String.valueOf(variantModelClass.getVariant_ID())});
            sq.setTransactionSuccessful();
            IsSuccessfull = true;
        } catch (Exception ex) {
            IsSuccessfull = false;
            throw ex;
        } finally {
            sq.endTransaction();
        }
        return IsSuccessfull;
    }

    public boolean dbInsertCompany(CompanyModel companyModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        boolean IsSuccessFull = false;
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CompanyWrapperClass.COMPANY_TITLE, companyModel.getCompany_Title());
            contentValues.put(CompanyWrapperClass.COMPANY_DESCRIPTION, companyModel.getCompany_Description());
            contentValues.put(CompanyWrapperClass.COMPANY_INSERTED_BY, companyModel.getCompany_InsertBY());
            contentValues.put(CompanyWrapperClass.COMPANY_INSERTION_TIME_DATE, companyModel.getCompany_InsertTimeDate());
            contentValues.put(CompanyWrapperClass.COMPANY_STATUS, companyModel.getCompany_Status());
            contentValues.put(CompanyWrapperClass.COMPANY_SIGNATURE, companyModel.getCompany_Signature());
            contentValues.put(CompanyWrapperClass.COMPANY_UUID, companyModel.getCompany_UUID());
            sqLiteDatabase.insert(CompanyWrapperClass.COMPANY_TABLE, null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return IsSuccessFull;
    }

    public List<CompanyModel> getAllCompanyRecords() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(CompanyWrapperClass.SELECT_ALL_COMPANY_RECORDS, null);
        List<CompanyModel> list = new ArrayList<>();
        CompanyModel companyModel;
        int status;
        if (cursor.moveToNext()) {
            do {
                companyModel = new CompanyModel();
                status = cursor.getInt(cursor.getColumnIndex(CompanyWrapperClass.COMPANY_STATUS));
                if (status == 1) {
                    companyModel.setCompany_ID(cursor.getString(cursor.getColumnIndex(CompanyWrapperClass.COMPANY_ID)));
                    companyModel.setCompany_Title(cursor.getString(cursor.getColumnIndex(CompanyWrapperClass.COMPANY_TITLE)));
                    companyModel.setCompany_Description(cursor.getString(cursor.getColumnIndex(CompanyWrapperClass.COMPANY_DESCRIPTION)));
                    list.add(companyModel);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }

    ////////////////////////////////  Delete Company \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public boolean dbDeleteCompany(CompanyModel companyModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        boolean IsSuccessFull = false;
        sqLiteDatabase.beginTransaction();
        Date date = new Date();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CompanyWrapperClass.COMPANY_TITLE, companyModel.getCompany_Title());
            contentValues.put(CompanyWrapperClass.COMPANY_DEACTIVE_REASON, companyModel.getCompany_Description());
            contentValues.put(CompanyWrapperClass.COMPANY_DEACTIVE_BY, companyModel.getCompany_DeactiveBy());
            contentValues.put(CompanyWrapperClass.COMPANY_INSERTION_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            contentValues.put(CompanyWrapperClass.COMPANY_STATUS, companyModel.getCompany_Status());
            Log.d("ccc", " status FOR company 4" + companyModel.getCompany_Status());
            sqLiteDatabase.update(CompanyWrapperClass.COMPANY_TABLE, contentValues, CompanyWrapperClass.COMPANY_ID + "=?",
                    new String[]{String.valueOf(companyModel.getCompany_ID())});
            Log.d("Tag", " ID FOR company 4" + companyModel.getCompany_ID());
            sqLiteDatabase.setTransactionSuccessful();
            IsSuccessFull = true;
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return IsSuccessFull;
    }

    ////////////////////////////////  Update Company \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public boolean dbUpdateCompany(CompanyModel companyModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        boolean IsSuccessFull = false;
        sqLiteDatabase.beginTransaction();
        Date date = new Date();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CompanyWrapperClass.COMPANY_TITLE, companyModel.getCompany_Title());
            contentValues.put(CompanyWrapperClass.COMPANY_DESCRIPTION, companyModel.getCompany_Description());
            contentValues.put(CompanyWrapperClass.COMPANY_LASTMODIFIED_BY, companyModel.getCompany_DeactiveBy());
            contentValues.put(CompanyWrapperClass.COMPANY_LAST_MODIFICATION_TIME_DATE, DateFormat.getDateTimeInstance().format(date));
            sqLiteDatabase.update(CompanyWrapperClass.COMPANY_TABLE, contentValues, CompanyWrapperClass.COMPANY_ID + "=?",
                    new String[]{String.valueOf(companyModel.getCompany_ID())});
            IsSuccessFull = true;
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception ex) {
            IsSuccessFull = false;
            throw ex;
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return IsSuccessFull;
    }


    public boolean insertBill(BillModel billModel) {

        SQLiteDatabase database = getWritableDatabase();
        boolean isSuccesful = false;

        database.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(DbWrapperBill.COLUMN_PRODUCT_NAME, billModel.getProductName());
            values.put(DbWrapperBill.COLUMN_PRICE, billModel.getProdctPrice());
            values.put(DbWrapperBill.COLUMN_QUANTITY, billModel.getProductQuantity());
            //
            // values.put(DbWrapperBill.COLUMN_TOTAL, billModel.getProductTotal());
            Log.d("ppp", "Values" + "  bill name" + billModel.getProdctPrice() + " bill price" + billModel.getProductName());
            database.insertOrThrow(DbWrapperBill.TABLE_ADD_TO_CART, null, values);
            database.setTransactionSuccessful();
            isSuccesful = true;
        } catch (Exception ex) {
            isSuccesful = false;
            throw ex;
        } finally {
            database.endTransaction();
        }
        return isSuccesful;
    }

    public List<BillModel> getAllProductsBill() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DbWrapperBill.SelectBillAllRecords, null);
        List<BillModel> list = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                BillModel billModel = new BillModel();
                billModel.setProductName(cursor.getString(cursor.getColumnIndex(DbWrapperBill.COLUMN_PRODUCT_NAME)));
                billModel.setProdctPrice(cursor.getInt(cursor.getColumnIndex(DbWrapperBill.COLUMN_PRICE)));
                billModel.setProductQuantity(cursor.getInt(cursor.getColumnIndex(DbWrapperBill.COLUMN_QUANTITY)));
                billModel.setProductTotal(cursor.getString(cursor.getColumnIndex(DbWrapperBill.COLUMN_TOTAL)));
                billModel.setBill_ID(cursor.getInt(cursor.getColumnIndex(DbWrapperBill.BILL_ID)));
                list.add(billModel);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public boolean deleteBill(int a) {
        SQLiteDatabase database = getWritableDatabase();
        //    Log.d("ddd", "Add method called ");
        boolean isSucces = false;
        Date date = new Date();
        database.beginTransaction();
        try {
            database.delete(DbWrapperBill.TABLE_ADD_TO_CART, DbWrapperBill.BILL_ID + "=?",
                    new String[]{String.valueOf(a)});
            database.setTransactionSuccessful();
            isSucces = true;
        } catch (Exception ex) {
            isSucces = false;
        } finally {
            database.endTransaction();
        }
        return isSucces;
    }

}


/*
 database.beginTransaction();
            CategoriesModel categoriesModel;

            String title, description, insertedBy, insertTime;
            int status1 = 1, signature = 1;
            try {
                for (int i = 0; i < list.size(); i++) {

                    title = list.get(i).getProduct_Category_Title();
                    description = list.get(i).getProduct_Category_Description();
                    ContentValues values = new ContentValues();
                    values.put(CategoriesWrapper.COLUMN_Category_Title, title);
                    values.put(CategoriesWrapper.COLUMN_Category_Descriptiuon, description);
                    values.put(CategoriesWrapper.COLUMN_Category_Inserted_By, "Ali");
                    values.put(CategoriesWrapper.COLUMN_Category_Insertion_Timedate, DateFormat.getDateTimeInstance().format(date));
                    values.put(CategoriesWrapper.COLUMN_Product_Type_ID, ID);
                    values.put(CategoriesWrapper.COLUMN_CATEGORY_STATUS, status1);
                    values.put(CategoriesWrapper.COLUMN_CATEGORY_SIGNATURE, signature);
                    //    Log.d("H", "product ID" + categoriesModel.getProduct_Type_Id());
                    //    Log.d("H", "product ID" + categoriesModel.getProduct_Category_Pic());
                    database.insertOrThrow(CategoriesWrapper.TABLE_NAME_For_Product_Categories, null, values);

                    database.setTransactionSuccessful();

                }
                IsSuccessFull = true;
            } catch (Exception ex) {
                IsSuccessFull = false;
                throw ex;
            } finally {
                db.endTransaction();
            }
        }
        return IsSuccessFull;
 */

/////////////////////  This function is used to get ID of Category show in Spinner \\\\\\\\\\
  /*  public String SearchCategoryTitle(String productName) {
        SQLiteDatabase db = getReadableDatabase();
        String query = CategoriesWrapper.SelectAllProductCategories;
        Cursor cursor = db.rawQuery(query, null);
        String name, CategoryId;
        CategoryId = "NotFound";
        if (cursor.moveToNext()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Title));
                // ProductTypeWrapper.KEY_PRODUCT_TITLE.indexOf(1);
                if (name.equals(productName)) {
                    //    Log.d("ddd", " Product Name...=" + productName);
                    //    Log.d("ddd", " Product....=" + name);
                    CategoryId = cursor.getString(cursor.getColumnIndex(CategoriesWrapper.COLUMN_Category_Id));
                    //    Log.d("ddd", " PCategoryID...=" + CategoryId);
                }
            } while (cursor.moveToNext());
        }
        return CategoryId;
    }
*/
