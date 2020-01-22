package com.mcal.decode.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.mcal.decode.App;

public final class Preferences {
    private static final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());

    public static boolean isNightModeEnabled() {
        return preferences.getBoolean("night_mode", false);
    }

    public static void setNightModeEnabled(boolean flag) {
        preferences.edit().putBoolean("night_mode", flag).apply();
    }
}
