package com.example.skyobserver.member;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.MainActivity;
import com.example.skyobserver.R;
import com.example.skyobserver.msmap.RegiongpsDTO;

import java.util.ArrayList;


class RegionSelDialog {
    Dialog dial;
    private Context context;
    private Activity activity;
    RecyclerView listViewl;
    RegionSearchAdapter replyListAdapter;
    ArrayList<RegiongpsDTO> regiongpsDTO = new ArrayList<>();
    String logId;
    EditText replyedit;
    String articleNo;

    public RegionSelDialog(Context context,Activity activity) {
        this.context = context;
        this.activity=activity;

    }

    public void popBoard() {


        dial = new Dialog(context);
        dial.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dial.setContentView(R.layout.regionselect_dialog);

        EditText search =dial.findViewById(R.id.redionsearch);
       


        SharedPreferences userPref = context.getSharedPreferences("userPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

        this.logId = userPref.getString("email", "");




        listViewl = dial.findViewById(R.id.dialRepllist);
        listViewl.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        listViewl.setLayoutManager(layoutManager);
        replyListAdapter = new RegionSearchAdapter(context, regiongpsDTO);
        listViewl.setAdapter(replyListAdapter);
        // setListViewHeightBasedOnChildren(listViewl);






        dial.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dial.show();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dial.getWindow().getAttributes());
        layoutParams.width = (int) (width * 0.9);
        layoutParams.height = (int) (height * 0.5);
        Window window = dial.getWindow();
        window.setAttributes(layoutParams);



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String search=s.toString();

                try {
                    new searchRegion().execute(search);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }


    public class searchRegion extends AsyncTask<String, Void, ArrayList<RegiongpsDTO>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<RegiongpsDTO> doInBackground(String... strings) {
          ArrayList<RegiongpsDTO> regionDTO=  MainActivity.regiongpsDTO;
          ArrayList<RegiongpsDTO> searchDTO=new ArrayList<>();

            for (RegiongpsDTO dto:regionDTO) {

                if(dto.getDong().trim().contains(strings[0].trim()) || dto.getRi().trim().contains(strings[0].trim()) ){

                    searchDTO.add(dto);

                }

            }

            return searchDTO;
        }

        @Override
        protected void onPostExecute(final ArrayList<RegiongpsDTO> searchDTO) {

            replyListAdapter = new RegionSearchAdapter(context, searchDTO);
            listViewl.setAdapter(replyListAdapter);

            replyListAdapter.setOnIteclickListner(new RegionSearchAdapter.OnItemClickListener() {
                @Override
                public void onitemClick(View v, int pos) {
                    Toast.makeText(context, "!!!!"+pos, Toast.LENGTH_SHORT).show();

                    dial.cancel();
                    ((Mypage)context).setRegion(searchDTO,pos);

//                    TextView sr=activity.findViewById(R.id.selectRegion);
//                  if(!searchDTO.get(pos).getDong().equals("null")){
//                      sr.setText(searchDTO.get(pos).getDong());
//                  }else{
//                      sr.setText("");
//                  }
//                  if(!searchDTO.get(pos).getRi().equals("null")){
//                      sr.append(searchDTO.get(pos).getRi());
//                  }else{
//                      sr.append("");
//                  }


                }
            });
        }

    }

    private void replupdate() {
        try {
            new searchRegion().execute(articleNo);
            replyedit.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}






