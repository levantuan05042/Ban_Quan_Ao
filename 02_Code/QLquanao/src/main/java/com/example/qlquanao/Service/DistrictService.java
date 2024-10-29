package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.DistrictDAO;
import com.example.qlquanao.Model.District;

import java.util.List;

public class DistrictService {
    private DistrictDAO districtDAO;

    public DistrictService() {
        this.districtDAO = new DistrictDAO();
    }

    public List<District> getDistrictsByProvinceId(int provinceId) {
        return districtDAO.getDistrictsByProvinceId(provinceId);
    }
}
