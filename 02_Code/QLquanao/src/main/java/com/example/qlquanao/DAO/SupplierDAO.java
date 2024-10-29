package com.example.qlquanao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.qlquanao.Model.Supplier;

public class SupplierDAO {

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT s.id, s.suppliername, s.address, s.phonenumber, s.email, "
                + "p.id as provinceId, p.provincename, "
                + "d.id as districtId, d.districtname, "
                + "c.id as communeId, c.communename "
                + "FROM supplier s "
                + "LEFT JOIN province p ON s.province = p.id "
                + "LEFT JOIN district d ON s.district = d.id "
                + "LEFT JOIN commune c ON s.commune = c.id";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("suppliername"),
                        rs.getString("address"),
                        rs.getString("phonenumber"),
                        rs.getString("email"),
                        rs.getInt("provinceId"),
                        rs.getString("provincename"),
                        rs.getInt("districtId"),
                        rs.getString("districtname"),
                        rs.getInt("communeId"),
                        rs.getString("communename"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void addSupplier(Supplier supplier) {
        String query = "INSERT INTO supplier (suppliername, address, phonenumber, email, province, district, commune) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getPhoneNumber());
            ps.setString(4, supplier.getEmail());
            ps.setInt(5, supplier.getProvinceId());
            ps.setInt(6, supplier.getDistrictId());
            ps.setInt(7, supplier.getCommuneId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Supplier findById(int id) {
        Supplier supplier = null;
        String query = "SELECT supplier.id, supplier.suppliername, supplier.address, supplier.phonenumber, supplier.email, "
                + "province.id as provinceId, province.provincename, "
                + "district.id as districtId, district.districtname, "
                + "commune.id as communeId, commune.communename "
                + "FROM supplier "
                + "LEFT JOIN province ON supplier.province = province.id "
                + "LEFT JOIN district ON supplier.district = district.id "
                + "LEFT JOIN commune ON supplier.commune = commune.id "
                + "WHERE supplier.id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    supplier = new Supplier(
                            rs.getInt("id"),
                            rs.getString("suppliername"),
                            rs.getString("address"),
                            rs.getString("phonenumber"),
                            rs.getString("email"),
                            rs.getInt("provinceId"),
                            rs.getString("provincename"),
                            rs.getInt("districtId"),
                            rs.getString("districtname"),
                            rs.getInt("communeId"),
                            rs.getString("communename"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public void updateSupplier(Supplier supplier) {
        String query = "UPDATE supplier SET suppliername = ?, address = ?, phonenumber = ?, email = ?, province = ?, district = ?, commune = ? WHERE id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getPhoneNumber());
            ps.setString(4, supplier.getEmail());
            ps.setInt(5, supplier.getProvinceId());
            ps.setInt(6, supplier.getDistrictId());
            ps.setInt(7, supplier.getCommuneId());
            ps.setInt(8, supplier.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
