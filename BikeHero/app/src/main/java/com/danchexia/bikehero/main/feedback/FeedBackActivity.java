package com.danchexia.bikehero.main.feedback;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.car.qijia.thinker.photo.view.ImageSelectorActivity;
import com.google.gson.Gson;
import com.danchexia.bikehero.R;
import com.danchexia.bikehero.config.ActivityController;
import com.danchexia.bikehero.config.Config;
import com.danchexia.bikehero.main.feedback.adapter.ChoosePhotoAdapter;
import com.danchexia.bikehero.main.feedback.adapter.QuestionAdapter;
import com.danchexia.bikehero.main.feedback.bean.FeedBackUpLoadBean;
import com.danchexia.bikehero.main.feedback.bean.FeedbackTypeListBean;
import com.danchexia.bikehero.main.feedback.bean.FeedbackTypeListData;
import com.danchexia.bikehero.service.GetBuleStatusThread;
import com.danchexia.bikehero.utils.DialogUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vc.thinker.mvp.MvpActivity;
import vc.thinker.tools.dialog.StanderdDialog;
import vc.thinker.tools.utils.LogUtils;
import vc.thinker.tools.utils.ShowToast;
import vc.thinker.tools.utils.Utils;
import vc.thinker.tools.views.MyGridView;

/**
 * Created by farley on 17/5/22.
 * description:
 */

public class FeedBackActivity extends MvpActivity<FeedBackPresenter, IFeedBackView> implements IFeedBackView, View.OnClickListener {
    private FeedBackPresenter presenter;
    private ImageView head_left, head_right;
    private TextView head_title;
    private MyGridView gridview_item;
    private ImageView scan_code;
    private EditText syscode;
    private EditText edt_desc;
    private RecyclerView mRecyclerView;
    private QuestionAdapter adapter;
    private List<FeedbackTypeListData> questionStrList = new ArrayList<>();
    private int chooseOne = 0;
    private ChoosePhotoAdapter chooseAdapter;
    private List<String> imgArray = new ArrayList<>();//照片
    private String typeQuestion = "1";//默认是一般问题
    private String codeQuestion;//车辆的code
    private TextView complete;
    private Long questionCode = -1L;
    private Long tripId = -1L;
    private String desc;
    private FeedBackUpLoadBean bean = new FeedBackUpLoadBean();
    private LinearLayout blue_ll_location;//关于蓝牙的定位
    private TextView desc_v_txt1;
    private TextView desc_v_txt2;
    private TextView desc_v_txt3;
    private TextView blueLockStatus;
    private TextView blueStatus;
    private Button resetSeach;
    private Integer tripOpenType;
    private LinearLayout exception_ll,normal_ll;
    private boolean needUpPhoto = false;//是否需要上传照片
    private String stopTypeStr = "null";
    private int previousPosition = 0;//点击更多时，上次的电机的item
    private TextView tv_textcount,tv_imgcount;

    @Override
    protected FeedBackPresenter CreatePresenter() {
        return presenter = new FeedBackPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        typeQuestion = getIntent().getStringExtra("TYPE");
        codeQuestion = getIntent().getStringExtra("SYSCODE");
        tripId = getIntent().getLongExtra("TRIPID", -1);
        tripOpenType = getIntent().getIntExtra("TRIPOPENTYPE",-1);
        stopTypeStr = getIntent().getStringExtra("PARKTYPE");
        initView();
        bean.setBluetoothConnection(1);//默认1

        //只需要获取一次数据就可以
        if (Config.HAVETRIPING) {
            if (!TextUtils.isEmpty(typeQuestion)) {
                presenter.getFeedBackTypeList(typeQuestion);//获取问题列表
            } else {
                presenter.getFeedBackTypeList("1");//获取问题列表
            }
        } else {
            presenter.getFeedBackTypeList("1");//获取问题列表
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toCloseThis = true;

    }
    private boolean toCloseThis = false;
    @Override
    protected void onPause() {
        super.onPause();
        if (toCloseThis) {
            finish();
        }
    }

    //没有未支付的行程
    public void setNoPayTrip() {

    }
    public List<Bitmap> getImageList() {
        List<Bitmap> bitmaps = new ArrayList<>();
        if (imgArray.size() > 0) {
            for (String url : imgArray) {
                File file = new File(url);
                Bitmap bitmap = Utils.getBitmapFromFile(file, 1000, 1000);
                LogUtils.d("bitmap=" + bitmap);
                if (bitmap != null) {
                    bitmaps.add(bitmap);
                }
            }
            return bitmaps;
        }
        return null;
    }

    public void initData(FeedbackTypeListBean feedbackTypeListBean) {
        questionStrList.clear();
        if (feedbackTypeListBean != null) {
            questionStrList.addAll(feedbackTypeListBean.getDatas());
        }
        setQuestionList();
        setPhoto();
    }

    private void initView() {
        blue_ll_location = (LinearLayout) findViewById(R.id.blue_ll_location);
        exception_ll = (LinearLayout) findViewById(R.id.exception_ll);
        normal_ll = (LinearLayout) findViewById(R.id.normal_ll);
        complete = (TextView) findViewById(R.id.complete);
        resetSeach = (Button) findViewById(R.id.resetSeach);
        scan_code = (ImageView) findViewById(R.id.scan_code);
        edt_desc = (EditText) findViewById(R.id.edt_desc);
        syscode = (EditText) findViewById(R.id.syscode);
        desc_v_txt1 = (TextView) findViewById(R.id.desc_v_txt1);
        desc_v_txt2 = (TextView) findViewById(R.id.desc_v_txt2);
        desc_v_txt3 = (TextView) findViewById(R.id.desc_v_txt3);
        blueLockStatus = (TextView) findViewById(R.id.blueLockStatus);
        blueStatus = (TextView) findViewById(R.id.blueStatus);
        gridview_item = (MyGridView) findViewById(R.id.gridview_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_receycleview);
        head_title = (TextView) findViewById(R.id.head_title);
        head_left = (ImageView) findViewById(R.id.head_left);
        head_right = (ImageView) findViewById(R.id.head_right);
        tv_textcount = (TextView) findViewById(R.id.tv_textcount);
        tv_imgcount = (TextView) findViewById(R.id.tv_imgcount);

        head_title.setText(Utils.object2String(getString(R.string.feedback_title)));
        head_right.setVisibility(View.GONE);
        head_left.setOnClickListener(this);
        scan_code.setOnClickListener(this);
        complete.setOnClickListener(this);
        edt_desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                desc = s.toString();
                if (s.length() > 0 && !TextUtils.isEmpty(syscode.getText().toString().trim()) && questionCode > -1) {
                    complete.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //记录输入的字数
                int count =s.length();
                tv_textcount.setText((150-count)+"");
            }
        });
        if (!TextUtils.isEmpty(codeQuestion)) {
            syscode.setText(codeQuestion);
            syscode.setEnabled(false);
            scan_code.setEnabled(false);
        }
        if (Config.isOpenBattery) {
            syscode.setHint(getString(R.string.feedback_bike_code_battaery));
        }
        String desc1 = "3、若您已在单车附近，点击<font color=\"#000000\">【重新搜索】</font>重新搜索蓝牙信息；";
        String desc2 = "4、已到单车附近并有重新搜索的操作，还是无法解决，请您尝试重启蓝牙或重新打开应用，点击<font color=\"#000000\">【重新搜索】</font>重新搜索蓝牙信息；";
        String desc3 = "2、关锁后您可点击<font color=\"#000000\">【重新搜索】</font>重新搜索蓝牙信息；";
        desc_v_txt1.setText(Html.fromHtml(desc1));
        desc_v_txt2.setText(Html.fromHtml(desc2));
        desc_v_txt3.setText(Html.fromHtml(desc3));
        resetSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBlueStatusThread();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left:
                finish();
                break;
            case R.id.scan_code:
                ActivityController.startFeedBackScanReturnData(this);
                toCloseThis = false;
                break;
            case R.id.complete://需求是锁在开的状态下才必须上传照片 胡阳    2017-12-5易晓梅 修改需求  只要是蓝牙锁就上传照片
                if (TextUtils.isEmpty(desc)) {
                    ShowToast.show(this, getString(R.string.feedback_toast1));
                } else if (TextUtils.isEmpty(syscode.getText().toString().trim())) {
                    ShowToast.show(this, getString(R.string.feedback_toast2));
                } else if (questionCode < 0) {
                    ShowToast.show(this, getString(R.string.feedback_toast3));
                } else if (imgArray.size() == 0 && needUpPhoto){
                    ShowToast.show(this, "请上传照片留证");
                }else {
                    if (tripId > 0) {
                        bean.setTripId(tripId);
                    }
                    bean.setFeedDesc(desc);
                    bean.setSysCode(syscode.getText().toString().trim());
                    bean.setTypeId(String.valueOf(questionCode));
                    if (imgArray.size() > 0) {
                        bean.setImgUrl1(imgArray.get(0));
                    } else if (imgArray.size() > 1) {
                        bean.setImgUrl1(imgArray.get(0));
                        bean.setImgUrl2(imgArray.get(1));
                    } else if (imgArray.size() > 2) {
                        bean.setImgUrl1(imgArray.get(0));
                        bean.setImgUrl2(imgArray.get(1));
                        bean.setImgUrl3(imgArray.get(2));
                    } else if (imgArray.size() > 3) {
                        bean.setImgUrl1(imgArray.get(0));
                        bean.setImgUrl2(imgArray.get(1));
                        bean.setImgUrl3(imgArray.get(2));
                        bean.setImgUrl4(imgArray.get(3));
                    }
                    presenter.feedback(bean);
                }
                break;
            default:
                break;
        }
    }

    //设置问题类型
    private void setQuestionList() {
        if (questionStrList.size() > 5) {
            FeedbackTypeListData data = new FeedbackTypeListData();
            data.setTypeName(getString(R.string.feedback_qusetion_item6));
            questionStrList.add(5, data);
        }
        adapter = new QuestionAdapter(this, questionStrList);
        gridview_item.setAdapter(adapter);
        if (questionStrList.size() > 0 ) {
            adapter.setSelected(0);//无论是否有车辆编号都默认选择第一个问题
            selectAfter(0);
        }
        adapter.setOnMySelectedListener(new QuestionAdapter.OnMySeletcedListener() {
            @Override
            public void onSelected(int position) {
                if (position < 5) {
                    previousPosition = position;
                }
                adapter.setSelected(position);
                if (questionStrList.size() > 5 && position == 5) {
                    Gson gson = new Gson();
                    FeedbackTypeListBean myBean = new FeedbackTypeListBean(questionStrList);

                    String strList = gson.toJson(myBean);
                    Intent it = new Intent(FeedBackActivity.this, ChooseMoreQuestionActivity.class);
                    it.putExtra("POSITION", chooseOne);
                    it.putExtra("STRLIST", strList);
                    startActivityForResult(it, 199);
                }
                selectAfter(position);
            }
        });
    }
    //选择问题后的逻辑
    private void selectAfter(int position) {
        questionCode = questionStrList.get(position).getId();
        if (questionStrList.get(position).getCheckLock() != null && questionStrList.get(position).getCheckLock()
                && (tripOpenType == 2 || tripOpenType == 4)) {
            LogUtils.d("=====================checklock="+questionStrList.get(0).getCheckLock() + "tripOpenType="+tripOpenType);
            needUpPhoto = true;
            blue_ll_location.setVisibility(View.VISIBLE);
            getBlueStatusThread();
        } else {
            needUpPhoto = false;
            blue_ll_location.setVisibility(View.GONE);
        }
    }

    //设置照片
    private void setPhoto() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        chooseAdapter = new ChoosePhotoAdapter(this, imgArray);
        chooseAdapter.setOnItemClickLitener(new ChoosePhotoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                toCloseThis = false;
                if (position == imgArray.size()) {
                    ImageSelectorActivity activty = new ImageSelectorActivity();
                    activty.start(FeedBackActivity.this, 4 - imgArray.size(), ImageSelectorActivity.MODE_MULTIPLE, true, true, false);
                } else {
                   /* Intent review = new Intent(AssignUpActivity.this, PhotoReviewActivity.class);
                    review.putStringArrayListExtra("IMGLIST", (ArrayList<String>) imgArray);
                    review.putExtra("POSITION",  position);
                    startActivityForResult(review,1000);*/
                }
            }
        });
        mRecyclerView.setAdapter(chooseAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int listCount =adapter.getCount();//显示的item个数
        if (resultCode == 199) {
            if (data != null) {
                //Long str = data.getLongExtra("MOREQUESTION", -1L);
                int posSelected = data.getIntExtra("MOREPOSITION", 0);
                chooseOne = posSelected;
                previousPosition =posSelected + listCount ;
                //questionCode = str;

                //点击完成后执行业务逻辑
                selectAfter(posSelected);
                //为adapter设置返回的数据
                adapter.setResultPosition(posSelected + listCount);
                //让最后一个高亮
                adapter.setSelected(listCount - 1);
                adapter.notifyDataSetChanged();

            }
        }

        if (resultCode == 1990) {//没有选择更多问题返回，此时仍以之前选择的item的id为准

            selectAfter(previousPosition);
            //当选择更多问题后，再点击更多问题，直接返回，要按照上次选择的更多问题为主
            if(previousPosition > listCount-1){
                adapter.setSelected(listCount-1);
            }else {
                adapter.setSelected(previousPosition);
            }
        }

        if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            if (images.size() > 0) {
                LogUtils.d("size=" + images.size());
                imgArray.addAll(images);
                if (chooseAdapter != null) {
                    chooseAdapter.notifyDataSetChanged();
                }
            }
            //显示选择照片的个数
            tv_imgcount.setText("上传照片("+images.size()+"/4)");
        }
        if (requestCode == 5) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    String codes = "";
                    if (result.length() > 8) {
                        codes = result.substring(result.length() - 8);//截取后八位
                        if (isNumber(codes)) {//为数字
                            codeQuestion = codes;
                            syscode.setText(codeQuestion);
                        } else {
                            ShowToast.show(FeedBackActivity.this, getString(R.string.feedback_toast4));
                        }
                    } else {
                        ShowToast.show(FeedBackActivity.this, getString(R.string.feedback_toast4));
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ShowToast.show(this, getString(R.string.feedback_toast4));
                }
            }
        }
        if (requestCode == 403 && resultCode == RESULT_OK) {
            getBlueStatusThread();
        }
    }

    public void setFinishIt(){
        LogUtils.d("==================问题反馈，关锁了=============");
        if (null != stopTypeStr && !stopTypeStr.equals("fixed_point")) {
            setResult(RESULT_OK);
            finish();
        }
    }


    //    判断是否为数字的
    private boolean isNumber(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private  StanderdDialog dialog;
    private void getBlueStatusThread(){
        DialogUtils.showWaitText(this);

        GetBuleStatusThread myBlueToothThread = new GetBuleStatusThread(this);
        myBlueToothThread.setOnCloseListener(new GetBuleStatusThread.OnCloseListener() {
            @Override
            public void onClose() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.successWaitDialogText();
                        blueLockStatus.setText("关");
                        bean.setLockOnoff(false);
                        blueStatus.setText("正常");
                        blueStatus.setTextColor(getResources().getColor(R.color.color_aux_gray));
                        normal_ll.setVisibility(View.GONE);
                        exception_ll.setVisibility(View.GONE);
                        setFinishIt();
                    }
                });

            }

            @Override
            public void onopen() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.successWaitDialogText();
                        bean.setLockOnoff(true);
                        blueLockStatus.setText("开");
                        blueLockStatus.setTextColor(getResources().getColor(R.color.color_tip_red));
                        blueStatus.setText("正常");
                        blueStatus.setTextColor(getResources().getColor(R.color.color_aux_gray));
                        normal_ll.setVisibility(View.VISIBLE);
                        exception_ll.setVisibility(View.GONE);
                    }
                });

            }

            @Override
            public void onException() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.successWaitDialogText();
                        bean.setBluetoothConnection(2);
                        blueStatus.setText("异常");
                        blueStatus.setTextColor(getResources().getColor(R.color.color_tip_red));
                        normal_ll.setVisibility(View.GONE);
                        exception_ll.setVisibility(View.VISIBLE);
                    }
                });

            }

            @Override
            public void onTimeOut() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.successWaitDialogText();
                        bean.setBluetoothConnection(3);
                        blueStatus.setText("超时");
                        blueStatus.setTextColor(getResources().getColor(R.color.color_tip_red));
                        normal_ll.setVisibility(View.GONE);
                        exception_ll.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void show(final BluetoothAdapter mBluetoothAdapter) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.successWaitDialogText();
                        if (dialog == null) {
                            dialog = new StanderdDialog(FeedBackActivity.this, "无法连接蓝牙, 请打开您的手机蓝牙后重试!", "知道了",
                                    new StanderdDialog.OnDialogClickListener() {
                                        @Override
                                        public void doAnyClick() {
                                        }

                                        @Override
                                        public void doMainClick() {
                                            toCloseThis = false;
                                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                            startActivityForResult(enableBtIntent,403);
                                            dialog.dismiss();
                                        }
                                    });
                        }else {
                            if (!dialog.isShowing()) {
                                dialog.show();
                            }
                        }
                    }
                });

            }
        });
        myBlueToothThread.start();
    }


}
