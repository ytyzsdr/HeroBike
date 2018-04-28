package com.danchexia.bikehero.myviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;

import java.util.ArrayList;
import java.util.List;

import vc.thinker.tools.utils.LogUtils;

/**
 * Created by seal on 16/4/6.
 */
public class SuggestionSearchEditText extends EditText implements OnGetSuggestionResultListener,View.OnFocusChangeListener {
    private SuggestionSearch mSuggestionSearch;
    private OnSuggestionSearchEditTextListener mOnSuggestionSearchEditTextListener;
    private String userCity = "深圳";//当前所在城市
    private String keyWords = "南";//关键字

    public interface OnSuggestionSearchEditTextListener {
        void onResults(List<AddressEntity> result);
    }

    public SuggestionSearchEditText(Context context) {
        super(context);
        init();
    }

    public SuggestionSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuggestionSearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnSuggestionSearchEditTextListener(OnSuggestionSearchEditTextListener listener) {
        mOnSuggestionSearchEditTextListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSuggestionSearch.destroy();
    }

    private void init() {
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        /*SuggestionSearchOption suggestionSearchOption = new SuggestionSearchOption();
        suggestionSearchOption.city(userCity);
        suggestionSearchOption.keyword(keyWords);
        mSuggestionSearch.requestSuggestion(suggestionSearchOption);*/

        addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    return;
                }
                SuggestionSearchOption suggestionSearchOption = new SuggestionSearchOption();
                suggestionSearchOption.city(userCity);
                suggestionSearchOption.keyword(cs.toString());
                mSuggestionSearch.requestSuggestion(suggestionSearchOption);
            }
        });


        setOnFocusChangeListener(this);
    }


    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }

        final List<AddressEntity> addressEntityList = new ArrayList<>();

        List<String> suggest = new ArrayList<String>();
        for (final SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
            if (info.key != null) {
                suggest.add(info.key);

                if(info.pt != null) {
                    final AddressEntity addressEntity = new AddressEntity();
                    GeoHelper geoHelper = new GeoHelper();
                    geoHelper.reverseGeo(info.pt.latitude, info.pt.longitude, new GeoHelper.OnReverseGeoListener() {
                        @Override
                        public void onGetResult(double longitude, double latitude, String poi, String district, String address) {
                            addressEntity.setArea(district); LogUtils.d("district="+district);
                            addressEntity.setName(poi); LogUtils.d("poi="+poi);
                            addressEntity.setAddress(address); LogUtils.d("address="+address);
                            addressEntity.setDistrict(district);
                            addressEntity.setLongitude((float) longitude);
                            addressEntity.setLatitude((float) latitude);
                            addressEntity.setType(0);
                            addressEntityList.add(addressEntity);

                            if(mOnSuggestionSearchEditTextListener != null) {
                                mOnSuggestionSearchEditTextListener.onResults(addressEntityList);
                            }
                        }
                    });
                }
            }
        }
    }



    /**
     * 是否是默认图标再左边的样式
     */
    private boolean isLeft = false;
    /**
     * 是否点击软键盘搜索
     */
    private boolean pressSearch = false;

    @Override
    protected void onDraw(Canvas canvas) {
        if (isLeft) { // 如果是默认样式，则直接绘制
            super.onDraw(canvas);
        } else { // 如果不是默认样式，需要将图标绘制在中间
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableLeft = drawables[0];
            Drawable drawableRight = drawables[2];
            translate(drawableLeft, canvas);
            translate(drawableRight, canvas);
            super.onDraw(canvas);
        }
    }

    public void translate(Drawable drawable, Canvas canvas) {
        if (drawable != null) {
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawable.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            if (drawable == getCompoundDrawables()[0]) {
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            } else {
                setPadding(getPaddingLeft(), getPaddingTop(), (int) (getWidth() - bodyWidth - getPaddingLeft()), getPaddingBottom());
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft()) / 2, 0);
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // 恢复EditText默认的样式
        if (!pressSearch && TextUtils.isEmpty(getText().toString())) {
            isLeft = hasFocus;
        }
    }

    /**
     * 设置所在城市
     * @param city
     */
    public void setUserCity(String city){
        userCity = city;
    }
    /**
     * 设置搜索关键字
     * @param keywords
     */
    public void setUserKeyWords(String keywords){
        keyWords = keywords;
    }
}
