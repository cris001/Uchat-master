package com.netease.nim.demo.main.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.demo.DemoCache;
import com.netease.nim.demo.GetUserInfo;
import com.netease.nim.demo.R;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.model.ToolBarOptions;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LimitSettingActivity extends UI {
    TextView textView;
    EditText editText;
    Button button;
    String limit;
    String identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.limit_setting_activity);
        ToolBarOptions toolBarOptions=new ToolBarOptions();
        toolBarOptions.titleId=R.string.identy_verify;
        setToolBar(R.id.toolbar,toolBarOptions);

        textView=(TextView)findViewById(R.id.identity_text);
        textView.setText(DemoCache.getPermission());
        editText=(EditText)findViewById(R.id.identity_edit);
        button=(Button)findViewById(R.id.identity_verify);


        //验证身份
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identity=editText.getText().toString();
                Log.i("----------","-----verify test-----");
                Log.i("------------",identity.length()+"");
                if(identity.length()<18||identity.length()>18){
                    Toast.makeText(LimitSettingActivity.this,"身份证号格式错误 ! 请重新输入",Toast.LENGTH_SHORT).show();

                }
                else{


                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                HashMap<String,String> map=new HashMap<>();
                                map.put("u_account", DemoCache.getAccount());
                                map.put("u_identification",identity);

                                //获取服务器返还的身份信息
                                limit=new GetUserInfo().GetLimitFromLocalServer(map);
                                Log.i("---------",limit);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }.start();


                }
            }
        });

        textView.setText(limit);
        DemoCache.setPermission(textView.getText().toString());
    }


}
