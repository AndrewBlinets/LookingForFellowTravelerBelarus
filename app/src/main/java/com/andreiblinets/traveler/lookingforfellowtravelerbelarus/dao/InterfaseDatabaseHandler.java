package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import java.util.List;

public interface InterfaseDatabaseHandler<T> {
    public void create(T t);
    public T  getById(int id);
    public List<T> getAll();
    public int update(T t);
    public void deleteById(int id);
    public void deleteAll();
}
