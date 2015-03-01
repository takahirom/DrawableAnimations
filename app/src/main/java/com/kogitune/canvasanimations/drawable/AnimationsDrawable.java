package com.kogitune.canvasanimations.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;

/**
 * Created by takam on 2015/03/01.
 */
public class AnimationsDrawable extends AnimationDrawable {
    @Override
    public void run() {
        super.run();
        // onFrameChanged
        if (getCurrent() instanceof Animatable) {
            ((Animatable) getCurrent()).start();
        }
    }
}
