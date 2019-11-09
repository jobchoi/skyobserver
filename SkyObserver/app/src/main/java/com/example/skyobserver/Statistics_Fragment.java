package com.example.skyobserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skyobserver.nearstation.NStationAdapter;
import com.example.skyobserver.nearstation.NStationDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Statistics_Fragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.statistics_fragment,container,false);




        return  view;

    }



    public class testst extends AsyncTask<String, Void, String> {

        String data = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String url ="http://192.168.0.14:8081/hanulshop/androidwrite.hanul";

            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                // JSONArray jArr = json.getJSONArray("list");

                // for (int i = 0; i < jArr.length(); i++) {}

                //    json = jArr.getJSONObject(i);

                String stationName = json.getString("gggg");




                //  NStationDTO nStation = new NStationDTO( stationName,  addr,  pm10Value,  pm10Value24,  pm10Grade,  pm10Grade1h,  pm25Value,  pm25Value24,  pm25Grade,  pm25Grade1h);

                this.data=stationName;

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
                JSONObject param=new JSONObject();

                try {
                    param.put("na","dff");
                    param.put("na2","z666v");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("check",param.toString());
                URL url = new URL(pUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setDefaultUseCaches(false);

                OutputStream out_stream = urlConnection.getOutputStream();

                out_stream.write(param.toString().getBytes("UTF-8"));
                out_stream.flush();
                out_stream.close();

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
        protected void onPostExecute(String data) {
            TextView textView=getView().findViewById(R.id.test69);
            textView.setText(data);
        }
    }
}
