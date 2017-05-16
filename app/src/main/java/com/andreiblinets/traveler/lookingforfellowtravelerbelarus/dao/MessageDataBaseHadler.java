package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import android.content.Context;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Message;

import java.util.List;

public class MessageDataBaseHadler extends BaseClassDataBaseHadler<Message> {

    private final String KEY_INDEFICATIONUSER = "indeficationuser";
    private final String KEY_IDTEAM = "idteam";
    private final String KEY_MESSAGE = "message";
    private final String KEY_DATEMESSAGE = "datemessage";

    public MessageDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.MESSAGE);
        CREATE_TABLE = "";
    }


    @Override
    public void add(Message message) {

    }

    @Override
    public Message getById(int id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public int update(Message message) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
