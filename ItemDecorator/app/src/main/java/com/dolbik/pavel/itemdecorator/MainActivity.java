package com.dolbik.pavel.itemdecorator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CheckBox             top;
    private CheckBox             middle;
    private CheckBox             bottom;
    private CheckBox             header;
    private RecyclerView         list;
    private Adapter              adapter;
    private CustomItemDecoration decoration;
    private HeaderDecoration     headerDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top    = (CheckBox)     findViewById(R.id.checkBox1);
        middle = (CheckBox)     findViewById(R.id.checkBox2);
        bottom = (CheckBox)     findViewById(R.id.checkBox3);
        header = (CheckBox)     findViewById(R.id.checkBox4);
        top.setOnClickListener(this);
        middle.setOnClickListener(this);
        bottom.setOnClickListener(this);
        header.setOnClickListener(this);


        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++ ) { data.add("Item "+i); }

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setHasFixedSize(true);
        adapter = new Adapter(data);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBox1:
            case R.id.checkBox2:
            case R.id.checkBox3:
            case R.id.checkBox4:
                checkSelection();
                break;
        }
    }


    private void checkSelection() {
        int startOffset;
        int endOffset;
        int divider;

        if (top.isChecked()) {
            startOffset = getResources().getDimensionPixelOffset(R.dimen.start_offset);
        } else {
            startOffset = 0;
        }


        if (bottom.isChecked()) {
            endOffset = getResources().getDimensionPixelOffset(R.dimen.end_offset);
        } else {
            endOffset = 0;
        }


        if (middle.isChecked()) {
            divider = R.drawable.divider;
        } else {
            divider = R.drawable.divider_empty;
        }


        if (header.isChecked()) {
            if (headerDecoration != null) {
                list.removeItemDecoration(headerDecoration);
            }
            headerDecoration = HeaderDecoration
                    .with(list)
                    .inflate(R.layout.header_view)
                    .parallax(0.2f)
                    .build();
            list.addItemDecoration(headerDecoration);
        } else {
            if (headerDecoration != null) {
                list.removeItemDecoration(headerDecoration);
            }
        }


        if (startOffset == 0 && endOffset == 0 && divider == R.drawable.divider_empty) {
            if (decoration != null) {
                list.removeItemDecoration(decoration);
                decoration = null;
            }
        } else {
            if (decoration != null) {
                list.removeItemDecoration(decoration);
            }
            decoration = new CustomItemDecoration(this, divider, startOffset, endOffset);
            list.addItemDecoration(decoration);
        }
    }
}
