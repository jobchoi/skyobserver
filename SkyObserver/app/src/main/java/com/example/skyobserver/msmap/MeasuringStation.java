package com.example.skyobserver.msmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.skyobserver.Common;
import com.example.skyobserver.Intro;
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

public class MeasuringStation extends AsyncTask<String, Integer, ArrayList<MStation>> {
    ProgressDialog progressDialog;
    Activity activity;
    boolean isConnection = false;
    TextView textView;
    ProgressBar progressBar;

    //String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?pageNo=1&numOfRows=999&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";
    String url = Common.SERVER_URL + "/allstationmeasure";

    ArrayList<MStation> data = new ArrayList<>();

    public MeasuringStation( ) {
    }
    public MeasuringStation(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog=new ProgressDialog(activity);
//         progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//         progressDialog.setMessage(" 데이터 읽어 오는중");
//         progressDialog.show();
        super.onPreExecute();
      //  progressDialog = ProgressDialog.show(activity, "Reading", "데이터 수신중...");
       //progressBar=activity.findViewById(R.id.textprog);

        if (activity instanceof Intro) {
            progressBar= activity.findViewById(R.id.introprog);
            progressBar.setProgress(0);
            textView= activity.findViewById(R.id.textprog);
        }



    }

    @Override
    protected ArrayList<MStation> doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        try {

            String jsonPage = getStringFromUrl(url);

            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("smlist");

            if (activity instanceof Intro) {
                progressBar.setMax(jArr.length());
            }

            for (int i = 0; i < jArr.length(); i++) {

                if (activity instanceof Intro) {
                    publishProgress(i);
                }

                json = jArr.getJSONObject(i);


                String stationName = json.getString("stationName");
                // String map = json.getString("map");
                //String oper = json.getString("oper");
                //String photo = json.getString("photo");
                String pm10value = json.getString("pm10value");
                String pm25value = json.getString("pm25value");
                String dmX = json.getString("dmX");
                String dmY = json.getString("dmY");
                String addr = json.getString("addr");
                String pm10Grade1h = null;
                String pm25Grade1h = null;


                if (pm10value.equals("-")) {
                    pm10Grade1h = "-";
                } else {

                    if (0 <= Integer.parseInt(pm10value) && 30 >= Integer.parseInt(pm10value)) {
                        pm10Grade1h = "1";
                    } else if (30 < Integer.parseInt(pm10value) && 80 >= Integer.parseInt(pm10value)) {
                        pm10Grade1h = "2";
                    } else if (80 < Integer.parseInt(pm10value) && 150 >= Integer.parseInt(pm10value)) {
                        pm10Grade1h = "3";
                    } else {
                        pm10Grade1h = "4";
                    }
                }

                if (pm25value.equals("-")) {
                    pm25Grade1h = "-";
                } else {

                    if (0 <= Integer.parseInt(pm25value) && 15 >= Integer.parseInt(pm25value)) {
                        pm25Grade1h = "1";
                    } else if (15 < Integer.parseInt(pm25value) && 35 >= Integer.parseInt(pm25value)) {
                        pm25Grade1h = "2";
                    } else if (35 < Integer.parseInt(pm25value) && 75 >= Integer.parseInt(pm25value)) {
                        pm25Grade1h = "3";
                    } else {
                        pm25Grade1h = "4";
                    }
                }
                MStation mStation = new MStation(addr, stationName, dmX, dmY, pm10value, pm25value, pm10Grade1h, pm25Grade1h);
                this.data.add(mStation);
                isConnection = true;
                Thread.sleep(1);
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
            textView.setText("측정소 데이터 가져오는중 ");
        }else{
            progressBar.setProgress(progressBar.getMax());
            textView.setText("측정소 데이터 로딩 완료 ");
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
    protected void onPostExecute(ArrayList<MStation> data) {


        super.onPostExecute(data);

        if (isConnection) {
            if (activity instanceof Intro) {
                ((Intro) activity).updateProducts(data);
                ((Intro) activity).gpsdataget(data);


            }

           // progressDialog.dismiss();
        } else {
          //  progressDialog.dismiss();
        }
    }
}
