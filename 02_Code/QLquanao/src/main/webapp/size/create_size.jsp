<%-- Created by IntelliJ IDEA. User: letau Date: 10/5/2024 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Kích Thước</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
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
            display: flex; /* Sử dụng flexbox cho các liên kết */
            align-items: center; /* Căn giữa icon và text */
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

        .table img {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 8px;
        }

        .btn-primary {
            background-color: #1e90ff;
            border: none;
        }


        .table th {
            background-color: #6c757d;
            color: #fff;
        }
        .banner img {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
        }

        .icon {
            margin-right: 10px; /* Khoảng cách giữa icon và text */
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
    <a href="/products" class="custom-link"><i class="fas fa-tshirt icon"></i> Sản Phẩm</a>
    <a href="/productcategory"><i class="fas fa-list icon"></i> Danh Mục Sản Phẩm</a>
    <a href="/supplier"><i class="fas fa-truck icon"></i> Nhà Cung Cấp</a>
    <a href="/carts"><i class="fas fa-shopping-cart icon"></i> Giỏ Hàng</a>
    <a href="/orders"><i class="fas fa-file-invoice icon"></i> Đơn Hàng</a>
    <a href="/salesreports"><i class="fas fa-chart-line icon"></i> Thống Kê</a>
    <a href="/accounts"><i class="fas fa-user icon"></i> Tài khoản</a>
    <a href="/logout"><i class="fas fa-sign-out-alt icon"></i> Đăng Xuất</a>
</div>

<!-- Content -->
<div class="content">
    <div class="container">
        <h1 style="text-align: center;">Thêm Kích Thước Cho Sản Phẩm</h1>
        <form action="/sizes?action=createPostSize" method="post">
            <input type="hidden" name="productid" value="${productid}" />
            <div class="mb-3">
                <label for="quantity_M" class="form-label">Số Lượng Size M</label>
                <input type="number" class="form-control" id="quantity_M" name="quantity_M" placeholder="Nhập số lượng cho size M">
            </div>
            <div class="mb-3">
                <label for="quantity_L" class="form-label">Số Lượng Size L</label>
                <input type="number" class="form-control" id="quantity_L" name="quantity_L" placeholder="Nhập số lượng cho size L">
            </div>
            <div class="mb-3">
                <label for="quantity_XL" class="form-label">Số Lượng Size XL</label>
                <input type="number" class="form-control" id="quantity_XL" name="quantity_XL" placeholder="Nhập số lượng cho size XL">
            </div>
            <div class="mb-3">
                <label for="quantity_XXL" class="form-label">Số Lượng Size XXL</label>
                <input type="number" class="form-control" id="quantity_XXL" name="quantity_XXL" placeholder="Nhập số lượng cho size XXL">
            </div>
            <button type="submit" class="btn btn-primary">Lưu Kích Thước</button>
            <a href="/products" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Quay Về Trang Chủ
            </a>
        </form>
    </div>
</div>

</body>
</html>
