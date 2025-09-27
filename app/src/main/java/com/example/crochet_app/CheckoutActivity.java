package com.example.crochet_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private EditText nameEditText, addressEditText, phoneEditText;
    private TextView totalPriceTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        submitButton = findViewById(R.id.submitButton);

        // Get the total price passed from CartActivity
        Intent intent = getIntent();
        int totalPrice = intent.getIntExtra("totalPrice", 0); // Default to 0 if not passed
        totalPriceTextView.setText("Total Price: $" + totalPrice);

        // Handle form submission
        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(CheckoutActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Process the order (for now, just show a message)
                Toast.makeText(CheckoutActivity.this, "Order Submitted! Total: $" + totalPrice, Toast.LENGTH_SHORT).show();
                // Optionally, you can clear the cart here after submission
                CartActivity.cartItems.clear();
                finish(); // Close the CheckoutActivity and return to CartActivity
            }
        });
    }
}
