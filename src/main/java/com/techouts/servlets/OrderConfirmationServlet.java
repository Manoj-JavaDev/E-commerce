package com.techouts.servlets;

import java.io.IOException;

import com.techouts.dao.OrderDAO;
import com.techouts.entity.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-confirmation")
public class OrderConfirmationServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderNumber = req.getParameter("orderNumber");

        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        Order order = orderDAO.findByOrderNumber(orderNumber);

        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        req.setAttribute("order", order);
        req.getRequestDispatcher("/order-confirmation.jsp").forward(req, resp);
    }
}