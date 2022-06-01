package com.example.caspos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Bill_Activity extends AppCompatActivity {

    List<BillModel> billListt;

    TextView tvBillNetPrice, tvbilNetincome, tvBillPrint;
    EditText etDiscount;
    View view;
    Button btnTotalBil, btnCheckout, btnContinueShopping, btnBillPrint;
    RecyclerView rc_Bill;
    String Productname, Price, quantity;

    List<BillModel> modelList;

    DBHelperClass dbClas;


    float billCalculate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_);

        tvBillNetPrice = findViewById(R.id.billtv_netBillPrice);
        tvbilNetincome = findViewById(R.id.billtv_NetAmount);
        etDiscount = findViewById(R.id.billet_discount);
        btnTotalBil = findViewById(R.id.btn_netAmount);
        btnContinueShopping = findViewById(R.id.btncontinueShopping);
        btnCheckout = findViewById(R.id.btnCheclout);
        rc_Bill = findViewById(R.id.rcv_Bill);

        dbClas = DBHelperClass.getInstance(this);

        billListt = dbClas.getAllProductsBill();

        showbill();
        btnTotalBil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float discount = Float.parseFloat(etDiscount.getText().toString());
                float netAmount = (billCalculate / 100) * discount;
                float totalPrice = billCalculate - netAmount;
                tvbilNetincome.setText(totalPrice + "");
            }
        });
    }


    public void showbill() {
        final DBHelperClass dbHelper = DBHelperClass.getInstance(Bill_Activity.this);
        modelList = dbHelper.getAllProductsBill();

        for (int i = 0; i < modelList.size(); i++) {
            Log.d("ppp", "ShowBill" + (modelList.get(i).getProductTotal()));
            billCalculate = billCalculate + (modelList.get(i).getProductQuantity() * modelList.get(i).getProdctPrice());
        }

        BillAdapter billAdapter = new BillAdapter(modelList, Bill_Activity.this);
        rc_Bill.setLayoutManager(new LinearLayoutManager(Bill_Activity.this));
        rc_Bill.setAdapter(billAdapter);
        rc_Bill.setHasFixedSize(true);

        tvBillNetPrice.setText(billCalculate + "");

        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill_Activity.this, DashBoardActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billListt.isEmpty()) {
                    Toast.makeText(Bill_Activity.this, "Please Select at least one item", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(Bill_Activity.this, PaymentActivity.class));
                }

            }
        });




    }


}
