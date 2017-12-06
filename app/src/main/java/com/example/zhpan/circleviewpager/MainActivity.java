package com.example.zhpan.circleviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.viewpager.holder.HolderCreator;
import com.example.viewpager.holder.ViewHolder;
import com.example.viewpager.view.CircleViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CircleViewPager mViewpager;
    private CircleViewPager mViewPager2;
    private List<DataBean> mList = new ArrayList<>();
    private List<Integer> mListInt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setViewPager();
    }

    private void initView() {
        mViewpager = (CircleViewPager) findViewById(R.id.viewpager);
        mViewPager2 = (CircleViewPager) findViewById(R.id.viewpager2);
    }

    private void initData() {
        DataBean dataBean = new DataBean("http://img0.imgtn.bdimg.com/it/u=3159618424,497154385&fm=214&gp=0.jpg", "图片一");
        DataBean dataBean1 = new DataBean("http://img0.imgtn.bdimg.com/it/u=3159618424,497154385&fm=214&gp=0.jpg", "图片二");
        DataBean dataBean2 = new DataBean("http://img4.imgtn.bdimg.com/it/u=928730363,1881984966&fm=214&gp=0.jpg", "图片三");
        DataBean dataBean3 = new DataBean("http://img4.imgtn.bdimg.com/it/u=3779410813,199087977&fm=214&gp=0.jpg", "图片四");
        DataBean dataBean4 = new DataBean("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg", "图片五");
        mList.add(dataBean);
        mList.add(dataBean1);
        mList.add(dataBean2);
        mList.add(dataBean3);
        mList.add(dataBean4);


        for (int i = 1; i <= 5; i++) {
            int drawable = getResources().getIdentifier("a" + i, "drawable", getPackageName());
            mListInt.add(drawable);
        }
    }

    private void setViewPager() {

        //  设置指示器半径大小（dp）
        mViewpager.setDotWidth(8);
        //  设置指示器位置
        mViewpager.setIndicatorGravity(CircleViewPager.IndicatorGravity.END);
        mViewpager.setAutoPlay(true);
        //  是否显示指示器
        mViewpager.isShowIndicator(true);
        //  设置图片切换时间间隔
        mViewpager.setInterval(3000);
        mViewpager.setCanLoop(true);
        //  设置页面点击事件
        mViewpager.setOnPageClickListener(new CircleViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                List<DataBean> list = mViewpager.getList();
                Toast.makeText(MainActivity.this, "点击了" + list.get(position).getDescribe(), Toast.LENGTH_SHORT).show();
            }
        });

        mViewpager.setPages(mList, new HolderCreator<ViewHolder>() {
            @Override
            public ViewHolder createViewHolder() {
                return new MyViewHolder();
            }
        });


        mViewPager2.setPages(mListInt, new HolderCreator<ViewHolder>() {
            @Override
            public ViewHolder createViewHolder() {
                return new MyViewHolder();
            }
        });
        //  设置指示器资源图片
        mViewPager2.setIndicatorColor(Color.parseColor("#6C6D72"),
                Color.parseColor("#18171C"));
        mViewPager2.setOnPageClickListener(new CircleViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                Toast.makeText(MainActivity.this, "图片"+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager2.stopLoop();
        mViewpager.stopLoop();
    }
}
