package com.qinyue.monitor.my;

import android.os.Bundle;
import android.view.View;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/27
 * 描述:
 **/
public class MyMsgActivity extends BaseActivity{
    @BindView(R.id.riv_head_pic)
    RadiusImageView headerImageView;
    @BindView(R.id.view_name)
    SuperTextView nameView;
    @BindView(R.id.view_sex)
    SuperTextView sexView;
    @BindView(R.id.view_phone)
    SuperTextView phoneView;
    @BindView(R.id.view_idcard)
    SuperTextView idCardView;
    @BindView(R.id.view_email)
    SuperTextView emailView;
    @BindView(R.id.view_address)
    SuperTextView addressView;
    @Override
    public String initTitleText() {
        return "我的信息";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.isLogin()){
            nameView.setRightString(UserUtils.getRealName());
            sexView.setRightString(UserUtils.getSex());
            phoneView.setRightString(UserUtils.getUserName().replaceAll(TagConstant.POHNETOX,TagConstant.POHNETOY));
            idCardView.setRightString(UserUtils.getIdCard().replaceAll(TagConstant.IDCARDTOX,TagConstant.IDCARDTOY));
            emailView.setRightString(UserUtils.getEmail());
            addressView.setRightString(UserUtils.getAddress());
        }
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }
    @OnClick({R.id.view_edit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.view_edit:{//修改
                starActivity(EditMyMsgActivty.class);
            }break;
        }
    }
    @Override
    protected void initview() {

    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mymsg;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
}
