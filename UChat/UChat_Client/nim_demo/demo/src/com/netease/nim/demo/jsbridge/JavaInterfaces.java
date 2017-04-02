package com.netease.nim.demo.jsbridge;


import android.widget.Toast;

import com.netease.nimlib.jsbridge.annotation.JavaInterface;
import com.netease.nimlib.jsbridge.annotation.Param;
import com.netease.nimlib.jsbridge.annotation.ParamCallback;
import com.netease.nimlib.jsbridge.interfaces.IJavaReplyToJsImageInfo;

/**
 * Created by AllStar on 2017/3/8.
 */
public class JavaInterfaces {

    private JsBridgeActivity context;

    public JavaInterfaces(JsBridgeActivity context) {
        this.context = context;
    }

    @JavaInterface("notification")
    public void notification(@Param("msg") String msg) {
        // notification
        NotificationHelper helper = new NotificationHelper(context);
        helper.activeCallingNotification(true, msg);

        // toast
        Toast.makeText(context.getApplicationContext(), "发送成功", Toast.LENGTH_LONG).show();
    }

    @JavaInterface("picture")
    public void picture(@ParamCallback IJavaReplyToJsImageInfo jsCallback) {
        context.pickPicture(jsCallback);
    }
}
