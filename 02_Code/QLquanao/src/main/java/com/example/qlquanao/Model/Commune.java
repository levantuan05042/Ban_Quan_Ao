package com.example.qlquanao.Model;

public class Commune {
    private int id;
    private String communeName;
    private int districtId;

    public Commune(int id, String communeName, int districtId) {
        this.id = id;
        this.communeName = communeName;
        this.districtId = districtId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    // Getters and setters
}
