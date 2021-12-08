package es.iespuertodelacruz.daniel.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import es.iespuertodelacruz.daniel.entities.OrderDetail;
import es.iespuertodelacruz.daniel.entities.Product;

public class ProductRepository implements JPACRUD<Product,Integer>{

	private EntityManagerFactory emf;

	public ProductRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Product> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Product> lista = em.createNamedQuery("Product.findAll", Product.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Product findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product product = em.find(Product.class, id);
		em.getTransaction().commit();
		em.close();
		return product;
	}


	public Product findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product product = null;
		try {
			product = em.createNamedQuery("Product.findByName", Product.class)
					.setParameter("name", nombre)
					.getSingleResult();
		} catch (NoResultException nre){
			// No hacemos nada, que no se encuentre Product está bien
		}
		

		em.getTransaction().commit();
		em.close();
		return product;
	}	
	
	
	@Override
	public Product save(Product obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product product = em.merge(obj);
		em.persist(product);
		em.getTransaction().commit();
		em.close();
		return obj;
	}

	@Override
	public Product update(Product obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return true;
	}

}
