<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
  <title>Chi Tiết Sản Phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" crossorigin="anonymous" />
  <style>
    body {
      background-color: #f8f9fa;
      font-family: 'Arial', sans-serif;
    }
    .content {
      padding: 30px;
    }
    .container {
      max-width: 1200px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
      padding: 30px;
      margin-top: 30px;
    }
    .product-image {
      width: 100%;
      border-radius: 10px;
    }
    .product-details h2 {
      font-size: 36px;
      font-weight: bold;
      color: #333;
    }
    .product-details p.price {
      font-size: 28px;
      color: red;
      font-weight: bold;
    }
    .product-details p.description {
      font-size: 18px;
      color: #666;
    }
    .policies {
      margin-top: 20px;
      font-size: 16px;
      color: #555;
    }
    .policies i {
      color: #5cb85c;
      margin-right: 5px;
    }
    .size-quantity-list {
      margin-top: 20px;
    }
    .size-item {
      display: flex;
      justify-content: space-between;
      border-bottom: 1px solid #ddd;
      padding: 10px 0;
    }
    .btn-custom {
      width: 100%;
      margin-top: 10px;
      padding: 15px;
      font-size: 18px;
    }
    .add-to-cart {
      background-color: #28a745;
      color: white;
    }
    .add-to-cart:hover {
      background-color: #218838;
    }
  </style>
</head>
<body>
<div class="content">
  <div class="container">
    <c:if test="${not empty products}">
      <c:set var="product" value="${products[0]}" />
      <div class="row">
        <div class="col-md-6">
          <img src="${pageContext.request.contextPath}/uploads/${product.productimage}" alt="${product.productname}" class="product-image">
        </div>
        <div class="col-md-6">
          <div class="product-details">
            <h2>${product.productname}</h2>
            <p class="price">${product.price} VND</p>
            <p class="description">${product.description}</p>
            <div class="policies">
              <p><i class="fas fa-truck"></i> Freeship</p>
              <p><i class="fas fa-shield-alt"></i> Bảo hiểm thời trang</p>
              <p><i class="fas fa-exchange-alt"></i> Đổi trả trong vòng 30 ngày</p>
            </div>
          </div>
          <div class="size-quantity-list">
            <h4>Kích Thước & Số Lượng</h4>
            <c:forEach var="pro" items="${products}">
              <c:if test="${pro.quantity > 0}">
                <div class="size-item">
                  <span>Kích Thước: <strong>${pro.sizeName}</strong></span>
                  <span>Số Lượng: <strong>${pro.quantity}</strong></span>
                </div>
              </c:if>
            </c:forEach>
            <form method="post" action="user?action=add">
              <div class="mb-3">
                <input type="hidden" name="productId" value="${product.id}" required>
                <input type="hidden" name="price" value="${product.price}" required>
                <label for="quantity" class="form-label">Số Lượng</label>
                <input type="number" class="form-control" name="quantity" id="quantity" min="1" value="1" required>
              </div>
              <div class="mb-3">
                <label for="sizeId" class="form-label">Kích Thước</label>
                <select class="form-select" name="sizeId" id="sizeId" required>
                  <option value="">--- Chọn kích thước ---</option>
                  <c:forEach items="${sizes}" var="size">
                    <option value="${size.id}">${size.sizename}</option>
                  </c:forEach>
                </select>
              </div>
              <button type="submit" class="btn btn-custom add-to-cart">
                <i class="fas fa-cart-plus"></i> Thêm Vào Giỏ
              </button>
            </form>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${empty products}">
      <p class="text-danger text-center">Sản phẩm không tồn tại!</p>
    </c:if>
  </div>
</div>
</body>
</html>
