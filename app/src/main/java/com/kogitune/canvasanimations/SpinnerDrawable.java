package com.kogitune.canvasanimations;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.view.animation.LinearInterpolator;

/**
 * Created by takam on 2015/02/28.
 */
public class SpinnerDrawable extends Drawable implements Animatable, Runnable {
    private final Paint paint;
    private static final long FRAME_DURATION = 1000 / 60;
    private static final int ANIMATION_DURATION = 250;
    private long mStartTime;
    private boolean mRunning;
    private TimeInterpolator mInterpolator = new LinearInterpolator();
    private float mInterpolationValue;

    public SpinnerDrawable(){
        paint = new Paint();
    }
    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public void start() {
        mRunning = true;
        mStartTime = SystemClock.uptimeMillis();
        scheduleSelf(this, mStartTime + FRAME_DURATION);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    @Override
    public void run() {
        long currentTime = SystemClock.uptimeMillis();
        long diff = currentTime - mStartTime;
        if (diff < ANIMATION_DURATION) {
            mInterpolationValue = mInterpolator.getInterpolation((float) diff / (float) ANIMATION_DURATION);
            scheduleSelf(this, currentTime + FRAME_DURATION);
            invalidateSelf();
        } else {
            unscheduleSelf(this);
            mRunning = false;
            mInterpolationValue = 1f;
            notifyFinishedToListener();
        }
    }

    private void notifyFinishedToListener() {

    }
}
