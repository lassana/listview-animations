package com.github.lassana.animations.scrolling.fragment;

import android.os.Bundle;
import android.view.View;

import com.github.lassana.animations.base.BaseListFragment;
import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.scrolling.adapter.AnimatedArrayAdapter;
import com.github.lassana.animations.scrolling.animator.AnimateScrollListener;

/**
 * @author lassana
 * @since 3/15/2014
 */
public class ScrollingFragment extends BaseListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AnimatedArrayAdapter adapter = new AnimatedArrayAdapter(getActivity(), DatasetBuilder.buildLarge());
        getListView().setOnScrollListener(new AnimateScrollListener(adapter));
        setListAdapter(adapter);
    }

}
