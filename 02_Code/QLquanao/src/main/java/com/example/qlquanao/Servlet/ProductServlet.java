package com.example.qlquanao.Servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.qlquanao.Model.Product;
import com.example.qlquanao.Model.ProductCategory;
import com.example.qlquanao.Model.Supplier;
import com.example.qlquanao.Service.ProductService;
import com.example.qlquanao.Service.ProductCategorySevice;
import com.example.qlquanao.Service.SupplierService;

@WebServlet("/products")
@MultipartConfig
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private ProductCategorySevice productCategorySevice = new ProductCategorySevice();
    private SupplierService supplierService = new SupplierService();

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
            case "createGet":
                createGetProduct(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            case "updateGetproduct":
                updateGetProduct(request,response);
                break;
            default:
                displayAllProduct(request, response);
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
                createPostProduct(request, response);
                break;
            case "updatePost":
                updatePostProduct(request, response);
                break;
        }
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list_product.jsp");
        request.setAttribute("products", productService.FINDALL());
        requestDispatcher.forward(request, response);
    }

    private void createGetProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create_product.jsp");
        request.setAttribute("productCategories", productCategorySevice.FINDALL());
        request.setAttribute("suppliers", supplierService.getAllSuppliers());
        requestDispatcher.forward(request, response);
    }

    private void createPostProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productname = request.getParameter("productname");
        String priceStr = request.getParameter("price");
        BigDecimal price = null;
        if (priceStr != null && !priceStr.isEmpty()) {
            try {
                price = new BigDecimal(priceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Giá sản phẩm không hợp lệ.");
                request.getRequestDispatcher("product/create_product.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "Giá sản phẩm không được để trống.");
            request.getRequestDispatcher("product/create_product.jsp").forward(request, response);
            return;
        }

//        int supplieryid = Integer.parseInt(request.getParameter("supplierId"));
//        Supplier supplier = supplierService.findById(supplieryid);

        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
        int supplierId = Integer.parseInt(request.getParameter("supplierId")); // New line
        Supplier supplier = supplierService.findById(supplierId);

        Part filePart = request.getPart("productimage");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("/") + "uploads";
        File uploadsDir = new File(uploadPath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
        filePart.write(uploadPath + File.separator + fileName);
        String description = request.getParameter("description");
        Product newProduct = new Product(productname, price, productCategory, supplier, fileName, description);
        productService.CREATEPRODUCT(newProduct);
        response.sendRedirect(request.getContextPath() + "/sizes?action=createGetSize&productid=" + newProduct.getId());
    }

    private void findByProductCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list_product.jsp");
        request.setAttribute("products", productService.FINDBYPRODUCTCATEGORY(categoryid));
        requestDispatcher.forward(request, response);
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Product product = productService.findByProductName(productName);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/product_detail.jsp");
        dispatcher.forward(request, response);
    }
    private void updateGetProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        request.setAttribute("productCategories", productCategorySevice.FINDALL());
        request.setAttribute("suppliers", supplierService.getAllSuppliers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/update_product.jsp");
        dispatcher.forward(request, response);
    }
        private void updatePostProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productname = request.getParameter("productname");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        String productimage = request.getParameter("productimage");
        String description = request.getParameter("description");

        ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
        Supplier supplier = supplierService.findById(supplierId);
        Product product = new Product(id, productname, price, productCategory, supplier, productimage, description, null);

        productService.updateProduct(product);
        response.sendRedirect("products?action=edit&id=" + id + "&success=true");

    }
}