package com.example.skyobserver;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Main_Fragment extends Fragment {
    ArrayList<mainfragementDTO> data10 = new ArrayList<>();
    ArrayList<mainfragementDTO> data25 = new ArrayList<>();
    FrameLayout frameLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        final Button button = view.findViewById(R.id.pm25);
        final Button button2 = view.findViewById(R.id.pm10);

        this.frameLayout=view.findViewById(R.id.mfback);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ImageView imageView = getView().findViewById(R.id.suchiimg);
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm25));
                    setGrade25(data25);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ImageView imageView = getView().findViewById(R.id.suchiimg);
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm10));
                    setGrade(data10);
            }
        });
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                try {
////
////                    ImageView imageView = getView().findViewById(R.id.suchiimg);
////                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm25));
////
////                    String pm25 = "25";
////                   new MainFragment().execute(pm25).get();
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////
////
////
////        button2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                try {
////
//////                    button2.setBackgroundColor(gr);
//////
//////                    button.setBackgroundColor(or);
////
////                    ImageView imageView = getView().findViewById(R.id.suchiimg);
////                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm10));;
////
////                    String pm10 = "10";
////                   new MainFragment().execute(pm10).get();
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        });
//
//        //  TextView textView=view.findViewById(R.id.seoul);
//        //  textView.setText("asdfasdfdf");
//
        try {
         //   new MainFragment().execute("10");
          //  new MainFragment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"25");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    public void setPm10(ArrayList<mainfragementDTO> data){
        this.data10=data;
    }
    public void setPm25(ArrayList<mainfragementDTO> data){
        this.data25=data;
    }

    public void setGrade(ArrayList<mainfragementDTO> data){

        int position =2;

        ImageView imageView=getView().findViewById(R.id.seoulimg);
        ImageView imageView2=getView().findViewById(R.id.busanimg);
        ImageView imageView3=getView().findViewById(R.id.daeguimg);
        ImageView imageView4=getView().findViewById(R.id.incheonimg);
        ImageView imageView5=getView().findViewById(R.id.gwangjuimg);
        ImageView imageView6=getView().findViewById(R.id.daejeonimg);
        ImageView imageView7=getView().findViewById(R.id.ulsanimg);
        ImageView imageView8=getView().findViewById(R.id.gyeonggiimg);
        ImageView imageView9=getView().findViewById(R.id.gangwonimg);
        ImageView imageView10=getView().findViewById(R.id.chungbukimg);
        ImageView imageView11=getView().findViewById(R.id.chungnamimg);
        ImageView imageView12=getView().findViewById(R.id.jeonbukimg);
        ImageView imageView13=getView().findViewById(R.id.jeonnamimg);
        ImageView imageView14=getView().findViewById(R.id.gyeongnamimg);
        ImageView imageView15=getView().findViewById(R.id.jejuimg);
        ImageView imageView16=getView().findViewById(R.id.sejongimg);
        ImageView imageView17=getView().findViewById(R.id.gyeongbukimg);



        int seoul=Integer.parseInt(data.get(position).getSeoul());
        int busan=Integer.parseInt(data.get(position).getBusan());
        int daegu=Integer.parseInt(data.get(position).getDaegu());
        int incheon=Integer.parseInt(data.get(position).getIncheon());
        int gwangju=Integer.parseInt(data.get(position).getGwangju());
        int daejeon=Integer.parseInt(data.get(position).getDaejeon());
        int ulsan=Integer.parseInt(data.get(position).getUlsan());
        int gyeonggi=Integer.parseInt(data.get(position).getGyeonggi());
        int gangwon=Integer.parseInt(data.get(position).getGangwon());
        int chungbuk=Integer.parseInt(data.get(position).getChungbuk());
        int chungnam=Integer.parseInt(data.get(position).getChungnam());
        int jeonbuk=Integer.parseInt(data.get(position).getJeonbuk());
        int jeonnam=Integer.parseInt(data.get(position).getJeonnam());
        int gyeongnam=Integer.parseInt(data.get(position).getGyeongnam());
        int jeju=Integer.parseInt(data.get(position).getJeju());
        int sejong=Integer.parseInt(data.get(position).getSejong());
        int gyeongbuk=Integer.parseInt(data.get(position).getGyeongbuk());


        if(0 <= seoul && 30 >= seoul) {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul01));
        } else if (31 <= seoul && 80 >= seoul){
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul02));
        } else if (81 <= seoul && 150 >= seoul) {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul03));
        }else {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul04));
        }

        if(0 <= busan && 30 >= busan) {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan01));
        } else if (31 <= busan && 80 >= busan){
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan02));
        } else if (81 <= busan && 150 >= busan) {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan03));
        }else {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan04));
        }

        if(0 <= daegu && 30 >= daegu) {
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu01));
        } else if (31 <= daegu && 80 >= daegu){
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu02));
        } else if (81 <= daegu && 150 >= daegu) {
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu03));
        }else
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu04));

        if(0 <= incheon && 30 >= incheon) {
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon01));
        } else if (31 <= incheon && 80 >= incheon){
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon02));
        } else if (81 <= incheon && 150 >= incheon) {
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon03));
        }else
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon04));

        if(0 <= gwangju && 30 >= gwangju) {
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju1));
        } else if (31 <= gwangju && 80 >= gwangju){
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju02));
        } else if (81 <= gwangju && 150 >= Integer.parseInt(data.get(position).getGwangju())) {
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju03));
        }else
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju04));

        if(0 <= daejeon && 30 >= daejeon) {
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon01));
        } else if (31 <= daejeon && 80 >= daejeon){
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon02));
        } else if (81 <= daejeon && 150 >= daejeon) {
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon03));
        }else
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon04));

        if(0 <= ulsan && 30 >= ulsan) {
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan01));
        } else if (31 <= ulsan && 80 >= ulsan){
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan02));
        } else if (81 <= ulsan && 150 >= ulsan) {
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan03));
        }else
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan04));

        if(0 <= gyeonggi && 30 >= gyeonggi) {
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi01));
        } else if (31 <= gyeonggi && 80 >= gyeonggi){
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi02));
        } else if (81 <= gyeonggi && 150 >= gyeonggi) {
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi03));
        }else
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi04));

        if(0 <= gangwon && 30 >= gangwon) {
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon01));
        } else if (31 <= gangwon && 80 >= gangwon){
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon02));
        } else if (81 <= gangwon && 150 >= gangwon) {
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon03));
        }else
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon04));

        if(0 <= chungbuk && 30 >= chungbuk) {
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk01));
        } else if (31 <= chungbuk && 80 >= chungbuk){
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk02));
        } else if (81 <= chungbuk && 150 >= chungbuk) {
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk03));
        }else
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk04));

        if(0 <= chungnam && 30 >= chungnam) {
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam01));
        } else if (31 <= chungnam && 80 >= chungnam){
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam02));
        } else if (81 <= chungnam && 150 >= chungnam) {
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam03));
        }else
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam04));

        if(0 <= jeonbuk && 30 >= jeonbuk) {
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk01));
        } else if (31 <= jeonbuk && 80 >= jeonbuk){
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk02));
        } else if (81 <= jeonbuk && 150 >= jeonbuk) {
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk03));
        }else
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk04));

        if(0 <= jeonnam && 30 >= jeonnam) {
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam01));
        } else if (31 <= jeonnam && 80 >= jeonnam){
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam02));
        } else if (81 <= jeonnam && 150 >= jeonnam) {
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam03));
        }else
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam04));

        if(0 <= gyeongnam && 30 >= gyeongnam) {
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam01));
        } else if (31 <= gyeongnam && 80 >= gyeongnam){
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam02));
        } else if (81 <= gyeongnam && 150 >= gyeongnam) {
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam03));
        }else
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam04));

        if(0 <= jeju && 30 >= jeju) {
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju01));
        } else if (31 <= jeju && 80 >= jeju){
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju02));
        } else if (81 <= jeju && 150 >= jeju) {
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju03));
        }else
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju04));

        if(0 <= sejong && 30 >= sejong) {
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong01));
        } else if (31 <= sejong && 80 >= sejong){
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong02));
        } else if (81 <= sejong && 150 >= sejong) {
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong03));
        }else
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong04));

        if(0 <= gyeongbuk && 30 >= gyeongbuk) {
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk01));
        } else if (31 <= gyeongbuk && 80 >= gyeongbuk){
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk02));
        } else if (81 <= gyeongbuk && 150 >= gyeongbuk) {
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk03));
        }else
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk04));




       int avg=(seoul+busan+daegu+incheon+gwangju+daejeon+ulsan+gyeonggi+gangwon+chungbuk+chungnam+jeonbuk+jeonnam+gyeongnam+jeju+sejong+gyeongbuk)/17;
        if(0 <= avg && 30 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125,200,255));

        } else if (31 <= avg && 80 >= avg){
            frameLayout.setBackgroundColor(Color.rgb(125,225 ,100));
        } else if (81 <= avg && 150 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(255,200,125));
        }else
            frameLayout.setBackgroundColor(Color.rgb(255,125,125));


    }

    public void setGrade25(ArrayList<mainfragementDTO> data){

        int position =2;

        ImageView imageView=getView().findViewById(R.id.seoulimg);
        ImageView imageView2=getView().findViewById(R.id.busanimg);
        ImageView imageView3=getView().findViewById(R.id.daeguimg);
        ImageView imageView4=getView().findViewById(R.id.incheonimg);
        ImageView imageView5=getView().findViewById(R.id.gwangjuimg);
        ImageView imageView6=getView().findViewById(R.id.daejeonimg);
        ImageView imageView7=getView().findViewById(R.id.ulsanimg);
        ImageView imageView8=getView().findViewById(R.id.gyeonggiimg);
        ImageView imageView9=getView().findViewById(R.id.gangwonimg);
        ImageView imageView10=getView().findViewById(R.id.chungbukimg);
        ImageView imageView11=getView().findViewById(R.id.chungnamimg);
        ImageView imageView12=getView().findViewById(R.id.jeonbukimg);
        ImageView imageView13=getView().findViewById(R.id.jeonnamimg);
        ImageView imageView14=getView().findViewById(R.id.gyeongnamimg);
        ImageView imageView15=getView().findViewById(R.id.jejuimg);
        ImageView imageView16=getView().findViewById(R.id.sejongimg);
        ImageView imageView17=getView().findViewById(R.id.gyeongbukimg);



        int seoul=Integer.parseInt(data.get(position).getSeoul());
        int busan=Integer.parseInt(data.get(position).getBusan());
        int daegu=Integer.parseInt(data.get(position).getDaegu());
        int incheon=Integer.parseInt(data.get(position).getIncheon());
        int gwangju=Integer.parseInt(data.get(position).getGwangju());
        int daejeon=Integer.parseInt(data.get(position).getDaejeon());
        int ulsan=Integer.parseInt(data.get(position).getUlsan());
        int gyeonggi=Integer.parseInt(data.get(position).getGyeonggi());
        int gangwon=Integer.parseInt(data.get(position).getGangwon());
        int chungbuk=Integer.parseInt(data.get(position).getChungbuk());
        int chungnam=Integer.parseInt(data.get(position).getChungnam());
        int jeonbuk=Integer.parseInt(data.get(position).getJeonbuk());
        int jeonnam=Integer.parseInt(data.get(position).getJeonnam());
        int gyeongnam=Integer.parseInt(data.get(position).getGyeongnam());
        int jeju=Integer.parseInt(data.get(position).getJeju());
        int sejong=Integer.parseInt(data.get(position).getSejong());
        int gyeongbuk=Integer.parseInt(data.get(position).getGyeongbuk());


        if(0 <= seoul && 15 >= seoul) {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul01));
        } else if (16 <= seoul && 35 >= seoul){
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul02));
        } else if (36 <= seoul && 75 >= seoul) {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul03));
        }else {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul04));
        }

        if(0 <= busan && 15 >= busan) {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan01));
        } else if (16 <= busan && 35 >= busan){
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan02));
        } else if (36 <= busan && 75 >= busan) {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan03));
        }else {
            imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan04));
        }

        if(0 <= daegu && 15 >= daegu) {
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu01));
        } else if (16 <= daegu && 35 >= daegu){
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu02));
        } else if (36 <= daegu && 75 >= daegu) {
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu03));
        }else
            imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu04));

        if(0 <= incheon && 15 >= incheon) {
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon01));
        } else if (16 <= incheon && 35 >= incheon){
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon02));
        } else if (36 <= incheon && 75 >= incheon) {
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon03));
        }else
            imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon04));

        if(0 <= gwangju && 15 >= gwangju) {
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju1));
        } else if (16 <= gwangju && 35 >= gwangju){
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju02));
        } else if (36 <= gwangju && 75 >= Integer.parseInt(data.get(position).getGwangju())) {
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju03));
        }else
            imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju04));

        if(0 <= daejeon && 15 >= daejeon) {
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon01));
        } else if (16 <= daejeon && 35 >= daejeon){
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon02));
        } else if (36 <= daejeon && 75 >= daejeon) {
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon03));
        }else
            imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon04));

        if(0 <= ulsan && 15 >= ulsan) {
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan01));
        } else if (16 <= ulsan && 35 >= ulsan){
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan02));
        } else if (36 <= ulsan && 75 >= ulsan) {
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan03));
        }else
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan04));

        if(0 <= gyeonggi && 15 >= gyeonggi) {
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi01));
        } else if (16 <= gyeonggi && 35 >= gyeonggi){
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi02));
        } else if (36 <= gyeonggi && 75 >= gyeonggi) {
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi03));
        }else
            imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi04));

        if(0 <= gangwon && 15 >= gangwon) {
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon01));
        } else if (16 <= gangwon && 35 >= gangwon){
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon02));
        } else if (36 <= gangwon && 75 >= gangwon) {
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon03));
        }else
            imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon04));

        if(0 <= chungbuk && 15 >= chungbuk) {
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk01));
        } else if (16 <= chungbuk && 35 >= chungbuk){
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk02));
        } else if (36 <= chungbuk && 75 >= chungbuk) {
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk03));
        }else
            imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk04));

        if(0 <= chungnam && 15 >= chungnam) {
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam01));
        } else if (16 <= chungnam && 35 >= chungnam){
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam02));
        } else if (36 <= chungnam && 75 >= chungnam) {
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam03));
        }else
            imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam04));

        if(0 <= jeonbuk && 15 >= jeonbuk) {
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk01));
        } else if (16 <= jeonbuk && 35 >= jeonbuk){
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk02));
        } else if (36 <= jeonbuk && 75 >= jeonbuk) {
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk03));
        }else
            imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk04));

        if(0 <= jeonnam && 15 >= jeonnam) {
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam01));
        } else if (16 <= jeonnam && 35 >= jeonnam){
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam02));
        } else if (36 <= jeonnam && 75 >= jeonnam) {
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam03));
        }else
            imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam04));

        if(0 <= gyeongnam && 15 >= gyeongnam) {
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam01));
        } else if (16 <= gyeongnam && 35 >= gyeongnam){
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam02));
        } else if (36 <= gyeongnam && 75 >= gyeongnam) {
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam03));
        }else
            imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam04));

        if(0 <= jeju && 15 >= jeju) {
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju01));
        } else if (16 <= jeju && 35 >= jeju){
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju02));
        } else if (36 <= jeju && 75 >= jeju) {
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju03));
        }else
            imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju04));

        if(0 <= sejong && 15 >= sejong) {
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong01));
        } else if (16 <= sejong && 35 >= sejong){
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong02));
        } else if (36 <= sejong && 75 >= sejong) {
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong03));
        }else
            imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong04));

        if(0 <= gyeongbuk && 15 >= gyeongbuk) {
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk01));
        } else if (16 <= gyeongbuk && 35 >= gyeongbuk){
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk02));
        } else if (36 <= gyeongbuk && 75 >= gyeongbuk) {
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk03));
        }else
            imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk04));




        int avg=(seoul+busan+daegu+incheon+gwangju+daejeon+ulsan+gyeonggi+gangwon+chungbuk+chungnam+jeonbuk+jeonnam+gyeongnam+jeju+sejong+gyeongbuk)/17;
        if(0 <= avg && 15 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(125,200,255));
        } else if (16 <= avg && 35 >= avg){
            frameLayout.setBackgroundColor(Color.rgb(125,225 ,100));
        } else if (36 <= avg && 75 >= avg) {
            frameLayout.setBackgroundColor(Color.rgb(255,200,125));
        }else
            frameLayout.setBackgroundColor(Color.rgb(255,125,125));


    }




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public class MainFragment extends AsyncTask<String, Void, ArrayList<mainfragementDTO>> {

        ArrayList<mainfragementDTO> data = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected ArrayList<mainfragementDTO> doInBackground(String... strings) {

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?itemCode=PM"+strings[0].toString()+"&dataGubun=HOUR&searchCondition=WEEK&pageNo=1&numOfRows=10&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";
            StringBuffer sb = new StringBuffer();
            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("list");

                for (int i = 0; i < jArr.length(); i++) {

                    json = jArr.getJSONObject(i);

                    String itemCode= json.getString("itemCode");
                    String seoul = json.getString("seoul");
                    String busan = json.getString("busan");
                    String daegu = json.getString("daegu");
                    String incheon = json.getString("incheon");
                    String gwangju = json.getString("gwangju");
                    String daejeon = json.getString("daejeon");
                    String ulsan = json.getString("ulsan");
                    String gyeonggi = json.getString("gyeonggi");
                    String gangwon = json.getString("gangwon");
                    String chungbuk = json.getString("chungbuk");
                    String chungnam = json.getString("chungnam");
                    String jeonbuk = json.getString("jeonbuk");
                    String jeonnam = json.getString("jeonnam");
                    String gyeongbuk = json.getString("gyeongbuk");
                    String gyeongnam = json.getString("gyeongnam");
                    String jeju = json.getString("jeju");
                    String sejong = json.getString("sejong");

                    mainfragementDTO nStation = new mainfragementDTO(itemCode,seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

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
        protected void onPostExecute(ArrayList<mainfragementDTO> data) {

            if(data.get(1).getItemCode().equals("10007")){
                 setPm10(data);
                 setGrade(data);
            }
            if(data.get(1).getItemCode().equals("10008")){
                setPm25(data);
            }
        }
    }
}

