package com.example.crochet_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MufflerActivity extends AppCompatActivity {

    private ListView mufflerListView;
    private ArrayList<CrochetItem> mufflerList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muffler);

        mufflerListView = findViewById(R.id.mufflerListView);
        mufflerList = new ArrayList<>();

        // Add sample data
        mufflerList.add(new CrochetItem(R.drawable.muffler_1, "Warm Muffler", "$20", "Soft and warm crochet muffler"));
        mufflerList.add(new CrochetItem(R.drawable.muffler_2, "Patterned Muffler", "$25", "Beautifully patterned crochet muffler"));
        mufflerList.add(new CrochetItem(R.drawable.muffler_3, "Gray Muffler", "$30", "Soft crochet muffler"));
        mufflerList.add(new CrochetItem(R.drawable.muffler_4, "Floral Muffler", "$30", "Beautiful floral patterned crochet muffler"));
        mufflerList.add(new CrochetItem(R.drawable.muffler_5, "Decent Muffler", "$25", "elegant colored crochet muffler"));

        // Initialize adapter and set to ListView
        adapter = new CustomAdapter(this, mufflerList);
        mufflerListView.setAdapter(adapter);


    }
}
