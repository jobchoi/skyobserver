package com.example.skyobserver.member;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Signup extends AppCompatActivity {

    EditText email;

    private byte state = 0x00;
    private boolean flag = false;
    private boolean formatEmail = false;

    private String imageFilePath = null;
    private Uri photoUri;
    File photoFile;
    URL url;
    File imgFile;

    EditText pwd ;
    EditText repwd ;

    ProductWriteAsync pwa;

    String strPwd ;
    String strRePwd;
    String sendUrl = Common.SERVER_URL+"/signupactionand.ob";


    String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
    CharSequence inputStr;

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("회원가입");

        Button signUpButton = findViewById(R.id.buttonEnroll);

        pwd = findViewById(R.id.pwd_my);
        repwd = findViewById(R.id.repwd_my);
        email = findViewById(R.id.email_my);

        // --------------------- 유효성 검사 - Email ---------------------
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    Log.d("hasFocus","!hasFocus");
                    // 이메일 유효성 검사
                    inputStr = email.getText().toString();

                    Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(inputStr);
                    if (matcher.matches()) {
                        Toast.makeText(Signup.this, "ok", Toast.LENGTH_SHORT).show();
                        Log.d("SignUp","Email pattern OK");
                        formatEmail = true;
                        flag = true;
                    } else {
                        Toast.makeText(Signup.this, "Email 형식을 확인해주세요.", Toast.LENGTH_SHORT).show();
                        Log.d("SignUp","Email pattern NG");
                        flag = false;
                        formatEmail = false;
                    }
                } else {
                    //Toast.makeText(Signup.this, "포커스아웃", Toast.LENGTH_SHORT).show();
                    Log.d("SignUp","onFocusChange-else 실행");
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(flag == true && !(email.getText().toString().isEmpty())
                    && !(pwd.getText().toString().isEmpty()) && !(repwd.getText().toString().isEmpty())){
                strPwd = pwd.getText().toString();
                strRePwd = repwd.getText().toString();

                if(comparePwd(strPwd, strRePwd )){
                    Log.d("SignupSendData", "Task 실행");
                    pwa = new ProductWriteAsync();
                    pwa.execute(sendUrl);
                } else {
                    Toast.makeText(Signup.this, "입력한 password가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(formatEmail){
                    Log.d("formatEmail - if",Boolean.toString(formatEmail));
                    Toast.makeText(Signup.this, "빠진항목을 채워주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("formatEmail - else ",Boolean.toString(formatEmail));
                    Toast.makeText(Signup.this, "형식에 맞지 않거나 입력한 passwrod가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }


    private boolean comparePwd(String pwd, String repwd){
        if(repwd.equals(pwd)){
            return true;
        } else {
            return false;
        }
    }

    public class SendSignupDataTaask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... email) {
            executeSend(email);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SendSignup","onPostExcute" +s);
        }

        public  String executeSend(String... email){
            Log.d("Signup DATA ","email[0]"+email[0]);
            return null;
        }
    }

    //        private class ProductWriteAsync extends AsyncTask<String, String, String> {   //  origin
    protected class ProductWriteAsync extends AsyncTask<String, Void, String> {     //test
        @Override
        protected String doInBackground(String... URI) {
            // multipart/form-data 타입으로 전송시 각각의 변수마다 들어가게 되는 문자열(twoHyphens, boundary) 이게 없으면 전송 안됨.
            String twoHyphens = "--";
            String boundary = "*****";
            DataOutputStream dos = null;
            String lindEnd = "\r\n"; // 개행문자
            String http_result_ok = "fail";

            try {
                url = new URL(sendUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false); // 캐쉬 사용 안함.
                conn.setDoOutput(true);

                // POST 방식 설정(파일은 반드시 POST방식으로 전송해야 함)
                conn.setRequestMethod("POST");

                // post 방식으로 넘길 자료 정보 설정(header)
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                // 파일업로드 이므로 다양한 파일 포맷을 지원하기 위해 DataOutputStream 객체 생성
                dos = new DataOutputStream(conn.getOutputStream());

                // 저장할 내용
                //  twoHyphens 부분과 Content-Disposition: 부분을 쌍으로 가져감
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + URLEncoder.encode(email.getText().toString(), "UTF-8") + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"pwd\"\r\n\r\n" + URLEncoder.encode(pwd.getText().toString(), "UTF-8") + lindEnd);

                Log.d("결과 확인 1 : ",email.getText().toString());
                Log.d("결과 확인 2 : ",pwd.getText().toString());

                Log.d("---test--",dos.toString());

                // 여기서 전송 끝 -> 컨트롤러로 감
                dos.writeBytes(lindEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lindEnd);

                Log.d("dos화인 : ",dos.toString());

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    http_result_ok = "200";
                    Log.i("서버통신결과", "성공");

                    // =============== 서버 통신 성공후 결과 확인 ===============

                    try (InputStream in = conn.getInputStream();
                         ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                        byte[] buf = new byte[1024 * 8];
                        int len = 0;
                        while ((len = in.read(buf)) != -1) {
                            out.write(buf, 0, len);
                        }
                        System.out.println("ffffffffffffffffffffffff"+new String(out.toByteArray(), "UTF-8"));
                    }
                    // =============== 서버 통신 성공후 결과 확인 ===============


                } else {
                    Log.i("conn.getResponseCode()", "" + conn.getResponseCode());
                    Log.i("서버통신결과", "실패");
                }

//                    fis.close();  // 통신 테스트 위해 파일 업로드 보류.
                dos.flush();
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return http_result_ok;
        }

        //수정 (2019.10.16)
        @Override
        protected void onPostExecute(String http_result_ok) {
            Log.d("result_ok====> ", http_result_ok);
            if (http_result_ok.equals("200")) {
                Toast.makeText(Signup.this, "등록성공", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(Signup.this, "등록실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
