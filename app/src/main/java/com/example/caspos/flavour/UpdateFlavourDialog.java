package com.example.caspos.flavour;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class UpdateFlavourDialog extends Dialog {
    EditText etFlavourTitle, etFlavourDescription;
    Button btnUpdate, btnCancel;
    DBHelperClass dbHelper;
    String FlavourUpdateID;

    public UpdateFlavourDialog(Context context, String flavourTitle, String Description, String flavourID) {
        super(context);
        setContentView(R.layout.activity_update_flavour_dialog);
        etFlavourTitle = findViewById(R.id.etFlavourTitleForUpdateDialog);
        etFlavourDescription = findViewById(R.id.etFlavourDescriptionForUpdateDialog);
        btnUpdate = findViewById(R.id.btnUpdateFlavour);
        btnCancel = findViewById(R.id.btnCancelUpdateFlavour);
        dbHelper = DBHelperClass.getInstance(context);

        etFlavourTitle.setText(flavourTitle);
        etFlavourDescription.setText(Description);
        FlavourUpdateID = flavourID;

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFlavourTitle.getText().toString().equals("") && etFlavourDescription.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.dbFlavourUpdate(getDataFronUI())) {
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public FlavourModelClass getDataFronUI() {
        FlavourModelClass f = new FlavourModelClass();
        f.setFlavour_title(etFlavourTitle.getText().toString());
        f.setFlavour_description(etFlavourDescription.getText().toString());
        f.setFlavour_Status(1);
        f.setFlavour_ID(FlavourUpdateID);
        f.setFlavour_ModifiedBy("sana");
        f.setFlavour_Signature(1);
        return f;
    }
}
