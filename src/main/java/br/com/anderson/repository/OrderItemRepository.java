package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entity.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {

}
