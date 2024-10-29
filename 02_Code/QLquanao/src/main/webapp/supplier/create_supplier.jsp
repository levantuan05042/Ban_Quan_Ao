<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thêm Nhà Cung Cấp</title>
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
      max-width: 800px;
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

    .form-label {
      font-weight: bold;
    }
    .icon {
      margin-right: 10px;
    }
    .custom-link {
      background-color: #718096;
      color: #fff;
    }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $("#province").change(function() {
        var provinceId = $(this).val();
        $.ajax({
          url: "supplier?action=loadDistricts",
          type: "GET",
          data: { provinceId: provinceId },
          success: function(response) {
            $("#district").html('<option value="">Chọn Quận/Huyện</option>'); // Clear and reload districts
            $("#district").append(response); // Thêm Quận/Huyện mới vào
            $("#commune").html('<option value="">Chọn Xã/Phường</option>');
          }
        });
      });

      $("#district").change(function() {
        var districtId = $(this).val();
        $.ajax({
          url: "supplier?action=loadCommunes",
          type: "GET",
          data: { districtId: districtId },
          success: function(response) {
            $("#commune").html('<option value="">Chọn Xã/Phường</option>'); // Clear and reload communes
            $("#commune").append(response); // Thêm Xã/Phường mới vào
          }
        });
      });
    });
  </script>
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
    <h1 class="text-center mb-4">Thêm Nhà Cung Cấp</h1>
    <form action="supplier?action=createPost" method="post">
      <div class="mb-3">
        <label for="supplierName" class="form-label">Tên Nhà Cung Cấp</label>
        <input type="text" class="form-control" id="supplierName" name="supplierName" required />
      </div>
      <div class="mb-3">
        <label for="province" class="form-label">Tỉnh/Thành phố</label>
        <select name="province" id="province" class="form-control" required>
          <option value="">Chọn Tỉnh</option>
          <c:forEach var="province" items="${provinces}">
            <option value="${province.id}">${province.provinceName}</option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label for="district" class="form-label">Quận/Huyện</label>
        <select name="district" id="district" class="form-control" required>
          <option value="">Chọn Quận/Huyện</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="commune" class="form-label">Xã/Phường</label>
        <select name="commune" id="commune" class="form-control" required>
          <option value="">Chọn Xã/Phường</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="address" class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" id="address" name="address" required />
      </div>
      <div class="mb-3">
        <label for="phoneNumber" class="form-label">Số điện thoại</label>
        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" required />
      </div>
      <div class="mb-3">
        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Lưu</button>
        <a href="/supplier" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Quay Về Danh Sách</a>
      </div>
    </form>
  </div>
</div>

</body>
</html>
