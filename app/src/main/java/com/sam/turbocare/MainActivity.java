package com.sam.turbocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sam.turbocare.fragments.AddVehicle;
import com.sam.turbocare.fragments.SelectFuel;
import com.sam.turbocare.fragments.SelectMFR;
import com.sam.turbocare.fragments.SelectModel;
import com.sam.turbocare.fragments.SelectTransmission;
import com.sam.turbocare.fragments.Vehicles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements AddVehicle.VehicleRegType, SelectMFR.MRFName, SelectModel.ModelName, SelectFuel.FuelTypeSendMain, SelectTransmission.TranstypesendMain {
    public Toolbar mToolbar;
    public ArrayList<String> listMRF, listMODELS;
    private String mRegNo, mModelName, mMFRName, mFuelType, mTransmission, mVHCLType;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Vehicles()).commit();
        }
        initViews();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(Color.WHITE);


        api = API.retrofit.create(API.class);
        getMRF();
        getMODELS();



    }

    private void getMODELS() {
        Call<ArrayList<String>> call = api.getMODELS();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (response.isSuccessful()){

                    listMODELS = response.body();
                    Log.d("ALIST","LIST: "+ listMODELS);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("ALIST","Something went wrong");
            }
        });
    }

    private void getMRF() {
        Call<ArrayList<String>> call = api.getMRF();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (response.isSuccessful()){

                    listMRF = response.body();
                    Log.d("ALIST","LIST: "+ listMRF);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("ALIST","Something went wrong");
            }
        });

    }

    //Initialise all views
    private void initViews() {
        mToolbar = findViewById(R.id.toolBar);
    }

    public void bikeMRF(View view) {


    }

    @Override
    public void regNnType(String regNo, String type) {
//        Log.d("ALIST","regNo: "+regNo+" type: "+type);
        mRegNo = regNo;
        mVHCLType = type;

    }

    public ArrayList<String> getListMRF(){
        return listMRF;
    }
    public ArrayList<String> getListMODELS(){
        return listMODELS;
    }

    @Override
    public void mfrName(String mfrname) {
//        Log.d("ALIST", "MainActivity: mrf: "+mfrname);
        mMFRName = mfrname;
    }

    @Override
    public void modelName(String modelname) {
//        Log.d("ALIST", "MainActivity: model: "+modelname);
        mModelName = modelname;
    }

    @Override
    public void fuelType(String fueltype) {
//        Log.d("ALIST", "MainActivity: model: "+fueltype);
        mFuelType = fueltype;
    }

    @Override
    public void transType(String Transtype) {
//        Log.d("ALIST", "MainActivity: model: "+Transtype);
        mTransmission = Transtype;
//        Log.d("ALIST", "MainActivity: model: "+mRegNo +" "+mModelName+" "+mMFRName+" "+mFuelType+" "+ mTransmission+" "+ mVHCLType);

        addVehicle();
    }

    private void addVehicle() {

        VehicleDBHelper vehicleDBHelper = new VehicleDBHelper(this);

        //Do not put DB read & Write Operation on Main Thread use AsyncTask
        SQLiteDatabase database = vehicleDBHelper.getWritableDatabase();
        vehicleDBHelper.addVehicles(mRegNo,mVHCLType,mMFRName,mModelName,mFuelType,mTransmission, database);
        vehicleDBHelper.close();

        Toast.makeText(this, "Vehicle Added Successfully!!!",Toast.LENGTH_SHORT).show();
    }


}