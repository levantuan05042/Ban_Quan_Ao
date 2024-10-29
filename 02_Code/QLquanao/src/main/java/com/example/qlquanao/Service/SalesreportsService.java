package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.CartDAO;
import com.example.qlquanao.DAO.SalesReportDAO;
import com.example.qlquanao.Model.Cart;
import com.example.qlquanao.Model.SalesReport;

import java.util.ArrayList;

public class SalesreportsService {
    private SalesReportDAO salesReportDAO;

    public SalesreportsService() {

        this.salesReportDAO = new SalesReportDAO();
    }

    public ArrayList<SalesReport>FINDALL() {

        return this.salesReportDAO.findAll();
    }
}
