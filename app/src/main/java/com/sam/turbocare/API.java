package com.sam.turbocare;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://test.turbocare.app/turbo/care/v1/";
    String MRF = "makes?class=2w";
    String MODELS = "models?class=2w&make=honda";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET(MRF)
    Call<ArrayList<String>> getMRF();

    @GET(MODELS)
    Call<ArrayList<String>> getMODELS();
}
