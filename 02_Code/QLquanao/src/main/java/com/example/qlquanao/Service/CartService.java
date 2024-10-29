package com.example.qlquanao.Service;

import com.example.qlquanao.DAO.CartDAO;
import com.example.qlquanao.Model.Cart;

import java.util.ArrayList;

public class CartService {
    private CartDAO cartDAO;

    public CartService() {

        this.cartDAO = new CartDAO();
    }

    public ArrayList<Cart> FINDALL() {

        return this.cartDAO.findAll();
    }
    public Cart FINDBYID(int id) {

        return this.cartDAO.findCartById(id);
    }
    public ArrayList<Cart> findCartsByUsername(String username) {

        return cartDAO.findCartsByUsername(username);
    }
    public void DELETE2(int id) {
        this.cartDAO.DELETE(id);
    }
}
