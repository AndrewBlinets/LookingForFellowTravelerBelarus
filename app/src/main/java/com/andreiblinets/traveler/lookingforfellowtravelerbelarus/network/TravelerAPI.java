package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.NetWork;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Token;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TravelerAPI {

    @GET(NetWork.GET_DATE_LAST_UPDATE)
    Call<DateUpdateInformation> getLastUpdate();

    @GET(NetWork.GET_LIST_COUNTRY)
    Call<List<Country>> getListCountry();

    @GET(NetWork.GET_LIST_REGION)
    Call<List<Region>> getListRegion();

    @GET(NetWork.GET_LIST_CITY)
    Call<List<City>> getListCity();

    @POST(NetWork.CHECKING_TOKEN)
    Call<User> checkingToken(@Body String token);
}
