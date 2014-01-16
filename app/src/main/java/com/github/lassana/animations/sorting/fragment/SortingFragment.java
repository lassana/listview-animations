package com.github.lassana.animations.sorting.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.R;
import com.github.lassana.animations.sorting.adapter.EasyAdapter;
import com.github.lassana.animations.sorting.util.SortingHelper;

import java.util.ArrayList;

/**
 * @author lassana
 * @since 10/8/13
 */
public class SortingFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
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
                new ArrayList<String>(DatasetBuilder.build()), getActivity()));
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
        SortingHelper<String> sortingHelper = new SortingHelper<String>(listView);
        ((EasyAdapter)listView.getAdapter()).shuffle();
        sortingHelper.animateNewState();
    }

}
