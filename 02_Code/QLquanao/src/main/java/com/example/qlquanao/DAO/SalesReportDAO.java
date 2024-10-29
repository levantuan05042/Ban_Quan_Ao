package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Cart;
import com.example.qlquanao.Model.SalesReport;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesReportDAO {
    public ArrayList<SalesReport> findAll() {
        ArrayList<SalesReport> salesReports = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                    "    SalesReport.id,\n" +
                    "    Product.productname AS product_name,\n" +
                    "    SalesReport.quantitySold,\n" +
                    "    SalesReport.unitPrice,\n" +
                    "    SalesReport.totalSales,\n" +
                    "    SalesReport.reportDate\n" +
                    "FROM\n" +
                    "    SalesReport\n" +
                    "    JOIN Product ON SalesReport.productId = Product.id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                int quantity = Integer.parseInt(resultSet.getString("quantitySold"));
                BigDecimal price = resultSet.getBigDecimal("unitPrice");
                BigDecimal total = resultSet.getBigDecimal("totalSales");
                LocalDateTime addeddate = resultSet.getTimestamp("reportDate").toLocalDateTime();
                salesReports.add(new SalesReport(id, productName, quantity, price, total,addeddate));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return salesReports;
    }
}
