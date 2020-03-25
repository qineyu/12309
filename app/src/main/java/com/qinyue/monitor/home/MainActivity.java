package com.qinyue.monitor.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private int CheckedIndex = 0;
    private List<String> list = new ArrayList<>();
    private List<Integer> listImgYes = new ArrayList<>();
    private List<Integer> listImgNo = new ArrayList<>();
    List<Fragment> mFragments = new ArrayList<>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    CommonNavigator commonNavigator;
    @Override
    public String initTitleText() {
        return "";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        list.add("首页");
        listImgYes.add(R.mipmap.ic_launcher);
        listImgYes.add(R.mipmap.ic_launcher);
        listImgYes.add(R.mipmap.ic_launcher);
        listImgNo.add(R.mipmap.ic_launcher_round);
        listImgNo.add(R.mipmap.ic_launcher_round);
        listImgNo.add(R.mipmap.ic_launcher_round);
        list.add("办事");
        list.add("个人中心");
        mFragments.add(new FirstFragment());
        mFragments.add(new WorkFragment());
        mFragments.add(new MyFragment());
        initMagicIndicator1();
        mFragmentContainerHelper.handlePageSelected(CheckedIndex, false);
        switchPages(CheckedIndex);
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
    }
    private void initMagicIndicator1() {
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setBackgroundColor(Color.TRANSPARENT);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int i) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                // load custom layout
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout2, null);
                final ImageView titleImg = customLayout.findViewById(R.id.img);
                final TextView titleText = customLayout.findViewById(R.id.tv);
                titleText.setText(list.get(i));
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.theme_color));
                        titleImg.setImageResource(listImgYes.get(i));
                        RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) titleImg.getLayoutParams();
                        layoutParams.width= ConvertUtils.dp2px(35);
                        layoutParams.height=ConvertUtils.dp2px(35);
                        titleImg.setLayoutParams(layoutParams);

                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Color.parseColor("#666666"));
                        titleImg.setImageResource(listImgNo.get(i));
                        RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) titleImg.getLayoutParams();
                        layoutParams.width=ConvertUtils.dp2px(20);
                        layoutParams.height=ConvertUtils.dp2px(20);
                        titleImg.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
//                        titleImg.setScaleX(0.7f * leavePercent);
//                        titleImg.setScaleY(0.7f * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
//                        titleImg.setScaleX(1.2f * enterPercent);
//                        titleImg.setScaleY(1.2f * enterPercent);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedIndex = i;
                        mFragmentContainerHelper.handlePageSelected(i);
                        switchPages(i);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }
    private void switchPages(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        for (int i = 0, j = mFragments.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mFragments.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            }else {
                fragmentTransaction.add(R.id.fl_fragment_content, fragment);
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mFragments.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fl_fragment_content, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        switch (list.get(index)){
            case "首页":
                break;
            case "办事":
                break;
            case "个人中心":
                break;
        }
    }
}
