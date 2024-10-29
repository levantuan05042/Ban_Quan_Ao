package com.example.qlquanao.User;

import com.example.qlquanao.DAO.CartDAO;
import com.example.qlquanao.DAO.ProductDAO;
import com.example.qlquanao.Model.*;
import com.example.qlquanao.Service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user")
public class UserproductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CartService cartService = new CartService();
    private OrderService orderService = new OrderService();
    private ProductCategorySevice productCategorySevice = new ProductCategorySevice();
    private ProvinceService provinceService = new ProvinceService();
    private DistrictService districtService = new DistrictService();
    private CommuneService communeService = new CommuneService();
    private SizeService sizeService = new SizeService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "findByGetProductCategory":
                findByProductCategory(request, response);
                break;
            case "lastest":
                displayLastest(request, response); // Gọi hàm hiển thị sản phẩm bán chạy
                break;
            case "bestseller":
                displayBestsellers(request, response); // Gọi hàm hiển thị sản phẩm bán chạy
                break;
            case "searchGet":
                SEARCH(request, response);
                break;
            case "searchprice":
                SEARCHPRICE(request, response);
                break;
            case "price_increaset":
                price_increaset(request, response);
                break;
            case "price_reduction":
                price_reduction(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            case "cartGet":
                cartGet(request,response);
                break;
            case "orderpadingGet":
                orderpadingGet(request,response);
                break;
            case "updateGet":
                updateGetcart(request,response);
                break;
            case "loadDistricts":
                loadDistricts(request, response);
                break;
            case "loadCommunes":
                loadCommunes(request, response);
                break;
            case "deleteGet":
                DELETE(request, response);
                break;
            case "deleteGetCart":
                DELETECart(request, response);
                break;
            case "orderconfirmedGet":
                orderconfirmedGet(request,response);
                break;
                default:
                displayProductsAndCategories(request, response);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "createPost":
                createPostOrder(request,response);
                break;
            case "add":
                addToCart(request, response); // Xử lý thêm vào giỏ hàng
                break;
        }
    }
    private void displayProductsAndCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách sản phẩm và danh mục
        ArrayList<Product> products = productService.FINDALL();
        ArrayList<ProductCategory> categories = productCategorySevice.FINDALL();
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void displayBestsellers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> bestsellers = productService.bestseller();
        ArrayList<ProductCategory> categories = productCategorySevice.FINDALL();
        request.setAttribute("products", bestsellers);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void displayLastest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> lastest = productService.lastest();
        ArrayList<ProductCategory> categories = productCategorySevice.FINDALL();
        request.setAttribute("products", lastest);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void findByProductCategory (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        request.setAttribute("products", productService.FINDBYPRODUCTCATEGORY(categoryid));
        request.setAttribute("categories", productCategorySevice.FINDALL());
        requestDispatcher.forward(request, response);
    }
    private void SEARCH(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        request.setAttribute("products", productService.SEARCHBYNAME2(searchQuery));
        request.setAttribute("categories", productCategorySevice.FINDALL());
        request.setAttribute("searchQuery", searchQuery);
        requestDispatcher.forward(request, response);
    }
    private void SEARCHPRICE(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigDecimal minPrice = new BigDecimal(request.getParameter("minPrice"));
        BigDecimal maxPrice = new BigDecimal(request.getParameter("maxPrice"));
        System.out.println("Min Price: " + minPrice + ", Max Price: " + maxPrice);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        request.setAttribute("products", productService.SEARCHBYPRICE(minPrice,maxPrice));
        request.setAttribute("categories", productCategorySevice.FINDALL());
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        requestDispatcher.forward(request, response);
    }
    private void price_increaset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> lastest = productService.price_increase();
        ArrayList<ProductCategory> categories = productCategorySevice.FINDALL();
        request.setAttribute("products", lastest);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void price_reduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> lastest = productService.price_reduction();
        ArrayList<ProductCategory> categories = productCategorySevice.FINDALL();
        request.setAttribute("products", lastest);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Đảm bảo tham số "id" cũng đã được kiểm tra.

        String productIdParam = request.getParameter("productId");
        if (productIdParam == null || productIdParam.isEmpty()) {
            // Xử lý lỗi khi productId không hợp lệ
            request.setAttribute("errorMessage", "Mã sản phẩm không hợp lệ!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return; // Dừng xử lý nếu có lỗi
        }

        int productId;
        try {
            productId = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu không thể chuyển đổi
            request.setAttribute("errorMessage", "Mã sản phẩm không hợp lệ!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return; // Dừng xử lý nếu có lỗi
        }

        // Gửi thông tin sản phẩm theo ID
        request.setAttribute("products", productService.ProductDetailById(id));

        // Gửi danh sách kích thước cho sản phẩm
        request.setAttribute("sizes", sizeService.getAllSize(productId));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/product_detail.jsp");
        requestDispatcher.forward(request, response);
    }


    private void cartGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            CartService cartService = new CartService();
            ArrayList<Cart> carts = cartService.findCartsByUsername(username);
            request.setAttribute("carts", carts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/cart.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    private void orderpadingGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            OrderService orderService = new OrderService();
            ArrayList<Order> orders = orderService.findOrdersByAccountId(username);
            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/order.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    private void updateGetcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = provinceService.getAllProvinces();
        request.setAttribute("provinces", provinces);
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("carts", cartService.FINDBYID(id));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/update_cart.jsp");
        requestDispatcher.forward(request, response);
    }

    private void loadDistricts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int provinceId = Integer.parseInt(request.getParameter("provinceId"));
        List<District> districts = districtService.getDistrictsByProvinceId(provinceId);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<option value=''>Select District</option>");
        for (District district : districts) {
            out.println("<option value='" + district.getId() + "'>" + district.getDistrictName() + "</option>");
        }
        out.close();
    }

    private void loadCommunes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int districtId = Integer.parseInt(request.getParameter("districtId"));
        List<Commune> communes = communeService.getCommunesByDistrictId(districtId);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<option value=''>Select Commune</option>");
        for (Commune commune : communes) {
            out.println("<option value='" + commune.getId() + "'>" + commune.getCommuneName() + "</option>");
        }
        out.close();
    }
    private void createPostOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int cartId = Integer.parseInt(request.getParameter("cartId")); // Lấy cartId từ request
            int province = Integer.parseInt(request.getParameter("province"));
            int district = Integer.parseInt(request.getParameter("district"));
            int commune = Integer.parseInt(request.getParameter("commune"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            int productId = Integer.parseInt(request.getParameter("productId"));
            int sizeID = Integer.parseInt(request.getParameter("sizeId"));
            String productimage = request.getParameter("productimage");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            HttpSession session = request.getSession();
            Integer accountId = (Integer) session.getAttribute("userId");
            if (accountId == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            Order order = new Order(accountId, province, district, commune, address, phoneNumber, productId, sizeID, productimage, quantity, price);
            orderService.insertOrder(order);
            if (cartId > 0) {
                cartService.DELETE2(cartId);
            }
            response.sendRedirect("/user?action=orderpadingGet");
    }


    private void DELETE(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.DELETE2(id);
        response.sendRedirect("/user?action=orderpadingGet");
    }
    private void DELETECart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cartService.DELETE2(id);
        response.sendRedirect("/user?action=cartGet");
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int sizeId = Integer.parseInt(request.getParameter("sizeId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));

        // Lấy userId từ phiên
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Kiểm tra xem userId có null không
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            return;
        }

        // Tạo đối tượng Cart
        Cart cart = new Cart(userId,productId, sizeId, quantity, price);
        // Chèn vào giỏ hàng
        CartDAO cartDAO = new CartDAO();
        cartDAO.insertCart(cart);

        response.sendRedirect("/user?action=cartGet"); // Chuyển hướng đến trang giỏ hàng
    }
    private void orderconfirmedGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            OrderService orderService = new OrderService();
            ArrayList<Order> orders = orderService.findOrdersByAccountId2(username);
            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/order_confirmed.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
