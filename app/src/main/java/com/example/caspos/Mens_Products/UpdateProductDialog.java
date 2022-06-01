package com.example.caspos.Mens_Products;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class UpdateProductDialog extends Dialog {

    EditText et_Updatetitle, et_Updatedescription;
    DBHelperClass dbHelper;
    Button btn_Update_Now_Product, btn_Cancel_Update_Now_Product;
    Context context1;
    String productUpdateID;

    public UpdateProductDialog(Context context, String productTitle, String description, String productID) {
        super(context);
        setContentView(R.layout.activity_update_product_dialog);

        et_Updatetitle = findViewById(R.id.etProductTitleForUpdateDialog);
        et_Updatedescription = findViewById(R.id.etProductDescriptionForUpdateDialog);
        btn_Update_Now_Product = findViewById(R.id.btnUpdateProductType);
        btn_Cancel_Update_Now_Product = findViewById(R.id.btnCancelUpdateProductType);
        dbHelper = DBHelperClass.getInstance(context);
        context1 = context;
        et_Updatetitle.setText(productTitle);
        et_Updatedescription.setText(description);
        productUpdateID = productID;
        btn_Update_Now_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_Updatetitle.getText().toString().equals("") && et_Updatedescription.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.dbProductTypeUpdate(getDataFromUI())) {
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    context1.startActivity(new Intent(context1, ProductTypeActivity.class));
                    dismiss();
                }
            }


        });
        btn_Cancel_Update_Now_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public ProductTypeModel getDataFromUI() {
        ProductTypeModel model = new ProductTypeModel();
        model.setProduct_type_title(et_Updatetitle.getText().toString());
        model.setProduct_type_description(et_Updatedescription.getText().toString());
        model.setProductTypeStatus(1);
        model.setProduct_type_ID(productUpdateID);
        return model;
    }
}
