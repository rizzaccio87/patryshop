package com.projects.patryshop.services;

import com.projects.patryshop.entities.Cake;
import com.projects.patryshop.exceptions.EntityNotFoundException;
import com.projects.patryshop.repos.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CakeServiceImpl implements CakeService {

    @Autowired
    private CakeRepository repository;

    @Override
    public Cake create(Cake cake) {
        return repository.saveAndFlush(cake);
    }

    @Override
    public List<Cake> readAll() {
        return repository.findAll();
    }

    @Override
    public Cake read(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cake not found for this id :: " + id));
    }

    @Override
    public Cake update(Long id, Cake cake) throws EntityNotFoundException {
        Cake retrievedCake = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cake not found for this id :: " + id));
        retrievedCake.setName(cake.getName());
        retrievedCake.setPrice(cake.getPrice());
        retrievedCake.setIngredients(cake.getIngredients());
        return repository.save(retrievedCake);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        Cake retrievedCake = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cake not found for this id :: " + id));
        repository.delete(retrievedCake);
        return true;
    }

}
