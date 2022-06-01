package com.example.caspos.Kids_Products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;
import com.example.caspos.Women_Products.CategoriesModel;
import com.example.caspos.Mens_Products.ProductTypeModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddSubCategoryActivity extends AppCompatActivity {

    EditText etTitel, etDescription;
    Button btnAdd;
    private final int RequestCodeGallery = 1;
    String SubCategoryUUID;
    List<CategoriesModel> subCategoryList;
    List<ProductTypeModel> productTypeModelList;
    Spinner subCategorySpinner, productTypeSpinner;
    DBHelperClass dbHelper;
    String CategoryId;
    String picUrlPath;
    ImageView iv_subCategory;
    String productTypeIDgetFromSpinner;
    String categoryID_get_fromSubCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category);

        etTitel = findViewById(R.id.etTitleforDialogforSubCategory);
        etDescription = findViewById(R.id.etDescribtionForDialogforSubCategory);
        btnAdd = findViewById(R.id.btnAddNowForDialogforSubCategory);
        iv_subCategory = findViewById(R.id.imageViewForSubCategory);

        iv_subCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String ImagPath = file.getPath();

                Uri uri = Uri.parse(ImagPath);
                intent.setDataAndType(uri, "image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, RequestCodeGallery);
            }
        });

        dbHelper = DBHelperClass.getInstance(AddSubCategoryActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(" ccc ", "Product Type ID 2 " + productTypeIDgetFromSpinner);
                if (SubCategoryUUID == null) {
                    SubCategoryUUID = UUID.randomUUID().toString();
                }
                if ((etTitel.getText().toString().isEmpty())) {
                    etTitel.setError("Field should not empty");
                } else if ((etDescription.getText().toString().isEmpty())) {
                    etDescription.setError("Field should not empty");
                } else if (dbHelper.AddProductSubCategory(getData())) {
                    Toast.makeText(AddSubCategoryActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSubCategoryActivity.this, SubCategoryActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            private SubCategoriesModel getData() {
                SubCategoriesModel categoriesModel = new SubCategoriesModel();
                categoriesModel.setSub_category_Title(etTitel.getText().toString());
                categoriesModel.setSub_category_Description(etDescription.getText().toString());
                categoriesModel.setSub_category_Uu_Id(SubCategoryUUID);
                categoriesModel.setSubcategory_Status(1);
                categoriesModel.setSub_category_Pic(picUrlPath);
                return categoriesModel;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeGallery) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        InputStream stream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(stream);
                        iv_subCategory.setImageBitmap(bitmap);
                        picUrlPath = uri.toString();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}



