<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
  <title>Chỉnh Sửa Số Lượng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
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
  <div class="container">
    <h2 class="text-center mb-4">Cập Nhật Số Lượng Kích Thước</h2>
    <form action="/sizes?action=updatePost" method="post">
      <input type="hidden" name="id" value="${size.id}">
      <div class="mb-3">
        <label for="quantity" class="form-label">Số Lượng</label>
        <input type="number" class="form-control" id="quantity" name="quantity" value="${size.quantity}" required>
      </div>
      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary" onclick="return confirm('Bạn có chắc chắn muốn sửa số lượng của kích thước này không?');">
          <i class="fas fa-save"></i> Lưu Số Lượng
        </button>
        <a href="/products" class="btn btn-secondary">
          <i class="fas fa-arrow-left"></i> Quay Về Trang Chủ
        </a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
