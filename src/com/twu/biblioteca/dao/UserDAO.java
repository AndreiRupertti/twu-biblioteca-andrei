package com.twu.biblioteca.dao;

import com.twu.biblioteca.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO implements BaseDAO<User> {
    public final static List<User> ALL_USERS = new ArrayList<>(Arrays.asList(new User[] {
            new User("555-6666", "101010", "Tamara", "tata@thopughtworks.com","4002-8222")
    }));

    public UserDAO() {}

    public List<User> getAll() {
        return ALL_USERS;
    }
}
