package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
//        userDao.createUsersTable();
//        userDao.saveUser("Dima", "Mironov",  (byte)36);
//        userDao.saveUser("Valera", "Churlyaev",  (byte)28);
//        userDao.saveUser("Leo", "Messi",  (byte)37);
//        userDao.saveUser("Alex", "Ovechkin",  (byte)39);
//        userDao.removeUserById(1);
        //userDao.getAllUsers();
        //userDao.cleanUsersTable();
        //userDao.dropUsersTable();

        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Dima", "Mironov",  (byte)36);
        userDao.saveUser("Valera", "Churlyaev",  (byte)28);
        userDao.saveUser("Leo", "Messi",  (byte)37);
        userDao.saveUser("Alex", "Ovechkin",  (byte)39);
        userDao.getAllUsers();
    }
}
