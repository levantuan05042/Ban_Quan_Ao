package com.example.qlquanao.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesReport {
    private int id;
    private String productName;
    private int quantitySold;
    private BigDecimal unitPrice;
    private BigDecimal totalSales;
    private LocalDateTime reportDate;

    public SalesReport(int id, String productName, int quantitySold, BigDecimal unitPrice, BigDecimal totalSales, LocalDateTime reportDate) {
        this.id = id;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.totalSales = totalSales;
        this.reportDate = reportDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}
