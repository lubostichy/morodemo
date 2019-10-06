package com.lubostichy.morodemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.lubostichy.morodemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private EntityManager entityManager;
	
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User order by id",User.class);
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public User getUserById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);		
		currentSession.saveOrUpdate(user);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from User where id=:userId")
				.setParameter("userId", id);
		query.executeUpdate();
		
	}

	@Override
	public User getUserByUsername(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User where username=:username", User.class);
		query.setParameter("username", username);
		return query.uniqueResult();
	}
}
