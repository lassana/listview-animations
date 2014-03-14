package com.github.lassana.animations.scrolling.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.github.lassana.animations.base.AnimatorHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lassana
 * @since 3/14/2014
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ListItemAnimatorSetBuilder {

    private static final float ANIMATOR_SKEW_START = 0.5f;
    private static final float ANIMATOR_TRANSLATE_X = 140;
    private static final float ANIMATOR_TRANSLATE_Y = 140;

    private List<Animator> mAnimatorList = new ArrayList<>();

    private ListItemAnimatorSetBuilder addAnimatorsToList(Animator... animators) {
        Collections.addAll(mAnimatorList, animators);
        return this;
    }

    public AnimatorSet build() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(mAnimatorList);
        animatorSet.setDuration(AnimatorHelper.DURATION_LONG);
        mAnimatorList.clear();
        return animatorSet;
    }

    public ListItemAnimatorSetBuilder addTranslateAnimator(View view, int position, int previousPosition) {
        float translationX, translationY;
        if (previousPosition < position) {
            translationX = ANIMATOR_TRANSLATE_X;
            translationY = ANIMATOR_TRANSLATE_Y;
        } else {
            translationX = -ANIMATOR_TRANSLATE_X;
            translationY = -ANIMATOR_TRANSLATE_Y;
        }
        return addAnimatorsToList(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, translationX, 0f),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY, 0f));
    }

    public ListItemAnimatorSetBuilder addSkewAnimator(View view) {
        return addAnimatorsToList(ObjectAnimator.ofFloat(view, "skewX", ANIMATOR_SKEW_START, 0f));
    }

    public ListItemAnimatorSetBuilder addTextColorAnimator(TextView textView, int fromColor, int toColor) {
        final Property<TextView, Integer> property = new Property<TextView, Integer>(int.class, "textColor") {
            @Override
            public Integer get(TextView object) {
                return object.getCurrentTextColor();
            }

            @Override
            public void set(TextView object, Integer value) {
                object.setTextColor(value);
            }
        };
        textView.setTextColor(fromColor);
        final ObjectAnimator animator = ObjectAnimator.ofInt(textView, property, toColor);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setInterpolator(new DecelerateInterpolator(2));
        return addAnimatorsToList(animator);
    }

}
