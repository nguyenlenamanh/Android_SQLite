package com.example.student.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private Context context;
    private int resource;
    private ArrayList<SinhVien> sinhViens;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien> sinhViens) {
        super(context, resource, sinhViens);

        this.context = context;
        this.resource = resource;
        this.sinhViens = sinhViens;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);

        TextView tvID = convertView.findViewById(R.id.tvIdSV);
        TextView tvName = convertView.findViewById(R.id.tvNameSV);

        SinhVien sinhvien = sinhViens.get(position);

        tvID.setText(Integer.toString(sinhvien.getId()));
        tvName.setText(sinhvien.getName());

        return convertView;
    }
}
