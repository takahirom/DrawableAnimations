package com.kogitune.canvasanimations.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by takam on 2015/03/01.
 */
public class SpinnerDrawable extends CustomAnimationDrawable {
    private static final int ANIMATION_DURATION = 4000;
    private int width;
    private int height;
    private RectF rect;
    private Paint paint;

    public SpinnerDrawable() {
        super(ANIMATION_DURATION);
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        width = bounds.width();
        height = bounds.height();

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        rect = new RectF(10, 10, width - 10, height - 10);
    }

    @Override
    public void doDraw(Canvas canvas, float interpolationValue) {
        float sweep = 180f;
        if (interpolationValue * 720 < 180) {
            sweep = interpolationValue * 720;
        }
        canvas.drawArc(rect, (interpolationValue * ANIMATION_DURATION) % 360, sweep, false, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
