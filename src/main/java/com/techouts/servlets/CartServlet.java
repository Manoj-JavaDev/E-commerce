package com.techouts.servlets;

import com.techouts.dao.CartDAO;
import com.techouts.dao.ProductDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.Product;
import com.techouts.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/addToCart")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();
        long productId = Long.parseLong(request.getParameter("productId"));
        Cart cart = user.getCart();
        Product product = productDAO.findById(productId);

        cartDAO.addItemToCart(cart, product);

        request.getRequestDispatcher("displayCart").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        long productId = Long.parseLong(request.getParameter("product"));

        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();

        Cart cart = user.getCart();
        Product product = productDAO.findById(productId);

        cartDAO.addItemToCart(cart, product);

        System.out.println("Successfully added product to cart");

        response.sendRedirect(request.getContextPath() + "/products");
    }
}

