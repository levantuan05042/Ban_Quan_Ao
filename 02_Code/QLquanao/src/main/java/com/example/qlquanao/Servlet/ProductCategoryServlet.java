package com.example.qlquanao.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.qlquanao.Model.ProductCategory;
import com.example.qlquanao.Service.ProductCategorySevice;

import java.io.*;


@WebServlet(name = "ProductCategoryServlet", value = "/productcategory")
public class ProductCategoryServlet extends HttpServlet{
    private ProductCategorySevice productCategorySevice = new ProductCategorySevice();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createGet":
                createGetProductcategory(request, response);
                break;
            case "updateGet":
                updateGetProductcategory(request, response);
                break;
            case "":
                displayAllProductcategory(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "createPost":
                createPostProductcategory(request, response);
                break;
            case "updatePost":
                updatePostProductcategory(request, response);
                break;
        }
    }
    private void displayAllProductcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productcategory/list_productcategory.jsp");
        request.setAttribute("productcategory", productCategorySevice.FINDALL());
        requestDispatcher.forward(request, response);
    }


    private void createGetProductcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productcategory/create_productcategory.jsp");
        requestDispatcher.forward(request, response);

    }
    private void createPostProductcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        productCategorySevice.CREATEPRODUCTCATEORY(new ProductCategory(name));
        response.sendRedirect("productcategory");
    }
    private void updateGetProductcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productcategory/update_productcategory.jsp");
        request.setAttribute("productcategory", productCategorySevice.FINDBYID(id));
        requestDispatcher.forward(request, response);
    }
    private void updatePostProductcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        productCategorySevice.UPDTEPRODUCTCATEORY(new ProductCategory(id, name));
        response.sendRedirect("productcategory");
    }
}
