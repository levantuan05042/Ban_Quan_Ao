package com.example.qlquanao.Model;

public class District {
    private int id;
    private String districtName;
    private int provinceId;

    public District(int id, String districtName, int provinceId) {
        this.id = id;
        this.districtName = districtName;
        this.provinceId = provinceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
