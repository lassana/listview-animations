package com.github.lassana.animations.scrolling.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * @author lassana
 * @since 1/9/14
 */
public class SkewingRelativeLayout extends RelativeLayout {

    private float skewX = 0f;

    public SkewingRelativeLayout(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public SkewingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public SkewingRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        if (skewX != 0) {
            canvas.skew(skewX, 0);
        }
        super.draw(canvas);
    }

    public void setSkewX(float skewX) {
        this.skewX = skewX;
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
