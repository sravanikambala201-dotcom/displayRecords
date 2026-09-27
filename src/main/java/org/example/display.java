package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/datadisplay")
public class display extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/admin",
                    "root",
                    "admin123"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<body>");

            out.println("<h2>Employee Details</h2>");
            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Name</th>");
            out.println("<th>Salary</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("salary") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}