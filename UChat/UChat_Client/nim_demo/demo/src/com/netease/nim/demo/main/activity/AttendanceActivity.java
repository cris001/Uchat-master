package com.netease.nim.demo.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.netease.nim.demo.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * Created by starwill on 2017/3/6.
 */

public class AttendanceActivity extends AppCompatActivity {

    private TextView saomajieguo;  //扫码结果
    private EditText dingwei;  //定位结果
    private ImageView mIvResult;  //图片
    private TextView state;   //签到状态
    private TextView tv_xianshi;
    private ProgressBar pro_bar;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();  //生成二维码定位
    public AMapLocationListener mLocationListener1 = new MyAMapLocationListener1();  //扫描二维码定位
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.actionbar_dark_logo_icon);
        toolbar.setTitle("考勤");
        setSupportActionBar(toolbar);
        tv_xianshi= (TextView) findViewById(R.id.textView_tishi1);
        saomajieguo = (TextView) findViewById(R.id.saomajieguo);
        saomajieguo.setVisibility(View.GONE);
        dingwei = (EditText) findViewById(R.id.dingweijieguo);
        mIvResult = (ImageView) findViewById(R.id.imageView_logo);
        mIvResult.setVisibility(View.GONE);
        pro_bar= (ProgressBar) findViewById(R.id.progressBar_wait);
        pro_bar.setVisibility(View.GONE);
        state= (TextView) findViewById(R.id.jieguo);
        state.setVisibility(View.GONE);
        init();
    }

    /**
     * 点击事件
     */
    public void onclick(View v){
        switch (v.getId()){
            case R.id.button_cxsc:   //重新生成二维码
                Again();
                break;
            case R.id.button_smqd:   //扫码签到
                Scan();
                break;
            case R.id.button_qdjl:   //查看签到记录
                Intent intent = new Intent(AttendanceActivity.this,RecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 扫描二维码
     */
    public void Scan(){
        if(pro_bar.getVisibility()==View.GONE){
            pro_bar.setVisibility(View.VISIBLE);
        }else {
            pro_bar.setVisibility(View.GONE);
        }
        init1();
    }

    //扫描二维码时候调用
    private void init1() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener1);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //扫描二维码时候调用
    private class MyAMapLocationListener1 implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                Log.e("位置：", aMapLocation.getAddress());
                state.setText(aMapLocation.getAddress()+"");
                pro_bar.setVisibility(View.GONE);
                startActivityForResult(new Intent(AttendanceActivity.this, CaptureActivity.class), 0);
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    //得到扫码结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            saomajieguo.setText(result);
            if(state.getText().toString().equals(saomajieguo.getText().toString())){
                AlertDialog.Builder dialog_success = new AlertDialog.Builder(AttendanceActivity.this);
                dialog_success.setTitle("签到结果");
                dialog_success.setMessage("签到成功！");
                dialog_success.setCancelable(false);
                dialog_success.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog_success.show();
            } else{
                AlertDialog.Builder dialog_fail = new AlertDialog.Builder(AttendanceActivity.this);
                dialog_fail.setTitle("签到结果");
                dialog_fail.setMessage("签到失败，位置不匹配，请重新扫码签到");
                dialog_fail.setCancelable(false);
                dialog_fail.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog_fail.show();
            }
        }
    }

    /**
     * 重新生成二维码
     */
    public void Again(){
        dingwei.setText(null);
        tv_xianshi.setText("正在定位，请稍等...");
        mIvResult.setVisibility(View.GONE);
        init();
        String text = dingwei.getText().toString();
        if(text.equals("")){
            Toast.makeText(AttendanceActivity.this, "正在定位，请稍等...", Toast.LENGTH_LONG).show();
        } else {
//            mIvResult.setVisibility(View.VISIBLE);
//            Bitmap bitmap = EncodingUtils.createQRCode(dingwei.getText().toString(), 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.logo));
//            mIvResult.setImageBitmap(bitmap);
        }
    }

    //生成二维码时候调用
    private void init() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //生成二维码时候调用
    private class MyAMapLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                Log.e("位置：", aMapLocation.getAddress());
                dingwei.setText(aMapLocation.getAddress()+"");
                dingwei.setSelection(dingwei.getText().length());
                tv_xianshi.setText("您的位置：");
                mIvResult.setVisibility(View.VISIBLE);
                Bitmap bitmap = EncodingUtils.createQRCode(dingwei.getText().toString(), 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.logo));
                mIvResult.setImageBitmap(bitmap);
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
}
