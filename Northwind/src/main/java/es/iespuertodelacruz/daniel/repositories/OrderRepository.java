package es.iespuertodelacruz.daniel.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import es.iespuertodelacruz.daniel.entities.Order;
import es.iespuertodelacruz.daniel.entities.OrderDetail;

public class OrderRepository implements JPACRUD<Order,Integer>{

	private EntityManagerFactory emf;

	public OrderRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Order> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Order> lista = em.createNamedQuery("Order.findAll", Order.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Order findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Order Order = em.find(Order.class, id);
		em.getTransaction().commit();
		em.close();
		return Order;
	}


	public Order findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Order Order = null;
		try {
			Order = em.createNamedQuery("Order.findByName", Order.class)
					.setParameter("name", nombre)
					.getSingleResult();
		} catch (NoResultException nre){
			// No hacemos nada, que no se encuentre Order está bien
		}
		

		em.getTransaction().commit();
		em.close();
		return Order;
	}	
	
	
	@Override
	public Order save(Order obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for( OrderDetail orderdetail: obj.getOrderDetails()) {
			orderdetail.setOrder(obj);
			//em.persist(orderdetail);
		}
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
		return obj;
	}

	@Override
	public Order update(Order obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return true;
	}

}
