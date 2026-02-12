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

@WebServlet ("/deleteFromCart")
public class DeleteFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Cart cart = user.getCart();

        CartDAO cartDAO = new CartDAO();

        long productId = Long.parseLong(req.getParameter("productId"));

        CartProducts cartProducts = cartDAO.findCartProductByProductId(cart,productId);

        cartDAO.deleteCartProductByProductId(cart,cartProducts.getProduct());

        req.getRequestDispatcher("/displayCart").forward(req,response);
    }

}
