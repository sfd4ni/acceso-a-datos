package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Operadore;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.OperadorRepository;


@Service
public class OperadorService implements GenericService<Operadore, Integer> {
	@Autowired
	OperadorRepository operadorRepository;

	@Override
	public Iterable<Operadore> findAll() {
		return operadorRepository.findAll();
	}

	@Override
	public Page<Operadore> findAll(Pageable pageable) {
		return operadorRepository.findAll(pageable);
	}

	@Override
	public Optional<Operadore> findById(Integer id) {
		return operadorRepository.findById(id);
	}

	@Override
	public Operadore save(Operadore object) {
		return operadorRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		operadorRepository.deleteById(id);
		
	}

	@Override
	public void delete(Operadore object) {
		operadorRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Operadore findByNick(String nick) {
		Operadore u = null;
		List<Operadore> lista = operadorRepository.findByNick(nick);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
