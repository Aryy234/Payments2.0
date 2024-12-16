package ec.ary.uce.jpa;

import ec.ary.uce.jpaUtil.EntityManagerUtil;
import ec.ary.uce.util.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UserService {

    private final EntityManager em;

    public UserService() {
        this.em = EntityManagerUtil.getEntityManager();
    }

    public UserService(EntityManager em) {
        this.em = em;
    }

    public void createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteUser(int userId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUserById(int userId) {
        return em.find(User.class, userId);
    }
}
