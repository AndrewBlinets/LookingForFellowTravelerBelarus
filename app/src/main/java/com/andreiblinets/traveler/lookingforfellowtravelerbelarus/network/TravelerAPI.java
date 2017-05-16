package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.NetWork;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.UserRegistration;

import java.util.List;

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
    Call<List<User>> autification(@Query("login") String login, @Query("hashpassword") int hashPassword);

    @GET (NetWork.SEARCH_ROAD)
    Call<List<Trip>> search (@Query("start") long start, @Query("finish") long finish);

    @POST (NetWork.CREATE_ROAD)
    Call<String> createRoad(@Body Trip trip);
}
