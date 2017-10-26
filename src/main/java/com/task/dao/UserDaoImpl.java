package com.task.dao;


import com.task.model.User;
import com.task.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        String query = "";
        if (System.getProperty("storage").equals("mongo")) {
            query = "db.users.find({})";
        }
        if (System.getProperty("storage").equals("mysql")) {
            query = "SELECT * from users";
        }
        List<User> accounts = entityManager.createNativeQuery(query, User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return accounts;
    }

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User entity = entityManager.find(User.class, id);
        entityManager.close();
        return entity;
    }

    @Override
    public void removeUser(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User entityForRemove = entityManager.find(User.class, id);
        entityManager.remove(entityForRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
