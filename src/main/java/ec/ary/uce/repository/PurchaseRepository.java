package ec.ary.uce.repository;

import ec.ary.uce.interfaces.CrudRepository;
import ec.ary.uce.util.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class PurchaseRepository implements CrudRepository<Purchase> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Purchase t) throws Exception {
        this.em.getTransaction().begin();
        this.em.persist(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void update(Purchase t) throws Exception {
        this.em.getTransaction().begin();
        this.em.merge(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws Exception {
        Purchase purchase = this.em.find(Purchase.class, id);
        if (purchase != null) {
            this.em.getTransaction().begin();
            this.em.remove(purchase);
            this.em.getTransaction().commit();
        }
    }

    @Override
    public Purchase byId(Long id) throws Exception {
        return this.em.find(Purchase.class, id);
    }

    @Override
    public List<Purchase> list() throws Exception {
        return this.em.createQuery("SELECT p FROM Purchase p", Purchase.class).getResultList();
    }





}
