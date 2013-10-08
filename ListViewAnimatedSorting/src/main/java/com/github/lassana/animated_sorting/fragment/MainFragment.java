package com.github.lassana.animated_sorting.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.lassana.animated_sorting.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lassana
 * @since 10/8/13
 */
public class MainFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final String[] cars = { "Volvo", "Mercedes", "Audi", "Land Rover", "BMW", "Ford", "GMC", "Mazda",
                "Acura", "Vaz", "Renault"};
        final ArrayList<String> carsArrayList = new ArrayList<String>(Arrays.asList(cars));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, carsArrayList);
        setListAdapter(adapter);
    }
}
