package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Size;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SizeDAO {

    // Lấy danh sách kích thước theo productId
    public ArrayList<Size> findBySize(int productId) {
        ArrayList<Size> sizes = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Size WHERE productid = ?;");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idDB = resultSet.getInt("id");
                String sizename = resultSet.getString("sizename");
                int quantity = resultSet.getInt("quantity");
                int productIDDB = resultSet.getInt("productid");

                sizes.add(new Size(idDB, sizename, quantity, productIDDB));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return sizes;
    }

    // Thêm một kích thước mới
    public void createSize(Size size) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Size (sizename, quantity, productid) VALUES (?, ?, ?)");
            preparedStatement.setString(1, size.getSizename());
            preparedStatement.setInt(2, size.getQuantity());
            preparedStatement.setInt(3, size.getProductId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }
    public void updateSizeQuantity(int id, int quantity) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Size SET quantity = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }
    public Size findById(int id) {
        Size size = null;
        String query = "SELECT * FROM Size WHERE id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    size = new Size(
                            rs.getInt("id"),
                            rs.getString("sizename"),
                            rs.getInt("quantity"),
                            rs.getInt("productid")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }
    public List<Size> getAllSizes(int productId) {
        List<Size> sizes = new ArrayList<>();
        String query = "SELECT * FROM Size WHERE productId = ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);  // Thiết lập giá trị cho tham số productId
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size size = new Size(rs.getInt("id"), rs.getString("sizename"));
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }
}
