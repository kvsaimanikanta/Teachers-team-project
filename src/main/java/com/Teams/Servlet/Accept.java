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

import com.Teams.Pojo.SignUpPojo;
import com.Teams.Pojo.TeamsPojo;

/**
 * Servlet implementation class Accept
 */
@WebServlet("/Accept")
public class Accept extends HttpServlet {
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
		
		String adminEmail = req.getParameter("adminEmail");
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();
		
		Query<?> qref=sref.createQuery("from SignUpPojo where email	=:em");
		qref.setParameter("em", email);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		String name = null,email2 = null,subject = null;
		long number = 0;
		while(itr.hasNext())
		{
			SignUpPojo sp = (SignUpPojo)itr.next();
			name=sp.getName();
			email2=sp.getEmail();
			subject = sp.getSubject();
			number = sp.getNumber();
		}
		//System.out.println(email1+" "+name+" "+email2+" "+subject+" "+number);
		
		Query<?> qref1=sref.createQuery("from TeamsPojo where teamAdminEmail =:em and teamMateSubject=:sub");
		qref1.setParameter("em", adminEmail);
		qref1.setParameter("sub", subject);
		
		List<?> lref1 = qref1.list();
		Iterator<?> itr1 = lref1.iterator();
		
		if(itr1.hasNext())
		{
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>Accepting Failed</h1><h2>You are Alredy In that Team<h2><h3>Or There Is A "+subject+" Teacher<h3></center></body></html>");
			
		}else {
			
			Query<?> qref2=sref.createQuery("delete from InvitePojo where adminEmail=:em and teacherEmail=:em1");
			qref2.setParameter("em", adminEmail);
			qref2.setParameter("em1", email);
			qref2.executeUpdate();
			
			TeamsPojo tp = new TeamsPojo();
			tp.setTeamAdminEmail(adminEmail);
			tp.setTeamMateEmail(email2);
			tp.setTeamMateName(name);
			tp.setTeamMateNumber(number);
			tp.setTeamMateSubject(subject);
			sref.save(tp);
			
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>You Are Added To that Team Successfully </h1></center></body></html>");
			
		}
		
		tref.commit();
		sref.close();
		sfref.close();

	}

}
