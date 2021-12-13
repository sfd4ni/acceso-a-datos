package es.iespuertodelacruz.daniel.pruebarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.pruebarest.entity.Historicocambioeuro;
import es.iespuertodelacruz.daniel.pruebarest.repository.HistoricoRepository;

public class HistoricoService implements GenericService<Historicocambioeuro, Integer> {

	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Historicocambioeuro> findAll() {
		return historicoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Historicocambioeuro> findAll(Pageable pageable) {
		return historicoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Historicocambioeuro> findById(Integer id) {
		return historicoRepository.findById(id);
	}

	@Override
	@Transactional
	public Historicocambioeuro save(Historicocambioeuro objeto) {
		return historicoRepository.save(objeto);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		historicoRepository.deleteById(id);
		
	}
}
