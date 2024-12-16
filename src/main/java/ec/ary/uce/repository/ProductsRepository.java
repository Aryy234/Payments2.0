package ec.ary.uce.repository;

import ec.ary.uce.annotations.Repository;
import ec.ary.uce.interfaces.CrudRepository;
import ec.ary.uce.util.Products;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public class ProductsRepository implements CrudRepository<Products> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Products> list() throws Exception {
        return this.em.createQuery("SELECT p FROM Products p", Products.class).
                getResultList();

    }

    @Override
    public Products byId(Long id) throws Exception {
        return this.em.find(Products.class, id);
    }

    @Override
    public void save(Products t) throws Exception {
        this.em.getTransaction().begin();
        this.em.persist(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void update(Products t) throws Exception {
        this.em.getTransaction().begin();
        this.em.merge(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws Exception {
        Products product = this.em.find(Products.class, id);
        if (product != null) {
            this.em.getTransaction().begin();
            this.em.remove(product);
            this.em.getTransaction().commit();
        }
    }


}
