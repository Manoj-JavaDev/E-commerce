package com.techouts.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import com.techouts.dao.OrderDAO;
import com.techouts.dao.ProductDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.Order;
import com.techouts.entity.OrderItem;
import com.techouts.entity.Product;
import com.techouts.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        
        req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        try {
            String shippingAddress = req.getParameter("address");
            String phone = req.getParameter("phone");

            // Validation
            if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
                req.setAttribute("error", "Shipping address is required");
                req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
                return;
            }

            if (phone == null || phone.trim().isEmpty()) {
                req.setAttribute("error", "Phone number is required");
                req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
                return;
            }

            // Create order
            Order order = new Order();
            order.setUser(user);
            order.setTotalAmount(cart.getTotal());
            order.setShippingAddress(shippingAddress);
            order.setPhone(phone);
            order.setStatus(Order.OrderStatus.PENDING);

            // Add order items
            for (Cart.CartItem cartItem : cart.getItems()) {
                Product product = cartItem.getProduct();
                
                // Check stock availability
                if (product.getStock() < cartItem.getQuantity()) {
                    req.setAttribute("error", "Product " + product.getName() + " is out of stock");
                    req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
                    return;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItem.calculateSubtotal();
                order.addOrderItem(orderItem);

                // Update product stock
                product.setStock(product.getStock() - cartItem.getQuantity());
                productDAO.update(product);
            }

            // Save order
            orderDAO.save(order);

            // Clear cart
            cart.clear();

            // Redirect to order confirmation
            resp.sendRedirect(req.getContextPath() + "/order-confirmation?orderNumber=" + order.getOrderNumber());

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while processing your order. Please try again.");
            req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
        }
    }
}