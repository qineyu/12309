package com.qinyue.monitor.splash;

import android.app.Activity;

import com.qinyue.monitor.home.MainActivity;
import com.qinyue.monitor.data.DataProvider;
import com.xuexiang.xui.widget.activity.BaseGuideActivity;

import java.util.List;


/**
 * 创建人:qinyue
 * 创建日期:2020/2/26
 * 描述:引导页
 **/
public class GuideActivity extends BaseGuideActivity {

    @Override
    protected List<Object> getGuideResourceList() {
        return DataProvider.getUsertGuides();
    }

    @Override
    protected Class<? extends Activity> getSkipClass() {
        return MainActivity.class;
    }
}
