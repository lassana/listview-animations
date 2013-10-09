package com.github.lassana.animated_sorting.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.lassana.animated_sorting.R;
import com.github.lassana.animated_sorting.util.SortingHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author lassana
 * @since 10/8/13
 */
public class MainFragment extends ListFragment {

    private final static String[] CARS = { "Volvo", "Mercedes", "Audi", "Land Rover", "BMW", "Ford", "GMC", "Mazda",
            "Acura", "Vaz", "Renault"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView listView = getListView();
        listView.setAdapter(getNextAdapter());
    }

    private ArrayAdapter<String> getNextAdapter() {
        ArrayList<String> carsArrayList = new ArrayList<String>(Arrays.asList(CARS));
        Collections.shuffle(carsArrayList);
        return new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, carsArrayList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shuffle:
                shuffleListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shuffleListView() {
        ListView listView = getListView();
        SortingHelper sortingHelper = new SortingHelper(listView);
        listView.setAdapter(getNextAdapter());
        sortingHelper.animateNewState();

    }
}
