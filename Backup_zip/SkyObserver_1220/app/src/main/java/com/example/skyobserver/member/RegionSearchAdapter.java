package com.example.skyobserver.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.R;
import com.example.skyobserver.msmap.RegiongpsDTO;

import java.util.ArrayList;

public class RegionSearchAdapter extends RecyclerView.Adapter<RegionSearchAdapter.ViewHolder> {


    Context context;
    ArrayList<RegiongpsDTO> aDto; // 상품 목록

    public RegionSearchAdapter(@NonNull Context context, @NonNull ArrayList<RegiongpsDTO> aDto) {
         this.context=context;
        this.aDto=aDto;
    }


    public interface  OnItemClickListener{
        void onitemClick(View v,int pos);
    }

    private OnItemClickListener mListner=null;

    public void setOnIteclickListner(OnItemClickListener Listner) {
        this.mListner = Listner;
    }


    public RegiongpsDTO getItem(int position) { // 상품 목록(Products) 중 position번째의 상품(Product)
        return aDto.get(position);
    }

    @NonNull
    @Override
    public RegionSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.region_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionSearchAdapter.ViewHolder holder, int position) {

        RegiongpsDTO item=aDto.get(position);
        holder.sido.setText(item.getSido()); // 홀더를 이용하여 p_price2 대입
        if(!item.getGugun().equals("null")){ holder.gugun.setText(item.getGugun());
        }else{holder.gugun.setText("");} // 홀더를 이용하여 p_name 대입
        if(!item.getDong().equals("null")){ holder.dong.setText(item.getDong());}
        else{holder.dong.setText("");}// 홀더를 이용하여 p_name 대입
        if(!item.getRi().equals("null")) {holder.ri.setText(item.getRi());}
        else{holder.ri.setText("");}// 홀더를 이용하여 p_name 대입

    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return  aDto.size();
    }

    /*@Override
    public void onClick(View view) {
        ProductListViewHolder holder = (ProductListViewHolder) view.getTag();

        // 상세화면으로 천이
        // TO-DO : 상세화면으로 천이 로직 작성할 것.
        *//*Intent intent = new Intent(this, ProductDetail.class);
        startActivity(intent);*//*
    }*/


    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView sido;
        TextView gugun;
        TextView dong;
        TextView ri;

        public ViewHolder(View view) {
            super(view);
            sido = view.findViewById(R.id.sido);
            gugun = view.findViewById(R.id.gugun);
            dong = view.findViewById(R.id.dong);
            ri = view.findViewById(R.id.ri);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION){
                        if(mListner!=null){
                            mListner.onitemClick(v,pos);
                        }

                    }
                }
            });
        }
    }


}
