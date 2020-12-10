package com.projects.patryshop.controller;

import com.projects.patryshop.entities.Cake;
import com.projects.patryshop.exceptions.EntityNotFoundException;
import com.projects.patryshop.exceptions.ResourceNotFoundException;
import com.projects.patryshop.services.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CakeController {

    @Autowired
    private CakeService service;

    @GetMapping("/cakes")
    public List<Cake> getAllCakes() {
        return service.readAll();
    }

    @GetMapping("/cakes/{id}")
    public ResponseEntity<Cake> getCakeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Cake cake = null;
        try {
            cake = service.read(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cake not found for this id :: " + id);
        }
        return ResponseEntity.ok(cake);
    }

    @PostMapping("/cakes")
    public Cake createCake(@RequestBody Cake cake) {
        return service.create(cake);
    }

    @PutMapping("/cakes/{id}")
    public ResponseEntity<Cake> updateCake(@PathVariable(value = "id") Long id, @RequestBody Cake cake) throws ResourceNotFoundException {
        Cake updatedCake = null;
        try {
            updatedCake = service.update(id, cake);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cake not found for this id :: " + id);
        }
        return ResponseEntity.ok(updatedCake);
    }

    @DeleteMapping("/cakes/{id}")
    public Map<String, Boolean> deleteCake(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Boolean deleted;
        try {
            deleted = service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cake not found for this id :: " + id);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return response;
    }
}
