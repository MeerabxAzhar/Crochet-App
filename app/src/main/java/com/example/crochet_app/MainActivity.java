package com.example.crochet_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    // Buttons for navigation
    Button btnYarn, btnSweaters, btnMufflers, btnSocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnYarn = findViewById(R.id.btnYarn);
        btnSweaters = findViewById(R.id.btnSweaters);
        btnMufflers = findViewById(R.id.btnMufflers);
        btnSocks = findViewById(R.id.btnSocks);
        ImageView emailIcon = findViewById(R.id.emailIcon);
        ImageView phoneIcon = findViewById(R.id.phoneIcon);
        ImageView cartIcon = findViewById(R.id.cartIcon);

        // Email icon functionality
        emailIcon.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:cmanager@crochetapp.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry from Crochet App User");
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        });
        ImageView musicIcon = findViewById(R.id.musicIcon);

        // Open the MusicFragment when the music icon is clicked
        musicIcon.setOnClickListener(v -> {
            MusicFragment myFragment = new MusicFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, myFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        // Phone icon functionality
        phoneIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SendSmsActivity.class);
            startActivity(intent);
        });

        // Navigation buttons
        btnYarn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, YarnActivity.class);
            startActivity(intent);
        });

        btnSweaters.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SweaterActivity.class);
            startActivity(intent);
        });

        btnMufflers.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MufflerActivity.class);
            startActivity(intent);
        });

        btnSocks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SocksActivity.class);
            startActivity(intent);
        });


        cartIcon.setOnClickListener(v -> {
            // Open the CartActivity when the cart icon is clicked
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

    }
}
