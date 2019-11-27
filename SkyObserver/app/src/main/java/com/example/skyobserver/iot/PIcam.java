package com.example.skyobserver.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyobserver.R;

public class PIcam extends AppCompatActivity {

    private EditText mAddressEdit;
    private WebView myWebView;
    private Button mMoveButton;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            myWebView = findViewById(R.id.web_view);

            // WebView의 설정
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    Toast.makeText(PIcam.this, "페이지 로딩 시작", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                }
            });

            mAddressEdit = findViewById(R.id.address_edit);
            mMoveButton = findViewById(R.id.move_button);

            mAddressEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mMoveButton.callOnClick();
                        return true;
                    }
                    return false;
                }
            });

            // 소프트 키보드의 돋보기를 클릭했을 때 이동 버튼을 클릭하는 효과
            mAddressEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mMoveButton.callOnClick();

                        // 키보드 숨기기
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        return true;
                    }
                    return false;
                }
            });
        }

        // 이동 버튼을 클릭했을 때의 동작을 정의
        public void onClick (View view){
            String address = mAddressEdit.getText().toString();
            if (address.startsWith("http://") == false) {
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
            inflater.inflate(R.menu.menu_main, menu);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.action_back:
                    // 뒤로 가기
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                    }
                    break;
                case R.id.action_forward:
                    // 앞으로 가기
                    if (myWebView.canGoForward()) {
                        myWebView.goForward();
                    }
                    break;
                case R.id.action_refresh:
                    // 새로 고침
                    myWebView.reload();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
    }
