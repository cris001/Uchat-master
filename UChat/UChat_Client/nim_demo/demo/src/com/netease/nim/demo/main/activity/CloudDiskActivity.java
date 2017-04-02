package com.netease.nim.demo.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.nim.demo.DemoCache;
import com.netease.nim.demo.R;
import com.netease.nim.demo.session.SessionHelper;

/**
 * Created by starwill on 2017/3/6.
 */

public class CloudDiskActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionHelper.startP2PSession(this, DemoCache.getAccount());
//        setContentView(R.layout.clouddisk_activity);
    }
}
