package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network;

import android.support.v4.util.ArrayMap;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.InformationAboutUser;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.NetWork;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Search;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.UserRegistration;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TravelerAPI {

    @GET(NetWork.GET_DATE_LAST_UPDATE)
    Call<LastUpdate> getLastUpdate();

    @GET(NetWork.GET_LIST_COUNTRY)
    Call<List<Country>> getListCountry();

    @GET(NetWork.GET_LIST_REGION)
    Call<List<Region>> getListRegion();

    @GET(NetWork.GET_LIST_CITY)
    Call<List<City>> getListCity();

    @GET(NetWork.CHECKING_EMAIL)
    Call<String> chekingEmail(@Body String email);

    @GET(NetWork.CHECKING_PHONE)
    Call<String> chekingPhone(@Body String phone);

    @GET(NetWork.CHECKING_TOKEN)
    Call<User> checkingToken(@Body String token);

    @POST(NetWork.REGISTRATION)
    Call<String> registration(@Body UserRegistration userRegistration);

    @GET(NetWork.AUTIFICATION)
    Call<InformationAboutUser> autification(@Query("login") String login, @Query("hashpassword") String hashPassword);

    @GET(NetWork.MY_ROAD)
    Call<List<Trip>> getMyRoad(@Query("token") String phone);

    @GET (NetWork.SEARCH_ROAD)
    Call<List<Trip>> search (@Query("idCityOfDeparture") int idCityOfDeparture, @Query("idCityOfArrived") int idCityOfArrived, @Query("data") String hashPassword);

    @POST (NetWork.CREATE_ROAD)
    Call<String> createRoad(@Body Trip trip);
}
