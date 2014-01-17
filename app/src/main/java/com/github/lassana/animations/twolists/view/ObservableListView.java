package com.github.lassana.animations.twolists.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * @author Nikolai Doronin
 * @since 1/17/14
 */
public class ObservableListView extends ListView {

    public static interface ListViewObserver {
        public void onScroll(float deltaY);
    }

    private ListViewObserver mObserver;
    private View mTrackedChild;
    private int mTrackedChildPrevPosition;
    private int mTrackedChildPrevTop;

    public ObservableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mTrackedChild == null) {
            if (getChildCount() > 0) {
                mTrackedChild = getChildInTheMiddle();
                mTrackedChildPrevTop = mTrackedChild.getTop();
                mTrackedChildPrevPosition = getPositionForView(mTrackedChild);
            }
        } else {
            boolean childIsSafeToTrack = mTrackedChild.getParent() == this && getPositionForView(mTrackedChild) == mTrackedChildPrevPosition;
            if (childIsSafeToTrack) {
                int top = mTrackedChild.getTop();
                if (mObserver != null) {
                    float deltaY = top - mTrackedChildPrevTop;
                    mObserver.onScroll(deltaY);
                }
                mTrackedChildPrevTop = top;
            } else {
                mTrackedChild = null;
            }
        }
    }

    private View getChildInTheMiddle() {
        return getChildAt(getChildCount() / 2);
    }

    public void setObserver(ListViewObserver observer) {
        mObserver = observer;
    }

}