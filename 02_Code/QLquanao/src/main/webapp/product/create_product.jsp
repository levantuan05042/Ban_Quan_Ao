<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
  <title>Thêm Sản Phẩm</title>
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

    .btn-info {
      background-color: #17a2b8;
      border: none;
    }

    .btn-warning {
      background-color: #ffc107;
      border: none;
    }

    .btn-danger {
      background-color: #dc3545;
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

<!-- Main Content -->
<div class="content">
  <div class="container">
    <h2 class="text-center mb-4">Thêm Sản Phẩm</h2>

    <form action="/products?action=createPost" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="productname" class="form-label">Tên Sản Phẩm</label>
        <input type="text" class="form-control" id="productname" name="productname" placeholder="Nhập tên sản phẩm" required>
      </div>

      <div class="mb-3">
        <label for="price" class="form-label">Giá Sản Phẩm</label>
        <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Nhập giá sản phẩm" required>
      </div>

<%--      <div class="mb-3">--%>
<%--        <label for="supplier" class="form-label">Nhà Cung Cấp</label>--%>
<%--        <select class="form-control" name="categoryid" id="supplier" required>--%>
<%--          <option value="">--- Chọn Nhà Cung Cấp ---</option>--%>
<%--          <c:forEach items="${suppliers}" var="s">--%>
<%--            <option value="${s.id}">${s.supplierName}</option>--%>
<%--          </c:forEach>--%>
<%--        </select>--%>
<%--      </div>--%>

      <div class="mb-3">
        <label for="productCategory" class="form-label">Danh Mục</label>
        <select class="form-control" name="categoryid" id="productCategory" required>
          <option value="">--- Chọn danh mục ---</option>
          <c:forEach items="${productCategories}" var="category">
            <option value="${category.id}">${category.categoryname}</option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="supplier" class="form-label">Nhà Cung Cấp</label>
        <select class="form-control" name="supplierId" id="supplier" required>
          <option value="">--- Chọn Nhà Cung Cấp ---</option>
          <c:forEach items="${suppliers}" var="s">
            <option value="${s.id}">${s.supplierName}</option>
          </c:forEach>
        </select>
      </div>


      <div class="mb-3">
        <label for="productimage" class="form-label">Ảnh Sản Phẩm</label>
        <input type="file" class="form-control" id="productimage" name="productimage" accept="image/*" required>
      </div>

      <div class="mb-3">
        <label for="description" class="form-label">Mô Tả</label>
        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Nhập mô tả sản phẩm" required></textarea>
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">
          <i class="fas fa-save"></i> Lưu Sản Phẩm
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
