package es.iespuertodelacruz.daniel.matriculasrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Usuarioconrol;

public interface UsuarioRepository extends JpaRepository <Usuarioconrol, Integer> {
	@Query("SELECT t FROM Usuarioconrol t where t.nombre = :name")
	 List<Usuarioconrol> findByNombre(@Param("name") String strNombre);
	
}
