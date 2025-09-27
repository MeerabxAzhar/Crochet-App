package com.example.crochet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CrochetItem> itemList;
    private ArrayList<CrochetItem> cartList = new ArrayList<>();  // Cart List

    public CustomAdapter(Context context, ArrayList<CrochetItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.crochet_items_layout, parent, false);
        }

        // Get the current item
        CrochetItem currentItem = (CrochetItem) getItem(position);

        // Set image, name, price, and description
        ImageView crochetImage = convertView.findViewById(R.id.crochetImage);
        TextView crochetName = convertView.findViewById(R.id.crochetName);
        TextView crochetPrice = convertView.findViewById(R.id.crochetPrice);
        TextView crochetDescription = convertView.findViewById(R.id.crochetDescription);

        crochetImage.setImageResource(currentItem.getImage());
        crochetName.setText(currentItem.getName());
        crochetPrice.setText(currentItem.getPrice());
        crochetDescription.setText(currentItem.getDescription());

        // Set a click listener to show product details in a dialog
        convertView.setOnClickListener(v -> showProductDetailsDialog(currentItem));

        return convertView;
    }

    // Method to show the product details in a dialog
    private void showProductDetailsDialog(CrochetItem item) {
        // Inflate the custom dialog layout
        View dialogView = LayoutInflater.from(context).inflate(R.layout.product_details_dialog, null);

        // Get the views from dialog layout
        ImageView productImage = dialogView.findViewById(R.id.dialogProductImage);
        TextView productName = dialogView.findViewById(R.id.dialogProductName);
        TextView productPrice = dialogView.findViewById(R.id.dialogProductPrice);
        TextView productDescription = dialogView.findViewById(R.id.dialogProductDescription);
        TextView addToCartButton = dialogView.findViewById(R.id.addToCartButton);

        // Set data to dialog views
        productImage.setImageResource(item.getImage());
        productName.setText(item.getName());
        productPrice.setText(item.getPrice());
        productDescription.setText(item.getDescription());

        // Create the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);

        // Add the "Add to Cart" button functionality
        addToCartButton.setOnClickListener(v -> {
            // Check for duplicates before adding to the cart
            if (!CartActivity.cartItems.contains(item)) {
                CartActivity.cartItems.add(item);
                Toast.makeText(context, item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, item.getName() + " is already in the cart", Toast.LENGTH_SHORT).show();
            }

        });

        // Show the dialog
        dialogBuilder.create().show();
    }

    // Method to get the current cart list (for future use)
    public ArrayList<CrochetItem> getCartList() {
        return cartList;
    }
}
