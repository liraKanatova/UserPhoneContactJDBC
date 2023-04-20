package peaksoft.service.serviceImpl;

import peaksoft.dao.UserDao;
import peaksoft.dao.daoImpl.UserDaoImpl;
import peaksoft.models.User;
import peaksoft.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void createUserTable() {
        userDao.createUserTable();

    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUserInfo(Long id, User user) {
userDao.updateUserInfo(id, user);
    }

    @Override
    public void cleanUserTable() {
userDao.cleanUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();

    }

    @Override
    public void deleteById(Long id) {
userDao.deleteById(id);
    }

    @Override
    public List<User> getAllSortedUsers(String ascOrDeck) {
        return userDao.getAllSortedUsers(ascOrDeck);
    }
}
