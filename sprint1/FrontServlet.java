package etu1896.framework.servlet;
import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
public class FrontServlet extends HttpServlet {
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());out.println(req.getServletPath());
    }
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
    }
}