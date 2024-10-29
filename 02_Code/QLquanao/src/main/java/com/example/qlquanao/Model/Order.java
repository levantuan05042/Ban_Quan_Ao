package com.example.qlquanao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private String userName;
    private String accountName;
    private LocalDateTime orderdate;
    private String productName;
    private String productImage;
    private String sizeName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
    private String status;
    private String provinceName;
    private String districtName;
    private String communeName;
    private String address;
    private String phonenumber;
    private String notes;
    private int accountId;  // ID tài khoản
    private int productId;  // ID sản phẩm
    private int sizeId;     // ID kích thước
    private int provinceId;
    private int districtId;
    private int communeId;



    public Order(int accountId, int provinceId, int districtId, int communeId, String address,
                 String phoneNumber, int productId, int sizeId, String productImage,
                 int quantity, BigDecimal price) {
        this.accountId = accountId;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.communeId = communeId;
        this.address = address;
        this.phonenumber = phoneNumber;
        this.productId = productId;
        this.sizeId = sizeId;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
    }
    public Order(int id, String userName, String accountName, LocalDateTime orderdate, String productName, String sizeName, int quantity, BigDecimal price, BigDecimal total,
    String status, String provinceName, String districtName, String communeName,String address, String phonenumber, String notes) {
        this.id = id;
        this.userName = userName;
        this.accountName = accountName;
        this.orderdate = orderdate;
        this.productName = productName;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.status = status;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.communeName = communeName;
        this.address = address;
        this.phonenumber = phonenumber;
        this.notes = notes;
    }
    public Order(int provinceId, int districtId, int communeId, String address, String phoneNumber, int productId, int sizeId, String productImage, int quantity, BigDecimal price, String username) {
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.communeId = communeId;
        this.address = address;
        this.phonenumber = phoneNumber;
        this.productId = productId;
        this.sizeId = sizeId;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
        this.userName = username; // khởi tạo
    }

    public Order(int id, String userName,String accountName, LocalDateTime orderdate, String productName, String productImage, String sizeName, int quantity, BigDecimal price, BigDecimal total, String status, String provinceName, String districtName, String communeName, String address, String phonenumber, String notes) {
        this.id = id;
        this.userName = userName;
        this.accountName = accountName;
        this.orderdate = orderdate;
        this.productName = productName;
        this.productImage = productImage;
        this.sizeName = sizeName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.status = status;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.communeName = communeName;
        this.address = address;
        this.phonenumber = phonenumber;
        this.notes = notes;
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

    public LocalDateTime getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDateTime orderdate) {
        this.orderdate = orderdate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }
}
