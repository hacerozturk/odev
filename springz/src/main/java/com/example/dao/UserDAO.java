package com.example.dao;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;  
	
	public Long insert(User user) {
		
		return (Long) sessionFactory.getCurrentSession().save(user);
		
	}
	
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
	
	//READ
	
	public User getNoteFindByUsernameAndPass(String username, String pass){
		
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND password=:password AND active=:active")
				.setString("username", username)
				.setString("password", pass)
				.setBoolean("active",true);
			
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();	
		} catch (javax.persistence.NoResultException e ) {
			user = null; 
		}
		return user;
	}
	
	
	public User getNoteFindByUsername(String username){
		
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username")
				.setString("username", username);			
		
		return (User) query.getSingleResult();
	}

	public User getNoteFindByKey(String key){
		
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE keyreg=:key")
				.setString("key", key);		
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();	
		} catch (javax.persistence.NoResultException e ) {
			user = null; 
		}
		return user;
			
	}
	
}
