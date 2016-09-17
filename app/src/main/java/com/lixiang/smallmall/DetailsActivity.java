package com.lixiang.smallmall;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;
    private String url;
    private Button btnCollet;
    private Button btnAdd;
    private TextView tvDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        btnAdd= (Button) findViewById(R.id.add);
        btnCollet= (Button) findViewById(R.id.collect);
        tvDot= (TextView) findViewById(R.id.dot);
        url = getIntent().getExtras().getString("url");
        webView = (WebView) findViewById(R.id.wv_details);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new webViewClient());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences num = getSharedPreferences("buyCar", MODE_PRIVATE);
                int count = num.getInt("num",0);
                tvDot.setText(count+1);
            }
        });
    }

    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

        }

    }
}
