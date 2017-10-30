package com.task.service;

import com.task.dao.BookDao;
import com.task.dao.BookDaoImpl;
import com.task.dao.UserDao;
import com.task.dao.UserDaoImpl;
import com.task.model.Book;
import com.task.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookService {

    private BookDao bookDao = new BookDaoImpl();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> getBooks() {
        return bookDao.getNotAssignedBooks();
    }

    @GET
    @Path("/{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Book getBook(@PathParam("bookId") int bookId) {
        return bookDao.findById(bookId);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response addBook(@QueryParam("name") String name,
                            @QueryParam("author") String author) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        bookDao.saveBook(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{bookId}")
    public Response updateBook(@PathParam("bookId") int bookId,
                               @QueryParam("name") String name,
                               @QueryParam("author") String author) {
        Book book = bookDao.findById(bookId);
        book.setName(name);
        book.setAuthor(author);
        bookDao.updateBook(book);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteBook(@PathParam("bookId") int bookId) {
        bookDao.removeBook(bookId);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{bookId}")
    public Response assignBook(@PathParam("bookId") int bookId,
                               @QueryParam("userId") int userId) {
        Book book = bookDao.findById(bookId);
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(userId);
        bookDao.assignToUser(book, user);
        return Response.status(Response.Status.OK).build();
    }
}
