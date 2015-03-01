package com.kogitune.canvasanimations.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class CropDrawable extends CustomAnimationDrawable {
    private static final int ANIMATION_DURATION = 3000;
    private final Paint paint;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private RectF drawRect;
    private int bitmapHeight;

    public CropDrawable(Bitmap bitmap) {
        super(ANIMATION_DURATION);
        this.bitmap = bitmap;
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        bitmapHeight = bitmap.getHeight();
        paint = new Paint();
        paint.setDither(true);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        width = bounds.width();
        height = bounds.height();
        drawRect = new RectF(0, 0, bounds.width(), bounds.height());
    }

    @Override
    public void doDraw(Canvas canvas, float interpolationValue) {
        drawRect.top = height * interpolationValue;
        bitmapRect.top = Math.round(bitmapHeight * interpolationValue);
        canvas.drawBitmap(bitmap, bitmapRect, drawRect, paint);
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
