package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.OrderDAO;
import com.example.qlquanao.DAO.CartDAO; // Import CartDAO
import com.example.qlquanao.Model.Order;

import java.util.ArrayList;

public class OrderService {
    private OrderDAO orderDAO;
    private CartDAO cartDAO; // Khai báo CartDAO

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.cartDAO = new CartDAO(); // Khởi tạo CartDAO
    }

    public ArrayList<Order> FINDALL() {
        return this.orderDAO.findAll();
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        orderDAO.updateOrderStatus(orderId, newStatus);
    }

    public void insertOrder(Order order) {
        orderDAO.insertOrder(order);
    }

    public ArrayList<Order> findOrdersByAccountId(String username) {
        return orderDAO.findOrdersByAccountId(username);
    }

    public void DELETE2(int id) {
        this.orderDAO.DELETE(id);
    }

    // Thêm phương thức removeFromCart
    public void removeFromCart(int cartId) {
        this.cartDAO.DELETE(cartId); // Gọi phương thức DELETE trong CartDAO
    }
    public ArrayList<Order> findOrdersByAccountId2(String username) {
        return orderDAO.findOrdersByAccountId2(username);
    }
}
