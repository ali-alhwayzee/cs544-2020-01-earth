package com.cs544.dao;

import com.cs544.domain.Location;

import java.util.List;

public interface ILocationDao {

    boolean save(Location location);
    List<Location> findAll();
}