package com.example.qlquanao.Model;

public class Supplier {
    private int id;
    private String supplierName;
    private String address;
    private String phoneNumber;
    private String email;
    private int provinceId;
    private int districtId;
    private int communeId;
    private String provinceName;
    private String districtName;
    private String communeName;

    public Supplier(int id, String supplierName, String address, String phoneNumber, String email, int provinceId, String provinceName, int districtId, String districtName, int communeId, String communeName) {
        this.id = id;
        this.supplierName = supplierName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.communeId = communeId;
        this.communeName = communeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }
}
