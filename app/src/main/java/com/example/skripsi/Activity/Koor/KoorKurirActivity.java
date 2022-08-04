package com.example.skripsi.Activity.Koor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skripsi.Activity.Admin.AdminActivity;
import com.example.skripsi.Activity.Koor.DaftarKurir.DataKurirActivity;
import com.example.skripsi.Activity.Koor.RutePengiriman.CekHitungActivity;
import com.example.skripsi.Activity.Koor.AturPengiriman.DataPengirimanActivity;
import com.example.skripsi.Activity.SessionManagerLogin;
import com.example.skripsi.LoginActivity;
import com.example.skripsi.R;

public class KoorKurirActivity extends AppCompatActivity {

    TextView tvNamaKurir, tvNipKurir;
    CardView cardAturPengiriman, cardDaftarKurir, cardRutePengiriman;
    SessionManagerLogin sessionManager;
    String namaKurir, nipKurir;
    private ImageView imageUser;
    private SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_kurir);

        sessionManager = new SessionManagerLogin(KoorKurirActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        cardAturPengiriman = findViewById(R.id.cardAturPengiriman);
        cardDaftarKurir = findViewById(R.id.cardDaftarKurir);
        cardRutePengiriman = findViewById(R.id.cardRutePengiriman);
        tvNamaKurir = findViewById(R.id.namaKurir);
        tvNipKurir = findViewById(R.id.nipKurir);

        namaKurir = sessionManager.getUserDetail().get(SessionManagerLogin.NAME);
        nipKurir = sessionManager.getUserDetail().get(SessionManagerLogin.NIP);

        tvNamaKurir.setText(namaKurir);
        tvNipKurir.setText("Nip. "+nipKurir);

        cardAturPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KoorKurirActivity.this, DataPengirimanActivity.class));
            }
        });

        cardDaftarKurir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KoorKurirActivity.this, DataKurirActivity.class));
            }
        });

        cardRutePengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KoorKurirActivity.this, CekHitungActivity.class));
            }
        });

        imageUser = findViewById(R.id.imageUser);
        SP = getSharedPreferences("user", Context.MODE_PRIVATE);
        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogout();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(KoorKurirActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void doLogout() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Apakah anda ingin keluar akun ?");
        dialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences.Editor editor = SP.edit();
                editor.putString("id_user", "");
                editor.putString("status", "");
                editor.apply();

                Intent intent = new Intent(KoorKurirActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}