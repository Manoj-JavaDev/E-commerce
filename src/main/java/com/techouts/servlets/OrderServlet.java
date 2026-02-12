package com.techouts.servlets;

import com.techouts.dao.CartDAO;
import com.techouts.dao.OrdersDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.Orders;
import com.techouts.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        Cart cart = user.getCart();

        CartDAO cartDAO = new CartDAO();
        OrdersDAO orderDAO= new OrdersDAO();


        Orders order = new Orders();
        order.setPostalCode(Long.parseLong(request.getParameter("postalCode")));
        order.setCountry(request.getParameter("country"));
        order.setCity(request.getParameter("city"));
        order.setAddress(request.getParameter("address"));
        order.setPaymentMethod(request.getParameter("paymentMethod"));
        orderDAO.addOrderDetails(order,cart);

        request.getRequestDispatcher("OrderSuccess.jsp").forward(request,response);

    }

}
