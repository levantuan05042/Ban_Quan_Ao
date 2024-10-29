package com.example.qlquanao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.qlquanao.Model.Commune;

public class CommuneDAO {
    public List<Commune> getCommunesByDistrictId(int districtId) {
        List<Commune> communes = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            String query = "SELECT * FROM commune WHERE district_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, districtId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commune commune = new Commune(
                        rs.getInt("id"),
                        rs.getString("communename"),
                        rs.getInt("district_id"));
                communes.add(commune);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communes;
    }
}
