package com.example.caspos.Kids_Products;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class DialogDeleteSubCategory extends Dialog {

    TextView tv_title;
    EditText et_DeletionReason;
    Button btn_DeleteSubCategory, btn_CancelDeleteSubCatagory;
    String subCategoryID;
    DBHelperClass dbHelperClass;
    String categoryID_get_fromSubCategory;

    public DialogDeleteSubCategory(final Context context, String title, String description, String ID, String categoryID) {
        super(context);
        setContentView(R.layout.delete_sub_category);

        tv_title = findViewById(R.id.tvDeleteSubCategoryTitle);
        et_DeletionReason = findViewById(R.id.etSubCategoryReason);
        btn_DeleteSubCategory = findViewById(R.id.btnDeleteSubCategory);
        btn_CancelDeleteSubCatagory = findViewById(R.id.btnCancelDeleteSubCategory);

        tv_title.setText(title);
        subCategoryID = ID;
        categoryID_get_fromSubCategory = categoryID;
        dbHelperClass = DBHelperClass.getInstance(context);

        btn_DeleteSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_DeletionReason.getText().toString().equals(null)) {
                    et_DeletionReason.setError("put Reason");
                }
                dismiss();
            }
        });
        btn_CancelDeleteSubCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public SubCategoriesModel deleteData() {
        SubCategoriesModel model = new SubCategoriesModel();
        model.setSub_category_Title(tv_title.getText().toString());
        model.setSub_category_Deletion_Reason(et_DeletionReason.getText().toString());
        model.setSubcategory_Status(0);
        model.setSub_category_Id(subCategoryID);
        return model;
    }
}
