package ec.ary.uce.jpa;

import ec.ary.uce.util.Products;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductsService {

    private EntityManager em;

    public ProductsService(EntityManager em) {
        this.em = em;
    }

    public void createProduct(Products product) {
        this.em.getTransaction().begin();
        this.em.persist(product);
        this.em.getTransaction().commit();
    }

    public void updateProduct(Products product) {
        this.em.getTransaction().begin();
        this.em.merge(product);
        this.em.getTransaction().commit();
    }

    public void deleteProduct(int productId) {
        this.em.getTransaction().begin();
        Products product = this.em.find(Products.class, productId);
        if (product != null) {
            this.em.remove(product);
        }
        this.em.getTransaction().commit();

    }

    public Products getProductById(int id) {

        return this.em.find(Products.class, id);
    }

    public List<Products> getAllProducts() {
        return this.em.createQuery("SELECT p FROM Products p", Products.class).getResultList();
    }

}
