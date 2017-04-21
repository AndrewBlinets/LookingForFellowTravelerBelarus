package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.NetWork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TravelerRestAdapter {
    private Retrofit retrofit;
    private static TravelerAPI travelerAPI;

    public TravelerRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl(NetWork.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        travelerAPI = retrofit.create(TravelerAPI.class);
    }

    private static Retrofit getRetrofitInstance()
    {
        return new Retrofit.Builder().baseUrl(NetWork.HOST)
                .addConverterFactory(GsonConverterFactory.create( )).build();
    }

    public static TravelerAPI getApi() {
        return  getRetrofitInstance().create(TravelerAPI.class);
    }
}
