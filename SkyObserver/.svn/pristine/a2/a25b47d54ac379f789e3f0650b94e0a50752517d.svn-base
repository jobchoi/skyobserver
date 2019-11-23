package com.example.skyobserver.msmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.skyobserver.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MeasuringStation extends AsyncTask<String, Void, ArrayList<MStation>> {

    ProgressDialog progressDialog;
    Activity activity;
    boolean isConnection = false;


    String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?pageNo=1&numOfRows=999&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";


    ArrayList<MStation> data = new ArrayList<>();

    public MeasuringStation(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "Reading", "데이터 수신중...");
        super.onPreExecute();
    }

    @Override
    protected ArrayList<MStation> doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        try {

            String jsonPage = getStringFromUrl(url);

            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("list");

            for (int i = 0; i < jArr.length(); i++) {

                json = jArr.getJSONObject(i);


                String addr = json.getString("addr");
               // String map = json.getString("map");
                //String oper = json.getString("oper");
                String photo = json.getString("photo");
                String stationName = json.getString("stationName");
                String dmX = json.getString("dmX");
                String dmY = json.getString("dmY");


/*

                    String url2 = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" + stationName + "&dataTerm=daily&pageNo=1&numOfRows=3&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&ver=1.3&_returnType=json";

                    String jsonPage2 = getStringFromUrl(url2);

                    JSONObject json2 = new JSONObject(jsonPage2);
                    JSONArray jArr2 = json2.getJSONArray("list");

                    json2 = jArr2.getJSONObject(2);


                    String pm10Value = json2.getString("pm10Value");
                    String pm10Value24 = json2.getString("pm10Value24");
                    String pm25Value = json2.getString("pm25Value");
                    String pm25Value24 = json2.getString("pm25Value24");
                    String pm10Grade = json2.getString("pm10Grade");
                    String pm25Grade = json2.getString("pm25Grade");
                    String pm10Grade1h = json2.getString("pm10Grade1h");
                    String pm25Grade1h = json2.getString("pm25Grade1h");

                    //Log.d("Product", "=======" + dmX);


                    MStation mStation = new MStation( addr,  photo,  stationName,  dmX,  dmY,    pm10Value,  pm10Value24,  pm25Value,  pm25Value24,  pm10Grade,  pm25Grade,  pm10Grade1h,  pm25Grade1h);
*/
                MStation mStation = new MStation( addr,  photo,  stationName,  dmX,  dmY);

                    this.data.add(mStation);

                    isConnection = true;

            }


        } catch (Exception e) {
            e.printStackTrace();
            isConnection = true;
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
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).updateProducts(data);
            }
            progressDialog.dismiss();
        } else {
            progressDialog.dismiss();
        }
    }
}
