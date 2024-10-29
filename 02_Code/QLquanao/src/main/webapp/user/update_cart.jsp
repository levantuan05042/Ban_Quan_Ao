<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập Nhật Danh Mục Sản Phẩm</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
        }
        .content {
            margin-top: 30px;
            padding: 30px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #343a40;
            font-weight: bold;
        }
        .table th, .table td {
            vertical-align: middle; /* Giữa cột */
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
    </style>
    <script>
        $(document).ready(function () {
            $('#province').change(function () {
                var provinceId = $(this).val();
                $.ajax({
                    url: 'user?action=loadDistricts',
                    type: 'GET',
                    data: {provinceId: provinceId},
                    success: function (data) {
                        $('#district').html(data);
                        $('#commune').html("<option value=''>Chọn Xã/Phường</option>"); // Reset commune options
                    }
                });
            });

            $('#district').change(function () {
                var districtId = $(this).val();
                $.ajax({
                    url: 'user?action=loadCommunes',
                    type: 'GET',
                    data: {districtId: districtId},
                    success: function (data) {
                        $('#commune').html(data);
                    }
                });
            });
        });

        function updateTotal(price, quantity) {
            // Tính tổng
            const total = price * quantity;
            // Cập nhật tổng vào ô tổng
            document.getElementById("totalAmount").innerText = total.toFixed(2);
        }
    </script>
</head>
<body>
<div class="content">
    <div class="container">
        <form action="/user?action=createPost" method="post">
            <h2 class="text-center mb-4"><i class="fas fa-shopping-cart"></i> Xác Nhận Địa Chỉ Giao Hàng</h2>

            <div class="form-group">
                <label for="province" class="form-label">Tỉnh/Thành phố</label>
                <select name="province" id="province" class="form-control" required>
                    <option value="">Chọn Tỉnh</option>
                    <c:forEach var="province" items="${provinces}">
                        <option value="${province.id}">${province.provinceName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="district" class="form-label">Quận/Huyện</label>
                <select name="district" id="district" class="form-control" required>
                    <option value="">Chọn Quận/Huyện</option>
                </select>
            </div>
            <div class="form-group">
                <label for="commune" class="form-label">Xã/Phường</label>
                <select name="commune" id="commune" class="form-control" required>
                    <option value="">Chọn Xã/Phường</option>
                </select>
            </div>
            <div class="form-group">
                <label for="address" class="form-label">Địa chỉ</label>
                <input type="text" class="form-control" id="address" name="address" required />
            </div>
            <div class="form-group">
                <label for="phoneNumber" class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required />
            </div>
            <div class="form-group">
                <input type="hidden" name="cartId" value="${carts.id}"/> <!-- Trường ẩn để lưu ID của sản phẩm trong giỏ hàng -->
                <input type="hidden" class="form-control" name="productId" required value="${carts.productId}"/>
                <input type="hidden" class="form-control" name="sizeId" required value="${carts.sizeId}"/>
                <input type="hidden" class="form-control" name="productimage" required value="${carts.productimage}"/>
                <label class="form-label">Số lượng</label>
                <input type="number" class="form-control" name="quantity" required value="${carts.quantity}" onchange="updateTotal(${carts.price}, this.value)"/>
                <input type="hidden" class="form-control" name="price" required value="${carts.price}"/>
            </div>

            <table class="table table-hover table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>Tên Sản Phẩm</th>
                    <th>Hình Ảnh</th>
                    <th>Kích Thước</th>
                    <th>Giá</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${carts.productName}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/uploads/${carts.productimage}" alt="${carts.productimage}" style="width: 100px;"/>
                    </td>
                    <td>${carts.sizeName}</td>
                    <td>${carts.price}</td>
                </tr>
                </tbody>
            </table>

            <div class="mb-3">
                <button type="submit" class="btn btn-secondary"><i class="fas fa-save"></i> Thực hiện thanh toán: <strong id="totalAmount">${carts.total}</strong> VNĐ</button>
                <a href="/user" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Quay Về Danh Mục</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
