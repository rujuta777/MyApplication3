package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter2 extends RecyclerView.Adapter<DataAdapter2.ViewHolder>
{
    public List<Record3>record3List;
    public DataAdapter2(List<Record3>trecord2){
        this.record3List=trecord2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView name,roll,class1,batch,mobile,email,atte;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            name=mview.findViewById(R.id.name_format);
            class1=mview.findViewById(R.id.class_format);
            batch=mview.findViewById(R.id.batch_format);
            roll=mview.findViewById(R.id.roll_format);
            mobile=mview.findViewById(R.id.mobile_format);
            email=mview.findViewById(R.id.email_format);
            atte=mview.findViewById(R.id.Attendance_format);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_format,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.name.setText(record3List.get(position).getNamet());
        holder.class1.setText(record3List.get(position).getClasst());
        holder.batch.setText(record3List.get(position).getDivt());
        holder.roll.setText(record3List.get(position).getRollt());
        holder.mobile.setText(record3List.get(position).getMobilet());
        holder.email.setText(record3List.get(position).getEmailt());
        holder.atte.setText(record3List.get(position).getAttendance());
    }

    @Override
    public int getItemCount()
    {
        return record3List.size();
    }
}