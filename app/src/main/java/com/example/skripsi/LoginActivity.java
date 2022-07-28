package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skripsi.API.APIRequestData;
import com.example.skripsi.API.RetroServer;
import com.example.skripsi.Activity.Admin.AdminActivity;
import com.example.skripsi.Activity.Koor.KoorKurirActivity;
import com.example.skripsi.Activity.Koor.RutePengiriman.CekHitungActivity;
import com.example.skripsi.Activity.SessionManagerLogin;
import com.example.skripsi.Model.LoginModel.LoginData;
import com.example.skripsi.Model.LoginModel.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    String stts, username, password;
    SessionManagerLogin SMLogin;
    //Integer Id;
//    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

//        SP = getSharedPreferences("skripsi", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                login(username, password);
                break;
        }
    }


    private void login(String username, String password) {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<LoginModel> loginUser = ardData.ardLogin(username, password);

        loginUser.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    SMLogin = new SessionManagerLogin(LoginActivity.this);
                    LoginData loginData = response.body().getData();
                    SMLogin.createLoginSession(loginData);

                    Toast.makeText(LoginActivity.this, response.body().getData().getName(),
                            Toast.LENGTH_SHORT).show();

                    if(loginData.getStts().equals("admin")) {
//                        SharedPreferences.Editor editor = SP.edit();
//                        editor.putString("iduser", loginData.getId());
//                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else if(loginData.getStts().equals("koorkur")) {
                        Intent intent = new Intent(LoginActivity.this, KoorKurirActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, CekHitungActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } /*else if (response.body() != null && response.isSuccessful() && response.body().isStatus() &&
                        response.body().getData().getStts() == "kookurir"){

                    SMLogin = new SessionManagerLogin(LoginActivity.this);
                    LoginData loginData = response.body().getData();
                    SMLogin.createLoginSession(loginData);

                    Toast.makeText(LoginActivity.this, response.body().getData().getName(),
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                } */else {
                    Toast.makeText(LoginActivity.this, response.body().getPesan(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal Menghubungi Server! | "
                        +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}