package br.com.anderson.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.entity.Pedido;
import br.com.anderson.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/cliente/{id}")
    public List<Pedido> listarPorCliente(@PathVariable Long id) {
        return service.listarPorCliente(id);
    }
    
    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        return service.salvar(pedido);
    }

    
}