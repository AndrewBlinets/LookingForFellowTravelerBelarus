package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.UserDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CountryDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.LastUpdateDataBaseHandler;
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
        api = TravelerRestAdapter.getApi();
        Call<LastUpdate> call = api.getLastUpdate();
        call.enqueue(new Callback<LastUpdate>() {
            @Override
            public void onResponse(Call<LastUpdate> call, Response<LastUpdate> response) {
                LastUpdate result = response.body();
                checkingInformationCountry(result);
            }

            @Override
            public void onFailure(Call<LastUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                flagAboutCheckingToken = false;
                goToMainLayout(new User());
            }
        });
    }

    private void checkingInformationCountry(final LastUpdate updateInformationWithServer) {
        LastUpdateDataBaseHandler db = new LastUpdateDataBaseHandler(this);
        final LastUpdate updateInformationWithDB = db.getById(1);
        if (updateInformationWithDB.getId() == 0) {
            updateInformationWithDB.setLastUpdateCountry("");
            updateInformationWithDB.setLastUpdateRegion("");
            updateInformationWithDB.setLastUpdateCountry("");
            db.add(updateInformationWithServer);
            flagUpdate = false;
        }
        if(!updateInformationWithDB.getLastUpdateCountry().equals(updateInformationWithServer.getLastUpdateCountry()))
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
                    Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            checkingInformationRegion(updateInformationWithServer, updateInformationWithDB);
        }
    }

    private void updateCountry(List<Country> list, LastUpdate updateInformationWithServer, LastUpdate updateInformationWithDB) {
        CountryDataBaseHadler db = new CountryDataBaseHadler(this);
        if (flagUpdate) {
            for (Country country : list) {
                db.update(country);
            }
        }
        else
        {
            for (Country country : list) {
                db.add(country);
            }
        }
        checkingInformationRegion(updateInformationWithServer, updateInformationWithDB);
    }

    private void checkingInformationRegion(final LastUpdate updateInformationWithServer, final LastUpdate updateInformationWithDB) {
        if (!updateInformationWithDB.getLastUpdateRegion().equals(updateInformationWithServer.getLastUpdateRegion())) {
            Call<List<Region>> call = api.getListRegion();
            call.enqueue(new Callback<List<Region>>() {
                @Override
                public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                    List<Region> result = response.body();
                    updateRegion(result, updateInformationWithServer, updateInformationWithDB);
                }

                @Override
                public void onFailure(Call<List<Region>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            checkingInformationCity(updateInformationWithServer, updateInformationWithDB);
        }
    }

    private void updateRegion(List<Region> list, LastUpdate updateInformationWithServer, LastUpdate updateInformationWithDB) {
        RegionDataBaseHadler db = new RegionDataBaseHadler(this);
        if (flagUpdate) {
            for (Region region : list) {
                db.update(region);
            }
        }
        else
        {
            for (Region region : list) {
                db.add(region);
            }
        }
        checkingInformationCity(updateInformationWithServer, updateInformationWithDB);
    }

    private void checkingInformationCity(final LastUpdate updateInformationWithServer, final LastUpdate updateInformationWithDB) {
        if (!updateInformationWithDB.getLastUpdateCity().equals(updateInformationWithServer.getLastUpdateCity())) {
            Call<List<City>> call = api.getListCity();
            call.enqueue(new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    List<City> result = response.body();
                    updateCity(result, updateInformationWithServer, updateInformationWithDB);
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            updateInformationAboutUpdateInBD(updateInformationWithServer);
        }
    }

    private void updateCity(List<City> list, LastUpdate updateInformationWithServer, LastUpdate updateInformationWithDB) {
        CityDataBaseHadler db = new CityDataBaseHadler(this);
        if (flagUpdate) {
            for (City city : list) {
                db.update(city);
            }
        }
        else
        {
            for (City city : list) {
                db.add(city);
            }
       }
        updateInformationAboutUpdateInBD(updateInformationWithServer);
    }

    private void updateInformationAboutUpdateInBD(LastUpdate updateInformationWithServer) {
        LastUpdateDataBaseHandler db = new LastUpdateDataBaseHandler(this);
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
                    Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
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


