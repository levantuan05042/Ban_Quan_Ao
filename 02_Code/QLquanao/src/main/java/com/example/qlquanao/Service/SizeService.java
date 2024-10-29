package com.example.qlquanao.Service;


import com.example.qlquanao.DAO.ProductDAO;
import com.example.qlquanao.DAO.SizeDAO;
import com.example.qlquanao.Model.Product;
import com.example.qlquanao.Model.Size;
import com.example.qlquanao.Model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SizeService {
    private SizeDAO sizeDAO;

    public SizeService() {

        this.sizeDAO = new SizeDAO();
    }
    public ArrayList<Size> FINDBYSIZE(int productid) {
        return this.sizeDAO.findBySize(productid);
    }
    public void CREATEPRODUCT(Size size) {
        this.sizeDAO.createSize(size);
    }

    public Size findById(int id) {
        return sizeDAO.findById(id);
    }
        public void updateSizeQuantity(int id, int quantity) {
        sizeDAO.updateSizeQuantity(id, quantity);
    }
    public List<Size> getAllSize(int productId) {
        return sizeDAO.getAllSizes(productId);
    }

}

