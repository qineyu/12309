package com.qinyue.monitor.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.login.RegisterActivity;
import com.qinyue.monitor.login.UserBean;
import com.qinyue.monitor.login.VerificationActivity;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xutil.data.SPUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/25
 * 描述:
 **/
public class MyFragment extends Fragment {
    Unbinder unbinder;
    MainActivity mainActivity;
    @BindView(R.id.riv_head_pic)
    RadiusImageView headerImageView;
    @BindView(R.id.text_login)
    TextView loginText;
    @BindView(R.id.view_logout)
    SuperTextView logoutView;
    public static final MutableLiveData<Boolean> logTagChanged = new MutableLiveData<>();
    public MyFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();
        initOnCliclek();
        return view;
    }

    private void initOnCliclek() {
        if (UserUtils.isLogin()){
            loginText.setText(UserUtils.getRealName()+"\n"+UserUtils.getUserName().replaceAll(TagConstant.POHNETOX,TagConstant.POHNETOY));
            logoutView.setVisibility(View.VISIBLE);
        }else {
            loginText.setText("点击注册/登录");
            logoutView.setVisibility(View.GONE);
        }
        logTagChanged.observe(this,aBoolean -> {
            if (aBoolean){
                //登录了
                logoutView.setVisibility(View.VISIBLE);
                UserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, UserBean.class);
                if (object!=null){
                    loginText.setText(object.getRealName()+"\n"+object.getUsername().replaceAll(TagConstant.POHNETOX,TagConstant.POHNETOY));
                }
            }else{
                //退出登录
                loginText.setText("点击注册/登录");
                SPUtils.clear(SPUtils.getDefaultSharedPreferences());
                logoutView.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.view_login,R.id.view_my,R.id.view_msg,R.id.view_xf,R.id.view_yy,R.id.view_qzyjx,R.id.view_jczxx,R.id.view_logout})
    public void onCkick(View view){
        switch (view.getId()){
            case R.id.view_login:{//登录
                startActivity(new Intent(mainActivity, VerificationActivity.class));
            }
            break;
            case R.id.view_my:{//我的信息

            }
            break;
            case R.id.view_msg:{//登录

            }
            break;
            case R.id.view_xf:{//登录

            }
            break;
            case R.id.view_yy:{//登录

            }
            break;
            case R.id.view_jczxx:{//登录

            }
            break;
            case R.id.view_logout:{//退出登录

            }
            break;
            case R.id.view_qzyjx:{//退出登录
                logTagChanged.setValue(false);
            }
            break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
