package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    public List<Record2>record2List;
    public DataAdapter(List<Record2>trecord2){
        this.record2List=trecord2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView topic,name,conduct,venue,time,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            topic=mview.findViewById(R.id.topic);
            name=mview.findViewById(R.id.name);
            conduct=mview.findViewById(R.id.conduct);
            venue=mview.findViewById(R.id.venue);
            time=mview.findViewById(R.id.time);
            date=mview.findViewById(R.id.tdate);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.download_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.topic.setText(record2List.get(position).getTopict());
        holder.name.setText(record2List.get(position).getEventt());
        holder.conduct.setText(record2List.get(position).getConductort());
        holder.venue.setText(record2List.get(position).getVenuet());
        holder.time.setText(record2List.get(position).getTimet());
        holder.date.setText(record2List.get(position).getDatet());
        EventActivity e=new EventActivity();

    }



    @Override
    public int getItemCount() {
        return record2List.size();
    }
}