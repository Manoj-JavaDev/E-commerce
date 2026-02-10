package com.techouts.servlets;

import java.io.IOException;

import com.techouts.dao.ProductDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        // Get or create cart
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            switch (action) {
                case "add":
                    addToCart(req, cart);
                    resp.sendRedirect(req.getContextPath() + "/products?added=true");
                    break;

                case "update":
                    updateCart(req, cart);
                    resp.sendRedirect(req.getContextPath() + "/cart");
                    break;

                case "remove":
                    removeFromCart(req, cart);
                    resp.sendRedirect(req.getContextPath() + "/cart");
                    break;

                case "clear":
                    cart.clear();
                    resp.sendRedirect(req.getContextPath() + "/cart");
                    break;

                default:
                    resp.sendRedirect(req.getContextPath() + "/cart");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/cart?error=true");
        }
    }

    private void addToCart(HttpServletRequest req, Cart cart) {
        Long productId = Long.parseLong(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = productDAO.findById(productId);
        if (product != null && product.isInStock()) {
            cart.addItem(product, quantity);
        }
    }

    private void updateCart(HttpServletRequest req, Cart cart) {
        Long productId = Long.parseLong(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        cart.updateQuantity(productId, quantity);
    }

    private void removeFromCart(HttpServletRequest req, Cart cart) {
        Long productId = Long.parseLong(req.getParameter("productId"));
        cart.removeItem(productId);
    }
}