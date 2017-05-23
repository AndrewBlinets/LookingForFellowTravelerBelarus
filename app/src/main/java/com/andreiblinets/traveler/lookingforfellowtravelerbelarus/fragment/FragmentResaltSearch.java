package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.adapter.Adapter_results_search;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentResaltSearch extends Fragment{

    private static final int LAYOUT = R.layout.fragment_search_results;
    private View view;

    ListView listView;

    private TextView Data_set;
    private TextView Start;
    private TextView Finish;
    private TextView Not_way;

    private Button Next_data;
    private Button Back_data;
    private TravelerAPI api;

    private String Str_req;
    private String Respons_json;

    Adapter_results_search adapter_results_search;
    List<Trip> List_way;
    List<String> city;

    int idCityOfArrived;
    int idCityOfDeparture;
    String data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        Bundle bundle = this.getArguments();
        data =  bundle.getString("data");
        idCityOfArrived = bundle.getInt("idCityOfArrived");
        idCityOfDeparture = bundle.getInt("idCityOfDeparture");
        api = TravelerRestAdapter.getApi();
        Data_set = (TextView)view.findViewById(R.id.fragment_search_results_textView_data);
        Start = (TextView)view.findViewById(R.id.fragment_search_results_editText_start);
        Finish = (TextView)view.findViewById(R.id.fragment_search_results_editText_finish);
        Not_way = (TextView)view.findViewById(R.id.fragment_search_results_textView_not_way);
        Next_data = (Button)view.findViewById(R.id.fragment_search_results_button_next_data);
        Back_data = (Button)view.findViewById(R.id.fragment_search_results_button_back_data);
        getRoad();
        city = new ArrayList<>();
        city.add("Березино");
        city.add("Борисов");
        city.add("Вилейка");
        city.add("Воложин");
        city.add("Дзержинск");
        city.add("Клецк");
        city.add("Копыль");
        city.add("Крупки");
        city.add("Логойск");
        city.add("Любань");
        city.add("Минск");
        city.add("Молодечно");
        city.add("Мядель");
        city.add("Несвиж");
        city.add("Марьина Горка");
        city.add("Слуцк");
        city.add("Смолевичи");
        city.add("Солигорск");
        city.add("Старые Дороги");
        city.add("Столбцы");
        city.add("Узда");
        city.add("Червень");
        city.add("Брест");
        city.add("Барановичи");
        city.add("Берёза");
        city.add("Ганцевичи");
        city.add("Дрогичин");
        city.add("Жабинка");
        city.add("Иваново");
        city.add("Ивацевичи");
        city.add("Каменец");
        city.add("Кобрин");
        city.add("Лунинец");
        city.add("Ляховичи");
        city.add("Малорита");
        city.add("Пинск");
        city.add("Пружаны");
        city.add("Столин");
        city.add("Берестовица");
        city.add("Волковыск");
        city.add("Вороново");
        city.add("Гродно");
        city.add("Дятлово");
        city.add("Зельва");
        city.add("Ивье");
        city.add("Кореличи");
        city.add("Лида");
        city.add("Мосты");
        city.add("Новогрудок");
        city.add("Ошмяны");
        city.add("Островец");
        city.add("Свислочь");
        city.add("Слоним");
        city.add("Сморгонь");
        city.add("Щучин");
        city.add("Витебск");
        city.add("Бешенковичи");
        city.add("Браслав");
        city.add("Верхнедвинск");
        city.add("Глубокое");
        city.add("Городок");
        city.add("Докшицы");
        city.add("Дубровно");
        city.add("Лепель");
        city.add("Лиозно");
        city.add("Миоры");
        city.add("Орша");
        city.add("Полоцк");
        city.add("Поставы");
        city.add("Россоны");
        city.add("Сенно");
        city.add("Толочин");
        city.add("Ушачи");
        city.add("Чашники");
        city.add("Шарковщина");
        city.add("Шумилино");
        city.add("Белыничи");
        city.add("Бобруйск");
        city.add("Быхов");
        city.add("Глуск");
        city.add("Горки");
        city.add("Дрибин");
        city.add("Кировск");
        city.add("Климовичи");
        city.add("Кличев");
        city.add("Краснополье");
        city.add("Кричев");
        city.add("Круглое");
        city.add("Костюковичи");
        city.add("Могилёв");
        city.add("Мстиславль");
        city.add("Осиповичи");
        city.add("Славгород");
        city.add("Хотимск");
        city.add("Чаусы");
        city.add("Чериков");
        city.add("Шклов");
        city.add("Брагин");
        city.add("Буда-Кошелёво");
        city.add("Ветка");
        city.add("Гомель");
        city.add("Добруш");
        city.add("Ельск");
        city.add("Житковичи");
        city.add("Жлобин");
        city.add("Калинковичи");
        city.add("Корма");
        city.add("Лельчицы");
        city.add("Лоев");
        city.add("Мозырь");
        city.add("Наровля");
        city.add("Октябрьский");
        city.add("Петриков");
        city.add("Речица");
        city.add("Рогачёв");
        city.add("Светлогорск");
        city.add("Хойники");
        city.add("Чечерск");
        Next_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data_set.setText( Data_set.getText().toString().split(".")[0] + "." +
                        Data_set.getText().toString().split(".")[1] + "." +
                        (Integer.parseInt(Data_set.getText().toString().split(".")[2])+1));
            }
        });
        Back_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data_set.setText( Data_set.getText().toString().split(".")[0] + "." +
                        Data_set.getText().toString().split(".")[1] + "." +
                        (Integer.parseInt(Data_set.getText().toString().split(".")[2])-1));
            }
        });

        return view;
    }

    private void getRoad() {
        Call<List<Trip>> call = api.search(idCityOfDeparture, idCityOfArrived,data);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                List<Trip> list = response.body();
                if(list != null){
                    List_way = list;
                    show();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Маршруты не найдены", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void show() {
        adapter_results_search = new Adapter_results_search(getActivity().getBaseContext(),List_way);
        // setListAdapter(new Adapter_save_road(getActivity(),List_way));
        listView = (ListView)view.findViewById(R.id.fragment_search_results_listView);
        Start.setText(city.get(idCityOfDeparture - 1));
        Finish.setText(city.get(idCityOfArrived - 1));
        listView.setAdapter(adapter_results_search);
        if(List_way.size()!=0) {

            Not_way.setText("");
        }
        else
        {
            Not_way.setText("Not Way By Your Search");
        }

    }
}
