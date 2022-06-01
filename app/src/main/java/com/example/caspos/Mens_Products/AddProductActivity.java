package com.example.caspos.Mens_Products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class AddProductActivity extends AppCompatActivity {

    EditText et_title, et_description;
    ImageView iv;
    DBHelperClass dbHelper;
    Button btn_Add_Now_Product;
    Context context;
    String picPath;
    private final int RequestCodeGallery = 1;

    String productTypeUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        iv = findViewById(R.id.imageViewForProduct);
        et_title = findViewById(R.id.etProductTitle);
        et_description = findViewById(R.id.etProductDescription);
        btn_Add_Now_Product = findViewById(R.id.btnAddProductNow);
        dbHelper = DBHelperClass.getInstance(context);


        iv.setOnClickListener(new View.OnClickListener() {
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

        btn_Add_Now_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productTypeUUID == null) {
                    productTypeUUID = UUID.randomUUID().toString();
                }
                if (et_title.getText().toString().equals("")) {
                    et_title.setError("Field not be empty");
                } else if (et_description.getText().toString().equals("")) {
                    et_description.setError("Field not be empty");
                } else if (dbHelper.dbProductTypeInsert(getDataFromUI())) {
                    Toast.makeText(AddProductActivity.this, "Insertion Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddProductActivity.this, ProductTypeActivity.class));
                    finish();
                }
            }
        });
    }

    public ProductTypeModel getDataFromUI() {
        ProductTypeModel model = new ProductTypeModel();
        model.setProduct_type_title(et_title.getText().toString());
        model.setProduct_type_description(et_description.getText().toString());
        model.setProductTypeStatus(1);
        model.setProductTypePic(picPath);
        model.setProductPrice(400);
        model.setProductTypeUUID(productTypeUUID);
        Log.d("ppp", "Pic path = " + picPath);
        return model;
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
                        iv.setImageBitmap(bitmap);
                        picPath = uri.toString();
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
