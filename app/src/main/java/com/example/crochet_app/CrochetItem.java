package com.example.crochet_app;

public class CrochetItem {
    private int image;
    private String name;
    private String price;
    private String description;

    public CrochetItem(int image, String name, String price, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getImage() { return image; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }
}
