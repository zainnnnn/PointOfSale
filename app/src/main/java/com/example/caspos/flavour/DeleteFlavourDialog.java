package com.example.caspos.flavour;

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

public class DeleteFlavourDialog extends Dialog {
    EditText etFlavourReason;
    TextView tvFlavourTitledelete;
    Button btnDeleteFlavour, btnCancel;
    DBHelperClass dbHelper;
    String DeleteFlavourID;
    List<FlavourModelClass> classList;


    public DeleteFlavourDialog(final Context context, String flavourTitle, int position, final String FlavourID) {
        super(context);
        setContentView(R.layout.activity_delete_flavour_dialog);
        etFlavourReason = findViewById(R.id.tvFlavourReasonForDeleteDialog);
        tvFlavourTitledelete = findViewById(R.id.tvFlavourTitleForDeleteDialog);
        btnDeleteFlavour = findViewById(R.id.btnDeleteFlavour);
        btnCancel = findViewById(R.id.btnCancelDeleteFalvour);

        dbHelper = DBHelperClass.getInstance(context);
        classList = dbHelper.getAllFlavours();

        tvFlavourTitledelete.setText(flavourTitle);
        final DBHelperClass dbHelper = DBHelperClass.getInstance(getContext());
        DeleteFlavourID = FlavourID;


        btnDeleteFlavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.dbFlavpurDelete(getdateFromUI())) {
                    Toast.makeText(context, "yeah Deleted", Toast.LENGTH_LONG).show();
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

    public DeleteFlavourDialog(Context context) {
        super(context);
    }

    public FlavourModelClass getdateFromUI() {
        FlavourModelClass f = new FlavourModelClass();
        f.setFlavour_title(tvFlavourTitledelete.getText().toString());
        f.setFlavour_DeactiveReason(etFlavourReason.getText().toString());
        f.setFlavour_Status(0);
        f.setFlavour_ID(DeleteFlavourID);
        f.setFlavour_Signature(1);
        f.setFlavour_DeactiveBy("sana");
        return f;
    }
}
