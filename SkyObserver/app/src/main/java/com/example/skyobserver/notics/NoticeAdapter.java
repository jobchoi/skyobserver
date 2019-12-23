package com.example.skyobserver.notics;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NoticeDTO> items;
    // private MyRecyclerViewClickListener mListener;

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;


    public NoticeAdapter(Context context, ArrayList<NoticeDTO> data) {
        this.items = data;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final NoticeDTO item = items.get(position);
        holder.setItem(item, position);

//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  LinearLayout linearLayout=holder.itemView.findViewById(R.id.boardcontent);
//                // linearLayout.setVisibility(View.VISIBLE);
//                holder.noticscontent.setBackgroundColor(Color.RED);
//            }
//        });






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

    public ArrayList<NoticeDTO> getItems() {


        return items;
    }


    void addItem(NoticeDTO item) {
        // 외부에서 item을 추가시킬 함수입니다.
        items.add(item);
    }

   class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView noticstitle;
        TextView noticscontent;
        TextView noticsdate;
        private int position;
       LinearLayout ni;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noticstitle = itemView.findViewById(R.id.noticstitle);
            noticscontent = itemView.findViewById(R.id.noticscontent);
            noticsdate = itemView.findViewById(R.id.noticsdate);
             ni=itemView.findViewById(R.id.noticeitem);

        }


        public void setItem(NoticeDTO item, int position) {


            this.position = position;

            noticstitle.setText(item.getTitle());
            noticscontent.setText(item.getContent());
            noticsdate.setText(item.getWritedate());

            changeVisibility(selectedItems.get(position));

            noticstitle.setOnClickListener(this);
            noticscontent.setOnClickListener(this);
            noticsdate.setOnClickListener(this);
            ni.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.noticstitle:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
            }


        }

        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 150;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    noticscontent.getLayoutParams().height = value;
                    noticscontent.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    noticscontent.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }


/*
    public void setOnclickListener(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }


    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);


    }*/
    }
}