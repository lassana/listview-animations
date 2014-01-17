package com.github.lassana.animations.twolists.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.github.lassana.animations.R;
import com.github.lassana.animations.base.DatasetBuilder;
import com.github.lassana.animations.twolists.view.ObservableListView;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Nikolai Doronin
 * @since 1/17/14
 */
public class TwoListFragment extends Fragment {

    private ObservableListView mListViewA;
    private ObservableListView mListViewB;

    private int listViewAHeight;
    private int listViewBHeight;

    private long mPreviousEventTimeA = 0;

    private int mPreviousOffset = 0;

    private ListAdapter mAdapterA;
    private ListAdapter mAdapterB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_lists, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListViewA = (ObservableListView) view.findViewById(R.id.listA);
        mListViewB = (ObservableListView) view.findViewById(R.id.listB);

        mAdapterA = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, DatasetBuilder.buildLarge(5));
        mAdapterB = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, DatasetBuilder.buildLarge(50));

        mListViewA.setAdapter(mAdapterA);
        mListViewB.setAdapter(mAdapterB);

        mListViewA.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listViewAHeight == 0) {
                    View child = mListViewA.getChildAt(0);
                    if (child == null) {
                        return;
                    }
                    listViewAHeight = mAdapterA.getCount() * child.getMeasuredHeight();
                }
                if (listViewBHeight == 0) {
                    View child = mListViewB.getChildAt(0);
                    if (child == null) {
                        return;
                    }
                    listViewBHeight = mAdapterB.getCount() * child.getMeasuredHeight();
                }
                int neededOffset = (getScrollA() * listViewBHeight) / listViewAHeight;

                int scroll = neededOffset - getScrollB();

                long time = SystemClock.elapsedRealtime();
                mListViewB.smoothScrollBy(scroll, (int) (time - mPreviousEventTimeA));
                mPreviousEventTimeA = time;
                //int scroll =
                //Log.v("onScroll", "scroll: " + scroll);
                //
                //mListViewB.smoothScrollBy((adapterB.getCount()/adapterA.getCount())*(scroll-mPreviousOffset), (int)(time-mPreviousEventTimeA));
                //mPreviousOffset = scroll;
                //
            }
        });

        /*mListViewA.setObserver(new ObservableListView.ListViewObserver() {
            @Override
            public void onScroll(float deltaY) {
                //int position = mListViewA.getFirstVisiblePosition();
                mCurrentOffset += deltaY;
                Log.v("onScroll", "mCurrentOffset: " + mCurrentOffset);

                long time = SystemClock.elapsedRealtime();
                //mListViewB.smoothScrollBy(10*(int)-deltaY, (int)(time-mPreviousEventTimeA));
                mPreviousEventTimeA = time;
            }
        });*/
    }

    private Dictionary<Integer, Integer> listViewAItemHeights = new Hashtable<>();
    private Dictionary<Integer, Integer> listViewBItemHeights = new Hashtable<>();

    private int getScrollA() {
        View c = mListViewA.getChildAt(0); //this is the first visible row
        if (c == null) return 0;
        int scrollY = -c.getTop();
        listViewAItemHeights.put(mListViewA.getFirstVisiblePosition(), c.getHeight());
        for (int i = 0; i < mListViewA.getFirstVisiblePosition(); ++i) {
            if (listViewAItemHeights.get(i) != null) // (this is a sanity check)
                scrollY += listViewAItemHeights.get(i); //add all heights of the views that are gone
        }
        return scrollY;
    }

    private int getScrollB() {
        View c = mListViewB.getChildAt(0); //this is the first visible row
        if (c == null) return 0;
        int scrollY = -c.getTop();
        listViewBItemHeights.put(mListViewB.getFirstVisiblePosition(), c.getHeight());
        for (int i = 0; i < mListViewB.getFirstVisiblePosition(); ++i) {
            if (listViewBItemHeights.get(i) != null) // (this is a sanity check)
                scrollY += listViewBItemHeights.get(i); //add all heights of the views that are gone
        }
        return scrollY;
    }
}
