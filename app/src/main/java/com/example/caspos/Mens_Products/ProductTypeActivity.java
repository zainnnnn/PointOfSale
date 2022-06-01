package com.example.caspos.Mens_Products;

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
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.List;

public class ProductTypeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ProductTypeModel> productTypeModelList;
    DBHelperClass dbHelper;
    Context context;
    ProductTypeAdapter adapter;

    public static String name1, price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rcv_product_Type);

        show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(ProductTypeActivity.this, AddProductActivity.class));
            }
        });
    }

    public void show() {

        dbHelper = DBHelperClass.getInstance(context);

        productTypeModelList = dbHelper.getAllProductTypes();
        adapter = new ProductTypeAdapter(this, productTypeModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        adapter.setOnItemClickListener(new ProductTypeAdapter.OnItemClickListener() {
            @Override
            public void OnItem(int position) {
                String p = productTypeModelList.get(position).getProduct_type_ID();
                Log.d("Tag", " variable P" + p);
                Intent intent = new Intent(ProductTypeActivity.this, CategorieslistActivity.class);
                intent.putExtra("productTypeID", p);
                //  startActivity(intent);
                name1 = productTypeModelList.get(position).getProduct_type_title();
                price1 = productTypeModelList.get(position).getProduct_type_description();
                AddToCart addToCart = new AddToCart(ProductTypeActivity.this, name1, price1);
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
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProductTypeActivity.this, DashBoardActivity.class));
    }
}
