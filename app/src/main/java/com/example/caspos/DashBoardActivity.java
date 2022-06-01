package com.example.caspos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.example.caspos.Kids_Products.SubCategoryActivity;
import com.example.caspos.Women_Products.CategorieslistActivity;
import com.example.caspos.company.CompanyActivity;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.caspos.flavour.FlavourActivity;
import com.example.caspos.Mens_Products.ProductTypeActivity;
import com.example.caspos.varient.VarientActivity;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SliderView sliderView;
    CardView products, Flavour, addCompany, addVariant, addSubCategories, addCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        sliderView = findViewById(R.id.imageSlider);
        products = findViewById(R.id.cardMensPrducts);
        Flavour = findViewById(R.id.cardBill);
        addCompany = findViewById(R.id.cardNewCompany);
        addVariant = findViewById(R.id.cardAddVariant);
        addCategories = findViewById(R.id.cardWomen_Product);
        addSubCategories = findViewById(R.id.cardKids_Products);

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Toast.makeText(DashBoardActivity.this, "Home Product", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoardActivity.this, ProductTypeActivity.class));
            }
        });

        Flavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(DashBoardActivity.this, "Home Catagories", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoardActivity.this, Bill_Activity.class));
            }
        });

        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(DashBoardActivity.this, "A", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoardActivity.this, CompanyActivity.class));
            }
        });

        addVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(DashBoardActivity.this, "Add Catagories", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashBoardActivity.this, VarientActivity.class));
            }
        });
        addCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this, CategorieslistActivity.class));
            }
        });
        addSubCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this, SubCategoryActivity.class));
            }
        });

        SliderAdapter adapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gallery) {

            Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {

            Toast.makeText(this, "Slide Show", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tools) {

            Toast.makeText(this, "Tools", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {

            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {

            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

