package com.dolbik.pavel.itemdecorator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


public class ParallaxDecoration extends RecyclerView.ItemDecoration {

    private Bitmap bitmap;

    public ParallaxDecoration(Context context, @DrawableRes int resId) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(view) == 5) {
                int offset = view.getTop() / 3;
                c.drawBitmap(
                        bitmap,
                        new Rect(0, offset, bitmap.getWidth(), view.getHeight() + offset),
                        new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()),
                        null);
            }
        }
    }
}
