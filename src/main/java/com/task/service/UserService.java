package com.task.service;

import com.task.dao.UserDao;
import com.task.dao.UserDaoImpl;
import com.task.model.Book;
import com.task.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserService {

    private UserDao userDao = new UserDaoImpl();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @GET
    @Path("/{userId}/books")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> getUserBooks(@PathParam("userId") int userId) {
        User user = userDao.findById(userId);
        return user.getBooks();
    }

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("userId") int userId) {
        return userDao.findById(userId);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public void addUser(@QueryParam("name") String name,
                        @QueryParam("password") String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.saveUser(user);
    }

    @PUT
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void updateUser(@PathParam("userId") int userId,
                           @QueryParam("name") String name,
                           @QueryParam("password") String password) {
        User user = userDao.findById(userId);
        user.setName(name);
        user.setPassword(password);
        userDao.updateUser(user);
    }

    @DELETE
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteUser(@PathParam("userId") int userId) {
        userDao.removeUser(userId);
    }
}
