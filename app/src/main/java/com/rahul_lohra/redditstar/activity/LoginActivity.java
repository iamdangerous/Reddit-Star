package com.rahul_lohra.redditstar.activity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.rahul_lohra.redditstar.R;
import com.rahul_lohra.redditstar.Utility.MyUrl;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    /*
    https://www.reddit.com/api/v1/authorize?client_id=CLIENT_ID&response_type=TYPE&
    state=RANDOM_STRING&redirect_uri=URI&duration=DURATION&scope=SCOPE_STRING
     */

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loadWebView();
    }

    private void loadWebView()
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(MyUrl.LOGIN_AUTHORITY)
                .appendPath("api")
                .appendPath("v1")
                .appendPath("authorize")
                .appendQueryParameter("client_id", MyUrl.CLIENT_ID)
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("state","AnyState")
                .appendQueryParameter("redirect_uri", MyUrl.REDIRECT_URI)
                .appendQueryParameter("duration","permanent")
                .appendQueryParameter("scope",MyUrl.SCOPE);
        String myUrl = builder.build().toString();
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewC(myUrl));
        webView.loadUrl(myUrl);
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);





    }
}
