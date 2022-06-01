package com.example.caspos.Women_Products;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class DialogUpdateCategory extends Dialog {

    EditText et_UpdatetitleCategory, et_UpdatedescriptionCategory;
    DBHelperClass dbHelper;
    Button btn_Update_Category, btn_Cancel_Update_Category;
    String CategoryUpdateID;
    String productTypeID1;

    public DialogUpdateCategory(final Context context, String CategoryTitle, String CategoryDescription, String CategoryID, String productTypeID) {
        super(context);
        setContentView(R.layout.activity_update_category_dialog);
        et_UpdatetitleCategory = findViewById(R.id.etCategoryTitleForUpdateDialog);
        et_UpdatedescriptionCategory = findViewById(R.id.etCategoryDescriptionForUpdateDialog);
        btn_Update_Category = findViewById(R.id.btnUpdateCategory);
        btn_Cancel_Update_Category = findViewById(R.id.btnCancelUpdateCategory);

        dbHelper = DBHelperClass.getInstance(context);
        et_UpdatetitleCategory.setText(CategoryTitle);
        et_UpdatedescriptionCategory.setText(CategoryDescription);
        CategoryUpdateID = CategoryID;
        productTypeID1 = productTypeID;
        btn_Update_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_UpdatetitleCategory.getText().toString().equals("") && et_UpdatedescriptionCategory.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.DBCategoryUpdate(getDataFromUI())) {
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, CategorieslistActivity.class);
                    intent.putExtra("productTypeID", productTypeID1);
                    context.startActivity(intent);
                    dismiss();
                }
            }
        });

        btn_Cancel_Update_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CategoriesModel getDataFromUI() {
        CategoriesModel model = new CategoriesModel();
        model.setProduct_Category_Title(et_UpdatetitleCategory.getText().toString());
        model.setProduct_Category_Description(et_UpdatedescriptionCategory.getText().toString());
        model.setProduct_Category_Status(1);
        model.setProduct_Category_Id(CategoryUpdateID);
        model.setProduct_Category_Updation_By("Ali");
        return model;
    }
}
