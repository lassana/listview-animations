package com.github.lassana.animations.scrolling.animator;

import android.os.SystemClock;
import android.util.Log;
import android.widget.AbsListView;

/**
 * @author lassana
 * @since 1/9/14
 */
public class AnimateScrollListener implements AbsListView.OnScrollListener {

    private static final String TAG = "AnimateScrollListener";
    public static final int MAX_PERMITTED_SPEED = 24;
    private Animate mAnimate;

    private int mScrollState;

    private int mPreviousFirstVisibleItem = 0;
    private long mPreviousEventTime = 0;

    public AnimateScrollListener(Animate animate) {
        mAnimate = animate;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mScrollState = scrollState;
        mAnimate.setAnimate(scrollState == SCROLL_STATE_FLING
                || scrollState == SCROLL_STATE_TOUCH_SCROLL);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mPreviousFirstVisibleItem != firstVisibleItem) {
            long currentTime = SystemClock.elapsedRealtime();
            long timeToScrollOneElement = currentTime - mPreviousEventTime;
            double speed = (1d / timeToScrollOneElement) * 1000;

            Log.d(TAG, "speed: " + speed);

            mPreviousFirstVisibleItem = firstVisibleItem;
            mPreviousEventTime = currentTime;

            if (mScrollState == SCROLL_STATE_FLING && speed > MAX_PERMITTED_SPEED) {
                mAnimate.setAnimate(false);
                mAnimate.cancelAnimation();
            } else {
                mAnimate.setAnimate(true);
            }
        }
    }
}
