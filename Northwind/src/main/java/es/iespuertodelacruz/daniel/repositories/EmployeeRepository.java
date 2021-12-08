package es.iespuertodelacruz.daniel.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import es.iespuertodelacruz.daniel.entities.Employee;

public class EmployeeRepository implements JPACRUD<Employee,Integer>{

	private EntityManagerFactory emf;

	public EmployeeRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Employee> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Employee> lista = em.createNamedQuery("Employee.findAll", Employee.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Employee findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Employee employee = em.find(Employee.class, id);
		em.getTransaction().commit();
		em.close();
		return employee;
	}


	public Employee findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Employee employee = null;
		try {
			employee = em.createNamedQuery("Employee.findByName", Employee.class)
					.setParameter("name", nombre)
					.getSingleResult();
		} catch (NoResultException nre){
			// No hacemos nada, que no se encuentre Employee está bien
		}
		

		em.getTransaction().commit();
		em.close();
		return employee;
	}	
	
	
	@Override
	public Employee save(Employee obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
		return obj;
	}

	@Override
	public Employee update(Employee obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		return true;
	}

}

