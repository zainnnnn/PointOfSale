package com.example.caspos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class invoice extends AppCompatActivity {

    TextView tvinvoice;

    double TotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvinvoice=findViewById(R.id.tv_invoice);

        Bill_Activity bill_activity=new Bill_Activity();
        TotalAmount=bill_activity.billCalculate;

        tvinvoice.setText(TotalAmount+"");




    }

}
