package com.example.qlquanao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

    private int id;
    private String productname;
    private BigDecimal price;
    private ProductCategory productCategory;
    private Supplier supplier;
    private String supplierName;
    private String productimage;
    private String description;
    private LocalDateTime entrydate;
    private int totalQuantitySold;
    private String sizeName;
    private int quantity;
    private Size sizes;


    public Product(int id, String productname, BigDecimal price, ProductCategory productCategory, Supplier supplier, String productimage, String description, LocalDateTime entrydate, int totalQuantitySold) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.productimage = productimage;
        this.description = description;
        this.entrydate = entrydate;
        this.totalQuantitySold = totalQuantitySold;
    }
    public Product(int id, String productname, BigDecimal price, ProductCategory productCategory, Supplier supplier, String productimage, String description, LocalDateTime entrydate) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.productimage = productimage;
        this.description = description;
        this.entrydate = entrydate;
    }

    public Product(int id, String productname, BigDecimal price,  String productimage, String description, String sizeName, int quantity) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.productimage = productimage;
        this.description = description;
        this.sizeName = sizeName;
        this.quantity = quantity;
    }

    public Product(int id, String productname, BigDecimal price, ProductCategory productCategory, String productimage, String description, LocalDateTime entrydate) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.productCategory = productCategory;
        this.productimage = productimage;
        this.description = description;
        this.entrydate = entrydate;
    }

    public Product(String productname, BigDecimal price, ProductCategory productCategory, String productimage, String description) {
        this.productname = productname;
        this.price = price;
        this.productCategory = productCategory;
        this.productimage = productimage;
        this.description = description;
    }

    public Product(String productname, BigDecimal price, String productimage, int totalQuantitySold) {
        this.productname = productname;
        this.price = price;
        this.productimage = productimage;
        this.totalQuantitySold = totalQuantitySold;
    }

    public Product(String productname, BigDecimal price, ProductCategory productCategory, Supplier supplier, String productimage, String description) {
        this.productname = productname;
        this.price = price;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.productimage = productimage;
        this.description = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getPrice() {
        return price; // Trả về giá trị BigDecimal
    }

    public void setPrice(BigDecimal price) {
        this.price = price; // Thiết lập giá trị BigDecimal
    }
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(LocalDateTime entrydate) {
        this.entrydate = entrydate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
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

    public Size getSizes() {
        return sizes;
    }

    public void setSizes(Size sizes) {
        this.sizes = sizes;
    }

}
