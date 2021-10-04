package com.Teams.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Teams.Pojo.SignUpPojo;

public class SignUpDao {

	public void details(String name, String email, String password, long number, String subject) {
		
		Configuration cref = new Configuration();
		cref.configure("team.cfg.xml");
		
		SessionFactory sfref =cref.buildSessionFactory();
		
		Session sref = sfref.openSession();
		
		Transaction tref = sref.beginTransaction();
		
		SignUpPojo sp = new SignUpPojo();
		sp.setEmail(email);
		sp.setName(name);
		sp.setPassword(password);
		sp.setNumber(number);
		sp.setSubject(subject);
		sref.save(sp);
		
		tref.commit();
		sref.close();
		sfref.close();
	}

}
