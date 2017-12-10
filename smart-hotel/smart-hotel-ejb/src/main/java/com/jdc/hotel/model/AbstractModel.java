package com.jdc.hotel.model;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

public abstract class AbstractModel<T> {

	protected abstract Class<T> getType();

	protected abstract EntityManager getEm();

	public void save(T t) {

		try {

			Field[] fs = getType().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				if (null != f.getAnnotation(Id.class)) {
					Object id = f.get(t);
					if (null == findById(id)) {
						getEm().persist(t);
					} else {
						getEm().merge(t);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public T findById(Object id) {
		return getEm().find(getType(), id);
	}

	public List<T> findByQuery(String sql, Optional<Map<String, Object>> params) {
		TypedQuery<T> q = getEm().createQuery(sql, getType());
		params.ifPresent(p -> p.keySet().forEach(k -> q.setParameter(k, p.get(k))));
		return q.getResultList();
	}

	public List<T> findByNameQuery(String name, Optional<Map<String, Object>> params) {
		TypedQuery<T> q = getEm().createNamedQuery(name, getType());
		params.ifPresent(p -> p.keySet().forEach(k -> q.setParameter(k, p.get(k))));
		return q.getResultList();
	}

}
