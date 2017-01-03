package com.pavel.dolbik.simplerecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        Adapter adapter = new Adapter(getData());
        adapter.setOnItemClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    private String[] getData() {
        String[] data = new String[100];
        for (int i = 0; i < 100; i++) {
            data[i] = "item "+i;
        }
        return data;
    }


    @Override
    public void onItemClick(String item) {
        Toast.makeText(this, "Item - "+item, Toast.LENGTH_SHORT).show();
    }
}
