package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.FragmentConstants;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHelperHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.InformationUpdateDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.RegionDataBaseHelperHandler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.UserDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Country;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.LastUpdate;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CountryDataBaseHadler;
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
    private TextView messageView;

    private LastUpdate lastUpdateWithServer;
    private LastUpdate lastUpdateWithDB;

    private RegionDataBaseHelperHandler regionDataBaseHadler;
    private InformationUpdateDataBase informationUpdateDataBase;
    private CountryDataBaseHadler countryDataBaseHadler;
    private TokenDataBaseHadler tokenDataBaseHadler;
    private UserDataBaseHadler userDataBaseHadler;
    private CityDataBaseHelperHadler cityDataBaseHelperHadler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        informationUpdateDataBase = new InformationUpdateDataBase(this);
        regionDataBaseHadler = new RegionDataBaseHelperHandler(this);
        countryDataBaseHadler = new CountryDataBaseHadler(this);
        tokenDataBaseHadler = new TokenDataBaseHadler(this);
        userDataBaseHadler = new UserDataBaseHadler(this);
        cityDataBaseHelperHadler = new CityDataBaseHelperHadler(this);

        messageView = (TextView)findViewById(R.id.textView_startpage);

        api = TravelerRestAdapter.getApi();

        messageView.setText("Получения информации.");

        getInformationUpdate();
    }

    private void getInformationUpdate() {
        Call<LastUpdate> call = api.getLastUpdate();
        call.enqueue(new Callback<LastUpdate>() {
            @Override
            public void onResponse(Call<LastUpdate> call, Response<LastUpdate> response) {
                lastUpdateWithServer = response.body();
                checkingInformationCountry();
            }
            @Override
            public void onFailure(Call<LastUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                flagAboutCheckingToken = false;
                goToMainLayout(new User());
            }
        });
    }

    private void checkingInformationCountry() {
        lastUpdateWithDB = informationUpdateDataBase.getById(1);
        if (lastUpdateWithDB.getId() == 0) {
            lastUpdateWithDB.setLastUpdateCountry("");
            lastUpdateWithDB.setLastUpdateRegion("");
            lastUpdateWithDB.setLastUpdateCity("");
            informationUpdateDataBase.add(lastUpdateWithServer);
            flagUpdate = false;
        }
        if(!lastUpdateWithDB.getLastUpdateCountry().equals(lastUpdateWithServer.getLastUpdateCountry()))
        {
            messageView.setText("Обновления стран.");
            updateCountry();
        }
        else {
            checkingInformationRegion();
        }
    }

    private void updateCountry() {
        Call<List<Country>> call = api.getListCountry();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> result = response.body();
                updateCountry(result);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                goToMainLayout(new User());
            }
        });
    }

    private void updateCountry(List<Country> list) {
        if (flagUpdate) {
            for (Country country : list) {
                countryDataBaseHadler.update(country);
            }
        }
        else
        {
            for (Country country : list) {
                countryDataBaseHadler.add(country);
            }
        }
        checkingInformationRegion();
    }

    private void checkingInformationRegion() {
        if (!lastUpdateWithDB.getLastUpdateRegion().equals(lastUpdateWithServer.getLastUpdateRegion())) {
            messageView.setText("Обновления регионов.");
            updateRegion();
        }
        else {
            checkingInformationCity();
        }
    }

    private void updateRegion() {
        Call<List<Region>> call = api.getListRegion();
        call.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                List<Region> result = response.body();
                updateRegion(result);
            }

            @Override
            public void onFailure(Call<List<Region>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                goToMainLayout(new User());
            }
        });
    }

    private void updateRegion(List<Region> list) {

        if (flagUpdate) {
            for (Region region : list) {
                regionDataBaseHadler.update(region);
            }
        }
        else
        {
            for (Region region : list) {
                regionDataBaseHadler.add(region);
            }
        }
        checkingInformationCity();
    }

    private void checkingInformationCity() {
        if (!lastUpdateWithDB.getLastUpdateCity().equals(lastUpdateWithServer.getLastUpdateCity())) {
            messageView.setText("Обновления городов.");
            updateCity();
        }
        else
        {
            updateInformationAboutUpdateInBD();
        }
    }

    private void updateCity() {
        Call<List<City>> call = api.getListCity();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> result = response.body();
                updateCity(result);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                goToMainLayout(new User());
            }
        });
    }

    private void updateCity(List<City> list) {
        if (flagUpdate) {
            for (City city : list) {
                cityDataBaseHelperHadler.update(city);
            }
        }
        else
        {
            for (City city : list) {
                cityDataBaseHelperHadler.add(city);
            }
       }
        updateInformationAboutUpdateInBD();
    }

    private void updateInformationAboutUpdateInBD() {
        lastUpdateWithServer.setId(1);
        informationUpdateDataBase.update(lastUpdateWithServer);
        checkingTokenInDB();
    }

    private void checkingTokenInDB() {
        if(tokenDataBaseHadler.getById(1).getId() == 1) {
            messageView.setText("Проверка токен-ключа.");
            chekingTokenKey();
        }
        else
        {
            goToLayoutMainActivityNotAutorizationUser();
        }
    }

    private void chekingTokenKey() {
        Call<User> call = api.checkingToken(tokenDataBaseHadler.getById(1).getToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User result = response.body();
                goToMainLayout(result);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
                goToLayoutMainActivityNotAutorizationUser();
            }
        });
    }

    private void goToMainLayout(User user) {
        messageView.setText("Запуск приложения.");
        if(flagAboutCheckingToken)
        {
            if(user.getId() == 0)
            {
                tokenDataBaseHadler.deleteById(1);
                goToLayoutMainActivityNotAutorizationUser();
            }
            else {
                userDataBaseHadler.update(user);
                goToLayoutMainActivityAutorizationUser();
            }
        }
        else {

            if (tokenDataBaseHadler.getById(1).getId() == 1) {
                goToLayoutMainActivityAutorizationUser();
            } else {
                //goToLayoutMainActivityAutorizationUser();
                goToLayoutMainActivityNotAutorizationUser();
            }
        }
    }

    private void goToLayoutMainActivityAutorizationUser() {
        Intent intent = new Intent(this, MainActivityAutorizationUser.class);
        intent.putExtra(FragmentConstants.USER_NAME,"Bdfyjd");
        intent.putExtra(FragmentConstants.USER_SURNAME,"zxc");
        intent.putExtra(FragmentConstants.USER_TOKEN,"zxc");
        startActivity(intent);
    }

    private void goToLayoutMainActivityNotAutorizationUser() {
        Intent intent = new Intent(this, MainActivityNotAutorizationUser.class);
        startActivity(intent);
    }
}


