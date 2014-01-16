package com.github.lassana.animations.base;

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
    private float skewY = 0f;

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
        if (!(skewX == 0 && skewY == 0)) {
            canvas.skew(skewX, skewY);
        }
        super.draw(canvas);
    }

    public void setSkewY(float skewY) {
        this.skewY = skewY;
    }

    public void setSkewX(float skewX) {
        this.skewX = skewX;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public float getSkewX() {
        return skewX;
    }

    public float getSkewY() {
        return skewY;
    }

    public void setSkews(float skewX, float skewY) {
        this.skewX = skewX;
        this.skewY = skewY;
    }
}
