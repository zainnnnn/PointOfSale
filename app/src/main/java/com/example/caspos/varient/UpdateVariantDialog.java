package com.example.caspos.varient;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

public class UpdateVariantDialog extends Dialog {
    EditText VariantTitle, VariantDescription;
    Button btnUpdate, btnCancel;
    DBHelperClass dbHelper;
    String Variantid;

    public UpdateVariantDialog(Context context, String title, String description, String VariantID) {
        super(context);
        setContentView(R.layout.update_variant_dialog);
        VariantTitle = findViewById(R.id.etVariantTitleForUpdateDialog);
        VariantDescription = findViewById(R.id.etVariantDescriptionForUpdateDialog);
        btnUpdate = findViewById(R.id.btnUpdateVariant);
        btnCancel = findViewById(R.id.btnCancelUpdateVariant);

        dbHelper = DBHelperClass.getInstance(context);
        VariantTitle.setText(title);
        VariantDescription.setText(description);
        Variantid = VariantID;

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariantTitle.getText().toString().equals("") && VariantDescription.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fields should not be empty", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.dbVariantUpdate(getDataFromUI())) {
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

    public VariantModelClass getDataFromUI() {
        VariantModelClass v = new VariantModelClass();
        v.setVariant_title(VariantTitle.getText().toString());
        v.setVariant_description(VariantDescription.getText().toString());
        v.setVariant_Status(1);
        v.setVariant_ID(Variantid);
        v.setVariant_ModifiedBy("sana");
        v.setVariant_Signature(1);
        return v;
    }
}
