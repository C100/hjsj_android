package com.test.tworldapplication.activity.main;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.tworldapplication.R;
import com.test.tworldapplication.activity.account.AccountMainActivity;
import com.test.tworldapplication.activity.card.CardMainActivity;
import com.test.tworldapplication.activity.home.HomeMainActivity;
import com.test.tworldapplication.activity.order.OrderMainActivity;
import com.test.tworldapplication.adapter.ViewpagerAdapter;
import com.test.tworldapplication.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  implements ViewPager.OnPageChangeListener{

    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.llMain)
    LinearLayout llMain;
    ViewpagerAdapter adapter;
    private List<View> list = new ArrayList<View>();
    LocalActivityManager manager;
    String[] mlist = {"首页","订单管理","账户管理","卡片管理"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackGroundTitle("首页");
        manager = new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        initPagerView();
        initText();
    }
    private void initText() {
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    text = "首页";
                    break;
                case 1:
                    text = "订单管理";
                    break;
                case 2:
                    text = "账户管理";
                    break;
                case 3:
                    text = "卡片管理";
                    break;
            }
            final TextView textview = new TextView(this);
            textview.setId(i);
            textview.setText(text);
            if (i==0){
                textview.setTextColor(getResources().getColor(R.color.colorAccent));
            }else{
                textview.setTextColor(getResources().getColor(R.color.colorBlack));
            }
            textview.setGravity(Gravity.CENTER);
            textview.setWidth(getWindowManager().getDefaultDisplay().getWidth() / 4);
            llMain.addView(textview);
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vp.setCurrentItem(textview.getId());
                    setBackGroundTitle(mlist[textview.getId()]);
                    textview.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            });
        }
    }

    private void initPagerView() {
        Intent intent0 = new Intent(MainActivity.this,HomeMainActivity.class);
        View v0 = getView(mlist[0],intent0);
        list.add(v0);
        Intent intent1 = new Intent(MainActivity.this,OrderMainActivity.class);
        View v1 = getView(mlist[1],intent1);
        list.add(v1);
        Intent intent2 = new Intent(MainActivity.this,AccountMainActivity.class);
        View v2 = getView(mlist[2],intent2);
        list.add(v2);
        Intent intent3 = new Intent(MainActivity.this,CardMainActivity.class);
        View v3 = getView(mlist[3],intent3);
        list.add(v3);
        adapter = new ViewpagerAdapter(this, list);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
    }
    private View getView(String id,Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBackGroundTitle(mlist[position]);
        for (int i = 0 ; i <list.size();i++){
            TextView t = (TextView)llMain.getChildAt(i);
            if (i==position){
                t.setTextColor(Color.RED);
            }else{
                t.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
