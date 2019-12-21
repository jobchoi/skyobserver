package com.example.skyobserver.board;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private Context context;
    private  ArrayList<BoardDTO> items;
   // private MyRecyclerViewClickListener mListener;
   private RequestManager requestManager;

    public BoardAdapter(Context context, ArrayList<BoardDTO> data , RequestManager requestManager){
     this.items=data;
     this.context=context;
     this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.boardlistcard, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BoardDTO item=items.get(position);
    holder.setItem(item);

    if(!item.getFilename().equals("null")){
        requestManager.clear(holder.cardImg);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.skipMemoryCache(true);
        requestOptions.signature(new ObjectKey(System.currentTimeMillis()));
        requestOptions.transform(new CenterCrop(),new RoundedCorners(20));


        requestManager
                .load(Common.SERVER_URL+item.getFilepath())
                .apply(requestOptions)
                .into(holder.cardImg);}
        Log.d("process","============="+item.getFilename());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              LinearLayout linearLayout=holder.itemView.findViewById(R.id.boardcontent);
             // linearLayout.setVisibility(View.VISIBLE);


          BoardDialog bDialog = new BoardDialog(context,requestManager);

              bDialog.popBoard(items.get(position));
          }
      });






    /*
        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
                }
            });
        }*/



    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<BoardDTO> getItems() {


        return items;
    }

    static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView boardTitle;
        TextView boardId;
        TextView content;
        LinearLayout linearLayout;
        ImageView cardImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.boardcontent);
            boardTitle=itemView.findViewById(R.id.boardTitle);
            boardId=itemView.findViewById(R.id.boardId);
            content=itemView.findViewById(R.id.content);
            cardImg=itemView.findViewById(R.id.cardImg);


        }


        public void setItem(BoardDTO item) {

            boardTitle.setText(item.getSubject());
            boardId.setText(item.getNickName());
            content.setText(item.getContent());



        }
    }

/*
    public void setOnclickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }


    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);


    }*/
}
