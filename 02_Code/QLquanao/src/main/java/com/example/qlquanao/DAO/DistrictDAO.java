package com.example.qlquanao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.qlquanao.Model.District;

public class DistrictDAO {
    public List<District> getDistrictsByProvinceId(int provinceId) {
        List<District> districts = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            String query = "SELECT * FROM district WHERE province_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, provinceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                District district = new District(
                        rs.getInt("id"),
                        rs.getString("districtname"),
                        rs.getInt("province_id"));
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districts;
    }

}
