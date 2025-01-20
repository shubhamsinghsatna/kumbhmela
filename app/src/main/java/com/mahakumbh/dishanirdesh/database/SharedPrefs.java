package com.mahakumbh.dishanirdesh.database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    public static String sharedPrefName = "TFP_Mahakumbh_2025";
    Context context;

    public SharedPrefs(Context context2) {
        this.context = context2;
    }

    public void clearSharedData() {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    public boolean getLogin() {
        return this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).getBoolean("isLoggedIn", false);
    }

    public void setLogin(Boolean login) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).edit();
        editor.putBoolean("isLoggedIn", login.booleanValue());
        editor.apply();
    }

    public boolean getLangsetup() {
        return this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).getBoolean("Langsetup", false);
    }

    public void setLangsetup(Boolean login) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).edit();
        editor.putBoolean("Langsetup", login.booleanValue());
        editor.apply();
    }

    public String getAppLanguage() {
        return this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).getString("SelectedLanguage", "en");
    }

    public void setAppLanguage(String language) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).edit();
        editor.putString("SelectedLanguage", language);
        editor.apply();
    }

}
