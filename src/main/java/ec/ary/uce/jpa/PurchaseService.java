package ec.ary.uce.jpa;

import ec.ary.uce.util.Purchase;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PurchaseService {

    private EntityManager em;

    public PurchaseService(EntityManager em) {
        this.em = em;
    }

    public void createPurchase(Purchase purchase) {
        this.em.getTransaction().begin();
        this.em.persist(purchase);
        this.em.getTransaction().commit();
    }

    public void updatePurchase(Purchase purchase) {
        this.em.getTransaction().begin();
        this.em.merge(purchase);
        this.em.getTransaction().commit();
    }

    public void deletePurchase(Purchase purchase) {
        this.em.getTransaction().begin();
        this.em.remove(purchase);
        this.em.getTransaction().commit();
    }

    public Purchase getPurchaseById(int id) {

        return this.em.find(Purchase.class, id);
    }

    public List<Purchase> getAllPurchases() {
        return this.em.createQuery("SELECT p FROM Purchase p", Purchase.class).getResultList();
    }

    public List<Purchase> getPurchasesByUserId(int userId) {
        return this.em.createQuery("SELECT p FROM Purchase p WHERE p.user.id = :userId", Purchase.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
