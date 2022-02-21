package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.EjemplarRepository;

@Service
public class EjemplarService implements GenericService<Ejemplare, Integer> {
	@Autowired
	EjemplarRepository ejemplarRepository;

	@Override
	public Iterable<Ejemplare> findAll() {
		return ejemplarRepository.findAll();
	}

	@Override
	public Page<Ejemplare> findAll(Pageable pageable) {
		return ejemplarRepository.findAll(pageable);
	}

	@Override
	public Optional<Ejemplare> findById(Integer id) {
		return ejemplarRepository.findById(id);
	}

	@Override
	public Ejemplare save(Ejemplare object) {
		return ejemplarRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		ejemplarRepository.deleteById(id);
		
	}

	@Override
	public void delete(Ejemplare object) {
		ejemplarRepository.delete(object);
		
	}
}
