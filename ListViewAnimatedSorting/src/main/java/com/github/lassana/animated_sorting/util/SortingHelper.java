package com.github.lassana.animated_sorting.util;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.HashMap;

/**
 * @author lassana
 * @since 10/9/13
 */
public class SortingHelper {

    public static final int DURATION_MILLIS = 333;
    private final ListView mListView;

    private HashMap<Object, Integer> mSavedState = new HashMap<Object, Integer>();
    private Interpolator mInterpolator = new DecelerateInterpolator();

    public SortingHelper(ListView listView) {
        this.mListView = listView;
        saveOldState();
    }

    private void saveOldState() {
        mSavedState.clear();
        int first = mListView.getFirstVisiblePosition();
        int last = mListView.getLastVisiblePosition();
        ListAdapter adapter = mListView.getAdapter();
        for(int i=0; i<adapter.getCount(); i++) {
            if( i >= first && i <= last) {
                View v = mListView.getChildAt(i-first);
                Integer top = v.getTop();
                Object dataId = adapter.getItem(i);
                mSavedState.put(dataId, top);
            } else if( i < first ) {
                Integer top = mListView.getTop() - mListView.getHeight()/2;
                Object dataId = adapter.getItem(i);
                mSavedState.put(dataId, top);
            } else if( i > last ) {
                Integer top = mListView.getBottom() + mListView.getHeight()/2;
                Object dataId = adapter.getItem(i);
                mSavedState.put(dataId, top);
            }
        }
        for(int i=0; i < mListView.getChildCount(); i++) {
            View v = mListView.getChildAt(i);
            Integer top = v.getTop();
            int dataIdx = first + i;
            Object dataId = adapter.getItem(dataIdx);
            mSavedState.put(dataId, top);
        }
    }

    public void animateNewState() {
        int first = mListView.getFirstVisiblePosition();
        int last = mListView.getLastVisiblePosition();
        ListAdapter adapter = mListView.getAdapter();
        for(int i=0; i < mListView.getChildCount(); i++) {
            int dataIdx = first + i;
            Object dataId = adapter.getItem(dataIdx);
            if( mSavedState.containsKey(dataId) ) {
                View v = mListView.getChildAt(i);
                int top = v.getTop();
                int oldTop = mSavedState.get(dataId);
                int hDiff = top - oldTop;
                TranslateAnimation anim = new TranslateAnimation(0, 0, -hDiff, 0);
                anim.setInterpolator(mInterpolator);
                anim.setDuration(DURATION_MILLIS);
                v.startAnimation(anim);
            }
        }
    }
}
