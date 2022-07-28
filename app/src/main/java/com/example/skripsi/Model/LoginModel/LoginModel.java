package com.example.skripsi.Model.LoginModel;

import com.google.gson.annotations.SerializedName;

public class LoginModel{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private LoginData loginData;

	@SerializedName("status")
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(LoginData loginData){
		this.loginData = loginData;
	}

	public LoginData getData(){
		return loginData;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}