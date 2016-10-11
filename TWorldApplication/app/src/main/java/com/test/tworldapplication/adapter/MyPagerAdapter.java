package com.test.tworldapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 27733 on 2016/9/19.
 */
public class MyPagerAdapter extends PagerAdapter {

    Context context;
    private List<Integer> list;
//    List<ItemBean> itemList;
    public MyPagerAdapter(Context context, List<Integer> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position%list.size();
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(list.get(position));
       /* 动态创建imageview 添加到container*/
        container.addView(imageView);
        return imageView;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position%list.size();
        container.removeView((View)object);
    }
}
