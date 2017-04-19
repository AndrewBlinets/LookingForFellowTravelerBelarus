package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;

public class FragmentSearch extends Fragment {

    private static int LAYOUT = R.layout.fragment_search;
    private View view;

    String[] cities = {"Москва", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.editTextStart);
        // Создаем адаптер для автозаполнения элемента AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, cities);
               // new ArrayAdapter<String>(view, R.layout.support_simple_spinner_dropdown_item, cities);
        autoCompleteTextView.setAdapter(adapter);
        return view;
    }
}
