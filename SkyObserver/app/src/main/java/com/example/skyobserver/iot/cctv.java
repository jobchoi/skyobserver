package com.example.skyobserver.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.skyobserver.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class cctv extends AppCompatActivity {

    private WebView myWebView;
    String homeAddr = "192.168.0.19";
    int port = 8001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);

        myWebView = findViewById(R.id.web_view);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String address = "192.168.0.19:8081";
        if(address.startsWith("http://") == false){
            address = "http://" + address;
        }
        myWebView.loadUrl(address);
        SockRasp doorState = new SockRasp(homeAddr, port, "requestHome");
        doorState.execute();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.door1){
//            문1
            Toast.makeText(this, "door1", Toast.LENGTH_SHORT).show();
            SockRasp sockRasp = new SockRasp(homeAddr ,port,"door1");
            sockRasp.execute();


        } else if(view.getId() == R.id.door2){
//            문2 클릭
            Toast.makeText(this, "door2", Toast.LENGTH_SHORT).show();
            SockRasp sockRasp = new SockRasp(homeAddr ,port,"door2");
            sockRasp.execute();
        }
    }

    public class SockRasp extends AsyncTask<Void, Void, Void>{

        Activity activity;
        private Socket socket;
        BufferedReader in;
        PrintWriter out;
        String data;

        // 참고 코드
        String dstAddress;
        int dstPort;
        String response;
        String myMessage;

        SockRasp(String addr, int port, String message){
            dstAddress = addr;
            dstPort = port;
            myMessage = message;
        }




        // 라즈베리와 소켓통신
//        public SockRasp(Activity activity){ this.activity = activity; }

        @Override
        protected Void doInBackground(Void... arg0) {
            // socket
            Socket socket = null;
            myMessage = myMessage.toString();
            try {
                socket = new Socket(dstAddress, dstPort);
                OutputStream out = socket.getOutputStream();
                out.write(myMessage.getBytes());

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];
                int bytesRead;
                InputStream inputStream = socket.getInputStream();

                while((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }
                response = "서버 응답 : " + response;
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("socket","socket - onPostExecute : "+response.toString());
            super.onPostExecute(aVoid);
        }
    }
}
