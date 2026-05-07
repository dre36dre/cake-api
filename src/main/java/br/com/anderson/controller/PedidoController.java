package br.com.anderson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.entities.Pedido;
import br.com.anderson.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repo;

    @PostMapping
    public ResponseEntity<String> receber(@RequestBody Pedido pedido) {
        repo.save(pedido);
        return ResponseEntity.ok("Pedido recebido");
    }

    @GetMapping
    public List<Pedido> listar() {
        return repo.findAll();
    }
}
