package com.kogitune.canvasanimations.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class SpreadDrawable extends CustomAnimationDrawable {
    private static final int ANIMATION_DURATION = 3000;
    private final Paint paint;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private Rect spreadRect;

    public SpreadDrawable(Bitmap bitmap) {
        super(ANIMATION_DURATION);
        this.bitmap = bitmap;
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint = new Paint();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        final int margin = bounds.width() / 4;
        width = bounds.width();
        height = bounds.height();
        drawRect = new Rect(0, 0, width, height);
        spreadRect = new Rect(-margin, -margin, width + margin, height + margin);
    }

    @Override
    public void doDraw(Canvas canvas, float interpolationValue) {
        final float top = spreadRect.top * interpolationValue + drawRect.top * (1 - interpolationValue);
        final float left = spreadRect.left * interpolationValue + drawRect.left * (1 - interpolationValue);
        final float right = spreadRect.right * interpolationValue + drawRect.right * (1 - interpolationValue);
        final float bottom = spreadRect.bottom * interpolationValue + drawRect.bottom * (1 - interpolationValue);
        paint.setAlpha((int) ((1f - interpolationValue) * 255));

        canvas.drawBitmap(bitmap, bitmapRect, new RectF(left, top, right, bottom), paint);
        canvas.drawBitmap(bitmap, bitmapRect, drawRect, new Paint());
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
