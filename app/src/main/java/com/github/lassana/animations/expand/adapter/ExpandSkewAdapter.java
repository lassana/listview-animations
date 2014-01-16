package com.github.lassana.animations.expand.adapter;

import android.app.Activity;
import android.os.Build;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.github.lassana.animations.base.SkewingRelativeLayout;
import com.github.lassana.animations.expand.animator.SkewAnimation;
import com.github.lassana.animations.expand.model.ListItemData;

import java.util.List;

/**
 * @author Nikolai Doronin
 * @since 1/16/14
 */
public class ExpandSkewAdapter extends ExpandAdapter {

    private boolean mIsSkewLayout;
    private boolean mLayoutTypeObtained = false;
    private boolean mNeedAnimation = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

    public ExpandSkewAdapter(Activity activity, List<ListItemData> dataset) {
        super(activity, dataset);
    }

    @Override
    protected Animation[] getAdditionalAnimations(ViewGroup content, boolean isExpand) {
        if (mNeedAnimation) {
            if (!mLayoutTypeObtained) {
                mIsSkewLayout = content instanceof SkewingRelativeLayout;
                mLayoutTypeObtained = true;
            }
            if (mIsSkewLayout) {
                SkewAnimation skewAnimation;
                if (isExpand) {
                    ((SkewingRelativeLayout)content).setSkewX(-1.0f);
                    ((SkewingRelativeLayout)content).setSkewY(-1.0f);
                    skewAnimation = new SkewAnimation(((SkewingRelativeLayout)content), 0f, 0f);
                } else {
                    ((SkewingRelativeLayout)content).setSkewX(0f);
                    ((SkewingRelativeLayout)content).setSkewY(0f);
                    skewAnimation = new SkewAnimation(((SkewingRelativeLayout)content), -1.0f, -1.0f);
                }
                return new Animation[]{skewAnimation};
            }
        }
        return null;
    }

}
