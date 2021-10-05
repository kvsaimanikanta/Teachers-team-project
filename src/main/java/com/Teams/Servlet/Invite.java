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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.Teams.Pojo.InvitePojo;


/**
 * Servlet implementation class Invite
 */
@WebServlet("/Invite")
public class Invite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		ServletContext sc= getServletContext();
		String adminEmail = (String) sc.getAttribute("t-email");
		
		String email = req.getParameter("email");
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();
		
		Query<?> qref=sref.createQuery("from InvitePojo where adminEmail=:em and teacherEmail=:em1");
		qref.setParameter("em", adminEmail);
		qref.setParameter("em1", email);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		if(itr.hasNext()) {
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>you alredy invited "+email+"</h1><a href='InvitePeople'>Invite AnotherOne</center></body></html>");

		}else {
			
			InvitePojo ip = new InvitePojo();
			ip.setAdminEmail(adminEmail);
			ip.setTeacherEmail(email);
			sref.save(ip);
			
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>Initation sent succedssfully to "+email+"</h1><a href='InvitePeople'>Invite More</center></body></html>");

			tref.commit();
			sref.close();
			sfref.close();
		}
		
	}

}
