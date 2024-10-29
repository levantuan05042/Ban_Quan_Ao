<%--
  Created by IntelliJ IDEA.
  User: letau
  Date: 10/14/2024
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: letau
  Date: 10/14/2024
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%-- Created by IntelliJ IDEA. User: letau Date: 10/4/2024 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Thống Kê</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <!-- FontAwesome CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" crossorigin="anonymous" />
  <!-- Chart.js -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
     body {
       background-color: #f0f8ff;
       margin: 0;
       padding: 0;
       display: flex;
       min-height: 100vh;
       font-family: 'Arial', sans-serif;
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

    canvas {
      max-width: 100%;
      height: 400px;
    }
  </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
  <h4>Quản lý Shop</h4>
  <a href="/products" ><i class="fas fa-tshirt icon"></i> Sản Phẩm</a>
  <a href="/productcategory"><i class="fas fa-list icon"></i> Danh Mục Sản Phẩm</a>
  <a href="/supplier"><i class="fas fa-truck icon"></i> Nhà Cung Cấp</a>
  <a href="/carts"><i class="fas fa-shopping-cart icon"></i> Giỏ Hàng</a>
  <a href="/orders"><i class="fas fa-file-invoice icon"></i> Đơn Hàng</a>
  <a href="/salesreports"class="custom-link"><i class="fas fa-chart-line icon"></i> Thống Kê</a>
  <a href="/accounts"><i class="fas fa-user icon"></i> Tài khoản</a>
  <a href="/logout"><i class="fas fa-sign-out-alt icon"></i> Đăng Xuất</a>
</div>
<!-- Content -->
<div class="content">
  <div class="container">
    <table class="table table-hover table-bordered">
      <div class="content">
        <div class="container">
          <h2 class="mb-4 text-center"><i class="fas fa-chart-line"></i> Thống Kê Theo Ngày và Sản Phẩm</h2>

          <!-- Biểu đồ -->
          <canvas id="salesChart"></canvas>
        </div>
      </div>
    </table>
  </div>
</div>
<script>
  const ctx = document.getElementById('salesChart').getContext('2d');

  const salesChart = new Chart(ctx, {
    type: 'bar', // Biểu đồ cột
    data: {
      labels: [
        <c:forEach items="${salesreports}" var="s">
        '${s.productName} - ${s.reportDate}', // Kết hợp tên sản phẩm và ngày
        </c:forEach>
      ],
      datasets: [{
        label: 'Số lượng đã bán',
        data: [
          <c:forEach items="${salesreports}" var="s">
          ${s.quantitySold}, // Số lượng bán được
          </c:forEach>
        ],
        backgroundColor: 'rgba(75, 192, 192, 0.5)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          display: true,
          position: 'top',
        },
        tooltip: {
          callbacks: {
            label: function(tooltipItem) {
              return `Số lượng: ${tooltipItem.raw}`;
            }
          }
        }
      },
      scales: {
        x: {
          title: {
            display: true,
            text: 'Sản Phẩm - Ngày'
          }
        },
        y: {
          beginAtZero: true,
          title: {
            display: true,
            text: 'Số Lượng Bán'
          }
        }
      }
    }
  });
</script>
</body>
</html>

