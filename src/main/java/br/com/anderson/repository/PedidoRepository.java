package br.com.anderson.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
