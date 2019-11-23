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

        final  Button seoul = findViewById(R.id.seoul);
        seoul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "seoul");
                intent.putExtra("region",seoul.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_seoul);
            }
        });

        final Button busan = findViewById(R.id.busan);
        busan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "Region");
                intent.putExtra("region",busan.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_busan);
            }
        });


        final  Button daegu = findViewById(R.id.daegu);
        daegu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "daegu");
                intent.putExtra("region",daegu.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_daegu);
            }
        });
        final Button incheon = findViewById(R.id.incheon);
        incheon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "incheon");
                intent.putExtra("region",incheon.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_incheon);
            }
        });
        final  Button gwangju = findViewById(R.id.gwangju);
        gwangju.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "gwangju");
                intent.putExtra("region",gwangju.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_gwangju);
            }
        });
        final  Button daejeon = findViewById(R.id.daejeon);
        daejeon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "daejeon");
                intent.putExtra("region",daejeon.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_daejeon);
            }
        });
        final  Button ulsan = findViewById(R.id.ulsan);
        ulsan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "ulsan");
                intent.putExtra("region",ulsan.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_ulsan);
            }
        });
        final  Button gyeonggi = findViewById(R.id.gyeonggi);
        gyeonggi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "gyeonggi");
                intent.putExtra("region",gyeonggi.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_gyeonggi);
            }
        });
        final Button gangwon = findViewById(R.id.gangwon);
        gangwon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "gangwon");
                intent.putExtra("region",gangwon.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_gangwon);
            }
        });
        final  Button chungbuk = findViewById(R.id.chungbuk);
        chungbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "chungbuk");
                intent.putExtra("region",chungbuk.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_chungbuk);
            }
        });
        final Button chungnam = findViewById(R.id.chungnam);
        chungnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "chungnam");
                intent.putExtra("region",chungnam.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_chungnam);
            }
        });
        final Button jeonbuk = findViewById(R.id.jeonbuk);
        jeonbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "jeonbuk");
                intent.putExtra("region",jeonbuk.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_jeonbuk);
            }
        });
        final  Button jeonnam = findViewById(R.id.jeonnam);
        jeonnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "jeonnam");
                intent.putExtra("region",jeonnam.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_jeonnam);
            }
        });
        final Button gyeongbuk = findViewById(R.id.gyeongbuk);
        gyeongbuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "gyeongbuk");
                intent.putExtra("region",gyeongbuk.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_gyeongbuk);
            }
        });
        final  Button gyeongnam = findViewById(R.id.gyeongnam);
        gyeongnam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "gyeongnam");
                intent.putExtra("region",gyeongnam.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_gyeongnam);
            }
        });
        final  Button jeju = findViewById(R.id.jeju);
        jeju.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "jeju");
                intent.putExtra("region",jeju.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_jeju);
            }
        });
        final Button sejong = findViewById(R.id.sejong);
        sejong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent.putExtra("titleMsg", "sejong");
                intent.putExtra("region",sejong.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_sejong);
            }
        });
    }




        }



