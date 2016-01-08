package com.pavel.dolbik.gridrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView   recyclerView;
    private Adapter        adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new Adapter();

        List<SectionAdapter.Section> sections = new ArrayList<>();
        sections.add(new SectionAdapter.Section(0,"Section 1"));
        sections.add(new SectionAdapter.Section(5,"Section 2"));
        sections.add(new SectionAdapter.Section(12,"Section 3"));
        sections.add(new SectionAdapter.Section(14,"Section 4"));
        sections.add(new SectionAdapter.Section(20,"Section 5"));

        SectionAdapter mSectionedAdapter = new SectionAdapter(recyclerView, adapter);
        mSectionedAdapter.setSections(sections);

        recyclerView.setAdapter(mSectionedAdapter);
    }
}
