package com.example.qlquanao.User;

import com.example.qlquanao.DAO.AccountDAO;
import com.example.qlquanao.Model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Account account = (Account) session.getAttribute("loggedInUser");
        request.setAttribute("account", account);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }
}
