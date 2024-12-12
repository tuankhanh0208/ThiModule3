package controller;

import entity.Category;
import entity.Product;
import service.IProductService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "productController", value = "/products")
public class ProductController extends HttpServlet {
    private final IProductService<Product> productService = new ProductService();

    public ProductController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "add":
                showAdd(request, response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            case "delete":
                showDelete(request,response);
                break;
            case "search":
                showSearch(request,response);
                break;
            default:
                showHome(request, response);
                break;
        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        double price = Double.parseDouble(request.getParameter("price"));
        List<Product> products = productService.findByName(keyword);
//        List<Product> products1 = productService.findByPrice(price);
        request.setAttribute("list", products);
//        request.setAttribute("list", products1);
        request.getRequestDispatcher("home.jsp").forward(request, response);
        System.out.println(price);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/products");
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categoriesList = productService.getAllCategory();
        request.setAttribute("categoriesList", categoriesList);
        request.setAttribute("product",product);
        request.getRequestDispatcher("style/page/editProduct/edit.jsp").forward(request,response);
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoriesList = productService.getAllCategory();
        request.setAttribute("categoriesList", categoriesList);
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request,response);
                break;
            default:
//                showHome(request,response);
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryMethod = Integer.parseInt(request.getParameter("categoryMethod"));
        Category category = new Category(categoryMethod);
        Product product = new Product(name, price, quantity, color, description, category);
        productService.update(id,product);
        response.sendRedirect("/products");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String categoryMethodStr = request.getParameter("categoryMethod");

        boolean hasError = false;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Tên sản phẩm không được để trống.");
            hasError = true;
        }

        double price = 0;
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("priceError", "Giá sản phẩm không được để trống.");
            hasError = true;
        } else {
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 10000000) {
                    request.setAttribute("priceError", "Giá sản phẩm phải lớn hơn 10.000.000 VNĐ.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("priceError", "Giá sản phẩm phải là số hợp lệ.");
                hasError = true;
            }
        }

        int quantity = 0;
        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            request.setAttribute("quantityError", "Số lượng không được để trống.");
            hasError = true;
        } else {
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    request.setAttribute("quantityError", "Số lượng phải là số nguyên dương.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("quantityError", "Số lượng phải là số nguyên hợp lệ.");
                hasError = true;
            }
        }

        if (color == null || (!color.equals("Đỏ") && !color.equals("Xanh") && !color.equals("Đen") && !color.equals("Trắng") && !color.equals("Vàng"))) {
            request.setAttribute("colorError", "Màu sắc không hợp lệ. Vui lòng chọn Đỏ, Xanh, Đen, Trắng hoặc Vàng.");
            hasError = true;
        }

        int categoryMethod = 0;
        if (categoryMethodStr == null || categoryMethodStr.trim().isEmpty()) {
            request.setAttribute("categoryError", "Danh mục không được để trống.");
            hasError = true;
        } else {
            try {
                categoryMethod = Integer.parseInt(categoryMethodStr);
            } catch (NumberFormatException e) {
                request.setAttribute("categoryError", "Danh mục không hợp lệ.");
                hasError = true;
            }
        }

        if (hasError) {
            request.getRequestDispatcher("add.jsp").forward(request, response);
            List<Category> categoriesList = productService.getAllCategory();
            request.setAttribute("categoriesList", categoriesList);
            return;
        }

        Category category = new Category(categoryMethod);
        Product product = new Product(name, price, quantity, color, description, category);
        productService.add(product);
        request.setAttribute("text","Thêm sản phẩm thành công");
        response.sendRedirect("/products");
    }

}