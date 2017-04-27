package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.UserDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CountryDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.DateUpdateInformationDataBaseHandler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.RegionDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.TokenDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartPage extends Activity {

    private static final int LAYOUT = R.layout.startpage;
    private TravelerAPI api;
    boolean flagUpdate;
    boolean flagAboutCheckingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
//        DateUpdateInformation obj = new DateUpdateInformation();
//        obj.setDataTimeUpdateCity("qwe");
//        obj.setDataTimeUpdateRegion("zxc");
//        obj.setDataTimeUpdateCountry("asd");
//        obj.setId(2);
//        checkingInformationCountry(obj);
        api = TravelerRestAdapter.getApi();
        Call<DateUpdateInformation> call = api.getLastUpdate();
        call.enqueue(new Callback<DateUpdateInformation>() {
            @Override
            public void onResponse(Call<DateUpdateInformation> call, Response<DateUpdateInformation> response) {
                DateUpdateInformation result = response.body();
                checkingInformationCountry(result);
            }

            @Override
            public void onFailure(Call<DateUpdateInformation> call, Throwable t) {
                // Enter mesage about error in enternet and about information not update
                System.out.println(t.getMessage());
                flagAboutCheckingToken = false;
                goToMainLayout(new User());
            }
        });
    }

    private void checkingInformationCountry(final DateUpdateInformation updateInformationWithServer) {
        DateUpdateInformationDataBaseHandler db = new DateUpdateInformationDataBaseHandler(this);
        final DateUpdateInformation updateInformationWithDB = db.getById(1);
        if (updateInformationWithDB.getId() == 0) {
            updateInformationWithDB.setDataTimeUpdateCountry("");
            updateInformationWithDB.setDataTimeUpdateRegion("");
            updateInformationWithDB.setDataTimeUpdateCity("");
            db.create(updateInformationWithServer);
            flagUpdate = false;
        }
        if(!updateInformationWithDB.getDataTimeUpdateCountry().equals(updateInformationWithServer.getDataTimeUpdateCountry()))
        {
            Call<List<Country>> call = api.getListCountry();
            call.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    List<Country> result = response.body();
                    updateCountry(result, updateInformationWithServer, updateInformationWithDB);
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
        else {
            checkingInformationRegion(updateInformationWithServer, updateInformationWithDB);
        }
    }

    private void updateCountry(List<Country> list, DateUpdateInformation updateInformationWithServer, DateUpdateInformation updateInformationWithDB) {
        CountryDataBaseHadler db = new CountryDataBaseHadler(this);
        if (flagUpdate) {
            for (Country country : list) {
                db.update(country);
            }
        }
        else
        {
            for (Country country : list) {
                db.create(country);
            }
        }
        checkingInformationRegion(updateInformationWithServer, updateInformationWithDB);
    }

    private void checkingInformationRegion(final DateUpdateInformation updateInformationWithServer, final DateUpdateInformation updateInformationWithDB) {
        if (!updateInformationWithDB.getDataTimeUpdateRegion().equals(updateInformationWithServer.getDataTimeUpdateRegion())) {
            Call<List<Region>> call = api.getListRegion();
            call.enqueue(new Callback<List<Region>>() {
                @Override
                public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                    List<Region> result = response.body();
                    updateRegion(result, updateInformationWithServer, updateInformationWithDB);
                }

                @Override
                public void onFailure(Call<List<Region>> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
        else {
            checkingInformationCity(updateInformationWithServer, updateInformationWithDB);
        }
    }

    private void updateRegion(List<Region> list, DateUpdateInformation updateInformationWithServer, DateUpdateInformation updateInformationWithDB) {
        RegionDataBaseHadler db = new RegionDataBaseHadler(this);
        if (flagUpdate) {
            for (Region region : list) {
                db.update(region);
            }
        }
        else
        {
            for (Region region : list) {
                db.create(region);
            }
        }
        checkingInformationCity(updateInformationWithServer, updateInformationWithDB);
    }

    private void checkingInformationCity(final DateUpdateInformation updateInformationWithServer, final DateUpdateInformation updateInformationWithDB) {
        if (!updateInformationWithDB.getDataTimeUpdateCity().equals(updateInformationWithServer.getDataTimeUpdateCity())) {
            Call<List<City>> call = api.getListCity();
            call.enqueue(new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    List<City> result = response.body();
                    updateCity(result, updateInformationWithServer, updateInformationWithDB);
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
        else
        {
            updateInformationAboutUpdateInBD(updateInformationWithServer);
        }
    }

    private void updateCity(List<City> list, DateUpdateInformation updateInformationWithServer, DateUpdateInformation updateInformationWithDB) {
        CityDataBaseHadler db = new CityDataBaseHadler(this);
        if (flagUpdate) {
            for (City city : list) {
                db.update(city);
            }
        }
        else
        {
            for (City city : list) {
                db.create(city);
            }
       }
        updateInformationAboutUpdateInBD(updateInformationWithServer);
    }

    private void updateInformationAboutUpdateInBD(DateUpdateInformation updateInformationWithServer) {
        DateUpdateInformationDataBaseHandler db = new DateUpdateInformationDataBaseHandler(this);
        updateInformationWithServer.setId(1);
        db.update(updateInformationWithServer);
        checkingTokenInDB();
    }

    private void checkingTokenInDB() {
        TokenDataBaseHadler db = new TokenDataBaseHadler(this);
        if(db.getById(1).getId() == 1) {
            Call<User> call = api.checkingToken(db.getById(1).getToken());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User result = response.body();
                    goToMainLayout(result);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
        else
        {
            goToLayoutMainActivityNotAutorizationUser();
        }
    }

    private void goToMainLayout(User user) {
        TokenDataBaseHadler db = new TokenDataBaseHadler(this);
        if(flagAboutCheckingToken)
        {
            if(user.getId() == 0)
            {
                db.deleteById(1);
                goToLayoutMainActivityNotAutorizationUser();
            }
            else {
                UserDataBaseHadler userDataBaseHadler = new UserDataBaseHadler(this);
                userDataBaseHadler.update(user);
                goToLayoutMainActivityAutorizationUser();
            }
        }
        else {

            if (db.getById(1).getId() == 1) {
                goToLayoutMainActivityAutorizationUser();
            } else {
                goToLayoutMainActivityNotAutorizationUser();
            }
        }
    }

    private void goToLayoutMainActivityAutorizationUser() {
        Intent intent = new Intent(this, MainActivityAutorizationUser.class);
        startActivity(intent);
    }

    private void goToLayoutMainActivityNotAutorizationUser() {
        Intent intent = new Intent(this, MainActivityNotAutorizationUser.class);
        startActivity(intent);
    }
}


