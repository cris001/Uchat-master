package com.netease.nim.demo.contact;

import android.content.Context;

import com.netease.nim.demo.contact.activity.UserProfileActivity;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.contact.ContactEventListener;

/**
 * UIKit联系人列表定制展示类
 * <p/>
 * Created by AllStar on 2017/3/8.
 */
public class ContactHelper {

    public static void init() {
        setContactEventListener();
    }

    private static void setContactEventListener() {
        NimUIKit.setContactEventListener(new ContactEventListener() {
            @Override
            public void onItemClick(Context context, String account) {
                UserProfileActivity.start(context, account);
            }

            @Override
            public void onItemLongClick(Context context, String account) {

            }

            @Override
            public void onAvatarClick(Context context, String account) {
                UserProfileActivity.start(context, account);
            }
        });
    }

}
