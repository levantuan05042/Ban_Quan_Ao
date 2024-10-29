<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Cập Nhật Nhà Cung Cấp</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" crossorigin="anonymous"/>
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

    .btn-primary {
      background-color: #1e90ff;
      border: none;
    }

    .btn-secondary {
      background-color: #6c757d;
      border: none;
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

<!-- Content -->
<div class="content">
  <div class="container">
    <h1 class="text-center mb-4">Cập Nhật Nhà Cung Cấp</h1>
    <form action="supplier?action=updatePost" method="post">
      <input type="hidden" name="id" value="${supplier.id}"/>
      <div class="mb-3">
        <label for="supplierName" class="form-label">Tên Nhà Cung Cấp</label>
        <input type="text" class="form-control" id="supplierName" name="supplierName" value="${supplier.supplierName}" required>
      </div>
      <div class="mb-3">
        <label for="province" class="form-label">Tỉnh/Thành</label>
        <select class="form-control" id="province" name="province" required>
          <option value="">Chọn Tỉnh/Thành</option>
          <c:forEach var="province" items="${provinces}">
            <option value="${province.id}" <c:if test="${province.id == supplier.provinceId}">selected</c:if>>
                ${province.provinceName}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="district" class="form-label">Quận/Huyện</label>
        <select class="form-control" id="district" name="district" required>
          <option value="">Chọn Quận/Huyện</option>
          <c:forEach var="district" items="${districts}">
            <option value="${district.id}" <c:if test="${district.id == supplier.districtId}">selected</c:if>>
                ${district.districtName}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="commune" class="form-label">Phường/Xã</label>
        <select class="form-control" id="commune" name="commune" required>
          <option value="">Chọn Phường/Xã</option>
          <c:forEach var="commune" items="${communes}">
            <option value="${commune.id}" <c:if test="${commune.id == supplier.communeId}">selected</c:if>>
                ${commune.communeName}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="address" class="form-label">Địa Chỉ</label>
        <input type="text" class="form-control" id="address" name="address" value="${supplier.address}" required>
      </div>
      <div class="mb-3">
        <label for="phoneNumber" class="form-label">Số Điện Thoại</label>
        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${supplier.phoneNumber}" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" value="${supplier.email}" required>
      </div>
      <div class="mb-3">
        <button type="submit" class="btn btn-primary" onclick="return confirm('Bạn có chắc chắn muốn sửa thông tin nhà cung cấp này không?');">
          <i class="fas fa-save"></i> Cập Nhật
        </button>
        <a href="supplier" class="btn btn-secondary">
          <i class="fas fa-arrow-left"></i> Quay Về
        </a>
      </div>
    </form>
  </div>
</div>

</body>
</html>
