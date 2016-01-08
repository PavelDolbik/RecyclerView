package com.pavel.dolbik.simplerecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pavel on 08.01.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ViewHolder item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter  adapter;
        private TextView textView;

        public ViewHolder(View itemView, Adapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.adapter  = adapter;
            this.textView = (TextView) itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            final OnItemClickListener listener = adapter.getOnItemClickListener();
            if(listener != null){
                listener.onItemClick(this, getAdapterPosition());
            }

        }
    }


    private String[] data;

    public Adapter(String[] data) {
        this.data = data;
    }


    @Override
    // Создает новые views (вызывается layout manager-ом)
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view, this);
    }


    @Override
    // Заменяет контент отдельного view (вызывается layout manager-ом)
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.textView.setText(data[position]);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }
}
