package com.example.george.myapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.util.Log;

/*
Target device: Nexus_5_API_23_x86 [emulator-5554]
Installing APK: C:\Users\George\AndroidStudioProjects\MyApplication\RMApp\build\outputs\apk\RMApp-debug.apk
Uploading file to: /data/local/tmp/com.example.george.myapplication
Installing com.example.george.myapplication
DEVICE SHELL COMMAND: pm install -r "/data/local/tmp/com.example.george.myapplication"
	pkg: /data/local/tmp/com.example.george.myapplication
Success


Launching application: com.example.george.myapplication/com.example.george.myapplication.MainActivity.
DEVICE SHELL COMMAND: am start  -n "com.example.george.myapplication/com.example.george.myapplication.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.example.george.myapplication/.MainActivity }
*/

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private WebView myWebView;

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "XXXX shouldOverrideUrlLoading: " + url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG, "XXXX onPageStarted: " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.i(TAG, "XXXX onPageFinished: " + url);
        }
    }

    /** Opens the URL in a browser */
    private void openURL() {
        //webView.loadUrl("http://www.google.com");
        myWebView.loadUrl("http://www.rebeccaminkoff.com");
        myWebView.requestFocus();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // load webpage view
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        openURL();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mail this selection to ...\t\t\t\t © 2015 8SHX Ltd.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
