package com.example.skyobserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.skyobserver.msmap.MStation;
import com.example.skyobserver.msmap.MeasuringStation;
import com.example.skyobserver.msmap.Regiongps;
import com.example.skyobserver.nearstation.NearSt_Fragment;
import com.example.skyobserver.statistics.StatisticsAsync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {
    // GeoPoint tmset;
    public static final int REQUEST_CODE_PERMISSIONS = 1009;
    ArrayList<MStation> mStations;
    ArrayList<Integer> nearindex;
    TextView progtext;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);
        progtext = findViewById(R.id.textprog);
        progressBar = findViewById(R.id.introprog);

        ImageView fa=findViewById(R.id.fineairIntro);
        Glide.with(this).load(R.drawable.fineair).into(fa);

        MeasuringStation ms = new MeasuringStation(Intro.this);
        OneHourAsync oh=new OneHourAsync(Intro.this);
        StatisticsAsync sa= new StatisticsAsync(Intro.this);
        Regiongps rg=new Regiongps(Intro.this);

        progtext.setText("데이터 불러오는중");
        progressBar.setProgress(0);
        try {
       //     Log.d("순서","         인트로 ms 1");
            ms.execute();
            //   Log.d("순서","         인트로sa  6");
            oh.execute();
            //   Log.d("순서","         인트로oh  10");
            sa.execute();
            rg.execute();


        } catch (Exception e) {
            e.printStackTrace();

        }



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Intro.this, MainActivity.class);
                intent.putExtra("mStations", mStations);
                // String XY[]={String.valueOf(tmset.getX()), String.valueOf(tmset.getY())};
                //intent.putExtra("nearindex", nearindex);

                startActivity(intent);
                finish();
            }
        }, 60000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void gpsdataget(ArrayList<MStation> data) {

      //  Log.d("순서","         인트로 ms post gps 3");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            // GeoPoint lastLoc = new GeoPoint(location.getLongitude(), location.getLatitude());

            double gpsy = location.getLongitude();
            double gpsx = location.getLatitude();
            nearindex = new ArrayList<>();
            List<Double> dist=new ArrayList<>();

            for (int i = 0; data.size() > i; i++) {
                double msx = Double.parseDouble(data.get(i).getDmX());
                double msy = Double.parseDouble(data.get(i).getDmY());
                double dis = Math.sqrt(Math.pow(((gpsx - msx) * 88.804), 2) + Math.pow(((gpsy - msy) * 111.195), 2));
                if (dis <= 10) {
                    this.nearindex.add(i);
                   dist.add(dis);

                }
            }



            Log.d("dist=====",dist.toString());
            Log.d("nearindex=====",nearindex.toString());
            double tempd=-1;

            int tempi=-1;

            for (int i = 0; i <=nearindex.size()-1; i++) {

                for (int j = i+1; j <= nearindex.size()-1; j++) {


                   if( dist.get(i)>dist.get(j)){
                       tempd=dist.get(i);
                       dist.set(i,dist.get(j));
                       dist.set(j,tempd);

                       tempi= nearindex.get(i);
                       nearindex.set(i,nearindex.get(j));
                       nearindex.set(j,tempi);

                   }}

            }

            Log.d("dist=====",dist.toString());
            Log.d("nearindex=====",nearindex.toString());

            NearSt_Fragment nf=new NearSt_Fragment(Intro.this);
            nf.getnearindex(nearindex);
            progtext.setText("위치 정보 가져오는중");
            progressBar.setMax(100);
            progressBar.setProgress(100);

            //Log.d("1234",String.valueOf(location.getLongitude())+String.valueOf(location.getLatitude()));

            //LatLng hanul = new LatLng(35.153469, 126.887775);

            // GeoPoint lastLoc = new GeoPoint(hanul.longitude,hanul.latitude);

            // GeoPoint tmset = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, lastLoc);
            // this.tmset=tmset;
            // Log.d("process",tmset.getX()+"      "+tmset.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateProducts(final ArrayList<MStation> mStations) {
        this.mStations = mStations;
      //  Log.d("순서","         인트로 ms post 2");
    }
}
