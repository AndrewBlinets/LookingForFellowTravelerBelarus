package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;

public class FragmentSearch extends Fragment {

    private static int LAYOUT = R.layout.fragment_search;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        return view;
    }
}
