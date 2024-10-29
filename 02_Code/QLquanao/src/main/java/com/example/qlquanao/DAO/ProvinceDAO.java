package com.example.qlquanao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.qlquanao.Model.Province;

public class ProvinceDAO {
    public List<Province> getAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            String query = "SELECT * FROM province";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Province province = new Province(
                        rs.getInt("id"),
                        rs.getString("provincename"));
                provinces.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provinces;
    }
}
