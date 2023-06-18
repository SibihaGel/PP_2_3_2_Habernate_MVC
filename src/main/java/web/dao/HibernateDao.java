package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateDao implements DAO {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }
    @Override
    public List<User> AllUser() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }
    @Override
    @Transactional
    public void updateUser(int id, User updateUser) {

        User userToBeUpdated = entityManager.merge(updateUser);

        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setEmail(updateUser.getEmail());
    }
    @Override
    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
   public User getUserById(int id) { return entityManager.find(User.class, id);
    }
}
