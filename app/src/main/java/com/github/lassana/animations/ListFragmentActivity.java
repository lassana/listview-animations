package com.github.lassana.animations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.github.lassana.animations.expand.fragment.ExpandFragment;
import com.github.lassana.animations.scrolling.fragment.ScrollingFragment;
import com.github.lassana.animations.sorting.fragment.SortingFragment;
import com.github.lassana.animations.twolists.fragment.TwoListFragment;

/**
 * @author lassana
 * @since 1/16/14
 */
public class ListFragmentActivity extends ActionBarActivity {

    public static final String EXTRA_FRAGMENT_ID = "extra_fragment_activity";
    public static final int FRAGMENT_LIST_SORTING = 1;
    public static final int FRAGMENT_LIST_SCROLLING = 2;
    public static final int FRAGMENT_EXPAND_LIST_ITEM = 3;
    public static final int FRAGMENT_TWO_LISTS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
            Fragment fragment;
            int titleResId;
            switch (getIntent().getIntExtra(EXTRA_FRAGMENT_ID, 0)) {
                case FRAGMENT_LIST_SORTING:
                    fragment = new SortingFragment();
                    titleResId = R.string.sorting;
                    break;
                case FRAGMENT_LIST_SCROLLING:
                    fragment = new ScrollingFragment();
                    titleResId = R.string.scrolling;
                    break;
                case FRAGMENT_EXPAND_LIST_ITEM:
                    fragment = new ExpandFragment();
                    titleResId = R.string.expand;
                    break;
                case FRAGMENT_TWO_LISTS:
                    fragment = new TwoListFragment();
                    titleResId = R.string.two_lists;
                    break;
                default:
                    throw new IllegalStateException("Intent has no EXTRA_FRAGMENT_ID");
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
            setTitle(titleResId);
        }
    }

}
