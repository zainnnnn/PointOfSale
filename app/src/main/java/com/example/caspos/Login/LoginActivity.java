package com.example.caspos.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.DashBoardActivity;
import com.example.caspos.R;
import com.example.caspos.Signup.SignUpActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText et_loginUserName, et_loginUserPassword;
    Button btn_Login, btn_Signup;
    Context context;
    DBHelperClass dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_loginUserName = findViewById(R.id.et_login_user_Name);
        et_loginUserPassword = findViewById(R.id.et_login_user_password);
        btn_Login = findViewById(R.id.btn_for_login);
        btn_Signup = findViewById(R.id.Btn_SignUp);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_loginUserName.getText().toString();
                String password = et_loginUserPassword.getText().toString();
                Log.d("ddd", "Password =" + password);
                dbHelper = DBHelperClass.getInstance(LoginActivity.this);
                String searchpassword = dbHelper.SearchPassword(email);
                Log.d("ddd", "Search =" + searchpassword);
                if (password.equals(searchpassword)) {
                    Toast.makeText(LoginActivity.this, "SuccesfullyLogin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));

                } else {
                    Toast.makeText(LoginActivity.this, "Enter email password/ Mismatch Password", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


    }

}

