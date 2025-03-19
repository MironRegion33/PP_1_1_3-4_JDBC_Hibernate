package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class Util {

    static volatile Util INSTANCE = null;
    static final String URL = "jdbc:mysql://localhost:3302/mydatabase";
    static final String USERNAME = "myuser";
    static final String PASSWORD = "mypassword";
    static SessionFactory sessionFactory;

    public static Util getInstance() {
        if (INSTANCE == null) {
            synchronized (Util.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Util();
                }
            }
        }
        return INSTANCE;
    }

    private Util() {
    }

    public SessionFactory getMySessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USERNAME)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .setProperty("hibernate.current_session_context_class", "thread")
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                return configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
