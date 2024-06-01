package com.javafx_database.model.DAO;

import java.util.List;

public interface IDataAccessObject<T> {
    public T getById(int id);
    public List<T> getAll();
    public boolean add(T t);
    public boolean delete(T t);
}
