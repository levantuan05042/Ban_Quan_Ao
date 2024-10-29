package com.example.qlquanao.Servlet;

import com.example.qlquanao.Service.CartService;
import com.example.qlquanao.Service.SalesreportsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet(name = "SalesroportsSetvlet", value = "/salesreports")
    public class SalesroportsSetvlet extends HttpServlet {
        private SalesreportsService salesreportsService = new SalesreportsService();
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                default:
                    displayAllProduct(request, response);
                    break;
            }
        }
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {
            }
        }
        private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("salesreports/list_salesreports.jsp");
            request.setAttribute("salesreports", salesreportsService.FINDALL());
            requestDispatcher.forward(request, response);
        }
    }

