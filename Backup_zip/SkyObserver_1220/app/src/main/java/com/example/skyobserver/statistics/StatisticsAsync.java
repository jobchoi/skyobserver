package com.example.skyobserver.statistics;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skyobserver.Common;
import com.example.skyobserver.Intro;
import com.example.skyobserver.MainfragementDTO;
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
import java.util.HashMap;

public class StatisticsAsync extends AsyncTask<String, Integer,  HashMap<String,Object>> {
    Activity activity;
    ArrayList<MainfragementDTO> info10 = new ArrayList<>();
    ArrayList<MainfragementDTO> info25 = new ArrayList<>();
    HashMap<String,Object> weekInfo;
    TextView textView;
    ProgressBar progressBar;

    public StatisticsAsync(){}

    public StatisticsAsync(Activity activity){
        this.activity=activity;
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
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (values[0]!=progressBar.getMax()-1) {
            progressBar.setProgress(values[0]);
            textView.setText("통계 데이터 가져오는중 ");
        }else{
            progressBar.setProgress(progressBar.getMax());
            textView.setText("통계 데이터 로딩 완료 ");
                    }


    }

    @Override
    protected HashMap<String,Object> doInBackground(String... strings) {

        String url = Common.SERVER_URL + "/weekavg";
        StringBuffer sb = new StringBuffer();



        try {

            String jsonPage = getStringFromUrl(url);

            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("sido10list");
            JSONArray jArr2 = json.getJSONArray("sido25list");
            if (activity instanceof Intro) {
                progressBar.setMax(jArr.length());
            }

            for (int i = 0; i < jArr.length(); i++) {

                if (activity instanceof Intro) {
                    publishProgress(i);
                }
                json = jArr.getJSONObject(i);


                String datatime = json.getString("datatime");
                String seoul = json.getString("seoul");
                String busan = json.getString("busan");
                String daegu = json.getString("daegu");
                String incheon = json.getString("incheon");
                String gwangju = json.getString("gwangju");
                String daejeon = json.getString("daejeon");
                String ulsan = json.getString("ulsan");
                String gyeonggi = json.getString("gyeonggi");
                String gangwon = json.getString("gangwon");
                String chungbuk = json.getString("chungbuk");
                String chungnam = json.getString("chungnam");
                String jeonbuk = json.getString("jeonbuk");
                String jeonnam = json.getString("jeonnam");
                String gyeongbuk = json.getString("gyeongbuk");
                String gyeongnam = json.getString("gyeongnam");
                String jeju = json.getString("jeju");
                String sejong = json.getString("sejong");


                MainfragementDTO nStation = new MainfragementDTO(datatime,seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

                this.info10.add(nStation);
            }

            for (int i = 0; i < jArr2.length(); i++) {

                json = jArr.getJSONObject(i);


                String datatime = json.getString("datatime");
                String seoul = json.getString("seoul");
                String busan = json.getString("busan");
                String daegu = json.getString("daegu");
                String incheon = json.getString("incheon");
                String gwangju = json.getString("gwangju");
                String daejeon = json.getString("daejeon");
                String ulsan = json.getString("ulsan");
                String gyeonggi = json.getString("gyeonggi");
                String gangwon = json.getString("gangwon");
                String chungbuk = json.getString("chungbuk");
                String chungnam = json.getString("chungnam");
                String jeonbuk = json.getString("jeonbuk");
                String jeonnam = json.getString("jeonnam");
                String gyeongbuk = json.getString("gyeongbuk");
                String gyeongnam = json.getString("gyeongnam");
                String jeju = json.getString("jeju");
                String sejong = json.getString("sejong");


                MainfragementDTO nStation = new MainfragementDTO(datatime,seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

                this.info25.add(nStation);
                Thread.sleep(1);
            }


            weekInfo=new HashMap<>();

            weekInfo.put("week10",info10);
            weekInfo.put("week25",info25);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekInfo;
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
                Log.d("line:", line);
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
    protected void onPostExecute( HashMap<String,Object> weekInfo) {

        //Log.d("순서","          통계 포스트  11");
        new Statistics().getWeekInfo(weekInfo);

//            BarChart chart = findViewById(R.id.barchart);
//            XAxis xAxis = chart.getXAxis();
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            final String[] stations = new String[]{"7일전", "6일전", "5일전", "4일전", "3일전", "2일전", "어제"};
//            IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(stations);
//            xAxis.setGranularity(1f);
//            xAxis.setValueFormatter(formatter);
//
//            Log.d("1111111","==============="+msg);
//
//
//            List<BarEntry> entries = new ArrayList<>();
//            entries.add(new BarEntry(0f, Float.parseFloat(info.get(6).getRegion(msg))));
//            entries.add(new BarEntry(1f, Float.parseFloat(info.get(5).getRegion(msg))));
//            entries.add(new BarEntry(2f, Float.parseFloat(info.get(4).getRegion(msg))));
//            entries.add(new BarEntry(3f, Float.parseFloat(info.get(3).getRegion(msg))));
//            entries.add(new BarEntry(4f, Float.parseFloat(info.get(2).getRegion(msg))));
//            entries.add(new BarEntry(5f, Float.parseFloat(info.get(1).getRegion(msg))));
//            entries.add(new BarEntry(6f, Float.parseFloat(info.get(0).getRegion(msg))));
//
//
//
//
//
//
//
//
//
//            BarDataSet set = new BarDataSet(entries, "BarDataSet");
//
//
//            BarData data = new BarData(set);
//            data.setBarWidth(0.2f); // set custom bar width
//            chart.getXAxis().setLabelCount(7);
//
//            chart.setData(data);
//            chart.setFitBars(true); // make the x-axis fit exactly all bars
//            chart.invalidate(); // refresh



    }
}


