package com.example.crochet_app;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class YarnActivity extends AppCompatActivity {

    private ListView yarnListView;
    private ArrayList<CrochetItem> yarnList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yarn);

        yarnListView = findViewById(R.id.yarnListView);
        yarnList = new ArrayList<>();

        // Add sample data
        yarnList.add(new CrochetItem(R.drawable.yarn_2, "Soft Wool", "$10", "High-quality soft wool yarn"));
        yarnList.add(new CrochetItem(R.drawable.yarn_1, "Colorful Yarn", "$8", "Perfect for vibrant designs"));
        yarnList.add(new CrochetItem(R.drawable.yarn_3, "Soft Wool", "$10", "High-quality soft wool yarn"));
        yarnList.add(new CrochetItem(R.drawable.yarn_4, "Multishade Yarn", "$11", "Perfect for vibrant designs"));
        yarnList.add(new CrochetItem(R.drawable.yarn_5, "Colorful Yarn", "$10", "Perfect for vibrant designs"));

        // Initialize adapter and set to ListView
        adapter = new CustomAdapter(this, yarnList);
        yarnListView.setAdapter(adapter);
    }
}
