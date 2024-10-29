package com.example.qlquanao.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.qlquanao.Model.Size;
import com.example.qlquanao.Service.SizeService;
import java.util.List;

@WebServlet("/sizes")
public class SizeServlet extends HttpServlet {
    private SizeService sizeService = new SizeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "findBySize":
                findBySize(request, response);
                break;
            case "createGetSize":
                createGetSize(request, response);
                break;
            case "updateGet":
                updatGetSize(request, response);
                break;
            default:
                response.sendRedirect("404.jsp"); // Redirect nếu action không hợp lệ
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "createPostSize":
                createPostSize(request, response);
                break;
            case "updatePost":
                updatePostSize(request, response);
                break;
            default:
                response.sendRedirect("404.jsp");
                break;
        }
    }
    private void findBySize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productid"));
        List<Size> sizes = sizeService.FINDBYSIZE(productId); // Lấy danh sách kích thước
        request.setAttribute("sizes", sizes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("size/list_size.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGetSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productid = Integer.parseInt(request.getParameter("productid"));
        request.setAttribute("productid", productid); // Truyền productid vào request
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("size/create_size.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPostSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productid"));
        String[] sizeNames = {"M", "L", "XL", "XXL"};
        for (String sizeName : sizeNames) {
            String quantityStr = request.getParameter("quantity_" + sizeName);
            int quantity = (quantityStr != null && !quantityStr.isEmpty()) ? Integer.parseInt(quantityStr) : 0;
            Size sizeObj = new Size(sizeName, quantity, productId);
            sizeService.CREATEPRODUCT(sizeObj);
        }
        response.sendRedirect("products");
    }
    private void updatGetSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Size size = sizeService.findById(id);
        request.setAttribute("size", size);
        RequestDispatcher dispatcher = request.getRequestDispatcher("size/update_size.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePostSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Size size = sizeService.findById(id);
        int productId = size.getProductId();
        sizeService.updateSizeQuantity(id, quantity);
        response.sendRedirect("sizes?action=findBySize&productid=" + productId);
    }

}
