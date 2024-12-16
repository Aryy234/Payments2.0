package ec.ary.uce.jpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("PaymentUnity");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
