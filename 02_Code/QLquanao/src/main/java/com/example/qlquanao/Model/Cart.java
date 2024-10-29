package com.example.qlquanao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cart {
    private int id;
    private int accountId;
    private String accountName;
    private String fullName;
    private String productName;
    private String productimage;
    private String sizeName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
    private LocalDateTime addeddate;
    private int productId;
    private int sizeId;

    public Cart(int id, String accountName, String fullName, String productName,  String sizeName, String productimage, int quantity, BigDecimal price, BigDecimal total, LocalDateTime addeddate) {
        this.id = id;
        this.accountName = accountName;
        this.fullName = fullName;
        this.productName = productName;
        this.sizeName = sizeName;
        this.productimage = productimage;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.addeddate = addeddate;
    }

    public Cart(int accountId, int productId, int sizeId,int quantity, BigDecimal price) {
        this.accountId=accountId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.price = price;

    }

    public Cart(int id, String accountName, String productName, String productimage, String sizeName, int quantity, BigDecimal price, BigDecimal total, LocalDateTime addeddate) {
        this.id = id;
        this.accountName = accountName;
        this.productName = productName;
        this.productimage = productimage;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.addeddate = addeddate;
    }
    public Cart(int id, String accountName, String fullName, int productId ,String productName,  int sizeId , String sizeName, String productimage, int quantity, BigDecimal price, BigDecimal total, LocalDateTime addeddate) {
        this.id = id;
        this.accountName = accountName;
        this.fullName = fullName;
        this.productId=productId;
        this.productName = productName;
        this.sizeId=sizeId;
        this.sizeName = sizeName;
        this.productimage = productimage;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.addeddate = addeddate;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(LocalDateTime addeddate) {
        this.addeddate = addeddate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
