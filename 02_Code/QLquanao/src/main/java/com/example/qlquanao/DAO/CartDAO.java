package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Cart;
import com.example.qlquanao.Model.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CartDAO {
    public ArrayList<Cart> findAll() {
        ArrayList<Cart> carts = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                    "    Cart.id,\n" +
                    "    Account.username AS account_name,\n" +
                    "    Account.fullname AS full_name,\n" +
                    "    Product.productname AS product_name,\n" +
                    "    Size.sizename AS size_name,\n" +
                    "    Product.productimage,\n" +
                    "    Cart.quantity,\n" +
                    "    Cart.price,\n" +
                    "    Cart.total,\n" +
                    "    Cart.addeddate\n" +
                    "FROM\n" +
                    "    Cart\n" +
                    "    JOIN Account ON Cart.accountId = Account.id\n" +
                    "    JOIN Product ON Cart.productId = Product.id\n" +
                    "    JOIN Size ON Cart.sizeId = Size.id;\n");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String accountName = resultSet.getString("account_name");
                String fullName = resultSet.getString("full_name");
                String productName = resultSet.getString("product_name");
                String sizeName = resultSet.getString("size_name");
                String productImage = resultSet.getString("productimage");
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                LocalDateTime addeddate = resultSet.getTimestamp("addeddate").toLocalDateTime();
                carts.add(new Cart(id, accountName, fullName, productName, sizeName, productImage, quantity, price, total, addeddate));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return carts;
    }

    public ArrayList<Cart> findCartsByUsername(String username) {
        ArrayList<Cart> carts = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "    cart.id, " +
                            "    account.username, " +
                            "    product.productname AS productname, " +
                            "    product.productimage AS productimage, " +
                            "    size.sizename AS sizeName, " +
                            "    cart.quantity, " +
                            "    cart.price, " +
                            "    cart.total, " +
                            "    cart.addeddate " +
                            "FROM " +
                            "    Cart cart " +
                            "JOIN " +
                            "    Product product ON cart.productId = product.id " +
                            "LEFT JOIN " +
                            "    Size size ON cart.sizeId = size.id " +
                            "JOIN " +
                            "    Account account ON cart.accountId = account.id " +
                            "WHERE " +
                            "    account.username = ?;"
            );
            preparedStatement.setString(1, username);  // Gán giá trị username

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String accountName = resultSet.getString("username");
                String productName = resultSet.getString("productname");
                String productimage = resultSet.getString("productimage");
                String sizeName = resultSet.getString("sizeName");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                LocalDateTime addeddate = resultSet.getTimestamp("addeddate").toLocalDateTime();
                carts.add(new Cart(id, accountName, productName, productimage, sizeName, quantity, price, total, addeddate));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return carts;
    }

    public Cart findCartById(int id) {
        Cart cart = null;
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "    Cart.id, " +

                            "    Account.username AS account_name, " +
                            "    Account.fullname AS full_name, " +
                            "    Cart.productId, " +
                            "    Product.productname AS product_name, " +
                            "    Cart.sizeId, " +
                            "    Size.sizename AS size_name, " +
                            "    Product.productimage, " +
                            "    Cart.quantity, " +
                            "    Cart.price, " +
                            "    Cart.total, " +
                            "    Cart.addeddate " +
                            "FROM " +
                            "    Cart " +
                            "JOIN Account ON Cart.accountId = Account.id " +
                            "JOIN Product ON Cart.productId = Product.id " +
                            "JOIN Size ON Cart.sizeId = Size.id " +
                            "WHERE Cart.id = ?;"
            );
            preparedStatement.setInt(1, id); // Gán giá trị ID vào truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String accountName = resultSet.getString("account_name");
                String fullName = resultSet.getString("full_name");
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("product_name");
                int sizeId = resultSet.getInt("sizeId");
                String sizeName = resultSet.getString("size_name");
                String productImage = resultSet.getString("productimage");
                int quantity = resultSet.getInt("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total = resultSet.getBigDecimal("total");
                LocalDateTime addedDate = resultSet.getTimestamp("addeddate").toLocalDateTime();
                cart = new Cart(id, accountName, fullName, productId, productName, sizeId, sizeName, productImage, quantity, price, total, addedDate);
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return cart; // Trả về đối tượng Cart tìm thấy hoặc null nếu không tìm thấy
    }
    public void DELETE(int id) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Cart where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
    }
    private boolean accountExists(int accountId) {
        String sql = "SELECT COUNT(*) FROM Account WHERE id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tài khoản tồn tại
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return false; // Nếu có lỗi hoặc không tìm thấy tài khoản
    }

    public void insertCart(Cart cart) {
        // Kiểm tra tài khoản có tồn tại không
        if (!accountExists(cart.getAccountId())) {
            System.out.println("Tài khoản không tồn tại: " + cart.getAccountId());
            return; // Ngưng thực hiện nếu tài khoản không tồn tại
        }

        String sql = "INSERT INTO Cart (accountId, productId, sizeId, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cart.getAccountId());
            preparedStatement.setInt(2, cart.getProductId());
            preparedStatement.setInt(3, cart.getSizeId());
            preparedStatement.setInt(4, cart.getQuantity());
            preparedStatement.setBigDecimal(5, cart.getPrice());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm đơn hàng thành công!");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }

}
