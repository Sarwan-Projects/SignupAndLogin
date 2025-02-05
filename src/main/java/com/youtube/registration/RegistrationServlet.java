package com.youtube.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String uname = req.getParameter("name");
		String uemail = req.getParameter("email");
		String upwd = req.getParameter("pass");
		String rupwd = req.getParameter("re_pass");
		String umobile = req.getParameter("contact");
		
		RequestDispatcher rd = null;
		Connection conn = null;
		if(uname==null || uname.equals(""))
		{
			req.setAttribute("status","InavalidName");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
		}
		if(uemail==null || uemail.equals(""))
		{
			req.setAttribute("status","InavalidEmail");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
		}
		if(upwd==null || upwd.equals(""))
		{
			req.setAttribute("status","InavalidUpwd");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
		}
		else if(!upwd.equals(rupwd))
		{
			req.setAttribute("status","InavalidConfirm");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
		}
		if(umobile==null || umobile.equals(""))
		{
			req.setAttribute("status","InavalidMobile");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
			return;
		}else if(umobile.length() < 10){
			req.setAttribute("status","InavalidLength");
			rd = req.getRequestDispatcher("registration.jsp");
			rd.forward(req, res);
			return;
		}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","root");
			PreparedStatement pst = conn.prepareStatement("insert into users(uname,uemail,upwd,umobile) values(?,?,?,?)");
			
			pst.setString(1, uname);
			pst.setString(2, uemail);
			pst.setString(3, upwd);
			pst.setString(4, umobile);
			
			int result = pst.executeUpdate();
			rd = req.getRequestDispatcher("registration.jsp");
			
			if(result>0)
			{
				req.setAttribute("status", "success");
			}
			else
			{
				req.setAttribute("status", "failed");
			}
			rd.forward(req, res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}