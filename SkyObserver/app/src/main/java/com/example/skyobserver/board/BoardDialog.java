package com.example.skyobserver.board;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


class BoardDialog {

    private Context context;
    private RequestManager requestManager;
    RecyclerView listViewl;
   ReplyListAdapter replyListAdapter;
    ArrayList<ArticleReplyDTO> articleReplyDTO=new ArrayList<>();
    String logId;
    EditText replyedit;
    String articleNo;

    public BoardDialog(Context context, RequestManager requestManager) {
        this.context = context;
        this.requestManager = requestManager;
    }

    public void popBoard(final BoardDTO dto) {

        final Dialog dial = new Dialog(context);

        dial.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dial.setContentView(R.layout.board_dialog);

        TextView title = dial.findViewById(R.id.dialogTitle);
        TextView id = dial.findViewById(R.id.dialogId);
        TextView contents = dial.findViewById(R.id.dialogcontents);
        ImageView boardImg = dial.findViewById(R.id.dialogImg);
        Button deleteBtn=dial.findViewById(R.id.dialDeleteBtn);
        Button modifyBtn=dial.findViewById(R.id.dialModifyBtn);
        Button reply=dial.findViewById(R.id.replybtn);
        LinearLayout deleteMOdifyBtn=dial.findViewById(R.id.deleteMOdifyBtn);
        replyedit=dial.findViewById(R.id.replyEditText);


        SharedPreferences userPref = context.getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

         this.logId=userPref.getString("email", "");


         if(dto.getEmail().equals(logId)){
                      deleteMOdifyBtn.setVisibility(View.VISIBLE);
         }


        title.setText(dto.getSUBJECT());
        id.setText(dto.getName());
        contents.setText(dto.getCONTENT());
        if (dto.getFILENAME() != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.skipMemoryCache(true);
            requestOptions.signature(new ObjectKey(System.currentTimeMillis()));
            requestManager.load(dto.getFILENAME()).apply(requestOptions).into(boardImg);
        }

        listViewl=dial.findViewById(R.id.dialRepllist);
        listViewl.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        listViewl.setLayoutManager(layoutManager);
        replyListAdapter=new ReplyListAdapter(context,articleReplyDTO);
        listViewl.setAdapter(replyListAdapter);
       // setListViewHeightBasedOnChildren(listViewl);


        this.articleNo = dto.getARTICLENO();

        try {
            new replyLoad().execute(articleNo);
        } catch (Exception e) {
            e.printStackTrace();


        }


        dial.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dial.show();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dial.getWindow().getAttributes());
        layoutParams.width = (int) (width * 0.9);
        layoutParams.height = (int) (height * 0.8);
        Window window = dial.getWindow();
        window.setAttributes(layoutParams);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    new replyWrite().execute(articleNo);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AlertDialog.Builder deleteconfirm=new AlertDialog.Builder(context);
                deleteconfirm.setMessage("삭제하시겠습니까?");
                deleteconfirm.setCancelable(false);
                deleteconfirm.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String del = "delete";

                        try {
                            new deletModift().execute(del, dto.getARTICLENO()).get();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                            dial.cancel();
                    }
                });
                deleteconfirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog confirm=deleteconfirm.create();

              confirm.show();




            }
        });


    }
/* 리스트뷰 스크롤 마지막에서 길이 늘리기
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }*/






    public void updata(ArrayList<ArticleReplyDTO> articleReplyDTO ){

        this.articleReplyDTO = articleReplyDTO; // 새로 가져온 상품으로 변경
     //   replyListAdapter.clear(); // 어댑터 청소
       // replyListAdapter.addAll(articleReplyDTO); // 어댑터에 새롭게 찾은 상품 목록 넣기
        replyListAdapter.notifyDataSetChanged(); // 화면에 반영
    }


    public class replyLoad extends AsyncTask<String, Void, ArrayList<ArticleReplyDTO>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<ArticleReplyDTO> doInBackground(String... strings) {

            ArrayList<ArticleReplyDTO> data = new ArrayList<>();
            String url = Common.SERVER_URL+"/articlereplyload.hanul";

            try {

                String jsonPage = getStringFromUrl(url, strings[0]);


                JSONObject json9 = new JSONObject(jsonPage);
                JSONArray jArr = json9.getJSONArray("reply");
                Log.d("reply:", jArr.toString());
                for (int i = 0; i < jArr.length(); i++) {

                    json9 = jArr.getJSONObject(i);


                    String articleNo = json9.getString("articleNo");
                    String replyunum = json9.getString("replyunum");
                    String replycon = json9.getString("relycon");
                    String name = json9.getString("name");
                    String sumnail = json9.getString("sumnail");
                    String heart = json9.getString("heart");
                    String replydate = json9.getString("replydate");

                    ArticleReplyDTO aDTO = new ArticleReplyDTO(articleNo, replyunum, replycon, name, sumnail, heart, replydate);
                    data.add(aDTO);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        public String getStringFromUrl(String pUrl, String strings) {

            BufferedReader bufreader = null;
            HttpURLConnection urlConnection = null;

            StringBuffer page = new StringBuffer();
            try {
                URL url = new URL(pUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setDefaultUseCaches(false);


                OutputStream out_stream = urlConnection.getOutputStream();

                out_stream.write(strings.getBytes("UTF-8"));
                out_stream.flush();
                out_stream.close();


                InputStream contentStream = urlConnection.getInputStream();

                bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
                String line = null;

                while ((line = bufreader.readLine()) != null) {
                    //Log.d("reply:", line);
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
        protected void onPostExecute(ArrayList<ArticleReplyDTO> articleReplyDTO) {

            replyListAdapter=new ReplyListAdapter(context,articleReplyDTO);
            listViewl.setAdapter(replyListAdapter);
           // updata(articleReplyDTO);
        }

    }
        public class replyWrite extends AsyncTask<String, Void, String> {



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                String page=null;
                ArrayList<ArticleReplyDTO> data = new ArrayList<>();
                String url = Common.SERVER_URL+"/dialreplywrite.hanul";

                try {

                   page= getStringFromUrl(url, strings[0]);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return  page;
            }

            public String getStringFromUrl(String pUrl, String strings) {

                BufferedReader bufreader = null;
                HttpURLConnection urlConnection = null;

                String rr=replyedit.getText().toString();

                String reply= "{\"articleNo\":\""+strings+"\",\"id\":\""+logId+"\",\"relycon\":\""+rr+"\"}";

                StringBuffer page = new StringBuffer();
                try {
                    URL url = new URL(pUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setUseCaches(false);
                    urlConnection.setDefaultUseCaches(false);


                    OutputStream out_stream = urlConnection.getOutputStream();

                    out_stream.write(reply.getBytes("UTF-8"));
                    out_stream.flush();
                    out_stream.close();


                    InputStream contentStream = urlConnection.getInputStream();

                    bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
                    String line = null;

                    while ((line = bufreader.readLine()) != null) {
                        //Log.d("reply:", line);
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
            protected void onPostExecute(String page) {



                if(page.equals("1")) {
                    Toast.makeText(context, "등록", Toast.LENGTH_SHORT).show();
                    replupdate();
                }else{
                    Toast.makeText(context, "등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
               }



            }







        }

    private void replupdate() {
        try {
            new replyLoad().execute(articleNo);
            replyedit.setText(null);
        } catch (Exception e) {
            e.printStackTrace();


        }

    }





    public class deletModift extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String page=null;

            try {

                 getStringFromUrl(params[0], params[1]);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return  page;
        }

        public String getStringFromUrl(String select,String articleNo) {

             BufferedReader bufreader = null;
            HttpURLConnection urlConnection = null;

             StringBuffer page = new StringBuffer();
            try {

                String pUrl=null;
                if(select.equals("delete")) {
                    pUrl = Common.SERVER_URL+"/androidremove.hanul";
                }else if(select.equals("modify")){
                    Toast.makeText(context, "구현중", Toast.LENGTH_SHORT).show();
                }

                URL url = new URL(pUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setDefaultUseCaches(false);



                OutputStream out_stream = urlConnection.getOutputStream();

                out_stream.write(articleNo.getBytes("UTF-8"));
                out_stream.flush();
                out_stream.close();


            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
            String line = null;

            while ((line = bufreader.readLine()) != null) {
                Log.d("reply:", line);
                page.append(line);
            }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                 bufreader.close();
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           return page.toString();
        }

        @Override
        protected void onPostExecute(String page) {



        //    if(page.equals("1")) {
                Toast.makeText(context, "삭제", Toast.LENGTH_SHORT).show();

                ((BoardActivity)context).reload();
//
//          }else{
//               Toast.makeText(context, "삭제실패.", Toast.LENGTH_SHORT).show();
//           }



        }







    }










}






