package com.netease.nim.uikit.session.module;

import android.app.Activity;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/**
 * Created by AllStar on 2017/3/18.
 */
public class Container {
    public final Activity activity;
    public final String account;
    public final SessionTypeEnum sessionType;
    public final ModuleProxy proxy;

    public Container(Activity activity, String account, SessionTypeEnum sessionType, ModuleProxy proxy) {
        this.activity = activity;
        this.account = account;
        this.sessionType = sessionType;
        this.proxy = proxy;
    }
}
