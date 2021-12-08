package es.iespuertodelacruz.daniel.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import es.iespuertodelacruz.daniel.entities.Shipper;

public class ShipperRepository implements JPACRUD<Shipper,Integer>{

	private EntityManagerFactory emf;

	public ShipperRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Shipper> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Shipper> lista = em.createNamedQuery("Shipper.findAll", Shipper.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Shipper findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Shipper shipper = em.find(Shipper.class, id);
		em.getTransaction().commit();
		em.close();
		return shipper;
	}


	public Shipper findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Shipper shipper = null;
		try {
			shipper = em.createNamedQuery("Shipper.findByName", Shipper.class)
					.setParameter("name", nombre)
					.getSingleResult();
		} catch (NoResultException nre){
			// No hacemos nada, que no se encuentre Shipper está bien
		}
		

		em.getTransaction().commit();
		em.close();
		return shipper;
	}	
	
	
	@Override
	public Shipper save(Shipper obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
		return obj;
	}

	@Override
	public Shipper update(Shipper obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return true;
	}

}
