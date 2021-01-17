package com.sam.turbocare.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.turbocare.R;

import java.util.ArrayList;

public class FuelTypeAdapter extends RecyclerView.Adapter<FuelTypeAdapter.MyViewHolder4> {
    ArrayList<String> fuelList;

    private FuelTypeAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick3(View view, int position);
    }

    public void setOnEntryClickListener3(FuelTypeAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    public FuelTypeAdapter(ArrayList<String> fuelList) {
        this.fuelList = fuelList;
    }

    @NonNull
    @Override
    public MyViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fuelListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mfr_view_layout,parent,false);

        return new MyViewHolder4(fuelListView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder4 holder, int position) {

        holder.fuelTypeName.setText(fuelList.get(position));
    }

    @Override
    public int getItemCount() {
        return fuelList.size();
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fuelTypeName;

        public MyViewHolder4(@NonNull View itemView) {
            super(itemView);

            // we do this because we want to check when an item has been clicked:
            itemView.setOnClickListener(this);

            fuelTypeName = itemView.findViewById(R.id.txtMRFName);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick3(v, getLayoutPosition());
            }
        }
    }
}
