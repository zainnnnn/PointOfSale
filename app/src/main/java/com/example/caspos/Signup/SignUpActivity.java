package com.example.caspos.Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspos.DBHelperClass;
import com.example.caspos.DashBoardActivity;
import com.example.caspos.Login.LoginActivity;
import com.example.caspos.R;

public class SignUpActivity extends AppCompatActivity {

    EditText et_name,et_password,et_phone,et_email;
    Button btn_signup,btnLogin;
    DBHelperClass dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et_name=findViewById(R.id.et_Signup_name);
        et_password=findViewById(R.id.et_signup_Password);
        et_email=findViewById(R.id.et_signup_email);
        et_phone=findViewById(R.id.et_signup_Phone);
        btn_signup=findViewById(R.id.btn_for_signup);
        btnLogin=findViewById(R.id.btn_login_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().isEmpty()){
                    et_name.setError("Enter Email");
                }
                else if (et_email.getText().toString().isEmpty()){
                    et_email.setError("Enter Name");
                }
                else if (et_password.getText().toString().isEmpty()){
                    et_password.setError("Enter Password");
                }
                else if (et_phone.getText().toString().isEmpty()){
                    et_phone.setError("Enter Pfone");
                }
                else {

                    dbHelper=DBHelperClass.getInstance(SignUpActivity.this);
                    if (dbHelper.CreateAcoount(getData())){
                        Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, DashBoardActivity.class));
                    }else {
                        Toast.makeText(SignUpActivity.this, "Already have a account", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            public SignUpModel getData(){
                SignUpModel signUpModel=new SignUpModel();
                signUpModel.setEmail(et_email.getText().toString());
                signUpModel.setName(et_name.getText().toString());
                signUpModel.setPassword(et_password.getText().toString());
                signUpModel.setPhoneNo(et_phone.getText().toString());

                return signUpModel;
            }
        });
    }
}
