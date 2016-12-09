package br.com.fwtj.hibernatesearch;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fredw
 */
public class JPAUtil {

    private static EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("HibernateSearch", PersistenceProperties.get());
        } catch (Exception ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entityManagerFactory.createEntityManager();
    }

}
