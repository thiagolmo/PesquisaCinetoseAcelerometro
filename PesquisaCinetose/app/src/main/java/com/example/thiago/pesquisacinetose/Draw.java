package com.example.thiago.pesquisacinetose;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by Thiago on 12/11/2016.
 */

public class Draw extends SurfaceView {

    Bitmap image;
    SurfaceHolder ourHolder;


    public Draw(Context context) {
        super(context);
        image = BitmapFactory.decodeResource(getResources(),R.drawable.bettyskateboard);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
