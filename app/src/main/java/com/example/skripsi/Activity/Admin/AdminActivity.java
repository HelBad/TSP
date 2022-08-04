package com.example.skripsi.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skripsi.Activity.Koor.AturPengiriman.DataPengirimanActivity;
import com.example.skripsi.Activity.Koor.DaftarKurir.DataKurirActivity;
import com.example.skripsi.Activity.Koor.KoorKurirActivity;
import com.example.skripsi.Activity.Koor.RutePengiriman.TabelHitungActivity;
import com.example.skripsi.Activity.Kurir.PengirimanKurirActivity;
import com.example.skripsi.Activity.SessionManagerLogin;
import com.example.skripsi.LoginActivity;
import com.example.skripsi.R;

import java.io.Serializable;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{

    SessionManagerLogin sessionManager;
    TextView tvNama, tvId;
    String nama, id;
    ImageButton imgUser;
    private CardView dtKurir, dtKirim;
    private ImageView imageUser;
    private SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sessionManager = new SessionManagerLogin(AdminActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        tvNama = findViewById(R.id.namaLengkap);
        tvId = findViewById(R.id.nip);
      //  imgUser = findViewById(R.id.imageUser);

        nama = sessionManager.getUserDetail().get(SessionManagerLogin.NAME);
        //id = sessionManager.getUserDetail().get(SessionManagerLogin.NIP);

        tvNama.setText(nama);
        //tvId.setText("Nip. "+id);

        dtKirim = findViewById(R.id.cardCrudKurir);
        dtKurir = findViewById(R.id.cardCrudPengiriman);

        dtKurir.setOnClickListener(this);
        dtKirim.setOnClickListener(this);

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
        Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardCrudKurir:
                Intent intentKurir = new Intent(this, AdminKurirActivity.class);
                startActivity(intentKurir);
                break;
            case R.id.cardCrudPengiriman:
                Intent intentKirim = new Intent(this, DataPengirimanActivity.class);
                startActivity(intentKirim);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulogout, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
             //   SessionManagerLogin.logoutSession();
                finish();
        }
        return super.onOptionsItemSelected(item);
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

                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
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