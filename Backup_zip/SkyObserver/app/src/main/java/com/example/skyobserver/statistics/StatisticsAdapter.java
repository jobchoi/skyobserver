package com.example.skyobserver.statistics;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.R;
import com.example.skyobserver.mainfragementDTO;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private  ArrayList<mainfragementDTO> mDataList;

    public StatisticsAdapter(ArrayList<mainfragementDTO> dataList) {
        mDataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mainfragementDTO item = mDataList.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() { return mDataList.size(); }

    static class  ViewHolder extends RecyclerView.ViewHolder{

        TextView seoul;
        LinearLayout seoul1;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            seoul=itemView.findViewById(R.id.seoul21);

            Log.d("d", "된다.");
        }


        public void setItem(mainfragementDTO item){


            seoul.setText(item.getSeoul());




            }
        }



    }
