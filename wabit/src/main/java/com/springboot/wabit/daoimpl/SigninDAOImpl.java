package com.springboot.wabit.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.wabit.dao.SignupDAO;
import com.springboot.wabit.dto.Info;
import com.springboot.wabit.dto.SignUpUser;
import com.springboot.wabit.dto.User;

@Repository("SignupDAO")
@Transactional
public class SigninDAOImpl implements SignupDAO {

	@PersistenceContext
	@Autowired
	private EntityManager em;

	@Override
	public boolean add(User user) {
		try {
			em.persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Info info) {
		try {
			em.merge(info);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Info info) {
		try {
			em.persist(info);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * @Override public boolean delete1(int id) { try { // call the update method
	 * //return this.update(id);
	 * 
	 * } catch(Exception ex) { ex.printStackTrace(); } return false; }
	 */

	@Override
	public boolean delete(int id) {
		try {

			em.createQuery("delete from Info where id = :id")
			.setParameter("id", id).executeUpdate();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return false;
	}

	/*
	 * @Override public boolean delete1(int id) { String query =
	 * "remove FROM Info WHERE id = :id"; System.out.println("hiiiii" +query);
	 * return em .createQuery(query,Info.class) .setParameter("id",id)
	 * .getResultList() != null;
	 * 
	 * 
	 * 
	 * }
	 */

	@Override
	public List<Info> getbyID(int id) {
		String query = "FROM Info WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, Info.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<User> loginbyName(String username) {
		String query = "FROM User WHERE username = :username";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, User.class).setParameter("username", username).getResultList();
	}

	@Override
	public List<User> getAllUser() {
		String query = "FROM User";
		return em.createQuery(query, User.class).getResultList();
	}

	@Override
	public List<Info> getAllInfo() {
		String query = "FROM Info";
//		String query = "FROM Info ORDER BY id DESC";
		return em.createQuery(query, Info.class)

				.getResultList();
	}

	@Override
	public User getByUsername(String username) {
		String selectQuery = "FROM User WHERE username = :username";
		try {
			return em.createQuery(selectQuery, User.class).setParameter("username", username).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public SignUpUser get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Info getid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public boolean add(Info info) { // TODO Auto-generated method stub
	 * return false; }
	 */

}
