package com.example.skripsi.Model.LoginModel;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("nip")
	private String nip;

	@SerializedName("stts")
	private String stts;

	@SerializedName("id")
	private String id;

	@SerializedName("Name")
	private String name;

	public void setNip(String nip){
		this.nip = nip;
	}

	public String getNip(){
		return nip;
	}

	public void setStts(String stts){
		this.stts = stts;
	}

	public String getStts(){
		return stts;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}