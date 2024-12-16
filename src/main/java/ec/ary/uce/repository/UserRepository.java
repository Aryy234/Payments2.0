package ec.ary.uce.repository;

import ec.ary.uce.interfaces.CrudRepository;
import ec.ary.uce.util.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UserRepository implements CrudRepository<User> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> list() throws Exception {
        return this.em.createQuery("SELECT u FROM User u", User.class).
                getResultList();

    }

    @Override
    public User byId(Long id) throws Exception {
        return this.em.find(User.class, id);
    }

    @Override
    public void save(User t) throws Exception {
        this.em.getTransaction().begin();
        this.em.persist(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void update(User t) throws Exception {
        this.em.getTransaction().begin();
        this.em.merge(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = this.em.find(User.class, id);
        if (user != null) {
            this.em.getTransaction().begin();
            this.em.remove(user);
            this.em.getTransaction().commit();
        }
    }




}
