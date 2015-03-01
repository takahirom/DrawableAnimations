package com.kogitune.canvasanimations.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class CropDrawable extends CustomAnimationDrawable {
    private static final int ANIMATION_DURATION = 2000;
    private final Paint paint;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private int bitmapHeight;
    private Bitmap backgroundBitmap;

    public CropDrawable(Bitmap bitmap, Bitmap backgroundBitmap) {
        super(ANIMATION_DURATION);
        this.bitmap = bitmap;
        this.backgroundBitmap = backgroundBitmap;
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
        drawRect = new Rect(0, 0, bounds.width(), bounds.height());
    }

    @Override
    public void doDraw(Canvas canvas, float interpolationValue) {
        // draw background
        canvas.drawBitmap(backgroundBitmap, new Rect(0, 0, bitmapRect.width(), bitmapHeight), new Rect(0, 0, width, height), paint);

        bitmapRect.top = (int) (bitmapHeight * interpolationValue);
        drawRect.top = (int) (height * bitmapRect.top / bitmapHeight);
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
