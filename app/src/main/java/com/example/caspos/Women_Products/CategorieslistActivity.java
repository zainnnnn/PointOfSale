package com.example.caspos.Women_Products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.caspos.AddToCart;
import com.example.caspos.DBHelperClass;
import com.example.caspos.DashBoardActivity;
import com.example.caspos.R;
import com.example.caspos.Mens_Products.ProductTypeActivity;
import com.example.caspos.Kids_Products.SubCategoryActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class CategorieslistActivity extends AppCompatActivity {

    List<CategoriesModel> modelList;
    DBHelperClass dbHelper;
    RecyclerView recyclerView;
    Context context;
    CategoriesAdapter categoriesAdapter;
    String productTypeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorieslist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rc);
        productTypeID = getIntent().getStringExtra("productTypeID");
        Log.d("Tag", "pro iD" + productTypeID);
        SHow();

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategorieslistActivity.this, AddCatagoriesActivity.class);
                intent.putExtra("ProductTypeID", productTypeID);
                startActivity(intent);
//finish();
            }
        });
    }

    public void SHow() {
        dbHelper = DBHelperClass.getInstance(CategorieslistActivity.this);
        Log.d("ddd", "pro iD" + productTypeID);

        if (productTypeID == null) {
            modelList = dbHelper.getAllCatagories();
            categoriesAdapter = new CategoriesAdapter(context, modelList);
        } else {
            modelList = dbHelper.getSingleCatagories(productTypeID);
            categoriesAdapter = new CategoriesAdapter(context, modelList);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(categoriesAdapter);
        recyclerView.setHasFixedSize(true);

        categoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void OnItem(int position) {
                String CategoryPrice = modelList.get(position).getProduct_Category_Description();
                String catageoryName = modelList.get(position).getProduct_Category_Title();

                AddToCart addToCart = new AddToCart(CategorieslistActivity.this,catageoryName,CategoryPrice);
                addToCart.show();
            }
        });
        //   Log.d("eee", "Catagory ID for sub catagory" + CategoryID);
        //   Log.d("eee", "Catagory Title for sub catagory" + catageoryName);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CategorieslistActivity.this, DashBoardActivity.class));
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
                categoriesAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
