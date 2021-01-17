package com.sam.turbocare.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam.turbocare.MainActivity;
import com.sam.turbocare.R;


public class SelectTransmission extends Fragment {
    TextView mManual, mAuto;
    String txtManual, txtAuto;
    TranstypesendMain transtypesendMain;
    public SelectTransmission() {
        // Required empty public constructor
    }

    // Container Activity must implement this interface
    public interface TranstypesendMain{
        public void transType(String Transtype);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_transmission, container, false);

        ((MainActivity)getActivity()).mToolbar.setTitle("Choose Transmission");

        mManual = view.findViewById(R.id.txtManual);
        mAuto = view.findViewById(R.id.txtAuto);

        mManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtManual = mManual.getText().toString();

                transtypesendMain.transType(txtManual);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Vehicles())
                        .addToBackStack(null)
                        .commit();

            }
        });

        mAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAuto = mAuto.getText().toString();

                transtypesendMain.transType(txtAuto);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Vehicles())
                        .addToBackStack(null)
                        .commit();
            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = getActivity();
        try {
            if (context instanceof Activity)
                transtypesendMain = (TranstypesendMain) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TransType Interface");
        }

    }
}