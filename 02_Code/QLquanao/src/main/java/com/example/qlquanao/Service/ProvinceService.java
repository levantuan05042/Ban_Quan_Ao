package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.ProvinceDAO;
import com.example.qlquanao.Model.Province;

import java.util.List;

public class ProvinceService {
    private ProvinceDAO provinceDAO;

    public ProvinceService() {
        this.provinceDAO = new ProvinceDAO();
    }

    public List<Province> getAllProvinces() {
        return provinceDAO.getAllProvinces();
    }
}
