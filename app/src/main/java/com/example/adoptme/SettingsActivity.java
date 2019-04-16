package com.example.adoptme;


import android.content.Context;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
;
import android.os.Bundle;


public class SettingsActivity extends PreferenceActivity {
    public final static String BG_COLOR = "cute";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


    }

    public static boolean CHANGE_BC(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BG_COLOR, false);
    }

}