package br.com.anderson.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.anderson.dto.CakeDTO;
import br.com.anderson.entity.Cake;
import br.com.anderson.exception.ResourceNotFoundException;
import br.com.anderson.repository.CakeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.anderson.entity.Cake;
import br.com.anderson.repository.CakeRepository;

@Service
public class CakeService {

    private final CakeRepository repository;

    public CakeService(CakeRepository repository) {
        this.repository = repository;
    }

    public Cake create(Cake cake) {

        if (cake.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return repository.save(cake);
    }

    public List<Cake> findAll() {
        return repository.findAll();
    }
    

    public Cake findById(Long id) {
        return repository.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Cake not found"));
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Cake update(Long id, Cake updatedCake) {

        Cake existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cake not found"));

        existing.setName(updatedCake.getName());
        existing.setDescription(updatedCake.getDescription());
        existing.setPrice(updatedCake.getPrice());
        existing.setAvailable(updatedCake.getAvailable());

        return repository.save(existing);
    }

    public Cake fromDTO(CakeDTO dto) {
        Cake cake = new Cake();
        cake.setName(dto.getName());
        cake.setDescription(dto.getDescription());
        cake.setPrice(dto.getPrice());
        return cake;
    }
}
