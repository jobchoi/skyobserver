package com.example.skyobserver.statistics;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.MainfragementDTO;
import com.example.skyobserver.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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

public class Region extends AppCompatActivity {
    Context context;

    String msg;
    String region;
    int type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        this.msg=getIntent().getStringExtra("titleMsg");
        this.region=getIntent().getStringExtra("region");
        this.type=getIntent().getIntExtra("type",0);
        context = this.getBaseContext();


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(region);



    }



    @Override
    public void onStart() {
        ArrayList<MainfragementDTO> info=null;

        if(type==10){
            info= Statistics.week10;
        }else if(type==25){
            info= Statistics.week25;

        }
        BarChart chart = findViewById(R.id.barchart);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] stations = new String[]{"7일전", "6일전", "5일전", "4일전", "3일전", "2일전", "어제"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(stations);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        YAxis yAxisr=chart.getAxisRight();
        yAxisr.setDrawLabels(false);
        yAxisr.setDrawAxisLine(false);
        yAxisr.setDrawGridLines(false);







        int blue= Color.rgb(0, 164, 224);
        int green=  Color.rgb(111   , 192, 126);
        int orange= Color.rgb(248   , 181, 74);
        int red= Color.rgb(199, 43, 29);



        List<Integer> colors = new ArrayList<>();



        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            entries.add(new BarEntry(i, Float.parseFloat(info.get(6-i).getRegion(msg))));
            if (0 <= Float.parseFloat(info.get(6-i).getRegion(msg)) && 30 >=  Float.parseFloat(info.get(6-i).getRegion(msg))) {
                colors.add(blue);
            } else if (30 <  Float.parseFloat(info.get(6-i).getRegion(msg)) && 80 >=  Float.parseFloat(info.get(6-i).getRegion(msg))) {
                colors.add(green);
            } else if (80 <  Float.parseFloat(info.get(6-i).getRegion(msg)) && 150 >= Float.parseFloat(info.get(6-i).getRegion(msg))) {
                colors.add(orange);
            } else {
                colors.add(red);
            }

        }

     /*
        entries.add(new BarEntry(0f, Float.parseFloat(info.get(6).getRegion(msg))));
        entries.add(new BarEntry(1f, Float.parseFloat(info.get(5).getRegion(msg))));
        entries.add(new BarEntry(2f, Float.parseFloat(info.get(4).getRegion(msg))));
        entries.add(new BarEntry(3f, Float.parseFloat(info.get(3).getRegion(msg))));
        entries.add(new BarEntry(4f, Float.parseFloat(info.get(2).getRegion(msg))));
        entries.add(new BarEntry(5f, Float.parseFloat(info.get(1).getRegion(msg))));
        entries.add(new BarEntry(6f, Float.parseFloat(info.get(0).getRegion(msg))));*/


        

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColors(colors);
        xAxis.setGridColor(Color.LTGRAY);
        chart.setScaleEnabled(false);
        chart.getAxisLeft().setGridColor(Color.LTGRAY);

        BarData data = new BarData(set);
        data.setBarWidth(0.2f); // set custom bar width
        chart.getXAxis().setLabelCount(7);


        Description description = new Description();
        if(type==10){
            description.setText("미세먼지 하루 평균(㎍/m³)");
        }else{
            description.setText("초미세먼지 하루 평균(㎍/m³)");
        }
        chart.setDescription(description);
        chart.getDescription().setPosition(1000f,100f);

        chart.getLegend().setEnabled(false);
        data.setDrawValues(true);
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh


        super.onStart();
    }



}