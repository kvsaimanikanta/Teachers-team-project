package com.Teams.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class LoginDao {

	public boolean login(String email, String password) {
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Query<?> qref=sref.createQuery("from SignUpPojo where email	=:em and password=:pass");
		qref.setParameter("em", email);
		qref.setParameter("pass", password);
		
		List<?> lref = qref.list();
		Iterator<?> itr = lref.iterator();
		
		if(itr.hasNext()) {
			return true;
		}else {
			return false;
		}
	}

}
