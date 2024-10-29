package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AccountDAO {

    // Lấy danh sách tất cả tài khoản
    public ArrayList<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Account");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phonenumber");
                LocalDateTime createdDate = resultSet.getTimestamp("createddate").toLocalDateTime();
                accounts.add(new Account(id, username, password, fullname, email, phoneNumber, createdDate));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return accounts;
    }

    // Tìm tài khoản theo tên đăng nhập
    public Account findByAccountName(String username) {
        Account account = null;
        String query = "SELECT * FROM Account WHERE username = ?";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account = new Account(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("fullname"),
                            rs.getString("email"),
                            rs.getString("phonenumber"),
                            rs.getTimestamp("createddate").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Đăng nhập
    public Account login(String username, String password) {
        Account account = null;
        String query = "SELECT * FROM Account WHERE username = ? AND password = ?";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account = new Account(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("fullname"),
                            rs.getString("email"),
                            rs.getString("phonenumber"),
                            rs.getTimestamp("createddate").toLocalDateTime(),
                            rs.getString("role") // Lưu ý: Kiểm tra xem trường 'role' có tồn tại không
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Kiểm tra xem username đã tồn tại chưa
    public boolean checkUsernameExists(String username) {
        String query = "SELECT 1 FROM Account WHERE username = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Trả về true nếu tìm thấy username
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, coi như username không tồn tại
        }
    }

    // Thêm tài khoản vào cơ sở dữ liệu
    public void insert(Account account) {
        String query = "INSERT INTO Account (username, password, fullname, email, phonenumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getFullName());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhoneNumber());

            ps.executeUpdate(); // Thực thi câu lệnh INSERT
            System.out.println("Thêm tài khoản thành công!");

        } catch (SQLException e) {
            System.err.println("Có lỗi xảy ra khi thêm tài khoản: " + e.getMessage());
        }
    }
}
