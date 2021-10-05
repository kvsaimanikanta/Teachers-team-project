package com.Teams.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Decline
 */
@WebServlet("/Decline")
public class Decline extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		
		ServletContext sc = getServletContext();
		String email = (String) sc.getAttribute("t-email");
		
		String adminEmail = req.getParameter("adminEmail");
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();

		Query<?> qref2=sref.createQuery("delete from InvitePojo where adminEmail=:em and teacherEmail=:em1");
		qref2.setParameter("em", adminEmail);
		qref2.setParameter("em1", email);
		qref2.executeUpdate();
		
		tref.commit();
		sref.close();
		sfref.close();
		
		RequestDispatcher rd = req.getRequestDispatcher("afterlogin.html");
		rd.forward(req, res);
		
	}

}
