package com.techouts.servlets;

import java.io.IOException;
import java.util.List;

import com.techouts.dao.ProductDAO;
import com.techouts.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String category = req.getParameter("category");
        String search = req.getParameter("search");

        List<Product> products;

        if (search != null && !search.trim().isEmpty()) {
            products = productDAO.searchByName(search.trim());
            req.setAttribute("searchQuery", search);
        } else if (category != null && !category.trim().isEmpty()) {
            products = productDAO.findByCategory(category);
            req.setAttribute("selectedCategory", category);
        } else {
            products = productDAO.findAll();
        }

        List<String> categories = productDAO.getAllCategories();

        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}