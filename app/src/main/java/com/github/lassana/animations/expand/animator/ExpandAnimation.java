package com.github.lassana.animations.expand.animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.github.lassana.animations.base.AnimatorHelper;

/**
 * @author lassana
 * @since 1/16/14
 */
public class ExpandAnimation extends Animation {

    private final View mAnimatedView;
    private final int mEndHeight;
    private final boolean mExpand;

    /**
     * Initializes expand collapse animation, has two types, collapse and expand.
     *
     * @param view     The view to animate
     * @param duration Animation duration
     * @param isExpand The type of animation: true will expand from gone and 0 size
     *                 to visible and layout size defined in xml.
     *                 False will collapse view and set to gone
     */
    public ExpandAnimation(final View view, final int duration, final boolean isExpand) {
        assert view != null;
        setDuration(duration);
        mAnimatedView = view;
        mEndHeight = mAnimatedView.getLayoutParams().height;
        mExpand = isExpand;
        if (mExpand) {
            mAnimatedView.getLayoutParams().height = 0;
            mAnimatedView.setVisibility(View.VISIBLE);
        }
    }

    public ExpandAnimation(View view, boolean isExpand) {
        this(view, AnimatorHelper.DURATION_MEDIUM, isExpand);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            if (mExpand) {
                mAnimatedView.getLayoutParams().height = (int) (mEndHeight * interpolatedTime);
            } else {
                mAnimatedView.getLayoutParams().height = mEndHeight - (int) (mEndHeight * interpolatedTime);
            }
            mAnimatedView.requestLayout();
        } else {
            if (mExpand) {
                mAnimatedView.getLayoutParams().height = mEndHeight;
                mAnimatedView.requestLayout();
            } else {
                mAnimatedView.getLayoutParams().height = 0;
                mAnimatedView.setVisibility(View.GONE);
                mAnimatedView.requestLayout();
                mAnimatedView.getLayoutParams().height = mEndHeight;
            }
        }
    }

}
