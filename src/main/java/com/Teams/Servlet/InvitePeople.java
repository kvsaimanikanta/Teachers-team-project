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

import com.Teams.Pojo.SignUpPojo;

/**
 * Servlet implementation class InvitePeople
 */
@WebServlet("/InvitePeople")
public class InvitePeople extends HttpServlet {
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
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Query<?> qref=sref.createQuery("from TeamsPojo where teamAdminEmail=:em");
		qref.setParameter("em", email);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		if(itr.hasNext()) {
			
			Query<?> qref1=sref.createQuery("from SignUpPojo where email != :em");
			qref1.setParameter("em", email);
			
			List<?> lref1 = qref1.list();
			Iterator<?> itr1 = lref1.iterator();
			
			out.print("<table border='1' cellpadding='4' width='100%'>");
			out.print("<body style ='background-color:AliceBlue;'/>");
			out.print("<tr><td>Name</td><td>Email</td><td>Subject</td></tr>");
			
			while(itr1.hasNext()) {
				
				SignUpPojo sp = (SignUpPojo)itr1.next();
				
				out.print("<tr><td>"+sp.getName()+"</td><td>"+sp.getEmail()+"</td><td>"+sp.getSubject()+"</td><td><a href='Invite?email="+sp.getEmail()+"'>Invite</td></tr>");
				
			}
			
		}else {
			//you dont have a team
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You don't have any teams if you want to create click on below link</h1><a href='createTeam.html'>Create Team</center></body></html>");

		}
		
	}

}
