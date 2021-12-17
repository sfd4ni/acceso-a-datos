package es.iespuertodelacruz.jc.cambiomonedas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;

public interface MonedasRepository extends JpaRepository<Monedas, Integer> {
	@Query("SELECT m FROM Monedas m where m.nombre = :name")
	List<Monedas> findByNombre(@Param("name") String strNombre);
}
