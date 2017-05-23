package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.MainActivityAutorizationUser;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.FragmentConstants;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.InformationAboutUser;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.User;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAutorization extends Fragment {

    private static final int LAYOUT = R.layout.fragment_autorization;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        final TravelerAPI api = TravelerRestAdapter.getApi();
        ((Button)view.findViewById(R.id.button_avt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =((EditText)view.findViewById(R.id.editText_email)).getText().toString();
                String b = ((EditText)view.findViewById(R.id.editText_pass)).getText().toString();
                Call<InformationAboutUser> call = api.autification(
                       a ,b);
                call.enqueue(new Callback<InformationAboutUser>() {
                    @Override
                    public void onResponse(Call<InformationAboutUser> call, Response<InformationAboutUser> response) {
                        InformationAboutUser informationAboutUser = response.body();
                        if(informationAboutUser!=null)
                        {
                            Intent intent = new Intent(getActivity(), MainActivityAutorizationUser.class);
                            intent.putExtra(FragmentConstants.USER_FOTO,informationAboutUser.getFoto());
                            intent.putExtra(FragmentConstants.USER_NAME,informationAboutUser.getName());
                            intent.putExtra(FragmentConstants.USER_SURNAME,informationAboutUser.getSurname());
                            intent.putExtra(FragmentConstants.USER_TOKEN,informationAboutUser.getToken());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<InformationAboutUser> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }

}
