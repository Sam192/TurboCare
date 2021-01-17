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
import com.sam.turbocare.adapter.ModelAdapter;

import java.util.ArrayList;

public class SelectModel extends Fragment {
    private RecyclerView mModel_recyclerView;
    String mMFRName;
    ArrayList<String> modelList;
    ModelName mModelName;

    public SelectModel() {
        // Required empty public constructor
    }

    // Container Activity must implement this interface
    public interface ModelName{
        public void modelName(String modelname);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_model, container, false);

        ((MainActivity)getActivity()).mToolbar.setTitle("Select Model");

        mModel_recyclerView = view.findViewById(R.id.model_recyclerview);
        MainActivity mainActivity = (MainActivity) getActivity();
        modelList = new ArrayList<>();
        modelList = mainActivity.getListMODELS();
        Log.d("ALIST","Model list: "+modelList);

        mModel_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ModelAdapter modelAdapter = new ModelAdapter(modelList);
        modelAdapter.setOnEntryClickListener2(new ModelAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick2(View view, int position) {
                Log.d("ALISt","You Clicked :"+ modelList.get(position));
                String modelname = modelList.get(position);
                mModelName.modelName(modelname);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SelectFuel())
                        .addToBackStack(null)
                        .commit();
            }
        });
        mModel_recyclerView.setAdapter(modelAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = getActivity();
        try {
            if (context instanceof Activity)
                mModelName = (ModelName) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MFRName Interface");
        }

    }
}