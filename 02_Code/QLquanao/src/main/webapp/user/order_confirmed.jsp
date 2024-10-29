<%--
  Created by IntelliJ IDEA.
  User: letau
  Date: 10/25/2024
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: letau
  Date: 10/17/2024
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
  <title>Đơn Hàng</title>
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #f1f1f1;
      margin: 0;
      padding: 0;
      padding-top: 150px;
    }
    .header {
      background: linear-gradient(to right, #a6a6a6, #d9d9d9);
      color: #333;
      padding: 20px 0;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      background: linear-gradient(to right, #a6a6a6, #d9d9d9);
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      z-index: 1000;
    }
    .header {
      background-color: #f8f9fa; /* Màu nền nhẹ */
      padding: 20px 0;
      border-bottom: 2px solid #ddd;
    }

    .header h1 {
      font-size: 2.5rem;
      font-weight: bold;
    }

    .header .nav a {
      color: #333;
      font-size: 1.1rem;
      margin: 0 10px;
      text-transform: uppercase;
      transition: color 0.3s;
      font-weight: bold; /* Thêm in đậm */
    }
    .header .nav a:hover {
      color: #ffffff;
    }
    .carousel-item img {
      height: 400px;
      object-fit: cover;
      border-radius: 10px;
    }


    .sidebar h6 {
      color: #333;
      font-weight: bold;
      margin-bottom: 20px;
      text-align: center;
    }



    .product-item img {
      width: 100%;
      height: 220px;
      object-fit: cover;
    }

    .footer {
      background-color: #343a40;
      color: #ffffff;
      padding: 15px 0;
      text-align: center;
      font-size: 1rem;
      font-weight: bold;
      font-style: italic;
      border-top: 4px solid #ff6600;
    }
    .btn-spacing form {
      margin-right: 8px;
    }

    .btn-spacing form:last-child {
      margin-right: 0;
    }
    .site-title {
      font-family: 'Arial', sans-serif;
      font-size: 2rem;
      margin-bottom: 10px;
      color: #333;
      text-transform: uppercase;
    }
    .nav {
      margin-bottom: 10px;
    }

    .nav-link {
      font-size: 1rem;
      color: #007bff;
      text-decoration: none;
      margin: 0 15px;
      transition: color 0.3s;
    }

    .nav-link:hover {
      color: #ffffff;
    }

    .user-options {
      display: flex;
      justify-content: center;
      gap: 15px;
    }

    .option-link {
      font-size: 1rem;
      color: #343a40;
      text-decoration: none;
      transition: color 0.3s;
    }

    .option-link i {
      margin-right: 5px;
    }

    .option-link:hover {
      color: #ffffff;
    }
    .header-space {
      margin-top: 100px; /* Khoảng cách 100px từ đầu trang */
    }

  </style>
</head>
<body>
<div class="container-fluid">
  <header class="header text-center">
    <h1 class="site-title">Cửa Hàng Quần Áo</h1>
    <nav class="nav justify-content-center">
      <a href="/user" class="nav-link">Trang Chủ</a>
      <a href="/user/about.jsp" class="nav-link">Giới Thiệu</a>
      <a href="/user/news.jsp" class="nav-link">Tin Tức</a>
      <a href="/user/contact.jsp" class="nav-link">Liên Hệ</a>
    </nav>
    <div class="user-options">
      <c:choose>
        <c:when test="${not empty sessionScope.account}">
          <!-- Đã đăng nhập -->
          <a href="/user?action=cartGet" class="option-link"><i class="fas fa-shopping-cart"></i> Giỏ Hàng</a>
          <a href="/user?action=orderpadingGet" class="option-link"style="color: white"><i class="fas fa-box"></i> Đơn Hàng</a>
          <a href="/account.jsp" class="option-link" ><i class="fas fa-box"></i>Tài Khoản</a>
          <a href="/logout" class="option-link"><i class="fas fa-sign-out-alt"></i> Đăng Xuất</a>
        </c:when>
        <c:otherwise>
          <!-- Chưa đăng nhập -->
          <a href="/login.jsp" class="option-link"><i class="fas fa-user"></i> Đăng Nhập</a>
          <a href="/register.jsp" class="option-link"><i class="fas fa-user-plus"></i> Đăng Ký</a>
        </c:otherwise>
      </c:choose>
    </div>
  </header>
  <div class="content">
    <div class="container">
      <h2 class="text-center mb-4 header-space"><i class="fas fa-shopping-cart"></i> Đơn Hàng</h2>
      <div style="display: flex; gap: 10px;">
        <form action="user" method="get">
          <button type="submit" name="action" value="orderpadingGet" class="btn btn-outline-secondary">Chưa xác nhận</button>
        </form>
        <form action="user" method="get">
          <button type="submit" name="action" value="orderconfirmedGet" class="btn btn-outline-secondary">Đã xác nhận</button>
        </form>
      </div>
      <c:if test="${not empty orders}">
        <table class="table table-hover table-bordered">
          <thead class="table-dark">
          <tr>
            <th>Tên Người Đặt</th>
            <th>Số Điện Thoại</th>
            <th>Địa Chỉ</th>
            <th>Xã</th>
            <th>Huyện</th>
            <th>Tỉnh</th>
            <th>Tên Sản Phẩm</th>
            <th>Ảnh</th>
            <th>Kích Cỡ</th>
            <th>Số Lượng</th>
            <th>Giá</th>
            <th>Tổng</th>
            <th>Ngày Đặt</th>>
          </tr>
          </thead>
          <tbody>
          <c:set var="totalAmount" value="0" />
          <c:forEach var="o" items="${orders}">
            <tr>
              <td>${o.accountName}</td>
              <td>${o.phonenumber}</td>
              <td>${o.address}</td>
              <td>${o.communeName}</td>
              <td>${o.districtName}</td>
              <td>${o.provinceName}</td>
              <td>${o.productName}</td>
              <td>
                <img src="${pageContext.request.contextPath}/uploads/${o.productImage}" alt="${o.productImage}" style="width: 100px;"/>
              </td>
              <td>${o.sizeName}</td>
              <td>${o.quantity}</td>
              <td>${o.price}</td>
              <td>${o.total}</td>
              <td>${o.orderdate}</td>
              <c:set var="totalAmount" value="${totalAmount + o.total}" />
            </tr>
          </c:forEach>
          </tbody>
        </table>

        <!-- Hiển thị tổng thành tiền -->
        <div class="alert alert-info text-right">
          <strong>Thành Tiền:</strong> <span id="totalPrice">${totalAmount}</span> VNĐ
        </div>
      </c:if>

      <c:if test="${empty orders}">
        <div class="alert alert-warning" role="alert">
          Hiện tại bạn chưa có sản phẩm nào trong giỏ hàng.
        </div>
      </c:if>

      <div class="text-center">
        <a href="/user" class="btn btn-secondary">Tiếp Tục Mua Sắm</a>
      </div>
    </div>
  </div>



  <div class="row mb-3">
    <div class="col">
      <div class="d-flex align-items-center justify-content-between flex-wrap">
        <div class="button-group-container d-flex align-items-center justify-content-between flex-wrap">
        </div>
      </div>
    </div>
  </div>
</div>
</div>

<footer class="footer">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <p>&copy; 2024 Cửa Hàng Quần Áo. Tất cả quyền được bảo lưu. Chúng tôi cam kết cung cấp những sản phẩm chất lượng nhất và dịch vụ tốt nhất cho khách hàng của mình. Cảm ơn bạn đã chọn chúng tôi để làm đẹp cho tủ quần áo của bạn!</p>
        <p>Địa chỉ: 74 P. Đông Thiên, Vĩnh Hưng, Hoàng Mai, Hà Nội, Việt Nam</p>
        <p>Điện thoại: (0123) 456-789</p>
        <p>Email: <a href="#">shopquanao@gmail.com</a></p>
      </div>
      <div class="col-md-6">
        <h5>Liên hệ với chúng tôi</h5>
        <p>Để không bỏ lỡ các chương trình khuyến mãi, bạn hãy theo dõi chúng tôi trên các trang mạng xã hội:</p>
        <ul class="social-links">
          <li><a href="#" target="_blank">Facebook</a></li>
          <li><a href="#" target="_blank">YouTube</a></li>
          <li><a href="#" target="_blank">TikTok</a></li>
          <li><a href="#" target="_blank">Instagram</a></li>
          <li><a href="#" target="_blank">Twitter</a></li>
        </ul>
        <p>Chúng tôi luôn lắng nghe ý kiến đóng góp từ bạn để cải thiện dịch vụ. Nếu bạn có bất kỳ câu hỏi nào, xin vui lòng liên hệ với chúng tôi qua số điện thoại hoặc email ở trên.</p>
      </div>
    </div>
  </div>
</footer>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


