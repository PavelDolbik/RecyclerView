package com.pavel.dolbik.gridrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Pavel on 08.01.2016.
 */
public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SECTION_TYPE = 0;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView         mRecyclerView;
    private SparseArray<Section> mSections = new SparseArray();

    private boolean mValid = true;



    public SectionAdapter(RecyclerView recyclerView, RecyclerView.Adapter baseAdapter) {
        this.mAdapter      = baseAdapter;
        this.mRecyclerView = recyclerView;

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                mValid = mAdapter.getItemCount()>0;
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                mValid = mAdapter.getItemCount()>0;
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mValid = mAdapter.getItemCount()>0;
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                mValid = mAdapter.getItemCount()>0;
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });

        // Секция займет 4 ячейки
        final GridLayoutManager layoutManager = (GridLayoutManager) (mRecyclerView.getLayoutManager());
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (isSectionHeaderPosition(position))? layoutManager.getSpanCount() : 1 ;
            }
        });
    }



    public static class Section {
        private int    firstPosition;
        private int    sectionedPosition;
        private String title;

        public Section(int firstPosition, String title) {
            this.firstPosition = firstPosition;
            this.title = title;
        }
    }


    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public SectionViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.section_text);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
        if (typeView == SECTION_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section, parent, false);
            return new SectionViewHolder(view);
        }else{
            return mAdapter.onCreateViewHolder(parent, typeView -1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder sectionViewHolder, int position) {
        if (isSectionHeaderPosition(position)) {
            ((SectionViewHolder)sectionViewHolder).title.setText(mSections.get(position).title);
        }else{
            mAdapter.onBindViewHolder(sectionViewHolder, sectionedPositionToPosition(position));
        }

    }

    @Override
    public int getItemViewType(int position) {
        //Определяем тип
        // 0 - секция
        // 1 - список
        return isSectionHeaderPosition(position) ? SECTION_TYPE : 1;
    }


    //Определяем, на каких местах будут находиться секции
    public void setSections(List<Section> sections) {
        int offset = 0;
        for (Section section : sections) {
            section.sectionedPosition = section.firstPosition + offset;
            mSections.append(section.sectionedPosition, section);
            ++offset;
        }

        //Получили коллекцию:
        // 0  - Section 1
        // 6  - Section 2
        // 14 - Section 3
        // 17 - Section 4
        // 24 - Section 5
        notifyDataSetChanged();
    }

    public boolean isSectionHeaderPosition(int position) {
        return mSections.get(position) != null;
    }

    public int sectionedPositionToPosition(int sectionedPosition) {

        int offset = 0;
        for (int i = 0; i < mSections.size(); i++) {
            if (mSections.valueAt(i).sectionedPosition > sectionedPosition) {
                break;
            }
            --offset;
        }
        return sectionedPosition + offset;
    }

    @Override
    public int getItemCount() {
        return (mValid ? mAdapter.getItemCount() + mSections.size() : 0);
    }

}