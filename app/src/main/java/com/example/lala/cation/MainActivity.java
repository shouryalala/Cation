package com.example.lala.cation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public int iloaded = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static final boolean isNotLoggedIn(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://google.com");   // Change to "http://google.com" for www  test.
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(10 * 1000);          // 10 s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.wtf("Connection", "Success !");
                    return true;
                } else {

                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String uname = "140905316";
        final String password = "Q347IV8Z";

        WebView ionwebView = (WebView) findViewById(R.id.webView);
        ionwebView.getSettings().setJavaScriptEnabled(true);
        ionwebView.loadUrl("http://172.16.16.16/logout");
        ionwebView.getSettings().setDomStorageEnabled(true);

        Toast.makeText(getApplicationContext(), "loaded page 1", Toast.LENGTH_SHORT).show();
        Log.d("ion", "scene started");
        ionwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                v.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView v, String url) {

                if (iloaded == 0 || iloaded == 1) {
                    v.getSettings().setDomStorageEnabled(true);
                    v.getSettings().setJavaScriptEnabled(true);

                    v.loadUrl("javascript: {var user = document.getElementsByName('username')[0].value = '" + uname + "';" +
                            "var pass = document.getElementsByName('password')[0].value = '" + password + "';" +
                            "var frm = document.getElementsByName('clientloginform')[0].submit(); };");

                    iloaded++;
                    Log.d("ion", "inner class and iloaded=" + iloaded);
                }
                else Log.d("ion","skipped");
            }
        });


        Toast.makeText(getApplicationContext(), "loaded page 2", Toast.LENGTH_SHORT).show();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lala.cation/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lala.cation/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class ionWebClient extends WebViewClient {
        private String unm, pwd;
        private int iterate;

        ionWebClient(String uname, String pwd) {
            this.unm = uname;
            this.pwd = pwd;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView v, String url) {
            v.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView v, String url) {
            Log.d("ion", "entered ionWebClient");
            if (iterate == 0 || iterate == 1) {
                v.getSettings().setDomStorageEnabled(true);
                v.getSettings().setJavaScriptEnabled(true);
                v.loadUrl("javascript: {var user = document.getElementsByName('username')[0].value = '" + unm + "';" +
                        "var pass = document.getElementsByName('password')[0].value = '" + pwd + "';" +
                        "var frm = document.getElementsByName('clientloginform')[0].submit(); };");
                iterate++;
                Log.d("ion", "ionWebClient class iterate number=" + iterate);
            }
        }

    }
}
