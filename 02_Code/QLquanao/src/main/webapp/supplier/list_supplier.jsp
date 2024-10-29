<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nhà Cung Cấp</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <!-- FontAwesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" crossorigin="anonymous" />

    <style>
        body {
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
            display: flex;
            min-height: 100vh;
            font-family: 'Arial', sans-serif;
        }

        .sidebar {
            width: 250px;
            background-color: #4a5568;
            color: #fff;
            padding: 20px;
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .sidebar h4 {
            font-weight: bold;
            margin-bottom: 30px;
            text-align: center;
        }

        .sidebar a {
            color: #cbd5e0;
            text-decoration: none;
            padding: 12px;
            border-radius: 8px;
            transition: all 0.3s;
            display: flex;
            align-items: center;
        }

        .sidebar a:hover {
            background-color: #718096;
            color: #fff;
        }

        .content {
            flex: 1;
            padding: 20px;
            background-color: #fff;
        }

        .container {
            max-width: 1200px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 30px;
        }

        .table th {
            background-color: #6c757d;
            color: #fff;
        }

        .btn-warning {
            background-color: #ffc107;
            border: none;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn-custom {
            background-color: #1e90ff;
            color: #fff;
            border: none;
            margin-top: 10px;
        }

        .btn-custom:hover {
            background-color: #1c7ed6;
        }

        .icon {
            margin-right: 10px;
        }
        .custom-link {
            background-color: #718096;
            color: #fff;
        }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h4>Quản lý Shop</h4>
    <a href="/products"><i class="fas fa-tshirt icon"></i> Sản Phẩm</a>
    <a href="/productcategory"><i class="fas fa-list icon"></i> Danh Mục Sản Phẩm</a>
    <a href="/supplier"class="custom-link"><i class="fas fa-truck icon"></i> Nhà Cung Cấp</a>
    <a href="/carts"><i class="fas fa-shopping-cart icon"></i> Giỏ Hàng</a>
    <a href="/orders"><i class="fas fa-file-invoice icon"></i> Đơn Hàng</a>
    <a href="/salesreports"><i class="fas fa-chart-line icon"></i> Thống Kê</a>
    <a href="/accounts"><i class="fas fa-user icon"></i> Tài khoản</a>
    <a href="/logout"><i class="fas fa-sign-out-alt icon"></i> Đăng Xuất</a>
</div>

<!-- Main Content -->
<div class="content">
    <div class="container">
        <h2 class="text-center mb-4"><i class="fas fa-truck"></i> Danh Sách Nhà Cung Cấp</h2>
        <a href="supplier?action=createGet" class="btn btn-primary mb-3">
            <i class="fa-solid fa-plus"></i> Thêm Nhà Cung Cấp
        </a>
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Tỉnh</th>
                <th>Quận</th>
                <th>Xã</th>
                <th>Địa Chỉ</th>
                <th>Số Điện Thoại</th>
                <th>Email</th>
                <th>Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="supplier" items="${suppliers}">
                <tr>
                    <td>${supplier.id}</td>
                    <td>${supplier.supplierName}</td>
                    <td>${supplier.provinceName}</td>
                    <td>${supplier.districtName}</td>
                    <td>${supplier.communeName}</td>
                    <td>${supplier.address}</td>
                    <td>${supplier.phoneNumber}</td>
                    <td>${supplier.email}</td>
                    <td class="text-center">
                        <a href="supplier?action=updateGet&id=${supplier.id}" class="btn btn-warning btn-sm">
                            <i class="fas fa-edit"></i> Sửa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
