package com.example.skyobserver.iot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import java.net.URL;

public class PIcam extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picam);

        myWebView = findViewById(R.id.web_view);


        // WebView의 설정
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String address = "192.168.0.13:8083/index.html";
        if(address.startsWith("http://") == false){
            address = "http://" + address;
        }
        myWebView.loadUrl(address);
    }


    @Override
    public void onBackPressed () {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
//            case R.id.action_back:
//                // 뒤로 가기
//                if (myWebView.canGoBack()) {
//                    myWebView.goBack();
//                }
//                break;
//            case R.id.action_refresh:
//                // 새로 고침
//                myWebView.reload();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        if( v.getId() == R.id.doorBtn){
            Toast.makeText(this, "btn click", Toast.LENGTH_SHORT).show();
        }
    }
}
