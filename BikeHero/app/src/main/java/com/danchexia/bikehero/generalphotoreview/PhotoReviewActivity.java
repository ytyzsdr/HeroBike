package com.danchexia.bikehero.generalphotoreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danchexia.bikehero.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import vc.thinker.tools.utils.LogUtils;

/**
 * Created by farley on 17/4/11.
 * description:
 */

public class PhotoReviewActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager viewpager;
    private List<String> imgList = new ArrayList<>();
    private TextView back,number,delete;
    private int currentPosition;
    private MyPageAdapter adapter;
    private boolean visiable = true;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_review);
        getImgList();
        initView();
    }

    private void getImgList() {
        Intent it = getIntent();
        if (it != null){
            imgList = it.getStringArrayListExtra("IMGLIST");
            currentPosition = it.getIntExtra("POSITION",0);
            visiable = it.getBooleanExtra("VISIABLE",true);
            LogUtils.d("imgList.size="+imgList.size());
        }
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        number = (TextView) findViewById(R.id.number);
        delete = (TextView) findViewById(R.id.delete);
        number.setText((currentPosition+1)+"/"+imgList.size());
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        viewpager.setOnPageChangeListener(pageChangeListener);

        adapter = new MyPageAdapter(imgList);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(currentPosition);
        if (!visiable){
            delete.setVisibility(View.GONE);
        }
    }
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            currentPosition = arg0;
            number.setText((currentPosition+1)+"/"+imgList.size());
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent it = new Intent();
                it.putStringArrayListExtra("RESULT", (ArrayList<String>) imgList);
                setResult(RESULT_OK,it);
                finish();
                break;
            case R.id.delete:
                if (imgList.size() == 1) {
                    removeImgs();
                    Intent it1 = new Intent();
                    it1.putStringArrayListExtra("RESULT", (ArrayList<String>) imgList);
                    setResult(RESULT_OK,it1);
                    finish();
                } else {
                    removeImg(currentPosition);
                    viewpager.removeAllViews();
                    adapter.removeView(currentPosition);
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
    private void removeImgs() {
        imgList.clear();
    }

    private void removeImg(int location) {
        if (location + 1 <= imgList.size()) {
            imgList.remove(location);
        }
    }
    @Override
    public void onBackPressed() {
        Intent it = new Intent();
        it.putStringArrayListExtra("RESULT", (ArrayList<String>) imgList);
        setResult(RESULT_OK,it);
        finish();
    }

    class MyPageAdapter extends PagerAdapter {
        private List<String> mDataListNew = new ArrayList<String>();
        private ArrayList<PhotoView> mViews = new ArrayList<PhotoView>();

        public MyPageAdapter( List<String> mDataListNew ) {
            this.mDataListNew = mDataListNew;
            int size = mDataListNew.size();
            for (int i = 0; i != size; i++) {
                LayoutInflater inflater=getLayoutInflater();
                View view = inflater.inflate(R.layout.fragment_photo_review, null);
                PhotoView iv = (PhotoView) view.findViewById(R.id.photo_view);
                LogUtils.d("tupian==="+mDataListNew.get(i));
                if (!TextUtils.isEmpty(mDataListNew.get(i))) {
                    Glide.with(PhotoReviewActivity.this).load(mDataListNew.get(i)).into(iv);
                }
                mViews.add(iv);
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public Object instantiateItem(View arg0, int arg1) {
            PhotoView iv = mViews.get(arg1);
            ((ViewPager) arg0).addView(iv);
            return iv;
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            if (mViews.size() >= arg1 + 1) {
                ((ViewPager) arg0).removeView(mViews.get(arg1));
            }
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return mDataListNew.size();
        }

        public void removeView(int position) {
            if (position + 1 <= mViews.size()) {
                mViews.remove(position);
            }
        }

    }
}
