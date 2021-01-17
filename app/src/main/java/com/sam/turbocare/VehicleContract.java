package com.sam.turbocare;

public class VehicleContract {

    //      To avoid accidental initialisation of these class
    private VehicleContract() {
    }

    //Inner Class
    public static class VehicleDetails{

        //Database Schema
        public static final String TABLE_NAME = "vehicle_info";
        public static final String REG_NO ="reg_no";
        public static final String VHEL_CL = "vehicle_class";
        public static final String MFR = "manufacturer";
        public static final String MODEL = "model";
        public static final String FUEL_TYPE = "fuel_type";
        public static final String TRANSMISSION = "transmission";


    }
}
