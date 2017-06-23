package com.example.abhi.omconstruction;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by abhi on 23/6/17.
 */

public class Maitree extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefresh;
    ProgressDialog pd;
    WebView mWebViewRewards;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maitree);
        mSwipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh();
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        String url="http://www.maitree.co.in";
        mWebViewRewards =(WebView)findViewById(R.id.web_view_rewards);
        mWebViewRewards.loadUrl(url);
        mWebViewRewards.getSettings().setUseWideViewPort(true);
        mWebViewRewards.getSettings().setLoadWithOverviewMode(true);
        mWebViewRewards.getSettings().setJavaScriptEnabled(true);
        mWebViewRewards.getSettings().setSupportZoom(true);
        mWebViewRewards.getSettings().setBuiltInZoomControls(false);
        mWebViewRewards.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebViewRewards.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebViewRewards.getSettings().setDomStorageEnabled(true);
        mWebViewRewards.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebViewRewards.setScrollbarFadingEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebViewRewards.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebViewRewards.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mWebViewRewards.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
                mSwipeRefresh.setRefreshing(false);
            }
        });


    }
    void swipeRefresh() {
        mSwipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebViewRewards.reload();
            }
        });
    }
}
