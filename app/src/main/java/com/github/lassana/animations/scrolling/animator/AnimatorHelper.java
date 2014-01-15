package com.github.lassana.animations.scrolling.animator;

import android.content.res.Resources;

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

}
