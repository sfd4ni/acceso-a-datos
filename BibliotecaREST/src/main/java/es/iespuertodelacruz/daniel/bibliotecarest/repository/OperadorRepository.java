package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Operadore;


public interface OperadorRepository extends JpaRepository <Operadore, Integer> {
	@Query("SELECT t FROM Operadore t where t.nick = :name")
	 List<Operadore> findByNick(@Param("name") String strNombre);
	
}
