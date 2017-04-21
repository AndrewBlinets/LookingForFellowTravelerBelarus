package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO.DateUpdateInformation;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.DateUpdateInformationDataBaseHandler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartPage extends Activity {

    private static final int LAYOUT = R.layout.startpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        TravelerAPI api = TravelerRestAdapter.getApi();
        Call<DateUpdateInformation> call = api.getLastUpdate();
        call.enqueue(new Callback<DateUpdateInformation>() {
            @Override
            public void onResponse(Call<DateUpdateInformation> call, Response<DateUpdateInformation> response) {
                DateUpdateInformation result = response.body();
                checInformation(result);
            }

            @Override
            public void onFailure(Call<DateUpdateInformation> call, Throwable t) {

            }
        });


    }

    private void checInformation(DateUpdateInformation updateInformation) {
        DateUpdateInformationDataBaseHandler db = new DateUpdateInformationDataBaseHandler(this);
         if(db.getById(1).getId() == 1)
        {
            Intent intent = new Intent(this, MainActivityAutorizationUser.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, MainActivityNotAutorizationUser.class);
            startActivity(intent);
        }
    }
}
