package com.example.skyobserver.statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skyobserver.MainfragementDTO;
import com.example.skyobserver.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Statistics extends AppCompatActivity {



    public static ArrayList<MainfragementDTO> week10=new ArrayList<>();
    public static ArrayList<MainfragementDTO> week25=new ArrayList<>();


    public static final int REQUEST_CODE_REGION = 218;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        setchart(week10,10);






//
//        final Button seoul = findViewById(R.id.seoul);
//        seoul.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "seoul");
//                intent.putExtra("region", seoul.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_seoul);
//            }
//        });
//
//        final Button busan = findViewById(R.id.busan);
//        busan.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "busan");
//                intent.putExtra("region", busan.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_busan);
//            }
//        });
//
//
//        final Button daegu = findViewById(R.id.daegu);
//        daegu.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "daegu");
//                intent.putExtra("region", daegu.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_daegu);
//            }
//        });
//        final Button incheon = findViewById(R.id.incheon);
//        incheon.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "incheon");
//                intent.putExtra("region", incheon.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_incheon);
//            }
//        });
//        final Button gwangju = findViewById(R.id.gwangju);
//        gwangju.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "gwangju");
//                intent.putExtra("region", gwangju.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_gwangju);
//            }
//        });
//        final Button daejeon = findViewById(R.id.daejeon);
//        daejeon.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "daejeon");
//                intent.putExtra("region", daejeon.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_daejeon);
//            }
//        });
//        final Button ulsan = findViewById(R.id.ulsan);
//        ulsan.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "ulsan");
//                intent.putExtra("region", ulsan.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_ulsan);
//            }
//        });
//        final Button gyeonggi = findViewById(R.id.gyeonggi);
//        gyeonggi.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "gyeonggi");
//                intent.putExtra("region", gyeonggi.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_gyeonggi);
//            }
//        });
//        final Button gangwon = findViewById(R.id.gangwon);
//        gangwon.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "gangwon");
//                intent.putExtra("region", gangwon.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_gangwon);
//            }
//        });
//        final Button chungbuk = findViewById(R.id.chungbuk);
//        chungbuk.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "chungbuk");
//                intent.putExtra("region", chungbuk.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_chungbuk);
//            }
//        });
//        final Button chungnam = findViewById(R.id.chungnam);
//        chungnam.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "chungnam");
//                intent.putExtra("region", chungnam.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_chungnam);
//            }
//        });
//        final Button jeonbuk = findViewById(R.id.jeonbuk);
//        jeonbuk.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "jeonbuk");
//                intent.putExtra("region", jeonbuk.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_jeonbuk);
//            }
//        });
//        final Button jeonnam = findViewById(R.id.jeonnam);
//        jeonnam.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "jeonnam");
//                intent.putExtra("region", jeonnam.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_jeonnam);
//            }
//        });
//        final Button gyeongbuk = findViewById(R.id.gyeongbuk);
//        gyeongbuk.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "gyeongbuk");
//                intent.putExtra("region", gyeongbuk.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_gyeongbuk);
//            }
//        });
//        final Button gyeongnam = findViewById(R.id.gyeongnam);
//        gyeongnam.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "gyeongnam");
//                intent.putExtra("region", gyeongnam.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_gyeongnam);
//            }
//        });
//        final Button jeju = findViewById(R.id.jeju);
//        jeju.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "jeju");
//                intent.putExtra("region", jeju.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_jeju);
//            }
//        });
//        final Button sejong = findViewById(R.id.sejong);
//        sejong.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Region.class);
//                intent.putExtra("titleMsg", "sejong");
//                intent.putExtra("region", sejong.getText().toString());
//                startActivityForResult(intent, REQUEST_CODE_sejong);
//            }
//        });




    }


    public  void setchart(ArrayList<MainfragementDTO> week, final int type){

        int seoulavg=0;
        int  busanavg=0;
        int daeguavg=0;
        int incheonavg=0;
        int  gwangjuavg=0;
        int  daejeonavg=0;
        int  ulsanavg=0;
        int  gyeonggiavg=0;
        int  gangwonavg=0;
        int  chungbukavg=0;
        int chungnamavg=0;
        int jeonbukavg=0;
        int jeonnamavg=0;
        int   gyeongbukavg=0;
        int  gyeongnamavg=0;
        int   jejuavg=0;
        int  sejongavg =0;


        for (int i = 0; i < week.size(); i++) {

            seoulavg+=Integer.parseInt(week.get(i).getSeoul());
            busanavg+=Integer.parseInt(week.get(i).getBusan());
            daeguavg+=Integer.parseInt(week.get(i).getDaegu());
            incheonavg+=Integer.parseInt(week.get(i).getIncheon());
            gwangjuavg+=Integer.parseInt(week.get(i).getGwangju());
            daejeonavg+=Integer.parseInt(week.get(i).getDaejeon());
            ulsanavg+=Integer.parseInt(week.get(i).getUlsan());
            gyeonggiavg+=Integer.parseInt(week.get(i).getGyeonggi());
            gangwonavg+=Integer.parseInt(week.get(i).getGangwon());
            chungbukavg+=Integer.parseInt(week.get(i).getChungbuk());
            chungnamavg+=Integer.parseInt(week.get(i).getChungnam());
            jeonbukavg+=Integer.parseInt(week.get(i).getJeonbuk());
            jeonnamavg+=Integer.parseInt(week.get(i).getJeonnam());
            gyeongbukavg+=Integer.parseInt(week.get(i).getGyeongbuk());
            gyeongnamavg+=Integer.parseInt(week.get(i).getGyeongnam());
            jejuavg+=Integer.parseInt(week.get(i).getJeju());
            sejongavg+=Integer.parseInt(week.get(i).getSejong());
        }


        seoulavg =  seoulavg/7;
        busanavg = busanavg /7;
        daeguavg =daeguavg /7;
        incheonavg =incheonavg /7;
        gwangjuavg = gwangjuavg /7;
        daejeonavg = daejeonavg /7;
        ulsanavg = ulsanavg /7;
        gyeonggiavg = gyeonggiavg /7;
        gangwonavg  =gangwonavg/7;
        chungbukavg = chungbukavg/7;
        chungnamavg =chungnamavg /7;
        jeonbukavg =jeonbukavg /7;
        jeonnamavg =jeonnamavg /7;
        gyeongbukavg =  gyeongbukavg /7;
        gyeongnamavg = gyeongnamavg /7;
        jejuavg  = jejuavg /7;
        sejongavg  = sejongavg /7;


        int[] avg={ seoulavg,busanavg ,daeguavg ,incheonavg,gwangjuavg ,daejeonavg,ulsanavg ,gyeonggiavg,
                gangwonavg,chungbukavg,chungnamavg,jeonbukavg,jeonnamavg,gyeongbukavg,gyeongnamavg,
                jejuavg ,sejongavg} ;

        //Arrays.sort(avg);

//        Log.d("9999",String.valueOf(avg[1])+"    "+String.valueOf(avg[7]));

        reverseArrayInt(avg);



        final HorizontalBarChart chart = findViewById(R.id.mainbarchart);
        final XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition. BOTTOM);
        final String[] stations = new String[]{"서울", "부산", "대구", "인천", "광주", "대전", "울산","경기","강원","충북","충남","전북","전남","경북","경남","제주","세종"};
        final String[] region=new String[]{"seoul", "busan", "daegu", "incheon", "gwangju", "daejeon", "ulsan","gyeonggi","gangwon","chungbuk","chungnam"
                ,"jeonbuk","jeonnam","gyeongbuk","gyeongnam","jeju","sejong" };
        reverseArrayString(stations);
        reverseArrayString(region);

        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(stations);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);


        int blue= Color.rgb(0, 164, 224);
        int green=  Color.rgb(111   , 192, 126);
        int orange= Color.rgb(248   , 181, 74);
        int red= Color.rgb(199, 43, 29);



        List<Integer> colors = new ArrayList<>();



        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i <17 ; i++) {
            entries.add(new BarEntry(i,avg[i]));
            if (0 <= avg[i] && 30 >= avg[i]) {
                colors.add(blue);
            } else if (30 < avg[i] && 80 >= avg[i]) {
                colors.add(green);
            } else if (80 < avg[i] && 150 >= avg[i]) {
                colors.add(orange);
            } else {
                colors.add(red);
            }

        }



        BarDataSet set = new BarDataSet(entries, "미세먼지");
        set.setColors(colors);

        BarData data = new BarData(set);
        data.setBarWidth(0.5f); // set custom bar width




        xAxis.setLabelCount(17);
        xAxis.setGridColor(Color.LTGRAY);
        chart.setScaleEnabled(false);

        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setGridColor(Color.LTGRAY);
        chart.getAxisRight().setGridColor(Color.LTGRAY);

        chart.getAxisRight().setAxisMinimum(0f);
        chart.getAxisLeft().setDrawTopYLabelEntry(false);

        Description description = new Description();
        if(type==10){
            description.setText("미세먼지 일주일 평균(㎍/m³)");
        }else{
            description.setText("초미세먼지 일주일 평균(㎍/m³)");
        }
        chart.setDescription(description);


        chart.getLegend().setEnabled(false);
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh



        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Intent intent = new Intent(getApplicationContext(), Region.class);
                intent. putExtra("type",type);
                intent.putExtra("titleMsg", region[(int)e.getX()]);
                intent.putExtra("region",chart.getXAxis().getFormattedLabel((int) e.getX()));
                startActivityForResult(intent, REQUEST_CODE_REGION);

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);



        return true;

    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {


        switch(item.getItemId())
        {
            case R.id.action_settings:
                setchart(week10,10);
                break;
            case R.id.action_settings2:
                setchart(week25,25);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void  getWeekInfo(HashMap<String,Object> weekInfo){
        this.week10= (ArrayList<MainfragementDTO>) weekInfo.get("week10");
        this.week25= (ArrayList<MainfragementDTO>) weekInfo.get("week25");
       // Log.d("순서","         통계 oh  12");
    }


    public  void reverseArrayString(String[] array) {
        String temp;

        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = temp;
        }
    }

    public  void reverseArrayInt(int[] array) {
        int temp;

        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = temp;
        }
    }


}



