package br.com.anderson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.anderson.entities.Pedido;
import br.com.anderson.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Optional<Pedido> listarPorCliente(Long clienteId) {
        return repository.findById(clienteId);
    }

    public Pedido salvar(Pedido pedido) {
        return repository.save(pedido);
    }

}