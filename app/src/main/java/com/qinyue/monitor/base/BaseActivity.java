package com.qinyue.monitor.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.qinyue.monitor.R;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;


/**
 * BaseActivity,所有Activty基类，抽取公共点，实现解耦。
 */
public abstract class BaseActivity extends AppCompatActivity{
    private Context activityContext;
    private long exitTime = 0;
    protected final String TAG = "这是" + this.getClass().getCanonicalName();
    private int netMobile;
    ImageView ivTitleRight;
   public MiniLoadingDialog miniLoadingDialog;
    private boolean isShowImgStrLef = false;
    TitleBar titleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            boolean result = fixOrientation();
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        miniLoadingDialog = WidgetUtils.getMiniLoadingDialog(this);
        //打印是哪个activity
        Log.e("BaseActivity", this.getLocalClassName() + "Activity");
        //判断是否需要沉浸式
        if (status() == null) {
            setImmersion(false);
        } else {
            setImmersion(status());
        }
        initview();
        //初始化title
        initializeTitleView();
        bottomNavigation();
        init();
        initData(savedInstanceState);

    }
    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }
    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    private void initializeTitleView(){
        titleBar = findViewById(R.id.titleview);
        if (titleBar!=null){
            String s = initTitleText();
            if (s!=null) {
                titleBar.setTitle(s);
            }
            titleBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickTitleLeft();
                }
            });
        }
    }

    /**
     * title左边点击
     */
    protected void onClickTitleLeft(){ }
    /**
     * title左边图标显示
     * @param visible
     */
    protected void setTitleIconLeftVisible(boolean visible){
        if (titleBar!=null){
            titleBar.setLeftVisible(visible);
        }
    }
    /**
     * title文字修改
     * @param text
     */
    protected void setTitleText(String text){
        if (titleBar!=null&&text!=null){
            titleBar.setTitle(text);
        }
    }
    /**
     * 获取标题
     * @return
     */
    protected TitleBar getTitleBar(){
        return titleBar;
    }
    /**
     * 显示或者隐藏标题
     */
    protected void setTitleBarVisibility(boolean visible){
        if (titleBar!=null){
            titleBar.setVisibility(visible?View.VISIBLE:View.GONE);
        }
    }
    /**
     * 初始化标题文字
     *
     * @return 文字
     */
    public abstract String initTitleText();

    /**
     * 子类重写后初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 子类重写后初始化view
     */
    protected abstract void initview();

    /**
     * @return 控制判断是否网络判断
     */
    protected abstract boolean network();

    /**
     * @return 子类返回布局ID
     */
    protected abstract int getLayoutID();

    /**
     * 初始化方法
     */
    protected abstract void init();

    /**
     * 设置是为沉浸式状态栏，true为沉浸式，false为正常
     */
    protected abstract Boolean status();


    /**
     * 销毁页面解除presenter关联
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取supportFragmentManager
     *
     * @return supportFragmentManager
     */
    protected FragmentManager getsupportFragmentManager() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        return supportFragmentManager;
    }

    /**
     * 底部导航栏适配
     */

    private void bottomNavigation() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * getActivityContext
     */
    protected Context getActivityContext() {
        activityContext = this.activityContext.getApplicationContext();
        return activityContext;
    }

    /**
     * 沉浸式
     */
    public void setImmersion(boolean status) {
        if (status) {
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setNavigationBarColor(Color.TRANSPARENT);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * @param context 上下文
     * @param message 信息
     */
    public void showToast(Context context, String message) {
        if (context!=null&&message!=null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * showLog 默认或者错误都为E级Log
     *
     * @param rank    级别
     * @param message 信息
     */
    public void showLog(String rank, String message) {
        switch (rank) {
            case "e":
                Log.e(TAG, message);
                break;
            case "d":
                Log.d(TAG, message);
                break;
            case "i":
                Log.i(TAG, message);
                break;
            case "v":
                Log.v(TAG, message);
                break;
            case "w":
                Log.w(TAG, message);
                break;
            default:
                Log.e(TAG, message);
        }
    }


    /**
     * 主动调用，可以获取网络是否连接
     */
    protected boolean isNetworkConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            assert manager != null;
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }

        }
        return false;
    }


    /**
     * 启动Activity使用
     */

    public void starActivity(Class<?> classs) {
        this.startActivity(new Intent(this, classs));
    }

}





