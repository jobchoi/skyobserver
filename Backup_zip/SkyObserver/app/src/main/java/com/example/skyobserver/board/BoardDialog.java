package com.example.skyobserver.board;


import android.app.Dialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
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
    ListView listViewl;
   ArrayAdapter replyListAdapter;
    ArrayList<ArticleReplyDTO> articleReplyDTO=new ArrayList<>();

    public BoardDialog(Context context, RequestManager requestManager) {
        this.context = context;
        this.requestManager = requestManager;
    }

    public void popBoard(BoardDTO dto) {

        final Dialog dial = new Dialog(context);

        dial.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dial.setContentView(R.layout.board_dialog);

        TextView title = dial.findViewById(R.id.dialogTitle);
        TextView id = dial.findViewById(R.id.dialogId);
        TextView contents = dial.findViewById(R.id.dialogcontents);
        ImageView boardImg = dial.findViewById(R.id.dialogImg);

        title.setText(dto.getSUBJECT());
        id.setText(dto.getID());
        contents.setText(dto.getCONTENT());
        if (dto.getFILENAME() != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.skipMemoryCache(true);
            requestOptions.signature(new ObjectKey(System.currentTimeMillis()));



            requestManager.load(dto.getFILENAME()).apply(requestOptions).into(boardImg);
        }

        listViewl=dial.findViewById(R.id.dialRepllist);
        replyListAdapter=new ReplyListAdapter(context,android.R.layout.simple_list_item_1,articleReplyDTO);
        listViewl.setAdapter(replyListAdapter);
       // setListViewHeightBasedOnChildren(listViewl);


        String articleNo = dto.getARTICLENO();

        try {
            new testst().execute(articleNo);
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


    }

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
    }






    public void updata(ArrayList<ArticleReplyDTO> articleReplyDTO ){

        this.articleReplyDTO = articleReplyDTO; // 새로 가져온 상품으로 변경
        replyListAdapter.clear(); // 어댑터 청소
        replyListAdapter.addAll(articleReplyDTO); // 어댑터에 새롭게 찾은 상품 목록 넣기
        replyListAdapter.notifyDataSetChanged(); // 화면에 반영
    }


    public class testst extends AsyncTask<String, Void, ArrayList<ArticleReplyDTO>> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected  ArrayList<ArticleReplyDTO> doInBackground(String... strings) {

            ArrayList<ArticleReplyDTO> data = new ArrayList<>();
            String url = "http://192.168.0.14:8081/hanulshop/articlereplyload.hanul";

            try {

                String jsonPage = getStringFromUrl(url, strings[0]);


                JSONObject json9 = new JSONObject(jsonPage);
                JSONArray jArr = json9.getJSONArray("reply");
                Log.d("reply:", jArr.toString());
                for (int i = 0; i < jArr.length(); i++) {

                    json9 = jArr.getJSONObject(i);


                     String articleNo=json9.getString("articleNo");
                     String replyunum=json9.getString("replyunum");
                     String replycon=json9.getString("relycon");
                     String name=json9.getString("name");
                     String sumnail=json9.getString("sumnail");
                     String heart=json9.getString("heart");
                     String replydate=json9.getString("replydate");

                    ArticleReplyDTO aDTO=new ArticleReplyDTO(articleNo,replyunum,replycon,name,sumnail,heart,replydate);
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



            updata(articleReplyDTO);




        }
    }
}






