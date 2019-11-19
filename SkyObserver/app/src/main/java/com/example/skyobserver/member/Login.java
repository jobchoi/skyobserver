package com.example.skyobserver.member;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
    CharSequence inputStr;
    private boolean flag = false;
    private boolean signServerResut = false;

    String resPhoneStr;
    EditText email;
    EditText pwd;
    public static SiginDTO sDto;

    ImageView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("LOGIN");


        Button button = findViewById(R.id.serverTransmitB);
        Button signup = findViewById(R.id.signup);
        Button sns = findViewById(R.id.sns);

        email = findViewById(R.id.loginEmail);
        pwd = findViewById(R.id.loginPWD);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                Log.d("hasFocus", "!hasFocus");
                // 이메일 유효성 검사
                inputStr = email.getText().toString();
                Log.d("입력값 확인 : ", "입력 email : " + email.getText().toString() );

                Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(inputStr);
                if (matcher.matches()) {
                    Log.d("SignUp", "Email pattern OK");
                    Toast.makeText(Login.this, "ok", Toast.LENGTH_SHORT).show();
                    flag = true;
                } else {
                    Log.d("SignUp", "Email pattern NG");
                    Toast.makeText(Login.this, "ng", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }
            }
        });

        // ---------------- signin 버튼 클릭----------------
        // flag에서 Email 유효성 체크, pwd를 입력 하였으면 서버로 전송.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true && !(pwd.getText().toString().isEmpty())){
                    //String url = Common.SERVER_URL+"/signinand.hanul";  // main
                    String url = Common.SERVER_URL + "/signinand.ob";  // local

                    // DBUG
                    Log.i("sendData-url", url);
                    new SendSignDataTask(Login.this).execute();
                    Toast.makeText(Login.this, "전송버튼 클릭", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Login.this, "빠진항목을 채워주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ---------------- Sign up 버튼 내용 구현----------------
        // 클릭 이벤트시 Listener에서 signup intent로 전환되는 작업을 메스드에서 처리 - 아래에 구현
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("signup-onClick", "click signup");
                signupCaller();
                /*if (flag) {
                } else {
                    Toast.makeText(Login.this, "입력항목을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public class SendSignDataTask extends AsyncTask<Void, Void, SiginDTO> {
        Activity activity;

        public SendSignDataTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
        }

        protected SiginDTO doInBackground(Void... Void) {
            SiginDTO resultsDto = executeSend();
            Log.d("결과값 : ", "EXECUTE 실행" + resultsDto.toString());
            return resultsDto;
        }

        @Override
        // Thread 마치고 수행할 메서드
        protected void onPostExecute(SiginDTO s) {
            super.onPostExecute(s);

            // ((Login)activity).getMainData(s);

            // 클래스 메서드 호출, 메서드는 메서드내에서 쉐어드로 값 저장 기 능 구현. -> 다른 액티비티등에서 사용 할수 있도록 구현.
            Log.d("Share결과 : ", "==========================\n");
            Log.d("SendData", "onPostExecute : " + s);

            if(signServerResut){
                if (resPhoneStr.equals("NG")){
                    Toast.makeText(activity, "Email 또는 passwrod를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public SiginDTO executeSend() {
        ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();

        email = findViewById(R.id.loginEmail);
        pwd = findViewById(R.id.loginPWD);

        post.add(new BasicNameValuePair("EMAIL", email.getText().toString()));
        post.add(new BasicNameValuePair("PWD", pwd.getText().toString()));

        // 연결 HttpClient 객체 생성
        HttpClient client = new DefaultHttpClient();

        // 객체 연결 설정 부분, 연결 최대시간 등등
        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);

        // Post객체 생성
        String url = Common.SERVER_URL + "/signinand.ob";
        HttpPost httpPost = new HttpPost(url);

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");
            httpPost.setEntity(entity);
            HttpResponse responsePOST = client.execute(httpPost);
            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                // 서버로부터 리턴값 확인인
                resPhoneStr = EntityUtils.toString(resEntity);

                if (resPhoneStr.equals("NG")) {
                    Log.d("RESPONSE_확인(NG) :", resPhoneStr);        //  EntityUtils.toString(resEntity) 를 Log.d에서 확인후 다시 JSon에서 사용할시 Error가 발생 -> Log.d에서 확인후 통신대기 또는 버퍼가 클리어되는 상황인듯
                    signServerResut = true;
                } else {
                    Log.d("RESPONSE_확인(OK) :", resPhoneStr);
                    try {
                        JSONObject json2 = new JSONObject(resPhoneStr);
                        JSONArray jArr = json2.getJSONArray("sigininfo");

                        JSONObject json = jArr.getJSONObject(0);

                        sDto = new SiginDTO();
                        sDto.setEmail(json.getString("email"));
                        sDto.setName(json.getString("name"));
                        sDto.setPwd(json.getString("pwd"));
                        sDto.setFilename(json.getString("filename"));

                        Log.i("Json_CHK: ", sDto.toString());

                        //Log.d("RESPONSE확인-1 :",entity.toString());                // 즉 변수에 저장 -> 저장된 변수를 사용하는 식으로 해야됨.

                        saveState(sDto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sDto;
    }

    // signup.setOnClickListener 에서 동작할 indent : Login -> Signup 으로 전환될 intent
    public void signupCaller() {
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }

    protected void saveState(SiginDTO sDto) {
        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

        Log.d("Length ----" ,String.valueOf(sDto.getName().length()));

        if(sDto.getName().equals("null")){
            sDto.setName("별명을 작성해주세요.");

           // Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();

        }
        editor.putString("name", sDto.getName());
        Log.d("-----Login name----",sDto.getName());

        editor.putString("pwd", sDto.getPwd());
        editor.putString("email",sDto.getEmail());
        editor.putString("filename", sDto.getFilename());

        Log.d("editor-DTO확인 : ", sDto.toString());


        editor.commit();
    }

    protected void clearState() {
        SharedPreferences pref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}