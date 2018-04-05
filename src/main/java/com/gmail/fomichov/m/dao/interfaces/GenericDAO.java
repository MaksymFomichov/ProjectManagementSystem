package com.gmail.fomichov.m.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, ID> {
    T getById(ID id) throws SQLException;
    List<T> getAll() throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    void create(T t) throws SQLException;
}
