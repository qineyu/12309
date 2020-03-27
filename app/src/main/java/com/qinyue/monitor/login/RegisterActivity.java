package com.qinyue.monitor.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseBean;
import com.qinyue.monitor.base.BaseBean2;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

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
    @BindView(R.id.edit_lszh)
    MaterialEditText lszhEdit;
    @BindView(R.id.tv_lsz)
    TextView lszTv;
    @BindView(R.id.lszfj_yl)
    ShadowButton lszBut;
    @BindView(R.id.tv_lsz_time)
    TextView lszTimeTv;
    @BindView(R.id.lszfj_yl_time)
    ShadowButton lszTimeBut;
    @BindView(R.id.tv_sfz)
    TextView sfzTv;
    @BindView(R.id.sfz_yl)
    ShadowButton sfzBut;
    @BindView(R.id.tv_sfz_time)
    TextView sfzTimeTv;
    @BindView(R.id.sfz_yl_time)
    ShadowButton sfzTimeBut;
    private int checkIndex = -1;
    private CountDownButtonHelper mCountDownHelper;
    private int checkfiletype = 0;
    private Map<Integer, LocalMedia> fileMap = new HashMap<>();

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

    @OnClick({R.id.choose_sex, R.id.btn_get_verify_code, R.id.but_reg, R.id.lszfj_yl, R.id.choose_lsz, R.id.lszfj_yl_time, R.id.choose_lsz_time, R.id.sfz_yl, R.id.choose_sfz, R.id.sfz_yl_time, R.id.choose_sfz_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_sex: {//选择性别
                showSimpleBottomSheetList();
                miniLoadingDialog.dismiss();
            }
            break;
            case R.id.lszfj_yl: {//预览律师证附件
                ylPhoto(0);
            }
            break;
            case R.id.choose_lsz: {//选择律师证附件
                checkfiletype = 0;
                showChoosePhotoDialog();
            }
            break;
            case R.id.lszfj_yl_time: {//预览律师证附件有效期
                ylPhoto(1);
            }
            break;
            case R.id.choose_lsz_time: {//选择律师证附件有效期
                checkfiletype = 1;
                showChoosePhotoDialog();
            }
            break;
            case R.id.sfz_yl: {//预览身份证附件
                ylPhoto(2);
            }
            break;
            case R.id.choose_sfz: {//选择身份证附件
                checkfiletype = 2;
                showChoosePhotoDialog();
            }
            break;
            case R.id.sfz_yl_time: {//预览身份证附件有效期
                ylPhoto(3);
            }
            break;
            case R.id.choose_sfz_time: {//选择身份证附件有效期
                checkfiletype = 3;
                showChoosePhotoDialog();
            }
            break;
            case R.id.but_reg: {//注册按钮
                if (nameEdit.isEmpty()) {
                    XToast.warning(this, "请输入姓名").show();
                    break;
                }
                if (checkIndex == -1) {
                    XToast.warning(this, "请选择性别").show();
                    break;
                }
                if (cardEdit.isEmpty()) {
                    XToast.warning(this, "请输入身份证号码").show();
                    break;
                }
                if (phoneEdit.isEmpty()) {
                    XToast.warning(this, "请输入手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()) {
                    XToast.warning(this, "请输入短信验证码").show();
                    break;
                }
                if (pwdEdit.isEmpty()) {
                    XToast.warning(this, "请输入密码").show();
                    break;
                }
                if (pwdAgEdit.isEmpty()) {
                    XToast.warning(this, "请再次输入密码").show();
                    break;
                }
                if (!pwdAgEdit.getEditValue().equals(pwdEdit.getEditValue())) {
                    XToast.warning(this, "两次输入的密码不一致").show();
                    break;
                }
                miniLoadingDialog.show();
                upLoad();
            }
            break;
            case R.id.btn_get_verify_code: {//获取验证码
                if (phoneEdit.validate()) {
                    getVCode();
                }
            }
            break;
        }
    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void ylPhoto(int type) {
        List<LocalMedia> media = new ArrayList<>();
        int position = 0;
        int index = 0;
        for (Integer key : fileMap.keySet()) {
            media.add(fileMap.get(key));
            if (key == type) {
                position = index;
            }
            index++;
        }

        PictureSelector.create(this)
                .themeStyle(R.style.picture_default_style)
                .openExternalPreview(position, media);
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

    private void showChoosePhotoDialog() {
        new BottomSheet.BottomListSheetBuilder(this)
                .addItem("拍照")
                .addItem("从相册中选择")
                .setTitle("选择文件")
                .setCheckedIndex(checkIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {
                            PictureSelector.create(RegisterActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(RegisterActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.SINGLE)
                                    .isCamera(false)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                })
                .build().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            uploadPhoto(selectList);
        } else if (requestCode == PictureConfig.REQUEST_CAMERA) {
            //拍照返回
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            uploadPhoto(selectList);
        }
    }

    private void uploadPhoto(List<LocalMedia> selectList) {
        if (selectList != null && selectList.size() > 0) {
            switch (checkfiletype) {
                case 0: {//律师证
                    fileMap.put(0, selectList.get(0));
                    lszBut.setVisibility(View.VISIBLE);
                    lszTv.setText(selectList.get(0).getPath());
                }
                break;
                case 1: {//律师证有效期
                    fileMap.put(1, selectList.get(0));
                    lszTimeBut.setVisibility(View.VISIBLE);
                    lszTimeTv.setText(selectList.get(0).getPath());
                }
                break;
                case 2: {//身份证
                    fileMap.put(2, selectList.get(0));
                    sfzBut.setVisibility(View.VISIBLE);
                    sfzTv.setText(selectList.get(0).getPath());
                }
                break;
                case 3: {//身份证有效期
                    fileMap.put(3, selectList.get(0));
                    sfzTimeBut.setVisibility(View.VISIBLE);
                    sfzTimeTv.setText(selectList.get(0).getPath());
                }
                break;
            }
        } else {
            XToast.error(this, "选择图片出错").show();
        }
    }

    private void getVCode() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getCode)
                .add("phone", phoneEdit.getEditValue())
                .add("secretKey", Base64Converter.encrypt32(phoneEdit.getEditValue() + "123456"))
                .add("source", "applets")
                .asParser(new SimpleParser<BaseBean2<CodeBean>>() {})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getCode()==101){
                        mCountDownHelper = new CountDownButtonHelper(verifyCodeBut, s.getData().getValidTime());
                        mCountDownHelper.start();
                        XToast.success(RegisterActivity.this,s.getData().getMessage()).show();
                    }else {
                        XToast.error(RegisterActivity.this,"获取验证码失败").show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(RegisterActivity.this, throwable.getMessage()).show();
                });
    }

    private void upLoad() {
        Map<String, String> map = new HashMap<>();
        map.put("realName", nameEdit.getEditValue());
        map.put("gender", (checkIndex + 1) + "");
        map.put("mobile", phoneEdit.getEditValue());
        map.put("idCard", cardEdit.getEditValue());
        map.put("password", pwdEdit.getEditValue());
        map.put("confirmPassword", pwdAgEdit.getEditValue());
        map.put("email", emailEdit.getEditValue());
        map.put("address", addressEdit.getEditValue());
        map.put("smsCode", verifyEdit.getEditValue());
        map.put("memberLevels", "1");
        String aes = new Gson().toJson(map);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.saveUser)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", Base64Converter.AESEncode(TagConstant.AESKEY,aes))
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(RegisterActivity.this, throwable.getMessage()).show();
                });
    }
}
