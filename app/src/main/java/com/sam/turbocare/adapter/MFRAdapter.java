package com.sam.turbocare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.turbocare.R;

import java.util.ArrayList;

public class MFRAdapter extends RecyclerView.Adapter<MFRAdapter.MyViewHolder2> {
    ArrayList<String> mfrList;

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    public MFRAdapter(ArrayList<String> mfrList) {
        this.mfrList = mfrList;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mfrListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mfr_view_layout,parent,false);

        return new MyViewHolder2(mfrListView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        holder.mfrName.setText(mfrList.get(position));
    }

    @Override
    public int getItemCount() {
        return mfrList.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mfrName;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            // we do this because we want to check when an item has been clicked:
            itemView.setOnClickListener(this);

            mfrName = itemView.findViewById(R.id.txtMRFName);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }
}
