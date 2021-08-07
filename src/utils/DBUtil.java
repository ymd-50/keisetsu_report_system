package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import constants.JpaConst;

public class DBUtil {
    private static EntityManagerFactory emf;
    
    /**
     * @return EntityManagerインスタンス
     */
    public static EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    /**
     * @return EntityManagerFactoryインスタンス
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory(JpaConst.PERSISTENCE_UNIT_NAME);
        }
        
        return emf;
    }
}
