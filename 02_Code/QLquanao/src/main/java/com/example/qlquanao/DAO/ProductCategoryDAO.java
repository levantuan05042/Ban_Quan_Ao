package com.example.qlquanao.DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.qlquanao.Model.ProductCategory;
import com.example.qlquanao.Model.Product;

public class ProductCategoryDAO {
    public ProductCategoryDAO() {}

    public ArrayList<ProductCategory> findAll() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ProductCategory;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String categoryname = resultSet.getString("categoryname");
                productCategories.add(new ProductCategory(id, categoryname));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
        return productCategories;
    }

    public ProductCategory findById(int id) {
        ProductCategory productCategories = null;
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ProductCategory where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idDB = resultSet.getInt("id");
                String categoryname = resultSet.getString("categoryname");
                productCategories = new ProductCategory(idDB, categoryname);
            }
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
        return productCategories;
    }
    public void createProductCategories(ProductCategory productCategory) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ProductCategory (categoryname) value (?);");
            preparedStatement.setString(1, productCategory.getCategoryname());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
    }
    public void updateProductCategories(ProductCategory productCategory) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update ProductCategory set categoryname = ? where id = ?;");
            preparedStatement.setString(1, productCategory.getCategoryname());
            preparedStatement.setInt(2, productCategory.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
    }

}
