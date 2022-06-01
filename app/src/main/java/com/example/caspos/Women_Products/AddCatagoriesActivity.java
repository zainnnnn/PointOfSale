package com.example.caspos.Women_Products;

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
import com.example.caspos.Mens_Products.ProductTypeModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddCatagoriesActivity extends AppCompatActivity {

    EditText etCatagoryTitel, etCatagoryDescription;
    Button btn_AddCatagory;
    ImageView imageViewForCatagory;
    String picPath;
    Spinner productTypeSpinner;
    List<ProductTypeModel> modelList;
    private final int RequestCodeGallery = 1;
    List<CategoriesModel> catagoryList;
    DBHelperClass db;
    String productID;
    String catagoryUUID;

    String productTypeIDgetFomCategoriesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catagories);

        etCatagoryTitel = findViewById(R.id.etCatagoryTitle);
        etCatagoryDescription = findViewById(R.id.etCatagoryDescription);
        btn_AddCatagory = findViewById(R.id.btnAddNowForCatagory);
        imageViewForCatagory = findViewById(R.id.imageViewForCatagory);

        productTypeIDgetFomCategoriesActivity = getIntent().getStringExtra("ProductTypeID");

        Log.d("rrr", "onCreate: " + productTypeIDgetFomCategoriesActivity);

        imageViewForCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String ImagPath = file.getPath();

                Uri uri = Uri.parse(ImagPath);
                intent.setDataAndType(uri, "image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, RequestCodeGallery);
            }
        });
        btn_AddCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperClass dbhelper = DBHelperClass.getInstance(AddCatagoriesActivity.this);
                if (catagoryUUID == null) {
                    catagoryUUID = UUID.randomUUID().toString();
                }
                if (etCatagoryTitel.getText().toString().isEmpty()) {
                    etCatagoryTitel.setError("Field should not empty");
                } else if (etCatagoryDescription.getText().toString().isEmpty()) {
                    etCatagoryDescription.setError("Field should not empty");
                } else if (dbhelper.AddProductCategory(getData())) {
                    Toast.makeText(AddCatagoriesActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCatagoriesActivity.this, CategorieslistActivity.class));
                    finish();
                }

            }
        });
    }

    public CategoriesModel getData() {
        CategoriesModel categoriesModel = new CategoriesModel();
        categoriesModel.setProduct_Category_Title(etCatagoryTitel.getText().toString());
        categoriesModel.setProduct_Category_Description(etCatagoryDescription.getText().toString());
        categoriesModel.setProduct_Category_Uu_Id(catagoryUUID);
        categoriesModel.setProduct_Category_Pic(picPath);
        categoriesModel.setProduct_Category_Status(1);
        //  Log.d("hhh", "Category .......PIC......... " + picPath);
        //  Log.d("ID", "Product ID " + productID);
        return categoriesModel;
    }

    public void getSelectedProduct(View view) {
        ProductTypeModel p = (ProductTypeModel) productTypeSpinner.getSelectedItem();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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


                        imageViewForCatagory.setImageBitmap(bitmap);
                        picPath = uri.toString();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}