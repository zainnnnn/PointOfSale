package com.example.caspos.Mens_Products;

import android.app.Dialog;
import android.content.Context;
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

public class DialogDeleteProduct extends Dialog {

    TextView tv_ProductTitle;
    EditText et_ProductReason;
    String deletedProductTypeID;
    Button btn_DeleteProductType, btn_CancelDeleteProductType;

    Spinner ProductReplacement;
    DBHelperClass dbHelper;
    List<ProductTypeModel> productTypeModelList;
    String productTypeIDGetFromSpinner;

    public DialogDeleteProduct(final Context context, String productTitle, int position, final String productID) {
        super(context);
        setContentView(R.layout.activity_dialog_delete_product);

        tv_ProductTitle = findViewById(R.id.tvProductTitleForDeleteDialog);
        et_ProductReason = findViewById(R.id.tvProductDescriptionForDeleteDialog);
        btn_DeleteProductType = findViewById(R.id.btnDeleteProduct);
        btn_CancelDeleteProductType = findViewById(R.id.btnCancelDeleteProductType);
        dbHelper = DBHelperClass.getInstance(context);
        productTypeModelList = dbHelper.getAllProductTypes();

        tv_ProductTitle.setText(productTitle);
        final DBHelperClass dbHelper = DBHelperClass.getInstance(getContext());
        deletedProductTypeID = productID;


        btn_DeleteProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_ProductReason.getText().toString().equals(null)) {
                    et_ProductReason.setError("Enter Fields");
                }
            }
        });
        btn_CancelDeleteProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public DialogDeleteProduct(Context context) {
        super(context);
    }

    public ProductTypeModel deleteDataFromUI() {
        ProductTypeModel model = new ProductTypeModel();
        model.setProduct_type_title(tv_ProductTitle.getText().toString());
        model.setProductTypeDeactiveReason(et_ProductReason.getText().toString());
        model.setProduct_type_ID(deletedProductTypeID);
        model.setProductTypeStatus(0);
        return model;
    }


}
