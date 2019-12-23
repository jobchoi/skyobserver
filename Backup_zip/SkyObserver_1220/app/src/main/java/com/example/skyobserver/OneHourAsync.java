package com.example.skyobserver;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skyobserver.statistics.CityDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class OneHourAsync extends AsyncTask<String, Integer, ArrayList<CityDTO>> {
        ArrayList<CityDTO> data = new ArrayList<>();
    Activity activity;
    TextView textView;
    ProgressBar progressBar;
        public OneHourAsync(){}

        public OneHourAsync(Activity activity){
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
        protected ArrayList<CityDTO> doInBackground(String... strings) {

            //String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?itemCode=PM" + strings[0].toString() + "&dataGubun=HOUR&searchCondition=WEEK&pageNo=1&numOfRows=10&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";
            String url = Common.SERVER_URL + "/onehouravg ";
            StringBuffer sb = new StringBuffer();
            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("list");
                if (activity instanceof Intro) {
                    progressBar.setMax(jArr.length());
                }
                for (int i = 0; i < jArr.length(); i++) {
                    if (activity instanceof Intro) {
                        publishProgress(i);
                    }
                    json = jArr.getJSONObject(i);

//                    String itemCode = json.getString("itemCode");
//                    String seoul = json.getString("seoul");
//                    String busan = json.getString("busan");
//                    String daegu = json.getString("daegu");
//                    String incheon = json.getString("incheon");
//                    String gwangju = json.getString("gwangju");
//                    String daejeon = json.getString("daejeon");
//                    String ulsan = json.getString("ulsan");
//                    String gyeonggi = json.getString("gyeonggi");
//                    String gangwon = json.getString("gangwon");
//                    String chungbuk = json.getString("chungbuk");
//                    String chungnam = json.getString("chungnam");
//                    String jeonbuk = json.getString("jeonbuk");
//                    String jeonnam = json.getString("jeonnam");
//                    String gyeongbuk = json.getString("gyeongbuk");
//                    String gyeongnam = json.getString("gyeongnam");
//                    String jeju = json.getString("jeju");
//                    String sejong = json.getString("sejong");
//
//                    MainfragementDTO nStation = new MainfragementDTO(itemCode, seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

                    String cityName = json.getString("cityName");
                    String pm10avg = json.getString("pm10avg");
                    String pm25avg = json.getString("pm25avg");
                    String datatime = json.getString("datatime");

                    CityDTO nStation = new CityDTO(cityName, pm10avg, pm25avg, datatime);

                    this.data.add(nStation);
                    Thread.sleep(1);
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
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (values[0]!=progressBar.getMax()-1) {
            progressBar.setProgress(values[0]);
            textView.setText("최근 데이터 가져오는중 ");
        }else{
            progressBar.setProgress(progressBar.getMax());
            textView.setText("최근 데이터 로딩 완료 ");
        }
    }

        @Override
        protected void onPostExecute(ArrayList<CityDTO> data) {
            Main_Fragment mf=new Main_Fragment();
            mf.setOneHourData(data);
          //  Log.d("순서","         한시간통계어싱크 oh post 8");

//            if (data.get(1).getItemCode().equals("10007")) {
//                setPm10(data);
//                setGrade(data);
//            }
//            if (data.get(1).getItemCode().equals("10008")) {
//                setPm25(data);
//            }
        }
    }









