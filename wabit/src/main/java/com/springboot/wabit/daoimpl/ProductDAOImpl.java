package com.springboot.wabit.daoimpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.wabit.dao.ProductDAO;
import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@PersistenceContext
	@Autowired
	private EntityManager em;

	@Override
	public boolean addOurProducts(OurProducts ourProducts) {
		try {
			em.persist(ourProducts);
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
	public boolean update(OurProducts ourProducts) {
		try {			
			em
						.merge(ourProducts);
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
	public boolean deleteOurProducts(int id) {
		try {

			em.createQuery("delete from OurProducts where id = :id")
			.setParameter("id", id).executeUpdate();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<OurProducts> getOurProductbyID(int id) {
		String query = "FROM OurProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, OurProducts.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<OurProducts> getAllOurProducts(int start) {
		String query = "FROM OurProducts ORDER BY id ASC ";
 
	 		return em.createQuery(query, OurProducts.class)
					.setFirstResult((start - 1) * 5)
	 				.setMaxResults(5)
					.getResultList(); 
	}
	
//	@Override
//	public List<OurProducts> getAllOurProducts1() {
//		String query = "SELECT Count(*) FROM OurProducts ";
//		  return em.createQuery(query, OurProducts.class).getResultList()
//				 ;
//		
//
//	 		   
//	 		 
//	}
	
	@Override
	public long getOurProductCount() {
		 	Query queryTotal = em.createQuery 
		("Select count(f.id) from OurProducts f");
		 long countResult = (long)queryTotal.getSingleResult();

		//System.out.println("Total Count :" +countResult);
		
	 	 	return countResult; 
	}

	
	@Override
	public OurProducts getid(int id) {
		// TODO Auto-generated method stub
		return null;	
	}

	
	// Client Products //
	
	@Override
	public boolean addClientProducts(ClientProducts clientProducts) {
		try {
			em.persist(clientProducts);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateClientProducts(ClientProducts clientProducts) {
		try {
			em.merge(clientProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}
 
	
	@Override
	public boolean deleteClientProducts(int id) {
		try {

			em.createQuery("delete from ClientProducts where id = :id")
			.setParameter("id", id).executeUpdate();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return false;
	}
	

	@Override
	public List<ClientProducts> getClientproductbyID(int id) {
		String query = "FROM ClientProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, ClientProducts.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<ClientProducts> getAllCLientproducts(int start) {
		String query = "FROM ClientProducts ORDER BY id DESC";
 		return em.createQuery(query, ClientProducts.class)
 				.setFirstResult((start - 1) * 5)
 				.setMaxResults(5)
 				.getResultList();
	}
 	
	@Override
	public long getCLientproductsCount() {
		 	Query queryTotal = em.createQuery 
		("Select count(f.id) from ClientProducts f");
		 long countResult = (long)queryTotal.getSingleResult();

		//System.out.println("Total Count :" +countResult);
		
	 	 	return countResult; 
	}

	
	@Override
	public ClientProducts getbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
}
