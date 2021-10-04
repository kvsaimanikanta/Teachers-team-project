package com.Teams.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Servlet implementation class Left
 */
@WebServlet("/Left")
public class Left extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		ServletContext sc = getServletContext();
		String email = (String) sc.getAttribute("t-email");
		
		String adminEmail = req.getParameter("adminMail");
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();
		
		if(adminEmail.equals(email)) {
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You are tha Admin of that team you con't Left from the team</h1></center></body></html>");

		}else {
		
		Query<?> qref=sref.createQuery("delete from TeamsPojo where teamAdminEmail=:em and teamMateEmail=:em1");
		qref.setParameter("em", adminEmail);
		qref.setParameter("em1", email);
		qref.executeUpdate();
		
		out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You Left from this team "+adminEmail+" Successfully</h1></center></body></html>");
		}
		
		tref.commit();
		sref.close();
		sfref.close();
		
	}

}
