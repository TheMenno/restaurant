package com.example.menno_000.restaurant;

/**
 * Created by menno_000 on 9-3-2018.
 */

public class MenuItem {

    private String name;
    private String description;
    private String imageUrl;
    private Integer price;
    private String category;


    public MenuItem(String aName, String aDescription, String aImageUrl, Integer aPrice, String aCategory) {
        name = aName;
        description = aDescription;
        imageUrl = aImageUrl;
        price = aPrice;
        category = aCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategory() { return category; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
