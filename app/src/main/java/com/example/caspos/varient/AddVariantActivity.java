package com.example.caspos.varient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.UUID;

public class AddVariantActivity extends AppCompatActivity {
EditText ettitleVariant,etDescriptionVariant;
Button btnAddVariant;
DBHelperClass dbHelper;
String VariantUUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_variant);
        ettitleVariant=findViewById(R.id.etVariantTitle);
        etDescriptionVariant=findViewById(R.id.etVariantDescription);
        btnAddVariant=findViewById(R.id.btnAddVariantNow);

        dbHelper=DBHelperClass.getInstance(AddVariantActivity.this);
        btnAddVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariantUUID == null) {
                    VariantUUID = UUID.randomUUID().toString();
                }
                if (ettitleVariant.getText().toString().equals("")) {
                    ettitleVariant.setError("Field not be empty");
                }
                else if (etDescriptionVariant.getText().toString().equals("")) {
                    etDescriptionVariant.setError("Field not be empty");
                }
                else
                {
                    dbHelper.dbVariantInsert(getDataFromUI());
                    Toast.makeText(AddVariantActivity.this, "added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddVariantActivity.this,VarientActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    public VariantModelClass getDataFromUI(){
        VariantModelClass variantModelClass=new VariantModelClass();
        variantModelClass.setVariant_title(ettitleVariant.getText().toString());
        variantModelClass.setVariant_description(etDescriptionVariant.getText().toString());
        variantModelClass.setVariant_InsertBY("Sana");
        variantModelClass.setVariant_Signature(1);
        variantModelClass.setVariant_Status(1);
        variantModelClass.setVariant_UUID(VariantUUID);
        return variantModelClass;
    }
}
