package com.example.caspos.company;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class DialogDeleteCompany extends Dialog {

    TextView tv_DeleteCompanyTitle;
    EditText et_ReasonForDeleteCompany;
    Button btn_deleteCompany, btn_cancelDeleteCompany;
    String company_ID;
    DBHelperClass dbHelperClass;
    Context context1;

    public DialogDeleteCompany(@NonNull Context context, String title, String ID) {
        super(context);
        setContentView(R.layout.dialog_delete_company);

        tv_DeleteCompanyTitle = findViewById(R.id.tvCompanyTitleForDeleteDialog);
        et_ReasonForDeleteCompany = findViewById(R.id.et_CompanyReasonForDeleteDialog);
        btn_deleteCompany = findViewById(R.id.btnDeleteCompany);
        btn_cancelDeleteCompany = findViewById(R.id.btnCancelDeleteCompany);

        tv_DeleteCompanyTitle.setText(title);
        company_ID = ID;
        Log.d("ccc", " ID FOR company 2 " + ID);
        dbHelperClass = DBHelperClass.getInstance(context);

        btn_deleteCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_ReasonForDeleteCompany.getText().toString().equals("")) {
                    et_ReasonForDeleteCompany.setError("Field should not empty");
                } else if (dbHelperClass.dbDeleteCompany(setData())) {
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                 //   getOwnerActivity().recreate();
                    dismiss();

                }
            }
        });

        btn_cancelDeleteCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CompanyModel setData() {
        CompanyModel companyModel = new CompanyModel();
        companyModel.setCompany_Title(tv_DeleteCompanyTitle.getText().toString());
        companyModel.setCompany_DeactiveReason(et_ReasonForDeleteCompany.getText().toString());
        companyModel.setCompany_ID(company_ID);
        Log.d("Tag", " ID FOR company 3" + company_ID);
        companyModel.setCompany_DeactiveBy("ALI");
        companyModel.setCompany_Status(0);
        return companyModel;
    }
}
