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
	
	@Override
	public boolean deleteOurProducts(OurProducts ourProducts) {
		try {
			em.merge(ourProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<OurProducts> getOurProductbyID(int id) {
		String query = "FROM OurProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, OurProducts.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<OurProducts> getAllOurProducts() {
		String query = "FROM OurProducts";
		return em.createQuery(query, OurProducts.class).getResultList();
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
	public boolean deleteClientProducts(ClientProducts clientProducts) {
		try {
			em.merge(clientProducts);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ClientProducts> getClientproductbyID(int id) {
		String query = "FROM ClientProducts WHERE id = :id";
		System.out.println("hiiiii" + query);

		return em.createQuery(query, ClientProducts.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<ClientProducts> getAllCLientproducts() {
		String query = "FROM ClientProducts";
		return em.createQuery(query, ClientProducts.class).getResultList();
	}

	@Override
	public ClientProducts getbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	  private final Path root = Paths.get("uploads");

	  @Override
	  public void init() {
	    try {
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public void save(MultipartFile file) {
	    try {
	      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	
}
