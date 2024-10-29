package com.example.qlquanao.DAO;

import com.example.qlquanao.Model.Product;
import com.example.qlquanao.Model.ProductCategory;
import com.example.qlquanao.Model.Supplier;
import com.example.qlquanao.Service.ProductCategorySevice;
import com.example.qlquanao.Service.SupplierService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProductDAO {
    private ProductCategorySevice productCategorySevice = new ProductCategorySevice();
    private SupplierService supplierService = new SupplierService();

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT " +
                    "    Product.id, " +
                    "    Product.productname, " +
                    "    Product.price, " +
                    "    Product.categoryid, " +
                    "    Product.supplierId, " +
                    "    Product.productimage, " +
                    "    Product.description, " +
                    "    Product.entrydate, " +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold " +
                    "FROM " +
                    "    Product " +
                    "LEFT JOIN " +
                    "    SalesReport ON Product.id = SalesReport.productId " +
                    "GROUP BY " +
                    "    Product.id, Product.productname, Product.price, Product.categoryid, Product.supplierId, Product.productimage, Product.description, Product.entrydate " +
                    "LIMIT 10;";


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productname = resultSet.getString("productname");
                BigDecimal price = resultSet.getBigDecimal("price");
                int categoryid = resultSet.getInt("categoryid");
                ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
                int supplierId = resultSet.getInt("supplierId");
                Supplier supplier = supplierService.findById(supplierId);
                String productimage = resultSet.getString("productimage");
                String description = resultSet.getString("description");
                LocalDateTime entrydate = resultSet.getTimestamp("entrydate").toLocalDateTime();
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");

                // Tạo sản phẩm mới với tổng số lượng đã bán
                products.add(new Product(id, productname, price, productCategory, supplier, productimage, description, entrydate, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }


    public ArrayList<Product> findByProductCategory(int categoryid) {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Product where categoryid = ?;");
            preparedStatement.setInt(1, categoryid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idDB = resultSet.getInt("id");
                String productname = resultSet.getString("productname");
                BigDecimal price = resultSet.getBigDecimal("price");
                ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
                String productimage = resultSet.getString("productimage");
                String description = resultSet.getString("description");
                LocalDateTime entrydate = resultSet.getTimestamp("entrydate").toLocalDateTime();
                products.add(new Product(idDB, productname, price, productCategory, productimage, description, entrydate));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
        return products;
    }

    public void createProduct(Product product) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Product (productname, price, categoryid, supplierId, productimage, description) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductname());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getProductCategory().getId());
            preparedStatement.setInt(4,product.getSupplier().getId());
            preparedStatement.setString(5, product.getProductimage());
            preparedStatement.setString(6, product.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
    }
    public Product findByProductName(String productName) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE productname = ?";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, productName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int categoryid = rs.getInt("categoryid");
                    ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
                    int supplier = rs.getInt("supplierId");
                    Supplier supplier1 = supplierService.findById(supplier);
                    product = new Product(
                            rs.getInt("id"),
                            rs.getString("productname"),
                            rs.getBigDecimal("price"),
                            productCategory,
                            supplier1,
                            rs.getString("productimage"),
                            rs.getString("description"),
                            rs.getTimestamp("entrydate").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public void updateProduct(Product product) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Product SET productname = ?, price = ?, categoryid = ?, supplierId = ?, productimage = ?, description = ? WHERE id = ?"
            );
            preparedStatement.setString(1, product.getProductname());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getProductCategory().getId());
            preparedStatement.setInt(4, product.getSupplier().getId());
            preparedStatement.setString(5, product.getProductimage());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Có lỗi:" + e.getMessage());
        }
    }
    public Product findById(int id) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int categoryid = rs.getInt("categoryid");
                    ProductCategory productCategory = productCategorySevice.FINDBYID(categoryid);
                    int supplierId = rs.getInt("supplierId");
                    Supplier supplier = supplierService.findById(supplierId);
                    product = new Product(
                            rs.getInt("id"),
                            rs.getString("productname"),
                            rs.getBigDecimal("price"),
                            productCategory,
                            supplier,
                            rs.getString("productimage"),
                            rs.getString("description"),
                            rs.getTimestamp("entrydate").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public ArrayList<Product> bestseller() {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price\n" +
                    "ORDER BY \n" +
                    "    total_quantity_sold DESC -- Sắp xếp từ lớn đến bé theo số lượng bán\n" +
                    "LIMIT 10; -- Giới hạn kết quả trả về chỉ 10 sản phẩm\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");

                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }
    public ArrayList<Product> latest() {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    Product.entrydate AS entry_date,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price, Product.entrydate\n" +
                    "ORDER BY \n" +
                    "    entry_date DESC \n" +
                    "LIMIT 10;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");
                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }
    public ArrayList<Product> SEARCHBYNAME(String name) {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    Product.entrydate AS entry_date,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "WHERE \n" +
                    "    Product.productname LIKE ?\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price, Product.entrydate;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");
                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }
    public ArrayList<Product> SEARCHBYPRICE(BigDecimal minPrice, BigDecimal maxPrice) {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    Product.entrydate AS entry_date,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "WHERE \n" +
                    "    Product.price BETWEEN ? AND ?\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price, Product.entrydate;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, minPrice);  // Gán giá trị cho giá tối thiểu
            preparedStatement.setBigDecimal(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");
                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }
    public ArrayList<Product> price_increase() {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "    SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    Product.entrydate AS entry_date,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price, Product.entrydate\n" +
                    "ORDER BY \n" +
                    "    product_price ASC ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");
                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }

    public ArrayList<Product> price_reduction() {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "    SELECT \n" +
                    "    Product.productname AS product_name,\n" +
                    "    Product.productimage AS product_image,\n" +
                    "    Product.price AS product_price,\n" +
                    "    Product.entrydate AS entry_date,\n" +
                    "    COALESCE(SUM(SalesReport.quantitySold), 0) AS total_quantity_sold\n" +
                    "FROM \n" +
                    "    Product\n" +
                    "LEFT JOIN \n" +
                    "    SalesReport ON Product.id = SalesReport.productId\n" +
                    "GROUP BY \n" +
                    "    Product.id, Product.productname, Product.productimage, Product.price, Product.entrydate\n" +
                    "ORDER BY \n" +
                    "    product_price DESC  ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productname = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("product_price");
                String productimage = resultSet.getString("product_image");
                int totalQuantitySold = resultSet.getInt("total_quantity_sold");
                products.add(new Product(productname, price, productimage, totalQuantitySold));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        }
        return products;
    }
    public ArrayList<Product> ProductDetailById(int id) {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            // Truy vấn lấy sản phẩm và tổng số lượng đã bán
            String query = "SELECT\n" +
                    "    p.id,\n" +
                    "    p.productname AS 'productname',\n" +
                    "    p.price AS 'price',\n" +
                    "    p.productimage AS 'productimage',\n" +
                    "    p.description, " +
                    "    s.sizename AS 'sizename',\n" +
                    "    s.quantity AS 'quantity'\n" +
                    "FROM \n" +
                    "    Product p\n" +
                    "LEFT JOIN \n" +
                    "    Size s ON p.id = s.productId\n" +
                    "WHERE \n" +
                    "    p.id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); // Đặt giá trị ID vào PreparedStatement
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDB = resultSet.getInt("id");
                String productname = resultSet.getString("productname");
                BigDecimal price = resultSet.getBigDecimal("price");
                String productimage = resultSet.getString("productimage");
                String description = resultSet.getString("description");
                String sizename = resultSet.getString("sizename");
                int quantity = resultSet.getInt("quantity");
                products.add(new Product(idDB, productname, price, productimage,description, sizename, quantity));
            }
        } catch (Exception e) {
            System.out.println("Có lỗi: " + e.getMessage());
        } finally {
            // Đóng kết nối ở đây nếu cần
        }
        return products;
    }

}
