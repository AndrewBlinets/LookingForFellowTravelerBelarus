package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;

public class FragmentCreateRoad extends Fragment {

    private static final int LAYOUT = R.layout.fragment_create_road;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        return view;
    }
}