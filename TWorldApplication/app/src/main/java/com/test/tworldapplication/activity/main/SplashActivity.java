package com.test.tworldapplication.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.test.tworldapplication.R;
import com.test.tworldapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.imageview)
    ImageView imageview;
    @Bind(R.id.click)
    Button click;
    Animation animation;
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        animation= AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
//        imageview.startAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                imageview.setVisibility(View.INVISIBLE);
//                gotoActy(MainActivity.class);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                imageview.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                gotoActy(MainActivity.class);
                SplashActivity.this.finish();

//                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
//                Splash.this.startActivity(mainIntent);
//                Splash.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);


    }

    @OnClick(R.id.click)
    public void onClick() {


    }
}
