package com.xybcoder.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

   @OnClick(R.id.but)
    public void but(View view){
        startActivity(new Intent(this,FirstActivity.class));
    }

}
