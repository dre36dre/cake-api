package br.com.anderson.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.entities.Cake;
import br.com.anderson.service.CakeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cakes")
public class CakeController {

    private final CakeService service;

    public CakeController(CakeService service) {
        this.service = service;
    }

    @PostMapping
    public Cake create(@RequestBody @Valid Cake cake) {
        return service.create(cake);
    }

    @GetMapping
    public List<Cake> listAll() {
        return service.findAll();
    }
}