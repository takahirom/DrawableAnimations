package com.kogitune.canvasanimations.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.LinearInterpolator;

/**
 * Created by takam on 2015/02/28.
 */
abstract public class CustomAnimationDrawable extends Drawable implements Animatable, Runnable {
    private static final long FRAME_DURATION = 1000 / 60;
    private final int animationDuration;

    private AnimationListener animationListener;

    private long startTime;
    private boolean running;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private float interpolationValue;

    interface AnimationListener {
        void onAnimationEnd();
    }

    public CustomAnimationDrawable(int animationDuration) {
        super();
        this.animationDuration = animationDuration;
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    @Override
    public void draw(Canvas canvas) {
        doDraw(canvas, interpolationValue);
    }

    abstract public void doDraw(Canvas canvas, float interpolationValue);

    @Override
    public void start() {
        running = true;
        interpolationValue = 0f;
        startTime = SystemClock.uptimeMillis();
        scheduleSelf(this, startTime + FRAME_DURATION);
    }

    @Override
    public void stop() {
        unscheduleSelf(this);
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        long currentTime = SystemClock.uptimeMillis();
        long diff = currentTime - startTime;
        if (diff < animationDuration) {
            interpolationValue = interpolator.getInterpolation((float) diff / (float) animationDuration);
            scheduleSelf(this, currentTime + FRAME_DURATION);
            invalidateSelf();
        } else {
            unscheduleSelf(this);
            running = false;
            interpolationValue = 1f;
            notifyFinishedToListener();
            invalidateSelf();
        }
    }

    private void notifyFinishedToListener() {
        if (animationListener != null) {
            animationListener.onAnimationEnd();
        }
    }

    public int getAnimationDuration() {
        return animationDuration;
    }
}
