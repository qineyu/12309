package com.qinyue.monitor.base;

import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.xuexiang.xui.XUI;
import com.xuexiang.xutil.XUtil;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/25
 * 描述:
 **/
public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LnitializeXUI();
    }

    /**
     * 初始化XUI
     */
    private void LnitializeXUI(){
        XUI.init(this); //初始化UI框架
        XUtil.init(this);
        XUtil.debug(true);
        XUI.debug(true);  //开启UI框架调试日志
    }
    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
//            SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
//                @Override
//                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                    //指定为经典Footer，默认是 BallPulseFooter
//                    return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
//                }
//            });
    }
}
