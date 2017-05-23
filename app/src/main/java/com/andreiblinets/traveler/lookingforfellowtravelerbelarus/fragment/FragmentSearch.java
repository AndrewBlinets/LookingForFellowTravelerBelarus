package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.CityDataBaseHelperHadler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.RegionDataBaseHelperHandler;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Region;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private static int LAYOUT = R.layout.fragment_search_road;
    private View view;
    final Calendar c = Calendar.getInstance();

    public static TextView dataSet;
    private Button searchButton;
    private AutoCompleteTextView startEditText;
    private AutoCompleteTextView finishtEditText;
    List<String> cities;

    private TravelerAPI api;


    private String nowDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        nowDate =  sdf.format(c.getTime());
        api = TravelerRestAdapter.getApi();
        startEditText = (AutoCompleteTextView) view.findViewById(R.id.editTextStart);
        getCity();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, cities);
        startEditText.setAdapter(adapter);
        finishtEditText = (AutoCompleteTextView) view.findViewById(R.id.editTextFinish);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, cities);
        finishtEditText.setAdapter(adapter);
        dataSet = (TextView) view.findViewById(R.id.data_new_search);
        dataSet.setText(c.get(Calendar.YEAR) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.DAY_OF_MONTH));

        dataSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DialogFragment picker = new DatePickerFragment();
                    picker.show(getFragmentManager(), "datePicker");
            }
        });

        searchButton = (Button) view.findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToFragment();
            }
        });
        return view;
    }

    private void sendToFragment() {
        FragmentResaltSearch fragmentResaltSearch = new FragmentResaltSearch();
        Bundle bundle = new Bundle();
        bundle.putInt("idCityOfDeparture", cities.indexOf(startEditText.getText().toString()) + 1);
        bundle.putInt("idCityOfArrived", cities.indexOf(finishtEditText.getText().toString()) + 1);
        bundle.putString("data", dataSet.getText().toString());
        fragmentResaltSearch.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent,
                    fragmentResaltSearch).commit();
    }

    private void getCity() {
        cities = new ArrayList<>();
        List<String> region = new ArrayList<>();//rdb.getAll();
        List<City> city = new ArrayList<>();//cityDataBaseHelperHadler.getAll();
        city.add(new City(1,"Березино"));
        city.add(new City(1,"Борисов"));
        city.add(new City(1,"Вилейка"));
        city.add(new City(1,"Воложин"));
        city.add(new City(1,"Дзержинск"));
        city.add(new City(1,"Клецк"));
        city.add(new City(1,"Копыль"));
        city.add(new City(1,"Крупки"));
        city.add(new City(1,"Логойск"));
        city.add(new City(1,"Любань"));
        city.add(new City(1,"Минск"));
        city.add(new City(1,"Молодечно"));
        city.add(new City(1,"Мядель"));
        city.add(new City(1,"Несвиж"));
        city.add(new City(1,"Марьина Горка"));
        city.add(new City(1,"Слуцк"));
        city.add(new City(1,"Смолевичи"));
        city.add(new City(1,"Солигорск"));
        city.add(new City(1,"Старые Дороги"));
        city.add(new City(1,"Столбцы"));
        city.add(new City(1,"Узда"));
        city.add(new City(1,"Червень"));
        city.add(new City(2,"Брест"));
        city.add(new City(2,"Барановичи"));
        city.add(new City(2,"Берёза"));
        city.add(new City(2,"Ганцевичи"));
        city.add(new City(2,"Дрогичин"));
        city.add(new City(2,"Жабинка"));
        city.add(new City(2,"Иваново"));
        city.add(new City(2,"Ивацевичи"));
        city.add(new City(2,"Каменец"));
        city.add(new City(2,"Кобрин"));
        city.add(new City(2,"Лунинец"));
        city.add(new City(2,"Ляховичи"));
        city.add(new City(2,"Малорита"));
        city.add(new City(2,"Пинск"));
        city.add(new City(2,"Пружаны"));
        city.add(new City(2,"Столин"));
        city.add(new City(3,"Берестовица"));
        city.add(new City(3,"Волковыск"));
        city.add(new City(3,"Вороново"));
        city.add(new City(3,"Гродно"));
        city.add(new City(3,"Дятлово"));
        city.add(new City(3,"Зельва"));
        city.add(new City(3,"Ивье"));
        city.add(new City(3,"Кореличи"));
        city.add(new City(3,"Лида"));
        city.add(new City(3,"Мосты"));
        city.add(new City(3,"Новогрудок"));
        city.add(new City(3,"Ошмяны"));
        city.add(new City(3,"Островец"));
        city.add(new City(3,"Свислочь"));
        city.add(new City(3,"Слоним"));
        city.add(new City(3,"Сморгонь"));
        city.add(new City(3,"Щучин"));
        city.add(new City(4,"Витебск"));
        city.add(new City(4,"Бешенковичи"));
        city.add(new City(4,"Браслав"));
        city.add(new City(4,"Верхнедвинск"));
        city.add(new City(4,"Глубокое"));
        city.add(new City(4,"Городок"));
        city.add(new City(4,"Докшицы"));
        city.add(new City(4,"Дубровно"));
        city.add(new City(4,"Лепель"));
        city.add(new City(4,"Лиозно"));
        city.add(new City(4,"Миоры"));
        city.add(new City(4,"Орша"));
        city.add(new City(4,"Полоцк"));
        city.add(new City(4,"Поставы"));
        city.add(new City(4,"Россоны"));
        city.add(new City(4,"Сенно"));
        city.add(new City(4,"Толочин"));
        city.add(new City(4,"Ушачи"));
        city.add(new City(4,"Чашники"));
        city.add(new City(4,"Шарковщина"));
        city.add(new City(4,"Шумилино"));
        city.add(new City(5,"Белыничи"));
        city.add(new City(5,"Бобруйск"));
        city.add(new City(5,"Быхов"));
        city.add(new City(5,"Глуск"));
        city.add(new City(5,"Горки"));
        city.add(new City(5,"Дрибин"));
        city.add(new City(5,"Кировск"));
        city.add(new City(5,"Климовичи"));
        city.add(new City(5,"Кличев"));
        city.add(new City(5,"Краснополье"));
        city.add(new City(5,"Кричев"));
        city.add(new City(5,"Круглое"));
        city.add(new City(5,"Костюковичи"));
        city.add(new City(5,"Могилёв"));
        city.add(new City(5,"Мстиславль"));
        city.add(new City(5,"Осиповичи"));
        city.add(new City(5,"Славгород"));
        city.add(new City(5,"Хотимск"));
        city.add(new City(5,"Чаусы"));
        city.add(new City(5,"Чериков"));
        city.add(new City(5,"Шклов"));
        city.add(new City(6,"Брагин"));
        city.add(new City(6,"Буда-Кошелёво"));
        city.add(new City(6,"Ветка"));
        city.add(new City(6,"Гомель"));
        city.add(new City(6,"Добруш"));
        city.add(new City(6,"Ельск"));
        city.add(new City(6,"Житковичи"));
        city.add(new City(6,"Жлобин"));
        city.add(new City(6,"Калинковичи"));
        city.add(new City(6,"Корма"));
        city.add(new City(6,"Лельчицы"));
        city.add(new City(6,"Лоев"));
        city.add(new City(6,"Мозырь"));
        city.add(new City(6,"Наровля"));
        city.add(new City(6,"Октябрьский"));
        city.add(new City(6,"Петриков"));
        city.add(new City(6,"Речица"));
        city.add(new City(6,"Рогачёв"));
        city.add(new City(6,"Светлогорск"));
        city.add(new City(6,"Хойники"));
        city.add(new City(6,"Чечерск"));
        region.add("Минская обл");
        region.add("Брестская обл");
        region.add("Гродненская обл");
        region.add("Витебская обл");
        region.add("Могилёвская обл");
        region.add("Гомельская обл");
        for (int i = 0; i < city.size(); i++)
        {
            cities.add(city.get(i).getName() + ", (" + region.get((int)city.get(i).getIdRegion()-1) + ")");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.data_new_search:
            {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");
                break;
            }
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int My_year = c.get(Calendar.YEAR);
            int My_month = c.get(Calendar.MONTH);
            int My_day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, My_year, My_month, My_day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            dataSet.setText( sdf.format(c.getTime()));
        }
    }

}
