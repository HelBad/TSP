package com.example.skripsi.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skripsi.Activity.Koor.AturPengiriman.DataPengirimanActivity;
import com.example.skripsi.Activity.Koor.DaftarKurir.DataKurirActivity;
import com.example.skripsi.Activity.SessionManagerLogin;
import com.example.skripsi.LoginActivity;
import com.example.skripsi.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{

    SessionManagerLogin sessionManager;
    TextView tvNama, tvId;
    String nama, id;
    ImageButton imgUser;
    private CardView dtKurir, dtKirim;
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
}