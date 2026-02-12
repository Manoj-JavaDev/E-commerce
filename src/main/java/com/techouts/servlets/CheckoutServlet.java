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
import java.math.BigDecimal;
import java.util.List;

@WebServlet ("/checkout")
public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        Cart cart = user.getCart();

        CartDAO cartDAO = new CartDAO();

        List<CartProducts> cartProducts = cartDAO.findAllCartProducts(cart);
        //request.setAttribute("cart", cart);
        request.setAttribute("cartProducts", cartProducts);

        BigDecimal total = BigDecimal.ZERO;
        for (CartProducts cartProduct : cartProducts) {

            BigDecimal itemTotal =
                    BigDecimal.valueOf(cartProduct.getProduct()
                            .getPrice());

            total = total.add(itemTotal);
        }

        request.setAttribute("total", total);

        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

}
