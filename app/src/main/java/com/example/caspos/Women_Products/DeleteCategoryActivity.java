package com.example.caspos.Women_Products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class DeleteCategoryActivity extends AppCompatActivity {
    TextView tvTitlecategory;
    EditText etCategoryReason;
    Button btnDeleteCategory, btnCancelDeleteCategory;
    String CategoryId;
    Spinner categoryReplacement;
    DBHelperClass dbHelper;
    List<CategoriesModel> categoriesModelList;
    String categoryIDgetFromSpinner;
    String Categorytitle;
    String CategoryID;
    String productTypeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        tvTitlecategory = findViewById(R.id.tvCategoryTitleForDeleteDialog);
        etCategoryReason = findViewById(R.id.etCategoryDescriptionForDeleteDialog);
        btnDeleteCategory = findViewById(R.id.btnDeleteCategory);
        btnCancelDeleteCategory = findViewById(R.id.btnCancelDeleteCategory);

        Categorytitle = getIntent().getStringExtra("title");
        productTypeID = getIntent().getStringExtra("productTypeID");
        CategoryID = getIntent().getStringExtra("categoryID");
        CategoryId = CategoryID;
        tvTitlecategory.setText(Categorytitle);
        Log.d("ccc", " Ti  ,  CID  ,  PID    " + Categorytitle + "   " + CategoryID + "     " + productTypeID);
        dbHelper = DBHelperClass.getInstance(DeleteCategoryActivity.this);
        categoriesModelList = dbHelper.getAllProductCategories();


        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnCancelDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public CategoriesModel deleteDataFromUI() {
        CategoriesModel model = new CategoriesModel();
        model.setProduct_Category_Title(tvTitlecategory.getText().toString());
        model.setProduct_Category_Deletion_Reason(etCategoryReason.getText().toString());
        model.setProduct_Category_Id(CategoryId);
        model.setProduct_Category_Status(0);
        model.setProduct_Category_Deletion_By("Ali");
        return model;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
