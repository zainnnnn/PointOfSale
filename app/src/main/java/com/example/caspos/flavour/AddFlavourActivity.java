package com.example.caspos.flavour;

import android.content.Context;
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

public class AddFlavourActivity extends AppCompatActivity {
    EditText flavourTitle, flavourDescription;
    Button flavourAdd;
    DBHelperClass dbHelper;
    Context context;
    String FlavourUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flavour);
        flavourTitle = findViewById(R.id.etFlavourTitle);
        flavourDescription = findViewById(R.id.etFlavourDescription);
        flavourAdd = findViewById(R.id.btnAddFlavourNow);

        dbHelper = DBHelperClass.getInstance(this);
        flavourAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FlavourUUID == null) {
                    FlavourUUID = UUID.randomUUID().toString();
                }
                if (flavourTitle.getText().toString().equals("")) {
                    flavourTitle.setError("Field not be empty");
                } else if (flavourDescription.getText().toString().equals("")) {
                    flavourDescription.setError("Field not be empty");
                } else {
                    dbHelper.dBFlavourInsert(getDataFromUI());
                    Toast.makeText(AddFlavourActivity.this, "yesh", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddFlavourActivity.this, FlavourActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public FlavourModelClass getDataFromUI() {
        FlavourModelClass flavourModelClass = new FlavourModelClass();
        flavourModelClass.setFlavour_title(flavourTitle.getText().toString());
        flavourModelClass.setFlavour_description(flavourDescription.getText().toString());
        flavourModelClass.setFlavour_Status(1);
        flavourModelClass.setFlavour_UUID(FlavourUUID);
        flavourModelClass.setFlavour_InsertBY("sana");
        flavourModelClass.setFlavour_Signature(1);
        return flavourModelClass;
    }
}
