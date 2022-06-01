package com.example.caspos.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.UUID;

public class AddCompanyActivity extends AppCompatActivity {

    EditText et_companyTitle, et_companyDescription;
    Button btn_AddCompany;
    DBHelperClass dbHelperClass;
    String companyUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        et_companyTitle = findViewById(R.id.etCompanyTitle);
        et_companyDescription = findViewById(R.id.etCompanyDescription);
        btn_AddCompany = findViewById(R.id.btnAddCompanyNow);

        dbHelperClass = DBHelperClass.getInstance(AddCompanyActivity.this);
        btn_AddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (companyUUID == null) {
                    companyUUID = UUID.randomUUID().toString();
                }
                if (dbHelperClass.dbInsertCompany(getData())) {
                    Toast.makeText(AddCompanyActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCompanyActivity.this, CompanyActivity.class));
                    finish();
                }
            }
        });
    }

    public CompanyModel getData() {
        CompanyModel companyModel = new CompanyModel();
        companyModel.setCompany_Title(et_companyTitle.getText().toString());
        companyModel.setCompany_Description(et_companyDescription.getText().toString());
        companyModel.setCompany_Status(1);
        companyModel.setCompany_InsertBY("Ali");
        companyModel.setCompany_UUID(companyUUID);
        companyModel.setCompany_Signature(1);
        return companyModel;
    }
}
