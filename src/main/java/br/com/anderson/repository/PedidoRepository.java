package br.com.anderson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByClienteId(Long clienteId);

}
