package com.qinyue.monitor.my;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/27
 * 描述:
 **/
public class EditMyMsgActivty extends BaseActivity {
    @BindView(R.id.tv_name)
    MaterialEditText nameEdit;
    @BindView(R.id.tv_sex)
    TextView sexTv;
    @BindView(R.id.edit_card)
    MaterialEditText cardEdit;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    @BindView(R.id.edit_email)
    MaterialEditText emailEdit;
    @BindView(R.id.edit_address)
    MaterialEditText addressEdit;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyBut;
    private CountDownButtonHelper mCountDownHelper;
    private int checkIndex = -1;
    @Override
    public String initTitleText() {
        return "修改信息";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        mCountDownHelper = new CountDownButtonHelper(verifyBut, 60);
    }
    @OnClick({R.id.btn_get_verify_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_get_verify_code:{//获取验证码
                if (phoneEdit.validate()){
                    mCountDownHelper.start();
                }
            }break;
            case R.id.choose_sex:{//选择性别
                showSimpleBottomSheetList();
                miniLoadingDialog.dismiss();
            }break;
        }
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
                        sexTv.setText(tag);
                    }
                })
                .build().show();
    }
    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activty_editmymsg;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected Boolean status() {
        return false;
    }
}
