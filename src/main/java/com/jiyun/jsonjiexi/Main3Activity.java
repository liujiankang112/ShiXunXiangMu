package com.jiyun.jsonjiexi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Main3Activity extends AppCompatActivity {

    private WebView mMyweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        mMyweb = (WebView) findViewById(R.id.myweb);
        String url = getIntent().getStringExtra("url");
        mMyweb.loadUrl("url");
       // mMyweb.setWebChromeClient(new WebChromeClient());
    }
}
