package com.github.lassana.animated_sorting.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        listView.setAdapter(new EasyAdapter(
                new ArrayList<String>(Arrays.asList(CARS)), getActivity()));
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
        ((EasyAdapter)listView.getAdapter()).shuffle();
        sortingHelper.animateNewState();
    }

    private static class EasyAdapter extends BaseAdapter {

        private ArrayList<String> mDataSet;
        private Context mContext;

        public EasyAdapter(ArrayList<String> dataSet, Context context) {
            this.mDataSet = dataSet;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mDataSet.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataSet.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if( v == null ) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                v = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) v.findViewById(android.R.id.text1);
            tv.setText(mDataSet.get(position));
            tv.setTextColor(Color.MAGENTA);
            tv.setBackgroundColor(Color.LTGRAY);
            return v;
        }


        public void shuffle() {
            Collections.shuffle(mDataSet);
        }
    }
}
