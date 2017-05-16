package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.dao;

import java.util.List;

public interface InterfaseDataBaseHandler<T> {
    void add(T t);
    T  getById(int id);
    List<T> getAll();
    int update(T t);
    void deleteById(int id);
    void deleteAll();
}
