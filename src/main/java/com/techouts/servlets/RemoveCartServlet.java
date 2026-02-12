package com.techouts.servlets;

import com.techouts.dao.CartDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.CartProducts;
import com.techouts.entity.Product;
import com.techouts.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/removeFromCart")
public class RemoveCartServlet extends HttpServlet {

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        Cart cart = user.getCart();

        CartDAO cartDAO = new CartDAO();

        long productId = Long.parseLong(req.getParameter("productId"));

        CartProducts cartProducts = cartDAO.findCartProductByProductId(cart,productId);

        if(cartDAO.removeItemFromCart(cart,cartProducts.getProduct()) == 1){
            req.setAttribute("removed",1);
            req.getRequestDispatcher("/displayCart").forward(req,resp);
        }
        else {
            req.setAttribute("removed",0);
            req.getRequestDispatcher("/displayCart").forward(req,resp);
        }
    }
}
