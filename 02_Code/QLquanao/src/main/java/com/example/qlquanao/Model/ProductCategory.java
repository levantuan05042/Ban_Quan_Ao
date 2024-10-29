package com.example.qlquanao.Model;

public class ProductCategory {
    private int id;
    private String categoryname;

    public ProductCategory() {
    }

    public ProductCategory(int id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;
    }

    public ProductCategory(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
