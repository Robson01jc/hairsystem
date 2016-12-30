package com.robsonrocha.dao.generic;

import java.util.List;

public interface GenericDAO<E, PK> {

	public E find(PK primaryKey);

	public void create(E entity);

	public void update(E entity);

	public void delete(E entity);

	public List<E> loadAll();

}
