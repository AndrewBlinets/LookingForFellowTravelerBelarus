package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.constants.ConstantsDataBase;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.Trip;

import java.util.List;

public class TripDataBaseHadler extends BaseClassDataBaseHadler<Trip> {

    private final String KEY_ID_CITY_OF_DEPARTURE = "idCityOfDeparture";
    private final String KEY_ID_CITY_OF_ARRIVED = "idCityOfArrived";
    private final String KEY_DATAT_IME_OF_DEPARTURE = "dataTimeOfDeparture";
    private final String KEY_COMPENSATION = "compensation";
    private final String KEY_TRANSPORT_ID = "transportId";
    private final String KEY_PLACE_FREE = "placeFree";
    private final String KEY_INFORMATION = "information";
    private final String KEY_DEPARTURE_DEVIATION = "departureDeviation";

    public TripDataBaseHadler(Context context) {
        super(context, ConstantsDataBase.TRIP);
        CREATE_TABLE = "create table " +
                NAME_TABLE +
                " ( " + KEY_ID + " integer primary key autoincrement, " +
                KEY_ID_CITY_OF_DEPARTURE + " integer, " +
                KEY_ID_CITY_OF_ARRIVED + " integer, " +
                KEY_DATAT_IME_OF_DEPARTURE + " text, " +
                KEY_COMPENSATION + " integer, " +
                KEY_TRANSPORT_ID + " integer, " +
                KEY_PLACE_FREE + " integer, " +
                KEY_INFORMATION + " text, " +
                KEY_DEPARTURE_DEVIATION + " integer " + " );";
    }

    public void add(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPENSATION, trip.getCompensation());
       // values.put(KEY_DATAT_IME_OF_DEPARTURE, trip.getDataTimeOfDeparture());
        values.put(KEY_DEPARTURE_DEVIATION, trip.getDepartureDeviation());
        values.put(KEY_ID_CITY_OF_ARRIVED, trip.getIdCityOfArrived());
        values.put(KEY_ID_CITY_OF_DEPARTURE, trip.getIdCityOfDeparture());
        //values.put(KEY_PLACE_FREE, trip.getPlaceFree());
        values.put(KEY_INFORMATION, trip.getInformation());
        values.put(KEY_TRANSPORT_ID, trip.getTransportId());
        db.insert(NAME_TABLE, null, values);
        db.close();
    }

    public Trip getById(int id) {
        Trip trip = new Trip();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(GET_BY_ID, new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(KEY_ID);

                trip.setId(cursor.getInt(idIndex));
            }
            cursor.close();
            db.close();
        }
        catch (Exception e)
        {

        }
        return trip;
    }

    public List<Trip> getAll() {
        return null;
    }

    public int update(Trip trip) {
        return 0;
    }

}
