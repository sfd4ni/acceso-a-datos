package es.iespuertodelacruz.daniel.matriculasrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Usuarioconrol;
import es.iespuertodelacruz.daniel.matriculasrest.repository.MatriculaRepository;
import es.iespuertodelacruz.daniel.matriculasrest.repository.UsuarioRepository;

@Service
public class UsuarioService implements GenericService<Usuarioconrol, Integer> {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public Iterable<Usuarioconrol> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuarioconrol> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Usuarioconrol> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuarioconrol save(Usuarioconrol object) {
		return usuarioRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public void delete(Usuarioconrol object) {
		usuarioRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Usuarioconrol findByNombre(String nombre) {
		Usuarioconrol u = null;
		List<Usuarioconrol> lista = usuarioRepository.findByNombre(nombre);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
