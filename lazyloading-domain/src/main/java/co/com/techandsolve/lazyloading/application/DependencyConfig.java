package co.com.techandsolve.lazyloading.application;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class DependencyConfig {

	@PersistenceContext(name = "lazyloadingDS", type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	@Produces
	@Dependent
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
