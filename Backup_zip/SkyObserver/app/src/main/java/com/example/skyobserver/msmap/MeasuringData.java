package com.example.skyobserver.msmap;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.skyobserver.MainActivity;
import com.example.skyobserver.MapsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class MeasuringData extends AsyncTask<String,Void,MData> {

    boolean isConnection = false;
   // ProgressDialog progressDialog;
    Activity activity;
    MData data=new MData();

    public MeasuringData(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MData doInBackground(String...String) {

        String url ="http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+String[0].toString()+"&dataTerm=month&pageNo=1&numOfRows=1&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&ver=1.3&_returnType=json";
        StringBuffer sb = new StringBuffer();
        try {
            String jsonPage = getStringFromUrl(url);

            JSONObject json = new JSONObject(jsonPage);

            JSONArray jArr = json.getJSONArray("list");


            json = jArr.getJSONObject(0);

            String dataTime =json.getString("dataTime");
            String  mangName=json.getString("mangName");
            String  so2Value=json.getString("so2Value");
            String coValue =json.getString("coValue");
            String  o3Value=json.getString("o3Value");
            String  no2Value=json.getString("no2Value");
            String  pm10Value=json.getString("pm10Value");
            String  pm10Value24=json.getString("pm10Value24");
            String  pm25Value=json.getString("pm25Value");
            String  pm25Value24 =json.getString("pm25Value24");
            String  khaiValue=json.getString("khaiValue");
            String  khaiGrade=json.getString("khaiGrade");
            String  so2Grade=json.getString("so2Grade");
            String  coGrade=json.getString("coGrade");
            String  o3Grade=json.getString("o3Grade");
            String  no2Grade=json.getString("no2Grade");
            String  pm10Grade=json.getString("pm10Grade");
            String  pm25Grade=json.getString("pm25Grade");
            String  pm10Grade1h=json.getString("pm10Grade1h");
            String pm25Grade1h =json.getString("pm25Grade1h");

            MData mData = new MData(dataTime, mangName, so2Value,coValue,  o3Value,  no2Value, pm10Value,  pm10Value24,  pm25Value,  pm25Value24,  khaiValue,  khaiGrade,  so2Grade,  coGrade,  o3Grade,  no2Grade,  pm10Grade,  pm25Grade,  pm10Grade1h,  pm25Grade1h) ;
            this.data=mData;
            isConnection = true;


        } catch (Exception e) {
            e.printStackTrace();
            isConnection = false;
        }
        return data;
//        return sb.toString();


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
    protected void onPostExecute(MData data) {
        super.onPostExecute(data);


        if (isConnection) {
         //   progressDialog.dismiss();
            if (activity instanceof MapsActivity) {
                ((MapsActivity) activity).secondLoading(data);

            }
        } else {
          //  progressDialog.dismiss();
        }


    }
}
