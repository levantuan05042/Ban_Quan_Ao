<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhẹ */
            font-family: Arial, sans-serif; /* Font chữ */
        }
        .register-container {
            max-width: 500px; /* Đặt chiều rộng tối đa */
            margin: 50px auto; /* Canh giữa */
            padding: 20px; /* Khoảng đệm */
            background-color: #ffffff; /* Màu nền trắng */
            border-radius: 10px; /* Bo góc */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng */
        }
        .register-title {
            text-align: center; /* Canh giữa tiêu đề */
            margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
        }
        .btn-custom {
            background-color: #007bff; /* Màu nền cho nút đăng ký */
            color: white; /* Màu chữ */
        }
        .btn-custom:hover {
            background-color: #0056b3; /* Màu nền khi hover */
            color: white; /* Màu chữ khi hover */
        }
        .toggle-password {
            cursor: pointer; /* Thay đổi con trỏ khi di chuột qua */
        }
    </style>
    <title>Đăng Ký</title>
</head>
<body>
<div class="register-container">
    <h2 class="register-title">Đăng Ký Tài Khoản</h2>
    <form action="register" method="post">
        <div class="form-group">
            <label for="username">Tên Đăng Nhập</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Mật Khẩu</label>
            <div class="input-group">
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="input-group-append">
                    <span class="input-group-text toggle-password" id="togglePassword1">
                        <i class="fas fa-eye" id="eyeIcon1"></i>
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Nhập Lại Mật Khẩu</label>
            <div class="input-group">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                <div class="input-group-append">
                    <span class="input-group-text toggle-password" id="togglePassword2">
                        <i class="fas fa-eye" id="eyeIcon2"></i>
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="fullname">Tên Đầy Đủ</label>
            <input type="text" class="form-control" id="fullname" name="fullname" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phone">Số Điện Thoại</label>
            <input type="text" class="form-control" id="phone" name="phone" required>
        </div>
        <button type="submit" class="btn btn-custom btn-block">Đăng Ký</button>
    </form>
</div>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<script>
    Swal.fire({
        icon: 'error',
        title: 'Lỗi!',
        text: '<%= error %>',
        confirmButtonColor: '#007bff'
    });
</script>
<% } %>

<script>
    // Tính năng hiển thị/ẩn mật khẩu
    document.getElementById('togglePassword1').addEventListener('click', function () {
        const passwordInput = document.getElementById('password');
        const eyeIcon = document.getElementById('eyeIcon1');
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            passwordInput.type = 'password';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    });

    document.getElementById('togglePassword2').addEventListener('click', function () {
        const confirmPasswordInput = document.getElementById('confirmPassword');
        const eyeIcon = document.getElementById('eyeIcon2');
        if (confirmPasswordInput.type === 'password') {
            confirmPasswordInput.type = 'text';
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            confirmPasswordInput.type = 'password';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    });
</script>

</body>
</html>
