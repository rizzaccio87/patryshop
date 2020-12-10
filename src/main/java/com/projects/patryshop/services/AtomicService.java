package com.projects.patryshop.services;

import com.projects.patryshop.exceptions.EntityNotFoundException;

import java.util.List;

public interface AtomicService<Entity> {

    public Entity create(Entity entity);
    public List<Entity> readAll();
    public Entity read(Long id) throws EntityNotFoundException;
    public Entity update(Long id, Entity entity) throws EntityNotFoundException;
    public boolean delete(Long id) throws EntityNotFoundException;

}
