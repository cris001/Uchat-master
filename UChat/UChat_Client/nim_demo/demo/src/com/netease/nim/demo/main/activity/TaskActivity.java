package com.netease.nim.demo.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.netease.nim.demo.R;

/**
 * Created by starwill on 2017/3/6.
 */

public class TaskActivity extends AppCompatActivity {
    private WebView webView;
    private String url;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.actionbar_dark_logo_icon);
        toolbar.setTitle("教务处");
        setSupportActionBar(toolbar);
        url="http://jwc.seu.edu.cn";
        webView=(WebView)findViewById(R.id.custom_webview);
        webView.loadUrl(url);
    }
}
