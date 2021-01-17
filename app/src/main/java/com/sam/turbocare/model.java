package com.sam.turbocare;

import java.io.Serializable;

public class model implements Serializable {
    String  REG_NO, VHEL_CL ,MFR ,MODEL ,FUEL_TYPE, TRANSMISSION;

    public model(String REG_NO, String VHEL_CL, String MFR, String MODEL, String FUEL_TYPE, String TRANSMISSION) {
        this.REG_NO = REG_NO;
        this.VHEL_CL = VHEL_CL;
        this.MFR = MFR;
        this.MODEL = MODEL;
        this.FUEL_TYPE = FUEL_TYPE;
        this.TRANSMISSION = TRANSMISSION;
    }

    public model(String REG_NO, String MFR, String MODEL) {
        this.REG_NO = REG_NO;
        this.MFR = MFR;
        this.MODEL = MODEL;
    }

    public String getREG_NO() {
        return REG_NO;
    }

    public void setREG_NO(String REG_NO) {
        this.REG_NO = REG_NO;
    }

    public String getVHEL_CL() {
        return VHEL_CL;
    }

    public void setVHEL_CL(String VHEL_CL) {
        this.VHEL_CL = VHEL_CL;
    }

    public String getMFR() {
        return MFR;
    }

    public void setMFR(String MFR) {
        this.MFR = MFR;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getFUEL_TYPE() {
        return FUEL_TYPE;
    }

    public void setFUEL_TYPE(String FUEL_TYPE) {
        this.FUEL_TYPE = FUEL_TYPE;
    }

    public String getTRANSMISSION() {
        return TRANSMISSION;
    }

    public void setTRANSMISSION(String TRANSMISSION) {
        this.TRANSMISSION = TRANSMISSION;
    }
}
