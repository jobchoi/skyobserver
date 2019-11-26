package com.example.skyobserver.board;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.skyobserver.R;

import java.util.ArrayList;
import java.util.List;

public class ReplyListAdapter extends ArrayAdapter<ArticleReplyDTO>  {


    Context context;
    ArrayList<ArticleReplyDTO> aDto; // 상품 목록

    public ReplyListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ArticleReplyDTO> aDto) {
        super(context, resource, aDto);
        this.context=context;
        this.aDto=aDto;
    }


    public int getCount() {
        return aDto.size();
    }

    public ArticleReplyDTO getItem(int position) { // 상품 목록(Products) 중 position번째의 상품(Product)
        return aDto.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final ProductListViewHolder holder;
            // 어댑터에서 레이아웃 준비
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView != null) { // 뷰홀더가 있다면 뷰 홀더를 사용
                holder = (ProductListViewHolder) convertView.getTag();
            } else { // 뷰홀더가 없다면 뷰 홀더 생성, 하나의 아이템이 하나의 홀더.
                // findViewById() 컴포넌트 생성 부분이 뷰홀더로 들어감.
                holder = new ProductListViewHolder(convertView = inflater.inflate(R.layout.replylist, null));
            }

           /* if (aDto.get(position).getP_imgUrl() != null) { // 이미지 주소가 있다면
                Log.i("imgUrl ", "====================" + products.get(position).getP_imgUrl());
                Bitmap bmp = pir.loadImage(products.get(position).getP_imgUrl(), this); // 어댑터 추가

                if (bmp != null) { // 상품 정보에 이미지 주소가 있다면 이미지가 저장되어 있는 ProductImageListRequest에서 이미지를 찾아서 가져오고
                    holder.productImageView.setImageBitmap(bmp);
                } else { // 그렇지 않으면 images_empty.png를 보여줌
                    holder.productImageView.setImageResource(R.drawable.images_empty);
                }
            } else {
                holder.productImageView.setImageResource(R.drawable.images_empty);
            }*/


            holder.nicname.setText(aDto.get(position).getName()); // 홀더를 이용하여 p_price2 대입
            holder.recontent.setText(aDto.get(position).getReplycon()); // 홀더를 이용하여 p_name 대입

            this.notifyDataSetChanged(); // 어댑터에 입력된 정보를 반영
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    /*@Override
    public void onClick(View view) {
        ProductListViewHolder holder = (ProductListViewHolder) view.getTag();

        // 상세화면으로 천이
        // TO-DO : 상세화면으로 천이 로직 작성할 것.
        *//*Intent intent = new Intent(this, ProductDetail.class);
        startActivity(intent);*//*
    }*/


    class ProductListViewHolder {
        public TextView nicname;
        public TextView recontent;

        public ProductListViewHolder(View view) {
            nicname = view.findViewById(R.id.renicname);
            recontent = view.findViewById(R.id.recontent);
            view.setTag(this);
        }
    }




}
