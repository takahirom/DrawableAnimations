package com.kogitune.canvasanimations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.kogitune.canvasanimations.drawable.AnimationsDrawable;
import com.kogitune.canvasanimations.drawable.CropDrawable;
import com.kogitune.canvasanimations.drawable.ShinyDrawable;
import com.kogitune.canvasanimations.drawable.SpinnerDrawable;
import com.kogitune.canvasanimations.drawable.SpreadDrawable;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewDrawables();
        setupAnimationsViewDrawable();
    }

    private void setupAnimationsViewDrawable() {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_black_48dp);
        final Bitmap maskBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp);

        final AnimationsDrawable animationsDrawable = new AnimationsDrawable();
        animationsDrawable.setOneShot(false);

        final CropDrawable cropDrawable = new CropDrawable(bitmap,maskBitmap);
        animationsDrawable.addFrame(cropDrawable, cropDrawable.getAnimationDuration());

        final SpreadDrawable spreadDrawable = new SpreadDrawable(maskBitmap);
        animationsDrawable.addFrame(spreadDrawable, spreadDrawable.getAnimationDuration());

        final ShinyDrawable shinyDrawable = new ShinyDrawable(bitmap,maskBitmap);
        animationsDrawable.addFrame(shinyDrawable, shinyDrawable.getAnimationDuration());

        findViewById(R.id.animations).setBackgroundDrawable(animationsDrawable);
        animationsDrawable.start();
    }

    private void setupViewDrawables() {
        final SpinnerDrawable spinnerDrawable = new SpinnerDrawable();
        findViewById(R.id.spinner).setBackgroundDrawable(spinnerDrawable);
        spinnerDrawable.start();

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_black_48dp);
        final Bitmap maskBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp);

        final CropDrawable cropDrawable = new CropDrawable(bitmap, maskBitmap);
        findViewById(R.id.crop).setBackgroundDrawable(cropDrawable);
        cropDrawable.start();

        final SpreadDrawable spreadDrawable = new SpreadDrawable(bitmap);
        findViewById(R.id.spread).setBackgroundDrawable(spreadDrawable);
        spreadDrawable.start();

        final ShinyDrawable shinyDrawable = new ShinyDrawable(bitmap, maskBitmap);
        findViewById(R.id.shiny).setBackgroundDrawable(shinyDrawable);
        shinyDrawable.start();
    }

    public void start(View view) {
        final Drawable background = view.getBackground();
        if (background instanceof Animatable) {
            ((Animatable) background).start();
        }
    }
}
