package com.Teams.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Teams.Dao.SignUpDao;


/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		long number = Long.parseLong(req.getParameter("number"));
		String subject = req.getParameter("subject");
		
		SignUpDao su = new SignUpDao();
		su.details(name,email,password,number,subject);
		
		out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>Account Created Successfully </h1></center></body></html>");
		
		RequestDispatcher rd = req.getRequestDispatcher("Login.html");
		rd.include(req, res);
		
	}

}
