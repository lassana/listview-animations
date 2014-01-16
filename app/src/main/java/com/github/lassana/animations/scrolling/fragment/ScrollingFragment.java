package com.github.lassana.animations.scrolling.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.R;
import com.github.lassana.animations.scrolling.adapter.AnimatedArrayAdapter;
import com.github.lassana.animations.scrolling.animator.AnimateScrollListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollingFragment extends ListFragment {

    public ScrollingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AnimatedArrayAdapter adapter
                = new AnimatedArrayAdapter(getActivity(), DatasetBuilder.buildLarge());

        getListView().setOnScrollListener(new AnimateScrollListener(adapter));
        setListAdapter(adapter);
    }

}
