package com.test.tworldapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test.tworldapplication.R;
import com.test.tworldapplication.adapter.MyPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 27733 on 2016/10/11.
 */
public class ADView extends RelativeLayout  implements ViewPager.OnPageChangeListener,ViewTreeObserver.OnGlobalLayoutListener {
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @Bind(R.id.view_red_point)
    View viewRedPoint;
    @Bind(R.id.rell_point)
    RelativeLayout rellPoint;
    private Context context;
    private RelativeLayout mRelativeLayout;
    private List<Integer> list;

    public static final int MARGIN_BETWEEN_POINTS = 30;
    MyPagerAdapter mAdapter;
    /*小红点移动的距离*/
    private int mPointWidth;
//    List<ItemBean> itemList;
//    Handler handler;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

//    @Override
//    public Handler getHandler() {
//        return handler;
//    }
//
//    public void setHandler(Handler handler) {
//        this.handler = handler;
//    }

    public ViewPager getVp() {
        return vp;
    }

    public void setVp(ViewPager vp) {
        this.vp = vp;
    }

    public ADView(Context context) {
        super(context);
    }

    public ADView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        mRelativeLayout = (RelativeLayout) mInflater.inflate(R.layout.layout_adview, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(mRelativeLayout, params);
        ButterKnife.bind(this);
//        init();
        context = context;

    }

    public void init() {
        viewRedPoint.setLayoutParams(new LayoutParams(20,20));

        for (int i = 0;i<list.size();i++){
             /*给每一个point添加布局，包括左右边距*/
            View point = new View(context);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            /*params(20,20)定义圆点的大小*/
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            /*圆点的左右边距*/
            params.leftMargin = MARGIN_BETWEEN_POINTS;
            params.rightMargin = MARGIN_BETWEEN_POINTS;
            point.setLayoutParams(params);
            /*把每一个point添加进线性布局*/
            llPointGroup.addView(point);
        }
        mAdapter = new MyPagerAdapter(context,list);
        vp.setAdapter(mAdapter);
        /*viewpager左右滑动*/
        vp.addOnPageChangeListener(this);
        /*设置viewpager定位到某个页面*/
        vp.setCurrentItem(0);

//        handler.sendEmptyMessageDelayed(0, 3000);//3秒后发送空消息
        /*view的组件布局要在onResume回调后完成，所以在onCreat中无法getWidth，getHeigt，该方法用来获得宽度和高度*/
        rellPoint.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

       /*滑动过程中一直调用此方法 positionOffset 偏 移的百分比*/
        position = position%list.size();

        int leftMargin;
        if (position == list.size()-1){
            leftMargin = position*mPointWidth+MARGIN_BETWEEN_POINTS;
        }else{
            leftMargin = (int)(mPointWidth*positionOffset+position*mPointWidth+MARGIN_BETWEEN_POINTS);
        }
        LayoutParams params = (LayoutParams)viewRedPoint.getLayoutParams();
        params.leftMargin = leftMargin;
        viewRedPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        position = position%list.size();
//        image_desc.setText(itemList.get(position).getTitle());
//        if (position == itemList.size()-1){
//            btn_start.setVisibility(View.VISIBLE);
//        }else{
//            btn_start.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {

        /*将添加的OnGlobalLayoutListener   remove掉*/
        rellPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);//removeGlobalOnLayoutListener(this);废弃了
        //获取小圆点间的距离，即小红点每次移动的距离
        mPointWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();

    }
}
