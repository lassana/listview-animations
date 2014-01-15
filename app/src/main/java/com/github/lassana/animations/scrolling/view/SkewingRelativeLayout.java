package com.github.lassana.animations.scrolling.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.github.lassana.animations.scrolling.animator.Skew;

/**
 * @author lassana
 * @since 1/9/14
 */
public class SkewingRelativeLayout extends RelativeLayout
        implements Skew {

    private float mSkewX = 0;

    public SkewingRelativeLayout(Context context) {
        super(context);
    }

    public SkewingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkewingRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mSkewX != 0) {
            canvas.skew(mSkewX, 0);
        }
        super.draw(canvas);
    }

    public void setSkewX(float skewX) {
        mSkewX = skewX;
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
