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

import com.Teams.Pojo.InvitePojo;
import com.Teams.Pojo.TeamsPojo;

/**
 * Servlet implementation class Invitations
 */
@WebServlet("/Invitations")
public class Invitations extends HttpServlet {
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
			
			Query<?> qref1=sref.createQuery("from InvitePojo where adminEmail=:em");
			qref1.setParameter("em", email);
			
			List<?> lref1 = qref1.list();
			Iterator<?> itr1 = lref1.iterator();
			
			if(itr1.hasNext()) {
				out.print("<table border='1' cellpadding='4' width='100%'>");
				out.print("<body style ='background-color:AliceBlue;'/>");
				
				while(itr1.hasNext()) {
					
					InvitePojo tp = (InvitePojo)itr.next();
					
					out.print("<tr><td>"+tp.getTeacherEmail()+"</td></tr>");
					
				}
			}else {
				out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You don't have any Pending Invitations show</h1><h2>If you want to Invite click on below link</h2><a href='InvitePeople'>InvitePeople</center></body></html>");

			}
			
		}else {
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You don't have any teams if you want to create click on below link</h1><a href='createTeam.html'>Create Team</center></body></html>");

		}
		
	}

}
