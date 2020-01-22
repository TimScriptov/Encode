package com.mcal.decode;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import com.mcal.decode.data.Preferences;

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        if (Preferences.isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
