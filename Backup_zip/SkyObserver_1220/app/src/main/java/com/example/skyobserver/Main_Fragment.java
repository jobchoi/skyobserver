package com.example.skyobserver;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.skyobserver.msmap.MStation;
import com.example.skyobserver.msmap.RegiongpsDTO;
import com.example.skyobserver.statistics.CityDTO;

import java.util.ArrayList;


public class Main_Fragment extends Fragment {


    public static ArrayList<CityDTO> data;
    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10,
            imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17;
    //    ArrayList<CityDTO> data10 = new ArrayList<>();
//    ArrayList<CityDTO> data25 = new ArrayList<>();
    LinearLayout frameLayout;

    TextView stationset, pm10set, regionset, pm25set;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        final Button button = view.findViewById(R.id.pm25);
        final Button button2 = view.findViewById(R.id.pm10);

        this.frameLayout = view.findViewById(R.id.mfback);


        imageView = view.findViewById(R.id.seoulimg);
        imageView2 = view.findViewById(R.id.busanimg);
        imageView3 = view.findViewById(R.id.daeguimg);
        imageView4 = view.findViewById(R.id.incheonimg);
        imageView5 = view.findViewById(R.id.gwangjuimg);
        imageView6 = view.findViewById(R.id.daejeonimg);
        imageView7 = view.findViewById(R.id.ulsanimg);
        imageView8 = view.findViewById(R.id.gyeonggiimg);
        imageView9 = view.findViewById(R.id.gangwonimg);
        imageView10 = view.findViewById(R.id.chungbukimg);
        imageView11 = view.findViewById(R.id.chungnamimg);
        imageView12 = view.findViewById(R.id.jeonbukimg);
        imageView13 = view.findViewById(R.id.jeonnamimg);
        imageView14 = view.findViewById(R.id.gyeongnamimg);
        imageView15 = view.findViewById(R.id.jejuimg);
        imageView16 = view.findViewById(R.id.sejongimg);
        imageView17 = view.findViewById(R.id.gyeongbukimg);

        stationset = view.findViewById(R.id.stationset);
        pm10set = view.findViewById(R.id.pm10set);
        regionset = view.findViewById(R.id.regionset);
        pm25set = view.findViewById(R.id.pm25set);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = getView().findViewById(R.id.suchiimg);
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm25));
                setGrade25(data);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = getView().findViewById(R.id.suchiimg);
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm10));
                setGrade(data);
            }
        });

        try {
            //   new MainFragment().execute("10");
            //  new MainFragment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"25");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setGrade(data);
        return view;

    }

    public void setOneHourData(ArrayList<CityDTO> data) {

        this.data = new ArrayList<>(data);
        // Log.d("순서","         메인플레그 oh setOneHourData 7");
    }


    public void setGrade(ArrayList<CityDTO> data) {
        // Log.d("순서","        메인프레그먼트 segratd 9");
        //int position = 2;
        int total = 0;
        for (int i = 0; i < data.size(); i++) {

            String city = data.get(i).getCityName().toLowerCase();
            double pm10 = Double.parseDouble(data.get(i).getPm10avg());
            total += pm10;
            switch (city) {
                case ("seoul"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul03));
                    } else {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul04));
                    }
                    break;
                case ("busan"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan03));
                    } else {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan04));
                    }
                    break;
                case ("daegu"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu03));
                    } else
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu04));
                    break;

                case ("incheon"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon03));
                    } else
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon04));
                    break;

                case ("gwangju"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju1));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju03));
                    } else
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju04));
                    break;

                case ("daejeon"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon03));
                    } else
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon04));
                    break;

                case ("ulsan"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan03));
                    } else
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan04));
                    break;

                case ("gyeonggi"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi03));
                    } else
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi04));
                    break;

                case ("gangwon"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon03));
                    } else
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon04));
                    break;

                case ("chungbuk"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk03));
                    } else
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk04));
                    break;

                case ("chungnam"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam03));
                    } else
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam04));
                    break;

                case ("jeonbuk"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk03));
                    } else
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk04));
                    break;

                case ("jeonnam"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam03));
                    } else
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam04));
                    break;

                case ("gyeongbuk"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk03));
                    } else
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk04));
                    break;


                case ("gyeongnam"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam03));
                    } else
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam04));
                    break;


                case ("jeju"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju03));
                    } else
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju04));
                    break;

                case ("sejong"):
                    if (0 <= pm10 && 30 >= pm10) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong01));
                    } else if (30 < pm10 && 80 >= pm10) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong02));
                    } else if (80 < pm10 && 150 >= pm10) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong03));
                    } else
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong04));
                    break;
            }


        }


        //int avg = (seoul + busan + daegu + incheon + gwangju + daejeon + ulsan + gyeonggi + gangwon + chungbuk + chungnam + jeonbuk + jeonnam + gyeongnam + jeju + sejong + gyeongbuk) / 17;
        int avg = total / 17;

        if (0 <= avg && 30 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125, 200, 255));

        } else if (30 < avg && 80 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125, 225, 100));
        } else if (80 < avg && 150 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(255, 200, 125));
        } else
            frameLayout.setBackgroundColor(Color.rgb(255, 125, 125));


    }

    public void setGrade25(ArrayList<CityDTO> data) {

        //int position = 2;
        int total = 0;
        for (int i = 0; i < data.size(); i++) {

            String city = data.get(i).getCityName().toLowerCase();
            double pm25 = Double.parseDouble(data.get(i).getPm25avg());
            total += pm25;
            switch (city) {
                case ("seoul"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul03));
                    } else {
                        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul04));
                    }
                    break;
                case ("busan"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan03));
                    } else {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan04));
                    }
                    break;
                case ("daegu"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu03));
                    } else
                        imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu04));
                    break;

                case ("incheon"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon03));
                    } else
                        imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon04));
                    break;

                case ("gwangju"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju1));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju03));
                    } else
                        imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju04));
                    break;

                case ("daejeon"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon03));
                    } else
                        imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon04));
                    break;

                case ("ulsan"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan03));
                    } else
                        imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan04));
                    break;

                case ("gyeonggi"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi03));
                    } else
                        imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi04));

                    break;

                case ("gangwon"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon03));
                    } else
                        imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon04));

                    break;

                case ("chungbuk"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk03));
                    } else
                        imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk04));

                    break;

                case ("chungnam"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam03));
                    } else
                        imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam04));
                    break;

                case ("jeonbuk"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk03));
                    } else
                        imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk04));

                    break;

                case ("jeonnam"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam03));
                    } else
                        imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam04));

                    break;

                case ("gyeongbuk"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk03));
                    } else
                        imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk04));
                    break;


                case ("gyeongnam"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam03));
                    } else
                        imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam04));

                    break;


                case ("jeju"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju03));
                    } else
                        imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju04));

                    break;

                case ("sejong"):
                    if (0 <= pm25 && 15 >= pm25) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong01));
                    } else if (15 < pm25 && 35 >= pm25) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong02));
                    } else if (35 < pm25 && 75 >= pm25) {
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong03));
                    } else
                        imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong04));

                    break;
            }


        }


        //int avg = (seoul + busan + daegu + incheon + gwangju + daejeon + ulsan + gyeonggi + gangwon + chungbuk + chungnam + jeonbuk + jeonnam + gyeongnam + jeju + sejong + gyeongbuk) / 17;
        int avg = total / 17;

        if (0 <= avg && 15 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125, 200, 255));
        } else if (15 < avg && 35 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125, 225, 100));
        } else if (35 < avg && 75 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(255, 200, 125));
        } else
            frameLayout.setBackgroundColor(Color.rgb(255, 125, 125));


    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences userPref = getActivity().getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        if (!userPref.getString("regioncode", "").equals("")) {

            ArrayList<RegiongpsDTO> dto = MainActivity.regiongpsDTO;
            for (int i = 0; i < dto.size(); i++) {
                if (userPref.getString("regioncode", "").equals(dto.get(i).getCode())) {


                    if (!dto.get(i).getGugun().equals("null")) {
                        regionset.setText(dto.get(i).getGugun());
                    } else {
                        regionset.setText("");
                    }

                    if (!dto.get(i).getDong().equals("null")) {
                        regionset.append(" " + dto.get(i).getDong());
                    } else {
                        regionset.append("");
                    }


                    if (!dto.get(i).getRi().equals("null")) {
                        regionset.append(" " + dto.get(i).getRi());
                    } else {
                        regionset.append("");
                    }

                    double gpsy = Double.parseDouble(dto.get(i).getY());
                    double gpsx = Double.parseDouble(dto.get(i).getX());
                    MStation close = gpsdataget(gpsy, gpsx);
                    stationset.setText(close.getStationName());
                    pm10set.setText(close.getPm10value());
                    pm25set.setText(close.getPm25value());
                    break;
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    public MStation gpsdataget(double gpsy, double gpsx) {


        ArrayList<MStation> dto = MainActivity.mStion;
        int close = -1;
        double temp = -1;
        for (int i = 0; dto.size() > i; i++) {
            double msx = Double.parseDouble(dto.get(i).getDmX());
            double msy = Double.parseDouble(dto.get(i).getDmY());
            double dis =Math.sqrt(Math.pow(((gpsx - msx) * 88.804), 2) + Math.pow(((gpsy - msy) * 111.195), 2));

            if ((temp == -1 || dis < temp) && !dto.get(i).getPm10value().equals("-") ) {
                close = i;
                temp = dis;
            }else{
                continue;
            }
        }


        return dto.get(close);

    }
}




