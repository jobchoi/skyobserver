package com.example.skyobserver.statistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.R;

public class Statistics extends AppCompatActivity {
    Context context;
    public static final int REQUEST_CODE_seoul = 218;
    public static final int REQUEST_CODE_busan = 202;
    public static final int REQUEST_CODE_daegu = 203;
    public static final int REQUEST_CODE_incheon = 204;
    public static final int REQUEST_CODE_gwangju = 205;
    public static final int REQUEST_CODE_daejeon = 206;
    public static final int REQUEST_CODE_ulsan = 207;
    public static final int REQUEST_CODE_gyeonggi = 208;
    public static final int REQUEST_CODE_gangwon = 209;
    public static final int REQUEST_CODE_chungbuk = 210;
    public static final int REQUEST_CODE_chungnam = 211;
    public static final int REQUEST_CODE_jeonbuk = 212;
    public static final int REQUEST_CODE_jeonnam = 213;
    public static final int REQUEST_CODE_gyeongbuk = 214;
    public static final int REQUEST_CODE_gyeongnam = 215;
    public static final int REQUEST_CODE_jeju = 216;
    public static final int REQUEST_CODE_sejong = 217;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button seoul = findViewById(R.id.seoul);
        seoul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Seoul.class);
                intent.putExtra("titleMsg", "서울");

                startActivityForResult(intent, REQUEST_CODE_seoul);
            }
        });

        Button busan = findViewById(R.id.busan);
        busan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.busan.class);
                intent.putExtra("titleMsg", "부산");

                startActivityForResult(intent, REQUEST_CODE_busan);
            }
        });


        Button daegu = findViewById(R.id.daegu);
        daegu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.daegu.class);
                intent.putExtra("titleMsg", "대구");

                startActivityForResult(intent, REQUEST_CODE_daegu);
            }
        });
        Button incheon = findViewById(R.id.incheon);
        incheon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), incheon.class);
                intent.putExtra("titleMsg", "인천");

                startActivityForResult(intent, REQUEST_CODE_incheon);
            }
        });
        Button gwangju = findViewById(R.id.gwangju);
        gwangju.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gwangju.class);
                intent.putExtra("titleMsg", "광주");

                startActivityForResult(intent, REQUEST_CODE_gwangju);
            }
        });
        Button daejeon = findViewById(R.id.daejeon);
        daejeon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), daejeon.class);
                intent.putExtra("titleMsg", "대전");

                startActivityForResult(intent, REQUEST_CODE_daejeon);
            }
        });
        Button ulsan = findViewById(R.id.ulsan);
        ulsan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.ulsan.class);
                intent.putExtra("titleMsg", "울산");

                startActivityForResult(intent, REQUEST_CODE_ulsan);
            }
        });
        Button gyeonggi = findViewById(R.id.gyeonggi);
        gyeonggi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.gyeonggi.class);
                intent.putExtra("titleMsg", "경기");

                startActivityForResult(intent, REQUEST_CODE_gyeonggi);
            }
        });
        Button gangwon = findViewById(R.id.gangwon);
        gangwon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.gangwon.class);
                intent.putExtra("titleMsg", "강원");

                startActivityForResult(intent, REQUEST_CODE_gangwon);
            }
        });
        Button chungbuk = findViewById(R.id.chungbuk);
        chungbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.chungbuk.class);
                intent.putExtra("titleMsg", "충북");

                startActivityForResult(intent, REQUEST_CODE_chungbuk);
            }
        });
        Button chungnam = findViewById(R.id.chungnam);
        chungnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), chungnam.class);
                intent.putExtra("titleMsg", "충남");

                startActivityForResult(intent, REQUEST_CODE_chungnam);
            }
        });
        Button jeonbuk = findViewById(R.id.jeonbuk);
        jeonbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.jeonbuk.class);
                intent.putExtra("titleMsg", "전북");

                startActivityForResult(intent, REQUEST_CODE_jeonbuk);
            }
        });
        Button jeonnam = findViewById(R.id.jeonnam);
        jeonnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.jeonnam.class);
                intent.putExtra("titleMsg", "전남");

                startActivityForResult(intent, REQUEST_CODE_jeonnam);
            }
        });
        Button gyeongbuk = findViewById(R.id.gyeongbuk);
        gyeongbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gyeongbuk.class);
                intent.putExtra("titleMsg", "경북");

                startActivityForResult(intent, REQUEST_CODE_gyeongbuk);
            }
        });
        Button gyeongnam = findViewById(R.id.gyeongnam);
        gyeongnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gyeongnam.class);
                intent.putExtra("titleMsg", "경남");

                startActivityForResult(intent, REQUEST_CODE_gyeongnam);
            }
        });
        Button jeju = findViewById(R.id.jeju);
        jeju.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.jeju.class);
                intent.putExtra("titleMsg", "제주");

                startActivityForResult(intent, REQUEST_CODE_jeju);
            }
        });
        Button sejong = findViewById(R.id.sejong);
        sejong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.skyobserver.statistics.sejong.class);
                intent.putExtra("titleMsg", "세종");

                startActivityForResult(intent, REQUEST_CODE_sejong);
            }
        });
    }




        }



