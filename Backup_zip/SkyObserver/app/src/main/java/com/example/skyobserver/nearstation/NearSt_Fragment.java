package com.example.skyobserver.nearstation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.GeoPoint;
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


public class NearSt_Fragment extends Fragment {

    public NStationAdapter nAdapter;
    GeoPoint tmset;
    RecyclerView recyclerView;
    ArrayList<NStationDTO> data=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.nearst_fragment,container,false);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        recyclerView=getView().findViewById(R.id.recyclerView);

        tmset= MainActivity.tmset;
        String XY[]={String.valueOf(tmset.getX()), String.valueOf(tmset.getY())};
        try {
            new NearStation().execute(XY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        nAdapter=new NStationAdapter(data);
        recyclerView.setAdapter(nAdapter);


    }

    public class NearStation extends AsyncTask<String, Void, ArrayList<NStationDTO>> {

        ArrayList<NStationDTO> data = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NStationDTO> doInBackground(String... strings) {

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX="+strings[0]+"&tmY="+strings[1]+"&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";

            StringBuffer sb = new StringBuffer();
            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("list");

                for (int i = 0; i < jArr.length(); i++) {

                    json = jArr.getJSONObject(i);

                    String stationName = json.getString("stationName");
                    String addr = json.getString("addr");

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

                    NStationDTO nStation = new NStationDTO( stationName,  addr,  pm10Value,  pm10Value24,  pm10Grade,  pm10Grade1h,  pm25Value,  pm25Value24,  pm25Grade,  pm25Grade1h);

                    this.data.add(nStation);
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
        protected void onPostExecute(ArrayList<NStationDTO> data) {
            nAdapter=new NStationAdapter(data);
            recyclerView.setAdapter(nAdapter);

        }
    }
}
