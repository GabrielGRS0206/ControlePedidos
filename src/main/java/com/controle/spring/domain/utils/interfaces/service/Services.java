package com.controle.spring.domain.utils.interfaces.service;

import java.util.List;
import java.util.Optional;

public interface Services<T> {

	public T save(Object obj);
	public T update(Object obj);
	public void delete(long id);
	public Optional<T> findById(long id);
	public List<T> findAll();
	public boolean existsById(long id);

}
