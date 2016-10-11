package com.test.tworldapplication.activity.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.tworldapplication.R;
import com.test.tworldapplication.entity.Home;
import com.test.tworldapplication.view.ADView;
import com.test.tworldapplication.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeMainActivity extends AppCompatActivity {

    @Bind(R.id.adview)
    ADView adview;
    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    private List<Integer> list;
    private List<Home> homeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ButterKnife.bind(this);
        init();

    }


    private void init() {
        idRecyclerview.setLayoutManager(new GridLayoutManager(this,4));
        idRecyclerview.setItemAnimator(new DefaultItemAnimator());
        idRecyclerview.addItemDecoration(new DividerGridItemDecoration(this));
//        idRecyclerview.setAdapter(mAdapter = new HomeAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        adview.setList(list);
        adview.init();
        homeList = new ArrayList<>();
        homeList.add(new Home(R.drawable.hfcz,"话费充值"));
        homeList.add(new Home(R.drawable.yecz,"余额充值"));
        homeList.add(new Home(R.drawable.yecx,"余额查询"));
        homeList.add(new Home(R.drawable.ckkh,"成卡开户"));
        homeList.add(new Home(R.drawable.bkkh,"白卡开户"));
        homeList.add(new Home(R.drawable.bk,"补卡"));

    }
}
