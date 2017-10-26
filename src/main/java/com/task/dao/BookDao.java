package com.task.dao;

import com.task.model.Book;
import com.task.model.User;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks();

    void saveBook(Book book);

    Book findById(int id);

    void removeBook(int id);

    void updateBook(Book book);

    void assignToUser(Book book, User user);
}
