package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter3 extends RecyclerView.Adapter<DataAdapter3.ViewHolder>
{
    public List<Record4>record4List;
    public DataAdapter3(List<Record4>trecord4){
        this.record4List=trecord4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView notice_display;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            notice_display=mview.findViewById(R.id.noticedisplay);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.download_data2,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        holder.notice_display.setText(record4List.get(position).getNotice());
    }
    @Override
    public int getItemCount() {
        return record4List.size();
    }
}