package com.example.qlquanao.Model;

public class Size {
    private int id;
    private String sizename; // Tên kích cỡ
    private int quantity; // Số lượng
    private int productId; // Mã sản phẩm (khóa ngoại)

    // Constructors
    public Size() {}

    public Size(int id, String sizename) {
        this.id = id;
        this.sizename = sizename;
    }

    public Size(int id, String sizename, int quantity, int productId) {
        this.id = id;
        this.sizename = sizename;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Size(String sizename, int quantity, int productId) {
        this.sizename = sizename;
        this.quantity = quantity;
        this.productId = productId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizename() {
        return sizename;
    }

    public void setSizename(String sizename) {
        this.sizename = sizename;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

