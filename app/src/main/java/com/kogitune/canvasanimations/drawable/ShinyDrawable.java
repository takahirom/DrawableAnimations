package com.kogitune.canvasanimations.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class ShinyDrawable extends CustomAnimationDrawable {
    private static final int ANIMATION_DURATION = 1000;
    private final Paint backgroundPaint;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private RectF drawRect;
    private Bitmap maskBitmap;
    private Paint paint;
    private Paint maskPaint;

    public ShinyDrawable(Bitmap bitmap, Bitmap maskBitmap) {
        super(ANIMATION_DURATION);
        this.bitmap = bitmap;
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        this.maskBitmap = maskBitmap;
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);


        backgroundPaint = new Paint();

        maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
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
        canvas.drawBitmap(maskBitmap, bitmapRect, drawRect, new Paint());
        backgroundPaint.setAlpha((int) (255 * interpolationValue));
        canvas.drawBitmap(bitmap, bitmapRect, drawRect, backgroundPaint);

        Bitmap result = createShinyBitmap(interpolationValue);
        canvas.drawBitmap(result, 0, 0, new Paint());
        result.recycle();
        result = null;
    }

    private Bitmap createShinyBitmap(float fPercent) {
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(result);

        tempCanvas.drawLine(fPercent * width * 2, 0, 0, fPercent * height * 2, paint);
        tempCanvas.drawBitmap(maskBitmap, bitmapRect, drawRect, maskPaint);
        return result;
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
