package com.Teams.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Teams.Dao.TeamDao;

/**
 * Servlet implementation class CreateTeam
 */
@WebServlet("/CreateTeam1")
public class CreateTeam1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		ServletContext sc= getServletContext();
		String email = (String) sc.getAttribute("t-email");
		
		String teamName = req.getParameter("name");
		
		TeamDao td = new TeamDao();
		if(td.details(teamName,email)) {
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>"+teamName+" Created Successfully</h1></center></body></html>");
		}else{
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>Creation Failed</h1><h2>You are alredy created a Team<h2><h3>Or You are taking same name of exesting Team Name<h3></center></body></html>");
		}	
	}

}
