package web.dao;

import web.models.User;
import java.util.List;

public interface DAO {
    void saveUser(User user);
    List<User> AllUser();
    void updateUser(int id, User updateUser);
    void delete(int id);
    User getUserById(int id);

}