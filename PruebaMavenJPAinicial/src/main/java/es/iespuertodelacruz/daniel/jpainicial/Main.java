package es.iespuertodelacruz.daniel.jpainicial;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.iespuertodelacruz.daniel.jpainicial.entities.Alumno;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaMavenJPAinicial");
		EntityManager em = emf.createEntityManager();
		Alumno alu = em.find(Alumno.class, "12312312K");
		System.out.println( alu.getNombre() + " " + alu.getApellidos());

	}

}
