package com.example.caspos.varient;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.List;

public class DeleteVariantDialog extends Dialog {
    EditText etVariantReason;
    TextView tvVariantTitledelete;
    Button btnDeleteVariant, btnCancel;
    DBHelperClass dbHelper;
    String DeleteVaraintID;
    List<VariantModelClass> classList;

    public DeleteVariantDialog(Context context, String VariantTitle, int position, final String VariantID) {
        super(context);
        setContentView(R.layout.delete_variant_dialog);
        etVariantReason = findViewById(R.id.etVariantReasonForDeleteDialog);
        tvVariantTitledelete = findViewById(R.id.tvVariantTitleForDeleteDialog);
        btnDeleteVariant = findViewById(R.id.btnDeleteVariant);
        btnCancel = findViewById(R.id.btnCancelDeleteVariant);

        dbHelper = DBHelperClass.getInstance(context);
        classList = dbHelper.getAllVariants();

        tvVariantTitledelete.setText(VariantTitle);
        final DBHelperClass dbHelper = DBHelperClass.getInstance(getContext());
        DeleteVaraintID = VariantID;

        btnDeleteVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dbHelper.dbVariantDelete(getdateFromUI())){
                    Toast.makeText(getContext(), "yeah Deleted", Toast.LENGTH_LONG).show();
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

    public VariantModelClass getdateFromUI() {
        VariantModelClass v = new VariantModelClass();
        v.setVariant_title(tvVariantTitledelete.getText().toString());
        v.setVariant_DeactiveReason(etVariantReason.getText().toString());
        v.setVariant_Status(0);
        v.setVariant_ID(DeleteVaraintID);
        v.setVariant_Signature(1);
        v.setVariant_DeactiveBy("sana");
        return v;
    }
}
