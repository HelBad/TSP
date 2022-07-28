package com.example.skripsi.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.skripsi.Model.LoginModel.LoginData;

import java.util.HashMap;

public class SessionManagerLogin {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID = "id";
    public static final String NAME = "Name";
    public static final String STTS = "stts";
    public static final String NIP = "nip";

    public SessionManagerLogin (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID, user.getId());
        editor.putString(NAME, user.getName());
        editor.putString(STTS, user.getStts());
        editor.putString(NIP, user.getNip());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(STTS, sharedPreferences.getString(STTS, null));
        user.put(NIP, sharedPreferences.getString(NIP, null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
