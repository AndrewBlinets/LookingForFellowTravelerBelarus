package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class AdapterMyRoad extends BaseAdapter {
    private final List<String> city;
    Context ctx;
    LayoutInflater lInflater;
    List<Trip> List_way;

    public AdapterMyRoad(Context ctx, List<Trip> list_way) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List_way = list_way;
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
    }

    @Override
    public int getCount() {
        return List_way.size();
    }

    @Override
    public Object getItem(int position) {
        return List_way.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = lInflater.inflate(R.layout.my_road, parent, false);
            holder.Start_ViewHolder = (TextView) view.findViewById(R.id.textView_way_iteam_start);
            holder.Data_ViewHolder = (TextView) view.findViewById(R.id.textView_way_iteam_data);
            holder.Time_ViewHolder = (TextView) view.findViewById(R.id.textView_way_iteam_time);
            holder.Fre_ViewHolder = (TextView) view.findViewById(R.id.textView_way_iteam_free_place);
            // holder.Finish_ViewHolder = (TextView) view.findViewById(R.id.textView_way_iteam_finish);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        if (position % 2 == 0)
            view.setBackgroundColor(0xFFE6E6E6);
        else
            view.setBackgroundColor(0xFFE8E8E8);
        Trip obj = getItems(position);
        holder.Start_ViewHolder.setText(city.get((int)obj.getIdCityOfDeparture()-1) + " -> " +
                city.get((int)obj.getIdCityOfArrived()-1));
        //holder.Finish_ViewHolder.setText(obj.getFinish());
        holder.Data_ViewHolder.setText(obj.getDataOfDeparture());
        holder.Time_ViewHolder.setText(obj.getTimeOfDeparture());
        holder.Fre_ViewHolder.setText(obj.getCountFree()+"");
        return view;
    }

    public Trip getItems(int position) {
        return ((Trip) List_way.get(position));
    }

    static class ViewHolder {
        public TextView Start_ViewHolder,Fre_ViewHolder,Data_ViewHolder,Time_ViewHolder;
    }
}
