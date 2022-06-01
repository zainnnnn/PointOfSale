package com.example.caspos.Women_Products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class UpdateCategoryActivity extends AppCompatActivity {
    EditText et_UpdatetitleCategory, et_UpdatedescriptionCategory;
    DBHelperClass dbHelper;
    Button btn_Update_Category, btn_Cancel_Update_Category;
    String CategoryUpdateID;
    String CategoryTitle, CategoryDescription, CategoryID, CategoryId, productTypeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        et_UpdatetitleCategory = findViewById(R.id.etCategoryTitleForUpdateDialog);
        et_UpdatedescriptionCategory = findViewById(R.id.etCategoryDescriptionForUpdateDialog);
        btn_Update_Category = findViewById(R.id.btnUpdateCategory);
        btn_Cancel_Update_Category = findViewById(R.id.btnCancelUpdateCategory);

        dbHelper = DBHelperClass.getInstance(UpdateCategoryActivity.this);

        CategoryTitle = getIntent().getStringExtra("title");
        CategoryDescription = getIntent().getStringExtra("description");
        productTypeID = getIntent().getStringExtra("productTypeID");
        CategoryID = getIntent().getStringExtra("categoryID");
        CategoryId = CategoryID;

        et_UpdatetitleCategory.setText(CategoryTitle);
        et_UpdatedescriptionCategory.setText(CategoryDescription);
        CategoryUpdateID = CategoryID;
        btn_Update_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_UpdatetitleCategory.getText().toString().equals("") && et_UpdatedescriptionCategory.getText().toString().equals("")) {
                    Toast.makeText(UpdateCategoryActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.DBCategoryUpdate(getDataFromUI())) {
                    Toast.makeText(UpdateCategoryActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateCategoryActivity.this, CategorieslistActivity.class);
                    intent.putExtra("productTypeID", productTypeID);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btn_Cancel_Update_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
