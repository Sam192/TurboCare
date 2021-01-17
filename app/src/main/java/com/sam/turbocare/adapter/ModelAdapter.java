package com.sam.turbocare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.turbocare.R;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder3> {
    ArrayList<String> modelList;

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick2(View view, int position);
    }

    public void setOnEntryClickListener2(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    public ModelAdapter(ArrayList<String> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View modelListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mfr_view_layout,parent,false);

        return new MyViewHolder3(modelListView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {

        holder.modelName.setText(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView modelName;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);

            // we do this because we want to check when an item has been clicked:
            itemView.setOnClickListener(this);

            modelName = itemView.findViewById(R.id.txtMRFName);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick2(v, getLayoutPosition());
            }
        }
    }
}