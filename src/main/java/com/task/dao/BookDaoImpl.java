package com.task.dao;

import com.task.model.Book;
import com.task.model.User;
import com.task.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public List<Book> getNotAssignedBooks() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        String query = "";
        if (System.getProperty("storage").equals("mongo")) {
            query = "db.books.find({user_id: null })";
        }
        if (System.getProperty("storage").equals("mysql")) {
            query = "SELECT * from books WHERE user_id is NULL";
        }
        List<Book> books = entityManager.createNativeQuery(query, Book.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return books;
    }

    @Override
    public void saveBook(Book book) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Book findById(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }

    @Override
    public void removeBook(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateBook(Book book) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void assignToUser(Book book, User user) {
        book.setUser(user);
        updateBook(book);
    }
}
