package com.sam.turbocare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.turbocare.R;
import com.sam.turbocare.fragments.Vehicles;
import com.sam.turbocare.model;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewholder> {
    ArrayList<model> dataholder;

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick5(View view, int position);
    }

    public void setOnEntryClickListener5(VehicleAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    public VehicleAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vehicleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_view_layout, parent, false);

        return new MyViewholder(vehicleView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {


        holder.mVehicleNo.setText(dataholder.get(position).getREG_NO());
        holder.mVManufacturers.setText(dataholder.get(position).getMODEL());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
          TextView mVehicleNo ,mVManufacturers;




        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            // we do this because we want to check when an item has been clicked:
            itemView.setOnClickListener(this);
            mVehicleNo = itemView.findViewById(R.id.txtVehicleNumber);
            mVManufacturers = itemView.findViewById(R.id.txtVManufacturer);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick5(v, getLayoutPosition());
            }
        }
    }
}
