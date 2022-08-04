package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.skripsi.API.APIRequestData;
import com.example.skripsi.API.RetroServer;
import com.example.skripsi.Activity.Admin.AdminActivity;
import com.example.skripsi.Activity.Koor.KoorKurirActivity;
import com.example.skripsi.Activity.Kurir.PengirimanKurirActivity;
import com.example.skripsi.Activity.SessionManagerLogin;
import com.example.skripsi.Model.LoginModel.LoginData;
import com.example.skripsi.Model.LoginModel.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    private SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//            }
//        }, 3000L); //3000L = 3detik

        SP = getSharedPreferences("user", Context.MODE_PRIVATE);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(500);
                    getStatusLogin(SP.getString("id_user", ""));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();
    }

    private void getStatusLogin(String id) {
        if (!id.equals("")) {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<LoginModel> loginUser = ardData.ardStatus(id);
            loginUser.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    if(response.isSuccessful()){
                        if (SP.getString("status", "").equals("admin")) {
                            Intent intent = new Intent(SplashScreenActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (SP.getString("status", "").equals("koorkur")) {
                            Intent intent = new Intent(SplashScreenActivity.this, KoorKurirActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
//                            SP.getString("nip", "");
                            Intent intent = new Intent(SplashScreenActivity.this, PengirimanKurirActivity.class);
                            intent.putExtra("nip", SP.getString("nip", ""));
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}