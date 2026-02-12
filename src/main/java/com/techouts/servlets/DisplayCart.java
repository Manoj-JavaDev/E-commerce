package com.techouts.servlets;

import com.techouts.dao.CartDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet ("/displayCart")
public class DisplayCart extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        Cart cart = user.getCart();

        CartDAO cartDAO = new CartDAO();

        List<CartProducts> cartProducts = cartDAO.findAllCartProducts(cart);

        request.setAttribute("cartProducts", cartProducts);

        request.getRequestDispatcher("/cart.jsp").forward(request, response);

    }

}
