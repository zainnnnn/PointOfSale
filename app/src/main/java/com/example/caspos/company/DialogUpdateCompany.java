package com.example.caspos.company;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class DialogUpdateCompany extends Dialog {

    EditText et_UpdateCompanyTitle;
    EditText et_DescriptionForUpdateCompany;
    Button btn_UpdateCompany, btn_cancelUpdateCompany;
    String company_ID;
    DBHelperClass dbHelperClass;

    public DialogUpdateCompany(@NonNull Context context, String title, String description, String ID) {
        super(context);
        setContentView(R.layout.dialog_update_company);

        et_UpdateCompanyTitle = findViewById(R.id.et_CompanyTitleForUpdateDialog);
        et_DescriptionForUpdateCompany = findViewById(R.id.et_CompanyDescriptionForUpdateDialog);
        btn_UpdateCompany = findViewById(R.id.btnUpdateCompany);
        btn_cancelUpdateCompany = findViewById(R.id.btnCancelUpdateCompany);

        et_UpdateCompanyTitle.setText(title);
        et_DescriptionForUpdateCompany.setText(description);
        company_ID = ID;
        Log.d("Tag", " ID FOR company 2 " + ID);
        dbHelperClass = DBHelperClass.getInstance(context);

        btn_UpdateCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_UpdateCompanyTitle.getText().toString().equals("")) {
                    et_UpdateCompanyTitle.setError("Field should not empty");
                } else if (dbHelperClass.dbUpdateCompany(setData())) {
                    Toast.makeText(getContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });

        btn_cancelUpdateCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CompanyModel setData() {
        CompanyModel companyModel = new CompanyModel();
        companyModel.setCompany_Title(et_UpdateCompanyTitle.getText().toString());
        companyModel.setCompany_Description(et_DescriptionForUpdateCompany.getText().toString());
        companyModel.setCompany_ID(company_ID);
        Log.d("Tag", " ID FOR company 3" + company_ID);
        companyModel.setCompany_ModifiedBy("ALI");
        return companyModel;
    }
}
