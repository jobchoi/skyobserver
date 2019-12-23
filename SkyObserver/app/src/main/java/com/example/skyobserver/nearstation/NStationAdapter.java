package com.example.skyobserver.nearstation;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skyobserver.R;

import java.util.ArrayList;

public class NStationAdapter extends RecyclerView.Adapter<NStationAdapter.ViewHolder> {

    private ArrayList<NStationDTO> items;


    public NStationAdapter(ArrayList<NStationDTO> data) {
        this.items = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NStationDTO item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stationname;
        TextView dust10text;
        TextView dust20text;
        LinearLayout dust10layout;
        LinearLayout dust20layout;
        private Object Context;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stationname = itemView.findViewById(R.id.stationname);
            dust10text = itemView.findViewById(R.id.dust10text);
            dust20text = itemView.findViewById(R.id.dust20text);
            dust10layout = itemView.findViewById(R.id.dust10layout);
            dust20layout = itemView.findViewById(R.id.dust20layout);

        }


        public void setItem(NStationDTO item) {

            stationname.setText(item.getStationName());
            dust10text.setText(item.getPm10Value());
            dust20text.setText(item.getPm25Value());
            GradientDrawable drawable10 = (GradientDrawable) dust10layout.getBackground();


            switch (item.getPm10Grade1h()) {
                case "1":
                    drawable10.setColor(Color.rgb(0, 164, 224));
                    break;
                case "2":
                    drawable10.setColor(Color.rgb(111, 192, 126));
                    break;
                case "3":
                    drawable10.setColor(Color.rgb(248, 181, 74));
                    break;
                case "4":
                    drawable10.setColor(Color.rgb(199, 43, 29));
                    break;
                case "-":
                    drawable10.setColor(Color.LTGRAY);
                    dust10text.setText("점검중");
                    break;

            }


            GradientDrawable drawable20 = (GradientDrawable) dust20layout.getBackground();
            switch (item.getPm25Grade1h()) {
                case "1":
                    drawable20.setColor(Color.rgb(0, 164, 224));
                    break;
                case "2":
                    drawable20.setColor(Color.rgb(111, 192, 126));
                    break;
                case "3":
                    drawable20.setColor(Color.rgb(248, 181, 74));
                    break;
                case "4":
                    drawable20.setColor(Color.rgb(199, 43, 29));
                    break;
                case "-":
                    drawable20.setColor(Color.LTGRAY);
                    dust20text.setText("점검중");
                    break;


            }
        }


    }
}
