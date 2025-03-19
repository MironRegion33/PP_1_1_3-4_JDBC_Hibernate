package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = Util.getInstance().getMySessionFactory();

    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("""
                                    CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255),
                                    last_name VARCHAR(255),
                                    age TINYINT
                            )""")
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table created");
        } catch (HibernateException ignored) {
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user")
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table dropped");
        } catch (HibernateException ignored) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("User с id - " + id + " удален из базы данных");
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table cleaned");
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }
}
