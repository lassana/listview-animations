package com.github.lassana.animations.expand.fragment;

import android.os.Bundle;
import android.view.View;

import com.github.lassana.animations.base.BaseListFragment;
import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.expand.adapter.ExpandSkewAdapter;
import com.github.lassana.animations.expand.model.ListItemData;

import java.util.List;

/**
 * @author lassana
 * @since 1/16/14
 */
public class ExpandFragment extends BaseListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setSelector(android.R.color.transparent);
        List<String> strings = DatasetBuilder.buildLarge();
        setListAdapter(new ExpandSkewAdapter(getActivity(), ListItemData.build(strings)));
    }
}
