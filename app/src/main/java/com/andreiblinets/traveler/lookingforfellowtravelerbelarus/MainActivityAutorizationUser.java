package com.andreiblinets.traveler.lookingforfellowtravelerbelarus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment.FragmentAutorization;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment.FragmentCreateRequest;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment.FragmentCreateRoad;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment.FragmentRegistration;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment.FragmentSearch;

public class MainActivityAutorizationUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static int LAYOUT = R.layout.activity_main;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.menu_navigation_authorized_user);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_toolbar; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Class fragmentClass = null;
        switch(item.getItemId())
        {
            case R.id.search_menu_toolbar:
            {
                fragmentClass = FragmentSearch.class;
                break;
            }
        }

        if(fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Class fragmentClass = null;
        switch(item.getItemId())
        {
            case R.id.create_road_menu_navigation:
            {
                fragmentClass = FragmentCreateRoad.class;
                break;
            }
            case R.id.search_menu_navigation:
            {
                fragmentClass = FragmentSearch.class;
                break;
            }
            case R.id.create_req_menu_navigation:
            {
                fragmentClass = FragmentCreateRequest.class;
                break;
            }
            case R.id.exit_menu_navigation: {
                finish();
                //очистить бд от сведений об аккаунте
                break;
            }
        }

        if(fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

/*
* public static  Bitmap convertBase64StringToBitmap(String source) {
	byte[] rawBitmap = Base64.decode(source.getBytes(), Base64.DEFAULT);
	Bitmap bitmap = BitmapFactory.decodeByteArray(rawBitmap, 0, rawBitmap.length);
	return bitmap;
} */