package com.Teams.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.Teams.Pojo.TeamsPojo;

/**
 * Servlet implementation class IamIn
 */
@WebServlet("/IamIn")
public class IamIn extends HttpServlet {
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
		
		System.out.println(email);
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Query<?> qref1=sref.createQuery("from TeamsPojo where teamMateEmail=:em");
		qref1.setParameter("em", email);
		
		List<?> lref1 = qref1.list();
		Iterator<?> itr1 = lref1.iterator();
		
		if(itr1.hasNext()) {
		
		Query<?> qref=sref.createQuery("from TeamsPojo where teamMateEmail=:em");
		qref.setParameter("em", email);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		out.print("<table border='1' cellpadding='4' width='100%'>");
		out.print("<body style ='background-color:AliceBlue;'/>");
		out.print("<tr><td>teamAdminEmailEmail</td></tr>");
		
		while(itr.hasNext()) {
			
			TeamsPojo tp = (TeamsPojo)itr.next();
			out.print("<tr><td>"+tp.getTeamAdminEmail()+"</td><td><a href='Left?adminMail="+tp.getTeamAdminEmail()+"'>Left</td></tr>");
			
		}
		}else
		{
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You are not in any Team</h1></center></body></html>");

		}
		
	}

}
