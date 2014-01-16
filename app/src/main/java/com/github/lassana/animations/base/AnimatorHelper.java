package com.github.lassana.animations.base;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @author lassana
 * @since 1/9/14
 */
@SuppressWarnings("ConstantConditions")
public class AnimatorHelper {

    public static final int DURATION_SHORT
            = Resources.getSystem().getInteger(android.R.integer.config_shortAnimTime);
    public static final int DURATION_MEDIUM
            = Resources.getSystem().getInteger(android.R.integer.config_mediumAnimTime);
    public static final int DURATION_LONG
            = Resources.getSystem().getInteger(android.R.integer.config_longAnimTime);

    /**
     * Calculates the height and sets it for views with wrap_content as height.
     */
    public static void setHeightForWrapContent(Activity activity, View view) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int screenWidth = metrics.widthPixels;

        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.EXACTLY);

        view.measure(widthMeasureSpec, heightMeasureSpec);
        int height = view.getMeasuredHeight();
        view.getLayoutParams().height = height;
    }


}
