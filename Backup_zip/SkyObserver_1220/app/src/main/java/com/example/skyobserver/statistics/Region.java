package com.example.skyobserver.statistics;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.MainfragementDTO;
import com.example.skyobserver.R;
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

public class Region extends AppCompatActivity {
    Context context;

    String msg;
    String region;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        this.msg=getIntent().getStringExtra("titleMsg");
        this.region=getIntent().getStringExtra("region");
        context = this.getBaseContext();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(region);



    }



    @Override
    public void onStart() {


        ArrayList<MainfragementDTO> info= Statistics.week10;
        BarChart chart = findViewById(R.id.barchart);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] stations = new String[]{"7일전", "6일전", "5일전", "4일전", "3일전", "2일전", "어제"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(stations);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, Float.parseFloat(info.get(6).getRegion(msg))));
        entries.add(new BarEntry(1f, Float.parseFloat(info.get(5).getRegion(msg))));
        entries.add(new BarEntry(2f, Float.parseFloat(info.get(4).getRegion(msg))));
        entries.add(new BarEntry(3f, Float.parseFloat(info.get(3).getRegion(msg))));
        entries.add(new BarEntry(4f, Float.parseFloat(info.get(2).getRegion(msg))));
        entries.add(new BarEntry(5f, Float.parseFloat(info.get(1).getRegion(msg))));
        entries.add(new BarEntry(6f, Float.parseFloat(info.get(0).getRegion(msg))));



        BarDataSet set = new BarDataSet(entries, "BarDataSet");


        BarData data = new BarData(set);
        data.setBarWidth(0.2f); // set custom bar width
        chart.getXAxis().setLabelCount(7);

        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh


        super.onStart();
    }



}