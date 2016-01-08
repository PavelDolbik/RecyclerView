package com.pavel.dolbik.simplerecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener{

    private String[]                   data;
    private RecyclerView               recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter                    adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = getData();

        recyclerView = (RecyclerView) findViewById(R.id.list);
        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(data);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private String[] getData() {
        String[] data = new String[100];
        for (int i = 0; i < 100; i++) {
            data[i] = "item "+i;;
        }
        return data;
    }

    @Override
    public void onItemClick(Adapter.ViewHolder item, int position) {
        Toast.makeText(this, "Position "+position+" "+data[position], Toast.LENGTH_SHORT).show();
    }
}
