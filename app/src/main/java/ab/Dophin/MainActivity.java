package ab.Dophin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.ValueCallback;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Build;
import android.app.Activity;
import android.widget.Toast;
import android.content.res.Configuration;
import android.webkit.WebViewClient;
import android.app.DownloadManager;
import android.view.View;
import java.text.SimpleDateFormat;
import java.io.File;
import android.annotation.SuppressLint;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
	private WebSettings webSettings;
	private String link = "http://192.168.16.1:1000/fgtauth?02236a001cfa2337";
//	
	private long backPressedTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.WebView);
        webView.loadUrl(link);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setUserAgentString( webSettings.getUserAgentString() ); // For Desktop side toggle
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
//        
        webView.setWebViewClient(new Callback());
//        
    }
//    @
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
	}
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
	}
    public class Callback extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {   
            Toast.makeText(getApplicationContext(), "Failed loading app!", Toast.LENGTH_SHORT).show();   
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("url")) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } else {
				view.loadUrl(url);
            }
            return true;
        }
	}
//  
}
// © https://github.com/ShivaShirsath
