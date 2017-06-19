package com.example.abhi.omconstruction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by abhi on 20/6/17.
 */

public class ProgramDetails extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_details);
        mWebView=(WebView)findViewById(R.id.webview_programDetails);
        mWebView.loadUrl("http://www.maitree.co.in/show/content/Program-Details_1");
        mWebView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
    }

    // Use Whenthe user clicks a link from a web page in your WebView
    private class MyWebViewClient extends WebViewClient {

        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (Uri.parse(url).getHost().equals("www.centerend.com")) {

                return false;

            }


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            startActivity(intent);

            return true;
        }
    }

    }
