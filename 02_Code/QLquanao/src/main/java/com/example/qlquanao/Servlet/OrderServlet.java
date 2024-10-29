package com.example.qlquanao.Servlet;

import com.example.qlquanao.Service.AccountService;
import com.example.qlquanao.Service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

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
            case "updateStatus":
                updateOrderStatus(request, response);
                break;
        }
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("order/list_order.jsp");
        request.setAttribute("orders", orderService.FINDALL());
        requestDispatcher.forward(request, response);
    }
    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        String newStatus = request.getParameter("status");

        if (newStatus.equals("pending") || newStatus.equals("confirmed")) {
            orderService.updateOrderStatus(orderId, newStatus);
            response.sendRedirect("/orders");
        } else {
            response.getWriter().write("Trạng thái không hợp lệ!");
        }
    }
}

