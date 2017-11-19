package com.jdc.account.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.jdc.account.entity.Category;


public class CategoryService {
	
	@Inject
	@Named
	private EntityManager em;
	
	public List<Category> getAllCategory() {
		return em.createNamedQuery("Category.getAll", Category.class).getResultList();
	}
}
