package com.example.qlquanao.Service;


import com.example.qlquanao.DAO.ProductDAO;
import com.example.qlquanao.Model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public ArrayList<Product> FINDALL() {
        return this.productDAO.findAll();
    }

    public ArrayList<Product> FINDBYPRODUCTCATEGORY(int categoryid) {
        return this.productDAO.findByProductCategory(categoryid);
    }

    public void CREATEPRODUCT(Product product) {
        this.productDAO.createProduct(product);
    }

    public Product findByProductName(String productName) {
        return productDAO.findByProductName(productName);

    }
    public Product findById(int id) {
        return productDAO.findById(id);
    }
        public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
    public ArrayList<Product> bestseller() {
        return this.productDAO.bestseller();
    }
    public ArrayList<Product> lastest() {
        return this.productDAO.latest();
    }
    public ArrayList<Product> SEARCHBYNAME2(String name) {
        return this.productDAO.SEARCHBYNAME(name);
    }
    public ArrayList<Product> SEARCHBYPRICE(BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productDAO.SEARCHBYPRICE(minPrice,maxPrice);
    }
    public ArrayList<Product> price_increase() {

        return this.productDAO.price_increase();
    }
    public ArrayList<Product> price_reduction() {

        return this.productDAO.price_reduction();
    }
    public ArrayList<Product> ProductDetailById(int id) {
        return this.productDAO.ProductDetailById(id);
    }

}
