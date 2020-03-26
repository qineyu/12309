package com.qinyue.monitor.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.home.MyFragment;
import com.qinyue.monitor.util.Base64Converter;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.data.SPUtils;
import com.xuexiang.xutil.net.JSONUtils;
import com.xuexiang.xutil.security.EncodeUtils;
import com.xuexiang.xutil.security.EncryptUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class PwdLoginActivity extends BaseActivity {
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_pwd)
    MaterialEditText pwdEdit;
    @Override
    public String initTitleText() {
        return "账号密码登录";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
    }
    @OnClick({R.id.tv_reg,R.id.tv_lsz,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_lsz:{//验证码登录
                finish();
            }break;
            case R.id.tv_reg:{//注册
                starActivity(RegisterActivity.class);
            }break;
            case R.id.but_reg:{//登录按钮
                if (phoneEdit.isEmpty()){
                    XToast.warning(this,"请输入账号").show();
                    break;
                }
                if (pwdEdit.isEmpty()){
                    XToast.warning(this,"请输入密码").show();
                    break;
                }
                doLogin();
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
        return R.layout.activty_pwdlogin;
    }

    @Override
    protected void init() {

    }

    @SuppressLint("CheckResult")
    private void doLogin(){
        miniLoadingDialog.show();
        String phone =new String(EncodeUtils.base64Encode(phoneEdit.getEditValue())) ;
        String pwd =new String(EncodeUtils.base64Encode(pwdEdit.getEditValue())) ;
        String md5 = Base64Converter.encrypt32(phoneEdit.getEditValue()+pwdEdit.getEditValue()+"123456");
        Map<String,String > map = new HashMap<>();
        map.put("username",phone);
        map.put("password",pwd);
        map.put("secretKey",md5);
        String data = new Gson().toJson(map);
        RxHttp.postForm(TagConstant.BASEURL+ NetConstant.loginByPwd)  //发送表单形式的Post请求
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("username",phone )
                .add("password",pwd )
                .add("secretKey",md5 )
                .asParser(new SimpleParser<BaseBean<UserBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    miniLoadingDialog.dismiss();
                    BaseBean<UserBean> beanBaseBean = s;
                    if (beanBaseBean.getCode()==101){
                        XToast.success(PwdLoginActivity.this,beanBaseBean.getMsg());
                        SPUtils.putObject(SPUtils.getDefaultSharedPreferences(),TagConstant.USERTAG,beanBaseBean.getData());
                        MyFragment.logTagChanged.postValue(true);
                        setResult(101);
                        finish();
                    }else {
                        XToast.error(PwdLoginActivity.this,beanBaseBean.getMsg());
                    }
                }, throwable -> {
                    //请求失败
                    miniLoadingDialog.dismiss();
                    XToast.error(PwdLoginActivity.this,"登录失败 "+throwable.getMessage());
                });

    }

    @Override
    protected Boolean status() {
        return false;
    }
}
