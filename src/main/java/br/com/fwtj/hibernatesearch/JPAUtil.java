package br.com.fwtj.hibernatesearch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fredw
 */
public class JPAUtil {
    
    private static EntityManagerFactory entityManagerFactory = 
            Persistence.createEntityManagerFactory("HibernateSearch");
    
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    
    
}
