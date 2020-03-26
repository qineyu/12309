package com.qinyue.monitor.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyCodeBut;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.tv_name)
    MaterialEditText nameEdit;
    @BindView(R.id.edit_card)
    MaterialEditText cardEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    @BindView(R.id.edit_pwd)
    MaterialEditText pwdEdit;
    @BindView(R.id.edit_email)
    MaterialEditText emailEdit;
    @BindView(R.id.edit_pwd_ag)
    MaterialEditText pwdAgEdit;
    @BindView(R.id.edit_address)
    MaterialEditText addressEdit;
    private int checkIndex = -1;
    private CountDownButtonHelper mCountDownHelper;
    @Override
    public String initTitleText() {
        return "用户注册";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
        mCountDownHelper = new CountDownButtonHelper(verifyCodeBut, 60);
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }
    @OnClick({R.id.choose_sex,R.id.btn_get_verify_code,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.choose_sex:{//选择性别
                showSimpleBottomSheetList();
                miniLoadingDialog.dismiss();
            }break;
            case R.id.but_reg:{//注册按钮
                if (nameEdit.isEmpty()){
                    XToast.warning(this,"请输入姓名").show();
                    break;
                }
                if (checkIndex==-1){
                    XToast.warning(this,"请选择性别").show();
                    break;
                }
                if (cardEdit.isEmpty()){
                    XToast.warning(this,"请输入身份证号码").show();
                    break;
                }
                if (phoneEdit.isEmpty()){
                    XToast.warning(this,"请输入手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()){
                    XToast.warning(this,"请输入短信验证码").show();
                    break;
                }
                if (pwdEdit.isEmpty()){
                    XToast.warning(this,"请输入密码").show();
                    break;
                }
                if (pwdAgEdit.isEmpty()){
                    XToast.warning(this,"请再次输入密码").show();
                    break;
                }
                if (!pwdAgEdit.getEditValue().equals(pwdEdit.getEditValue())){
                    XToast.warning(this,"两次输入的密码不一致").show();
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
    protected Boolean status() {
        return false;
    }
    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(checkIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndex = position;
                        tvSex.setText(tag);
                    }
                })
                .build().show();
    }
}
