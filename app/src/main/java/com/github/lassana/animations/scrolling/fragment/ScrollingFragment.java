package com.github.lassana.animations.scrolling.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lassana.animations.R;
import com.github.lassana.animations.scrolling.adapter.AnimatedArrayAdapter;
import com.github.lassana.animations.scrolling.animator.AnimateScrollListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollingFragment extends ListFragment {

    private static String[] ARRAY_DATA
            = {"BMW", "DeLorean", "Alfa Romeo", "Mercedes", "Toyota",
            "GMC", "Volvo", "Saab", "Ferrari", "Tesla"};

    public ScrollingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) Collections.addAll(arrayList, ARRAY_DATA);
        Collections.shuffle(arrayList);
        final AnimatedArrayAdapter adapter = new AnimatedArrayAdapter(getActivity(), arrayList);

        getListView().setOnScrollListener(new AnimateScrollListener(adapter));
        setListAdapter(adapter);
    }

}
