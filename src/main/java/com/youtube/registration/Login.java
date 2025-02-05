package com.youtube.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String upwd = req.getParameter("password");
		String uemail = req.getParameter("username");
		
		HttpSession s = req.getSession();
		RequestDispatcher rd = null;
		if(uemail==null || uemail.equals(""))
		{
			req.setAttribute("status","InavalidEmail");
			rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, res);
		}
		if(upwd==null || upwd.equals(""))
		{
			req.setAttribute("status","InavalidUpwd");
			rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, res);
		}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","root");
			PreparedStatement pst = conn.prepareStatement("select * from users where uemail = ? And upwd = ?");
			
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				s.setAttribute("name", rs.getString("uname"));
				rd = req.getRequestDispatcher("index.jsp");
			}
			else
			{
				req.setAttribute("status", "failed");
				rd = req.getRequestDispatcher("login.jsp");
			}
			rd.forward(req, res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}