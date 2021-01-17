package com.sam.turbocare.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;
import com.sam.turbocare.VehicleDBHelper;
import com.sam.turbocare.adapter.FuelTypeAdapter;
import com.sam.turbocare.adapter.VehicleAdapter;
import com.sam.turbocare.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Vehicles extends Fragment {
    public Toolbar mToolbar;
    private RecyclerView mVehicle_recyclerView;
    private FloatingActionButton mFab_add;
    private ArrayList<model> dataholder;


    public Vehicles() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicles, container, false);


//        mToolbar = view.findViewById(R.id.toolBar);
        ((MainActivity)getActivity()).mToolbar.setTitle("Vehicle List");

        dataholder = new ArrayList<>();
        mVehicle_recyclerView = view.findViewById(R.id.vehicle_recyclerview);
        mFab_add = view.findViewById(R.id.fab_addVehicle);
        mFab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddVehicle())
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(getContext(), "Fab is clicked", Toast.LENGTH_SHORT).show();
            }
        });


        VehicleDBHelper vehicleDBHelper = new VehicleDBHelper(getContext());



//        SQLiteDatabase database = vehicleDBHelper.getReadableDatabase();
        Cursor cursor = vehicleDBHelper.readAllData();

        if (cursor == null){
            Log.d("ALIST","Cursor is null");
        }
        while (cursor.moveToNext())
        {
            model obj = new model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5));
            dataholder.add(obj);
        }

        mVehicle_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VehicleAdapter vehicleAdapter = new VehicleAdapter(dataholder);

        vehicleAdapter.setOnEntryClickListener5(new VehicleAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick5(View view, int position) {
                Log.d("ALISt","You Clicked :"+ dataholder.get(position));
//                String fueltypes = dataholder.get(position).getREG_NO();
                model fueltypes = dataholder.get(position);
//                fuelTypeSendMain.fuelType(fueltypes);// send data to main activity

                VehicleProfile vehicleProfile = new VehicleProfile();
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj", fueltypes);
                vehicleProfile.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, vehicleProfile)
                        .addToBackStack(null)
                        .commit(); }
            });
        mVehicle_recyclerView.setAdapter(vehicleAdapter);
        return view;
    }
}