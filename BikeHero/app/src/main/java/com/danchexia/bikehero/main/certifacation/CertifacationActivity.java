package com.danchexia.bikehero.main.certifacation;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.car.qijia.thinker.photo.view.ImageSelectorActivity;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.app.MyApplication;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.generalphotoreview.PhotoReviewActivity;
import com.danchexia.bikehero.jpush.ReceiverBean;
import com.danchexia.bikehero.jpush.myobserveable.JiPushGetData;
import com.danchexia.bikehero.main.bean.SystemParamsBean;
import com.danchexia.bikehero.main.feedback.adapter.ChoosePhotoAdapter;
import com.danchexia.bikehero.main.my.bean.PersonalBean;
import com.danchexia.bikehero.main.my.fragment.CertifacationFragment;
import com.danchexia.bikehero.utils.MyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import vc.thinker.colours.client.model.RealnameVO;
import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;

/**
 * Created by farley on 17/5/17.
 * description:身份证认证
 */

public class CertifacationActivity extends MvpActivity<CertifacationPresenter,ICertifacationView> implements ICertifacationView, View.OnClickListener ,Observer {
    private CertifacationPresenter presenter;
    private Button complete;
    private CertifacationFragment certifacationFragment;
    private FragmentManager fragmentManager;
    private EditText userName,userAuth;
    private ImageView head_left,head_right;
    private TextView head_title;
    private RecyclerView mRecyclerView;
    private ChoosePhotoAdapter chooseAdapter;
    private List<String> imgArray = new ArrayList<>();//照片
    private LinearLayout student;
    private EditText studentId,callageName;
    private TextView failed_desc;
    private Observable observable;
    private LinearLayout certifation_ll;
    @Override
    protected CertifacationPresenter CreatePresenter() {
        return presenter = new CertifacationPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifacation);
        initView();
        initCertifacationFragment();
        setMyObserverable();//关于极光的
    }
    public void setMyObserverable() {
        JiPushGetData jiPushGetData = MyApplication.getJiPushGetData();
        this.observable = jiPushGetData;
        this.observable.addObserver(this);
    }
    private void initView() {
        student = (LinearLayout) findViewById(R.id.student);
        certifation_ll = (LinearLayout) findViewById(R.id.certifation_ll);
        complete = (Button) findViewById(R.id.complete);
        callageName = (EditText) findViewById(R.id.callageName);
        studentId = (EditText) findViewById(R.id.studentId);
        userName = (EditText) findViewById(R.id.userName);
        userAuth = (EditText) findViewById(R.id.userAuth);
        failed_desc = (TextView) findViewById(R.id.failed_desc);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_receycleview);
        head_title.setText(Utils.object2String(getString(R.string.activity_certifacation_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        complete.setOnClickListener(this);
        userAuth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String idCord = s.toString().trim();
                if (idCord.length() == 18){
//                    complete.setEnabled(true);
                }else{
//                    complete.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setPhoto();
        SystemParamsBean systemParamsBean = MyUtils.getSystemData();
        if (systemParamsBean != null){
            if ("student_card".equals(systemParamsBean.getAuthenType())){
                student.setVisibility(View.VISIBLE);
                haveStudentIdent = true;
            }
            certifation_ll.setVisibility(View.GONE);
            if (systemParamsBean.getNeedUpCertificates() == null){
                return;
            }
            if (systemParamsBean.getNeedUpCertificates()){
                certifation_ll.setVisibility(View.VISIBLE);
            }else{
                certifation_ll.setVisibility(View.GONE);
            }
        }
        PersonalBean personalBean = MyUtils.getPersonData();
        setAuthStatus(personalBean);
        if (Config.isOpenBattery){
//            certifation_ll.setVisibility(View.);
        }
    }
    private boolean haveStudentIdent = false;//是否有学号认证
    public void setAuthStatus(PersonalBean personalBean){
        if (personalBean != null && personalBean.getAuthStatus().equals(MyUtils.PERSONAL_AUTH_STATUS_ITEM2)){
            MyApplication.setIsIdenty(4);
            ActivityController.startInvate(this);
            finish();
            return;
        }
        if (personalBean != null && personalBean.getAuthApplyStatus() != null){
            if (personalBean.getAuthApplyStatus() == 1){//审批中
                setUpSuccess(personalBean);
            }else if (personalBean.getAuthApplyStatus() == 2){//审批成功
                MyApplication.setIsIdenty(4);
                ActivityController.startInvate(this);
                finish();
            }else if (personalBean.getAuthApplyStatus() == 3){//审批失败
                setUpFailed(personalBean);
            }
        }
    }
    private boolean clickEnable = true;//控制能否上传图片
    //设置照片
    private void setPhoto() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        chooseAdapter = new ChoosePhotoAdapter(this, imgArray);
        chooseAdapter.setPhotoTimes(3);
        chooseAdapter.setOnItemClickLitener(new ChoosePhotoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                if (clickEnable) {
                    if (position == imgArray.size()) {
                        ImageSelectorActivity activty = new ImageSelectorActivity();
                        activty.start(CertifacationActivity.this, 3 - imgArray.size(), ImageSelectorActivity.MODE_MULTIPLE, true, true, false);
                    } else {
                        Intent review = new Intent(CertifacationActivity.this, PhotoReviewActivity.class);
                        review.putStringArrayListExtra("IMGLIST", (ArrayList<String>) imgArray);
                        review.putExtra("POSITION", position);
                        startActivityForResult(review, 1000);
                    }
                }
            }
        });
        mRecyclerView.setAdapter(chooseAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            if (images.size() > 0) {
                LogUtils.d("size=" + images.size());
                imgArray.addAll(images);
                if (chooseAdapter != null) {
                    chooseAdapter.notifyDataSetChanged();
                }
            }
        }
        if (resultCode == RESULT_OK && requestCode == 1000) {
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra("RESULT");
            if (images != null) {
                LogUtils.d("size=" + images.size());
                imgArray = images;
                if (chooseAdapter != null) {
                    chooseAdapter.refreshData(imgArray);
                }
            }
        }
    }
    public List<Bitmap> getImageList()
    {
        List<Bitmap> bitmaps = new ArrayList<>();
        if (imgArray.size() > 0)
        {
            for (String url :imgArray) {
                File file  = new File(url);
                Bitmap bitmap = Utils.getBitmapFromFile(file,1000,1000);
                LogUtils.d("bitmap="+bitmap);
                if (bitmap != null) {
                    bitmaps.add(bitmap);
                }
            }
            return bitmaps;
        }
        return  null;
    }
    private void initCertifacationFragment(){
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        certifacationFragment = new CertifacationFragment(false);
        transaction.replace(R.id.certifacation, certifacationFragment);
        // 事务提交
        transaction.commit();
    }
    public void  setBtnEnable(){
//        complete.setEnabled(true);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.complete:
                String name = userName.getText().toString().trim();
                String idCard = userAuth.getText().toString().trim();
                String studentIdstr = studentId.getText().toString().trim();
                String callageNamestr = callageName.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    ShowToast.show(this,getString(R.string.certifacation_toast1));
                }else if(!Utils.checkIdCard(idCard)){
                    ShowToast.show(this,getString(R.string.certifacation_toast2));
                }else if(haveStudentIdent && TextUtils.isEmpty(studentIdstr)) {
                    ShowToast.show(this,getString(R.string.certifacation_toast4));
                }else if(haveStudentIdent && TextUtils.isEmpty(callageNamestr)) {
                    ShowToast.show(this,getString(R.string.certifacation_toast5));
                }else if(haveStudentIdent && imgArray.size() < 1) {
                    ShowToast.show(this,getString(R.string.certifacation_toast6));
                }else{
                    RealnameVO vo = new RealnameVO();
                    vo.setIdcard(idCard);
                    vo.setName(name);
                    vo.setSchoolName(callageNamestr);
                    vo.setStudentId(studentIdstr);

                    presenter.addimg(vo);
//                    complete.setEnabled(false);
                }
                break;
            case R.id.head_left:
                finish();
                break;
            default:
                break;
        }
    }
    //提交成功
    public void setUpSuccess(PersonalBean personalBean){
        complete.setText(getString(R.string.up_success));
        complete.setEnabled(false);
        userName.setText(Utils.object2String(personalBean.getName()));
        userAuth.setText(Utils.object2String(personalBean.getIdCard()));
        studentId.setText(Utils.object2String(personalBean.getStudentId()));
        callageName.setText(Utils.object2String(personalBean.getSchoolName()));
        userName.setEnabled(false);
        userAuth.setEnabled(false);
        studentId.setEnabled(false);
        callageName.setEnabled(false);
        failed_desc.setVisibility(View.GONE);
        clickEnable = false;
    }
    public void setUpFailed(PersonalBean bean){
        complete.setText(getString(R.string.up_failed));
        complete.setEnabled(true);
        failed_desc.setVisibility(View.VISIBLE);
        failed_desc.setText(Utils.object2String(bean.getAuthApplyRemark()));
        userName.setText(Utils.object2String(bean.getName()));
        userAuth.setText(Utils.object2String(bean.getIdCard()));
        studentId.setText(Utils.object2String(bean.getStudentId()));
        callageName.setText(Utils.object2String(bean.getSchoolName()));
        userName.setEnabled(true);
        userAuth.setEnabled(true);
        studentId.setEnabled(true);
        callageName.setEnabled(true);
        clickEnable = true;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JiPushGetData) {
            JiPushGetData jiPushGetData = (JiPushGetData) o;
            display(jiPushGetData.getReceiverBean());
        }
    }
    //如果获取到自定义消息是10002 则去支付
    private void display(ReceiverBean receiverBean) {
//        logout:"99999";
//        订单创建:"10001"
//        订单结束"10002"
        if (receiverBean.getMsgType().equals("808")){//通知点击
            if (receiverBean.isClicked()){
                presenter.getMyData();//获取我的个人数据
            }
        }
        LogUtils.d("====================show==============收到通知");
        if (receiverBean.getMsgType().equals("809")){//通知收到
            if (receiverBean.isClicked()){
                presenter.getMyData();//获取我的个人数据
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observable.deleteObserver(this);
    }
}
