package com.sam.turbocare.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;
import com.sam.turbocare.model;


public class VehicleProfile extends Fragment {
    private TextView mMRFName, mModelName, mFuelType, mTrans, mRegNumber, mVhclType;
    private String mrfname, modelname, fueltype, transmission, regnumber, vehicletype;

    public VehicleProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle_profile, container, false);
        ((MainActivity)getActivity()).mToolbar.setTitle("Vehicle Profile");

        initViews(view);

        Bundle bundle = getArguments();
        model vehicleInfo = (model) bundle.getSerializable("obj");

        regnumber = vehicleInfo.getREG_NO();
        vehicletype = vehicleInfo.getVHEL_CL();
        mrfname = vehicleInfo.getMFR();
        modelname = vehicleInfo.getMODEL();
        fueltype = vehicleInfo.getFUEL_TYPE();
        transmission = vehicleInfo.getTRANSMISSION();



        mRegNumber.setText(regnumber);
        mVhclType.setText(modelname);
        mVhclType.append(" ");
        mVhclType.append(vehicletype);
        mMRFName.setText(mrfname);
        mModelName.setText(modelname);
        mFuelType.setText(fueltype);
        mTrans.setText(transmission);


        return view;
    }

    private void initViews(View v) {
        mRegNumber = v.findViewById(R.id.txtRegnumber);
        mVhclType = v.findViewById(R.id.txtBikeName);
        mMRFName = v.findViewById(R.id.txtManufactName);
        mModelName = v.findViewById(R.id.txtModelName);
        mFuelType = v.findViewById(R.id.txtFuelName);
        mTrans = v.findViewById(R.id.txtTransName);

    }
}