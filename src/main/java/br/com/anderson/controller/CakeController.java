package br.com.anderson.controller;

import br.com.anderson.dto.CakeDTO;
import br.com.anderson.entity.Cake;
import br.com.anderson.service.CakeService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cakes")
@CrossOrigin(origins = "*")
public class CakeController {

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
    }
}