package com.dolbik.pavel.itemdecorator;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;

public class HeaderDecoration extends RecyclerView.ItemDecoration {


    private View    headerView;
    private boolean horizontalOrientation;
    private float   parallax;
    private int     column;


    public HeaderDecoration(View  headerView, boolean horizontalOrientation,
                            float parallax,   int     column) {
        this.headerView            = headerView;
        this.parallax              = parallax;
        this.column                = column;
        this.horizontalOrientation = horizontalOrientation;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // Устанавливаем размер и положение headerView.
        // Gets drawn on the reserved space on top of the first view.
        headerView.layout(parent.getLeft(), 0, parent.getRight(), headerView.getMeasuredHeight());
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(view) == 0) {
                c.save();
                if (horizontalOrientation) {
                    final int width = headerView.getMeasuredWidth();
                    final float left = (view.getLeft() - width) * parallax;
                    c.translate(left, 0);
                    headerView.draw(c);
                } else {
                    final int height = headerView.getMeasuredHeight();
                    final float top = (view.getTop() - height) * parallax;
                    c.translate(0, top);
                    headerView.draw(c);
                }
                c.restore();
                break;
            }
        }
    }


    /** Определяем размер и положение headerView в RecyclerView. <br> */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) < column) {
            if (horizontalOrientation) {
                if (headerView.getMeasuredWidth() <= 0) {
                    headerView.measure(View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), View.MeasureSpec.AT_MOST));
                }
                outRect.set(headerView.getMeasuredWidth(), 0, 0, 0);
            } else {
                if (headerView.getMeasuredHeight() <= 0) {
                    headerView.measure(View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), View.MeasureSpec.AT_MOST));
                }
                outRect.set(0, headerView.getMeasuredHeight(), 0, 0);
            }
        } else {
            outRect.setEmpty();
        }
    }


    public static class Builder {
        private WeakReference<Context> context;
        private View                   headerView;
        private boolean                horizontalOrientation;
        private float                  parallax = 1f;
        private int                    column   = 1;


        public Builder(Context context) {
            this.context = new WeakReference<>(context);
        }


        public Builder(Context context, boolean horizontal) {
            this.context = new WeakReference<>(context);
            this.horizontalOrientation = horizontal;
        }


        public Builder(Context context, int column) {
            this.context = new WeakReference<>(context);
            this.column = column;
        }


        public Builder inflate(@LayoutRes int layoutRes) {
            headerView = LayoutInflater.from(context.get()).inflate(layoutRes, null, false);
            return this;
        }


        /** Добавляем эффект смещения. Adds a parallax effect.
         *  @param parallax  0f - стоит на месте, 1f - движется с первым элементом. <br>
         *                   0f would be the view standing still, 1f moves along with the first item */
        public Builder parallax(@FloatRange(from = 0f, to = 1f) float parallax) {
            this.parallax = parallax;
            return this;
        }


        public HeaderDecoration build() {
            if (headerView == null) {
                throw new IllegalStateException("View must be set with either setView or inflate");
            } else {
                return new HeaderDecoration(headerView, horizontalOrientation, parallax, column);
            }
        }

    }


    public static Builder with(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            return new Builder(recyclerView.getContext(), manager.getOrientation() == LinearLayoutManager.HORIZONTAL);
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            return new Builder(recyclerView.getContext(), manager.getSpanCount());
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            return new Builder(recyclerView.getContext(),  manager.getSpanCount());
        } else {
            return new Builder(recyclerView.getContext());
        }
    }

}
