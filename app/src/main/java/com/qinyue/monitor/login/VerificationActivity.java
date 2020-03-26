package com.qinyue.monitor.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class VerificationActivity extends BaseActivity {
    private CountDownButtonHelper mCountDownHelper;
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyCodeBut;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    @Override
    public String initTitleText() {
        return "手机验证码登录";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
        mCountDownHelper = new CountDownButtonHelper(verifyCodeBut, 60);
    }
    @OnClick({R.id.tv_reg,R.id.tv_lsz,R.id.btn_get_verify_code,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_lsz:{//账号密码登录
                startActivityForResult(new Intent(this,PwdLoginActivity.class),100);
            }break;
            case R.id.tv_reg:{//注册
                starActivity(RegisterActivity.class);
            }break;
            case R.id.but_reg:{//登录按钮
                if (phoneEdit.isEmpty()){
                    XToast.warning(this,"请输入手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()){
                    XToast.warning(this,"请输入短信验证码").show();
                    break;
                }
                miniLoadingDialog.show();
            }break;
            case R.id.btn_get_verify_code:{//获取验证码
                if (phoneEdit.validate()){
                    mCountDownHelper.start();
                }
            }break;
        }
    }
    @Override
    protected boolean network() {
        return false;
    }
    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activty_verification;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==101){
            finish();
        }
    }

    @Override
    protected Boolean status() {
        return false;
    }
}
