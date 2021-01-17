package com.sam.turbocare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sam.turbocare.fragments.Vehicles;

public class VehicleDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="vehicle_db";
    public static final int DATABASE_VERSION = 1;

    //    SQL Query to create TABLE IN DB
    public static final String CREATE_TABLE = "create table "+ VehicleContract.VehicleDetails.TABLE_NAME+"("+
            VehicleContract.VehicleDetails.REG_NO +"  text,"+ VehicleContract.VehicleDetails.VHEL_CL+" text,"+
            VehicleContract.VehicleDetails.MFR+" text,"+ VehicleContract.VehicleDetails.MODEL+" text,"+
            VehicleContract.VehicleDetails.FUEL_TYPE+" text,"+ VehicleContract.VehicleDetails.TRANSMISSION+" text);";

    //    SQL Query to Drop TABLE From DB
    public static final String DROP_TABLE = "drop table if exists "+ VehicleContract.VehicleDetails.TABLE_NAME;

    public VehicleDBHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //        Log.d("Database_Operations", "Database Created Successfully...!!!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Database_Operations", "Table Created Successfully...!!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if table is already created we have to delete first
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    //   Method to  Add Information into the table
    public void addVehicles(String regno, String vhelcls, String mfr, String model, String fueltype, String trans,SQLiteDatabase database){
        //To Add info into the table first we have to create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(VehicleContract.VehicleDetails.REG_NO,regno);
        contentValues.put(VehicleContract.VehicleDetails.VHEL_CL,vhelcls);
        contentValues.put(VehicleContract.VehicleDetails.MFR,mfr);
        contentValues.put(VehicleContract.VehicleDetails.MODEL,model);
        contentValues.put(VehicleContract.VehicleDetails.FUEL_TYPE,fueltype);
        contentValues.put(VehicleContract.VehicleDetails.TRANSMISSION,trans);

        //Insert row data into DB table
        Long result = database.insert(VehicleContract.VehicleDetails.TABLE_NAME,null,contentValues);
        if (result == -1){
            Log.d("Database Operations", "Failed");

        }
        else {
            Log.d("Database Operations", "One Row Inserted Successfully...!!!");
        }




    }


    //Method to read data from DB
//    public Cursor readData(SQLiteDatabase database){
//        String [] projections = {VehicleContract.VehicleDetails.REG_NO,VehicleContract.VehicleDetails.VHEL_CL,VehicleContract.VehicleDetails.MFR,
//                VehicleContract.VehicleDetails.MODEL,VehicleContract.VehicleDetails.FUEL_TYPE,VehicleContract.VehicleDetails.TRANSMISSION};
//
//
//        //Start read information
//        Cursor cursor = null;
//        if (database != null){
//             cursor = database.query(VehicleContract.VehicleDetails.TABLE_NAME,projections,null,null,null,null,
//                    null);
//        }
//        return cursor;
//    }

    public Cursor readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from vehicle_info";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
