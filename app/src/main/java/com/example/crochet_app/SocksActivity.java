package com.example.crochet_app;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SocksActivity extends AppCompatActivity {

    private ListView socksListView;
    private ArrayList<CrochetItem> socksList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socks);

        socksListView = findViewById(R.id.socksListView);
        socksList = new ArrayList<>();

        // Add sample data
        socksList.add(new CrochetItem(R.drawable.socks_1, "Cozy Socks", "$15", "Comfortable crochet socks for cold weather"));
        socksList.add(new CrochetItem(R.drawable.socks_2, "Striped Socks", "$18", "Vibrant striped crochet socks"));
        socksList.add(new CrochetItem(R.drawable.socks_3, "Neon Socks", "$16", "Comfortable neon crochet socks for cold weather"));
        socksList.add(new CrochetItem(R.drawable.socks_4, "6 pack", "$18", "6 pack comfortable multi colored crochet socks"));
        socksList.add(new CrochetItem(R.drawable.socks_5, "Patterend Socks", "$20", "Patterend crochet socks"));

        // Initialize adapter and set to ListView
        adapter = new CustomAdapter(this, socksList);
        socksListView.setAdapter(adapter);
    }
}
