package com.example.caspos.company;

import android.content.Intent;
import android.os.Bundle;

import com.example.caspos.DBHelperClass;
import com.example.caspos.DashBoardActivity;
import com.example.caspos.R;
import com.example.caspos.varient.VarientActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.List;

public class CompanyActivity extends AppCompatActivity {

    RecyclerView rcv_company;
    DBHelperClass dbHelperClass;
    List<CompanyModel> companyModelList;
    CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcv_company = findViewById(R.id.rcv_CompanyRecyclerView);


        dbHelperClass = DBHelperClass.getInstance(CompanyActivity.this);

        show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(CompanyActivity.this, AddCompanyActivity.class));
                finish();
            }
        });
    }

    public void show() {
        companyModelList = dbHelperClass.getAllCompanyRecords();
        companyAdapter = new CompanyAdapter(CompanyActivity.this, companyModelList);
        rcv_company.setLayoutManager(new GridLayoutManager(this,2));
        rcv_company.setAdapter(companyAdapter);
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
                companyAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CompanyActivity.this, DashBoardActivity.class));
    }
}
