package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.NetWork;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TravelerAPI {

    @GET(NetWork.GET_DATE_LAST_UPDATE)
    Call<DateUpdateInformation> getLastUpdate();

}
