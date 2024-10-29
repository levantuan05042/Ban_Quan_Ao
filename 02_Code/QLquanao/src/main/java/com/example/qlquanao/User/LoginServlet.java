package com.example.qlquanao.User;

import com.example.qlquanao.DAO.AccountDAO;
import com.example.qlquanao.Model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.login(username, password);

        if (account != null) {
            request.getSession().setAttribute("account", account);
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userId", account.getId());

            // Kiểm tra vai trò của người dùng
            String role = account.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("/products");
            } else if ("staff".equals(role)) {
                response.sendRedirect("/user");
            }
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
