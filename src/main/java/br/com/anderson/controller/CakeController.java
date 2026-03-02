package br.com.anderson.controller;

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

@RestController
@RequestMapping("/api/cakes")
@CrossOrigin(origins = "*")
public class CakeController {

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
    }
}