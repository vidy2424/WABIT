package com.springboot.wabit.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.wabit.dao.TeamMemberDAO;
import com.springboot.wabit.dto.TeamMembers;

@Repository("TeamMemberDAO")
@Transactional
public class TeamMemberDAOImpl implements TeamMemberDAO {
	@PersistenceContext
	@Autowired
	private EntityManager em;

	@Override
	public boolean addTeamMember(TeamMembers teamMembers) {
		try {
			em.persist(teamMembers);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

/*	@Override
	public boolean updateOurProducts(OurProducts ourProducts) {
		try {
			em.merge(ourProducts);
			return true;
		} catch (Exception ex) {
			 //ex.printStackTrace();
			return false;
		}
	}*/

	@Override
	public boolean update(TeamMembers teamMembers) {
		try {			
			em
						.merge(teamMembers);
			return true;
		}
		catch(Exception ex) {		
			//ex.printStackTrace();
			return false;
		}					
	}
	
//	@Override
//	public boolean deleteOurProducts1(int id) {
//		try {
//			em.merge(ourProducts);
//			return true;
//		} catch (Exception ex) {
//			// ex.printStackTrace();
//			return false;
//		}
//	}

	@Override
	public boolean deleteTeamMembers(int id) {
		try {

			em.createQuery("delete from TeamMembers where id = :id")
			.setParameter("id", id).executeUpdate();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<TeamMembers> getTeamMembersbyID(int id) {
		String query = "FROM TeamMembers WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, TeamMembers.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<TeamMembers> getAllTeamMembers() {
		String query = "FROM TeamMembers";
//		String query = "FROM TeamMembers ORDER BY id DESC";
		return em.createQuery(query, TeamMembers.class).getResultList();
	}

	@Override
	public TeamMembers getid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
 
 

}
