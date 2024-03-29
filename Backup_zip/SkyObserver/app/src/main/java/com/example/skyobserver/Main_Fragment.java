package com.example.skyobserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        final Button button = view.findViewById(R.id.pm25);
        final Button button2 = view.findViewById(R.id.pm10);
        // final Button button3 = view.findViewById(R.id.selector_selected);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    ImageView imageView = getView().findViewById(R.id.suchiimg);
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm25));

                    String pm25 = "25";
                    new MainFragment().execute(pm25);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    button2.setBackgroundColor(gr);
//
//                    button.setBackgroundColor(or);

                    ImageView imageView = getView().findViewById(R.id.suchiimg);
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.pm10));;

                    String pm10 = "10";
                    new MainFragment().execute(pm10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //  TextView textView=view.findViewById(R.id.seoul);
        //  textView.setText("asdfasdfdf");

        try {
            new MainFragment().execute("10");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
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
        protected ArrayList<mainfragementDTO> doInBackground(String... strings) {

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?itemCode=PM"+strings[0].toString()+"&dataGubun=HOUR&searchCondition=WEEK&pageNo=1&numOfRows=10&ServiceKey=YDX9bhqzzi6UFHEraxXWeH%2FubajSBOmM4674vxXkNJLOrRm4IBlLSy8nJOgN%2Bmv%2Bq1MebN7zvZ7AmwsXzIRSVQ%3D%3D&_returnType=json";
            StringBuffer sb = new StringBuffer();
            try {

                String jsonPage = getStringFromUrl(url);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("list");

                for (int i = 0; i < jArr.length(); i++) {

                    json = jArr.getJSONObject(i);

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


                    mainfragementDTO nStation = new mainfragementDTO(seoul, busan, daegu, incheon, gwangju, daejeon, ulsan, gyeonggi, gangwon, chungbuk, chungnam, jeonbuk, jeonnam, gyeongbuk, gyeongnam, jeju, sejong);

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

            ImageView imageView=getView().findViewById(R.id.seoulimg);
                //imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul02));

                if(0 <= Integer.parseInt(data.get(2).getSeoul()) && 30 >= Integer.parseInt(data.get(2).getSeoul())) {
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul01));
            } else if (31 <= Integer.parseInt(data.get(2).getSeoul()) && 80 >= Integer.parseInt(data.get(2).getSeoul())){
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(),R.drawable.seoul02));
            } else if (81 <= Integer.parseInt(data.get(2).getSeoul()) && 150 >= Integer.parseInt(data.get(2).getSeoul())) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul03));
                }else
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.seoul04));

            ImageView imageView2=getView().findViewById(R.id.busanimg);

            if(0 <= Integer.parseInt(data.get(2).getBusan()) && 30 >= Integer.parseInt(data.get(2).getBusan())) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan01));
            } else if (31 <= Integer.parseInt(data.get(2).getBusan()) && 80 >= Integer.parseInt(data.get(2).getBusan())){
                imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(),R.drawable.busan02));
            } else if (81 <= Integer.parseInt(data.get(2).getBusan()) && 150 >= Integer.parseInt(data.get(2).getBusan())) {
                imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan03));
            }else
                imageView2.setImageDrawable(ContextCompat.getDrawable(imageView2.getContext(), R.drawable.busan04));

            ImageView imageView3=getView().findViewById(R.id.daeguimg);

            if(0 <= Integer.parseInt(data.get(2).getDaegu()) && 30 >= Integer.parseInt(data.get(2).getDaegu())) {
                imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu01));
            } else if (31 <= Integer.parseInt(data.get(2).getDaegu()) && 80 >= Integer.parseInt(data.get(2).getDaegu())){
                imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(),R.drawable.daegu02));
            } else if (81 <= Integer.parseInt(data.get(2).getDaegu()) && 150 >= Integer.parseInt(data.get(2).getDaegu())) {
                imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu03));
            }else
                imageView3.setImageDrawable(ContextCompat.getDrawable(imageView3.getContext(), R.drawable.daegu04));


            ImageView imageView4=getView().findViewById(R.id.incheonimg);

            if(0 <= Integer.parseInt(data.get(2).getIncheon()) && 30 >= Integer.parseInt(data.get(2).getIncheon())) {
                imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon01));
            } else if (31 <= Integer.parseInt(data.get(2).getIncheon()) && 80 >= Integer.parseInt(data.get(2).getIncheon())){
                imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(),R.drawable.incheon02));
            } else if (81 <= Integer.parseInt(data.get(2).getIncheon()) && 150 >= Integer.parseInt(data.get(2).getIncheon())) {
                imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon03));
            }else
                imageView4.setImageDrawable(ContextCompat.getDrawable(imageView4.getContext(), R.drawable.incheon04));

            ImageView imageView5=getView().findViewById(R.id.gwangjuimg);

            if(0 <= Integer.parseInt(data.get(2).getGwangju()) && 30 >= Integer.parseInt(data.get(2).getGwangju())) {
                imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju1));
            } else if (31 <= Integer.parseInt(data.get(2).getGwangju()) && 80 >= Integer.parseInt(data.get(2).getGwangju())){
                imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(),R.drawable.gwangju02));
            } else if (81 <= Integer.parseInt(data.get(2).getGwangju()) && 150 >= Integer.parseInt(data.get(2).getGwangju())) {
                imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju03));
            }else
                imageView5.setImageDrawable(ContextCompat.getDrawable(imageView5.getContext(), R.drawable.gwangju04));

            ImageView imageView6=getView().findViewById(R.id.daejeonimg);

            if(0 <= Integer.parseInt(data.get(2).getDaejeon()) && 30 >= Integer.parseInt(data.get(2).getDaejeon())) {
                imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon01));
            } else if (31 <= Integer.parseInt(data.get(2).getDaejeon()) && 80 >= Integer.parseInt(data.get(2).getDaejeon())){
                imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(),R.drawable.daejeon02));
            } else if (81 <= Integer.parseInt(data.get(2).getDaejeon()) && 150 >= Integer.parseInt(data.get(2).getDaejeon())) {
                imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon03));
            }else
                imageView6.setImageDrawable(ContextCompat.getDrawable(imageView6.getContext(), R.drawable.daejeon04));

            ImageView imageView7=getView().findViewById(R.id.ulsanimg);

            if(0 <= Integer.parseInt(data.get(2).getUlsan()) && 30 >= Integer.parseInt(data.get(2).getUlsan())) {
                imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan01));
            } else if (31 <= Integer.parseInt(data.get(2).getUlsan()) && 80 >= Integer.parseInt(data.get(2).getUlsan())){
                imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ulsan02));
            } else if (81 <= Integer.parseInt(data.get(2).getUlsan()) && 150 >= Integer.parseInt(data.get(2).getUlsan())) {
                imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan03));
            }else
                imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ulsan04));

            ImageView imageView8=getView().findViewById(R.id.gyeonggiimg);

            if(0 <= Integer.parseInt(data.get(2).getGyeonggi()) && 30 >= Integer.parseInt(data.get(2).getGyeonggi())) {
                imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi01));
            } else if (31 <= Integer.parseInt(data.get(2).getGyeonggi()) && 80 >= Integer.parseInt(data.get(2).getGyeonggi())){
                imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(),R.drawable.gyeonggi02));
            } else if (81 <= Integer.parseInt(data.get(2).getGyeonggi()) && 150 >= Integer.parseInt(data.get(2).getGyeonggi())) {
                imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi03));
            }else
                imageView8.setImageDrawable(ContextCompat.getDrawable(imageView8.getContext(), R.drawable.gyeonggi04));

            ImageView imageView9=getView().findViewById(R.id.gangwonimg);

            if(0 <= Integer.parseInt(data.get(2).getGangwon()) && 30 >= Integer.parseInt(data.get(2).getGangwon())) {
                imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon01));
            } else if (31 <= Integer.parseInt(data.get(2).getGangwon()) && 80 >= Integer.parseInt(data.get(2).getGangwon())){
                imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(),R.drawable.gangwon02));
            } else if (81 <= Integer.parseInt(data.get(2).getGangwon()) && 150 >= Integer.parseInt(data.get(2).getGangwon())) {
                imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon03));
            }else
                imageView9.setImageDrawable(ContextCompat.getDrawable(imageView9.getContext(), R.drawable.gangwon04));

            ImageView imageView10=getView().findViewById(R.id.chungbukimg);

            if(0 <= Integer.parseInt(data.get(2).getChungbuk()) && 30 >= Integer.parseInt(data.get(2).getChungbuk())) {
                imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk01));
            } else if (31 <= Integer.parseInt(data.get(2).getChungbuk()) && 80 >= Integer.parseInt(data.get(2).getChungbuk())){
                imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(),R.drawable.chungbuk02));
            } else if (81 <= Integer.parseInt(data.get(2).getChungbuk()) && 150 >= Integer.parseInt(data.get(2).getChungbuk())) {
                imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk03));
            }else
                imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.chungbuk04));

            ImageView imageView11=getView().findViewById(R.id.chungnamimg);

            if(0 <= Integer.parseInt(data.get(2).getChungnam()) && 30 >= Integer.parseInt(data.get(2).getChungnam())) {
                imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam01));
            } else if (31 <= Integer.parseInt(data.get(2).getChungnam()) && 80 >= Integer.parseInt(data.get(2).getChungnam())){
                imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(),R.drawable.chungnam02));
            } else if (81 <= Integer.parseInt(data.get(2).getChungnam()) && 150 >= Integer.parseInt(data.get(2).getChungnam())) {
                imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam03));
            }else
                imageView11.setImageDrawable(ContextCompat.getDrawable(imageView11.getContext(), R.drawable.chungnam04));

            ImageView imageView12=getView().findViewById(R.id.jeonbukimg);

            if(0 <= Integer.parseInt(data.get(2).getJeonbuk()) && 30 >= Integer.parseInt(data.get(2).getJeonbuk())) {
                imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk01));
            } else if (31 <= Integer.parseInt(data.get(2).getJeonbuk()) && 80 >= Integer.parseInt(data.get(2).getJeonbuk())){
                imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(),R.drawable.jeonbuk02));
            } else if (81 <= Integer.parseInt(data.get(2).getJeonbuk()) && 150 >= Integer.parseInt(data.get(2).getJeonbuk())) {
                imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk03));
            }else
                imageView12.setImageDrawable(ContextCompat.getDrawable(imageView12.getContext(), R.drawable.jeonbuk04));

            ImageView imageView13=getView().findViewById(R.id.jeonnamimg);

            if(0 <= Integer.parseInt(data.get(2).getJeonnam()) && 30 >= Integer.parseInt(data.get(2).getJeonnam())) {
                imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam01));
            } else if (31 <= Integer.parseInt(data.get(2).getJeonnam()) && 80 >= Integer.parseInt(data.get(2).getJeonnam())){
                imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(),R.drawable.jeonnam02));
            } else if (81 <= Integer.parseInt(data.get(2).getJeonnam()) && 150 >= Integer.parseInt(data.get(2).getJeonnam())) {
                imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam03));
            }else
                imageView13.setImageDrawable(ContextCompat.getDrawable(imageView13.getContext(), R.drawable.jeonnam04));

            ImageView imageView14=getView().findViewById(R.id.gyeongnamimg);

            if(0 <= Integer.parseInt(data.get(2).getGyeongnam()) && 30 >= Integer.parseInt(data.get(2).getGyeongnam())) {
                imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam01));
            } else if (31 <= Integer.parseInt(data.get(2).getGyeongnam()) && 80 >= Integer.parseInt(data.get(2).getGyeongnam())){
                imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(),R.drawable.gyeongnam02));
            } else if (81 <= Integer.parseInt(data.get(2).getGyeongnam()) && 150 >= Integer.parseInt(data.get(2).getGyeongnam())) {
                imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam03));
            }else
                imageView14.setImageDrawable(ContextCompat.getDrawable(imageView14.getContext(), R.drawable.gyeongnam04));

            ImageView imageView15=getView().findViewById(R.id.jejuimg);

            if(0 <= Integer.parseInt(data.get(2).getJeju()) && 30 >= Integer.parseInt(data.get(2).getJeju())) {
                imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju01));
            } else if (31 <= Integer.parseInt(data.get(2).getJeju()) && 80 >= Integer.parseInt(data.get(2).getJeju())){
                imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(),R.drawable.jeju02));
            } else if (81 <= Integer.parseInt(data.get(2).getJeju()) && 150 >= Integer.parseInt(data.get(2).getJeju())) {
                imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju03));
            }else
                imageView15.setImageDrawable(ContextCompat.getDrawable(imageView15.getContext(), R.drawable.jeju04));

            ImageView imageView16=getView().findViewById(R.id.sejongimg);

            if(0 <= Integer.parseInt(data.get(2).getSejong()) && 30 >= Integer.parseInt(data.get(2).getSejong())) {
                imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong01));
            } else if (31 <= Integer.parseInt(data.get(2).getSejong()) && 80 >= Integer.parseInt(data.get(2).getSejong())){
                imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(),R.drawable.sejong02));
            } else if (81 <= Integer.parseInt(data.get(2).getSejong()) && 150 >= Integer.parseInt(data.get(2).getSejong())) {
                imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong03));
            }else
                imageView16.setImageDrawable(ContextCompat.getDrawable(imageView16.getContext(), R.drawable.sejong04));



            ImageView imageView17=getView().findViewById(R.id.gyeongbukimg);

            if(0 <= Integer.parseInt(data.get(2).getGyeongbuk()) && 30 >= Integer.parseInt(data.get(2).getGyeongbuk())) {
                imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk01));
            } else if (31 <= Integer.parseInt(data.get(2).getGyeongbuk()) && 80 >= Integer.parseInt(data.get(2).getGyeongbuk())){
                imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(),R.drawable.gyeongbuk02));
            } else if (81 <= Integer.parseInt(data.get(2).getGyeongbuk()) && 150 >= Integer.parseInt(data.get(2).getGyeongbuk())) {
                imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk03));
            }else
                imageView17.setImageDrawable(ContextCompat.getDrawable(imageView17.getContext(), R.drawable.gyeongbuk04));







    }

    }
}

