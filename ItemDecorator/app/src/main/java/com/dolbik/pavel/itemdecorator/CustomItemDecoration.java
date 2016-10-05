package com.dolbik.pavel.itemdecorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;


public class CustomItemDecoration extends RecyclerView.ItemDecoration {


    private Drawable divider;
    private int      orientation;
    private int      startOffsetPx;
    private int      endOffsetPx;


    /** @param divider Разделитель который будет нарисован в RecyclerView. <br>
     *  A divider to be drawn on the RecyclerView. */
    public CustomItemDecoration(Drawable divider) {
        this.divider = divider;
    }


    /** @param divider  Разделитель который будет нарисован в RecyclerView. A divider to be drawn on the RecyclerView.
     *  @param startOffsetPx Размер смещения, в пикселях, которое будет добавлено в начало RecyclerView.
     *  The size of the offset to be added to the start of the RecyclerView in pixels.
     *  @param endOffsetPx Размер смещения, в пикселях, которое будет добавлено в конец RecyclerView.
     *  The size of the offset to be added to the end of the RecyclerView in pixels. */
    public CustomItemDecoration(Drawable divider, int startOffsetPx, int endOffsetPx) {
        this.divider       = divider;
        this.startOffsetPx = startOffsetPx;
        this.endOffsetPx   = endOffsetPx;
    }


    /** @param resId  Id разделителя который будет нарисован в RecyclerView. <br>
     *  A divider Id to be drawn on the RecyclerView. */
    public CustomItemDecoration(Context context, int resId) {
        divider = ContextCompat.getDrawable(context, resId);
    }


    /** @param resId    Id разделителя который будет нарисован в RecyclerView. A divider Id to be drawn on the RecyclerView.
     *  @param startOffsetPx Размер смещения, в пикселях, которое будет добавлено в начало RecyclerView.
     *  The size of the offset to be added to the start of the RecyclerView in pixels.
     *  @param endOffsetPx Размер смещения, в пикселях, которое будет добавлено в конец RecyclerView.
     *  The size of the offset to be added to the end of the RecyclerView in pixels.*/
    public CustomItemDecoration(Context context, int resId, int startOffsetPx, int endOffsetPx) {
        divider = ContextCompat.getDrawable(context, resId);
        this.startOffsetPx = startOffsetPx;
        this.endOffsetPx   = endOffsetPx;
    }


    /** Рисуем горизонтальный или вертикальный разделитель в зависимости от родительского RecyclerView. <br>
     *  Draws horizontal or vertical dividers onto the parent RecyclerView.
     *
     *  @param canvas {@link Canvas} на которой разделитель будет нарисован. The {@link Canvas} onto which dividers will be drawn.
     *  @param parent RecyclerView в который будет добавлен разделитель. The RecyclerView onto which dividers are being added.
     *  @param state  Текущее RecyclerView.State в RecyclerView. The current RecyclerView.State of the RecyclerView. */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (orientation == LinearLayout.HORIZONTAL) {
            drawHorizontalDividers(canvas, parent);
        } else {
            drawVerticalDividers(canvas, parent);
        }
    }


    /** Добавляем разделитель в RecyclerView с LinearLayoutManager или его подклассом, который имеет горизонтальную ориентацию. <br>
     *  Adds dividers to a RecyclerView with a LinearLayoutManager or its subclass oriented horizontally.*/
    private void drawHorizontalDividers(Canvas canvas, RecyclerView parent){
        int parentTop    = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount -1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int parentLeft  = child.getRight() + params.rightMargin;
            int parentRight = parentLeft + divider.getIntrinsicWidth();

            divider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            divider.draw(canvas);
        }
    }


    /** Добавляем разделитель в RecyclerView с LinearLayoutManager или его подклассом, который имеет вертикальную ориентацию. <br>
     *  Adds dividers to a RecyclerView with a LinearLayoutManager or its subclass oriented vertically.*/
    private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
        int parentLeft  = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int parentTop = child.getBottom() + params.bottomMargin;
            int parentBottom = parentTop + divider.getIntrinsicHeight();

            divider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            divider.draw(canvas);
        }
    }


    /** Определяем размер и положение для смещения между items в RecyclerView. <br>
     *  Determines the size and location of offsets between items in the parent RecyclerView.
     *
     *  @param outRect {@link Rect} смещение, которое будет добавлено вокруг ребенка. offsets to be added around the child view.
     *  @param view    Дочерняя view которая будет декорированна со смещением. The child view to be decorated with an offset.
     *  @param parent  RecyclerView в который будет добавлен разделитель. The RecyclerView onto which dividers are being added.
     *  @param state   Текущее RecyclerView.State в RecyclerView. The current RecyclerView.State of the RecyclerView. */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        orientation = ((LinearLayoutManager)parent.getLayoutManager()).getOrientation();

        //Смещение для первого элемента. Offset for the first element.
        if(parent.getChildAdapterPosition(view) == 0) {
            if(startOffsetPx > 0) {
                if(orientation == LinearLayoutManager.HORIZONTAL) {
                    outRect.left = startOffsetPx;
                } else {
                    outRect.top = startOffsetPx;
                }
            }
        }

        //Смещение для последнего элемента. Offset for the last element.
        else if(parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            if(endOffsetPx > 0) {
                if(orientation == LinearLayoutManager.HORIZONTAL) {
                    outRect.right = endOffsetPx;
                    outRect.left = divider.getIntrinsicWidth();
                } else {
                    outRect.bottom = endOffsetPx;
                    outRect.top = divider.getIntrinsicHeight();
                }
            }
        }

        //Отображаем разделитель между элементами. Showing divider between the elements.
        else {
            if(orientation == LinearLayoutManager.HORIZONTAL) {
                outRect.left = divider.getIntrinsicWidth();
            } else {
                outRect.top = divider.getIntrinsicHeight();
            }
        }
    }

}
