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
  <title>Cửa Hàng Quần Áo</title>
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #f1f1f1;
      margin: 0;
      padding: 0;
      padding-top: 150px; /* Thêm khoảng cách ở trên cùng để không bị che bởi header */
    }

    /* Header Styling */
    .header {
      background: linear-gradient(to right, #a6a6a6, #d9d9d9);
      color: #333;
      padding: 20px 0;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      background: linear-gradient(to right, #a6a6a6, #d9d9d9); /* Giữ màu nền */
      position: fixed; /* Đặt vị trí cố định */
      top: 0; /* Đặt ở trên cùng */
      left: 0; /* Đặt ở bên trái */
      right: 0; /* Đặt ở bên phải */
      z-index: 1000; /* Đặt độ ưu tiên cao để luôn nằm trên cùng */
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
      color: #ffffff; /* Thay đổi màu sắc khi hover */
    }

    /* Banner Styling */
    .carousel-item img {
      height: 400px;
      object-fit: cover;
      border-radius: 10px;
    }

    /* Sidebar */
    .sidebar {
      background-color: #fff;
      border-right: 1px solid #ddd;
      border-radius: 8px;
      padding: 20px;
    }

    .sidebar h6 {
      color: #333;
      font-weight: bold;
      margin-bottom: 20px;
      text-align: center;
    }

    .list-group-item {
      font-weight: 600;
      transition: background-color 0.2s;
    }

    .list-group-item:hover {
      background-color: #a6a6a6; /* Màu nền khi hover */
      color: #fff;
    }

    /* Product Card Styling */
    .product-item {
      background-color: #fff;
      border-radius: 15px;
      overflow: hidden;
      text-align: center;
      transition: transform 0.3s, box-shadow 0.3s;
    }

    .product-item:hover {
      transform: scale(1.05);
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    }

    .product-item img {
      width: 100%;
      height: 220px;
      object-fit: cover;
    }

    .product-name {
      font-size: 1.5rem;
      color: #333;
      margin-top: 10px;
      font-weight: bold;
    }

    .price {
      font-size: 1.0rem;
      color: red;
      margin-top: 5px;
      font-weight: bold;
    }
    .quantity {
      font-size: 0.7rem;
      color: #a6a6a6;
      margin-top: 5px;
      font-style: italic;
    }
    .btn-group .btn {
      background-color: #d9d9d9; /* Màu nền của nhóm nút */
      color: #333; /* Màu chữ */
      font-size: 0.9rem;
      transition: background-color 0.3s;
      display: inline-block;
    }

    .btn-group .btn:hover {
      background-color: #a6a6a6; /* Màu nền khi hover trong nhóm nút */
      color: #fff; /* Màu chữ khi hover trong nhóm nút */
    }
    .footer {
      background-color: #343a40; /* Màu nền footer */
      color: #ffffff; /* Màu chữ trắng */
      padding: 15px 0; /* Khoảng cách trên và dưới */
      text-align: center; /* Canh giữa nội dung */
      font-size: 1rem; /* Kích thước chữ */
      font-weight: bold; /* Chữ in đậm */
      font-style: italic; /* Chữ nghiêng */
      border-top: 4px solid #ff6600; /* Đường viền trên */
    }
    .btn-spacing form {
      margin-right: 8px;
    }

    .btn-spacing form:last-child {
      margin-right: 0; /* Không cần khoảng cách sau nút cuối cùng */
    }
    /* Tổng quan cho header */


    /* Tiêu đề chính */
    .site-title {
      font-family: 'Arial', sans-serif;
      font-size: 2rem;
      margin-bottom: 10px;
      color: #333;
      text-transform: uppercase;
    }

    /* Điều hướng */
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

    /* Phần tuỳ chọn người dùng */
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
      <a href="/user/contact.jsp" class="nav-link"style="color: white">Liên Hệ</a>
    </nav>
    <div class="user-options">
      <c:choose>
        <c:when test="${not empty sessionScope.account}">
          <!-- Đã đăng nhập -->
          <a href="/user?action=cartGet" class="option-link"><i class="fas fa-shopping-cart"></i> Giỏ Hàng</a>
          <a href="/user?action=orderpadingGet" class="option-link"><i class="fas fa-box"></i> Đơn Hàng</a>
          <a href="/account.jsp" class="option-link"><i class="fas fa-box"></i>Tài Khoản</a>
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
  <div id="carouselExampleIndicators" class="carousel slide mb-4" data-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="${pageContext.request.contextPath}/uploads/banner1.jpg" class="d-block w-100" alt="Banner 1">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath}/uploads/banner2.jpg" class="d-block w-100" alt="Banner 2">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath}/uploads/banner3.jpg" class="d-block w-100" alt="Banner 3">
      </div>
      <div class="carousel-item">
        <img src="${pageContext.request.contextPath}/uploads/banner4.jpg" class="d-block w-100" alt="Banner 4">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
        <div class="container mt-5">
          <h1 class="text-center mb-4">Thông Tin Liên Hệ</h1>
          <p class="text-center mb-4">
            Chúng tôi luôn sẵn sàng hỗ trợ bạn! Nếu bạn có bất kỳ câu hỏi hoặc yêu cầu nào, hãy liên hệ với chúng tôi qua các kênh bên dưới.
          </p>

          <ul class="list-group">
            <li class="list-group-item">
              <strong>📍 Địa chỉ:</strong> 74 P. Đông Thiên, Vĩnh Hưng, Hoàng Mai, Hà Nội, Việt Nam
            </li>
            <li class="list-group-item">
              <strong>📞 Số điện thoại:</strong>
              <a href="tel:+84868007353">+84868007353</a> (Tổng đài hỗ trợ 24/7)
            </li>
            <li class="list-group-item">
              <strong>📧 Email:</strong>
              <a href="#">shopquanao@gmail.com</a> <br>
              <em>(Chúng tôi sẽ phản hồi trong vòng 24 giờ)</em>
            </li>
            <li class="list-group-item">
              <strong>🕒 Giờ làm việc:</strong><br>
              Thứ Hai - Thứ Sáu: 8:00 - 18:00<br>
              Thứ Bảy: 9:00 - 16:00<br>
              Nghỉ Chủ Nhật và các ngày lễ lớn.
            </li>
            <li class="list-group-item">
              <strong>🌐 Website:</strong>
              <a href="#" target="_blank">shopquanao.com</a>
            </li>
            <li class="list-group-item">
              <strong>💬 Mạng xã hội:</strong><br>
              <a href="#" target="_blank">Facebook</a> <br>
              <a href="#" target="_blank">TikTok</a> <br>
              <a href="#" target="_blank">Instagram</a> <br>
              <a href="#" target="_blank">Zalo</a> (Hỗ trợ trực tuyến)
            </li>
            <li class="list-group-item">
              <strong>🚚 Địa điểm giao hàng:</strong> <br>
              Chúng tôi giao hàng toàn quốc, bao gồm cả các khu vực xa như miền núi và hải đảo.
            </li>
            <li class="list-group-item">
              <strong>🔎 Hỗ trợ khách hàng:</strong><br>
              - <strong>Chat trực tiếp:</strong> Truy cập website và nhấn vào biểu tượng chat góc dưới cùng.<br>
              - <strong>Hotline:</strong> +84 123 456 789 (Gọi nhanh nếu cần hỗ trợ gấp).<br>
              - <strong>Email:</strong> support@shopquanao.com (Dành cho khiếu nại & hoàn trả sản phẩm).
            </li>
          </ul>

          <div class="mt-4">
            <h2 class="text-center mb-3">Bản Đồ</h2>
            <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.6187125555547!2d105.867814!3d21.0048771!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135aceb90904c95%3A0x60f23b4af0531e9e!2zNzQgxJDhu5NuZyBUaGnDqm4sIFBoxrDhu51uZyBWaW5oIEjDsm5nLCBRdeG6rW4gSGFpIELhuqF0IFRydW5nLCBUaOG7pyBIw6AgTuG7mWk!5e0!3m2!1svi!2s!4v1697612004123"
                    width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">
            </iframe>
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

