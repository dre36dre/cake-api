package br.com.anderson.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.anderson.entity.Pedido;
import br.com.anderson.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }
}