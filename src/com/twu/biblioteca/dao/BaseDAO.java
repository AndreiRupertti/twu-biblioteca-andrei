package com.twu.biblioteca.dao;

import java.util.List;

public interface BaseDAO<T> {

    List<T> getAll();
}
