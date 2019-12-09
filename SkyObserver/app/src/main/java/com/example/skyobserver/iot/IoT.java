package com.example.skyobserver.iot;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class IoT extends AppCompatActivity {

    private boolean buttonFlag = false;
    ImageView imageView1 = null;
    IotAsync iotSync;
    String sendUrl = Common.SERVER_URL +"/iotstate";
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot_state);

        Log.i("iot","iot async");
        iotSync = new IotAsync();
        iotSync.execute(sendUrl);

    }

    public void iotState(View view) {
        IoTServiceImpl ism = new IoTServiceImpl(this);

        if(view.getId() == R.id.iotbutton1){
            ism.myToast("led 1", this);
            imageView1 = findViewById(R.id.iotbutton1);
            String str;
            if(buttonFlag){
                imageView1.setImageResource(R.drawable.ledoff);
                buttonFlag = false;
                str = "t";

            }else{
                imageView1.setImageResource(R.drawable.ledon);
                buttonFlag = true;
                str = "f";
            }
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        } else if(view.getId() == R.id.iotbutton2){
            ism.myToast("led 2", this);

        } else if(view.getId() == R.id.iotbutton3){
            ism.myToast("led 3", this);
        } else if(view.getId() == R.id.iotbutton4){
            ism.myToast("led 4",this);
        }  else if(view.getId() == R.id.iotbutton5){
            ism.myToast("led 5",this);
        }  else if(view.getId() == R.id.iotbutton6){
            // picam
            ism.myToast("picam", this);
            ism.homecam();

        }
    }

    protected class IotAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... URI) {
            // multipart/form-data 타입으로 전송시 각각의 변수마다 들어가게 되는 문자열(twoHyphens, boundary) 이게 없으면 전송 안됨.
            String twoHyphens = "--";
            String boundary = "*****";
            DataOutputStream dos = null;
            String lindEnd = "\r\n"; // 개행문자io
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
                dos.writeBytes("Content-Disposition: form-data; name=\"data\r\n\r\n"+ URLEncoder.encode("test", "UTF-8")+lindEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + URLEncoder.encode(email.getText().toString(), "UTF-8") + lindEnd);
//                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
//                dos.writeBytes("Content-Disposition: form-data; name=\"pwd\"\r\n\r\n" + URLEncoder.encode(pwd.getText().toString(), "UTF-8") + lindEnd);

                Log.d("---test--",dos.toString());

                int length = -1;

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

                dos.flush();
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return http_result_ok;
        }

        @Override
        protected void onPostExecute(String http_result_ok) {
//            super.onPostExecute(aVoid);
            if(http_result_ok.equals("200")){

                Toast.makeText(IoT.this, "통신연결 OK", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(IoT.this, "통신연결 NG", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
