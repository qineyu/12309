package com.qinyue.monitor.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    }

    @OnClick({R.id.view_login,R.id.view_my,R.id.view_msg,R.id.view_xf,R.id.view_yy,R.id.view_qzyjx,R.id.view_jczxx,R.id.view_logout})
    public void onCkick(View view){
        switch (view.getId()){
            case R.id.view_login:{//登录

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
            case R.id.view_logout:{//登录

            }
            break;
            case R.id.view_qzyjx:{//退出登录

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
