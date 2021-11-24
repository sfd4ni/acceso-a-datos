package es.iespuertodelacruz.jc.monedaswebjpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import es.iespuertodelacruz.jc.monedaswebjpa.entities.Moneda;

public class MonedaRepository implements JPACRUD<Moneda, Integer>{

	private EntityManagerFactory emf;

	public MonedaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Moneda> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Moneda> lista = em.createNamedQuery("Moneda.findAll", Moneda.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Moneda findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Moneda moneda = em.find(Moneda.class, id);
		em.getTransaction().commit();
		em.close();
		return moneda;
	}

	@Override
	public Moneda save(Moneda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Moneda update(Moneda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
