package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao.DatabaseHandler;

public class StartPage extends Activity {

    private static final int LAYOUT = R.layout.startpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        DatabaseHandler db = new DatabaseHandler(this);

        if(db.getById(1).getId() == 1)
        {
            Intent intent = new Intent(this, MainActivityAutorizationUser.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, MainActivityNotAutorizationUser.class);
            startActivity(intent);
        }
    }
}
