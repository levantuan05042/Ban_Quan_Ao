package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.CommuneDAO;
import com.example.qlquanao.Model.Commune;

import java.util.List;

public class CommuneService {
    private CommuneDAO communeDAO;

    public CommuneService() {
        this.communeDAO = new CommuneDAO();
    }

    public List<Commune> getCommunesByDistrictId(int districtId) {
        return communeDAO.getCommunesByDistrictId(districtId);
    }
}
