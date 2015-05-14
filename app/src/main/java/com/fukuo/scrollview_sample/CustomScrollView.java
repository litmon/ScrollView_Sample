package com.fukuo.scrollview_sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by fukuo on 2015/05/15.
 */
public class CustomScrollView extends ScrollView {

    LinearLayout layout;
    OnScrollChangedListener mListener;

    public interface OnScrollChangedListener {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public CustomScrollView(Context context) {
        super(context);

        initViews();
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews();
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initViews();
    }

    private void initViews() {
        layout = new LinearLayout(getContext());
        setDefaultSettings(layout);

        super.addView(layout);
    }

    private void setDefaultSettings(LinearLayout layout) {
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        layout.setLayoutParams(params);
    }

    @Override
    public void addView(View child) {
        layout.addView(child);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mListener != null) {
            mListener.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
