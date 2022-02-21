package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.AutorRepository;

@Service
public class AutorService implements GenericService<Autor, Integer> {
	@Autowired
	AutorRepository autorRepository;

	@Override
	public Iterable<Autor> findAll() {
		return autorRepository.findAll();
	}

	@Override
	public Page<Autor> findAll(Pageable pageable) {
		return autorRepository.findAll(pageable);
	}

	@Override
	public Optional<Autor> findById(Integer id) {
		return autorRepository.findById(id);
	}

	@Override
	public Autor save(Autor object) {
		return autorRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		autorRepository.deleteById(id);
		
	}

	@Override
	public void delete(Autor object) {
		autorRepository.delete(object);
		
	}
}
