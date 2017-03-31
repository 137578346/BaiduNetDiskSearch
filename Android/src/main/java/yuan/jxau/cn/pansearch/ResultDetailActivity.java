package yuan.jxau.cn.pansearch;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 编程只服JAVA on 2017.03.12.
 */

public class ResultDetailActivity extends Activity{
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initUI();
    }

    private void initUI() {
        String mUrl = getIntent().getStringExtra("mUrl");

        mWebView = (WebView) findViewById(R.id.wv_result);
        mWebView.loadUrl(mUrl);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);// 添加缩放按钮
        webSettings.setUseWideViewPort(true);// 支持双击缩放
        webSettings.setJavaScriptEnabled(true);// 支持js

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("网页开始加载！！！");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束！！！");
            }

            // 所有链接跳转都会走此方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            // 进度发生变化
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                System.out.println("进度" + newProgress);
            }

            // 网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                System.out.println("标题" + title);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
    }
}
