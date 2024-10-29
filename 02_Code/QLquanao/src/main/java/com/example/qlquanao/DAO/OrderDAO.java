package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDAO {
    public ArrayList<Order> findAll() {
        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT \n" +
                    "    o.id AS OrderID, \n" +
                    "    a.username AS Username, \n" +
                    "    a.fullname AS AccountName, \n" +
                    "    p.productname AS ProductName, \n" +
                    "    s.sizename AS SizeName, \n" +
                    "    o.quantity, \n" +
                    "    o.price, \n" +
                    "    o.total, \n" +
                    "    DATE_FORMAT(o.orderdate, '%Y-%m-%d %H:%i:%s') AS OrderDate, -- Định dạng ngày giờ\n" +
                    "    o.status, \n" +
                    "    pr.provincename AS ProvinceName, \n" +
                    "    d.districtname AS DistrictName, \n" +
                    "    c.communename AS CommuneName, \n" +
                    "    o.address, \n" +
                    "    o.phonenumber, \n" +
                    "    o.notes\n" +
                    "FROM \n" +
                    "    `Order` o\n" +
                    "JOIN \n" +
                    "    Account a ON o.accountId = a.id\n" +
                    "JOIN \n" +
                    "    Product p ON o.productId = p.id\n" +
                    "JOIN \n" +
                    "    Size s ON o.sizeId = s.id\n" +
                    "LEFT JOIN \n" +
                    "    Province pr ON o.province = pr.id\n" +
                    "LEFT JOIN \n" +
                    "    District d ON o.district = d.id\n" +
                    "LEFT JOIN \n" +
                    "    Commune c ON o.commune = c.id;\n");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("OrderID");
                String Username = resultSet.getString("Username");
                String accountName = resultSet.getString("AccountName");
                LocalDateTime orderdate = resultSet.getTimestamp("OrderDate").toLocalDateTime();
                String productName = resultSet.getString("ProductName");
                String sizeName = resultSet.getString("SizeName");
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                String status = resultSet.getString("status");
                String province = resultSet.getString("ProvinceName");
                String district = resultSet.getString("DistrictName");
                String commune = resultSet.getString("CommuneName");
                String address = resultSet.getString("address");
                String phonenumber = resultSet.getString("phonenumber");
                String notes = resultSet.getString("notes");
                orders.add(new Order(id,Username, accountName,orderdate,productName, sizeName, quantity, price, total,status,province, district, commune,address,phonenumber,notes));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return orders;
    }
    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE `Order` SET status = ? WHERE id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật trạng thái thành công.");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }
    public ArrayList<Order> findOrdersByAccountId(String username) {
        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Sử dụng câu lệnh SQL mới
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT \n" +
                            "    o.id AS OrderID, \n" +
                            "    a.username AS Username, \n" +
                            "    a.fullname AS AccountName, \n" +
                            "    p.productname AS ProductName, \n" +
                            "    p.productimage AS productImage, \n" +
                            "    s.sizename AS SizeName, \n" +
                            "    o.quantity, \n" +
                            "    o.price, \n" +
                            "    o.total, \n" +
                            "    DATE_FORMAT(o.orderdate, '%Y-%m-%d %H:%i:%s') AS OrderDate, -- Định dạng ngày giờ\n" +
                            "    o.status, \n" +
                            "    pr.provincename AS ProvinceName, \n" +
                            "    d.districtname AS DistrictName, \n" +
                            "    c.communename AS CommuneName, \n" +
                            "    o.address, \n" +
                            "    o.phonenumber, \n" +
                            "    o.notes\n" +
                            "FROM \n" +
                            "    `Order` o\n" +
                            "JOIN \n" +
                            "    Account a ON o.accountId = a.id\n" +
                            "JOIN \n" +
                            "    Product p ON o.productId = p.id\n" +
                            "JOIN \n" +
                            "    Size s ON o.sizeId = s.id\n" +
                            "LEFT JOIN \n" +
                            "    Province pr ON o.province = pr.id\n" +
                            "LEFT JOIN \n" +
                            "    District d ON o.district = d.id\n" +
                            "LEFT JOIN \n" +
                            "    Commune c ON o.commune = c.id\n" +
                            "WHERE \n" +
                            "    a.username = ? \n" +
                            "    AND o.status = 'pending';\n"
            );

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("OrderID");
                String accountName = resultSet.getString("Username");
                String fullname = resultSet.getString("AccountName");
                LocalDateTime orderDate = resultSet.getTimestamp("OrderDate").toLocalDateTime();
                String productName = resultSet.getString("ProductName");
                String productImage = resultSet.getString("productImage");
                String sizeName = resultSet.getString("SizeName");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                String status = resultSet.getString("status");
                String province = resultSet.getString("ProvinceName");
                String district = resultSet.getString("DistrictName");
                String commune = resultSet.getString("CommuneName");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phonenumber");
                String notes = resultSet.getString("notes");
                orders.add(new Order(id, accountName,fullname, orderDate, productName, productImage, sizeName,
                        quantity, price, total, status, province, district, commune, address, phoneNumber, notes));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return orders;
    }
    public void insertOrder(Order order) {
        String sql = "INSERT INTO `Order` (accountId, productId, sizeId, quantity, price, province, district, commune, address, phonenumber, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getAccountId()); // ID tài khoản
            preparedStatement.setInt(2, order.getProductId()); // ID sản phẩm
            preparedStatement.setInt(3, order.getSizeId()); // ID kích cỡ
            preparedStatement.setInt(4, order.getQuantity()); // Số lượng
            preparedStatement.setBigDecimal(5, order.getPrice()); // Giá sản phẩm
            preparedStatement.setInt(6, order.getProvinceId());
            preparedStatement.setInt(7, order.getDistrictId());
            preparedStatement.setInt(8, order.getCommuneId());
            preparedStatement.setString(9, order.getAddress()); // Địa chỉ
            preparedStatement.setString(10, order.getPhonenumber()); // Số điện thoại
            preparedStatement.setString(11, order.getNotes()); // Ghi chú

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm đơn hàng thành công!");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }
    public void DELETE(int id) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from `Order` where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
    }
    public ArrayList<Order> findOrdersByAccountId2(String username) {
        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Sử dụng câu lệnh SQL mới
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT \n" +
                            "    o.id AS OrderID, \n" +
                            "    a.username AS Username, \n" +
                            "    a.fullname AS AccountName, \n" +
                            "    p.productname AS ProductName, \n" +
                            "    p.productimage AS productImage, \n" +
                            "    s.sizename AS SizeName, \n" +
                            "    o.quantity, \n" +
                            "    o.price, \n" +
                            "    o.total, \n" +
                            "    DATE_FORMAT(o.orderdate, '%Y-%m-%d %H:%i:%s') AS OrderDate, -- Định dạng ngày giờ\n" +
                            "    o.status, \n" +
                            "    pr.provincename AS ProvinceName, \n" +
                            "    d.districtname AS DistrictName, \n" +
                            "    c.communename AS CommuneName, \n" +
                            "    o.address, \n" +
                            "    o.phonenumber, \n" +
                            "    o.notes\n" +
                            "FROM \n" +
                            "    `Order` o\n" +
                            "JOIN \n" +
                            "    Account a ON o.accountId = a.id\n" +
                            "JOIN \n" +
                            "    Product p ON o.productId = p.id\n" +
                            "JOIN \n" +
                            "    Size s ON o.sizeId = s.id\n" +
                            "LEFT JOIN \n" +
                            "    Province pr ON o.province = pr.id\n" +
                            "LEFT JOIN \n" +
                            "    District d ON o.district = d.id\n" +
                            "LEFT JOIN \n" +
                            "    Commune c ON o.commune = c.id\n" +
                            "WHERE \n" +
                            "    a.username = ? \n" +
                            "    AND o.status = 'confirmed';\n"
            );

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("OrderID");
                String accountName = resultSet.getString("Username");
                String fullname = resultSet.getString("AccountName");
                LocalDateTime orderDate = resultSet.getTimestamp("OrderDate").toLocalDateTime();
                String productName = resultSet.getString("ProductName");
                String productImage = resultSet.getString("productImage");
                String sizeName = resultSet.getString("SizeName");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                String status = resultSet.getString("status");
                String province = resultSet.getString("ProvinceName");
                String district = resultSet.getString("DistrictName");
                String commune = resultSet.getString("CommuneName");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phonenumber");
                String notes = resultSet.getString("notes");
                orders.add(new Order(id, accountName,fullname, orderDate, productName, productImage, sizeName,
                        quantity, price, total, status, province, district, commune, address, phoneNumber, notes));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return orders;
    }
}
