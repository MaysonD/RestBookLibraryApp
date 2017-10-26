package com.task.dao;

import com.task.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User clientEntity);

    User findById(int id);

    void removeUser(int id);

    void updateUser(User clientEntity);
}
