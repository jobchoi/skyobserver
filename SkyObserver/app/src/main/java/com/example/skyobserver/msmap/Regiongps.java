package com.example.skyobserver.msmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skyobserver.Common;
import com.example.skyobserver.Intro;
import com.example.skyobserver.MainActivity;
import com.example.skyobserver.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Regiongps extends AsyncTask<String, Integer, ArrayList<RegiongpsDTO>> {
    ProgressDialog progressDialog;
    Activity activity;
    boolean isConnection = false;
    TextView textView;
    ProgressBar progressBar;

    String url = Common.SERVER_URL + "/regiongps";

    ArrayList<RegiongpsDTO> data = new ArrayList<>();

    public Regiongps( ) {
    }
    public Regiongps(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (activity instanceof Intro) {
            progressBar= activity.findViewById(R.id.introprog);
            progressBar.setProgress(0);
            textView= activity.findViewById(R.id.textprog);
        }



    }

    @Override
    protected ArrayList<RegiongpsDTO> doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        try {

            String jsonPage = getStringFromUrl(url);

            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("regionGps");

            if (activity instanceof Intro) {
                progressBar.setMax(jArr.length());
            }

            for (int i = 0; i < jArr.length(); i++) {

                if (activity instanceof Intro) {
                    publishProgress(i);
                }

                json = jArr.getJSONObject(i);


                String code = json.getString("code");
                String sido = json.getString("sido");
                String gugun = json.getString("gugun");
                String dong = json.getString("dong");
                String ri = json.getString("ri");
                String x = json.getString("x");
                String y = json.getString("y");
                String wx = json.getString("wx");
                String wy = json.getString("wy");

              RegiongpsDTO regiongpsDTO= new RegiongpsDTO(code,sido,gugun,dong,ri,x,y,wx,wy);
                this.data.add(regiongpsDTO);
                isConnection = true;

            }


        } catch (Exception e) {
            e.printStackTrace();
            isConnection = true;
        }finally {

        }


        return data;


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (values[0]!=progressBar.getMax()-1) {
            progressBar.setProgress(values[0]);
            textView.setText("지역 좌표 데이터 가져오는중 ");
        }else{
            progressBar.setProgress(progressBar.getMax());
            textView.setText("지역 좌표 데이터 로딩 완료 ");

            try {
                Thread.sleep(500);
                textView.setText("Fine Air 를 시작 합니다 ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String getStringFromUrl(String pUrl) {
        BufferedReader bufreader = null;
        HttpURLConnection urlConnection = null;

        StringBuffer page = new StringBuffer();

        try {
            URL url = new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);

            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
            String line = null;

            while ((line = bufreader.readLine()) != null) {
                page.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
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
    protected void onPostExecute(ArrayList<RegiongpsDTO> data) {


        super.onPostExecute(data);
            new MainActivity().Regiongpsdata(data);
    }
}
