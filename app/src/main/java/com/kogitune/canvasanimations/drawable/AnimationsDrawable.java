package com.kogitune.canvasanimations.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by takam on 2015/03/01.
 */
public class AnimationsDrawable extends AnimationDrawable {
    @Override
    public void run() {
        super.run();
        // FrameChanged
        final Drawable currentDrawable = getCurrent();
        if (currentDrawable instanceof Animatable) {
            ((Animatable) currentDrawable).start();
        }
    }
}
