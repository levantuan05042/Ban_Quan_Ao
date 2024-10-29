package com.example.qlquanao.User;

import com.example.qlquanao.DAO.AccountDAO;
import com.example.qlquanao.Model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Kiểm tra lỗi nhập liệu
        if (accountDAO.checkUsernameExists(username)) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (password == null || password.length() < 6) {
            request.setAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (fullname == null || fullname.trim().isEmpty()) {
            request.setAttribute("error", "Tên đầy đủ không được để trống.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("error", "Email không hợp lệ.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!phone.matches("\\d{10,11}")) {
            request.setAttribute("error", "Số điện thoại phải có 10-11 chữ số.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng Account và chèn vào cơ sở dữ liệu
        Account account = new Account(0, username, password, fullname, email, phone, null);

        try {
            accountDAO.insert(account);
            response.sendRedirect("login.jsp");  // Đăng ký thành công, chuyển tới trang đăng nhập
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình đăng ký. Vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
