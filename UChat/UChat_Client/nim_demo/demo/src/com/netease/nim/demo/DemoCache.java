package com.netease.nim.demo;

import android.content.Context;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/**
 * Created by AllStar on 2017/3/20.
 */
public class DemoCache {

    private static Context context;

    private static String account;

    private static String permission;

    private static int holidayAllow;

    private static String classId;

    private static int countTime;

    private static StatusBarNotificationConfig notificationConfig;

    public static void clear() {
        account = null;
    }

    public static String getClassId() {
        return classId;
    }

    public static void setClassId(String classId) {
        DemoCache.classId = classId;
    }

    public static int getCountTime() {
        return countTime;
    }

    public static void setCountTime(int countTime) {
        DemoCache.countTime = countTime;
    }

    public static int getHolidayAllow() {
        return holidayAllow;
    }

    public static void setHolidayAllow(int holidayAllow) {
        DemoCache.holidayAllow = holidayAllow;
    }

    public static String getPermission(){
        return permission;
    }
    public static void setPermission(String limit){
        DemoCache.permission=limit;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        DemoCache.account = account;
        NimUIKit.setAccount(account);
    }

    public static void setNotificationConfig(StatusBarNotificationConfig notificationConfig) {
        DemoCache.notificationConfig = notificationConfig;
    }

    public static StatusBarNotificationConfig getNotificationConfig() {
        return notificationConfig;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();
    }
}
