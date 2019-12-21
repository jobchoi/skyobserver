package com.example.skyobserver.notics;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.skyobserver.Common;
import com.example.skyobserver.R;
import com.example.skyobserver.board.BoardAdapter;
import com.example.skyobserver.board.BoardWrite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;


public class NoticeActivity extends AppCompatActivity {

    NoticeAdapter boardAdapter;
    RecyclerView recyclerView;
    ArrayList<NoticeDTO> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_layout);


        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle("공지사항");


        recyclerView = findViewById(R.id.noticsRecyclerView);


        try {
            new noticsList().execute();
        } catch (Exception e) {
            e.printStackTrace();


        }


        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        boardAdapter = new NoticeAdapter(NoticeActivity.this, data);
        recyclerView.setAdapter(boardAdapter);






    }



    /*@Override
    public void onItemClicked(int position) {

        Toast.makeText(this, "터치터치", Toast.LENGTH_SHORT).show();

       *//* TextView textView=recyclerView.findViewById(R.id.boardcontent);

        textView.setVisibility(View.VISIBLE);
*//*
        Log.d("progress",data.get(position).toString() );
        BoardDialog bDialog = new BoardDialog(BoardActivity.this);
        bDialog.popBoard(data.get(position));

    }*/

    public class noticsList extends AsyncTask<String, Void, ArrayList<NoticeDTO>> {

        ArrayList<NoticeDTO> data = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NoticeDTO> doInBackground(String... strings) {

            String url = Common.SERVER_URL+"/android/notice";
            try {


                String jsonPage8 = getStringFromUrl(url);


                JSONObject json9 = new JSONObject(jsonPage8);
                JSONArray jArr = json9.getJSONArray("noticsList");

                for (int i = 0; i < jArr.length(); i++) {

                    json9 = jArr.getJSONObject(i);


                    String title = json9.getString("title");
                    String content = json9.getString("content");
                    String writedate = json9.getString("writedate");



                    NoticeDTO noticeDTO=new NoticeDTO(title,content,writedate);

                    this.data.add(noticeDTO);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            return data;
        }

        public String getStringFromUrl(String pUrl) {
            BufferedReader bufreader = null;
            HttpURLConnection urlConnection = null;

            StringBuffer page = new StringBuffer();
            Exception er = null;
            try {


                URL url = new URL(pUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(3000);

                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setDefaultUseCaches(false);

                InputStream contentStream = urlConnection.getInputStream();

                bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
                String line = null;


                while ((line = bufreader.readLine()) != null) {
                    Log.d("line:", line);
                    page.append(line);
                }

            } catch (IOException e) {
                TextView textView = findViewById(R.id.alert1);
                textView.setText("서버를 확인해주세요");
            } finally {


                try {
                    bufreader.close();
                    urlConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return page.toString();
        }

        @Override
        protected void onPostExecute(ArrayList<NoticeDTO> data) {
            boardAdapter = new NoticeAdapter(NoticeActivity.this, data);
            recyclerView.setAdapter(boardAdapter);





        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       /* refreshCastAdapter();
        try {
            new BoadList().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();

    /*    refreshCastAdapter();
        boardAdapter.notifyDataSetChanged();*/
    }


    private void setAdapter(ArrayList<NoticeDTO> list) {
        boardAdapter = new NoticeAdapter(NoticeActivity.this, data);
        recyclerView.setAdapter(boardAdapter);
    }

    private void refreshCastAdapter() {
        if (boardAdapter == null) return;
        ArrayList<NoticeDTO> list = boardAdapter.getItems();
        setAdapter(list);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void reload(){
        refreshCastAdapter();
        try {
            new noticsList().execute();
        } catch (Exception e) {
            e.printStackTrace();}

    }



}
