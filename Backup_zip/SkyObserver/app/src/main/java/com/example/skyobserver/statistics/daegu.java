package com.example.skyobserver.statistics;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.R;
import com.example.skyobserver.mainfragementDTO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

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

public class daegu extends AppCompatActivity {
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        context = this.getBaseContext();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("대구");

        new statistics().execute();

    }



    @Override
    public void onStart() {


        super.onStart();
    }

    public class statistics extends AsyncTask<String, Void, ArrayList<mainfragementDTO>> {

        ArrayList<mainfragementDTO> info = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<mainfragementDTO> doInBackground(String... strings) {

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?itemCode=PM10&dataGubun=DAILY&searchCondition=MONTH&pageNo=1&numOfRows=10&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json)";
            StringBuffer sb = new StringBuffer();
            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("list");

                for (int i = 0; i < jArr.length(); i++) {

                    json = jArr.getJSONObject(i);

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


                    mainfragementDTO nStation = new mainfragementDTO(seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

                    this.info.add(nStation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return info;
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
        protected void onPostExecute(ArrayList<mainfragementDTO> info) {
            BarChart chart = findViewById(R.id.barchart);
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            final String[] stations = new String[]{"7일전", "6일전", "5일전", "4일전", "3일전", "2일전", "어제"};
            IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(stations);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(formatter);

            List<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(0f, Float.parseFloat(info.get(6).getDaegu())));
            entries.add(new BarEntry(1f, Float.parseFloat(info.get(5).getDaegu())));
            entries.add(new BarEntry(2f, Float.parseFloat(info.get(4).getDaegu())));
            entries.add(new BarEntry(3f, Float.parseFloat(info.get(3).getDaegu())));
            entries.add(new BarEntry(4f, Float.parseFloat(info.get(2).getDaegu())));
            entries.add(new BarEntry(5f, Float.parseFloat(info.get(1).getDaegu())));
            entries.add(new BarEntry(6f, Float.parseFloat(info.get(0).getDaegu())));









            BarDataSet set = new BarDataSet(entries, "BarDataSet");


            BarData data = new BarData(set);
            data.setBarWidth(0.2f); // set custom bar width
            chart.getXAxis().setLabelCount(7);

            chart.setData(data);
            chart.setFitBars(true); // make the x-axis fit exactly all bars
            chart.invalidate(); // refresh

        }
    }
}