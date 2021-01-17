package com.sam.turbocare.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;
import com.sam.turbocare.adapter.MFRAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class SelectMFR extends Fragment {
    private RecyclerView mMFR_recyclerView;
    String mMFRName;
    ArrayList<String> mfrlist;
    MRFName mrfNames;



    public SelectMFR() {
        // Required empty public constructor
    }

    // Container Activity must implement this interface
    public interface MRFName{
        public void mfrName(String mfrname);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_m_f_r, container, false);

        ((MainActivity)getActivity()).mToolbar.setTitle("Select Manufacturer");

        MainActivity mainActivity = (MainActivity) getActivity();
        mMFR_recyclerView = view.findViewById(R.id.mfr_recyclerview);
//        mfrlist = new ArrayList<String>(Arrays.asList(mainActivity.getListMRF()));
        mfrlist = new ArrayList<>();
        mfrlist = mainActivity.getListMRF();
        Log.d("ALIST","list: "+mfrlist);
        mMFR_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        MFRAdapter mfrAdapter = new MFRAdapter(mfrlist);
        mfrAdapter.setOnEntryClickListener(new MFRAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Log.d("ALISt","You Clicked :"+ mfrlist.get(position));
                String manufacturerName = mfrlist.get(position);
                mrfNames.mfrName(manufacturerName);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SelectModel())
                        .addToBackStack(null)
                        .commit();
            }
        });
        mMFR_recyclerView.setAdapter(mfrAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = getActivity();
        try {
            if (context instanceof Activity)
                mrfNames = (MRFName) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MFRName Interface");
        }

    }
}