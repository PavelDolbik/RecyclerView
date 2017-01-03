package com.pavel.dolbik.simplerecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] data;
    private OnItemClickListener onItemClickListener;



    interface OnItemClickListener {
        void onItemClick(String item);
    }

    void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }




    static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }



    Adapter(String[] data) {
        this.data = data;
    }


    @Override
    // Создает новые views (вызывается layout manager-ом)
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        final RecyclerView.ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(data[adapterPosition]);
                }
            }
        });
        return (ViewHolder) holder;
    }


    @Override
    // Заменяет контент отдельного view (вызывается layout manager-ом)
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.textView.setText(data[holder.getAdapterPosition()]);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }
}
