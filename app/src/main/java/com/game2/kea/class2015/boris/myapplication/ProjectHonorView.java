package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by acer on 16-Apr-15.
 */
public class ProjectHonorView extends View
{

    private Bitmap titleGraphic;
    int screenW;
    int screenH;


    public ProjectHonorView(Context context)
    {

        super(context);

        titleGraphic = BitmapFactory.decodeResource(getResources(),R.drawable.mainmenu);

    }

    public void onSizeChanged(int w, int h, int oldW, int oldH)
    {
        super.onSizeChanged(w, h, oldW, oldH);
        screenW = w;
        screenH = h;
    }


    protected void onDraw(Canvas canvas)
    {
        canvas.drawBitmap(titleGraphic, 15 + (screenW - titleGraphic.getWidth()) / 2, 40, null);

    }




}
