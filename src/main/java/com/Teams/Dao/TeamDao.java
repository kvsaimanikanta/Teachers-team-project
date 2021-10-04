package com.Teams.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.Teams.Pojo.SignUpPojo;
import com.Teams.Pojo.TeamPojo;
import com.Teams.Pojo.TeamsPojo;

public class TeamDao {

	public boolean details(String teamName, String email) {
		// TODO Auto-generated method stub
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();
		
		Query<?> qref2=sref.createQuery("from SignUpPojo where email =:em");
		qref2.setParameter("em", email);
		
		List<?> lref2 = qref2.list();
		Iterator<?> itr2 = lref2.iterator();
		long number = 0;
		String name = null,subject= null;
		while(itr2.hasNext()) {
			SignUpPojo sp =(SignUpPojo)itr2.next();
			number = sp.getNumber();
			name = sp.getName();
			subject = sp.getSubject();
		}
		
//		Query<?> qref1=sref.createQuery("from TeamPojo where email =:em");
//		qref1.setParameter("em", email);
//		
//		List<?> lref1 = qref1.list();
//		Iterator<?> itr1 = lref1.iterator();
//		int id1 = 0;
//		String name1 = null;
//		while(itr1.hasNext()) {
//			TeamPojo tp1 =(TeamPojo)itr1.next();
//			id1 = tp1.getId();
//			name1 = tp1.getTeamName();
//		}
		
		//System.out.println(name+" "+subject+" "+number);
		
		Query<?> qref=sref.createQuery("from TeamPojo where email	=:em");
		qref.setParameter("em", email);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();

		if(itr.hasNext()) {
			return false;
		}else {
			
			TeamPojo tp = new TeamPojo();
			tp.setEmail(email);
			tp.setTeamName(teamName);
			sref.save(tp);
			
			TeamsPojo tsp = new TeamsPojo();
			tsp.setTeamMateEmail(email);
			tsp.setTeamMateName(name);
			tsp.setTeamMateNumber(number);
			tsp.setTeamMateSubject(subject);
			tsp.setTeamAdminEmail(email);
			sref.save(tsp);
			
			tref.commit();
			sref.close();
			sfref.close();
			
			return true;
		}
		
		
	}

}
