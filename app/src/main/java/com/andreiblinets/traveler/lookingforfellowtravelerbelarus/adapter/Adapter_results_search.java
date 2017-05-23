package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class Adapter_results_search extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Trip> List_way;

    public Adapter_results_search(Context ctx, List<Trip> list_way) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List_way = list_way;
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
            view = lInflater.inflate(R.layout.way_result_search, parent, false);
            holder.Time_ViewHolder = (TextView) view.findViewById(R.id.textView_way_result_search_time);
            holder.Fre_ViewHolder = (TextView) view.findViewById(R.id.textView_way_result_search_free_place);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        if (position % 2 == 0)
            view.setBackgroundColor(0xFFE6E6E6);
        else
            view.setBackgroundColor(0xFFE8E8E8);
        Trip obj = getItems(position);
        holder.Time_ViewHolder.setText(obj.getTimeOfDeparture().split(":")[0] + ":" + obj.getTimeOfDeparture().split(":")[1]);
        holder.Fre_ViewHolder.setText(obj.getCountFree()+"");
        return view;
    }

    public Trip getItems(int position) {
        return ((Trip) List_way.get(position));
    }

    static class ViewHolder {
        public ImageView Image_way_ViewHolder;
        public TextView Fre_ViewHolder,Time_ViewHolder;
    }
}
