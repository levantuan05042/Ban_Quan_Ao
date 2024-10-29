<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <title>Đăng Nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e9ecef;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        .login-title {
            text-align: center;
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
            color: #343a40;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2 class="login-title">Đăng Nhập</h2>
    <form action="login" method="post">
        <div id="errorMessage" class="error-message" style="display: none;"></div>

        <div class="form-group">
            <label for="username">Tên Đăng Nhập</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Mật Khẩu</label>
            <div class="input-group">
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="input-group-append">
                    <button type="button" class="btn btn-outline-secondary" id="passwordToggle" onclick="togglePasswordVisibility()">
                        <i class="fas fa-eye" id="eyeIcon"></i>
                    </button>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-custom btn-block" name="action" value="login">Đăng Nhập</button>
        <div class="text-center mt-3">
            <a href="register.jsp">Chưa có tài khoản? Đăng ký ngay!</a>
        </div>
    </form>
</div>

<script>
    function togglePasswordVisibility() {
        const passwordField = document.getElementById("password");
        const eyeIcon = document.getElementById("eyeIcon");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            eyeIcon.classList.remove("fa-eye");
            eyeIcon.classList.add("fa-eye-slash");
        } else {
            passwordField.type = "password";
            eyeIcon.classList.remove("fa-eye-slash");
            eyeIcon.classList.add("fa-eye");
        }
    }

    $(document).ready(function() {
        // Hiển thị thông báo lỗi nếu có
        const errorMessage = '<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>';
        if (errorMessage) {
            $('#errorMessage').text(errorMessage).show();
        }
    });
</script>
</body>
</html>
