package com.jdc.account.utils;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	private static EntityManagerFactory EMF;
	
	static {
		EMF = Persistence.createEntityManagerFactory("mini-acccount");
	}
	
	@Produces
	@Named("em")
	public EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
	public void despose(@Disposes EntityManager em) {
		if(null != em)
			em.close();
	}
	
	public static void close() {
		EMF.close();
	}
}
