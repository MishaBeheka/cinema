package com.dev.cinema.dao;

import com.dev.cinema.model.DbFile;

public interface DbFileDao {

    DbFile add(DbFile dbFile);

    DbFile getById(Long id);
}
