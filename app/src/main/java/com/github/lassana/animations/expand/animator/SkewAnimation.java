package com.github.lassana.animations.expand.animator;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.github.lassana.animations.base.AnimatorHelper;
import com.github.lassana.animations.base.SkewingRelativeLayout;

/**
 * @author Nikolai Doronin
 * @since 1/16/14
 */
public class SkewAnimation extends Animation {

    private final SkewingRelativeLayout mAnimatedView;
    private float mStartSkewX;
    private float mStartSkewY;
    private float mFinishSkewX;
    private float mFinishSkewY;

    public SkewAnimation(final SkewingRelativeLayout view,
                         float finishSkewX,
                         float finishSkewY) {
        this(view, AnimatorHelper.DURATION_MEDIUM, finishSkewX, finishSkewY);
    }

    public SkewAnimation(final SkewingRelativeLayout view,
                         final int duration,
                         float finishSkewX,
                         float finishSkewY) {
        mAnimatedView = view;
        mFinishSkewX = finishSkewX;
        mFinishSkewY = finishSkewY;
        mStartSkewX = view.getSkewX();
        mStartSkewY = view.getSkewY();
        setDuration(duration);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            float skewX, skewY;
            if (mStartSkewX < mFinishSkewX) {
                skewX = mStartSkewX + interpolatedTime * Math.abs(mFinishSkewX - mStartSkewX);
            } else {
                skewX = mStartSkewX - interpolatedTime * Math.abs(mFinishSkewX - mStartSkewX);
            }
            if (mStartSkewY < mFinishSkewY) {
                skewY = mStartSkewY + interpolatedTime * Math.abs(mFinishSkewY - mStartSkewY);
            } else {
                skewY = mStartSkewY - interpolatedTime * Math.abs(mFinishSkewY - mStartSkewY);
            }
            mAnimatedView.setSkews(skewX, skewY);
        } else {
            mAnimatedView.setSkews(mFinishSkewX, mFinishSkewY);
        }
    }
}
