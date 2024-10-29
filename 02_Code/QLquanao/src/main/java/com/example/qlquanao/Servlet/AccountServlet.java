package com.example.qlquanao.Servlet;

import com.example.qlquanao.Model.Account;
import com.example.qlquanao.Service.ProductCategorySevice;
import com.example.qlquanao.Service.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/accounts")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                viewAccount(request, response);
                break;
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
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/list_account.jsp");
        request.setAttribute("accounts", accountService.FINDALL());
        requestDispatcher.forward(request, response);
    }

    private void viewAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Account account = accountService.findByAccountName(username);
        request.setAttribute("account", account);
        RequestDispatcher dispatcher = request.getRequestDispatcher("account/account_detail.jsp");
        dispatcher.forward(request, response);
    }

}
