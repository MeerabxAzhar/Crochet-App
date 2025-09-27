package com.example.crochet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomCartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CrochetItem> cartItems;

    public CustomCartAdapter(Context context, ArrayList<CrochetItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        }

        // Get current cart item
        CrochetItem currentItem = cartItems.get(position);

        // Set views
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        ImageView itemImage = convertView.findViewById(R.id.itemImage);
        ImageView removeButton = convertView.findViewById(R.id.removeButton);

        itemName.setText(currentItem.getName());
        itemPrice.setText(currentItem.getPrice());
        itemImage.setImageResource(currentItem.getImage());

        // Remove item from cart and notify adapter
        removeButton.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyDataSetChanged();

            // After item removal, update the total in CartActivity
            ((CartActivity) context).updateTotal();
        });

        return convertView;
    }
}
