package com.example.caspos.Women_Products;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.R;

import java.util.ArrayList;
import java.util.List;

public class DialogDeleteCategory extends Dialog {
    TextView tvTitlecategory;
    EditText etCategoryReason;
    Button btnDeleteCategory, btnCancelDeleteCategory;
    String CategoryId;

    DBHelperClass dbHelper;
    List<CategoriesModel> categoriesModelList;


    public DialogDeleteCategory(final Context context, String Categorytitle, String CategoryID) {
        super(context);
        setContentView(R.layout.activity_dialog_delete_category);

        tvTitlecategory = findViewById(R.id.tvCategoryTitleForDeleteDialog);
        etCategoryReason = findViewById(R.id.etCategoryDescriptionForDeleteDialog);
        btnDeleteCategory = findViewById(R.id.btnDeleteCategory);
        btnCancelDeleteCategory = findViewById(R.id.btnCancelDeleteCategory);


        tvTitlecategory.setText(Categorytitle);
        dbHelper = DBHelperClass.getInstance(getContext());
        categoriesModelList = dbHelper.getAllProductCategories();
        CategoryId = CategoryID;
        btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCategoryReason.getText().toString().equals(null)) {
                    etCategoryReason.setError("Enter Fields");
                }
            }
        });
        btnCancelDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CategoriesModel deleteDataFromUI() {
        CategoriesModel model = new CategoriesModel();
        model.setProduct_Category_Title(tvTitlecategory.getText().toString());
        model.setProduct_Category_Deletion_Reason(etCategoryReason.getText().toString());
        model.setProduct_Category_Id(CategoryId);
        model.setProduct_Category_Status(0);
        model.setProduct_Category_Deletion_By("Ali");
        return model;
    }

    /*
    public void CategoryDeleteReplacementSpinner() {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < categoriesModelList.size(); i++) {
            list.add(categoriesModelList.get(i).getProduct_Category_Title());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
      //  categoryReplacement.setAdapter(adapter);
        categoryReplacement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // String name = adapterView.getItemAtPosition(position).toString();
                Integer l = adapterView.getSelectedItemPosition();
                categoryIDgetFromSpinner = categoriesModelList.get(position).getProduct_Category_Id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }*/
}
