package com.robsonrocha.dao.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class GenericDAOImpl<E, PK> implements GenericDAO<E, PK> {

	protected final Class<E> persistenceClass;
	
	@PersistenceContext(unitName = "hairsystem-persistence-unit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		persistenceClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected final EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public E find(PK primaryKey) {
		return entityManager.find(persistenceClass, primaryKey);
	}

	@Override
	public void create(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(E entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}

	@Override
	public void delete(E entity) {
		entityManager.remove((entityManager.contains(entity)) ? entity : entityManager.merge(entity));
	}

	@Override
	public List<E> loadAll() {
		TypedQuery<E> tQuery = entityManager.createQuery(("select e from " + persistenceClass.getSimpleName() + " e"), persistenceClass);
		return tQuery.getResultList();
	}

}