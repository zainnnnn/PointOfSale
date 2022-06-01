package com.example.caspos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddresActivity extends AppCompatActivity {

    EditText etName,etCity,etAddres,etPhone;

    Button btnFinalize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addres);
        etName=findViewById(R.id.etnameaddres);
        etAddres=findViewById(R.id.etaddresAdress);
        etCity=findViewById(R.id.etcityaddres);
        etPhone=findViewById(R.id.etphoneaddres);
        btnFinalize=findViewById(R.id.btnfinalize);

        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (etName.getText().toString().isEmpty()){
                   etName.setError("Enter Name");
               }else if (etCity.getText().toString().isEmpty()){
                   etCity.setError("Enter Address");
               }else if (etAddres.getText().toString().isEmpty()){
                   etAddres.setError("Enter city");
               }else if (etPhone.getText().toString().isEmpty()){
                   etPhone.setError("Enter Phone");
               }else {
                   startActivity(new Intent(AddresActivity.this,LastActivity.class));
               }
            }
        });
    }
}
