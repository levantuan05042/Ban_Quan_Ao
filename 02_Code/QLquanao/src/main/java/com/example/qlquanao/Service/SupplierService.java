package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.SupplierDAO;
import com.example.qlquanao.Model.Supplier;
import java.util.List;

public class SupplierService {

    private SupplierDAO supplierDAO;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    public void addSupplier(Supplier supplier) {
        supplierDAO.addSupplier(supplier);
    }

    public Supplier findById(int id) {
        return supplierDAO.findById(id);
    }

    public void updateSupplier(Supplier supplier) {
        supplierDAO.updateSupplier(supplier);
    }
}
