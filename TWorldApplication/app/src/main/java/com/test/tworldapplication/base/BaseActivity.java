package com.test.tworldapplication.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.test.tworldapplication.R;

/**
 * Created by 27733 on 2016/10/11.
 */
public class BaseActivity extends AppCompatActivity {
    private Animation mAnimation;
    private Application myApplication;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myApplication = (MyApplication) this.getApplicationContext();
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_info);


    }

    public void gotoActy(Class clazz) {
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);

    }

    public void setBackGroundTitle(String title) {
        tvTitle = (TextView) findViewById(R.id.textview);
        tvTitle.setText(title);
    }
}
