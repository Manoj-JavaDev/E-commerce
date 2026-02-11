package com.techouts.servlets;

import java.io.IOException;

import com.techouts.dao.UserDAO;
import com.techouts.entity.Cart;
import com.techouts.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            // Validation
            if (name == null || name.trim().isEmpty()) {
                req.setAttribute("error", "Name is required");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Email is required");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            if (password == null || password.trim().isEmpty()) {
                req.setAttribute("error", "Password is required");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            if (password.length() < 6) {
                req.setAttribute("error", "Password must be at least 6 characters");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            // Check if email already exists
            if (userDAO.findByEmail(email) != null) {
                req.setAttribute("error", "Email already registered !! Please Register with new Email...");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            // Create and save user
            User user = new User();
            user.setName(name.trim());
            user.setEmail(email.trim().toLowerCase());
            user.setPassword(password);
            user.setPhone(phone);
            user.setAddress(address);
            user.setRole(User.UserRole.CUSTOMER);

            req.setAttribute("user", user);
            Cart cart = new Cart();
            user.setCart(cart);

            userDAO.save(user);

            HttpSession session = req.getSession(false);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole().toString());
            req.setAttribute("registered", true);

            req.getRequestDispatcher("/products").forward(req, resp);

        }
        catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during registration. Please try again.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}