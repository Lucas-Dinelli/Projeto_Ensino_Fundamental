package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManager {
	//
	// ATRIBUTOS
	//
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PuMvcJpa");
	private static EntityManager entityManager;

	public static EntityManager getEntityManager() {
		if (entityManager == null)
			entityManager = factory.createEntityManager();
		return entityManager;
	}
}
