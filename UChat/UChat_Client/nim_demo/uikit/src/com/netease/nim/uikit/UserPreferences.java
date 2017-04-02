package com.netease.nim.uikit;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AllStar on 2017/3/3.
 */
public class UserPreferences {

    private static int holidayAllow;

    private final static String KEY_EARPHONE_MODE = "KEY_EARPHONE_MODE";

    public static void setEarPhoneModeEnable(boolean on) {
        saveBoolean(KEY_EARPHONE_MODE, on);
    }

    public static boolean isEarPhoneModeEnable() {
        return getBoolean(KEY_EARPHONE_MODE, true);
    }

    private static boolean getBoolean(String key, boolean value) {
        return getSharedPreferences().getBoolean(key, value);
    }

    private static void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    static SharedPreferences getSharedPreferences() {
        return NimUIKit.getContext().getSharedPreferences("UIKit." + NimUIKit.getAccount(), Context.MODE_PRIVATE);
    }

    public static int getHolidayAllow() {
        return holidayAllow;
    }

    public static void setHolidayAllow(int holidayAllow) {
        UserPreferences.holidayAllow = holidayAllow;
    }
}
