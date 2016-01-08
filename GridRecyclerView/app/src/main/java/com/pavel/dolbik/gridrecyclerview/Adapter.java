package com.pavel.dolbik.gridrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 08.01.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


   public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView title;

       public ViewHolder(View itemView) {
           super(itemView);
           title = (TextView) itemView.findViewById(R.id.title);
       }
   }


    private final List<Integer> mItems;

    public Adapter() {
        mItems = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mItems.add(i, i);
        }
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.title.setText(mItems.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
