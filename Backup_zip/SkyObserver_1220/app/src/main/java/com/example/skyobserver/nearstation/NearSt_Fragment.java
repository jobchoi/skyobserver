package com.example.skyobserver.nearstation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.skyobserver.Intro;
import com.example.skyobserver.MainActivity;
import com.example.skyobserver.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class NearSt_Fragment extends Fragment {
    public static ArrayList<Integer> nearindex;
    public NStationAdapter nAdapter;
    Activity activity;
    // String[] tmset;
    RecyclerView recyclerView;
    ArrayList<NStationDTO> data = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;


    public NearSt_Fragment( ) {
     }
    public NearSt_Fragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.nearst_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);


       // nearindex = MainActivity.nearindex;
        // tmset= MainActivity.tmset;
        //final String XY[]={String.valueOf(tmset.getX()), String.valueOf(tmset.getY())};

        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        nAdapter = new NStationAdapter(data);
        recyclerView.setAdapter(nAdapter);

        mSwipeRefreshLayout = view.findViewById(R.id.nearStationSwipe);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                nAdapter = null;

                try {
                    new NearStation().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

        try {
            new NearStation(activity).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }



    @Override
    public void onResume() {
        super.onResume();



    }

public void getnearindex( ArrayList<Integer> nearindex){
        this.nearindex=nearindex;
   // Log.d("순서","         니어 스테이션 4");

};


    public class NearStation extends AsyncTask<String, Integer, ArrayList<NStationDTO>> {

        ArrayList<NStationDTO> data = new ArrayList<>();
        Activity activity;
        TextView textView;
        ProgressBar progressBar;


        public NearStation(){}

        public NearStation(Activity activity){
            this.activity=activity;
            if (activity instanceof Intro) {
                progressBar= activity.findViewById(R.id.introprog);
                progressBar.setProgress(0);
                textView= activity.findViewById(R.id.textprog);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<NStationDTO> doInBackground(String... strings) {



          //  Log.d("순서","         니어 어싱크  5");
//            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX="+strings[0]+"&tmY="+strings[1]+"&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";
//
//            StringBuffer sb = new StringBuffer();
//            try {
//
//                String jsonPage = getStringFromUrl(url);
//
//                JSONObject json = new JSONObject(jsonPage);
//                JSONArray jArr = json.getJSONArray("list");
//
//                for (int i = 0; i < jArr.length(); i++) {
//
//                    json = jArr.getJSONObject(i);
//
//                    String stationName = json.getString("stationName");
//                    String addr = json.getString("addr");
//
//                    String url2 = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" + stationName + "&dataTerm=daily&pageNo=1&numOfRows=3&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&ver=1.3&_returnType=json";
//
//                    String jsonPage2 = getStringFromUrl(url2);
//
//                    JSONObject json2 = new JSONObject(jsonPage2);
//                    JSONArray jArr2 = json2.getJSONArray("list");
//
//                    json2 = jArr2.getJSONObject(2);
//
//                    String pm10Value = json2.getString("pm10Value");
//                    String pm10Value24 = json2.getString("pm10Value24");
//                    String pm25Value = json2.getString("pm25Value");
//                    String pm25Value24 = json2.getString("pm25Value24");
//                    String pm10Grade = json2.getString("pm10Grade");
//                    String pm25Grade = json2.getString("pm25Grade");
//                    String pm10Grade1h = json2.getString("pm10Grade1h");
//                    String pm25Grade1h = json2.getString("pm25Grade1h");
//
//                    NStationDTO nStation = new NStationDTO( stationName,  addr,  pm10Value,  pm10Value24,  pm10Grade,  pm10Grade1h,  pm25Value,  pm25Value24,  pm25Grade,  pm25Grade1h);
//
//                    this.data.add(nStation);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return data;
            if (activity instanceof Intro) {
                progressBar.setMax(nearindex.size());
            }
            for (int i = 0; i < nearindex.size(); i++) {

                if (activity instanceof Intro) {
                    publishProgress(i);
                }
                int position = nearindex.get(i);
                String stationName = MainActivity.mStion.get(position).getStationName();
                String pm10Value = MainActivity.mStion.get(position).getPm10value();
                String pm25Value = MainActivity.mStion.get(position).getPm25value();
                String pm10Grade1h = MainActivity.mStion.get(position).getPm10Grade1h();
                String pm25Grade1h = MainActivity.mStion.get(position).getPm25Grade1h();


                NStationDTO nStation = new NStationDTO(stationName, null, pm10Value, null, null, pm10Grade1h, pm25Value, null, null, pm25Grade1h);
                this.data.add(nStation);
            }

            return data;


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0]);
           if(values[0]!=progressBar.getMax()-1) {
               textView.setText("위치정보 적용중 ");
           }else {
               progressBar.setProgress(progressBar.getMax());
               textView.setText("위치정보 적용 완료");

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
            nAdapter = new NStationAdapter(data);
            recyclerView.setAdapter(nAdapter);

        }
    }
}
