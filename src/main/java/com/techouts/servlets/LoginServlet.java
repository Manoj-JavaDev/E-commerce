package com.techouts.servlets;

import java.io.IOException;

import com.techouts.dao.UserDAO;
import com.techouts.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            // Validation
            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Email is required");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            if (password == null || password.trim().isEmpty()) {
                req.setAttribute("error", "Password is required");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Find user by email
            User user = userDAO.findByEmail(email.trim().toLowerCase());

            if (user == null) {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Verify password
            if (!password.equals(user.getPassword())) {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Create session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole().toString());

            // Redirect based on role
            if (user.getRole() == User.UserRole.ADMIN) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/products");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during login. Please try again.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}