package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try (Connection con = DBConnection.getConnection()) {

            String checkSql =
                    "SELECT username FROM users WHERE username=? OR email=?";

            PreparedStatement check = con.prepareStatement(checkSql);

            check.setString(1, username);
            check.setString(2, email);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                request.setAttribute(
                        "error", "Username or Email already exists!"
                );

                request.getRequestDispatcher("register.jsp")
                        .forward(request, response);

            } else {

                String insertSql =
                        "INSERT INTO users VALUES (?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(insertSql);

                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, phone);

                ps.executeUpdate();

                request.setAttribute(
                        "success", "Registration Successful!"
                );

                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}