package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.Context;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.PopularInquiries;

import java.util.List;


public class PopularInquiriesDataBaseHadler extends BaseClassDataBaseHadler<PopularInquiries> {

    private final String KEY_COUNT = "count";
    private final String KEY_ID_CITY_OF_DEPARTURE = "idcityofdeparture";
    private final String KEY_ID_CITY_OF_ARRIVED = "idсityofarrived";

    public PopularInquiriesDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.POPULAR_INQUIRIES);
        CREATE_TABLE = "";
    }

    @Override
    public void add(PopularInquiries popularInquiries) {

    }

    @Override
    public PopularInquiries getById(int id) {
        return null;
    }

    @Override
    public List<PopularInquiries> getAll() {
        return null;
    }

    @Override
    public int update(PopularInquiries popularInquiries) {
        return 0;
    }
}
