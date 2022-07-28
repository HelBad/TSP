package com.example.skripsi.Activity.Koor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    private void moveToLogin() {
        Intent intent = new Intent(KoorKurirActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}