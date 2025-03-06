package com.Xposindia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SessionDao {

    @PersistenceContext(unitName = "xposDefault")
    protected EntityManager entityManager;
    
	@Transactional
	public Session getSession() {
		Session session = entityManager.unwrap(Session.class);
		return session;
	}
}
