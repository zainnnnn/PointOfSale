package com.example.caspos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCart extends Dialog {
  String UpdateName,UpdateModel,UpdateDescription;
  int UpdatePrice;
    TextView et_AddToCartProductName,et_AddToCartProductPrce;
    EditText getAddToCartQuantity;
    Button btnAddToCart;
    Context context;


    public AddToCart(final Context context, String UpdateName, String UpdatePrice) {
        super(context);
        setContentView(R.layout.activity_add_to_cart);
        this.UpdateName=UpdateName;
        this.UpdateDescription=UpdateDescription;

        et_AddToCartProductName=findViewById(R.id.addtocartName);

        et_AddToCartProductPrce=findViewById(R.id.addToCartPrice);
        getAddToCartQuantity=findViewById(R.id.addtocartQuantity);
        btnAddToCart=findViewById(R.id.addTocartButton);

        et_AddToCartProductName.setText(UpdateName);
        et_AddToCartProductPrce.setText(UpdatePrice+"");

        final  DBHelperClass dbHelper=DBHelperClass.getInstance(context);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbHelper.insertBill(getData())){
                    Toast.makeText(context, "Bill Insert", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, Bill_Activity.class));
                }
                dismiss();

            }
        });

    }

    public BillModel getData(){
        BillModel billModel=new BillModel();
        billModel.setProductName(et_AddToCartProductName.getText().toString());
        billModel.setProdctPrice(Integer.parseInt(et_AddToCartProductPrce.getText().toString()));
        billModel.setProductQuantity(Integer.parseInt(getAddToCartQuantity.getText().toString()));
        return billModel;
    }

}
