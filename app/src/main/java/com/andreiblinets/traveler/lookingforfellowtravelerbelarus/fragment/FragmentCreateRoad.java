package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.City;
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

public class FragmentCreateRoad extends Fragment {

    private static final int LAYOUT = R.layout.fragment_create_road;
    private View view;

    private static TextView dataSet;
    private static TextView timeSet;
    private TextView start;
    private TextView finish;
    private TextView countFree;
    private EditText infaAboutWay;

    private Button trade;
    private Button create;

    final Calendar c = Calendar.getInstance();

    private int countPlaceFree = 1;

    int My_year;
    int My_month;
    int My_day;
    int My_hour;
    int My_minute;
    String now_date;

    private AutoCompleteTextView startEditText;
    private AutoCompleteTextView finishtEditText;
    List<String> cities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        now_date =  sdf.format(c.getTime());


        startEditText = (AutoCompleteTextView) view.findViewById(R.id.editTextStart);
        getCity();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, cities);
        startEditText.setAdapter(adapter);
        finishtEditText = (AutoCompleteTextView) view.findViewById(R.id.editTextFinish);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, cities);
        finishtEditText.setAdapter(adapter);

        countFree = (TextView) view.findViewById(R.id.textView_count_Free);
        countFree.setText(countPlaceFree +"");
        Button minus = (Button) view.findViewById(R.id.button_minus);
        Button plus = (Button) view.findViewById(R.id.button_plus);
        create = (Button) view.findViewById(R.id.fragment_create_new_way_button_create);
        infaAboutWay = (EditText)view.findViewById(R.id.fragment_create_new_way_editText_infa_about_way);
        dataSet = (TextView) view.findViewById(R.id.fragment_create_new_way_textview_data);
        timeSet = (TextView) view.findViewById(R.id.fragment_create_new_way_textview_time);
        My_year = c.get(Calendar.YEAR);
        My_month = c.get(Calendar.MONTH);
        My_day = c.get(Calendar.DAY_OF_MONTH);
        My_hour = c.get(Calendar.HOUR_OF_DAY);
        My_minute = c.get(Calendar.MINUTE);
        dataSet.setText(My_day + "-" + (My_month + 1) + "-" + My_year);
        timeSet.setText(My_hour + ":" + My_minute);
        timeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new TimePickerFragment();
                picker.show(getFragmentManager(), "timePicker");
            }
        });
        dataSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");
            }
        });
//        trade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = start.getText().toString();
//                start.setText(finish.getText().toString());
//                finish.setText(str);
//            }
//        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countPlaceFree++;
                countFree.setText(countPlaceFree +"");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countPlaceFree != 0)
                { countPlaceFree--;
                    countFree.setText(countPlaceFree +"");}
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToServer();
            }
        });

        return view;
    }

    private void sendToServer() {
        Trip trip = new Trip();
        trip.setIdCityOfDeparture(cities.indexOf(startEditText.getText().toString()) + 1);
        trip.setIdCityOfArrived(cities.indexOf(finishtEditText.getText().toString()) + 1);
        trip.setCompensation(0);
        trip.setCountFree(countPlaceFree);
        trip.setAutoAdd(true);
        trip.setInformation(infaAboutWay.getText().toString());
        trip.setTransportId(1);
        trip.setTimeOfDeparture(timeSet.getText().toString());
        trip.setDataOfDeparture(dataSet.getText().toString());
        trip.setId(0);
        trip.setDepartureDeviation(0);
        TravelerAPI api = TravelerRestAdapter.getApi();
        Call<String> call = api.createRoad(trip);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String trip = response.body();
                if( trip != null){
                    Toast.makeText(getActivity().getApplicationContext(), "Маршруты создан успешно", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    try {
                        fragmentManager.beginTransaction().replace(R.id.flContent,
                                (FragmentSearch.class).newInstance()).commit();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Маршруты не создан, проверте данные и повторите попытку", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Нет доступа к сети", Toast.LENGTH_SHORT).show();
            }
        });
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

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        Calendar c = Calendar.getInstance();
        int myHour = c.get(Calendar.HOUR_OF_DAY);
        int myMinyt = c.get(Calendar.MINUTE);

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new TimePickerDialog(getActivity(), this, 0, 0, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinyt = minute;
            timeSet.setText(hourOfDay + ":" + minute);
        }
    }
}
