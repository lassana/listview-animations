package com.github.lassana.animations.scrolling.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.lassana.animations.R;
import com.github.lassana.animations.base.AnimatorHelper;
import com.github.lassana.animations.scrolling.animator.Animate;
import com.github.lassana.animations.base.SkewingRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lassana
 * @since 1/9/14
 */
public class AnimatedArrayAdapter extends BaseAdapter
        implements Animate {

    public static final boolean IS_POST_ICS_ANIMATION_ENABLED
            = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    private final LayoutInflater mLayoutInflater;
    private final List<String> mStringList;

    private int mPreviousPosition = -1;

    private ArrayList<Animator> mAnimatorList = new ArrayList<>();

    private final float mAnimX = 140;
    private final float mAnimY = 140;
    private boolean mAnimate;

    public AnimatedArrayAdapter(Context context, List<String> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mStringList = list;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void setAnimate(boolean animate) {
        mAnimate = animate;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void cancelAnimation() {
        if (IS_POST_ICS_ANIMATION_ENABLED) {
            for (int i = mAnimatorList.size() - 1; i >= 0; --i) {
                mAnimatorList.get(i).cancel();
            }
        }
    }

    class ViewHolder {
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = convertView == null ? null : (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_scrolling, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        viewHolder.title.setText((String) getItem(position));
        if (IS_POST_ICS_ANIMATION_ENABLED && mAnimate) {
            animatePostIcs(position, convertView);
        }
        mPreviousPosition = position;
        return convertView;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void animatePostIcs(int position, View view) {
        float startSkewX = 0.5f;
        float translationX;
        float translationY;

        if (mPreviousPosition < position) {
            translationX = mAnimX;
            translationY = mAnimY;
        } else {
            translationX = -mAnimX;
            translationY = -mAnimY;
        }

        ObjectAnimator skewAnimator = ObjectAnimator.ofFloat(view, "skewX", startSkewX, 0f);
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, translationX, 0f);
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(skewAnimator, translationXAnimator, translationYAnimator);
        animatorSet.setDuration(AnimatorHelper.DURATION_LONG);
        animatorSet.addListener(new AnimatorWithLayerListener(view));

        mAnimatorList.add(animatorSet);

        animatorSet.start();
    }

    /**
     * @author lassana
     * @since 1/9/14
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class AnimatorWithLayerListener implements Animator.AnimatorListener {

        private final View mView;
        private final boolean mIsSkew;
        private int mInitialLayerType;

        public AnimatorWithLayerListener(View view) {
            mView = view;
            mIsSkew = view instanceof SkewingRelativeLayout;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mInitialLayerType = mView.getLayerType();
            mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            ViewCompat.setHasTransientState(mView, true);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mView.setLayerType(mInitialLayerType, null);
            ViewCompat.setHasTransientState(mView, false);
            mAnimatorList.remove(animation);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mView.setTranslationX(0f);
            mView.setTranslationY(0f);
            if (mIsSkew) {
                ((SkewingRelativeLayout) mView).setSkewX(0f);
            }

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
