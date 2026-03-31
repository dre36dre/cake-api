package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entity.Pedido;

public interface OrderItemRepository  extends JpaRepository<Pedido, Long> {

}
