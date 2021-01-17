package com.sam.turbocare.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;


public class AddVehicle extends Fragment {
    private EditText mTxtRegNo;
    private FloatingActionButton mFab_addRegNo;
    private RadioButton mRBike, mRCar;
    private RadioGroup mRadioGrp;
    VehicleRegType vehicleRegType;

    public AddVehicle() {
        // Required empty public constructor
    }

    // Container Activity must implement this interface
    public interface VehicleRegType {
        public void regNnType(String regNo, String type);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        ((MainActivity)getActivity()).mToolbar.setTitle("Add Vehicle");

        mTxtRegNo = view.findViewById(R.id.etxtVnumber);
        mFab_addRegNo = view.findViewById(R.id.fab_addRegNo);
        mRadioGrp = view.findViewById(R.id.rgroup);
        mRBike = view.findViewById(R.id.rbtnBite);
        mRCar = view.findViewById(R.id.rbtnCar);


        mFab_addRegNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNo = mTxtRegNo.getText().toString().trim();
                String vType = "";

                if (mRBike.isChecked()){
                    vType = "Bike";
                }
                else if (mRCar.isChecked()){
                    vType = "Car";
                }
                if (regNo.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter Vehicle Number",Toast.LENGTH_LONG).show();
                }
                else if (vType.isEmpty()){
                    Toast.makeText(getContext(),"Please Select Vehicle Type",Toast.LENGTH_LONG).show();
                }
                else {
                    vehicleRegType.regNnType(regNo,vType);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SelectMFR())
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity a = getActivity();
        try {
            if (context instanceof Activity)
            vehicleRegType = (VehicleRegType) a;
        }catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }
}