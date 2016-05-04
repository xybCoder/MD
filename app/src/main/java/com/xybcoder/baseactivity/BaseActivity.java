package com.xybcoder.baseactivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * Created by dell on 2016/5/2.
 */
public abstract class BaseActivity extends AppCompatActivity{
    protected String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        //log当前操作的activity
        Log.i("====CurrentActivity====",TAG);
        initView();
    }

    /**
     * 抽象方法，获取布局资源
     *
     * @return
     */
    protected abstract int getLayoutResId();
    /**
     * 初始化
     */
    protected void initView(){}

    /**
     * 跳转activity
     */
    protected  void goToActivity(Context context,Class<?>cls){
       startActivity(new Intent(context,cls));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Log.i(TAG,"onDestroy");

    }
}
