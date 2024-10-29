package com.example.qlquanao.Service;

import java.util.ArrayList;

import com.example.qlquanao.DAO.ProductCategoryDAO;
import com.example.qlquanao.Model.ProductCategory;
import com.example.qlquanao.Model.Product;

public class ProductCategorySevice {
    private ProductCategoryDAO productCategoryDAO;

    public ProductCategorySevice() {

        this.productCategoryDAO = new ProductCategoryDAO();
    }

    public ArrayList<ProductCategory> FINDALL() {

        return this.productCategoryDAO.findAll();
    }

    public ProductCategory FINDBYID(int id) {

        return this.productCategoryDAO.findById(id);
    }
    public void CREATEPRODUCTCATEORY(ProductCategory productCategory) {
        this.productCategoryDAO.createProductCategories(productCategory);
    }
    public void UPDTEPRODUCTCATEORY(ProductCategory productCategory) {
        this.productCategoryDAO.updateProductCategories(productCategory);
    }
}
