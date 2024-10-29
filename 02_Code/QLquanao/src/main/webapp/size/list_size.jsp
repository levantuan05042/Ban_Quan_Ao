<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Kích Cỡ Sản Phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
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
    .table img {
      width: 80px;
      height: 80px;
      object-fit: cover;
      border-radius: 8px;
    }
    .table th {
      background-color: #6c757d;
      color: #fff;
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
  <div class="container mt-4">
    <h2 class="text-center">Kích Cỡ Của Sản Phẩm</h2>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>Kích Cỡ</th>
        <th>Số Lượng</th>
        <th>Chức Năng</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${sizes}" var="size">
        <tr>
          <td>${size.sizename}</td>
          <td>${size.quantity}</td>
          <td class="text-center">
            <a href="sizes?action=updateGet&id=${size.id}" class="btn btn-warning">
              <i class="fa-solid fa-pen-to-square"></i> Sửa
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <a href="products" class="btn btn-secondary">Quay về danh sách sản phẩm</a>
  </div>
</div>
</body>
</html>
