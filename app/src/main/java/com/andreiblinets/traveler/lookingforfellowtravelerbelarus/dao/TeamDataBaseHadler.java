package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;


import android.content.Context;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Team;

import java.util.List;

public class TeamDataBaseHadler extends BaseClassDataBaseHadler<Team> {

    private final String KEY_INDEFICATIONUSER = "indeficationuser";
    private final String KEY_IDTRIP = "idtrip";

    public TeamDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.TEAM);
        CREATE_TABLE = "";
    }

    @Override
    public void add(Team team) {
    }

    @Override
    public Team getById(int id) {
        return null;
    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public int update(Team team) {
        return 0;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
