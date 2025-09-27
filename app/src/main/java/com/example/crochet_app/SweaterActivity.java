package com.example.crochet_app;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SweaterActivity extends AppCompatActivity {

    private ListView sweaterListView;
    private ArrayList<CrochetItem> sweaterList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweater);

        sweaterListView = findViewById(R.id.sweaterListView);
        sweaterList = new ArrayList<>();

        // Add sample data
        sweaterList.add(new CrochetItem(R.drawable.sweater_1, "Winter Sweater", "$50", "Cozy and warm crochet sweater"));
        sweaterList.add(new CrochetItem(R.drawable.sweater_2, "Stylish Sweater", "$40", "Hand-made stylish crochet sweater"));
        sweaterList.add(new CrochetItem(R.drawable.sweater_3, "Black and Grey Sweater", "$50", "Cozy and warm crochet sweater"));
        sweaterList.add(new CrochetItem(R.drawable.sweater_4, "Brown and cream Sweater", "$50", "Cozy and warm crochet sweater"));
        sweaterList.add(new CrochetItem(R.drawable.sweater_5, "Elegant Sweater", "$45", "Hand-made stylish crochet sweater"));

        // Initialize adapter and set to ListView
        adapter = new CustomAdapter(this, sweaterList);
        sweaterListView.setAdapter(adapter);
    }
}
