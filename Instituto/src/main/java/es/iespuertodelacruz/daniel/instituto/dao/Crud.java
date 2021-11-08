package es.iespuertodelacruz.daniel.instituto.dao;

import java.util.ArrayList;

public interface Crud<T,E> {
	T save(T dao);
	T findById(E id);
	boolean update(T dao);
	boolean delete(E id);
	ArrayList<T> findAll();
}
