package com.example.caspos.Kids_Products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.caspos.AddToCart;
import com.example.caspos.DBHelperClass;
import com.example.caspos.DashBoardActivity;
import com.example.caspos.R;
import com.example.caspos.Women_Products.CategorieslistActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    List<SubCategoriesModel> modelList;
    RecyclerView recyclerView;
    Context context;
    String CategoryID_getFrom_Catagories;
    String CategoryId;
    String categoryName;
    SubCategoryAdapter subCategoriesAdapter;
    DBHelperClass dbHelperClass;
    String categoryID_pass_to_subCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rc);
        CategoryID_getFrom_Catagories = getIntent().getStringExtra("CategoryID");
        categoryName = getIntent().getStringExtra("catageoryName");

        //   categoryID_pass_to_subCategory=CategoryID_getFrom_Catagories;
        Log.d("ccc", "categoryId..........." + CategoryID_getFrom_Catagories);
        SHow();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(SubCategoryActivity.this, AddSubCategoryActivity.class);
                intent.putExtra("CategoryID", CategoryID_getFrom_Catagories);
                startActivity(intent);
              //  finish();

            }

        });
    }

    public void SHow() {
        Log.d("fff", "sub catagory" + CategoryID_getFrom_Catagories);
        Log.d("fff", "sub catagoryName" + categoryName);
        dbHelperClass = DBHelperClass.getInstance(SubCategoryActivity.this);

        if (CategoryID_getFrom_Catagories == null) {
            modelList = dbHelperClass.getAllSubCatagories();
            subCategoriesAdapter = new SubCategoryAdapter(context, modelList);
        } else {
            modelList = dbHelperClass.getSingleSubCatagories(CategoryID_getFrom_Catagories);//getSingleSubCatagories(CategoryID_getFrom_Catagories);
            subCategoriesAdapter = new SubCategoryAdapter(context, modelList);
        }

        recyclerView.setAdapter(subCategoriesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        subCategoriesAdapter.setOnItemClickListener(new SubCategoryAdapter.OnItemClickListener() {
            @Override
            public void OnItem(int position) {
                AddToCart addToCart = new AddToCart(SubCategoryActivity.this, modelList.get(position).getSub_category_Title(), modelList.get(position).getSub_category_Description());
                addToCart.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subCategoriesAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SubCategoryActivity.this, DashBoardActivity.class));
    }
}

/*
                final Dialog dialog = new Dialog(SubCategoryActivity.this);
                dialog.setContentView(R.layout.dialogforaddsubcategory);

                etTitel = dialog.findViewById(R.id.etTitleforDialogforSubCategory);
                etDescription = dialog.findViewById(R.id.etDescribtionForDialogforSubCategory);
                btnAdd = dialog.findViewById(R.id.btnAddNowForDialogforSubCategory);
                subCategorySpinner = dialog.findViewById(R.id.SubcatagoriesSpinner);

                dialog.show();
                dbHelper = DBHelperClass.getInstance(SubCategoryActivity.this);
                subCategoryList = dbHelper.getAllProductCategoriesTitle();
                ArrayList<String> listcatagory = new ArrayList<>();
                for (int i = 0; i < subCategoryList.size(); i++) {
                    listcatagory.add(subCategoryList.get(i).getProduct_Category_Title());
                }
                ArrayAdapter<String> catagoryAdapter = new ArrayAdapter<>(SubCategoryActivity.this,
                        R.layout.support_simple_spinner_dropdown_item, listcatagory);
                subCategorySpinner.setAdapter(catagoryAdapter);
                subCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("TAG", "hy " + position + "  " + id);

                        String name = parent.getItemAtPosition(position).toString();
                        Integer l = parent.getSelectedItemPosition();

                        CategoryId = dbHelper.SearchCategoryTitle(name);
                        Log.d("TAG ", "Product Name + ID " + name + "  " + CategoryId);
                        Log.d("TAG ", "Product Name + ID " + name + "  " + CategoryId);
                        Toast.makeText(SubCategoryActivity.this, "  " + name + "  " + CategoryId, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SubCategoryUUID == null) {
                            SubCategoryUUID = UUID.randomUUID().toString();
                        }
                        if ((etTitel.getText().toString().isEmpty()) && (etDescription.getText().toString().isEmpty())) {
                            etTitel.setError("Field should not empty");
                        } else {
                            DBHelperClass dbHelper = DBHelperClass.getInstance(SubCategoryActivity.this);
                            Log.d("ddd", "AddClick");
                            if (dbHelper.AddProductSubCategory(getData())) {
                                Toast.makeText(SubCategoryActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                                SubCategoryActivity.this.recreate();
                                dialog.dismiss();
                            }
                        }
                    }

                    public SubCategoriesModel getData() {
                        SubCategoriesModel categoriesModel = new SubCategoriesModel();
                        categoriesModel.setProduct_Category_Title(etTitel.getText().toString());
                        categoriesModel.setProduct_Category_Description(etDescription.getText().toString());
                        categoriesModel.setProduct_Category_Uu_Id(SubCategoryUUID);
                        categoriesModel.setCategory_ID(CategoryId);
                        categoriesModel.setSubCategory_Status(1);
                        Log.d("sss", "Category id insert" + CategoryId);
                        return categoriesModel;
                    }
                });
                */


/*

  public void ProductTypeSpinner() {
        dbHelper = DBHelperClass.getInstance(AddSubCategoryActivity.this);
        productTypeModelList = dbHelper.getAllProductTypes();
        ArrayList<String> listProductType = new ArrayList<>();
        for (int i = 0; i < productTypeModelList.size(); i++) {
            listProductType.add(productTypeModelList.get(i).getProduct_type_title());
        }
        ArrayAdapter<String> productTypeAdapter = new ArrayAdapter<>(AddSubCategoryActivity.this, R.layout.support_simple_spinner_dropdown_item,
                listProductType);
        productTypeSpinner.setAdapter(productTypeAdapter);
        productTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                productTypeIDgetFromSpinner = productTypeModelList.get(position).getProduct_type_ID();
                Log.d(" ccc ", "Product Type ID 1 " + productTypeIDgetFromSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        a = productTypeIDgetFromSpinner;
        Log.d(" ccc ", "Product Type ID a1 " + a);

    }

 */