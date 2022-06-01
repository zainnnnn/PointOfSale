package com.example.caspos.Kids_Products;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class DialogUpdateSubcategory extends Dialog {

    EditText updateSubCategoryTitle, updateSubCategoryDescription;
    Button btn_updateSubCategory, btn_cancelUpdateSubCategory;

    String subCategoryID;
    DBHelperClass dbHelperClass;
    String categoryID_get_fromSubCategory;

    public DialogUpdateSubcategory(final Context context, String title, String description, String ID ,String categoryID) {
        super(context);
        setContentView(R.layout.update_sub_category);

        updateSubCategoryTitle = findViewById(R.id.etUpdateSubCategoryTitle);
        updateSubCategoryDescription = findViewById(R.id.etUpdateSubCategoryDescription);
        btn_updateSubCategory = findViewById(R.id.btnUpdateSubCategory);
        btn_cancelUpdateSubCategory = findViewById(R.id.btnCancelUpdateSubCategory);

        updateSubCategoryTitle.setText(title);
        updateSubCategoryDescription.setText(description);
        subCategoryID = ID;
        dbHelperClass = DBHelperClass.getInstance(context);
        categoryID_get_fromSubCategory=categoryID;
        btn_updateSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (updateSubCategoryTitle.getText().toString().equals("") && updateSubCategoryDescription.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelperClass.dbSubCategoryUpdate(getData()))
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SubCategoryActivity.class);
                intent.putExtra("CategoryID", categoryID_get_fromSubCategory);
                context.startActivity(intent);
                dismiss();
            }
        });
        btn_cancelUpdateSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public SubCategoriesModel getData() {
        SubCategoriesModel model = new SubCategoriesModel();
        model.setSub_category_Title(updateSubCategoryTitle.getText().toString());
        model.setSub_category_Description(updateSubCategoryDescription.getText().toString());
        model.setSubcategory_Status(1);
        model.setSub_category_Id(subCategoryID);
        return model;
    }
}
