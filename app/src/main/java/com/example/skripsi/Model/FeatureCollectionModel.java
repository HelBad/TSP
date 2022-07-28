package com.example.skripsi.Model;

import com.example.skripsi.Model.LoginModel.LoginData;

import java.util.List;

public class FeatureCollectionModel {
    private int kode;
    private String pesan;
    private List<LoginData> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<LoginData> getData() {
        return data;
    }

    public void setData(List<LoginData> data) {
        this.data = data;
    }
}
