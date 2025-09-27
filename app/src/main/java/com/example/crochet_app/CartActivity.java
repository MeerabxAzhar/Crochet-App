package com.example.crochet_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private CustomCartAdapter cartAdapter;
    private Button checkoutButton;
    private TextView totalTextView;

    // Singleton cart list to avoid multiple instances
    public static ArrayList<CrochetItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        checkoutButton = findViewById(R.id.checkoutButton);
        totalTextView = findViewById(R.id.totalTextView); // Assuming there's a TextView in your layout for the total price

        // Initialize adapter and bind to ListView
        cartAdapter = new CustomCartAdapter(this, cartItems);
        cartListView.setAdapter(cartAdapter);

        // Update the total when the cart is modified
        updateTotal();

        // Handle checkout button click
        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(CartActivity.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // Calculate the total price
                int totalPrice = 0;
                for (CrochetItem item : cartItems) {
                    String priceString = item.getPrice();
                    priceString = priceString.replace("$", "").trim(); // Remove $ sign
                    totalPrice += Integer.parseInt(priceString);
                }

                // Pass total to CheckoutActivity
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }

    // Method to add item to cart
    public static void addToCart(CrochetItem item) {
        if (!cartItems.contains(item)) {
            cartItems.add(item);
        }
    }

    // Method to calculate and update the total price
    void updateTotal() {
        int total = 0;

        // Loop through each cart item to calculate the total
        for (CrochetItem item : cartItems) {
            // Get the price, remove the "$" symbol, and parse the number
            String priceString = item.getPrice();
            priceString = priceString.replace("$", "").trim(); // Remove $ and trim spaces

            try {
                int price = Integer.parseInt(priceString); // Now it should work
                total += price;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Set the total price to the TextView (assuming you have a TextView for total)
        totalTextView.setText("Total: $" + total);
    }

}
