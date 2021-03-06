package com.xybcoder.baseactivity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * Created by dell on 2016/5/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        //log当前操作的activity
        Log.i("====CurrentActivity====", TAG);
        initView();
        initData();
    }

    /**
     * 抽象方法，获取布局资源
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化UI
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    private void initData() {

    }


    /**
     * 跳转activity
     */
    protected void goToActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    protected void goToActivityForResult(Context context,Class<?> cls, Bundle bundle,int requestCode){
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Log.i(TAG, "onDestroy");

    }
}
