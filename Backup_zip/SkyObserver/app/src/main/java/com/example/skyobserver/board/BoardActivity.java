package com.example.skyobserver.board;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.skyobserver.R;
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
import java.util.List;


public class BoardActivity extends AppCompatActivity {

    BoardAdapter boardAdapter;
    RecyclerView recyclerView;
    ArrayList<BoardDTO> data = new ArrayList<>();
    RequestManager mRequestManager;
    public static final int REQUEST_CODE_RESTART=980;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle("게시판");

        mRequestManager = Glide.with(this);

        recyclerView = findViewById(R.id.boardRecyclerView);


        try {
            new BoadList().execute();
        } catch (Exception e) {
            e.printStackTrace();


        }


        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        boardAdapter = new BoardAdapter(BoardActivity.this, data, mRequestManager);
        recyclerView.setAdapter(boardAdapter);


         FloatingActionButton fab = findViewById(R.id.boardFloatingAction);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), BoardWrite.class);
                startActivityForResult(intent,REQUEST_CODE_RESTART);

            }
        });




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

    public class BoadList extends AsyncTask<String, Void, ArrayList<BoardDTO>> {

        ArrayList<BoardDTO> data = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<BoardDTO> doInBackground(String... strings) {

            String url = "http://192.168.0.14:8081/hanulshop/AndroidProductJsonAction.hanul";

            try {


                String jsonPage8 = getStringFromUrl(url);


                JSONObject json9 = new JSONObject(jsonPage8);
                JSONArray jArr = json9.getJSONArray("BOARD");

                for (int i = 0; i < jArr.length(); i++) {

                    json9 = jArr.getJSONObject(i);


                    String articleno = json9.getString("ARTICLENO");
                    String subject = json9.getString("SUBJECT");
                    String content = json9.getString("CONTENT");
                    String passwd = json9.getString("PASSWD");
                    String reg_date = json9.getString("REG_DATE");
                    String readcount = json9.getString("READCOUNT");
                    String ref = json9.getString("REF");
                    String re_step = json9.getString("RE_STEP");
                    String re_level = json9.getString("RE_LEVEL");
                    String filename = json9.getString("FILENAME");
                    String id = json9.getString("ID");


                    BoardDTO boardDTO = new BoardDTO(articleno, subject, content, passwd, reg_date, readcount, ref, re_step, re_level, filename, id);
                    this.data.add(boardDTO);
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
        protected void onPostExecute(ArrayList<BoardDTO> data) {
            boardAdapter = new BoardAdapter(BoardActivity.this, data, mRequestManager);
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


    private void setAdapter(ArrayList<BoardDTO> list) {
        boardAdapter = new BoardAdapter(BoardActivity.this, data, mRequestManager);
        recyclerView.setAdapter(boardAdapter);
    }

    private void refreshCastAdapter() {
        if (boardAdapter == null) return;
        ArrayList<BoardDTO> list = boardAdapter.getItems();
        setAdapter(list);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_RESTART && resultCode == RESULT_OK){
                Toast.makeText(this, "123123123", Toast.LENGTH_SHORT).show();
                refreshCastAdapter();
                try {
                    new BoadList().execute();
                } catch (Exception e) {
                    e.printStackTrace();}

        }

    }
}
