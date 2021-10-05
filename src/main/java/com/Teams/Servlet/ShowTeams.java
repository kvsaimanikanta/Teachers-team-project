package com.Teams.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.Teams.Pojo.TeamPojo;

/**
 * Servlet implementation class ShowTeams
 */
@WebServlet("/ShowTeams")
public class ShowTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Query<?> qref=sref.createQuery("from TeamPojo");
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		out.print("<table border='1' cellpadding='4' width='100%'>");
		out.print("<body style ='background-color:AliceBlue;'/>");
		out.print("<tr><td>TeamName</td><td>Team Admin</td><td>Join Link</td></tr>");
		
        String adminEmail=null;
        int id=0;
		while(itr.hasNext()) {
			
			TeamPojo tp = (TeamPojo)itr.next();
			
			out.print("<tr><td>"+tp.getTeamName()+"</td><td>"+tp.getEmail()+"</td><td><a href='Join?adminEmail="+tp.getEmail()+"'>Join</a></td></tr>");

//	        adminEmail=tp.getEmail();
//	        id=tp.getId();
//			ServletContext sc = getServletContext();
//	        sc.setAttribute("adminEmail", adminEmail);
	       
		}
		 System.out.println(adminEmail+"    "+id);		
	}

}
