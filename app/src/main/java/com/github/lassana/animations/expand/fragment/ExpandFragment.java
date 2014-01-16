package com.github.lassana.animations.expand.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.R;
import com.github.lassana.animations.expand.adapter.ExpandAdapter;
import com.github.lassana.animations.expand.model.ListItemData;

/**
 * @author lassana
 * @since 1/16/14
 */
public class ExpandFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setSelector(android.R.color.transparent);
        setListAdapter(new ExpandAdapter(getActivity(), ListItemData.build(DatasetBuilder.buildLarge())));
    }
}
