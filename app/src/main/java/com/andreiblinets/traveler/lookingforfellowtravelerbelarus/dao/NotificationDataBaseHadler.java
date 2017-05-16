package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.Context;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Notification;

import java.util.List;

public class NotificationDataBaseHadler extends BaseClassDataBaseHadler<Notification> {

    private final String KEY_TYPE = "type";
    private final String KEY_TEXT = "text";
    private final String KEY_STATUS = "status";
    private final String KEY_DATA_TIME = "dataTime";

    public NotificationDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.NOTIFICATION);
        CREATE_TABLE = "";
    }


    @Override
    public void add(Notification notification) {

    }

    @Override
    public Notification getById(int id) {
        return null;
    }

    @Override
    public List<Notification> getAll() {
        return null;
    }

    @Override
    public int update(Notification notification) {
        return 0;
    }

}
