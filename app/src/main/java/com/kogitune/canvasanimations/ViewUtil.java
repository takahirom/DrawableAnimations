package com.kogitune.canvasanimations;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Created by takam on 2015/03/01.
 */
public class ViewUtil {
    public static void setBackground(View view,Drawable drawable){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
