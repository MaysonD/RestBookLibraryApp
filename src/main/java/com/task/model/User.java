package com.task.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "user", targetEntity = Book.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        book.setUser(this);
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void removeBook(Book book) {
        book.setUser(this);
        books.remove(book);
    }

    public Book findAccount(int id) {
        Book accountEntity = new Book();
        List<Book> accounts = getBooks();
        for (int i = 0; i < getBooks().size(); i++) {
            if (id == accounts.get(i).getId()) {
                accountEntity = accounts.get(i);
            }
        }
        return accountEntity;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
