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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;
import com.sam.turbocare.adapter.FuelTypeAdapter;

import java.util.ArrayList;


public class SelectFuel extends Fragment {
    private RecyclerView mfuel_recyclerView;
    FuelTypeSendMain fuelTypeSendMain;

    public SelectFuel() {
        // Required empty public constructor
    }

    // Container Activity must implement this interface
    public interface FuelTypeSendMain{
        public void fuelType(String fueltype);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_fuel, container, false);

        ((MainActivity)getActivity()).mToolbar.setTitle("Select Fuel Type");

        mfuel_recyclerView = view.findViewById(R.id.fuel_recyclerview);

        ArrayList<String> fueltype = new ArrayList<>();
        fueltype.add("Petrol");
        fueltype.add("Diesel");
        fueltype.add("CNG");
        fueltype.add("Petrol + CNG");
        fueltype.add("Electric");
        fueltype.add("Hybrid");

        mfuel_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FuelTypeAdapter fuelTypeAdapter = new FuelTypeAdapter(fueltype);


        fuelTypeAdapter.setOnEntryClickListener3(new FuelTypeAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick3(View view, int position) {
                Log.d("ALISt","You Clicked :"+ fueltype.get(position));
                String fueltypes = fueltype.get(position);
                fuelTypeSendMain.fuelType(fueltypes);// send data to main activity

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SelectTransmission())
                        .addToBackStack(null)
                        .commit();
            }
        });

        mfuel_recyclerView.setAdapter(fuelTypeAdapter);
        return view;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = getActivity();
        try {
            if (context instanceof Activity)
                fuelTypeSendMain = (FuelTypeSendMain) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MFRName Interface");
        }

    }
}