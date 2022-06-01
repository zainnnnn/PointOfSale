package com.example.caspos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class LastActivity extends AppCompatActivity {

    Button btnfinish;

    List<BillModel> billList;


    DBHelperClass dbHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        btnfinish = findViewById(R.id.btnfinish);

        dbHelperClass = DBHelperClass.getInstance(this);

        billList = dbHelperClass.getAllProductsBill();


        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < billList.size(); i++) {

                    int a = billList.get(i).getBill_ID();
                    dbHelperClass.deleteBill(a);
                }

                startActivity(new Intent(LastActivity.this, DashBoardActivity.class));
                finish();
            }
        });
    }
}
