package com.example.qlquanao.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.example.qlquanao.Model.Supplier;
import com.example.qlquanao.Model.Province;
import com.example.qlquanao.Model.District;
import com.example.qlquanao.Model.Commune;
import com.example.qlquanao.Service.SupplierService;
import com.example.qlquanao.Service.ProvinceService;
import com.example.qlquanao.Service.DistrictService;
import com.example.qlquanao.Service.CommuneService;

@WebServlet(name = "SupplierServlet", value = "/supplier")
public class SupplierServlet extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    private ProvinceService provinceService = new ProvinceService();
    private DistrictService districtService = new DistrictService();
    private CommuneService communeService = new CommuneService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createGet":
                createGetSupplier(request, response);
                break;
            case "updateGet":
                updateGetSupplier(request, response);
                break;
            case "loadDistricts":
                loadDistricts(request, response);
                break;
            case "loadCommunes":
                loadCommunes(request, response);
                break;
            case "view":  // Thêm case này
                viewSupplier(request, response);
                break;
            default:
                displayAllSuppliers(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createPost":
                createPostSupplier(request, response);
                break;
            case "updatePost":
                updatePostSupplier(request, response);
                break;

        }
    }

    private void displayAllSuppliers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplier/list_supplier.jsp");
        request.setAttribute("suppliers", supplierService.getAllSuppliers());
        requestDispatcher.forward(request, response);
    }

    private void createGetSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = provinceService.getAllProvinces();
        request.setAttribute("provinces", provinces);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplier/create_supplier.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPostSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierName = request.getParameter("supplierName");
        int province = Integer.parseInt(request.getParameter("province"));
        int district = Integer.parseInt(request.getParameter("district"));
        int commune = Integer.parseInt(request.getParameter("commune"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        Supplier supplier = new Supplier(0, supplierName, address, phoneNumber, email, province, "", district, "", commune, "");
        supplierService.addSupplier(supplier);
        response.sendRedirect("supplier");
    }

    private void updateGetSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Supplier supplier = supplierService.findById(id);
        List<Province> provinces = provinceService.getAllProvinces();
        List<District> districts = districtService.getDistrictsByProvinceId(supplier.getProvinceId());
        List<Commune> communes = communeService.getCommunesByDistrictId(supplier.getDistrictId());
        request.setAttribute("supplier", supplier);
        request.setAttribute("provinces", provinces);
        request.setAttribute("districts", districts);
        request.setAttribute("communes", communes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplier/update_supplier.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updatePostSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String supplierName = request.getParameter("supplierName");
        int province = Integer.parseInt(request.getParameter("province"));
        int district = Integer.parseInt(request.getParameter("district"));
        int commune = Integer.parseInt(request.getParameter("commune"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        Supplier supplier = new Supplier(id, supplierName, address, phoneNumber, email, province, "", district, "", commune, "");
        supplierService.updateSupplier(supplier);
        response.sendRedirect("supplier");
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
    private void viewSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Supplier supplier = supplierService.findById(id);
        request.setAttribute("supplier", supplier);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplier/supplier_detail.jsp");
        requestDispatcher.forward(request, response);
    }
}
