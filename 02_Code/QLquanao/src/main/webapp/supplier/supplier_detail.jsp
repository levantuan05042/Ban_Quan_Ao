<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
  <title>Chi Tiết Nhà Cung Cấp</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
      max-width: 800px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
      padding: 30px;
      margin-top: 30px;
      flex-direction: column;
      gap: 15px;
    }
    h2 {
      text-align: center;
      margin-bottom: 30px;
    }
    p {
      font-size: 18px;
      line-height: 1.6;
    }
    strong {
      color: #4a5568;
    }
    .banner {
      background-color: #ffcc00;
      padding: 20px;
      text-align: center;
      margin-bottom: 20px;
      border-radius: 10px;
    }
    .banner img {
      max-width: 100%;
      height: auto;
      border-radius: 10px;
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
<div class="sidebar">
  <h4>Quản lý Shop</h4>
  <a href="/products"><i class="fas fa-tshirt icon"></i> Sản Phẩm</a>
  <a href="/productcategory"><i class="fas fa-list icon"></i> Danh Mục Sản Phẩm</a>
  <a href="/supplier" class="custom-link"><i class="fas fa-truck icon"></i> Nhà Cung Cấp</a>
  <a href="/carts"><i class="fas fa-shopping-cart icon"></i> Giỏ Hàng</a>
  <a href="/orders"><i class="fas fa-file-invoice icon"></i> Đơn Hàng</a>
  <a href="/salesreports"><i class="fas fa-chart-line icon"></i> Thống Kê</a>
  <a href="/accounts"><i class="fas fa-user icon"></i> Tài khoản</a>
  <a href="/logout"><i class="fas fa-sign-out-alt icon"></i> Đăng Xuất</a>
</div>
<div class="content">
  <div class="banner">
    <h2>Chi Tiết Nhà Cung Cấp</h2>
  </div>
  <div class="container">
    <h2>Chi Tiết Nhà Cung Cấp</h2>
    <c:if test="${supplier != null}">
      <p><strong>Tên Nhà Cung Cấp:</strong> ${supplier.supplierName}</p>
      <p><strong>Địa chỉ:</strong> ${supplier.address}</p>
      <p><strong>Số điện thoại:</strong> ${supplier.phoneNumber}</p>
      <p><strong>Email:</strong> ${supplier.email}</p>
      <p><strong>Tỉnh:</strong> ${supplier.provinceName}</p>
      <p><strong>Huyện:</strong> ${supplier.districtName}</p>
      <p><strong>Xã:</strong> ${supplier.communeName}</p>
    </c:if>
    <c:if test="${supplier == null}">
      <p>Nhà cung cấp không tồn tại!</p>
    </c:if>
  </div>
</div>
</body>
</html>
