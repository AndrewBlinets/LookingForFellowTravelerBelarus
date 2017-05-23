package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.adapter.AdapterMyRoad;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmntMyRoadIDriver extends Fragment {

    private static final int LAYOUT = R.layout.fragment_road_driver; //R.layout.fragment_login;
    private View view;

    private Button New_create;
    ListView listView;

    String token;

    AdapterMyRoad adapter_save_road;
    List<Trip> List_way = new ArrayList<>() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        Bundle bundle = this.getArguments();
        token = bundle.getString("token");
        getMyRoad();

        New_create = (Button)view.findViewById(R.id.button_new_create_my_road);
        New_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCreateRoad fragmentResaltSearch = new FragmentCreateRoad();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,
                        fragmentResaltSearch).commit();
            }
        });
        return view;
    }

    private void getMyRoad() {
        TravelerAPI api = TravelerRestAdapter.getApi();
        Call<List<Trip>> call = api.getMyRoad(token);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                List<Trip> trips = response.body();
                Trip trip = new Trip();
                trip.setIdCityOfDeparture(6);
                trip.setIdCityOfArrived(11);
                trip.setTimeOfDeparture("12:00");
                trip.setDataOfDeparture("26.05.2017");
                trip.setCountFree(1);
                List_way.add(trip);
                if(trips != null){
                    List_way = trips;
                    show();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Маршруты не найдены", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Нет доступа к сети", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void show() {

        adapter_save_road = new AdapterMyRoad(getActivity(),List_way);
        listView = (ListView)view.findViewById(R.id.listView_my_road);

        listView.setAdapter(adapter_save_road);
    }
}
