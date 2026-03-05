package br.com.anderson.controller;

<<<<<<< HEAD
import br.com.anderson.dto.CakeDTO;
import br.com.anderson.entity.Cake;
import br.com.anderson.service.CakeService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

=======
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.anderson.CakeApplication;
import br.com.anderson.entity.Cake;
import br.com.anderson.repository.CakeRepository;
import br.com.anderson.service.CakeService;

>>>>>>> 2c13ec2a2feece9ad77257beb07015a809076648
@RestController
@RequestMapping("/api/cakes")
@CrossOrigin(origins = "*")
public class CakeController {

<<<<<<< HEAD
    private final CakeService service;

    public CakeController(CakeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cake> create(@Valid @RequestBody CakeDTO dto) {
        Cake cake = service.fromDTO(dto);
        Cake saved = service.create(cake);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Cake>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cake> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cake> update(@PathVariable Long id, @RequestBody Cake cake) {
        return ResponseEntity.ok(service.update(id, cake));
=======
    private final CakeApplication cakeApplication;

    private final CakeRepository cakeRepository;

    private final CakeService service;

    public CakeController(CakeService service, CakeRepository cakeRepository, CakeApplication cakeApplication) {
        this.service = service;
        this.cakeRepository = cakeRepository;
        this.cakeApplication = cakeApplication;
    }

    @PostMapping
    public Cake create(@RequestBody Cake cake) {
        return service.create(cake);
    }

    @GetMapping
    public List<Cake> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cake findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PutMapping("/{id}")
    public Cake update(@PathVariable Long id, @RequestBody Cake cake) {
        return service.update(id, cake);
    }
    
    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
>>>>>>> 2c13ec2a2feece9ad77257beb07015a809076648
    }
}