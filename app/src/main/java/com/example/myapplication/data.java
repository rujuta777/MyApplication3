package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class data extends RecyclerView.Adapter {


    public List<Record> recordList;
    public data(List<Record>trecord){
        this.recordList=trecord;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView Object,Place,Description,time,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            final EditText Object= mview.findViewById(R.id.editText);
            final EditText  Place = mview.findViewById(R.id.editText2);
            final EditText Description = mview.findViewById(R.id.editText5);
            final TimePicker time= mview.findViewById(R.id.timePicker);
            final DatePicker date=mview.findViewById(R.id.datePicker);



        }
    }
    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_landf, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Object.setText(recordList.get(position).getTopict());
        holder.Place.setText(recordList.get(position).getEventt());
        holder.Description.setText(recordList.get(position).getEventt());
        holder.time.setText(recordList.get(position).getTimet());
        holder.date.setText(recordList.get(position).getDatet());
    }
    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
